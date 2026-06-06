package game.entity;

import game.animation.Trail;
import game.autoloads.Global;
import game.items.weapons.Weapon;
import game.resources.items.weapons.ItemWeapon;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.*;
import godot.core.Color;
import godot.core.StringName;
import godot.core.Vector2;

import java.util.ArrayList;
import java.util.List;

@RegisterClass
public class Player extends BaseUnit {
//	//Player Stats
//	@Export
//	@RegisterProperty
//	public PlayerStats stats;


	private Vector2 moveDirection = new Vector2(0.0f, 0.0f);



	// --- CÁC BIẾN CHO DASH ---
	private Timer dashTimer;
	private Timer dashCooldownTimer;
	private CollisionShape2D collision;
	private Trail trail;

    // Weapon
    // Tham chiếu đến cái Container sếp vừa làm ở video trước
    private WeaponContainer weaponContainer;

    // Bể chứa các vũ khí Player đang cầm
    private List<Node> currentWeapons = new ArrayList<>();

    public List<Node> getCurrentWeapons() {
        return currentWeapons;
    }


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

	// Biến làm chậm (Slow)
	private double slowTimer = 0.0;
	private float currentSpeedMulti = 1.0f;

	@RegisterFunction
	public void applySlow(float duration, float multiplier) {
		slowTimer = duration;
		currentSpeedMulti = multiplier;
	}

	@RegisterFunction
	@Override
	public void _ready() {
        //Run parent constructor
        super._ready();

		// Tìm các Node cơ bản
		visuals = (Node2D) getNode("%Visuals");
		sprite = (Sprite2D) getNode("%Sprite");
		animPlayer = (AnimationPlayer) getNode("AnimationPlayer");

		// Tìm các Node cho Dash
		dashTimer = (Timer) getNode("DashTimer");
		dashCooldownTimer = (Timer) getNode("DashCooldownTimer");
		collision = (CollisionShape2D) getNode("CollisionShape2D");
		trail = (Trail) getNode("%Visuals/%Trail");


		// Cài đặt thời gian cho Timer
		if (dashTimer != null) dashTimer.setWaitTime(dashDuration);
		if (dashCooldownTimer != null) dashCooldownTimer.setWaitTime(dashCooldown);

        weaponContainer = (WeaponContainer) getNode("%WeaponContainer");
	}

	@RegisterFunction
	@Override
	public void _process(double delta) {
        if (Global.gamePaused) return;
		float fDelta = (float) delta;

		moveDirection = Input.INSTANCE.getVector("move_left", "move_right", "move_up", "move_down", 0.5f);

		// Kiểm tra xem có thể Dash không
		if (canDash()) {
			startDash();
		}

		float defaultSpeed = (stats != null) ? stats.speed : 300.0f;
		
		// Xử lý làm chậm
		if (slowTimer > 0.0) {
			slowTimer -= delta;
            
            // Phủ băng xanh lên Player bằng Modulate
            if (sprite != null) {
                sprite.setModulate(new godot.core.Color(0.5f, 0.8f, 1.0f, 1.0f));
            }
            
			if (slowTimer <= 0.0) {
				currentSpeedMulti = 1.0f; // Hết thời gian làm chậm, về lại bình thường
                // Trả lại màu gốc
                if (sprite != null) {
                    sprite.setModulate(new godot.core.Color(1.0f, 1.0f, 1.0f, 1.0f));
                }
			}
		}

		Vector2 currentVelocity = moveDirection.times(defaultSpeed * currentSpeedMulti);

		// Nếu đang Dash, nhân tốc độ lên
		if (isDashing) {
			currentVelocity = currentVelocity.times(dashSpeedMulti);
		}

		Vector2 currentPos = getPosition();
		Vector2 newPos = currentPos.plus(currentVelocity.times(fDelta));
		// Ép điểm X nằm gọn trong khoảng -1000 đến 1000
		float clampedX = (float) Math.clamp(newPos.getX(), -1000.0f, 1000.0f);
		// Ép điểm Y nằm gọn trong khoảng -500 đến 500
		float clampedY = (float) Math.clamp(newPos.getY(), -500.0f, 500.0f);

		// Đặt lại vị trí mới đã bị nhốt
		setPosition(new Vector2(clampedX, clampedY));
		updateAnimations();
		updateRotation();
	}

