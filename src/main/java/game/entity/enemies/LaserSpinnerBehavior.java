package game.entity.enemies;

import game.autoloads.Global;
import game.components.HitBoxComponent;
import game.entity.PlayerChrono;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Node2D;
import godot.core.Color;
import godot.core.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@RegisterClass
public class LaserSpinnerBehavior extends Node2D {

    @Export
    @RegisterProperty
    public Enemy enemy;

    @Export
    @RegisterProperty
    public HitBoxComponent laserHitbox;

    @Export
    @RegisterProperty
    public float laserRange = 600.0f;

    @Export
    @RegisterProperty
    public float damage = 10.0f;

    @Export
    @RegisterProperty
    public float cooldown = 5.0f;

    @Export
    @RegisterProperty
    public float prepTime = 1.0f;

    @Export
    @RegisterProperty
    public float laserDuration = 3.0f;

    @Export
    @RegisterProperty
    public float rotationSpeed = 60.0f; // degrees per second

    @Export
    @RegisterProperty
    public int laserCount = 2; // Default 2 symmetric lasers (opposite directions)

    @Export
    @RegisterProperty
    public float playerRadius = 30.0f;

    @Export
    @RegisterProperty
    public float tickRate = 0.15f; // time between damage applications

    // Lightning Properties
    @Export
    @RegisterProperty
    public float lightningDamage = 25.0f;

    @Export
    @RegisterProperty
    public float lightningInterval = 1.0f;

    @Export
    @RegisterProperty
    public float lightningPrepTime = 0.8f;

    @Export
    @RegisterProperty
    public float lightningRadius = 70.0f;

    private double currentCooldown = 0.0;
    private double currentPrepTimer = 0.0;
    private double currentLaserTimer = 0.0;
    private double currentDamageTickTimer = 0.0;

    private boolean isPreparing = false;
    private boolean isBeaming = false;
    private float currentLaserAngle = 0.0f; // in radians

    // Lightning State Management
    private double currentLightningSpawnTimer = 0.0;
    private final List<LightningStrike> activeStrikes = new ArrayList<>();
    private final Random random = new Random();

    // Spawn Attack state
    private boolean hasTriggeredSpawnAttack = false;

    private static class LightningStrike {
        Vector2 targetPos;
        double prepTimer;
        double drawTimer;
        boolean struck;
        long seed; // Unique seed for random jaggedness

        public LightningStrike(Vector2 targetPos, double prepTime) {
            this.targetPos = targetPos;
            this.prepTimer = prepTime;
            this.drawTimer = 0.18; // duration to render lightning bolt
            this.struck = false;
            this.seed = new Random().nextLong();
        }
    }

    @RegisterFunction
    @Override
    public void _ready() {
        currentCooldown = 0.5; // Fire almost immediately after spawning
        if (laserHitbox != null) {
            laserHitbox.disable();
        }
    }

