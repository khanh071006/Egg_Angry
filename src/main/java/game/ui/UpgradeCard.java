package game.ui;

import game.resources.items.upgrades.ItemUpgrade;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Label;
import godot.api.Panel;
import godot.api.TextureRect;

@RegisterClass
public class UpgradeCard extends Panel {

    @Export
    @RegisterProperty
    public ItemUpgrade itemData;

    private Label itemName;
    private TextureRect itemIcon;
    private Label itemDescription;

    @RegisterFunction
    @Override
    public void _ready() {
        // Tự động tìm các node giao diện
        itemName = (Label) getNodeOrNull("%Name");
        itemIcon = (TextureRect) getNodeOrNull("%Icon");
        itemDescription = (Label) getNodeOrNull("%Description");
        
        // Cập nhật giao diện nếu đã có sẵn data từ inspector (dành cho việc test)
        if (itemData != null) {
            setData(itemData);
        }
    }

    @RegisterFunction
    public void setData(ItemUpgrade value) {
        this.itemData = value;
        
        if (value == null) return;
        
        // Cập nhật thông tin lên UI
        if (itemName != null) {
            itemName.setText(value.itemName);
        }
        
        if (itemIcon != null) {
            itemIcon.setTexture(value.itemIcon);
        }
        
        if (itemDescription != null) {
            itemDescription.setText(value.description);
        }

        // --- CẬP NHẬT MÀU NỀN CHO THẺ ---
        godot.api.StyleBoxFlat tierStyle = game.autoloads.Global.instance.getTierStyle(value.itemTier);
        if (tierStyle != null) {
            addThemeStyleboxOverride("panel", tierStyle);
        }
    }

    @RegisterFunction
    public void _on_custom_button_pressed() {
        if (itemData != null && godot.global.GD.isInstanceValid(game.autoloads.Global.player)) {
            game.autoloads.Global.instance.playSfx("res://assets/audio/level-up_sound.mp3");
            itemData.applyUpgrade();
            game.autoloads.Global.instance.onUpgradeSelected.emit();
        }
    }
}
