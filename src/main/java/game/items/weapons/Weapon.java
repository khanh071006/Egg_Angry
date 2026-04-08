package game.items.weapons;

import game.autoloads.Global;
import game.resources.items.weapons.ItemWeapon;


import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.*;
import godot.core.Vector2;
import godot.global.GD;

// Import 2 thư viện chuẩn của Java
import java.util.ArrayList;
import java.util.List;

@RegisterClass
public class Weapon extends Node2D {

    // --- References to Nodes (Unique Names) ---
    private Sprite2D sprite;
    private CollisionShape2D collision;
    private Timer cooldownTimer;


    public Sprite2D getSprite() {
        return sprite;
    }

    @Export
    @RegisterProperty
    public Node2D weaponBehavior;

    // --- Data & State ---
    @RegisterProperty
    public ItemWeapon data;

    public boolean isAttacking = false;
    public Vector2 attackStartPosition = new Vector2();
    public double weaponSpread = 0.0;

    // Dùng ArrayList của Java thay cho VariantArray (Dứt điểm gạch đỏ!)
    public List<Node> targets = new ArrayList<>();
    public Node closestTarget = null;

    @RegisterFunction
    @Override
    public void _ready() {
        // Lấy node bằng Unique Name
        sprite = (Sprite2D) getNode("%Sprite2D");
        collision = (CollisionShape2D) getNode("%CollisionShape2D");
        cooldownTimer = (Timer) getNode("%CooldownTimer");

        if (sprite != null) {
            attackStartPosition = sprite.getPosition();
        }
    }

    @RegisterFunction
    public void setupWeapon(ItemWeapon weaponData) {
        this.data = weaponData;

        // Cập nhật tầm đánh từ Stats vào CollisionShape
        if (data != null && data.stats != null && collision != null) {
//            CircleShape2D shape = (CircleShape2D) collision.getShape();
//            if (shape != null) {
//                shape.setRadius(data.stats.maxRange);
//            }

            CircleShape2D newShape = new CircleShape2D();
            newShape.setRadius((float) data.stats.maxRange); // Cài tầm đánh
            collision.setShape(newShape); // Lắp vòng tròn mới vào vũ khí
        }
    }

    @RegisterFunction
    public boolean canUseWeapon() {
        // Có thể dùng nếu Timer đã dừng VÀ có mục tiêu
        // IN RA ĐỂ SOI XEM NÓ ĐỌC ĐÚNG THÔNG SỐ KHÔNG
        return cooldownTimer.isStopped() && closestTarget != null;

    }

    // --- Signal Handlers ---

    @RegisterFunction
    public void _on_range_area_area_entered(Area2D area) {
        // 1. IN RA THẲNG TÊN CỦA AREA (Tuyệt đối không dùng getOwner ở bước này để chống crash)
        GD.print("QUÉT TRÚNG RỒI! Tên Area là: " + area.getName());

        Node enemy = area.getParent();
        if (enemy == null) return; // Nếu null thì bỏ qua luôn cho khỏi lỗi đỏ

        if (!targets.contains(enemy)) {
            targets.add(enemy);
            GD.print("Đã thêm quái: " + enemy.getName() + " | Số lượng: " + targets.size());
        }
    }

    @RegisterFunction
    public void _on_range_area_area_exited(Area2D area) {
        // Xóa khỏi danh sách (dùng remove của Java)
        targets.remove(area.getOwner());

        // Kiểm tra rỗng (dùng isEmpty của Java)
        if (targets.isEmpty()) {
            closestTarget = null;
        }
    }

    // --- LOGIC TÌM MỤC TIÊU ---

    @RegisterFunction
    public void updateClosestTarget() {
        closestTarget = getClosestTarget();
    }

    @RegisterFunction
    public Node2D getClosestTarget() {
        if (targets == null || targets.isEmpty()) return null;
        Node2D closestEnemy = (Node2D) targets.get(0);
        double closestDistance = this.getGlobalPosition().distanceTo(closestEnemy.getGlobalPosition());

        for (int j = 1; j < targets.size(); j++) {
            Node2D target = (Node2D) targets.get(j);
            double distance = this.getGlobalPosition().distanceTo(target.getGlobalPosition());
            if (distance < closestDistance) {
                closestDistance = distance;
                closestEnemy = target;
            }
        }
        return closestEnemy;
    }

