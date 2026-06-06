package game.ui;

import game.autoloads.Global;
import game.resources.items.ItemBase;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.annotation.RegisterSignal;
import godot.api.Button;
import godot.api.TextureRect;
import godot.core.Signal1;

@RegisterClass
public class ItemCard extends Button {

    @Export
    @RegisterProperty
    public ItemBase item;

    @RegisterSignal
    public Signal1<ItemCard> onItemCardSelected = Signal1.create(this, "onItemCardSelected");

    private TextureRect itemIcon;

    @RegisterFunction
    @Override
    public void _ready() {
        itemIcon = (TextureRect) getNodeOrNull("ItemIcon"); // Tùy thuộc vào cây Godot của bạn
    }

    @RegisterFunction
    public void setItem(ItemBase value) {
        this.item = value;
        if (value == null) return;

        // Đảm bảo node đã sẵn sàng
        if (itemIcon == null) {
            itemIcon = (TextureRect) getNodeOrNull("ItemIcon");
        }

        if (itemIcon != null && value.itemIcon != null) {
            itemIcon.setTexture(value.itemIcon);
        }

        godot.api.StyleBoxFlat style = Global.instance.getTierStyle(value.itemTier);
        if (style != null) {
            addThemeStyleboxOverride("normal", style);
        }
    }

    @RegisterFunction
    public void _on_button_pressed() {
        if (item != null && item.itemType == ItemBase.ItemType.WEAPON) {
            Global.instance.selectedWeapon = (game.resources.items.weapons.ItemWeapon) item;
            onItemCardSelected.emit(this);
        }
    }
}
