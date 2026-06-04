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
    private Button nextButton;
    private Button prevButton;
    private Button playerIntroNextButton;
    private Button enemyIntroPrevButton;
    private Button enemyIntroNextButton;
    private Button waveIntroPrevButton;
    private Button playButton;
    private RichTextLabel storyLabel;
    private RichTextLabel playerIntroLabel;
    private RichTextLabel enemyIntroLabel;
    private RichTextLabel waveIntroLabel;
    private Control plotImagePanel;
    private Control playerIntroPanel;
    private Control enemyIntroPanel;
    private Control waveIntroPanel;

    private String[] paragraphs = new String[] {
        "In the year 30XX, the universe is ruled by the [color=#9c5600][b]Culinary Alliance[/b][/color].",
        "A cargo ship carrying the royal eggs was suddenly ambushed by the [color=#b82300][b]Chitin Empire[/b][/color] - alien insectoids craving yolk and proteins to evolve.",
        "The ship crashed onto [color=#b85300][b]Fry-Top 9[/b][/color], a volcanic planet boiling like a massive frying pan.",
        "All eggs perished, scrambled upon impact. [color=#a31500][b]Except one.[/b][/color]",
        "Equipped with an experimental bio-suit allowing him to wield 6 weapons at once, [color=#9c5600][b]ANGRY EGG[/b][/color] rises from the ash.",
        "He is angry. Very angry.",
        "And he will [color=#b82300][b]not[/b][/color] become their breakfast!"
    };

    private String[] playerIntroParagraphs = new String[] {
        "This is [color=#ffd000][b]ANGRY EGG[/b][/color].",
        "The sole survivor of the royal shipment, equipped with a state-of-the-art combat suit.",
        "Engineered with a reinforced carbon-fiber shell and an experimental neural weapon link.",
        "This advanced technology allows him to wield [color=#ffd000][b]up to 6 weapons[/b][/color] at the same time.",
        "Fuelled by pure rage, he will hunt down the [color=#ff2200][b]Chitin Empire[/b][/color] to avenge his fallen kind.",
        "The volcanic sands of [color=#b85300][b]Fry-Top 9[/b][/color] will run red with alien blood.",
        "Prepare to fight. [color=#ff7c00][b]They will not make an omelette out of him![/b][/color]"
    };

    private String[] enemyIntroParagraphs = new String[] {
        "Behold the [color=#ff2200][b]CHITIN EMPIRE[/b][/color].",
        "A ravenous swarm of alien insectoids that consumes entire worlds to fuel their evolution.",
        "They have detected the royal yolk on [color=#b85300][b]Fry-Top 9[/b][/color] and are sending their vanguard.",
        "From fast-moving crawlers to massive armored bugs shooting deadly laser arrays.",
        "They crave the protein inside [color=#ffd000]ANGRY EGG[/color] and will stop at nothing to scramble him.",
        "Break their shells, dodge their stingers, and show them who is at the top of the food chain!",
        "Defend your yolk. [color=#ff2200][b]Let the insect hunt begin![/b][/color]"
    };

    private String[] waveIntroParagraphs = new String[] {
        "Survive the [color=#ffd000][b]Boiling Waves[/b][/color] of Fry-Top 9.",
        "The insect vanguard attacks in structured, relentless cycles.",
        "Each wave is hotter and faster, pushing your combat suit to its limits.",
        "Defeating waves will allow you to choose [color=#ffd000]powerful weapons and upgrades[/color] to bolster your arsenal.",
        "Adapt your strategy: build a wall of turrets, slice with blades, or blast them with lasers.",
        "How long can you stand the heat before you get cooked?",
        "Brace yourself. [color=#ff2200][b]The first wave is arriving![/b][/color]"
    };

    private int currentPhase = 1; // 1 = Story, 2 = Player, 3 = Enemy, 4 = Wave
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
        backButton = (Button) getNode("ClipContainer/PlotImagePanel/PlotImage/BackButton");
        nextButton = (Button) getNode("ClipContainer/PlotImagePanel/PlotImage/NextButton");
        storyLabel = (RichTextLabel) getNode("ClipContainer/PlotImagePanel/PlotImage/Panel/MarginContainer/StoryLabel");

        plotImagePanel = (Control) getNode("ClipContainer/PlotImagePanel");
        playerIntroPanel = (Control) getNode("ClipContainer/PlayerIntroPanel");
        playerIntroLabel = (RichTextLabel) getNode("ClipContainer/PlayerIntroPanel/PlayerIntroImage/Panel/MarginContainer/PlayerIntroLabel");
        prevButton = (Button) getNode("ClipContainer/PlayerIntroPanel/PlayerIntroImage/PrevButton");
        playerIntroNextButton = (Button) getNode("ClipContainer/PlayerIntroPanel/PlayerIntroImage/NextButton");

        enemyIntroPanel = (Control) getNode("ClipContainer/EnemyIntroPanel");
        enemyIntroLabel = (RichTextLabel) getNode("ClipContainer/EnemyIntroPanel/EnemyIntroImage/Panel/MarginContainer/EnemyIntroLabel");
        enemyIntroPrevButton = (Button) getNode("ClipContainer/EnemyIntroPanel/EnemyIntroImage/PrevButton");
        enemyIntroNextButton = (Button) getNode("ClipContainer/EnemyIntroPanel/EnemyIntroImage/NextButton");

        waveIntroPanel = (Control) getNode("ClipContainer/WaveIntroPanel");
        waveIntroLabel = (RichTextLabel) getNode("ClipContainer/WaveIntroPanel/WaveIntroImage/Panel/MarginContainer/WaveIntroLabel");
        waveIntroPrevButton = (Button) getNode("ClipContainer/WaveIntroPanel/WaveIntroImage/PrevButton");
        playButton = (Button) getNode("ClipContainer/WaveIntroPanel/WaveIntroImage/PlayButton");

        // Setup pivots for hover scaling animations
        setupButtonPivot(backButton);
        setupButtonPivot(nextButton);
        setupButtonPivot(prevButton);
        setupButtonPivot(playerIntroNextButton);
        setupButtonPivot(enemyIntroPrevButton);
        setupButtonPivot(enemyIntroNextButton);
        setupButtonPivot(waveIntroPrevButton);
        setupButtonPivot(playButton);

        // Position panels correctly at start
        if (plotImagePanel != null) {
            plotImagePanel.setPosition(new Vector2(0f, 0f));
        }
        if (playerIntroPanel != null) {
            playerIntroPanel.setPosition(new Vector2(938f, 0f));
        }
        if (enemyIntroPanel != null) {
            enemyIntroPanel.setPosition(new Vector2(1876f, 0f));
        }
        if (waveIntroPanel != null) {
            waveIntroPanel.setPosition(new Vector2(2814f, 0f));
        }

        if (nextButton != null) nextButton.setVisible(false);
        if (playerIntroNextButton != null) playerIntroNextButton.setVisible(false);
        if (enemyIntroNextButton != null) enemyIntroNextButton.setVisible(false);
        if (playButton != null) playButton.setVisible(false);

        if (storyLabel != null) {
            storyLabel.setText("");
            storyLabel.setVisibleCharacters(0);
        }
        if (playerIntroLabel != null) {
            playerIntroLabel.setText("");
            playerIntroLabel.setVisibleCharacters(0);
        }
        if (enemyIntroLabel != null) {
            enemyIntroLabel.setText("");
            enemyIntroLabel.setVisibleCharacters(0);
        }
        if (waveIntroLabel != null) {
            waveIntroLabel.setText("");
            waveIntroLabel.setVisibleCharacters(0);
        }

        setupNextParagraph();
    }

    private void setupButtonPivot(Button button) {
        if (button != null) {
            button.setPivotOffset(button.getSize().times(0.5f));
        }
    }

    private void setupNextParagraph() {
        if (currentPhase == 1) {
            if (storyLabel == null) return;
            if (currentParagraphIndex < paragraphs.length) {
                if (currentParagraphIndex > 0) {
                    fullTextAccumulated += "\n\n";
                }
                fullTextAccumulated += paragraphs[currentParagraphIndex];
                storyLabel.setText(fullTextAccumulated);
                
                String cleanText = fullTextAccumulated.replaceAll("\\[.*?\\]", "");
                totalCharacters = cleanText.length();
                isTyping = true;
                isWaiting = false;
            } else {
                isTyping = false;
                isWaiting = false;
                if (nextButton != null) {
                    nextButton.setVisible(true);
                    animateButtonScale(nextButton, new Vector2(1.0f, 1.0f));
                }
            }
        } else if (currentPhase == 2) {
            if (playerIntroLabel == null) return;
            if (currentParagraphIndex < playerIntroParagraphs.length) {
                if (currentParagraphIndex > 0) {
                    fullTextAccumulated += "\n\n";
                }
                fullTextAccumulated += playerIntroParagraphs[currentParagraphIndex];
                playerIntroLabel.setText(fullTextAccumulated);
                
                String cleanText = fullTextAccumulated.replaceAll("\\[.*?\\]", "");
                totalCharacters = cleanText.length();
                isTyping = true;
                isWaiting = false;
            } else {
                isTyping = false;
                isWaiting = false;
                if (playerIntroNextButton != null) {
                    playerIntroNextButton.setVisible(true);
                    animateButtonScale(playerIntroNextButton, new Vector2(1.0f, 1.0f));
                }
            }
        } else if (currentPhase == 3) {
            if (enemyIntroLabel == null) return;
            if (currentParagraphIndex < enemyIntroParagraphs.length) {
                if (currentParagraphIndex > 0) {
                    fullTextAccumulated += "\n\n";
                }
                fullTextAccumulated += enemyIntroParagraphs[currentParagraphIndex];
                enemyIntroLabel.setText(fullTextAccumulated);
                
                String cleanText = fullTextAccumulated.replaceAll("\\[.*?\\]", "");
                totalCharacters = cleanText.length();
                isTyping = true;
                isWaiting = false;
            } else {
                isTyping = false;
                isWaiting = false;
                if (enemyIntroNextButton != null) {
                    enemyIntroNextButton.setVisible(true);
                    animateButtonScale(enemyIntroNextButton, new Vector2(1.0f, 1.0f));
                }
            }
        } else if (currentPhase == 4) {
            if (waveIntroLabel == null) return;
            if (currentParagraphIndex < waveIntroParagraphs.length) {
                if (currentParagraphIndex > 0) {
                    fullTextAccumulated += "\n\n";
                }
                fullTextAccumulated += waveIntroParagraphs[currentParagraphIndex];
                waveIntroLabel.setText(fullTextAccumulated);
                
                String cleanText = fullTextAccumulated.replaceAll("\\[.*?\\]", "");
                totalCharacters = cleanText.length();
                isTyping = true;
                isWaiting = false;
            } else {
                isTyping = false;
                isWaiting = false;
                if (playButton != null) {
                    playButton.setVisible(true);
                    animateButtonScale(playButton, new Vector2(1.0f, 1.0f));
                }
            }
        }
    }

    @RegisterFunction
    @Override
    public void _process(double delta) {
        if (isTyping) {
            charTimer += delta;
            double charSpeed = 0.025;
            if (charTimer >= charSpeed) {
                int charsToAdd = (int) (charTimer / charSpeed);
                charTimer -= charsToAdd * charSpeed;
                
                visibleCharactersCount += charsToAdd;
                if (visibleCharactersCount >= totalCharacters) {
                    visibleCharactersCount = totalCharacters;
                    isTyping = false;
                    isWaiting = true;
                    pauseTimer = 0.6;
                }
                
                RichTextLabel activeLabel;
                if (currentPhase == 1) {
                    activeLabel = storyLabel;
                } else if (currentPhase == 2) {
                    activeLabel = playerIntroLabel;
                } else if (currentPhase == 3) {
                    activeLabel = enemyIntroLabel;
                } else {
                    activeLabel = waveIntroLabel;
                }
                
                if (activeLabel != null) {
                    activeLabel.setVisibleCharacters(visibleCharactersCount);
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

    private void transitionToPhase(int targetPhase, boolean isBackward) {
        if (plotImagePanel == null || playerIntroPanel == null || enemyIntroPanel == null || waveIntroPanel == null) return;

        if (nextButton != null) nextButton.setVisible(false);
        if (playerIntroNextButton != null) playerIntroNextButton.setVisible(false);
        if (enemyIntroNextButton != null) enemyIntroNextButton.setVisible(false);
        if (playButton != null) playButton.setVisible(false);

        isTyping = false;
        isWaiting = false;

        currentPhase = targetPhase;

        godot.api.Tween tween = createTween();
        if (tween != null) {
            tween.setParallel(true);
            float targetPlotX = (0 - (currentPhase - 1)) * 938f;
            float targetPlayerX = (1 - (currentPhase - 1)) * 938f;
            float targetEnemyX = (2 - (currentPhase - 1)) * 938f;
            float targetWaveX = (3 - (currentPhase - 1)) * 938f;

            tween.tweenProperty(plotImagePanel, "position", new Vector2(targetPlotX, 0f), 0.4f);
            tween.tweenProperty(playerIntroPanel, "position", new Vector2(targetPlayerX, 0f), 0.4f);
            tween.tweenProperty(enemyIntroPanel, "position", new Vector2(targetEnemyX, 0f), 0.4f);
            tween.tweenProperty(waveIntroPanel, "position", new Vector2(targetWaveX, 0f), 0.4f);
        }

        currentParagraphIndex = 0;
        fullTextAccumulated = "";
        totalCharacters = 0;
        visibleCharactersCount = 0;
        charTimer = 0.0;
        pauseTimer = 0.0;

        if (isBackward) {
            if (currentPhase == 1) {
                fullTextAccumulated = "";
                for (int i = 0; i < paragraphs.length; i++) {
                    if (i > 0) fullTextAccumulated += "\n\n";
                    fullTextAccumulated += paragraphs[i];
                }
                if (storyLabel != null) {
                    storyLabel.setText(fullTextAccumulated);
                    storyLabel.setVisibleCharacters(-1);
                }
                if (nextButton != null) {
                    nextButton.setVisible(true);
                    animateButtonScale(nextButton, new Vector2(1.0f, 1.0f));
                }
            } else if (currentPhase == 2) {
                fullTextAccumulated = "";
                for (int i = 0; i < playerIntroParagraphs.length; i++) {
                    if (i > 0) fullTextAccumulated += "\n\n";
                    fullTextAccumulated += playerIntroParagraphs[i];
                }
                if (playerIntroLabel != null) {
                    playerIntroLabel.setText(fullTextAccumulated);
                    playerIntroLabel.setVisibleCharacters(-1);
                }
                if (playerIntroNextButton != null) {
                    playerIntroNextButton.setVisible(true);
                    animateButtonScale(playerIntroNextButton, new Vector2(1.0f, 1.0f));
                }
            } else if (currentPhase == 3) {
                fullTextAccumulated = "";
                for (int i = 0; i < enemyIntroParagraphs.length; i++) {
                    if (i > 0) fullTextAccumulated += "\n\n";
                    fullTextAccumulated += enemyIntroParagraphs[i];
                }
                if (enemyIntroLabel != null) {
                    enemyIntroLabel.setText(fullTextAccumulated);
                    enemyIntroLabel.setVisibleCharacters(-1);
                }
                if (enemyIntroNextButton != null) {
                    enemyIntroNextButton.setVisible(true);
                    animateButtonScale(enemyIntroNextButton, new Vector2(1.0f, 1.0f));
                }
            }
        } else {
            if (currentPhase == 2) {
                if (playerIntroLabel != null) {
                    playerIntroLabel.setText("");
                    playerIntroLabel.setVisibleCharacters(0);
                }
            } else if (currentPhase == 3) {
                if (enemyIntroLabel != null) {
                    enemyIntroLabel.setText("");
                    enemyIntroLabel.setVisibleCharacters(0);
                }
            } else if (currentPhase == 4) {
                if (waveIntroLabel != null) {
                    waveIntroLabel.setText("");
                    waveIntroLabel.setVisibleCharacters(0);
                }
            }
            setupNextParagraph();
        }
    }

    @RegisterFunction
    public void _on_back_pressed() {
        getTree().changeSceneToFile("res://scenes/ui/MainMenu.tscn");
    }

    @RegisterFunction
    public void _on_next_pressed() {
        transitionToPhase(2, false);
    }

    @RegisterFunction
    public void _on_prev_pressed() {
        transitionToPhase(1, true);
    }

    @RegisterFunction
    public void _on_player_next_pressed() {
        transitionToPhase(3, false);
    }

    @RegisterFunction
    public void _on_enemy_prev_pressed() {
        transitionToPhase(2, true);
    }

    @RegisterFunction
    public void _on_enemy_next_pressed() {
        transitionToPhase(4, false);
    }

    @RegisterFunction
    public void _on_wave_prev_pressed() {
        transitionToPhase(3, true);
    }

    @RegisterFunction
    public void _on_play_pressed() {
        getTree().changeSceneToFile("res://scenes/arena/Arena_Spotlight.tscn");
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

    @RegisterFunction
    public void _on_next_mouse_entered() {
        animateButtonScale(nextButton, new Vector2(1.1f, 1.1f));
    }

    @RegisterFunction
    public void _on_next_mouse_exited() {
        animateButtonScale(nextButton, new Vector2(1.0f, 1.0f));
    }

    @RegisterFunction
    public void _on_prev_mouse_entered() {
        animateButtonScale(prevButton, new Vector2(1.1f, 1.1f));
    }

    @RegisterFunction
    public void _on_prev_mouse_exited() {
        animateButtonScale(prevButton, new Vector2(1.0f, 1.0f));
    }

    @RegisterFunction
    public void _on_player_next_mouse_entered() {
        animateButtonScale(playerIntroNextButton, new Vector2(1.1f, 1.1f));
    }

    @RegisterFunction
    public void _on_player_next_mouse_exited() {
        animateButtonScale(playerIntroNextButton, new Vector2(1.0f, 1.0f));
    }

    @RegisterFunction
    public void _on_enemy_prev_mouse_entered() {
        animateButtonScale(enemyIntroPrevButton, new Vector2(1.1f, 1.1f));
    }

    @RegisterFunction
    public void _on_enemy_prev_mouse_exited() {
        animateButtonScale(enemyIntroPrevButton, new Vector2(1.0f, 1.0f));
    }

    @RegisterFunction
    public void _on_enemy_next_mouse_entered() {
        animateButtonScale(enemyIntroNextButton, new Vector2(1.1f, 1.1f));
    }

    @RegisterFunction
    public void _on_enemy_next_mouse_exited() {
        animateButtonScale(enemyIntroNextButton, new Vector2(1.0f, 1.0f));
    }

    @RegisterFunction
    public void _on_wave_prev_mouse_entered() {
        animateButtonScale(waveIntroPrevButton, new Vector2(1.1f, 1.1f));
    }

    @RegisterFunction
    public void _on_wave_prev_mouse_exited() {
        animateButtonScale(waveIntroPrevButton, new Vector2(1.0f, 1.0f));
    }

    @RegisterFunction
    public void _on_play_mouse_entered() {
        animateButtonScale(playButton, new Vector2(1.1f, 1.1f));
    }

    @RegisterFunction
    public void _on_play_mouse_exited() {
        animateButtonScale(playButton, new Vector2(1.0f, 1.0f));
    }
}