    @RegisterFunction
    @Override
    public void _process(double delta) {
        if (PlayerChrono.isTimeWarpActive) {
            delta *= 0.15;
        }

        if (enemy == null) return;

        // Kích hoạt đòn đánh sét chào sân ngay khi xuất hiện
        if (!hasTriggeredSpawnAttack) {
            if (Global.player != null && !Global.player.isQueuedForDeletion()) {
                hasTriggeredSpawnAttack = true;
                triggerSpawnLightningBarrage();
            }
        }

        // Cập nhật trạng thái các tia sét đang đánh (hoạt động độc lập)
        boolean hasActiveStrikes = updateLightningStrikes(delta);

        // Trạng thái 1: Đang gồng cảnh báo bắn laser
        if (isPreparing) {
            currentPrepTimer -= delta;

            // Luôn hướng tia cảnh báo ban đầu về phía Player
            if (Global.player != null && !Global.player.isQueuedForDeletion()) {
                Vector2 targetDir = Global.player.getGlobalPosition().minus(enemy.getGlobalPosition()).normalized();
                currentLaserAngle = (float) Math.atan2(targetDir.getY(), targetDir.getX());
            }

            queueRedraw();

            if (currentPrepTimer <= 0.0) {
                isPreparing = false;
                startBeaming();
            }
            return;
        }

        // Trạng thái 2: Đang bắn laser và tự xoay
        if (isBeaming) {
            currentLaserTimer -= delta;
            currentDamageTickTimer -= delta;
            currentLightningSpawnTimer -= delta;

            // Xoay tia laser
            float radRotationSpeed = (float) Math.toRadians(rotationSpeed);
            currentLaserAngle += radRotationSpeed * delta;

            // Bắn sét định kỳ vào Player
            if (currentLightningSpawnTimer <= 0.0 && Global.player != null && !Global.player.isQueuedForDeletion()) {
                spawnLightningStrike();
                currentLightningSpawnTimer = lightningInterval;
            }

            // Kiểm tra va chạm với Player và gây sát thương
            if (Global.player != null && !Global.player.isQueuedForDeletion()) {
                boolean hit = checkPlayerIntersection();
                if (hit && currentDamageTickTimer <= 0.0) {
                    applyDamageToPlayer();
                    currentDamageTickTimer = tickRate;
                }
            }

            queueRedraw();

            if (currentLaserTimer <= 0.0) {
                endBeaming();
            }
            return;
        }

        // Trạng thái 3: Hồi chiêu (Di chuyển bình thường)
        if (currentCooldown > 0.0) {
            currentCooldown -= delta;
        } else {
            // Kiểm tra khoảng cách để bắt đầu tấn công
            if (Global.player != null && !Global.player.isQueuedForDeletion()) {
                double dist = getGlobalPosition().distanceTo(Global.player.getGlobalPosition());
                if (dist <= laserRange) {
                    startPrep();
                }
            }
        }

        // Nếu còn sét đang vẽ dở, liên tục vẽ lại
        if (hasActiveStrikes) {
            queueRedraw();
        }
    }

    private void triggerSpawnLightningBarrage() {
        if (Global.player == null || Global.player.isQueuedForDeletion()) return;

        Vector2 playerPos = Global.player.getGlobalPosition();
        Vector2 enemyPos = enemy.getGlobalPosition();

        // Tính hướng từ quái đến Player để tạo hàng ngang 3 tia sét vuông góc
        Vector2 dir = playerPos.minus(enemyPos).normalized();
        if (dir.length() == 0.0) {
            dir = new Vector2(1, 0);
        }

        Vector2 perp = new Vector2(-dir.getY(), dir.getX());
        float spacing = 130.0f; // Khoảng cách hàng ngang giữa các phát sét đánh

        // Spawn 3 tia sét đồng thời (1 chính diện Player, 2 bên cánh trái/phải vuông góc)
        activeStrikes.add(new LightningStrike(playerPos, lightningPrepTime));
        activeStrikes.add(new LightningStrike(playerPos.plus(perp.times(spacing)), lightningPrepTime));
        activeStrikes.add(new LightningStrike(playerPos.minus(perp.times(spacing)), lightningPrepTime));

        queueRedraw();
    }

    private void startPrep() {
        isPreparing = true;
        currentPrepTimer = prepTime;
        enemy.canMove = false; // Đứng im khi chuẩn bị bắn
    }

    private void startBeaming() {
        isBeaming = true;
        currentLaserTimer = laserDuration;
        currentDamageTickTimer = 0.0; // Gây sát thương ngay lập tức nếu trúng
        currentLightningSpawnTimer = 0.0; // Spawn sét ngay phát đầu tiên khi bắn laser

        if (laserHitbox != null) {
            float actualDamage = damage;
            if (enemy.stats != null) {
                actualDamage = enemy.stats.damage;
            }
            laserHitbox.setup(actualDamage, false, 0.0f, enemy);
            laserHitbox.enable();
        }
    }

    private void endBeaming() {
        isBeaming = false;
        currentCooldown = cooldown;
        enemy.canMove = true; // Cho phép di chuyển lại

        if (laserHitbox != null) {
            laserHitbox.disable();
        }
        queueRedraw();
    }

    private void spawnLightningStrike() {
        if (Global.player != null && !Global.player.isQueuedForDeletion()) {
            Vector2 target = Global.player.getGlobalPosition();
            activeStrikes.add(new LightningStrike(target, lightningPrepTime));
            queueRedraw();
        }
    }

