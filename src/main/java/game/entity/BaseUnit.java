package game.entity;

import game.autoloads.Global;
import game.components.HealthComponent;
import game.components.HitBoxComponent;
import game.resources.units.PlayerStats;
import game.resources.units.UnitStats;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.*;
import godot.global.GD;

@RegisterClass
public class BaseUnit extends Area2D { // Đổi tên class ở đây
	// add stat
	@Export
	@RegisterProperty
	public UnitStats stats;

	// --- CÁC BIẾN CHO FlashEffects ---
	private Timer flashTimer;

	protected Node2D visuals;
	protected Sprite2D sprite;
	protected AnimationPlayer animPlayer;
	public HealthComponent healthComponent;

	@RegisterFunction
	@Override
	public void _ready() {
		visuals = (Node2D) getNode("%Visuals");
		sprite = (Sprite2D) getNode("%Sprite");
		animPlayer = (AnimationPlayer) getNode("AnimationPlayer");

		healthComponent = (HealthComponent) getNode("HealthComponent");

		// Gọi setup và truyền biến stats của Unit vào (như video)
		if (healthComponent != null && this.stats != null) {
			healthComponent.setup(this.stats);
		}

		// Setup Timer bằng code hoặc kéo thả trong Editor
		flashTimer = (Timer) getNode("FlashTimer");
		flashTimer.setWaitTime(0.2);
		flashTimer.setOneShot(true);

	}

	@RegisterFunction
	public void setFlashMaterial() {
		// 1. Gắn Shader trắng vào Sprite
		if (sprite != null && Global.FLASH_MATERIAL != null) {
			sprite.setMaterial(Global.FLASH_MATERIAL);
		}

		// 2. Chạy đồng hồ đếm ngược 0.2 giây
		flashTimer.start();
	}

	@RegisterFunction
	public void _on_hurtbox_component_on_damage(HitBoxComponent hitbox) {
		// 2. Kiểm tra máu trước khi trừ
		if (healthComponent.currentHealth <= 0) {
			return;
		}
		// block
		PlayerStats playerStats;
		boolean blocked = Global.get_chance_sucess(stats.blockchance / 100);
		if (blocked) {
			Global.instance.onCreateBlockText.emit(this);
			return;
		}

		healthComponent.takeDamage(hitbox.damage);
		Global.instance.onCreateDamageText.emit(this, hitbox);

		// Rung màn hình khi Player bị quái cắn (Độ mạnh = 2.5f)
		if (this instanceof Player && Global.camera != null) {
			Global.camera.addShake(2.5f);
		}

		// Đẩy lùi đối phương ra xa (để hitbox thoái lui rồi đâm lại, trừ máu liên tục)
		godot.api.Node sourceNode = hitbox.source;
		if (sourceNode == null) {
			sourceNode = hitbox.getOwner();
		}

		if (sourceNode instanceof game.entity.enemies.Enemy) {
			game.entity.enemies.Enemy enemy = (game.entity.enemies.Enemy) sourceNode;
			// Vector hướng từ Player chỉ ra Enemy
			godot.core.Vector2 pushDir = enemy.getGlobalPosition().minus(this.getGlobalPosition()).normalized();
			enemy.applyKnockbackAdvanced(pushDir, 30.0, false); // false = không phải lực đẩy vũ khí
		}
	}

	@RegisterFunction
	public void _on_flash_timer_timeout() {
		if (sprite != null) {
			sprite.setMaterial(null);
		}
	}
}
