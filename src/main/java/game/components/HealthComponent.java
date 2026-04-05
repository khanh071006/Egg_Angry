package game.components;

import game.resources.units.UnitStats;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterSignal;
import godot.api.Node;
import godot.api.Node2D;
import godot.core.Signal0;
import godot.core.Signal2;
import godot.core.StringName;
import godot.global.GD;


@RegisterClass
public class HealthComponent extends Node {

    public float maxHealth = 1.0f;
    public float currentHealth = 1.0f;

    @RegisterSignal
    public Signal0 onUnitHit = Signal0.create(this, "onUnitHit");

    @RegisterSignal
    public Signal0 onUnitDie = Signal0.create(this, "onUnitDie");

    @RegisterSignal
        public Signal2<Float, Float> onHealthChanged = Signal2.create(this, "onHealthChanged");

    @RegisterFunction
    public void setup(UnitStats stats) {
        this.maxHealth = stats.health;
        this.currentHealth = this.maxHealth;
        onHealthChanged.emit(currentHealth, maxHealth);
    }

    @RegisterFunction
    public void takeDamage(float value){
        if (currentHealth <= 0){
            return;
        }

        currentHealth -= value;
        currentHealth = Math.max(currentHealth,0);

        onUnitHit.emit();
        onHealthChanged.emit(currentHealth,maxHealth);

        if (currentHealth <= 0){
            currentHealth = 0;
            onUnitDie.emit();
            Die();
        }
    }

    @RegisterFunction
    public void Die(){
        Node parent = getParent();
        if (parent != null){

            // 1. ĐÓNG BĂNG MỌI HOẠT ĐỘNG (Tương đương ProcessMode = PROCESS_MODE_DISABLED)
            // Lệnh này ép con quái ngừng chạy _process, ngừng AI, ngừng tính toán vật lý
            parent.setDeferred(new StringName("process_mode"), 4);

            // 2. TÀNG HÌNH (Ẩn nó khỏi màn hình)
            parent.setDeferred(new StringName("visible"), false);

            // 3. ĐÀY RA ĐẢO XA (Dịch chuyển nó ra khỏi bản đồ chơi để chắc chắn 100% không ai chạm trúng)
            if (parent instanceof Node2D) {
                // Ép kiểu tọa độ bằng godot.core.Vector2
                ((Node2D) parent).setDeferred(new StringName("global_position"), new godot.core.Vector2(-9999, -9999));
            }
        }
    }

    @RegisterFunction
    public void heal(float amount) {
        if (currentHealth <= 0) {
            return;
        }

        currentHealth += amount;
        currentHealth = Math.min(currentHealth, maxHealth);

        onHealthChanged.emit(currentHealth, maxHealth);
    }
}
