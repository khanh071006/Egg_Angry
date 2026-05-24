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
        applyTierOutline();
    }

    @RegisterFunction
    public void applyTierOutline() {
        if (data == null || data.itemTier == Global.UpgradeTier.COMMON) {
            if (sprite != null) {
                sprite.setMaterial(null);
            }
            return;
        }

        godot.core.Color outlineColor = Global.instance.getTierColor(data.itemTier);

        if (sprite != null && Global.OUTLINE_MATERIAL != null) {
            ShaderMaterial material = (ShaderMaterial) Global.OUTLINE_MATERIAL.duplicate(false);
            
            // Chỉ ghi đè màu sắc theo Tier, các thông số khác (độ dày, glow)
            // sẽ lấy tự động từ file outline_material.tres được chỉnh trong Godot Editor
            material.setShaderParameter(new godot.core.StringName("outline_color"), outlineColor);
            
            sprite.setMaterial(material);
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

        Node enemy = area.getParent();
        if (enemy == null) return; // Nếu null thì bỏ qua luôn cho khỏi lỗi đỏ

        if (!targets.contains(enemy)) {
            targets.add(enemy);
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
        // Lọc bỏ những mục tiêu đã chết (freed) khỏi danh sách trước khi quét
        // NẾU KHÔNG LÀM VIỆC NÀY, GAME SẼ CRASH KHI GỌI TỌA ĐỘ CỦA QUÁI ĐÃ CHẾT!
        if (targets != null) {
            targets.removeIf(node -> !godot.global.GD.isInstanceValid(node) || !node.isInsideTree());
        }
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
        if (targets.isEmpty() || closestTarget == null || !godot.global.GD.isInstanceValid(closestTarget)) {
            return getIdleRotation();
        }
        // Tính góc hướng tới mục tiêu
        return this.getGlobalPosition().directionTo(((Node2D) closestTarget).getGlobalPosition()).angle();
    }

    @RegisterFunction
    public double getCustomRotationToTarget() {
        if (closestTarget == null || !godot.global.GD.isInstanceValid(closestTarget) || !closestTarget.isInsideTree()) {
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
        if (Global.gamePaused) return;
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

            // Ép Timer nhận thông số
            cooldownTimer.setWaitTime(data.stats.cooldown);

            // Thực hiện bắn
            ((WeaponBehavior) weaponBehavior).executeAttack();

            // Khởi động đồng hồ
            cooldownTimer.start();
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