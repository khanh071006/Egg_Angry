package game.resources.units;

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

	@Export
	@RegisterProperty
	public float blockchance = 0.0f;

	@Export
	@RegisterProperty
	public float hpRegion = 0.0f;

	@Export
	@RegisterProperty
	public float lifesteal = 0.0f;

	@Export
	@RegisterProperty
	public float harvesting = 0.0f;

	// BẮT BUỘC CÓ CONSTRUCTOR RỖNG
	public UnitStats() {
	}

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public UnitType getType() {
        return type;
    }

    public void setType(UnitType type) {
        this.type = type;
    }

    public Texture2D getIcon() {
        return icon;
    }

    public void setIcon(Texture2D icon) {
        this.icon = icon;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getHealthIncreasePerWave() {
        return healthIncreasePerWave;
    }

    public void setHealthIncreasePerWave(float healthIncreasePerWave) {
        this.healthIncreasePerWave = healthIncreasePerWave;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getDamageIncreasePerWave() {
        return damageIncreasePerWave;
    }

    public void setDamageIncreasePerWave(float damageIncreasePerWave) {
        this.damageIncreasePerWave = damageIncreasePerWave;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getBlockchance() {
        return blockchance;
    }

    public void setBlockchance(float blockchance) {
        this.blockchance = blockchance;
    }

    public float getHpRegion() { return hpRegion; }
    public void setHpRegion(float hpRegion) { this.hpRegion = hpRegion; }
    public float getLifesteal() { return lifesteal; }
    public void setLifesteal(float lifesteal) { this.lifesteal = lifesteal; }
    public float getHarvesting() { return harvesting; }
    public void setHarvesting(float harvesting) { this.harvesting = harvesting; }
}
