package game.entity.enemies.behaviors;

import game.entity.enemies.core.Enemy;

import game.components.HealthComponent;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Node;
import godot.api.Node2D;
import godot.api.PackedScene;
import godot.core.Callable;
import godot.core.StringName;
import godot.core.Vector2;
import godot.global.GD;

@RegisterClass
public class SplitterBehavior extends Node2D {

    @Export
    @RegisterProperty
    public Enemy enemy;

    @Export
    @RegisterProperty
    public PackedScene spawnScene; // Scene quái nhỏ (Micro-Chaser)

    @Export
    @RegisterProperty
    public int spawnCount = 3; // Số lượng quái nhỏ sinh ra khi chết

    @RegisterFunction
    @Override
    public void _ready() {
        if (enemy == null) {
            Node parent = getParent();
            if (parent instanceof Enemy) {
                enemy = (Enemy) parent;
            }
        }

        if (enemy != null) {
            HealthComponent health = (HealthComponent) enemy.getNode("HealthComponent");
            if (health != null) {
                health.onUnitDie.connect(Callable.create(this, new StringName("on_parent_die")), 0);
            } else {
                GD.printErr("SplitterBehavior: Không tìm thấy HealthComponent trên enemy!");
            }
        } else {
            GD.printErr("SplitterBehavior: Không tìm thấy Enemy node cha!");
        }
    }

    @RegisterFunction
    public void on_parent_die() {
        if (enemy == null || spawnScene == null) {
            return;
        }

        Vector2 spawnPosition = enemy.getGlobalPosition();
        Node levelParent = enemy.getParent();

        if (levelParent == null) {
            return;
        }

        double angleStep = (Math.PI * 2.0) / spawnCount;
        float radius = 40.0f; // Bán kính sinh quái con lệch khỏi tâm quái cha

        for (int i = 0; i < spawnCount; i++) {
            Node spawnInstance = spawnScene.instantiate();
            if (spawnInstance instanceof Node2D) {
                Node2D childEnemy = (Node2D) spawnInstance;
                
                // Tính toán vị trí lệch để tránh việc trùng khít tọa độ
                double currentAngle = angleStep * i;
                Vector2 offset = new Vector2(Math.cos(currentAngle) * radius, Math.sin(currentAngle) * radius);
                childEnemy.setGlobalPosition(spawnPosition.plus(offset));
                
                // Thêm con quái con vào màn chơi (dùng callDeferred để tránh lỗi flushing queries của Physics)
                levelParent.callDeferred(new StringName("add_child"), childEnemy);
            }
        }
        
        GD.print("SplitterBehavior: Đã sinh ra " + spawnCount + " quái con sau khi quái cha chết!");
    }
}
