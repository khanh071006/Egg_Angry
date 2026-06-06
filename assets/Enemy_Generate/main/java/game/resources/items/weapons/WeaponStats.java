package game.resources.items.weapons;

import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterProperty;
import godot.api.PackedScene;
import godot.api.Resource;

@RegisterClass
public class WeaponStats extends Resource {

    // 1. BẮT BUỘC: Constructor rỗng (Quy tắc "sống còn" của anh em mình)
    public WeaponStats() {
        super();
    }

    // 2. CHỈ SỐ CHIẾN ĐẤU CƠ BẢN
    @Export @RegisterProperty public float damage = 1.0f;
    @Export @RegisterProperty public float accuracy = 0.9f;     // Độ chính xác (0.0 -> 1.0)
    @Export @RegisterProperty public float cooldown = 1.0f;     // Thời gian hồi chiêu (giây)
    @Export @RegisterProperty public float critChance = 0.05f;  // Tỷ lệ chí mạng (5%)
    @Export @RegisterProperty public float critDamage = 1.5f;   // Sát thương chí mạng (x1.5)
    @Export @RegisterProperty public float maxRange = 150.0f;   // Tầm đánh xa nhất
    @Export @RegisterProperty public float knockback = 0.0f;    // Lực đẩy lùi quái
    @Export @RegisterProperty public float lifesteal = 0.0f;    // Hút máu (0.0 -> 1.0)

    // 3. THÔNG SỐ DIỄN HOẠT (ANIMATION BY CODE)
    // Dùng để tạo hiệu ứng vung tay hoặc giật súng mà không cần làm AnimationPlayer
    @Export @RegisterProperty public float recoil = 25.0f;          // Độ giật (pixel)
    @Export @RegisterProperty public float recoilDuration = 0.1f;   // Thời gian giật về
    @Export @RegisterProperty public float attackDuration = 0.2f;   // Thời gian lao tới mục tiêu
    @Export @RegisterProperty public float backDuration = 0.15f;    // Thời gian thu tay về vị trí cũ

    // 4. DÀNH CHO VŨ KHÍ TẦM XA (PROJECTILES)
    @Export @RegisterProperty public PackedScene projectileScene;   // File .tscn của viên đạn
    @Export @RegisterProperty public float projectileSpeed = 800.0f; // Tốc độ bay của đạn
}