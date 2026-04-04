package game.resources.units;

import godot.annotation.RegisterClass;
import godot.annotation.RegisterProperty;
import godot.annotation.Export;

@RegisterClass
public class PlayerStats extends UnitStats {

	// Bổ sung những chỉ số CHỈ PLAYER mới có
	@Export
	@RegisterProperty
	public float luck = 1.0f;

	@Export
	@RegisterProperty
	public float blockChance = 0.0f;

	public PlayerStats() {
		super(); // Gọi hàm khởi tạo của Cha
		this.type = UnitType.PLAYER; // Tự động khóa chặt loại là PLAYER, khỏi sợ điền nhầm!
	}
}
