package game.entity.enemies;

import game.autoloads.Global;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.AnimationPlayer;
import godot.api.Node2D;
import godot.core.StringName;
import godot.core.Vector2;


@RegisterClass
public class ChaserBehavior extends Node2D {

    @Export
    @RegisterProperty
    public Enemy enemy; // Hoặc class BaseUnit tùy sếp đặt tên

    @Export
    @RegisterProperty
    public AnimationPlayer animEffects;

    @Export
    @RegisterProperty
    public float cooldown = 3.0f; // 3 giây húc 1 lần

    @Export
    @RegisterProperty
    public float prepTime = 0.7f; // Thời gian gồng/nhấp nháy đỏ (Khớp với độ dài Animation Charge)

    // 3. Biến quản lý trạng thái
    private double currentCooldown = 0.0;
    private double currentPrepTimer = 0.0;
    private Vector2 chargeTargetPosition = new Vector2(0, 0);
    private boolean isCharging = false;
    private boolean isPreparing = false; // Trạng thái đang gồng chờ húc

    @RegisterFunction
    @Override
    public void _ready() {
        // Mới vào game thì cho đếm ngược cooldown luôn
        currentCooldown = cooldown;
    }
    @RegisterFunction
    @Override
    public void _process(double delta) {
        if (enemy == null) return;

        if (isCharging) {
            Vector2 currentPos = enemy.getGlobalPosition();

            float chargeSpeed = enemy.stats.speed * 30.0f;

            // Tiến dần về mục tiêu đã chốt
            Vector2 newPos = currentPos.moveToward(chargeTargetPosition, chargeSpeed * (float)delta);
            enemy.setGlobalPosition(newPos);

            // Phanh lại: Nếu khoảng cách từ quái đến TỌA ĐỘ ĐÃ CHỐT nhỏ hơn 50 pixel -> Dừng
            if (currentPos.distanceTo(chargeTargetPosition) < 50.0f) {
                endCharge();
            }
        }

        else if (isPreparing) {
            currentPrepTimer -= delta;
            if (currentPrepTimer < 0.0f) {
                isPreparing = false;
                isCharging = true;
            }
        }

        else if (currentCooldown > 0.0f) {
            currentCooldown -= delta;
        }
        else{
            if (Global.player != null && !Global.player.isQueuedForDeletion()) {
                // CHỐT TỌA ĐỘ: Lấy vị trí của Player ngay khoảnh khắc này
                chargeTargetPosition = Global.player.getGlobalPosition();
                startCharge();
            }
        }

    }

    private void startCharge() {
        enemy.canMove = false; // Trói chân quái lại
        isPreparing = true;    // Chuyển sang trạng thái gồng
        currentPrepTimer = prepTime; // Cài đặt đồng hồ gồng bằng thời gian Animation (0.7s)

        // Bật Animation nhấp nháy đỏ
        if (animEffects != null) {
            animEffects.play(new StringName("charge"), -1, 1.0f, false);
        }
    }

    private void endCharge() {
        isCharging = false;
        currentCooldown = cooldown; // Reset đồng hồ húc
        enemy.canMove = true;
    }


}
