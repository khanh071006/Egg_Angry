package game.resources.items;

import game.autoloads.Global;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Resource;
import godot.api.Texture2D;


@RegisterClass
public class ItemBase extends Resource {

    public enum ItemType {
        WEAPON,   // 0
        UPGRADE,  // 1
        PASSIVE   // 2
    }

    @Export @RegisterProperty
    public String itemName = "";

    @Export @RegisterProperty
    public Texture2D itemIcon;

    @Export @RegisterProperty
    public Global.UpgradeTier itemTier = Global.UpgradeTier.COMMON; // Lấy từ Enum Global

    @Export @RegisterProperty
    public ItemType itemType = ItemType.WEAPON;

    @Export @RegisterProperty
    public int itemCost = 0;

    @RegisterFunction
    public String getDescription() { return ""; }

    public  ItemBase() {
    }
}
