package game.entity.enemies.types;

import game.autoloads.Global;
import game.entity.enemies.core.Enemy;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.AnimatedSprite2D;
import godot.api.Node2D;
import godot.api.PackedScene;
import godot.core.StringName;

@RegisterClass
public class EnemyEggLayer extends Enemy {

    @Export
    @RegisterProperty
    public PackedScene eggScene;

    private AnimatedSprite2D anim;
    
    private boolean isAttacking = false;
    private double attackTimer = 0.0;
    private double spawnCooldown = 4.0; // Đẻ trứng mỗi 4 giây

    @RegisterFunction
    @Override
    public void _ready() {
        super._ready();
        
        godot.api.Node animNode = getNode("%EggLayerAnimation");
        if (animNode instanceof AnimatedSprite2D) {
            anim = (AnimatedSprite2D) animNode;
        } else {
            animNode = getNode("Visuals/EggLayerAnimation");
            if (animNode instanceof AnimatedSprite2D) {
                anim = (AnimatedSprite2D) animNode;
            }
        }
        
        if (eggScene == null) {
            eggScene = (PackedScene) godot.global.GD.load("res://scenes/unit/EnemyCreate/enemy_egg.tscn");
        }
    }

    @RegisterFunction
    @Override
    public void _physicsProcess(double delta) {
        if (Global.gamePaused || !canAttack) return;
        
        if (isAttacking) {
            attackTimer -= delta;
            canMove = false; // Đứng yên khi rặn đẻ
            
            if (attackTimer <= 0.0) {
                spawnEgg();
                isAttacking = false;
                canMove = true;
                spawnCooldown = 5.0; // Cooldown 5s cho lần đẻ tiếp theo
                if (anim != null) anim.play(new StringName("move"), 1.0f, false);
            }
        } else {
            canMove = true;
            super._physicsProcess(delta); // Gọi hàm di chuyển cơ bản
            
            if (spawnCooldown > 0) {
                spawnCooldown -= delta;
            } else {
                startAttack(); // Hết cooldown thì tự động dừng lại đẻ trứng
            }
        }
    }

    private void startAttack() {
        isAttacking = true;
        attackTimer = 0.5; // Chờ 0.5s Animation rồi mới đẻ
        if (anim != null) anim.play(new StringName("attack"), 1.0f, false);
    }

    private void spawnEgg() {
        if (eggScene != null) {
            godot.api.Node instance = eggScene.instantiate();
            if (instance instanceof Node2D) {
                // Đưa quả trứng ra ngoài màn chơi để không đi theo mẹ
                getTree().getRoot().addChild(instance);
                Node2D eggNode = (Node2D) instance;
                // Đặt trứng ngay dưới chân
                eggNode.setGlobalPosition(getGlobalPosition());
            }
        }
    }
}
