package game.entity.enemies.types;

import game.autoloads.Global;
import game.entity.enemies.core.Enemy;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.api.AnimatedSprite2D;
import godot.api.PackedScene;
import godot.api.ResourceLoader;
import godot.core.StringName;
import godot.core.Vector2;

@RegisterClass
public class EnemyBomber extends Enemy {

    private PackedScene effectScene;
    private boolean isExploding = false;
    private AnimatedSprite2D bomberAnim;

    @RegisterFunction
    @Override
    public void _ready() {
        super._ready();

        // Tải scene hiệu ứng nổ
        effectScene = (PackedScene) ResourceLoader.load("res://scenes/unit/EnemyCreate/enemy_bomber_effect.tscn");
        bomberAnim = (AnimatedSprite2D) getNode("%BomberAnimation");
        if (bomberAnim == null) {
            // Thử lấy bằng đường dẫn thường nếu UniqueName bị lỗi
            godot.api.Node node = getNode("Visuals/BomberAnimation");
            if (node instanceof AnimatedSprite2D)
                bomberAnim = (AnimatedSprite2D) node;
        }
    }

    @RegisterFunction
    @Override
    public void _physicsProcess(double delta) {
        super._physicsProcess(delta); // Gọi logic lùa Player và đẩy lùi của Enemy

        if (Global.gamePaused)
            return;
        if (!canAttack || isExploding)
            return;

        // Logic check khoảng cách để phát nổ
        if (Global.isAttack && Global.player != null && godot.global.GD.isInstanceValid(Global.player)
                && !Global.player.isQueuedForDeletion()) {
            float dist = (float) getGlobalPosition().distanceTo(Global.player.getGlobalPosition());

            if (dist < 100.0f) {
                explode();
            }
        }
    }

    private void explode() {
        isExploding = true;

        // Đẻ ra effect ở vị trí của Player để chắc chắn trúng
        if (effectScene != null) {
            godot.api.Node instance = effectScene.instantiate();
            if (instance instanceof godot.api.Node2D) {
                getTree().getRoot().addChild(instance);
                godot.api.Node2D effectNode = (godot.api.Node2D) instance;
                if (Global.player != null && godot.global.GD.isInstanceValid(Global.player)) {
                    effectNode.setGlobalPosition(Global.player.getGlobalPosition());
                } else {
                    effectNode.setGlobalPosition(getGlobalPosition());
                }
            }
        }

        // Tự hủy
        queueFree();
    }
}
