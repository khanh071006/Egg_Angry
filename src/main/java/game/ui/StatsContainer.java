package game.ui;

import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Label;
import godot.api.Panel;
import godot.global.GD;

@RegisterClass
public class StatsContainer extends Panel {

	@Export
	@RegisterProperty
	public Label healthLabel;
	@Export
	@RegisterProperty
	public Label regenLabel;
	@Export
	@RegisterProperty
	public Label lifestealLabel;
	@Export
	@RegisterProperty
	public Label damageLabel;
	@Export
	@RegisterProperty
	public Label luckLabel;
	@Export
	@RegisterProperty
	public Label speedLabel;
	@Export
	@RegisterProperty
	public Label blockLabel;
	@Export
	@RegisterProperty
	public Label harvestingLabel;

	// Lưu lại chỉ số cũ để so sánh, tránh cập nhật String liên tục nếu không có thay đổi
	private float lastHealth = -1, lastRegen = -1, lastLifesteal = -1, lastDamage = -1;
	private float lastLuck = -1, lastSpeed = -1, lastBlock = -1, lastHarvesting = -1;

	@RegisterFunction
	@Override
	public void _ready() {
		// Kiểm tra an toàn bằng get_node_or_null để tránh crash game nếu sai tên Node trên Godot Editor
		if (healthLabel == null) healthLabel = (Label) getNodeOrNull(new godot.core.NodePath("%HealthLabel"));
		if (regenLabel == null) regenLabel = (Label) getNodeOrNull(new godot.core.NodePath("%HPRegenLabel"));
		if (lifestealLabel == null) lifestealLabel = (Label) getNodeOrNull(new godot.core.NodePath("%LifeStealLabel"));
		if (damageLabel == null) damageLabel = (Label) getNodeOrNull(new godot.core.NodePath("%DamageLabel"));
		if (luckLabel == null) luckLabel = (Label) getNodeOrNull(new godot.core.NodePath("%LuckLabel"));
		if (speedLabel == null) speedLabel = (Label) getNodeOrNull(new godot.core.NodePath("%SpeedLabel"));
		if (blockLabel == null) blockLabel = (Label) getNodeOrNull(new godot.core.NodePath("%BlockLabel"));
		if (harvestingLabel == null) harvestingLabel = (Label) getNodeOrNull(new godot.core.NodePath("%HarvestingLabel"));
	}

	@RegisterFunction
	@Override
	public void _process(double delta) {
		// Nếu Player không hợp lệ (đã chết), reset UI về 0 thay vì giữ nguyên số cũ
		if (!GD.isInstanceValid(game.autoloads.Global.player)) {
			clearStatsToZero();
			return;
		}

		game.resources.units.UnitStats stats = game.autoloads.Global.player.stats;
		if (stats == null) return;

		// 1. Cập nhật Health
		if (healthLabel != null && lastHealth != stats.health) {
			lastHealth = stats.health;
			healthLabel.setText(String.valueOf((int) lastHealth));
		}

		// 2. Cập nhật HP Regen
		if (regenLabel != null && lastRegen != stats.hpRegen) {
			lastRegen = stats.hpRegen;
			regenLabel.setText(String.valueOf((int) lastRegen));
		}

		// 3. Cập nhật Lifesteal
		if (lifestealLabel != null && lastLifesteal != stats.lifesteal) {
			lastLifesteal = stats.lifesteal;
			lifestealLabel.setText((int) lastLifesteal + "%");
		}

		// 4. Cập nhật Damage
		if (damageLabel != null && lastDamage != stats.damage) {
			lastDamage = stats.damage;
			damageLabel.setText(String.valueOf((int) lastDamage));
		}

		// 5. Cập nhật Luck (Chỉ xử lý ép kiểu khi có sự thay đổi chỉ số thực tế)
		if (luckLabel != null) {
			if (stats instanceof game.resources.units.PlayerStats) {
				float currentLuck = ((game.resources.units.PlayerStats) stats).luck;
				if (lastLuck != currentLuck) {
					lastLuck = currentLuck;
					luckLabel.setText(String.valueOf((int) lastLuck));
				}
			} else if (lastLuck != 0) {
				lastLuck = 0;
				luckLabel.setText("0");
			}
		}

		// 6. Cập nhật Speed
		if (speedLabel != null && lastSpeed != stats.speed) {
			lastSpeed = stats.speed;
			speedLabel.setText(String.valueOf((int) lastSpeed));
		}

		// 7. Cập nhật Block Chance
		if (blockLabel != null && lastBlock != stats.blockchance) {
			lastBlock = stats.blockchance;
			blockLabel.setText((int) lastBlock + "%");
		}

		// 8. Cập nhật Harvesting
		if (harvestingLabel != null && lastHarvesting != stats.harvesting) {
			lastHarvesting = stats.harvesting;
			harvestingLabel.setText(String.valueOf((int) lastHarvesting));
		}
	}

	// Hàm hỗ trợ dọn dẹp giao diện khi người chơi không còn tồn tại
	private void clearStatsToZero() {
		if (healthLabel != null && lastHealth != 0) { lastHealth = 0; healthLabel.setText("0"); }
		if (regenLabel != null && lastRegen != 0) { lastRegen = 0; regenLabel.setText("0"); }
		if (lifestealLabel != null && lastLifesteal != 0) { lastLifesteal = 0; lifestealLabel.setText("0%"); }
		if (damageLabel != null && lastDamage != 0) { lastDamage = 0; damageLabel.setText("0"); }
		if (luckLabel != null && lastLuck != 0) { lastLuck = 0; luckLabel.setText("0"); }
		if (speedLabel != null && lastSpeed != 0) { lastSpeed = 0; speedLabel.setText("0"); }
		if (blockLabel != null && lastBlock != 0) { lastBlock = 0; blockLabel.setText("0%"); }
		if (harvestingLabel != null && lastHarvesting != 0) { lastHarvesting = 0; harvestingLabel.setText("0"); }
	}
}
