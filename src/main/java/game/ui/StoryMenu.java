package game.ui;

import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.api.Control;
import godot.api.Button;
import godot.api.RichTextLabel;
import godot.core.Vector2;

@RegisterClass
public class StoryMenu extends Control {

    private Button backButton;
    private RichTextLabel storyLabel;

    private String[] paragraphs = new String[] {
        "In the year 30XX, the universe is ruled by the [color=#9c5600][b]Culinary Alliance[/b][/color].",
        "A cargo ship carrying the royal eggs was suddenly ambushed by the [color=#b82300][b]Chitin Empire[/b][/color] - alien insectoids craving yolk and proteins to evolve.",
        "The ship crashed onto [color=#b85300][b]Fry-Top 9[/b][/color], a volcanic planet boiling like a massive frying pan.",
        "All eggs perished, scrambled upon impact. [color=#a31500][b]Except one.[/b][/color]",
        "Equipped with an experimental bio-suit allowing him to wield 6 weapons at once, [color=#9c5600][b]ANGRY EGG[/b][/color] rises from the ash.",
        "He is angry. Very angry.",
        "And he will [color=#b82300][b]not[/b][/color] become their breakfast!"
    };

    private int currentParagraphIndex = 0;
    private String fullTextAccumulated = "";
    private int totalCharacters = 0;
    private int visibleCharactersCount = 0;
    private double charTimer = 0.0;
    private double pauseTimer = 0.0;
    
    private boolean isTyping = false;
    private boolean isWaiting = false;

    @RegisterFunction
    @Override
    public void _ready() {
        backButton = (Button) getNode("PlotImagePanel/PlotImage/BackButton");
        storyLabel = (RichTextLabel) getNode("PlotImagePanel/PlotImage/Panel/MarginContainer/StoryLabel");

        // Setup back button hover scaling
        if (backButton != null) {
            backButton.setPivotOffset(backButton.getSize().times(0.5f));
        }

        if (storyLabel != null) {
            storyLabel.setText("");
            storyLabel.setVisibleCharacters(0);
            setupNextParagraph();
        }
    }

    private void setupNextParagraph() {
        if (storyLabel == null) return;
        
        if (currentParagraphIndex < paragraphs.length) {
            if (currentParagraphIndex > 0) {
                fullTextAccumulated += "\n\n";
            }
            fullTextAccumulated += paragraphs[currentParagraphIndex];
            storyLabel.setText(fullTextAccumulated);
            
            // Clean text of BBCode tags to calculate actual rendered character count
            String cleanText = fullTextAccumulated.replaceAll("\\[.*?\\]", "");
            totalCharacters = cleanText.length();
            
            isTyping = true;
            isWaiting = false;
        } else {
            isTyping = false;
            isWaiting = false;
        }
    }

    @RegisterFunction
    @Override
    public void _process(double delta) {
        if (isTyping) {
            charTimer += delta;
            // 0.025 seconds per character for a professional, crisp typewriter speed
            double charSpeed = 0.025;
            if (charTimer >= charSpeed) {
                int charsToAdd = (int) (charTimer / charSpeed);
                charTimer -= charsToAdd * charSpeed;
                
                visibleCharactersCount += charsToAdd;
                if (visibleCharactersCount >= totalCharacters) {
                    visibleCharactersCount = totalCharacters;
                    isTyping = false;
                    isWaiting = true;
                    // Pause for 0.6 seconds between paragraphs for faster pacing
                    pauseTimer = 0.6;
                }
                
                if (storyLabel != null) {
                    storyLabel.setVisibleCharacters(visibleCharactersCount);
                }
            }
        } else if (isWaiting) {
            pauseTimer -= delta;
            if (pauseTimer <= 0.0) {
                currentParagraphIndex++;
                setupNextParagraph();
            }
        }
    }

    @RegisterFunction
    public void _on_back_pressed() {
        getTree().changeSceneToFile("res://scenes/ui/MainMenu.tscn");
    }

    private void animateButtonScale(Button button, Vector2 targetScale) {
        if (button == null) return;
        button.setPivotOffset(button.getSize().times(0.5f));
        
        godot.api.Tween tween = createTween();
        if (tween != null) {
            tween.tweenProperty(button, "scale", targetScale, 0.1f);
        }
    }

    @RegisterFunction
    public void _on_back_mouse_entered() {
        animateButtonScale(backButton, new Vector2(1.1f, 1.1f));
    }

    @RegisterFunction
    public void _on_back_mouse_exited() {
        animateButtonScale(backButton, new Vector2(1.0f, 1.0f));
    }
}
