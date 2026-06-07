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
import godot.core.Signal0;
import godot.core.VariantArray;
import godot.core.Signal1;
import godot.annotation.RegisterSignal;

@RegisterClass
public class SelectionPanel extends Panel {

    @Export
    @RegisterProperty
    public VariantArray<Resource> players = game.helper.GodotHelper.createResourceArray();

    @Export
    @RegisterProperty
    public VariantArray<Resource> startWeapons = game.helper.GodotHelper.createResourceArray();

    @Export
    @RegisterProperty
    public PackedScene selectionCardScene;

    private HBoxContainer playerContainer;
    private HBoxContainer weaponContainer;

    private TextureRect playerIcon;
    private Label playerName;
    private Label playerTitle;
    private RichTextLabel playerDescription;

    private TextureRect weaponIconPanel;
    private Label weaponName;
    private Label weaponTitle;
    private RichTextLabel weaponDescription;

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

        weaponIconPanel = (TextureRect) getNodeOrNull("%WeaponIconPanel");
        weaponName = (Label) getNodeOrNull("%WeaponName");
        weaponTitle = (Label) getNodeOrNull("%WeaponTitle");
        weaponDescription = (RichTextLabel) getNodeOrNull("%WeaponDescription");

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
        showWeaponInfo(false);
        loadPlayers();
        loadWeapons();
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
                    "Health: [color=green]" + player.getHealth() + "[/color]\n" +
                    "Damage: [color=green]" + player.getDamage() + "[/color]\n" +
                    "Speed: [color=green]" + player.getSpeed() + "[/color]\n" +
                    "Luck: [color=green]" + luckVal + "[/color]\n" +
                    "Block Chance: [color=green]" + player.getBlockchance() + "%[/color]\n\n" +
                    "[/code]";
            playerDescription.setText(bbcode);
        }
    }

    @RegisterFunction
    public void showWeaponInfo(boolean visible) {
        if (weaponIconPanel != null)
            weaponIconPanel.setVisible(visible);
        if (weaponName != null)
            weaponName.setVisible(visible);
        if (weaponTitle != null)
            weaponTitle.setVisible(visible);
        if (weaponDescription != null)
            weaponDescription.setVisible(visible);
    }

    @RegisterFunction
    public void loadWeapons() {
        if (startWeapons == null || startWeapons.isEmpty()) {
            return;
        }

        if (selectionCardScene != null && weaponContainer != null) {
            for (int i = 0; i < startWeapons.size(); i++) {
                Resource res = startWeapons.get(i);
                if (res instanceof ItemWeapon) {
                    ItemWeapon weapon = (ItemWeapon) res;
                    SelectionCard card = (SelectionCard) selectionCardScene.instantiate();
                    if (card != null) {
                        weaponContainer.addChild(card);
                        card.setIconTexture(weapon.itemIcon);
                        
                        card.playerIndex = i; // Reuse playerIndex for weapon index
                        card.onCardSelected.connect(
                                godot.core.Callable.create(this, new godot.core.StringName("on_weapon_selected_index")),
                                0);
                    }
                }
            }
        }
    }

    @RegisterFunction
    public void on_weapon_selected_index(int index) {
        if (startWeapons != null && index >= 0 && index < startWeapons.size()) {
            Resource res = startWeapons.get(index);
            if (res instanceof ItemWeapon) {
                onWeaponSelected((ItemWeapon) res);
            }
        }
    }

    @RegisterFunction
    public void onWeaponSelected(ItemWeapon weapon) {
        Global.mainWeaponSelected = weapon;
        showWeaponInfo(true);
        
        if (weaponIconPanel != null)
            weaponIconPanel.setTexture(weapon.itemIcon);
        if (weaponName != null)
            weaponName.setText(weapon.itemName);
        if (weaponDescription != null) {
            weaponDescription.setText(weapon.getDescription());
        }
    }

    @RegisterSignal
    public Signal0 selectionCompleted = Signal0.create(this, "selection_completed");

    @RegisterFunction
    public void _on_continue_button_pressed() {
        if (Global.mainPlayerSelected == null || Global.mainWeaponSelected == null) {
            return;
        }

        selectionCompleted.emit();
        this.hide();
    }
}
