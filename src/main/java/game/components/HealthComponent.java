package game.components;

import game.entity.enemies.Enemy;
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
			if (parent instanceof game.entity.Player) {
				((game.entity.Player) parent).stopMovement();
				game.autoloads.Global.isAttack = false;
				game.autoloads.Global.gamePaused = true;
				if (getTree() != null) {
					getTree().createTimer(1.0).getTimeout().connect(godot.core.Callable.create(this, new StringName("go_to_game_over")), 0);
				}
				// Chỉ tắt va chạm của Player để quái không đánh nữa, KHÔNG tắt process_mode
				if (parent instanceof godot.api.CollisionObject2D) {
					((godot.api.CollisionObject2D) parent).setDeferred(new StringName("collision_layer"), 0);
					((godot.api.CollisionObject2D) parent).setDeferred(new StringName("collision_mask"), 0);
				}
			} else {
				// ĐỐI VỚI ENEMY:
				// 1. ĐÓNG BĂNG MỌI HOẠT ĐỘNG
				parent.setDeferred(new StringName("process_mode"), 4);

				// 2. TÀNG HÌNH (Ẩn nó khỏi màn hình)
				parent.setDeferred(new StringName("visible"), false);
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

	@RegisterFunction
	public void goToGameOver() {
		if (getTree() != null) {
			getTree().changeSceneToFile("res://scenes/ui/GameOverScene.tscn");
		}
	}
}
