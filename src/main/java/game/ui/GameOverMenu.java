package game.ui;

import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.api.Control;
import godot.api.Button;
import godot.api.Tween;
import godot.core.Vector2;

@RegisterClass
public class GameOverMenu extends Control {

    private Button retryButton;
    private Button quitButton;

    @RegisterFunction
    @Override
    public void _ready() {
        retryButton = (Button) getNode("VBoxContainer/RetryButton");
        quitButton = (Button) getNode("VBoxContainer/QuitButton");

        setupButtonPivot(retryButton);
        setupButtonPivot(quitButton);
    }

    private void setupButtonPivot(Button button) {
        if (button != null) {
            button.setPivotOffset(button.getSize().times(0.5f));
        }
    }

    @RegisterFunction
    public void _on_retry_pressed() {
        game.autoloads.Global.instance.startBgm();
        getTree().changeSceneToFile("res://scenes/arena/Arena.tscn");
    }

    @RegisterFunction
    public void _on_quit_pressed() {
        getTree().changeSceneToFile("res://scenes/ui/MainMenu.tscn");
    }

    private void animateButtonScale(Button button, Vector2 targetScale) {
        if (button == null) return;
        button.setPivotOffset(button.getSize().times(0.5f));
        Tween tween = createTween();
        if (tween != null) {
            tween.tweenProperty(button, "scale", targetScale, 0.1f);
        }
    }

    @RegisterFunction
    public void _on_retry_mouse_entered() {
        animateButtonScale(retryButton, new Vector2(1.1f, 1.1f));
    }

    @RegisterFunction
    public void _on_retry_mouse_exited() {
        animateButtonScale(retryButton, new Vector2(1.0f, 1.0f));
    }

    @RegisterFunction
    public void _on_quit_mouse_entered() {
        animateButtonScale(quitButton, new Vector2(1.1f, 1.1f));
    }

    @RegisterFunction
    public void _on_quit_mouse_exited() {
        animateButtonScale(quitButton, new Vector2(1.0f, 1.0f));
    }
}
