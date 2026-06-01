package game.ui;

import game.resources.units.UnitStats;
import game.resources.items.weapons.ItemWeapon;
import game.autoloads.Global;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.*;
import godot.core.Callable;
import godot.core.VariantArray;

@RegisterClass
public class SelectionPanel extends Node {

    @Export
    @RegisterProperty
    public VariantArray<Resource> players = game.Helper.GodotHelper.createResourceArray();

    @Export
    @RegisterProperty
    public VariantArray<Resource> startWeapons = game.Helper.GodotHelper.createResourceArray();

    @Export
    @RegisterProperty
    public PackedScene selectionCardScene;

    private HBoxContainer playerContainer;
    private HBoxContainer weaponContainer;

    private TextureRect playerIcon;
    private Label playerName;
    private Label playerTitle;
    private RichTextLabel playerDescription;

    public SelectionPanel() {
        super();
    }

    @RegisterFunction
    @Override
    public void _ready() {
        playerContainer = (HBoxContainer) getNodeOrNull("%PlayerContainer");
        weaponContainer = (HBoxContainer) getNodeOrNull("%WeaponContainer");
        playerIcon = (TextureRect) getNodeOrNull("%PlayerIcon");
        playerName = (Label) getNodeOrNull("%PlayerName");
        playerTitle = (Label) getNodeOrNull("%PlayerTitle");
        playerDescription = (RichTextLabel) getNodeOrNull("%PlayerDescription");

        // Clear existing children
        if (playerContainer != null) {
            for (Node child : playerContainer.getChildren()) {
                child.queueFree();
            }
        }
        if (weaponContainer != null) {
            for (Node child : weaponContainer.getChildren()) {
                child.queueFree();
            }
        }

        if (selectionCardScene == null) {
            selectionCardScene = (PackedScene) godot.api.ResourceLoader
                    .load("res://scenes/ui/selection_panel/selection_card.tscn");
        }

        showPlayerInfo(false);
        loadPlayers();
    }

    @RegisterFunction
    public void showPlayerInfo(boolean visible) {
        if (playerIcon != null)
            playerIcon.setVisible(visible);
        if (playerName != null)
            playerName.setVisible(visible);
        if (playerTitle != null)
            playerTitle.setVisible(visible);
        if (playerDescription != null)
            playerDescription.setVisible(visible);
    }

    @RegisterFunction
    public void loadPlayers() {
        if (players == null || players.isEmpty()) {
            return;
        }

        if (selectionCardScene != null && playerContainer != null) {
            for (int i = 0; i < players.size(); i++) {
                Resource res = players.get(i);
                if (res instanceof UnitStats) {
                    UnitStats player = (UnitStats) res;
                    SelectionCard card = (SelectionCard) selectionCardScene.instantiate();
                    if (card != null) {
                        playerContainer.addChild(card);
                        card.setIconTexture(player.getIcon());

                        card.playerIndex = i;
                        card.onCardSelected.connect(
                                godot.core.Callable.create(this, new godot.core.StringName("on_player_selected_index")),
                                0);
                    }
                }
            }
        }
    }

    @RegisterFunction
    public void on_player_selected_index(int index) {
        if (players != null && index >= 0 && index < players.size()) {
            Resource res = players.get(index);
            if (res instanceof UnitStats) {
                onPlayerSelected((UnitStats) res);
            }
        }
    }

    @RegisterFunction
    public void onPlayerSelected(UnitStats player) {
        Global.mainPlayerSelected = player;
        showPlayerInfo(true);

        if (playerIcon != null)
            playerIcon.setTexture(player.getIcon());
        if (playerName != null)
            playerName.setText(player.getUnitName());

        if (playerDescription != null) {
            float luckVal = 0.0f;
            if (player instanceof game.resources.units.PlayerStats) {
                luckVal = ((game.resources.units.PlayerStats) player).getLuck();
            }

            String bbcode = "[code]\n\n" +
                    "Health: [color=green]" + player.getHealth() + "[/color]\n\n" +
                    "Damage: [color=green]" + player.getDamage() + "[/color]\n\n" +
                    "Speed: [color=green]" + player.getSpeed() + "[/color]\n\n" +
                    "Luck: [color=green]" + luckVal + "[/color]\n\n" +
                    "Block Chance: [color=green]" + player.getBlockchance() + "%[/color]\n\n" +
                    "[/code]";
            playerDescription.setText(bbcode);
        }
    }
}