    private boolean updateLightningStrikes(double delta) {
        if (activeStrikes.isEmpty()) return false;

        Iterator<LightningStrike> iterator = activeStrikes.iterator();
        while (iterator.hasNext()) {
            LightningStrike strike = iterator.next();
            if (!strike.struck) {
                strike.prepTimer -= delta;
                if (strike.prepTimer <= 0.0) {
                    strike.struck = true;
                    applyLightningDamage(strike);
                }
            } else {
                strike.drawTimer -= delta;
                if (strike.drawTimer <= 0.0) {
                    iterator.remove();
                }
            }
        }
        return !activeStrikes.isEmpty();
    }

    private void applyLightningDamage(LightningStrike strike) {
        // Rung màn hình khi sét đánh trúng đất
        if (Global.camera != null) {
            Global.camera.addShake(4.5f);
        }

        if (Global.player != null && !Global.player.isQueuedForDeletion() && laserHitbox != null) {
            double dist = strike.targetPos.distanceTo(Global.player.getGlobalPosition());
            if (dist <= lightningRadius) {
                // Tạm thời thay thế sát thương hitbox bằng sát thương sét đánh
                float prevDamage = laserHitbox.damage;
                laserHitbox.damage = lightningDamage;
                Global.player._on_hurtbox_component_on_damage(laserHitbox);
                laserHitbox.damage = prevDamage; // Trả lại sát thương laser
            }
        }
    }

