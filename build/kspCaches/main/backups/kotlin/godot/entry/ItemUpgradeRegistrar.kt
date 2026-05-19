// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.autoloads.Global.UpgradeTier
import game.resources.items.ItemBase.ItemType
import game.resources.items.upgrades.ItemUpgrade
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NONE
import godot.core.PropertyHint.RESOURCE_TYPE
import godot.core.VariantCaster.ENUM
import godot.core.VariantCaster.FLOAT
import godot.core.VariantCaster.INT
import godot.core.VariantParser.DOUBLE
import godot.core.VariantParser.LONG
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.core.VariantParser.STRING
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "ItemUpgrade",
  "Resource",
  "game.resources.items.upgrades.ItemUpgrade",
  "src/main/java/game/resources/items/upgrades/ItemUpgrade.java",
  "gdj/game/resources/items/upgrades/ItemUpgrade.gdj",
  "project-3",
  "game.resources.items.ItemBase,godot.api.Resource,godot.api.RefCounted,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.resources.items.upgrades.ItemUpgrade.value,game.resources.items.upgrades.ItemUpgrade.description,game.resources.items.upgrades.ItemUpgrade.statId,game.resources.items.upgrades.ItemUpgrade.itemName,game.resources.items.upgrades.ItemUpgrade.itemIcon,game.resources.items.upgrades.ItemUpgrade.itemTier,game.resources.items.upgrades.ItemUpgrade.itemType,game.resources.items.upgrades.ItemUpgrade.itemCost",
  "game.resources.items.upgrades.ItemUpgrade.getDescription,game.resources.items.upgrades.ItemUpgrade.applyUpgrade",
  true,
)
public open class ItemUpgradeRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<ItemUpgrade>(listOf("ItemBase"), ItemUpgrade::class, false, "Resource", "ItemUpgrade", "src/main/java/game/resources/items/upgrades/ItemUpgrade.java", "gdj/game/resources/items/upgrades/ItemUpgrade.gdj") {
        constructor(KtConstructor0(::ItemUpgrade))
        notificationFunctions(listOf())
        function(ItemUpgrade::getDescription, STRING, KtFunctionArgument(STRING, "kotlin.String"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(ItemUpgrade::applyUpgrade, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(ItemUpgrade::`value`, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemUpgrade::description, STRING, STRING, "kotlin.String", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemUpgrade::statId, STRING, STRING, "kotlin.String", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemUpgrade::itemName, STRING, STRING, "kotlin.String", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemUpgrade::itemIcon, OBJECT, OBJECT, "godot.api.Texture2D", RESOURCE_TYPE, "Texture2D", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemUpgrade::itemTier, ENUM<UpgradeTier>(UpgradeTier.entries.toTypedArray()), ENUM<UpgradeTier>(UpgradeTier.entries.toTypedArray()), "Int", godot.core.PropertyHint.ENUM, "COMMON,RARE,EPIC,LEGENDARY", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemUpgrade::itemType, ENUM<ItemType>(ItemType.entries.toTypedArray()), ENUM<ItemType>(ItemType.entries.toTypedArray()), "Int", godot.core.PropertyHint.ENUM, "WEAPON,UPGRADE,PASSIVE", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemUpgrade::itemCost, INT, LONG, "kotlin.Int", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
