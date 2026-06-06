// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.autoloads.Global.UpgradeTier
import game.resources.items.ItemBase
import game.resources.items.ItemBase.ItemType
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
  "ItemBase",
  "Resource",
  "game.resources.items.ItemBase",
  "src/main/java/game/resources/items/ItemBase.java",
  "gdj/game/resources/items/ItemBase.gdj",
  "project-3",
  "godot.api.Resource,godot.api.RefCounted,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.resources.items.ItemBase.itemName,game.resources.items.ItemBase.itemIcon,game.resources.items.ItemBase.itemTier,game.resources.items.ItemBase.itemType,game.resources.items.ItemBase.itemCost",
  "game.resources.items.ItemBase.getDescription",
  true,
)
public open class ItemBaseRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<ItemBase>(listOf(), ItemBase::class, false, "Resource", "ItemBase", "src/main/java/game/resources/items/ItemBase.java", "gdj/game/resources/items/ItemBase.gdj") {
        constructor(KtConstructor0(::ItemBase))
        notificationFunctions(listOf())
        function(ItemBase::getDescription, STRING, KtFunctionArgument(STRING, "kotlin.String"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(ItemBase::itemName, STRING, STRING, "kotlin.String", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemBase::itemIcon, OBJECT, OBJECT, "godot.api.Texture2D", RESOURCE_TYPE, "Texture2D", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemBase::itemTier, ENUM<UpgradeTier>(UpgradeTier.entries.toTypedArray()), ENUM<UpgradeTier>(UpgradeTier.entries.toTypedArray()), "Int", godot.core.PropertyHint.ENUM, "COMMON,RARE,EPIC,LEGENDARY", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemBase::itemType, ENUM<ItemType>(ItemType.entries.toTypedArray()), ENUM<ItemType>(ItemType.entries.toTypedArray()), "Int", godot.core.PropertyHint.ENUM, "WEAPON,UPGRADE,PASSIVE", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ItemBase::itemCost, INT, LONG, "kotlin.Int", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
