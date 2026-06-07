package game.entity.enemies.types;

import game.entity.enemies.core.Enemy;

import game.autoloads.Global;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.api.AnimatedSprite2D;
import godot.api.PackedScene;
import godot.api.ResourceLoader;
import godot.core.StringName;
import godot.core.Vector2;

@RegisterClass
public class EnemyGoblin extends Enemy {

    private AnimatedSprite2D goblinAnim;
    private PackedScene effectScene;
    
    private double attackCooldown = 3.0; // 3 giây đánh 1 lần
    private double currentCooldown = 0.0;
    private boolean isAttacking = false;
    private double attackTimer = 0.0; // Thời gian vung gậy
    private boolean effectSpawnedThisAttack = false;

    @RegisterFunction
    @Override
    public void _ready() {
        super._ready();
        
        godot.api.Node visualsNode = getNode("%Visuals");
        if (visualsNode != null) {
            goblinAnim = (AnimatedSprite2D) visualsNode.getNodeOrNull("GoblinAnimation");
        }
        
        effectScene = (PackedScene) ResourceLoader.load("res://scenes/unit/EnemyCreate/goblin_effect.tscn");
    }

    @RegisterFunction
    @Override
    public void _physicsProcess(double delta) {
        super._physicsProcess(delta); // Gọi logic lùa Player và đẩy lùi của Enemy

        if (Global.gamePaused) return;
        
        // Không thể di chuyển và không thể tấn công = đang chết hoặc bị stun cứng
        if (!canAttack) return;

        if (currentCooldown > 0) {
            currentCooldown -= delta;
        }

        if (isAttacking) {
            attackTimer -= delta;
            
            // Canh đúng frame 2 (tay đập xuống) thì mới mọc gai
            if (!effectSpawnedThisAttack && goblinAnim != null && goblinAnim.getFrame() >= 2) {
                spawnGoblinEffect();
                effectSpawnedThisAttack = true;
            }

            if (attackTimer <= 0) {
                isAttacking = false;
                canMove = true; // Cho phép di chuyển lại sau khi vung gậy xong
                currentCooldown = attackCooldown;
            }
            return;
        }

        // Logic check khoảng cách để tấn công
        if (Global.isAttack && Global.player != null && godot.global.GD.isInstanceValid(Global.player) && !Global.player.isQueuedForDeletion()) {
            float dist = (float) getGlobalPosition().distanceTo(Global.player.getGlobalPosition());
            
            if (dist < 180.0f && currentCooldown <= 0) {
                performAttack();
            } else if (dist >= 180.0f || currentCooldown > 0) {
                if (canMove && goblinAnim != null) {
                    goblinAnim.play(new StringName("move"), 1.0f, false);
                }
            }
        }
    }

    private void performAttack() {
        isAttacking = true;
        canMove = false; // Dừng lại để múa gậy
        attackTimer = 1.0; // Múa gậy mất 1 giây
        effectSpawnedThisAttack = false; // Reset cờ cho lần đánh này

        if (goblinAnim != null) {
            // Chơi từ frame 0 để tính toán cho chuẩn
            goblinAnim.setFrame(0);
            goblinAnim.play(new StringName("attack"), 1.0f, false);
        }
    }

    private void spawnGoblinEffect() {
        // Đẻ ra effect ở vị trí của Player
        if (effectScene != null && Global.player != null && godot.global.GD.isInstanceValid(Global.player)) {
            godot.api.Node instance = effectScene.instantiate();
            if (instance instanceof godot.api.Node2D) {
                // Đưa effect vào làm con của Player để nó mọc ngay dưới chân và chạy theo player
                Global.player.addChild(instance);
                godot.api.Node2D effectNode = (godot.api.Node2D) instance;
                effectNode.setPosition(new Vector2(0, 0));
                
                // Đảo mặt effect theo hướng của Goblin
                godot.api.Node visualsNode = getNode("%Visuals");
                if (visualsNode instanceof godot.api.Node2D) {
                    float faceX = (float) ((godot.api.Node2D) visualsNode).getScale().getX();
                    
                    // Lấy scale gốc của effect để không ghi đè tuỳ chỉnh trong Editor
                    float origScaleX = (float) Math.abs(effectNode.getScale().getX());
                    float origScaleY = (float) effectNode.getScale().getY();
                    
                    effectNode.setScale(new Vector2(-Math.signum(faceX) * origScaleX, origScaleY));
                }
            }
        }
    }
}
