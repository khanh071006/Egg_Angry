// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.ui.UpgradeCard
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.RESOURCE_TYPE
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "UpgradeCard",
  "Panel",
  "game.ui.UpgradeCard",
  "src/main/java/game/ui/UpgradeCard.java",
  "gdj/game/ui/UpgradeCard.gdj",
  "project-3",
  "godot.api.Panel,godot.api.Control,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.ui.UpgradeCard.itemData",
  "game.ui.UpgradeCard._ready,game.ui.UpgradeCard.setData,game.ui.UpgradeCard._on_custom_button_pressed",
  true,
)
public open class UpgradeCardRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<UpgradeCard>(listOf(), UpgradeCard::class, false, "Panel", "UpgradeCard", "src/main/java/game/ui/UpgradeCard.java", "gdj/game/ui/UpgradeCard.gdj") {
        constructor(KtConstructor0(::UpgradeCard))
        notificationFunctions(listOf())
        function(UpgradeCard::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(UpgradeCard::setData, NIL, OBJECT, KtFunctionArgument(OBJECT, "game.resources.items.upgrades.ItemUpgrade", "value"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(UpgradeCard::_on_custom_button_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(UpgradeCard::itemData, OBJECT, OBJECT, "game.resources.items.upgrades.ItemUpgrade", RESOURCE_TYPE, "ItemUpgrade", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
