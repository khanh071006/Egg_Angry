package game.arena;

import game.autoloads.Global;
import godot.api.Camera2D;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.core.Vector2;

import java.util.Random;

@RegisterClass
public class CameraController extends Camera2D {

    @Export
    @RegisterProperty
    public float lerpSpeed = 6.0f; // Tốc độ bắt kịp của camera (càng lớn càng nhanh)

    @Export
    @RegisterProperty
    public Vector2 cameraZoom = new Vector2(0.8f, 0.8f); // Tỷ lệ Zoom (nhỏ hơn 1.0 để nhìn rộng hơn)

    private float shakeIntensity = 0.0f; // Cường độ rung hiện tại
    private float shakeDecay = 25.0f;    // Tốc độ giảm rung theo thời gian (giảm nhanh để đỡ lag mắt)
    private Random random = new Random();

    @RegisterFunction
    @Override
    public void _ready() {
        // Đăng ký camera này với Global để mọi class khác gọi được
        Global.camera = this;

        // Cài đặt tỷ lệ zoom cho camera
        setZoom(cameraZoom);
    }

    @RegisterFunction
    @Override
    public void _process(double delta) {
        float fDelta = (float) delta;

        if (Global.player != null && Global.player.isInsideTree()) {
            Vector2 playerPos = Global.player.getGlobalPosition();
            Vector2 currentPos = getGlobalPosition();

            // 1. Tính toán vị trí di chuyển mượt mà (Lerp)
            Vector2 targetPos = currentPos.lerp(playerPos, lerpSpeed * fDelta);

            // 2. Cộng thêm độ lệch rung màn hình (Screen Shake)
            if (shakeIntensity > 0) {
                float offsetX = (random.nextFloat() * 2.0f - 1.0f) * shakeIntensity;
                float offsetY = (random.nextFloat() * 2.0f - 1.0f) * shakeIntensity;
                targetPos = targetPos.plus(new Vector2(offsetX, offsetY));

                // Giảm dần độ rung qua mỗi frame
                shakeIntensity -= shakeDecay * fDelta;
                if (shakeIntensity < 0) {
                    shakeIntensity = 0.0f;
                }
            }

            // Cập nhật tọa độ cho camera
            setGlobalPosition(targetPos);
        }
    }

    // Hàm gọi từ bất kỳ đâu (Ví dụ: khi nổ bom, khi nhận sát thương) để rung màn hình
    @RegisterFunction
    public void addShake(float intensity) {
        this.shakeIntensity = Math.max(this.shakeIntensity, intensity);
    }
}