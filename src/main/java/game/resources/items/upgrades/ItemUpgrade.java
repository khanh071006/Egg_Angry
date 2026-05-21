package game.resources.items.upgrades;

import game.autoloads.Global;
import game.resources.items.ItemBase;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.core.StringName;

@RegisterClass
public class ItemUpgrade extends ItemBase {

    @Export
    @RegisterProperty
    public float value = 0.0f;

    @Export
    @RegisterProperty
    public String description = "";

    @Export
    @RegisterProperty
    public String statId = "";

    public ItemUpgrade() {
        super();
        this.itemType = ItemType.UPGRADE; // Khóa chặt type luôn là UPGRADE
    }

    @RegisterFunction
    public void applyUpgrade() {
        if (Global.player == null || Global.player.stats == null) {
            return;
        }

        if (statId == null || statId.isEmpty()) {
            godot.global.GD.printErr("Lỗi: statId đang bị trống!");
            return;
        }

        // Lần trước bạn copy code cũ đè lên đoạn switch-case an toàn của tôi rồi!
        // Giờ tôi khôi phục lại cấu trúc switch-case để đảm bảo không bị lỗi ép kiểu (parsing) nữa.
        switch (statId) {
            case "health":
                Global.player.stats.health += value;
                break;
            case "damage":
                Global.player.stats.damage += value;
                break;
            case "speed":
                Global.player.stats.speed += value;
                break;
            case "blockchance":
                Global.player.stats.blockchance += value;
                break;
            case "hpRegen":
                Global.player.stats.hpRegen += value;
                break;
            case "lifesteal":
                Global.player.stats.lifesteal += value;
                break;
            case "harvesting":
                Global.player.stats.harvesting += value;
                break;
            case "luck":
                if (Global.player.stats instanceof game.resources.units.PlayerStats) {
                    ((game.resources.units.PlayerStats) Global.player.stats).luck += value;
                }
                break;
            default:
                godot.global.GD.printErr("Lỗi: Không hỗ trợ nâng cấp cho statId: " + statId);
                return;
        }

        godot.global.GD.print("Đã nâng cấp: " + statId + " thêm " + value);
    }
}
