package game.entity.enemies;

import game.components.HitBoxComponent;
import game.entity.PlayerChrono;
import game.autoloads.Global;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Node2D;
import godot.api.Sprite2D;
import godot.core.Color;
import godot.core.Vector2;

@RegisterClass
public class SlamBehavior extends Node2D {

    @Export
    @RegisterProperty
    public Enemy enemy;

    @Export
    @RegisterProperty
    public HitBoxComponent slamHitbox;

    @Export
    @RegisterProperty
    public float slamRange = 200.0f; // Bán kính cục bộ (sẽ nhân với scale của quái)

    @Export
    @RegisterProperty
    public float cooldown = 2.5f;

    @Export
    @RegisterProperty
    public float prepTime = 0.5f;

    @Export
    @RegisterProperty
    public float damage = 50.0f;

    @Export
    @RegisterProperty
    public float knockbackPower = 6.0f;

    private double currentCooldown = 0.0;
    private double currentPrepTimer = 0.0;
    private boolean isPreparing = false;

    @RegisterFunction
    @Override
    public void _ready() {
        currentCooldown = cooldown;
        if (slamHitbox != null) {
            slamHitbox.disable();
        }
    }

    @RegisterFunction
    @Override
    public void _process(double delta) {
        if (PlayerChrono.isTimeWarpActive) {
            delta *= 0.15;
        }

        if (enemy == null) return;

        // Trạng thái 1: Đang gồng dập đất
        if (isPreparing) {
            currentPrepTimer -= delta;
            queueRedraw(); // Cập nhật vẽ lại cảnh báo đỏ

            // Nhấp nháy màu đỏ trên quái vật
            Sprite2D sprite = (Sprite2D) enemy.getNode("%Sprite");
            if (sprite != null) {
                float pulse = (float) (Math.sin(currentPrepTimer * 20.0) + 1.0) / 2.0f;
                sprite.setModulate(new Color(1.0f, 1.0f - pulse * 0.5f, 1.0f - pulse * 0.5f, 1.0f));
            }

            if (currentPrepTimer <= 0.0) {
                isPreparing = false;
                executeSlam();
            }
            return;
        }

        // Trạng thái 2: Đếm ngược hồi chiêu
        if (currentCooldown > 0.0) {
            currentCooldown -= delta;
        } else {
            // Kiểm tra khoảng cách thực tế để kích hoạt chiêu (tiến vào 80% tầm đánh mới gồng)
            if (Global.player != null && !Global.player.isQueuedForDeletion()) {
                double dist = getGlobalPosition().distanceTo(Global.player.getGlobalPosition());
                float triggerRange = slamRange * 0.8f * (float) enemy.getScale().getX();
                if (dist <= triggerRange) {
                    startPrep();
                }
            }
        }
    }

    private void startPrep() {
        isPreparing = true;
        currentPrepTimer = prepTime;
        enemy.canMove = false; // Trói chân quái
    }

    private void executeSlam() {
        enemy.canMove = true; // Cho quái đi tiếp
        currentCooldown = cooldown; // Bắt đầu hồi chiêu

        // Reset màu sắc của quái về bình thường
        Sprite2D sprite = (Sprite2D) enemy.getNode("%Sprite");
        if (sprite != null) {
            sprite.setModulate(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        }

        queueRedraw(); // Xóa vòng tròn cảnh báo đỏ

        // Rung màn hình mạnh
        if (Global.camera != null) {
            Global.camera.addShake(8.0f);
        }

        // Tính toán khoảng cách thực tế để gây sát thương trực tiếp
        if (Global.player != null && !Global.player.isQueuedForDeletion()) {
            double dist = getGlobalPosition().distanceTo(Global.player.getGlobalPosition());
            float actualSlamRange = slamRange * (float) enemy.getScale().getX();

            godot.global.GD.print("GOLIATH SLAM CALLED: dist = " + dist + ", actualRange = " + actualSlamRange);

            if (dist <= actualSlamRange) {
                godot.global.GD.print("GOLIATH SLAM HIT PLAYER! Applying damage.");
                if (slamHitbox != null) {
                    // Cấu hình sát thương và lực đẩy cho Hitbox ảo
                    slamHitbox.setup(damage, false, knockbackPower, enemy);
                    
                    // Gọi trực tiếp xử lý sát thương của Player
                    Global.player._on_hurtbox_component_on_damage(slamHitbox);
                } else {
                    godot.global.GD.printErr("GOLIATH ERROR: slamHitbox is null!");
                }
            } else {
                godot.global.GD.print("GOLIATH SLAM MISSED (player too far).");
            }
        }
    }

    @RegisterFunction
    @Override
    public void _draw() {
        if (isPreparing) {
            // Vẽ vòng tròn cảnh báo đỏ nhạt
            drawCircle(new Vector2(0, 0), slamRange, new Color(1.0f, 0.0f, 0.0f, 0.3f));
            // Vẽ viền đỏ đậm
            drawArc(new Vector2(0, 0), slamRange, 0.0f, (float) (Math.PI * 2), 64, new Color(1.0f, 0.0f, 0.0f, 0.7f), 2.0f, false);
        }
    }
}