	// --- LOGIC DASH ---
	private boolean canDash() {
		return !isDashing &&
				dashCooldownTimer != null && dashCooldownTimer.isStopped() &&
				Input.INSTANCE.isActionJustPressed("dash", false) &&
				moveDirection.length()  > 0.0f;
	}

	private void startDash() {
		isDashing = true;

		if (dashTimer != null) dashTimer.start(-1.0); // Bắt đầu đếm ngược

		//Vẽ trail
		if (trail != null) {
			trail.startTrail();
		}

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

        //Sử dụng vũ khí

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

	@RegisterFunction
	public void stopMovement() {
		if (animPlayer != null) {
			animPlayer.play("die", -1.0, 1.0f, false);
		}
		
		if (currentWeapons != null) {
			for (godot.api.Node weaponNode : currentWeapons) {
				if (weaponNode instanceof godot.api.CanvasItem) {
					((godot.api.CanvasItem) weaponNode).setVisible(false);
				}
			}
		}
		
		godot.api.Node healthBar = getNodeOrNull("HealthBar");
		if (healthBar instanceof godot.api.CanvasItem) {
			((godot.api.CanvasItem) healthBar).setVisible(false);
		}
	}

	private void updateRotation() {
		if (visuals != null) {
			if (moveDirection.length() == 0.0f) return;
			if (moveDirection.getX() > 0.1f) {
				visuals.setScale(new Vector2(-1f, 1f));
			} else if (moveDirection.getX() < -0.1f) {
				visuals.setScale(new Vector2(1f, 1f));
			}
		}
	}

    @RegisterFunction
    public void addWeapon(ItemWeapon data){
        // 1. Lấy "Bản thiết kế" (.tscn) từ Data và đúc nó thành "Đồ thật"
        Node instance = data.weaponScene.instantiate();

        // 2. Ép kiểu nó về class Weapon của sếp
        Weapon weapon = (Weapon) instance;

        // 3. Gắn nó làm con của Player (để nó di chuyển theo Player)
        this.addChild(weapon);

        // 4. "Đổ xăng" - Truyền thông số từ ItemWeapon vào Vũ khí thực tế
        weapon.setupWeapon(data);

        // 5. Thêm vào balo và cập nhật vị trí đội hình
        currentWeapons.add(weapon);
        if (weaponContainer != null) {
            weaponContainer.updateWeaponsPosition(currentWeapons);
        }
    }

    @RegisterFunction
    public void removeWeapon(Node weapon) {
        currentWeapons.remove(weapon);
        if (weaponContainer != null) {
            weaponContainer.updateWeaponsPosition(currentWeapons);
        }
        weapon.queueFree();
    }

    public boolean isFacingRight() {
        // Nếu Scale X dương (thường là -1.0) thì là bên phải
        return this.visuals.getScale().getX() < 0;
    }

    @RegisterFunction
    public void updatePlayerNewWave() {
        if (stats == null) return;
        stats.health += stats.healthIncreasePerWave;
        if (healthComponent != null) {
            healthComponent.setup(stats);
        }
    }

    @RegisterFunction
    public void _on_hp_regen_timer_timeout() {
        if (healthComponent == null || stats == null) return;

        // Nếu máu đã hết (bằng 0) thì không hồi máu nữa
        if (healthComponent.currentHealth <= 0) return;

        // Chỉ hồi máu nếu máu hiện tại nhỏ hơn máu tối đa
        if (healthComponent.currentHealth < stats.health) {
            float healValue = stats.hpRegen;
            
            // Nếu chỉ số hồi máu bằng 0 thì không làm gì cả
            if (healValue <= 0) return;

            healthComponent.heal(healValue);
            Global.instance.onCreateHealText.emit(this, healValue);
        }
    }
}
