package game.entity;

import game.autoloads.Global;
import game.components.HitBoxComponent;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.AnimatedSprite2D;
import godot.api.Node2D;
import godot.core.StringName;
import godot.core.Vector2;

@RegisterClass
public class Bomb extends Node2D {

    @Export
    @RegisterProperty
    public HitBoxComponent hitbox;

    @Export
    @RegisterProperty
    public Vector2 bombOffset = new Vector2(0.0f, 0.0f); // Độ lệch của quả bom khi đếm ngược

    @Export
    @RegisterProperty
    public Vector2 explosionOffset = new Vector2(0.0f, -100.0f); // Độ lệch của vụ nổ để căn vào tâm (thường dịch lên trên)

    @Export
    @RegisterProperty
    public Vector2 bombScale = new Vector2(0.05f, 0.05f); // Tỷ lệ thu nhỏ quả bom (giảm từ 1024x1024 xuống cỡ 50x50)

    @Export
    @RegisterProperty
    public Vector2 explosionScale = new Vector2(2.5f, 2.5f); // Tỷ lệ phóng to vụ nổ (tăng từ 256x262 lên cỡ 640x655)

    private AnimatedSprite2D sprite;

    @RegisterFunction
    @Override
    public void _ready() {
        sprite = (AnimatedSprite2D) getNode("AnimatedSprite2D");

        // 1. Tự động tìm HitboxComponent nếu quên kéo thả trong Inspector
        if (hitbox == null) {
            hitbox = (HitBoxComponent) getNode("HitboxComponent");
            if (hitbox == null) {
                hitbox = (HitBoxComponent) getNode("HitBoxComponent");
            }
        }

        godot.global.GD.print("BOMB _ready: hitbox = " + hitbox);
        if (hitbox != null) {
            // 2. Tự động cấu hình va chạm bằng code để tránh lỗi quên cấu hình trên Editor
            hitbox.setCollisionLayer(4L); // Layer 3 (Player Weapon)
            hitbox.setCollisionMask(34L);  // Quét quái vật (Layer 2 + Layer 6 = 34)
            hitbox.disable();
            godot.global.GD.print("BOMB _ready: hitbox collision_layer = " + hitbox.getCollisionLayer() + ", collision_mask = " + hitbox.getCollisionMask());
        }

        // Bắt đầu chạy hoạt ảnh quả bom đếm ngược ("bomb")
        if (sprite != null) {
            sprite.setPosition(bombOffset); // Áp dụng độ lệch quả bom
            sprite.setScale(bombScale);     // Cài đặt tỷ lệ nhỏ cho quả bom
            sprite.play(new StringName("bomb"), 1.0f, false);
        }
    }

    // Kết nối hàm này với signal "timeout" của FuseTimer trên Editor
    @RegisterFunction
    public void _on_fuse_timer_timeout() {
        explode();
    }

    // Thực thi vụ nổ
    private void explode() {
        godot.global.GD.print("BOMB explode called");
        // 1. Chạy hoạt ảnh nổ tung và dịch chuyển vị trí tương ứng
        if (sprite != null) {
            sprite.setPosition(explosionOffset); // Áp dụng độ lệch của vụ nổ (dịch lên tâm)
            sprite.setScale(explosionScale);     // Cài đặt tỷ lệ lớn cho vụ nổ
            sprite.play(new StringName("explosion"), 1.0f, false);
        }

        // 2. Thiết lập sát thương nổ (Sát thương bom = 200% Dame của người chơi)
        if (hitbox != null) {
            float baseDamage = 25.0f;
            if (Global.player != null && Global.player.stats != null) {
                baseDamage = Global.player.stats.damage * 2.0f;
            }
            hitbox.setup(baseDamage, false, 4.0f, this);
            hitbox.enable(); // Bật vòng sát thương lên
            godot.global.GD.print("BOMB explode: hitbox enabled. Damage = " + baseDamage + ", monitoring = " + hitbox.isMonitoring() + ", monitorable = " + hitbox.isMonitorable());
        }

        // 3. Rung màn hình khi bom nổ! (Độ mạnh = 5.0f)
        if (Global.camera != null) {
            Global.camera.addShake(5.0f);
        }
    }

    // Kết nối hàm này với signal "animation_finished" của AnimatedSprite2D trên Editor
    @RegisterFunction
    public void _on_animated_sprite_2d_animation_finished() {
        if (sprite == null) return;

        String animName = sprite.getAnimation().toString();

        if ("bomb".equals(animName)) {
            // Khi đếm ngược xong -> Kích hoạt nổ
            explode();
        } 
        else if ("explosion".equals(animName)) {
            // Chỉ xóa quả bom khi hoạt ảnh "explosion" đã chạy xong xuôi
            if (hitbox != null) {
                hitbox.disable();
            }
            queueFree(); // Hủy quả bom
        }
    }
}
