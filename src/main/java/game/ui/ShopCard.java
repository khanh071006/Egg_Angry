package game.ui;

import game.autoloads.Global;
import game.resources.items.ItemBase;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.annotation.RegisterSignal;
import godot.api.Label;
import godot.api.Panel;
import godot.api.RichTextLabel;
import godot.api.TextureRect;
import godot.core.Signal1;

@RegisterClass
public class ShopCard extends Panel {

    @Export
    @RegisterProperty
    public ItemBase shopItem;

    @RegisterSignal
    public Signal1<ItemBase> onItemPurchased = Signal1.create(this, "onItemPurchased");

    private TextureRect itemIcon;
    private Label itemName;
    private Label itemType;
    private RichTextLabel itemDescription;
    private Label coinsLabel;
    private godot.api.Button buyButton;

    @RegisterFunction
    @Override
    public void _ready() {
        itemIcon = (TextureRect) getNodeOrNull("%ItemIcon");
        itemName = (Label) getNodeOrNull("%ItemName");
        itemType = (Label) getNodeOrNull("%ItemType");
        itemDescription = (RichTextLabel) getNodeOrNull("%ItemDescription");
        coinsLabel = (Label) getNodeOrNull("%CoinsLabel");
        buyButton = (godot.api.Button) getNodeOrNull("MarginContainer/Control/BuyButton");
        
        setProcess(true);
    }

    @RegisterFunction
    public void setShopItem(ItemBase value) {
        this.shopItem = value;
        if (value == null) return;
        
        if (itemIcon != null && value.itemIcon != null) {
            itemIcon.setTexture(value.itemIcon);
        }
        if (itemName != null) {
            itemName.setText(value.itemName);
        }
        if (itemType != null && value.itemType != null) {
            itemType.setText(value.itemType.name());
        }
        if (itemDescription != null) {
            itemDescription.setText(value.getDescription());
        }
        if (coinsLabel != null) {
            coinsLabel.setText(String.valueOf(value.itemCost));
            // Tạo bản sao của LabelSettings để đổi màu không bị dính sang các thẻ khác
            if (coinsLabel.getLabelSettings() != null) {
                godot.api.LabelSettings newSettings = (godot.api.LabelSettings) coinsLabel.getLabelSettings().duplicate(false);
                coinsLabel.setLabelSettings(newSettings);
            }
        }

        godot.api.StyleBoxFlat style = Global.instance.getTierStyle(value.itemTier);
        if (style != null) {
            addThemeStyleboxOverride("panel", style);
        }
    }

    @RegisterFunction
    public void _on_buy_button_pressed() {
        if (shopItem != null && Global.coins >= shopItem.itemCost) {
            onItemPurchased.emit(shopItem);
            Global.coins -= shopItem.itemCost;
            queueFree();
        }
    }

    @RegisterFunction
    @Override
    public void _process(double delta) {
        if (shopItem == null || coinsLabel == null) return;
        
        boolean notEnoughMoney = Global.coins < shopItem.itemCost;

        godot.api.LabelSettings settings = coinsLabel.getLabelSettings();
        if (settings != null) {
            if (notEnoughMoney) {
                settings.setFontColor(new godot.core.Color(1.0f, 0.0f, 0.0f, 1.0f));
            } else {
                settings.setFontColor(new godot.core.Color(1.0f, 1.0f, 1.0f, 1.0f));
            }
        }
        
        if (buyButton != null) {
            buyButton.setDisabled(notEnoughMoney);
        }
    }
}
