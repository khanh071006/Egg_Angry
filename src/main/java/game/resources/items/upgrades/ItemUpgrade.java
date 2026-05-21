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

        StringName id = new StringName(statId);

        // Lấy giá trị hiện tại dựa trên tên biến (statId)
        Object currentValObj = Global.player.stats.get(id);

        if (currentValObj == null) {
            godot.global.GD.printErr("Lỗi: Không tìm thấy biến nào có tên '" + statId + "' trong UnitStats!");
            return;
        }

        float currentVal = 0.0f;

        // Ép kiểu an toàn sang float
        if (currentValObj instanceof Number) {
            currentVal = ((Number) currentValObj).floatValue();
        } else {
            try {
                currentVal = Float.parseFloat(currentValObj.toString());
            } catch (Exception e) {
                godot.global.GD.printErr("Lỗi: Không thể chuyển đổi giá trị của '" + statId + "' thành số!");
                return;
            }
        }

        // Tính giá trị mới
        float newVal = currentVal + value;

        // Cập nhật lại vào PlayerStats
        Global.player.stats.set(id, newVal);

        godot.global.GD.print("Đã nâng cấp: " + statId + " lên " + newVal);
    }
}
