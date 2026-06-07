package game.entity.enemies.behaviors;

import game.entity.enemies.core.Enemy;

import game.autoloads.Global;
import game.components.HealthComponent;

import game.items.weapons.projectiles.Projectile;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Node;
import godot.api.Node2D;
import godot.api.PackedScene;
import godot.core.Color;
import godot.core.Vector2;

@RegisterClass
public class SplitterBomberBehavior extends Node2D {

    @Export
    @RegisterProperty
    public Enemy enemy;

    @Export
    @RegisterProperty
    public PackedScene projectileScene; // Path to enemy projectile (.tscn)

    @Export
    @RegisterProperty
    public float triggerRange = 350.0f; // Range to start self-destruct fuse

    @Export
    @RegisterProperty
    public float fuseTime = 1.0f; // Time before explosion

    @Export
    @RegisterProperty
    public float explosionRadius = 160.0f; // Range of the explosion damage

    @Export
    @RegisterProperty
    public float explosionDamage = 35.0f; // Damage dealt by explosion

    @Export
    @RegisterProperty
    public float projectileSpeed = 420.0f; // Speed of the 3 splitter bullets

    @Export
    @RegisterProperty
    public float projectileDamage = 12.0f; // Damage of the splitter bullets

    @Export
    @RegisterProperty
    public float cooldown = 3.0f; // Unused for self-destruct, but keeps standard values

    private double currentPrepTimer = 0.0;
    private boolean isPreparing = false;

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
        if (false) {
            delta *= 0.15;
        }

        if (enemy == null) return;

        // State 1: Preparing self-destruct (flashing red rapidly)
        if (isPreparing) {
            currentPrepTimer -= delta;

            // Flash modulating effect (cycles faster as time runs out)
            Node2D visuals = (Node2D) enemy.getNode("%Visuals");
            if (visuals != null) {
                float flashSpeed = 20.0f;
                if (currentPrepTimer < 0.4) {
                    flashSpeed = 40.0f; // Flash extremely fast right before blowing up
                }
                float lerpVal = (float) Math.abs(Math.sin((fuseTime - currentPrepTimer) * flashSpeed));
                // Modulate towards bright red
                visuals.setModulate(new Color(1.0f, 1.0f - lerpVal, 1.0f - lerpVal, 1.0f));
            }

            if (currentPrepTimer <= 0.0) {
                isPreparing = false;
                explode();
            }
            return;
        }

        // State 2: Chasing (normal state)
        if (Global.player != null && !Global.player.isQueuedForDeletion()) {
            double dist = getGlobalPosition().distanceTo(Global.player.getGlobalPosition());
            if (dist <= triggerRange) {
                startFuse();
            }
        }
    }

    private void startFuse() {
        isPreparing = true;
        currentPrepTimer = fuseTime;
        enemy.canMove = false; // Stop moving to prepare explosion
    }

    private void explode() {
        // 1. Apply explosion damage to player if within radius
        if (Global.player != null && !Global.player.isQueuedForDeletion()) {
            double dist = getGlobalPosition().distanceTo(Global.player.getGlobalPosition());
            if (dist <= explosionRadius) {
                HealthComponent playerHealth = (HealthComponent) Global.player.getNode("HealthComponent");
                if (playerHealth != null) {
                    playerHealth.takeDamage(explosionDamage);
                }
            }

            // 2. Shoot 12 projectiles in a full 360-degree circle
            if (projectileScene != null) {
                int numProjectiles = 12;
                double angleStep = (Math.PI * 2.0) / numProjectiles;
                Vector2 baseDir = new Vector2(1, 0);

                for (int i = 0; i < numProjectiles; i++) {
                    float angle = (float) (angleStep * i);
                    Projectile bullet = (Projectile) projectileScene.instantiate();
                    getTree().getRoot().addChild(bullet);
                    bullet.setGlobalPosition(enemy.getGlobalPosition());
                    Vector2 finalVelocity = baseDir.rotated(angle).times(projectileSpeed);
                    bullet.setProjectile(finalVelocity, projectileDamage, false, 0.0f, enemy);
                }
            }
        }

        // 3. Screen shake
        /*if (Global.camera != null) {
            Global.camera.addShake(4.5f);
        }*/

        // 4. Clean up enemy
        enemy.queueFree();
    }
}
