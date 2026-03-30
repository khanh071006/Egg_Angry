package game.components;

import game.resources.UnitStats;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterSignal;
import godot.api.Node;
import godot.core.Signal0;
import godot.core.Signal2;

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
        if (getOwner() != null){
            getOwner().queueFree();
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
