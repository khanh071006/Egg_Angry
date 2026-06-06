// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.ui.ShopCard
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.RESOURCE_TYPE
import godot.core.VariantParser.DOUBLE
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "ShopCard",
  "Panel",
  "game.ui.ShopCard",
  "src/main/java/game/ui/ShopCard.java",
  "gdj/game/ui/ShopCard.gdj",
  "project-3",
  "godot.api.Panel,godot.api.Control,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "game.ui.ShopCard.onItemPurchased",
  "game.ui.ShopCard.shopItem",
  "game.ui.ShopCard._process,game.ui.ShopCard._ready,game.ui.ShopCard.setShopItem,game.ui.ShopCard._on_buy_button_pressed",
  true,
)
public open class ShopCardRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<ShopCard>(listOf(), ShopCard::class, false, "Panel", "ShopCard", "src/main/java/game/ui/ShopCard.java", "gdj/game/ui/ShopCard.gdj") {
        constructor(KtConstructor0(::ShopCard))
        notificationFunctions(listOf())
        function(ShopCard::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(ShopCard::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(ShopCard::setShopItem, NIL, OBJECT, KtFunctionArgument(OBJECT, "game.resources.items.ItemBase", "value"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(ShopCard::_on_buy_button_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        signal(ShopCard::onItemPurchased, KtFunctionArgument(OBJECT, "game.resources.items.ItemBase", "p0"))
        property(ShopCard::shopItem, OBJECT, OBJECT, "game.resources.items.ItemBase", RESOURCE_TYPE, "ItemBase", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
