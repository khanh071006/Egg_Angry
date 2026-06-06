// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.ui.ItemCard
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
  "ItemCard",
  "Button",
  "game.ui.ItemCard",
  "src/main/java/game/ui/ItemCard.java",
  "gdj/game/ui/ItemCard.gdj",
  "project-3",
  "godot.api.Button,godot.api.BaseButton,godot.api.Control,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "game.ui.ItemCard.onItemCardSelected",
  "game.ui.ItemCard.item",
  "game.ui.ItemCard._ready,game.ui.ItemCard.setItem,game.ui.ItemCard._on_button_pressed",
  true,
)
public open class ItemCardRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<ItemCard>(listOf(), ItemCard::class, false, "Button", "ItemCard", "src/main/java/game/ui/ItemCard.java", "gdj/game/ui/ItemCard.gdj") {
        constructor(KtConstructor0(::ItemCard))
        notificationFunctions(listOf())
        function(ItemCard::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(ItemCard::setItem, NIL, OBJECT, KtFunctionArgument(OBJECT, "game.resources.items.ItemBase", "value"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(ItemCard::_on_button_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        signal(ItemCard::onItemCardSelected, KtFunctionArgument(OBJECT, "game.ui.ItemCard", "p0"))
        property(ItemCard::item, OBJECT, OBJECT, "game.resources.items.ItemBase", RESOURCE_TYPE, "ItemBase", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
