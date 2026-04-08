package game.items.weapons.projectiles;

import game.components.HitBoxComponent;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Area2D;
import godot.api.Node2D;
import godot.core.Vector2;
import godot.global.GD;

@RegisterClass
public class Projectile extends Node2D {

    @Export
    @RegisterProperty
    public HitBoxComponent hitbox;

    private Vector2 velocity = new Vector2(0, 0);

    @RegisterFunction
    public void setProjectile(Vector2 velocity,double damage,boolean critical, float knockback, Node2D unit){
        this.velocity = velocity;

        // Quay đầu viên đạn theo hướng bay
        this.setRotation((float) velocity.angle());

        // Bơm thông số sát thương vào Hitbox
        if (hitbox != null) {
            hitbox.setup((float) damage, critical, knockback, unit);
        }
    }

    @Override
    @RegisterFunction
    public void _process(double delta){
        double newX = this.getPosition().getX() + (velocity.getX() * delta);
        double newY = this.getPosition().getY() + (velocity.getY() * delta);
        setPosition(new Vector2(newX, newY));
    }

    @RegisterFunction
    public void _on_visible_on_screen_enabler_2d_screen_exited(){
        GD.print("Đạn đã bay màu khỏi màn hình!");
        queueFree(); // Hủy viên đạn cho đỡ tốn RAM
    }

    @RegisterFunction
    public void _on_hitbox_component_on_hit_hurtbox(Area2D hurtbox){
        queueFree();
    }
}
