package game.entity;

import game.animation.Trail;
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
        // 2. TEST GAME: Tải file dữ liệu của Punch LV1 (Thay đường dẫn cho đúng máy sếp)
        String weaponPath = "res://resources/items/weapons/melee/punch/item_punch_2.tres";
        ItemWeapon testWeapon = (ItemWeapon) ResourceLoader.load(weaponPath);

        if (testWeapon != null) {
            // Test thử add 6 cái vũ khí xem nó có xếp thành hình tròn không
            addWeapon(testWeapon);
            addWeapon(testWeapon);
            addWeapon(testWeapon);
            addWeapon(testWeapon);
            addWeapon(testWeapon);
            addWeapon(testWeapon);
        }
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

		float defaultSpeed = (stats != null) ? stats.speed : 300.0f;
		Vector2 currentVelocity = moveDirection.times(defaultSpeed);

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
				moveDirection.length() > 0.0f;
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
}
