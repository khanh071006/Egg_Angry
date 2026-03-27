package game.resources;

import godot.api.Resource;
import godot.api.Texture2D;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterProperty;
import godot.annotation.Export;

@RegisterClass
public class UnitStats extends Resource {

	public enum UnitType {
		PLAYER,
		ENEMY
	}

	@Export
	@RegisterProperty
	public String unitName = "";

	@Export
	@RegisterProperty
	public UnitType type = UnitType.PLAYER;; // Lát nữa class Con sẽ tự động điền cái này!

	@Export
	@RegisterProperty
	public Texture2D icon;

	@Export
	@RegisterProperty
	public float health = 1.0f;

	@Export
	@RegisterProperty
	public float healthIncreasePerWave = 1.0f;

	@Export
	@RegisterProperty
	public float damage = 1.0f;

	@Export
	@RegisterProperty
	public float damageIncreasePerWave = 1.0f;

	@Export
	@RegisterProperty
	public float speed = 300.0f;

	// BẮT BUỘC CÓ CONSTRUCTOR RỖNG
	public UnitStats() {
	}
}
