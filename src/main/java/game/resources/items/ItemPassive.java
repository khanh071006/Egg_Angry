package game.resources.items;

import game.autoloads.Global;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.global.GD;

@RegisterClass
public class ItemPassive extends ItemBase {

    @Export
    @RegisterProperty
    public float addValue = 0.0f;

    @Export
    @RegisterProperty
    public String addStat = "";

    @Export
    @RegisterProperty
    public float removeValue = 0.0f;

    @Export
    @RegisterProperty
    public String removeStat = "";

    public ItemPassive() {
        super();
        this.itemType = ItemType.PASSIVE;
    }

    @RegisterFunction
    @Override
    public String getDescription() {
        String description = "";

        if (addValue != 0.0f) {
            description += "[color=green]";
            // Ép kiểu hiển thị số nguyên nếu giá trị chẵn
            String valStr = (addValue == (long) addValue) ? String.format("%d", (long)addValue) : String.format("%s", addValue);
            description += "+" + valStr + " " + addStat;
            description += "[/color]";
        }

        if (removeValue != 0.0f) {
            if (!description.isEmpty()) {
                description += "\n";
            }
            description += "[color=red]";
            String valStr = (removeValue == (long) removeValue) ? String.format("%d", (long)removeValue) : String.format("%s", removeValue);
            description += "-" + valStr + " " + removeStat;
            description += "[/color]";
        }

        return description;
    }

    @RegisterFunction
    public void applyPassive() {
        if (addValue != 0.0f && addStat != null && !addStat.isEmpty()) {
            modifyStat(addStat, addValue);
        }
        if (removeValue != 0.0f && removeStat != null && !removeStat.isEmpty()) {
            modifyStat(removeStat, -removeValue);
        }
    }

    private void modifyStat(String statName, float value) {
        if (Global.player == null || Global.player.stats == null) {
            GD.printErr("ItemPassive: Global.player hoặc stats đang null!");
            return;
        }

        switch (statName.toLowerCase()) {
            case "health":
                Global.player.stats.health += value;
                if (Global.player.healthComponent != null) {
                    Global.player.healthComponent.maxHealth += value;
                    Global.player.healthComponent.currentHealth += value;
                }
                break;
            case "speed":
                Global.player.stats.speed += value;
                break;
            case "damage":
                Global.player.stats.damage += value;
                break;
            case "lifesteal":
                Global.player.stats.lifesteal += value;
                break;
            case "luck":
                if (Global.player.stats instanceof game.resources.units.PlayerStats) {
                    ((game.resources.units.PlayerStats) Global.player.stats).luck += value;
                }
                break;
            case "blockchance":
            case "block_chance":
                Global.player.stats.blockchance += value;
                break;
            case "hpregen":
            case "hp_regen":
                Global.player.stats.hpRegen += value;
                break;
            case "harvesting":
                Global.player.stats.harvesting += value;
                break;
            default:
                GD.printErr("ItemPassive: Không tìm thấy stat nào tên là " + statName);
                break;
        }
        
        GD.print("Áp dụng Passive: " + statName + " | Thay đổi: " + value);
    }
}
