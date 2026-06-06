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
    public float addValue2 = 0.0f;

    @Export
    @RegisterProperty
    public String addStat2 = "";

    @Export
    @RegisterProperty
    public float removeValue = 0.0f;

    @Export
    @RegisterProperty
    public String removeStat = "";

    @Export
    @RegisterProperty
    public float removeValue2 = 0.0f;

    @Export
    @RegisterProperty
    public String removeStat2 = "";

    public ItemPassive() {
        super();
        this.itemType = ItemType.PASSIVE;
    }

    @RegisterFunction
    @Override
    public String getDescription() {
        String description = "[code]\n\n";

        if (addValue != 0.0f) {
            description += "[color=green]";
            String valStr = (addValue == (long) addValue) ? String.format("%d", (long) addValue) : String.format("%s", addValue);
            description += "+" + valStr + " " + addStat;
            description += "[/color]\n";
        }
        
        if (addValue2 != 0.0f) {
            description += "[color=green]";
            String valStr = (addValue2 == (long) addValue2) ? String.format("%d", (long) addValue2) : String.format("%s", addValue2);
            description += "+" + valStr + " " + addStat2;
            description += "[/color]\n";
        }

        if (removeValue != 0.0f) {
            description += "[color=red]";
            String valStr = (removeValue == (long) removeValue) ? String.format("%d", (long) removeValue) : String.format("%s", removeValue);
            description += "-" + valStr + " " + removeStat;
            description += "[/color]\n";
        }
        
        if (removeValue2 != 0.0f) {
            description += "[color=red]";
            String valStr = (removeValue2 == (long) removeValue2) ? String.format("%d", (long) removeValue2) : String.format("%s", removeValue2);
            description += "-" + valStr + " " + removeStat2;
            description += "[/color]\n";
        }
        
        description += "[/code]";

        return description;
    }

    @RegisterFunction
    public void applyPassive() {
        if (addValue != 0.0f && addStat != null && !addStat.isEmpty()) {
            modifyStat(addStat, addValue);
        }
        if (addValue2 != 0.0f && addStat2 != null && !addStat2.isEmpty()) {
            modifyStat(addStat2, addValue2);
        }
        if (removeValue != 0.0f && removeStat != null && !removeStat.isEmpty()) {
            modifyStat(removeStat, -removeValue);
        }
        if (removeValue2 != 0.0f && removeStat2 != null && !removeStat2.isEmpty()) {
            modifyStat(removeStat2, -removeValue2);
        }
    }

    private void modifyStat(String statName, float value) {
        // Áp dụng cho các vũ khí đang trang bị
        if (statName.toLowerCase().equals("critchance") || statName.toLowerCase().equals("crit_chance") || statName.toLowerCase().equals("crit")) {
            for (game.resources.items.weapons.ItemWeapon w : Global.instance.equippedWeapons) {
                if (w.stats != null) {
                    w.stats.critChance += (value / 100.0f); // Crit is usually a float from 0 to 1
                }
            }
            GD.print("Áp dụng Passive vào Vũ khí: " + statName + " | Thay đổi: " + value);
            return;
        }
        
        if (statName.toLowerCase().equals("range") || statName.toLowerCase().equals("max_range")) {
            for (game.resources.items.weapons.ItemWeapon w : Global.instance.equippedWeapons) {
                if (w.stats != null) {
                    w.stats.maxRange += value;
                }
            }
            GD.print("Áp dụng Passive vào Vũ khí: " + statName + " | Thay đổi: " + value);
            return;
        }

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

    public boolean canAffordPenalty() {
        if (!canAffordSinglePenalty(removeStat, removeValue)) return false;
        if (!canAffordSinglePenalty(removeStat2, removeValue2)) return false;
        return true;
    }

    private boolean canAffordSinglePenalty(String statName, float penaltyValue) {
        if (penaltyValue <= 0 || statName == null || statName.isEmpty()) return true;
        
        if (Global.player == null || Global.player.stats == null) return true; // Failsafe
        
        float currentVal = 0;
        
        if (statName.toLowerCase().equals("critchance") || statName.toLowerCase().equals("crit_chance") || statName.toLowerCase().equals("crit")) {
            if (Global.instance.equippedWeapons.isEmpty()) return true;
            for (game.resources.items.weapons.ItemWeapon w : Global.instance.equippedWeapons) {
                if (w.stats != null && w.stats.critChance < (penaltyValue / 100.0f)) {
                    return false; // Nếu có vũ khí nào bị âm crit thì bỏ qua thẻ này
                }
            }
            return true;
        }

        if (statName.toLowerCase().equals("range") || statName.toLowerCase().equals("max_range")) {
            if (Global.instance.equippedWeapons.isEmpty()) return true;
            for (game.resources.items.weapons.ItemWeapon w : Global.instance.equippedWeapons) {
                if (w.stats != null && w.stats.maxRange < penaltyValue) {
                    return false;
                }
            }
            return true;
        }

        switch (statName.toLowerCase()) {
            case "health": currentVal = Global.player.stats.health; break;
            case "damage": currentVal = Global.player.stats.damage; break;
            case "speed": currentVal = Global.player.stats.speed; break;
            case "blockchance": 
            case "block_chance":
                currentVal = Global.player.stats.blockchance; break;
            case "hp_regen":
            case "hpregen": 
                currentVal = Global.player.stats.hpRegen; break;
            case "lifesteal": currentVal = Global.player.stats.lifesteal; break;
            case "harvesting": currentVal = Global.player.stats.harvesting; break;
            case "luck": 
                if (Global.player.stats instanceof game.resources.units.PlayerStats) {
                    currentVal = ((game.resources.units.PlayerStats) Global.player.stats).luck;
                }
                break;
        }
        
        return currentVal >= penaltyValue;
    }
}
