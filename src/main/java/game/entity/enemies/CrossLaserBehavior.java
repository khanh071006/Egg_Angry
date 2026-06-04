package game.entity.enemies;

import game.autoloads.Global;
import game.components.HealthComponent;
import game.entity.PlayerChrono;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Node;
import godot.api.Node2D;
import godot.core.Color;
import godot.core.Vector2;

@RegisterClass
public class CrossLaserBehavior extends Node2D {

    @Export
    @RegisterProperty
    public Enemy enemy;

    @Export
    @RegisterProperty
    public float laserRange = 4000.0f;

    @Export
    @RegisterProperty
    public float laserWidth = 35.0f;

    @Export
    @RegisterProperty
    public float triggerRange = 600.0f;

    @Export
    @RegisterProperty
    public float cooldown = 4.0f;

    @Export
    @RegisterProperty
    public float prepTime = 1.2f;

    @Export
    @RegisterProperty
    public float laserDuration = 0.8f;

    @Export
    @RegisterProperty
    public float playerRadius = 30.0f;

    private double currentCooldown = 1.0; // Wait 1 second before first attack after spawning
    private double currentPrepTimer = 0.0;
    private double currentLaserTimer = 0.0;

    private boolean isPreparing = false;
    private boolean isBeaming = false;
    private boolean hasDealtDamageThisCycle = false;

    @RegisterFunction
    @Override
    public void _ready() {
        if (enemy == null) {
            Node parent = getParent();
            if (parent instanceof Enemy) {
                enemy = (Enemy) parent;
            }
        }
    }

    @RegisterFunction
    @Override
    public void _process(double delta) {
        if (PlayerChrono.isTimeWarpActive) {
            delta *= 0.15;
        }

        if (enemy == null) return;

        // State 1: Preparing (warning with faint flashing lines)
        if (isPreparing) {
            currentPrepTimer -= delta;
            queueRedraw();

            if (currentPrepTimer <= 0.0) {
                isPreparing = false;
                startBeaming();
            }
            return;
        }

        // State 2: Beaming (firing lasers and checking collision)
        if (isBeaming) {
            currentLaserTimer -= delta;

            if (!hasDealtDamageThisCycle && Global.player != null && !Global.player.isQueuedForDeletion()) {
                if (checkPlayerIntersection()) {
                    applyHalfDamageToPlayer();
                    hasDealtDamageThisCycle = true;
                }
            }

            queueRedraw();

            if (currentLaserTimer <= 0.0) {
                endBeaming();
            }
            return;
        }

        // State 3: Cooldown (moving normally)
        if (currentCooldown > 0.0) {
            currentCooldown -= delta;
        } else {
            if (Global.player != null && !Global.player.isQueuedForDeletion()) {
                double dist = getGlobalPosition().distanceTo(Global.player.getGlobalPosition());
                if (dist <= triggerRange) {
                    startPrep();
                }
            }
        }
    }

    private void startPrep() {
        isPreparing = true;
        currentPrepTimer = prepTime;
        enemy.canMove = false; // Stop enemy movement during attack
        queueRedraw();
    }

    private void startBeaming() {
        isBeaming = true;
        currentLaserTimer = laserDuration;
        hasDealtDamageThisCycle = false;
        queueRedraw();
    }

    private void endBeaming() {
        isBeaming = false;
        currentCooldown = cooldown;
        enemy.canMove = true; // Resume enemy movement
        queueRedraw();
    }

    private boolean checkPlayerIntersection() {
        if (Global.player == null) return false;
        Vector2 playerPos = Global.player.getGlobalPosition();
        Vector2 enemyPos = enemy.getGlobalPosition();

        // 4 orthogonal angles: 0, 90, 180, 270 degrees in radians
        float[] angles = {0.0f, (float) Math.PI / 2.0f, (float) Math.PI, (float) (-Math.PI / 2.0f)};

        for (float angle : angles) {
            Vector2 dir = new Vector2((float) Math.cos(angle), (float) Math.sin(angle));
            Vector2 endPos = enemyPos.plus(dir.times(laserRange));

            // Collision thickness = player size + half width of laser
            if (isPointNearSegment(playerPos, enemyPos, endPos, playerRadius + laserWidth / 2.0f)) {
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

        double dotProduct = ap.getX() * ab.getX() + ap.getY() * ab.getY();
        double t = dotProduct / abLenSq;
        t = Math.max(0.0, Math.min(1.0, t));

        Vector2 closestPoint = a.plus(ab.times(t));
        return p.distanceTo(closestPoint) <= radius;
    }

    private void applyHalfDamageToPlayer() {
        if (Global.player == null) return;
        HealthComponent playerHealth = (HealthComponent) Global.player.getNode("HealthComponent");
        if (playerHealth != null) {
            float damageAmount = playerHealth.maxHealth / 2.0f;
            playerHealth.takeDamage(damageAmount);

            if (Global.camera != null) {
                Global.camera.addShake(5.0f); // Strong screen shake for half health hits
            }
        }
    }

    @RegisterFunction
    @Override
    public void _draw() {
        Vector2 localCenter = new Vector2(0, 0);
        float[] angles = {0.0f, (float) Math.PI / 2.0f, (float) Math.PI, (float) (-Math.PI / 2.0f)};

        if (isPreparing) {
            // Flashing faint red lines for warning
            float alpha = (float) (0.15 + 0.25 * Math.abs(Math.sin(currentPrepTimer * 12.0)));
            Color warnColor = new Color(1.0f, 0.0f, 0.0f, alpha);

            for (float angle : angles) {
                Vector2 dir = new Vector2((float) Math.cos(angle), (float) Math.sin(angle));
                Vector2 endLocal = localCenter.plus(dir.times(laserRange));

                drawLine(localCenter, endLocal, warnColor, laserWidth, false);
            }
        } else if (isBeaming) {
            // Thick solid red laser with a lighter orange-white core
            Color outerColor = new Color(1.0f, 0.0f, 0.0f, 0.9f);
            Color innerColor = new Color(1.0f, 0.8f, 0.8f, 0.95f);

            for (float angle : angles) {
                Vector2 dir = new Vector2((float) Math.cos(angle), (float) Math.sin(angle));
                Vector2 endLocal = localCenter.plus(dir.times(laserRange));

                // Outer laser line
                drawLine(localCenter, endLocal, outerColor, laserWidth, false);
                // Inner core line
                drawLine(localCenter, endLocal, innerColor, laserWidth * 0.35f, false);
            }
        }
    }
}
