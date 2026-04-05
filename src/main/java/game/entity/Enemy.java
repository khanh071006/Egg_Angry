package game.entity;

import game.autoloads.Global;
import game.components.HitBoxComponent;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.*;
import godot.core.VariantArray;
import godot.core.Vector2;

@RegisterClass
public class Enemy extends BaseUnit {

//    @Export
//    @RegisterProperty
//    public EnemyStats stats;

    @Export
    @RegisterProperty
    public float flockPush = 20.0f;

    private Area2D visionArea;
    private boolean canMove = true;

    //Knockback
    private Vector2 knockbackDirection = new Vector2(0, 0);
    public double knockbackPower = 0.0;

    @RegisterProperty
    public Timer knockbackTimer;

    @RegisterFunction
    @Override
    public void _ready() {
        super._ready();

        visuals = (Node2D) getNode("%Visuals");
        sprite = (Sprite2D) getNode("%Sprite");
        animPlayer = (AnimationPlayer) getNode("AnimationPlayer");
        visionArea = (Area2D) getNode("VisionArea");

        //KnockbackTimer init
        knockbackTimer = (Timer) getNode("KnockbackTimer");
    }

    // Dùng _physics_process để đồng bộ chuẩn với hệ thống quét Radar của Godot
    @RegisterFunction
    @Override
    public void _physicsProcess(double delta) {
        float fDelta = (float) delta;

        if (!canMove) return;

        // Tương đương hàm can_move_towards_player() trong video
        if (!canMoveTowardsPlayer()) {
            return;
        }

        Vector2 moveDir = getMoveDirection();

        float speed = (stats != null) ? stats.speed : 250.0f;
        Vector2 velocity = moveDir.times(speed);

        // 2. Tính toán Vector tổng (Cộng thêm lực đẩy lùi)
        // Velocity = (moveDir * speed) + (knockbackDirection * knockbackPower)
        Vector2 finalVelocity = moveDir.times(speed).plus(knockbackDirection.times(knockbackPower));
        setPosition(getPosition().plus(finalVelocity.times(delta)));
        updateRotation();
    }

    private boolean canMoveTowardsPlayer() {
        if (Global.player == null || !Global.player.isInsideTree()) {
            return false;
        }
        float dist = (float) getGlobalPosition().distanceTo(Global.player.getGlobalPosition());
        return dist > 60.0f;
    }

    private Vector2 getMoveDirection() {
        if (Global.player == null || !Global.player.isInsideTree()) {
            return new Vector2(0, 0);
        }

        Vector2 myPos = getGlobalPosition();
        Vector2 playerPos = Global.player.getGlobalPosition();

        // 1. Lấy hướng thẳng tới Player
        Vector2 direction = playerPos.minus(myPos).normalized();

        if (visionArea != null) {
            // SỬA LỖI CỦA YOUTUBER: Phải quét Bodies, KHÔNG PHẢI Areas!
            VariantArray<Area2D> overlapping = visionArea.getOverlappingAreas();

            for (int i = 0; i < overlapping.size(); i++) {
                Node2D other = overlapping.get(i);

                // Nếu không phải chính mình và kẻ đó còn sống
                if (other.getInstanceId() != this.getInstanceId() && other.isInsideTree()) {

                    // Tạo vector từ KẺ ĐÓ chĩa về MÌNH (Toán học giống hệt video)
                    Vector2 vector = myPos.minus(other.getGlobalPosition());
                    float length = (float) vector.length();

                    if (length > 0.0f) {
                        // direction += vector.normalized() / vector.length() * flock_push
                        Vector2 pushForce = vector.normalized().div(length).times(flockPush);
                        direction = direction.plus(pushForce);
                    }
                }
            }
        }

        // Trả về vector đã chuẩn hóa cuối cùng
        return direction.normalized();
    }

    private void updateRotation() {
        if (Global.player == null || visuals == null) return;

        Vector2 myPos = getGlobalPosition();
        Vector2 playerPos = Global.player.getGlobalPosition();

        // Xoay nhân vật dựa trên vị trí X của Player
        if (myPos.getX() < playerPos.getX()) {
            visuals.setScale(new Vector2(-1f, 1f));
        } else {
            visuals.setScale(new Vector2(1f, 1f));
        }
    }

    // Knock Timer
    @RegisterFunction
    public void applyKnockback(Vector2 direction, double power) {
        this.knockbackDirection = direction;
        this.knockbackPower = power;

        // Nếu đang bị đẩy mà lại bị đấm tiếp -> Reset Timer để đẩy tiếp từ đầu
        if (knockbackTimer.getTimeLeft() > 0) {
            knockbackTimer.stop();
        }
        knockbackTimer.start();
    }

    @RegisterFunction
    public void resetKnockback() {
        this.knockbackDirection = new Vector2(0, 0);
        this.knockbackPower = 0.0;
    }

    // Hàm này sếp nối với Signal "timeout" của KnockbackTimer trên Editor nhé
    @RegisterFunction
    public void _on_knockback_timer_timeout() {
        resetKnockback();
    }

    @RegisterFunction
    @Override
    public void _on_hurtbox_component_on_damage(HitBoxComponent hitbox) {
        // 1. Gọi hàm của lớp cha để trừ máu, hiện số nhảy...
        super._on_hurtbox_component_on_damage(hitbox);

        // 2. Nếu vũ khí có lực đẩy (Knockback Power > 0)
        if (hitbox.knockbackPower > 0) {
            // Tính hướng: Từ "Kẻ ra đòn" (Source) đến "Bản thân con quái"
            // Direction = (EnemyPos - PlayerPos).normalized()
            Vector2 knockDir = getGlobalPosition().minus(Global.player.getGlobalPosition()).normalized();

            // 3. Thực thi đẩy lùi
            applyKnockback(knockDir, hitbox.knockbackPower * 100); // Nhân 100 để lực đủ mạnh
        }
    }
}