    private boolean checkPlayerIntersection() {
        Vector2 playerPos = Global.player.getGlobalPosition();
        Vector2 enemyPos = enemy.getGlobalPosition();

        float angleStep = (float) (2.0 * Math.PI / laserCount);

        for (int i = 0; i < laserCount; i++) {
            float angle = currentLaserAngle + (angleStep * i);
            Vector2 dir = new Vector2((float) Math.cos(angle), (float) Math.sin(angle));
            Vector2 endPos = enemyPos.plus(dir.times(laserRange));

            if (isPointNearSegment(playerPos, enemyPos, endPos, playerRadius)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPointNearSegment(Vector2 p, Vector2 a, Vector2 b, float radius) {
        Vector2 ab = b.minus(a);
        Vector2 ap = p.minus(a);

        double abLenSq = ab.getX() * ab.getX() + ab.getY() * ab.getY();
        if (abLenSq == 0.0) {
            return p.distanceTo(a) <= radius;
        }

        // Tỉ lệ chiếu của P lên đoạn thẳng AB
        double dotProduct = ap.getX() * ab.getX() + ap.getY() * ab.getY();
        double t = dotProduct / abLenSq;
        t = Math.max(0.0, Math.min(1.0, t)); // Giới hạn trong đoạn AB

        Vector2 closestPoint = a.plus(ab.times(t));
        return p.distanceTo(closestPoint) <= radius;
    }

    private void applyDamageToPlayer() {
        if (laserHitbox != null && Global.player != null) {
            Global.player._on_hurtbox_component_on_damage(laserHitbox);
        }
    }

    @RegisterFunction
    @Override
    public void _draw() {
        Vector2 localCenter = new Vector2(0, 0);
        float angleStep = (float) (2.0 * Math.PI / laserCount);

        // 1. Vẽ tia Laser (Gồng & Bắn)
        if (isPreparing) {
            // Vẽ các đường cảnh báo màu đỏ/cam nhạt gợn gợn nhấp nháy
            float alpha = (float) (0.2 + 0.3 * Math.abs(Math.sin(currentPrepTimer * 15.0)));
            Color warnColor = new Color(1.0f, 0.3f, 0.0f, alpha);

            for (int i = 0; i < laserCount; i++) {
                float angle = currentLaserAngle + (angleStep * i);
                Vector2 dir = new Vector2((float) Math.cos(angle), (float) Math.sin(angle));
                Vector2 endLocal = localCenter.plus(dir.times(laserRange));

                // Vẽ đường nét đứt hoặc nét mảnh cảnh báo
                drawLine(localCenter, endLocal, warnColor, 1.5f, false);
                // Vẽ một vòng tròn nhỏ cảnh báo ở cuối tia laser
                drawCircle(endLocal, 6.0f, warnColor);
            }
        } else if (isBeaming) {
            // Vẽ tia laser: Lớp nền đỏ dày và lõi trắng sáng mảnh ở giữa
            Color laserOuterColor = new Color(1.0f, 0.1f, 0.1f, 0.85f);
            Color laserInnerColor = new Color(1.0f, 1.0f, 1.0f, 0.95f);

            for (int i = 0; i < laserCount; i++) {
                float angle = currentLaserAngle + (angleStep * i);
                Vector2 dir = new Vector2((float) Math.cos(angle), (float) Math.sin(angle));
                Vector2 endLocal = localCenter.plus(dir.times(laserRange));

                // Vẽ tia laser ngoài (Độ dày = 6.0px)
                drawLine(localCenter, endLocal, laserOuterColor, 6.0f, false);
                // Vẽ lõi laser trong (Độ dày = 2.0px)
                drawLine(localCenter, endLocal, laserInnerColor, 2.0f, false);
                // Vẽ tâm sáng tại điểm cuối
                drawCircle(endLocal, 8.0f, laserOuterColor);
                drawCircle(endLocal, 4.0f, laserInnerColor);
            }
        }

        // 2. Vẽ Sét đánh (Shadow cảnh báo & Tia Sét ngoằn ngoèo)
        for (LightningStrike strike : activeStrikes) {
            Vector2 localPos = toLocal(strike.targetPos);

            if (!strike.struck) {
                // Vẽ bóng (Shadow cảnh báo đỏ nhạt / đen tròn) dưới chân Player
                float alpha = (float) (0.2 + 0.25 * Math.abs(Math.sin(strike.prepTimer * 12.0)));
                Color shadowColor = new Color(0.1f, 0.1f, 0.15f, alpha);
                Color ringColor = new Color(0.0f, 0.6f, 1.0f, alpha * 2.0f);

                // Bóng tròn đỏ đen cảnh báo dưới đất
                drawCircle(localPos, lightningRadius, shadowColor);
                // Vòng tròn viền xanh lam nhấp nháy thu nhỏ dần báo hiệu thời gian đếm ngược
                float shrinkScale = (float) (strike.prepTimer / lightningPrepTime);
                drawArc(localPos, lightningRadius * shrinkScale, 0.0f, (float) (Math.PI * 2.0), 32, ringColor, 2.0f, false);
            } else if (strike.drawTimer > 0.0) {
                // Vẽ tia sét ngoằn ngoèo từ trời giáng xuống
                Color boltOuter = new Color(0.0f, 0.5f, 1.0f, 0.85f);
                Color boltInner = new Color(1.0f, 1.0f, 1.0f, 0.95f);

                // Dùng hạt giống ngẫu nhiên cố định cho mỗi cú đánh để tránh tia sét bị giật giật loạn xạ giữa các frame
                Random boltRand = new Random(strike.seed);

                Vector2 currentPoint = localPos.plus(new Vector2(0, -1000)); // Điểm khởi đầu trên trời cao / chân trời
                int segments = 8;
                float stepY = 1000.0f / segments;

                for (int s = 1; s <= segments; s++) {
                    float nextY = -1000.0f + s * stepY;
                    float nextX = (s == segments) ? 0.0f : (float) (boltRand.nextGaussian() * 20.0f);
                    Vector2 nextPoint = localPos.plus(new Vector2(nextX, nextY));

                    // Vẽ tia sét phát sáng ngoài (dày 5px, màu xanh lam)
                    drawLine(currentPoint, nextPoint, boltOuter, 5.0f, false);
                    // Vẽ lõi sét trong (dày 1.5px, màu trắng)
                    drawLine(currentPoint, nextPoint, boltInner, 1.5f, false);

                    currentPoint = nextPoint;
                }

                // Vẽ vụ nổ sáng tại tâm điểm sét đánh trúng đất
                drawCircle(localPos, 15.0f, boltOuter);
                drawCircle(localPos, 7.0f, boltInner);
            }
        }
    }
}
