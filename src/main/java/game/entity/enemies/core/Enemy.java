package game.entity.enemies.core;

import game.autoloads.Global;
import game.components.HitBoxComponent;
import game.entity.BaseUnit;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.*;
import godot.core.NativeCallable;
import godot.core.StringName;
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
    public boolean canMove = true;
    public boolean canAttack = true;

    //Knockback
    private Vector2 knockbackDirection = new Vector2(0, 0);
    public double knockbackPower = 0.0;

    @RegisterProperty
    public Timer knockbackTimer;
    private Timer deathTimer;

    // Knockback Immunity
    @Export
    @RegisterProperty
    public double knockbackImmunityTime = 0.3; // 0.3 giây miễn nhiễm sau khi bị đẩy
    private double knockbackCooldown = 0.0;

    protected float originalScaleX = 1.0f;
    protected float originalScaleY = 1.0f;

    @RegisterFunction
    @Override
    public void _ready() {
        super._ready();
        addToGroup(new StringName("enemy"));

        visuals = (Node2D) getNode("%Visuals");
        if (visuals != null) {
            originalScaleX = Math.abs((float) visuals.getScale().getX());
            originalScaleY = Math.abs((float) visuals.getScale().getY());
            if (originalScaleX == 0) originalScaleX = 1.0f;
            if (originalScaleY == 0) originalScaleY = 1.0f;
        }

        sprite = (Sprite2D) getNode("%Sprite");
        animPlayer = (AnimationPlayer) getNode("AnimationPlayer");
        visionArea = (Area2D) getNode("VisionArea");

        //KnockbackTimer init
        knockbackTimer = (Timer) getNode("KnockbackTimer");
        if (knockbackTimer != null) {
            knockbackTimer.setOneShot(true); // ĐẢM BẢO TIMER CHỈ CHẠY 1 LẦN, NẾU KHÔNG SẼ BỊ LẶP VĨNH VIỄN
        }

        // Death Timer
        deathTimer = new Timer();
        deathTimer.setOneShot(true);
        deathTimer.connect("timeout", new NativeCallable(this, new StringName("_on_death_timer_timeout")));
        addChild(deathTimer);
    }

    // Dùng _physics_process để đồng bộ chuẩn với hệ thống quét Radar của Godot
    @RegisterFunction
    @Override
    public void _physicsProcess(double delta) {
        if (Global.gamePaused) return;
        float fDelta = (float) delta;

        if (knockbackCooldown > 0) {
            knockbackCooldown -= delta;
        }

        if (!canMove) return;

        // Tương đương hàm can_move_towards_player() trong video
        if (!canMoveTowardsPlayer()) {
            return;
        }

        Vector2 moveDir = getMoveDirection();

        float speed = (stats != null) ? stats.speed : 250.0f;

        // 2. Tính toán Vector tổng
        Vector2 finalVelocity;
        if (knockbackPower > 0) {
            // Nếu đang bị đẩy: Đứng yên (vô hiệu hóa tốc độ gốc) và CHỈ áp dụng lực văng
            finalVelocity = knockbackDirection.times(knockbackPower);
        } else {
            // Bình thường: Đi lùa Player như thường
            finalVelocity = moveDir.times(speed);
        }

        Vector2 newPos = getPosition().plus(finalVelocity.times(delta));
        
        // Clamp vị trí không cho văng khỏi map
        float clampedX = Math.max(-1000.0f, Math.min(1000.0f, (float)newPos.getX()));
        float clampedY = Math.max(-500.0f, Math.min(500.0f, (float)newPos.getY()));
        
        setPosition(new Vector2(clampedX, clampedY));
        updateRotation();
    }

    private boolean canMoveTowardsPlayer() {
        if (!Global.isAttack || Global.player == null || !godot.global.GD.isInstanceValid(Global.player) || !Global.player.isInsideTree()) {
            return false;
        }
        float dist = (float) getGlobalPosition().distanceTo(Global.player.getGlobalPosition());
        return dist > 60.0f;
    }

    private Vector2 getMoveDirection() {
        if (!Global.isAttack || Global.player == null || !godot.global.GD.isInstanceValid(Global.player) || !Global.player.isInsideTree()) {
            return new Vector2(0, 0);
        }

        Vector2 myPos = getGlobalPosition();
        Vector2 playerPos = Global.player.getGlobalPosition();

        Vector2 direction = playerPos.minus(myPos).normalized();
        Vector2 totalFlockForce = new Vector2(0, 0);

        if (visionArea != null) {
            VariantArray<Area2D> overlapping = visionArea.getOverlappingAreas();

            for (int i = 0; i < overlapping.size(); i++) {
                Node2D other = overlapping.get(i);

                // Nếu không phải chính mình và kẻ đó còn sống
                if (other.getInstanceId() != this.getInstanceId() && other.isInsideTree()) {

                    // Tạo vector từ KẺ ĐÓ chĩa về MÌNH (Toán học giống hệt video)
                    Vector2 vector = myPos.minus(other.getGlobalPosition());
                    float length = (float) vector.length();

                    if (length > 0.0f) {
                        float pushMagnitude = Math.min(flockPush / length, 0.5f);
                        Vector2 pushForce = vector.normalized().times(pushMagnitude);
                        totalFlockForce = totalFlockForce.plus(pushForce);
                    }
                }
            }
        }
        
        // Giới hạn tổng lực đẩy không bao giờ vượt quá 0.8 để luôn bé hơn hướng đi tới player (1.0)
        if (totalFlockForce.length() > 0.8f) {
            totalFlockForce = totalFlockForce.normalized().times(0.8f);
        }
        
        direction = direction.plus(totalFlockForce);

        // Trả về vector đã chuẩn hóa cuối cùng
        return direction.normalized();
    }

    private void updateRotation() {
        if (Global.player == null || visuals == null) return;

        Vector2 myPos = getGlobalPosition();
        Vector2 playerPos = Global.player.getGlobalPosition();

        // Xoay nhân vật dựa trên vị trí X của Player, NHƯNG GIỮ NGUYÊN SCALE GỐC
        if (myPos.getX() < playerPos.getX()) {
            visuals.setScale(new Vector2(-originalScaleX, originalScaleY));
        } else {
            visuals.setScale(new Vector2(originalScaleX, originalScaleY));
        }
    }

    // Knock Timer
    @RegisterFunction
    public void applyKnockback(Vector2 direction, double power) {
        // Mặc định gọi hàm overload với isWeapon = true
        applyKnockbackAdvanced(direction, power, true);
    }

    public void applyKnockbackAdvanced(Vector2 direction, double power, boolean isWeapon) {
        if (isWeapon) {
            // Nếu đang trong thời gian miễn nhiễm đẩy lùi do vũ khí, bỏ qua đạn súng!
            if (knockbackCooldown > 0) return;

            // Đẩy do vũ khí: Áp dụng luật Max Power (So sánh)
            // LƯU Ý: Phải dùng DẤU LỚN HƠN HẲN (>) chứ không dùng (>=)
            // Nếu dùng (>=), cùng 1 cây súng bắn liên tục sẽ liên tục reset Timer, khiến quái bị Stun-lock vĩnh viễn!
            if (power > this.knockbackPower || knockbackTimer.getTimeLeft() == 0) {
                this.knockbackDirection = direction;
                this.knockbackPower = power;

                if (knockbackTimer.getTimeLeft() > 0) {
                    knockbackTimer.stop();
                }
                knockbackTimer.start();
            }
        } else {
            // Đẩy do quái cắn (recoil): Không so sánh, cộng dồn lực luôn!
            if (knockbackTimer.getTimeLeft() == 0) {
                this.knockbackDirection = direction;
                this.knockbackPower = power;
                knockbackTimer.start();
            } else {
                Vector2 currentForce = this.knockbackDirection.times((float)this.knockbackPower);
                Vector2 newForce = direction.times((float)power);
                Vector2 totalForce = currentForce.plus(newForce);
                
                this.knockbackPower = totalForce.length();
                if (this.knockbackPower > 0) {
                    this.knockbackDirection = totalForce.normalized();
                }
            }
        }
    }

    @RegisterFunction
    public void resetKnockback() {
        this.knockbackDirection = new Vector2(0, 0);
        this.knockbackPower = 0.0;
        
        // Ngay khi vừa dừng bay (reset), lập tức bật khiên miễn nhiễm đẩy lùi!
        this.knockbackCooldown = knockbackImmunityTime;
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

    @RegisterFunction
    public void destroyEnemy() {
        canMove = false;
        canAttack = false;
        animPlayer.play("die");
        
        deathTimer.start(0.6);
    }

    @RegisterFunction
    public void _on_death_timer_timeout() {
        queueFree();
    }

    @RegisterFunction
    public void _on_health_component_on_unit_die() {
        // Hàm này được Godot gọi khi máu quái về 0
        Global.instance.onEnemyDied.emit(this);
    }
}
