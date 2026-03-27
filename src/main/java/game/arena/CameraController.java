package game.arena;

import game.autoloads.Global;
import godot.api.Camera2D;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.core.Vector2;

@RegisterClass
public class CameraController extends Camera2D {

    @RegisterFunction
    @Override
    public void _process(double delta) {
        // Hỏi tổng đài xem Player có tồn tại không?
        if (Global.player != null) {
            // Có thì lấy tọa độ của Player đắp vào tọa độ của Camera
            Vector2 playerPos = Global.player.getGlobalPosition();
            setGlobalPosition(playerPos);
        }
    }
}