// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.autoloads.Global.UpgradeTier
import game.resources.items.ItemBase.ItemType
import game.resources.items.ItemPassive
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
  "ItemPassive",
  "Resource",
  "game.resources.items.ItemPassive",
  "src/main/java/game/resources/items/ItemPassive.java",
  "gdj/game/resources/items/ItemPassive.gdj",
  "project-3",
  "game.resources.items.ItemBase,godot.api.Resource,godot.api.RefCounted,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.resources.items.ItemPassive.addValue,game.resources.items.ItemPassive.addStat,game.resources.items.ItemPassive.addValue2,game.resources.items.ItemPassive.addStat2,game.resources.items.ItemPassive.removeValue,game.resources.items.ItemPassive.removeStat,game.resources.items.ItemPassive.removeValue2,game.resources.items.ItemPassive.removeStat2,game.resources.items.ItemPassive.itemName,game.resources.items.ItemPassive.itemIcon,game.resources.items.ItemPassive.itemTier,game.resources.items.ItemPassive.itemType,game.resources.items.ItemPassive.itemCost",
  "game.resources.items.ItemPassive.getDescription,game.resources.items.ItemPassive.applyPassive",
  true,
)
public open class ItemPassiveRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<ItemPassive>(listOf("ItemBase"), ItemPassive::class, false, "Resource", "ItemPassive", "src/main/java/game/resources/items/ItemPassive.java", "gdj/game/resources/items/ItemPassive.gdj") {
        constructor(KtConstructor0(::ItemPassive))
        notificationFunctions(listOf())
        function(ItemPassive::getDescription, STRING, KtFunctionArgument(STRING, "kotlin.String"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(ItemPassive::applyPassive, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(ItemPassive::addValue, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemPassive::addStat, STRING, STRING, "kotlin.String", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemPassive::addValue2, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemPassive::addStat2, STRING, STRING, "kotlin.String", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemPassive::removeValue, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemPassive::removeStat, STRING, STRING, "kotlin.String", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemPassive::removeValue2, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemPassive::removeStat2, STRING, STRING, "kotlin.String", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemPassive::itemName, STRING, STRING, "kotlin.String", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemPassive::itemIcon, OBJECT, OBJECT, "godot.api.Texture2D", RESOURCE_TYPE, "Texture2D", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemPassive::itemTier, ENUM<UpgradeTier>(UpgradeTier.entries.toTypedArray()), ENUM<UpgradeTier>(UpgradeTier.entries.toTypedArray()), "Int", godot.core.PropertyHint.ENUM, "COMMON,RARE,EPIC,LEGENDARY", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemPassive::itemType, ENUM<ItemType>(ItemType.entries.toTypedArray()), ENUM<ItemType>(ItemType.entries.toTypedArray()), "Int", godot.core.PropertyHint.ENUM, "WEAPON,UPGRADE,PASSIVE", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemPassive::itemCost, INT, LONG, "kotlin.Int", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
