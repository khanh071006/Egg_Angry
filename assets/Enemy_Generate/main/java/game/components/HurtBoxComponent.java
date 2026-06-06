package game.components;

import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterSignal;
import godot.api.Area2D;
import godot.core.StringName;
import godot.core.Signal1; // THÊM DÒNG NÀY

@RegisterClass
public class HurtBoxComponent extends Area2D {

    // SỬA CHỖ NÀY: Khai báo Signal1 mang theo gói hàng là HitboxComponent
    @RegisterSignal
    public Signal1<HitBoxComponent> onDamage = Signal1.create(this, "onDamage");

    @RegisterFunction
    public void _on_area_entered(Area2D area) {
        if (area instanceof HitBoxComponent hitbox) {

            // SỬA CHỖ NÀY: Cầm mic lên và phát tín hiệu!
            onDamage.emit(hitbox);
        }
    }
}