    @RegisterFunction
    public double getIdleRotation() {
        if (Global.player != null && Global.player.isFacingRight()) {
            return 0.0; // Chỉ thẳng sang phải
        }
        return Math.PI; // Xoay 180 độ (Pi radian) sang trái
    }

    @RegisterFunction
    public void calculateSpread() {
        if (data != null && data.stats != null) {
            double range = 1.0 - data.stats.accuracy;
            weaponSpread = GD.randfRange((float) -range, (float) range);
        }
    }

    @RegisterFunction
    public double getRotationToTarget() {
        if (targets.isEmpty() || closestTarget == null) {
            return getIdleRotation();
        }
        // Tính góc hướng tới mục tiêu
        return this.getGlobalPosition().directionTo(((Node2D) closestTarget).getGlobalPosition()).angle();
    }

    @RegisterFunction
    public double getCustomRotationToTarget() {
        if (closestTarget == null || !closestTarget.isInsideTree()) {
            return this.getRotation();
        }
        // Tính góc tới mục tiêu + độ giật (spread)
        double rot = this.getGlobalPosition().directionTo(((Node2D) closestTarget).getGlobalPosition()).angle();
        return rot + weaponSpread;
    }

    @RegisterFunction
    public void rotateToTarget() {
        if (isAttacking) {
            this.setRotation((float) getCustomRotationToTarget());
        } else {
            this.setRotation((float) getRotationToTarget());
        }
    }

    // --- VÒNG LẶP KHUNG HÌNH ---

    @RegisterFunction
    @Override
    public void _process(double delta) {
        if (!isAttacking) {
            if (!targets.isEmpty()) {
                updateClosestTarget();
            } else {
                closestTarget = null;
            }
            rotateToTarget();
        }
        if (canUseWeapon())
            useWeapon();
        updateVisuals();
    }

    // Sử dụng weapon
    @RegisterFunction
    public void useWeapon() {
        // KIỂM TRA ĐIỀU KIỆN:
        // 1. Đồng hồ Cooldown phải dừng (hết thời gian chờ)
        // 2. Không được đang trong trạng thái đánh (isAttacking = false)
        if (cooldownTimer.isStopped() && !isAttacking) {

            GD.print("==== KIỂM TRA TIMER ====");

            // BẰNG CHỨNG 1: Xem Java có đọc đúng file Stats không? (Nhập 10s nó có ra 10s không)
            GD.print("1. File Stats báo Cooldown là: " + data.stats.cooldown + " giây");

            // Ép Timer nhận thông số
            cooldownTimer.setWaitTime(data.stats.cooldown);

            // BẰNG CHỨNG 2: Xem cái Node Timer nó có chịu "nuốt" cái con số 10s kia không?
            GD.print("2. Timer thực tế đang cài đặt WaitTime là: " + cooldownTimer.getWaitTime() + " giây");

            // Thực hiện bắn
            ((WeaponBehavior) weaponBehavior).executeAttack();

            // Khởi động đồng hồ
            cooldownTimer.start();

            // BẰNG CHỨNG 3: Xem lúc đang chạy, đồng hồ nó đếm ngược từ số mấy?
            GD.print("3. Đã bấm Start! Thời gian đếm ngược còn lại: " + cooldownTimer.getTimeLeft() + " giây");
            GD.print("========================");
        }
    }

    @RegisterFunction
    public void updateVisuals() {
        if (sprite == null) return;

        // Lấy góc quay hiện tại của toàn bộ cây súng
        double currentRotation = this.getRotation();

        // Lấy Scale (kích thước) hiện tại của Sprite
        Vector2 currentScale = sprite.getScale();

        // Kiểm tra xem trị tuyệt đối của góc quay có lớn hơn 90 độ (PI/2) không
        if (Math.abs(currentRotation) > (Math.PI / 2.0)) {
            // Đang chĩa sang TRÁI -> Lật ngược trục Y (cho nó thành số âm)
            sprite.setScale(new Vector2(currentScale.getX(), -Math.abs(currentScale.getY())));
        } else {
            // Đang chĩa sang PHẢI -> Giữ trục Y bình thường (số dương)
            sprite.setScale(new Vector2(currentScale.getX(), Math.abs(currentScale.getY())));
        }
    }
}