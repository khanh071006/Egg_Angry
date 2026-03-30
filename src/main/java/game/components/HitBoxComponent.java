package game.components;


import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.annotation.RegisterSignal;
import godot.api.Area2D;
import godot.api.Node2D;
import godot.core.Signal1;
import godot.core.StringName;
import godot.global.GD;

@RegisterClass
public class HitBoxComponent extends Area2D {

    @RegisterProperty
    public float damage;

    @RegisterProperty
    public boolean critical;

    @RegisterProperty
    public float knockbackPower;

    public Node2D source;

    // SỬA CHỖ NÀY: Khai báo Signal1 (vì nó mang theo 1 tham số là HurtboxComponent)
    // Chữ "hurtbox" trong ngoặc là tên của gói hàng để Godot hiển thị ra ngoài
    @RegisterSignal
    public Signal1<HurtBoxComponent> onHitHurtbox = Signal1.create(this, "onHitHurtbox");

    @RegisterFunction
    public void enable(){
        setDeferred(new StringName("monitoring"),true);
        setDeferred(new StringName("monitorable"), true);
    }

    @RegisterFunction
    public void disable(){
        setDeferred(new StringName("monitoring"),false);
        setDeferred(new StringName("monitorable"), false);
    }

    @RegisterFunction
    public void setup(float damage, boolean critical, float knockback, Node2D source) {
        this.damage = damage;
        this.critical = critical;
        this.knockbackPower = knockback;
        this.source = source;
    }
    @RegisterFunction
    public void _on_area_entered(Area2D area){
        if (area instanceof HurtBoxComponent){
            HurtBoxComponent hurtbox = (HurtBoxComponent) area;

            onHitHurtbox.emit(hurtbox);

            if (area.getOwner() != null) {
                GD.print("CHÉM TRÚNG: " + area.getOwner().getName());
            }
        }
    }


}
