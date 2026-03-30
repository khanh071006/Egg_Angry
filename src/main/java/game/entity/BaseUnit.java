package game.entity;

import game.components.HealthComponent;
import game.components.HitBoxComponent;
import game.resources.UnitStats;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.AnimationPlayer;
import godot.api.Area2D;
import godot.api.Node2D;
import godot.api.Sprite2D;
import godot.global.GD;

@RegisterClass
public class BaseUnit extends Area2D { // Đổi tên class ở đây
    //add stat
    @Export
    @RegisterProperty
    public UnitStats stats;

	protected Node2D visuals;
	protected Sprite2D sprite;
	protected AnimationPlayer animPlayer;
    private HealthComponent healthComponent;
	@RegisterFunction
	@Override
	public void _ready() {
		visuals = (Node2D) getNode("%Visuals");
		sprite = (Sprite2D) getNode("%Sprite");
		animPlayer = (AnimationPlayer) getNode("AnimationPlayer");


        healthComponent = (HealthComponent) getNode("HealthComponent");


        // Gọi setup và truyền biến stats của Unit vào (như video)
        if (healthComponent != null && this.stats != null) {
            healthComponent.setup(this.stats);
        }

	}

    @RegisterFunction
    public void _on_hurtbox_component_on_damage(HitBoxComponent hitbox) {
        // 2. Kiểm tra máu trước khi trừ
        if (healthComponent.currentHealth <= 0) {
            return;
        }

        healthComponent.takeDamage(hitbox.damage);
        GD.print(getName()+" "+ healthComponent.currentHealth);
    }
}
