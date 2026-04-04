package game.items.weapons;

import game.resources.items.weapons.ItemWeapon;


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

    // --- Data & State ---
    @RegisterProperty
    public ItemWeapon data;

    public boolean isAttacking = false;
    public Vector2 attackStartPosition = new Vector2();

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

        if (data != null) {
            GD.print("Đã lắp vũ khí: " + data.itemName + " - Sát thương: " + data.stats.damage);
            // ... logic setRadius giữ nguyên ...
        } else {
            GD.printErr("LỖI: Chưa kéo thả file WeaponStats vào ItemWeapon!");
        }
        // Cập nhật tầm đánh từ Stats vào CollisionShape
        if (data != null && data.stats != null && collision != null) {
            CircleShape2D shape = (CircleShape2D) collision.getShape();
            if (shape != null) {
                shape.setRadius(data.stats.maxRange);
            }
        }
    }

    @RegisterFunction
    public boolean canUseWeapon() {
        // Có thể dùng nếu Timer đã dừng VÀ có mục tiêu
        return cooldownTimer.isStopped() && closestTarget != null;
    }

    // --- Signal Handlers ---

    @RegisterFunction
    public void _on_range_area_area_entered(Area2D area) {
        // Lấy chủ sở hữu (Enemy) của Area
        Node enemy = area.getOwner();

        // Thêm vào danh sách (dùng add của Java)
        if (!targets.contains(enemy)) {
            targets.add(enemy);
        }
        // Debug xem quái có thực sự bước vào vùng đánh chưa
        GD.print("Phát hiện mục tiêu: " + enemy.getName());

        if (!targets.contains(enemy)) {
            targets.add(enemy);
            GD.print("Số lượng mục tiêu trong tầm: " + targets.size());
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
}