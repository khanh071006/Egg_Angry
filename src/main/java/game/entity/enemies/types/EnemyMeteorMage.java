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
import godot.core.Vector2;

@RegisterClass
public class EnemyMeteorMage extends Enemy {

    @Export
    @RegisterProperty
    public PackedScene effectScene;

    private AnimatedSprite2D meteorAnim;
    
    private boolean isAttacking = false;
    private double attackTimer = 0.0;
    private double cooldownTimer = 0.0;

    @RegisterFunction
    @Override
    public void _ready() {
        super._ready();
        
        godot.api.Node animNode = getNode("%MeteorMageAnimation");
        if (animNode instanceof AnimatedSprite2D) {
            meteorAnim = (AnimatedSprite2D) animNode;
        } else {
            animNode = getNode("Visuals/MeteorMageAnimation");
            if (animNode instanceof AnimatedSprite2D) {
                meteorAnim = (AnimatedSprite2D) animNode;
            }
        }
        
        if (effectScene == null) {
            effectScene = (PackedScene) godot.global.GD.load("res://scenes/unit/EnemyCreate/enemy_meteor_mage_effect.tscn");
        }
    }

    @RegisterFunction
    @Override
    public void _physicsProcess(double delta) {
        if (Global.gamePaused || !canAttack) return;
        
        if (isAttacking) {
            attackTimer -= delta;
            canMove = false; // Đứng yên khi rặn chiêu
            
            if (attackTimer <= 0.0) {
                spawnMeteor();
                isAttacking = false;
                canMove = true;
                cooldownTimer = 3.0; // Cooldown 3s
                if (meteorAnim != null) meteorAnim.play(new StringName("move"), 1.0f, false);
            }
        } else {
            canMove = true;
            super._physicsProcess(delta); // Gọi hàm di chuyển cơ bản
            
            if (cooldownTimer > 0) {
                cooldownTimer -= delta;
            } else if (Global.isAttack && Global.player != null && godot.global.GD.isInstanceValid(Global.player) && !Global.player.isQueuedForDeletion()) {
                float dist = (float) getGlobalPosition().distanceTo(Global.player.getGlobalPosition());
                if (dist < 250.0f) {
                    startAttack();
                }
            }
        }
    }

    private void startAttack() {
        isAttacking = true;
        attackTimer = 0.5; // Chờ 0.5s Animation rồi mới thả thiên thạch
        if (meteorAnim != null) meteorAnim.play(new StringName("attack"), 1.0f, false);
    }

    private void spawnMeteor() {
        if (effectScene != null && Global.player != null && godot.global.GD.isInstanceValid(Global.player) && !Global.player.isQueuedForDeletion()) {
            godot.api.Node instance = effectScene.instantiate();
            if (instance instanceof Node2D) {
                getTree().getRoot().addChild(instance);
                Node2D effectNode = (Node2D) instance;
                // Thiên thạch rơi xuống ngay trên đầu người chơi (có thể offset lên trên một chút nếu ảnh đã có y-offset)
                effectNode.setGlobalPosition(Global.player.getGlobalPosition());
                
                // Đảo hướng effect theo hướng của Mage (thực ra thiên thạch từ trên trời rớt xuống thì đảo hay không cũng không quan trọng lắm, nhưng cứ đồng nhất)
                if (visuals != null && visuals.getScale().getX() < 0) {
                    effectNode.setScale(new Vector2(1.0f, 1.0f));
                } else {
                    effectNode.setScale(new Vector2(-1.0f, 1.0f));
                }
            }
        }
    }
}
