package game.resources.items.weapons;

import game.resources.items.ItemBase;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterProperty;
import godot.api.PackedScene;
import godot.api.Resource;

@RegisterClass
public class ItemWeapon extends ItemBase {
    public ItemWeapon(){
        super();
    }

    public enum WeaponType {
        MELEE, // 0
        RANGE  // 1
    }

    @Export
    @RegisterProperty()
    public WeaponType type = WeaponType.MELEE;

    // 3. File Scene (.tscn) thực tế của vũ khí để sinh ra trên tay nhân vật
    @Export
    @RegisterProperty
    public PackedScene weaponScene;

    // 4. Tham chiếu đến bộ thông số (Sát thương, Tốc độ...)
    // Sếp sẽ kéo file .tres của WeaponStats vào ô này trong Inspector
    @Export
    @RegisterProperty
    public WeaponStats stats;

    // 5. Hệ thống nâng cấp: Kéo file ItemWeapon cấp độ cao hơn vào đây
    @Export
    @RegisterProperty
    public Resource upgradeTo;
}
