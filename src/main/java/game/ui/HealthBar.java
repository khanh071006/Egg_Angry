package game.ui;

import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Control;
import godot.api.Label;
import godot.api.ProgressBar;
import godot.api.StyleBoxFlat;
import godot.core.Color; // SỬA: Dùng Color của Godot
import godot.core.StringName;

@RegisterClass
public class HealthBar extends Control {
    @Export
    @RegisterProperty
    public Color backColor = new Color(0.14f, 0.02f, 0.03f,1.0f); // Thêm 'f' cho số thực

    @Export
    @RegisterProperty
    public Color fillColor = new Color(0.92f, 0.45f, 0.28f,1.0f);

    private ProgressBar progressBar;
    private Label healthLabel;

    @RegisterFunction
    @Override
    public void _ready() {

        progressBar = (ProgressBar) getNode("ProgressBar");
        healthLabel = (Label) getNode("HealthAmount");

        if (progressBar != null) {
            // SỬA: Thêm tham số thứ 2 là "ProgressBar"
            StyleBoxFlat bgStyle = (StyleBoxFlat) progressBar.getThemeStylebox(
                    new StringName("bg"),
                    new StringName("ProgressBar")
            ).duplicate();

            bgStyle.setBgColor(backColor);
            progressBar.addThemeStyleboxOverride(new StringName("bg"), bgStyle);

            // SỬA: Thêm tham số thứ 2 là "ProgressBar"
            StyleBoxFlat fillStyle = (StyleBoxFlat) progressBar.getThemeStylebox(
                    new StringName("fill"),
                    new StringName("ProgressBar")
            ).duplicate();

            fillStyle.setBgColor(fillColor);
            progressBar.addThemeStyleboxOverride(new StringName("fill"), fillStyle);
        }
    }

    @RegisterFunction
    public void updateBar(float value, float health) {
        if (progressBar != null) {
            progressBar.setValue(value);
        }
        if (healthLabel != null) {
            // Ép kiểu float về int để mất số thập phân, sau đó biến thành String
            healthLabel.setText(String.valueOf((int) health));
        }
    }

    @RegisterFunction
    public void _on_health_component_on_health_changed(float current, float max){
        float progressHealth = current / max;

        updateBar(progressHealth,current);
    }
}