package game.entity;

import game.entity.enemies.Enemy;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.api.AnimationPlayer;
import godot.api.Node;
import godot.core.Color;
import godot.core.VariantArray;

@RegisterClass
public class PlayerChrono extends Player {
    
    public static boolean isTimeWarpActive = false;
    private boolean wasDashing = false;

    @RegisterFunction
    @Override
    public void _ready() {
        super._ready();
    }

    @RegisterFunction
    @Override
    public void _process(double delta) {
        // Run standard movement and processing logic at normal speed
        super._process(delta);

        if (isDashing) {
            if (!wasDashing) {
                wasDashing = true;
                isTimeWarpActive = true;
                
                // Bluish effect for the Chrono Player
                if (visuals != null) {
                    visuals.setModulate(new Color(0.4f, 0.7f, 1.0f, 0.8f));
                }
                
                // Tint all active enemies and slow their animations
                modulateEnemies(new Color(0.5f, 0.5f, 0.7f, 0.8f), 0.15f);
            }
        } else {
            if (wasDashing) {
                wasDashing = false;
                isTimeWarpActive = false;
                
                // Reset player visuals
                if (visuals != null) {
                    visuals.setModulate(new Color(1.0f, 1.0f, 1.0f, 1.0f));
                }
                
                // Reset enemies visuals and animations
                modulateEnemies(new Color(1.0f, 1.0f, 1.0f, 1.0f), 1.0f);
            }
        }
    }

    private void modulateEnemies(Color color, float animationSpeedScale) {
        Node parent = getParent();
        if (parent != null) {
            VariantArray<Node> children = parent.getChildren();
            for (int i = 0; i < children.size(); i++) {
                Node child = children.get(i);
                if (child instanceof Enemy) {
                    Enemy enemy = (Enemy) child;
                    enemy.setModulate(color);
                    
                    // Slow down/restore enemy animations
                    AnimationPlayer enemyAnim = (AnimationPlayer) enemy.getNode("AnimationPlayer");
                    if (enemyAnim != null) {
                        enemyAnim.setSpeedScale(animationSpeedScale);
                    }
                }
            }
        }
    }
}
