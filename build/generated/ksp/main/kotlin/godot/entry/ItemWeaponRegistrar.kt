// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.autoloads.Global.UpgradeTier
import game.resources.items.ItemBase.ItemType
import game.resources.items.weapons.ItemWeapon
import game.resources.items.weapons.ItemWeapon.WeaponType
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NONE
import godot.core.PropertyHint.RESOURCE_TYPE
import godot.core.VariantCaster.ENUM
import godot.core.VariantCaster.INT
import godot.core.VariantParser.LONG
import godot.core.VariantParser.OBJECT
import godot.core.VariantParser.STRING
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "ItemWeapon",
  "Resource",
  "game.resources.items.weapons.ItemWeapon",
  "src/main/java/game/resources/items/weapons/ItemWeapon.java",
  "gdj/game/resources/items/weapons/ItemWeapon.gdj",
  "project-3",
  "game.resources.items.ItemBase,godot.api.Resource,godot.api.RefCounted,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.resources.items.weapons.ItemWeapon.type,game.resources.items.weapons.ItemWeapon.weaponScene,game.resources.items.weapons.ItemWeapon.stats,game.resources.items.weapons.ItemWeapon.upgradeTo,game.resources.items.weapons.ItemWeapon.itemName,game.resources.items.weapons.ItemWeapon.itemIcon,game.resources.items.weapons.ItemWeapon.itemTier,game.resources.items.weapons.ItemWeapon.itemType,game.resources.items.weapons.ItemWeapon.itemCost",
  "game.resources.items.weapons.ItemWeapon.getDescription",
  true,
)
public open class ItemWeaponRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<ItemWeapon>(listOf("ItemBase"), ItemWeapon::class, false, "Resource", "ItemWeapon", "src/main/java/game/resources/items/weapons/ItemWeapon.java", "gdj/game/resources/items/weapons/ItemWeapon.gdj") {
        constructor(KtConstructor0(::ItemWeapon))
        notificationFunctions(listOf())
        function(ItemWeapon::getDescription, STRING, KtFunctionArgument(STRING, "kotlin.String"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(ItemWeapon::type, ENUM<WeaponType>(WeaponType.entries.toTypedArray()), ENUM<WeaponType>(WeaponType.entries.toTypedArray()), "Int", godot.core.PropertyHint.ENUM, "MELEE,RANGE", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemWeapon::weaponScene, OBJECT, OBJECT, "godot.api.PackedScene", RESOURCE_TYPE, "PackedScene", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemWeapon::stats, OBJECT, OBJECT, "game.resources.items.weapons.WeaponStats", RESOURCE_TYPE, "WeaponStats", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemWeapon::upgradeTo, OBJECT, OBJECT, "game.resources.items.weapons.ItemWeapon", RESOURCE_TYPE, "ItemWeapon", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemWeapon::itemName, STRING, STRING, "kotlin.String", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemWeapon::itemIcon, OBJECT, OBJECT, "godot.api.Texture2D", RESOURCE_TYPE, "Texture2D", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemWeapon::itemTier, ENUM<UpgradeTier>(UpgradeTier.entries.toTypedArray()), ENUM<UpgradeTier>(UpgradeTier.entries.toTypedArray()), "Int", godot.core.PropertyHint.ENUM, "COMMON,RARE,EPIC,LEGENDARY", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemWeapon::itemType, ENUM<ItemType>(ItemType.entries.toTypedArray()), ENUM<ItemType>(ItemType.entries.toTypedArray()), "Int", godot.core.PropertyHint.ENUM, "WEAPON,UPGRADE,PASSIVE", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemWeapon::itemCost, INT, LONG, "kotlin.Int", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
