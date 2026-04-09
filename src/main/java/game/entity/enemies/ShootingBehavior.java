package game.entity.enemies; // Đổi đường dẫn cho đúng game của sếp

import game.autoloads.Global;
import game.items.weapons.projectiles.Projectile;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Marker2D;
import godot.api.Node2D;
import godot.api.PackedScene;
import godot.core.Vector2;

@RegisterClass
public class ShootingBehavior extends Node2D {

    // 1. Gắn Node từ Inspector
    @Export @RegisterProperty public Enemy enemy; // Quái vật
    @Export @RegisterProperty public Marker2D firePosition; // Cái nòng súng (Tạo Node Marker2D đặt ở mũi con quái)
    @Export @RegisterProperty public PackedScene projectileScene; // File đạn (.tscn)

    // 2. Thông số súng
    @Export @RegisterProperty public float cooldown = 3.0f; // 3s bắn 1 lần
    @Export @RegisterProperty public int projectileCount = 3; // Bắn 3 viên 1 lúc
    @Export @RegisterProperty public float arcAngle = 45.0f; // Xòe 45 độ
    @Export @RegisterProperty public float projectileSpeed = 380.0f; // Tốc độ đạn bay

    // 3. Biến quản lý trạng thái
    private double currentCooldown = 0.0;
    private double waitTimer = 0.0; // Đồng hồ thay cho 'await' 1 giây
    private boolean isWaiting = false;

    @RegisterFunction
    @Override
    public void _ready() {
        currentCooldown = cooldown;
    }

    @RegisterFunction
    @Override
    public void _process(double delta) {
        if (enemy == null) return;

        // TRẠNG THÁI 1: Bắn xong, đứng chờ 1 giây cho ngầu
        if (isWaiting) {
            waitTimer -= delta;
            if (waitTimer <= 0) {
                isWaiting = false;
                enemy.canMove = true; // Hết 1 giây, cho phép quái đi tiếp
            }
            return;
        }

        // TRẠNG THÁI 2: Đếm ngược hồi chiêu
        if (currentCooldown > 0) {
            currentCooldown -= delta;
        } else {
            // Hết Cooldown -> Kiểm tra Player còn sống không thì Bóp cò!
            if (Global.player != null && !Global.player.isQueuedForDeletion()) {
                shoot();
                currentCooldown = cooldown; // Bắn xong quay lại đếm 3s
            }
        }
    }

    // HÀM BÓP CÒ
    private void shoot() {
        if (projectileScene == null || firePosition == null) return;

        enemy.canMove = false; // Bắt quái đứng im khi bắn

        // Lấy hướng cơ bản: Nối từ nòng súng tới mặt Player
        Vector2 baseDirection = firePosition.getGlobalPosition().directionTo(Global.player.getGlobalPosition());

        // Áp dụng Toán học tính Góc xòe (Đổi từ Độ sang Radian vì Godot dùng Radian để xoay)
        float startAngle = (float) Math.toRadians(-arcAngle / 2.0f);
        float angleStep = 0.0f;
        if (projectileCount > 1) {
            angleStep = (float) Math.toRadians(arcAngle / (projectileCount - 1));
        }

        // Vòng lặp đẻ ra N viên đạn
        for (int i = 0; i < projectileCount; i++) {
            // 1. Ép kiểu ngay từ lúc đẻ ra thành Projectile (Sửa lỗi Node không có tọa độ)
            Projectile bullet = (Projectile) projectileScene.instantiate();

            // Thêm vào Map trước để Godot nhận diện
            getTree().getRoot().addChild(bullet);

            // 2. Set vị trí ngay nòng súng
            bullet.setGlobalPosition(firePosition.getGlobalPosition());

            // 3. Tính toán góc bắn
            float currentAngle = startAngle + (angleStep * i);
            Vector2 rotatedDirection = baseDirection.rotated(currentAngle);

            // 4. TÍNH VẬN TỐC (Dùng hàm .times() thay vì multipliedBy)
            Vector2 finalVelocity = rotatedDirection.times(projectileSpeed);

            // 5. GỌI HÀM CỦA SẾP ĐỂ BƠM TẤT CẢ VÀO ĐẠN
            // Các thông số: Vận tốc, Sát thương, Chí mạng (false), Lực đẩy lùi (0), Nguồn bắn (enemy)
            // Sếp thay số 10.0 bằng thông số sát thương thực tế của quái nhé!
            bullet.setProjectile(finalVelocity, 10.0, false, 0.0f, enemy);
        }

        // Bắn xong rồi, bật đồng hồ đứng chờ 1 giây
        isWaiting = true;
        waitTimer = 1.0;
    }
}