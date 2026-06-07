package game.entity.enemies.types;

import game.components.HitBoxComponent;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.api.AnimatedSprite2D;
import godot.api.Node2D;
import godot.core.StringName;

@RegisterClass
public class GoblinEffect extends Node2D {
    private AnimatedSprite2D anim;
    private HitBoxComponent hitbox;

    @RegisterFunction
    @Override
    public void _ready() {
        anim = (AnimatedSprite2D) getNode("AnimatedSprite2D");
        if (anim != null) {
            anim.connect("animation_finished", godot.core.Callable.create(this, new StringName("_on_animation_finished")), 0);
            anim.connect("frame_changed", godot.core.Callable.create(this, new StringName("_on_frame_changed")), 0);
        }
        
        hitbox = (HitBoxComponent) getNode("HitboxComponent");
        if (hitbox != null) {
            hitbox.disable(); // Tắt sát thương lúc gai mới nhú (cho player cơ hội né)
        }
    }

    @RegisterFunction
    public void _on_frame_changed() {
        if (anim != null && anim.getFrame() >= 2) {
            if (hitbox != null) {
                hitbox.enable(); // Bật sát thương khi gai đã nhô cao (frame 2)
            }
        }
    }

    @RegisterFunction
    public void _on_animation_finished() {
        queueFree();
    }
}
