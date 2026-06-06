package game.ui;

import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.api.Control;
import godot.api.Button;

@RegisterClass
public class TutorialMenu extends Control {

    private Button backButton;

    @RegisterFunction
    @Override
    public void _ready() {
        backButton = (Button) getNode("BackButton");
    }

    @RegisterFunction
    public void _on_back_pressed() {
        getTree().changeSceneToFile("res://scenes/ui/MainMenu.tscn");
    }
}
