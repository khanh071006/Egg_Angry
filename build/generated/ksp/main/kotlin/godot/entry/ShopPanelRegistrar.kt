// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.ui.ShopPanel
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.RESOURCE_TYPE
import godot.core.PropertyHint.TYPE_STRING
import godot.core.VariantCaster.INT
import godot.core.VariantParser.ARRAY
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "ShopPanel",
  "Panel",
  "game.ui.ShopPanel",
  "src/main/java/game/ui/ShopPanel.java",
  "gdj/game/ui/ShopPanel.gdj",
  "project-3",
  "godot.api.Panel,godot.api.Control,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "game.ui.ShopPanel.onShopNextWave",
  "game.ui.ShopPanel.shopCardScene,game.ui.ShopPanel.shopItems",
  "game.ui.ShopPanel._ready,game.ui.ShopPanel.loadShop,game.ui.ShopPanel._on_next_wave_button_pressed,game.ui.ShopPanel._on_shop_card_on_item_purchased,game.ui.ShopPanel._on_item_card_selected",
  true,
)
public open class ShopPanelRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<ShopPanel>(listOf(), ShopPanel::class, false, "Panel", "ShopPanel", "src/main/java/game/ui/ShopPanel.java", "gdj/game/ui/ShopPanel.gdj") {
        constructor(KtConstructor0(::ShopPanel))
        notificationFunctions(listOf())
        function(ShopPanel::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(ShopPanel::loadShop, NIL, INT, KtFunctionArgument(INT, "kotlin.Int", "currentWave"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(ShopPanel::_on_next_wave_button_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(ShopPanel::_on_shop_card_on_item_purchased, NIL, OBJECT, KtFunctionArgument(OBJECT, "game.resources.items.ItemBase", "purchasedItem"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(ShopPanel::_on_item_card_selected, NIL, OBJECT, KtFunctionArgument(OBJECT, "game.ui.ItemCard", "card"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        signal(ShopPanel::onShopNextWave)
        property(ShopPanel::shopCardScene, OBJECT, OBJECT, "godot.api.PackedScene", RESOURCE_TYPE, "PackedScene", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ShopPanel::shopItems, ARRAY, ARRAY, "godot.core.VariantArray", TYPE_STRING, "24/17:Resource", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
