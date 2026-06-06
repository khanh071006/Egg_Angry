package game.ui;

import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.api.Control;
import godot.api.Button;
import godot.api.Panel;
import godot.api.Tween;
import godot.core.StringName;
import godot.core.Vector2;

@RegisterClass
public class MainMenu extends Control {

    private Button battleButton;
    private Button tutorialButton;
    private Button optionButton;
    private Button creditsButton;
    private Button quitButton;
    
    private Panel optionPanel;
    private Button closeOptionButton;
    
    private Panel creditsPanel;
    private Button closeCreditsButton;

    @RegisterFunction
    @Override
    public void _ready() {
        battleButton = (Button) getNode("RightVBox/BattleButton");
        tutorialButton = (Button) getNode("RightVBox/TutorialButton");
        optionButton = (Button) getNode("RightVBox/OptionButton");
        
        creditsButton = (Button) getNode("BottomRightHBox/CreditsButton");
        quitButton = (Button) getNode("BottomRightHBox/QuitButton");
        
        optionPanel = (Panel) getNode("OptionPanel");
        closeOptionButton = (Button) getNode("OptionPanel/CloseOptionButton");
        
        creditsPanel = (Panel) getNode("CreditsPanel");
        closeCreditsButton = (Button) getNode("CreditsPanel/CloseCreditsButton");

        // Set pivot points for buttons to scale from center
        setupButtonPivot(battleButton);
        setupButtonPivot(tutorialButton);
        setupButtonPivot(optionButton);
        setupButtonPivot(creditsButton);
        setupButtonPivot(quitButton);
    }

    private void setupButtonPivot(Button button) {
        if (button != null) {
            button.setPivotOffset(button.getSize().times(0.5f));
        }
    }

    @RegisterFunction
    public void _on_battle_pressed() {
        getTree().changeSceneToFile("res://scenes/arena/Arena.tscn");
    }

    @RegisterFunction
    public void _on_tutorial_pressed() {
        getTree().changeSceneToFile("res://scenes/ui/TutorialMenu.tscn");
    }

    @RegisterFunction
    public void _on_option_pressed() {
        if (optionPanel != null) {
            optionPanel.setVisible(true);
        }
        if (creditsPanel != null) {
            creditsPanel.setVisible(false);
        }
    }

    @RegisterFunction
    public void _on_close_option_pressed() {
        if (optionPanel != null) {
            optionPanel.setVisible(false);
        }
    }

    @RegisterFunction
    public void _on_credits_pressed() {
        getTree().changeSceneToFile("res://scenes/ui/StoryScene.tscn");
    }

    @RegisterFunction
    public void _on_quit_pressed() {
        getTree().quit();
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
    public void _on_battle_mouse_entered() {
        animateButtonScale(battleButton, new Vector2(1.1f, 1.1f));
    }

    @RegisterFunction
    public void _on_battle_mouse_exited() {
        animateButtonScale(battleButton, new Vector2(1.0f, 1.0f));
    }

    @RegisterFunction
    public void _on_tutorial_mouse_entered() {
        animateButtonScale(tutorialButton, new Vector2(1.1f, 1.1f));
    }

    @RegisterFunction
    public void _on_tutorial_mouse_exited() {
        animateButtonScale(tutorialButton, new Vector2(1.0f, 1.0f));
    }

    @RegisterFunction
    public void _on_option_mouse_entered() {
        animateButtonScale(optionButton, new Vector2(1.1f, 1.1f));
    }

    @RegisterFunction
    public void _on_option_mouse_exited() {
        animateButtonScale(optionButton, new Vector2(1.0f, 1.0f));
    }

    @RegisterFunction
    public void _on_credits_mouse_entered() {
        animateButtonScale(creditsButton, new Vector2(1.1f, 1.1f));
    }

    @RegisterFunction
    public void _on_credits_mouse_exited() {
        animateButtonScale(creditsButton, new Vector2(1.0f, 1.0f));
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
