package game;

import godot.annotation.Export;
import godot.annotation.RegisterProperty;
import godot.api.Input;
import godot.api.Node2D;
import godot.api.Sprite2D;
import godot.api.AnimationPlayer;
import godot.api.Timer;
import godot.api.CollisionShape2D;
import godot.core.Vector2;
import godot.core.Color;
import godot.core.StringName;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;

@RegisterClass
public class Player extends BaseUnit {
	private Vector2 moveDirection = new Vector2(0.0f, 0.0f);
	private final float defaultSpeed = 500.0f;

	// --- CÁC BIẾN CHO DASH ---
	private Timer dashTimer;
	private Timer dashCooldownTimer;
	private CollisionShape2D collision;

	@Export
	@RegisterProperty
	public float dashDuration = 0.4f;

	@Export
	@RegisterProperty
	public float dashSpeedMulti = 2.7f;

	@Export
	@RegisterProperty
	public float dashCooldown = 1.5f;
	private boolean isDashing = false;

	@RegisterFunction
	@Override
	public void _ready() {
		// Tìm các Node cơ bản
		visuals = (Node2D) getNode("%Visuals");
		sprite = (Sprite2D) getNode("%Sprite");
		animPlayer = (AnimationPlayer) getNode("AnimationPlayer");

		// Tìm các Node cho Dash
		dashTimer = (Timer) getNode("DashTimer");
		dashCooldownTimer = (Timer) getNode("DashCooldownTimer");
		collision = (CollisionShape2D) getNode("CollisionShape2D");

		// Cài đặt thời gian cho Timer
		if (dashTimer != null) dashTimer.setWaitTime(dashDuration);
		if (dashCooldownTimer != null) dashCooldownTimer.setWaitTime(dashCooldown);
	}

	@RegisterFunction
	@Override
	public void _process(double delta) {
		float fDelta = (float) delta;

		moveDirection = Input.INSTANCE.getVector("move_left", "move_right", "move_up", "move_down", 0.5f);

		// Kiểm tra xem có thể Dash không
		if (canDash()) {
			startDash();
		}
		Vector2 currentVelocity = moveDirection.times(defaultSpeed);

		// Nếu đang Dash, nhân tốc độ lên
		if (isDashing) {
			currentVelocity = currentVelocity.times(dashSpeedMulti);
		}

		Vector2 currentPos = getPosition();
		Vector2 newPos = currentPos.plus(currentVelocity.times(fDelta));
		setPosition(newPos);

		updateAnimations();
		updateRotation();
	}

	// --- LOGIC DASH ---
	private boolean canDash() {
		return !isDashing &&
				dashCooldownTimer != null && dashCooldownTimer.isStopped() &&
				Input.INSTANCE.isActionJustPressed("dash", false) &&
				moveDirection.length() > 0.0f;
	}

	private void startDash() {
		isDashing = true;

		if (dashTimer != null) dashTimer.start(-1.0); // Bắt đầu đếm ngược

		// Làm mờ nhân vật đi một nửa (Alpha = 0.5)
		if (visuals != null) {
			visuals.setModulate(new Color(1.0f, 1.0f, 1.0f, 0.5f));
		}

		// Tắt va chạm một cách an toàn (set_deferred)
		if (collision != null) {
			collision.setDeferred(new StringName("disabled"), true);
		}
	}

	// Hàm này sẽ được gọi khi DashTimer chạy xong (chạm mức 0)
	@RegisterFunction
	public void _on_dash_timer_timeout() {
		isDashing = false;

		// Trả lại màu sắc bình thường (Alpha = 1.0)
		if (visuals != null) {
			visuals.setModulate(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		}

		moveDirection = new Vector2(0.0f, 0.0f);

		// Bắt đầu thời gian chờ (Cooldown)
		if (dashCooldownTimer != null) {
			dashCooldownTimer.start(-1.0);
		}

		// Bật lại va chạm
		if (collision != null) {
			collision.setDeferred(new StringName("disabled"), false);
		}
	}

	private void updateAnimations() {
		if (animPlayer != null) {
			if (moveDirection.length() > 0.0f) {
				animPlayer.play("move", -1.0, 1.0f, false);
			} else {
				animPlayer.play("idle", -1.0, 1.0f, false);
			}
		}
	}

	private void updateRotation() {
		if (visuals != null) {
			if (moveDirection.length() == 0.0f) return;
			if (moveDirection.getX() > 0.1f) {
				visuals.setScale(new Vector2(-0.5f, 0.5f));
			} else if (moveDirection.getX() < -0.1f) {
				visuals.setScale(new Vector2(0.5f, 0.5f));
			}
		}
	}
}
