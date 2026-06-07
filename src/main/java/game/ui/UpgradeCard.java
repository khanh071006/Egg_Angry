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
	
	// Khai báo thêm biến chứa ảnh nền thẻ
	private TextureRect cardBackground;

	@RegisterFunction
	@Override
	public void _ready() {
		// Tự động tìm các node giao diện
		itemName = (Label) getNodeOrNull("%Name");
		itemIcon = (TextureRect) getNodeOrNull("%Icon");
		itemDescription = (Label) getNodeOrNull("%Description");
		
		// Tìm đến cái TextureRect làm nền
		cardBackground = (TextureRect) getNodeOrNull("%CardBackground");
		
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

		// --- CẬP NHẬT ẢNH NỀN CHO THẺ ---
		godot.api.Texture2D tierTexture = game.autoloads.Global.instance.getTierTexture(value.itemTier);
		
		if (cardBackground != null && tierTexture != null) {
			cardBackground.setTexture(tierTexture);
		}
	}

	@RegisterFunction
	public void _on_custom_button_pressed() {
		if (itemData != null && godot.global.GD.isInstanceValid(game.autoloads.Global.player)) {
			itemData.applyUpgrade();
			game.autoloads.Global.instance.onUpgradeSelected.emit();
		}
	}
}
