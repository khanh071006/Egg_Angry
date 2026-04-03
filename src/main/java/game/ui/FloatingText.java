package game.ui;

import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Label;
import godot.api.Node2D;
import godot.api.Tween;
import godot.core.Color;
import godot.core.StringName;
import godot.core.Vector2;
import godot.global.GD;


@RegisterClass
public class FloatingText extends Node2D {
    private Label valueLabel;

    @RegisterFunction
    public void setup(String value, Color color){
        valueLabel = (Label) getNode("ValueLabel");
        valueLabel.setText(value);

        setModulate(color);
        setScale(new Vector2(0,0));

        double randomRotation = Math.toRadians(GD.randfRange(-10f,10f));
        setRotation((float) randomRotation);

        // 3. Khởi tạo kích thước ngẫu nhiên (to nhỏ khác nhau)
        float randScale = (float) GD.randfRange(0.8f, 1.6f);
        Vector2 finalScale = new Vector2(randScale, randScale);

        //Tween

        // Cảnh 1: Bay lên và Phóng to (Chạy cùng lúc)
        Tween tween = createTween();
        tween.setParallel(true);
        tween.tweenProperty(this,"scale",finalScale,0.4f);
        tween.tweenProperty(this, "global_position", getGlobalPosition().plus(new Vector2(0,-15)), 0.4);

        // Cảnh 2: Đứng im 0.5s cho người chơi đọc
        tween.setParallel(false);
        tween.tweenInterval(0.5);

        // Cảnh 3: Mờ dần và Nhỏ lại (Chạy cùng lúc)
        tween.setParallel(true);
        tween.tweenProperty(this, "scale", new Vector2(0,0), 0.4);
        tween.tweenProperty(this, "modulate:a", 0.0, 0.4);

        // Cảnh 4: Xóa sổ khỏi RAM
        tween.setParallel(false);
        godot.core.Callable selfDestruct = godot.core.Callable.create(this, new StringName("queue_free"));
        tween.tweenCallback(selfDestruct);

    }
}
