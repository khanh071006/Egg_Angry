// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.items.Coins
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NONE
import godot.core.VariantCaster.FLOAT
import godot.core.VariantCaster.INT
import godot.core.VariantParser.DOUBLE
import godot.core.VariantParser.LONG
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.core.VariantParser.VECTOR2
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "Coins",
  "Area2D",
  "game.items.Coins",
  "src/main/java/game/items/Coins.java",
  "gdj/game/items/Coins.gdj",
  "project-3",
  "godot.api.Area2D,godot.api.CollisionObject2D,godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.items.Coins.moveSpeed,game.items.Coins.collectDistance,game.items.Coins.value",
  "game.items.Coins._process,game.items.Coins._ready,game.items.Coins.setCollectionTarget,game.items.Coins.playSpawnAnimation,game.items.Coins._on_area_entered",
  true,
)
public open class CoinsRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<Coins>(listOf(), Coins::class, false, "Area2D", "Coins", "src/main/java/game/items/Coins.java", "gdj/game/items/Coins.gdj") {
        constructor(KtConstructor0(::Coins))
        notificationFunctions(listOf())
        function(Coins::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Coins::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Coins::setCollectionTarget, NIL, VECTOR2, KtFunctionArgument(VECTOR2, "godot.core.Vector2", "screenPosition"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Coins::playSpawnAnimation, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Coins::_on_area_entered, NIL, OBJECT, KtFunctionArgument(OBJECT, "godot.api.Area2D", "area"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(Coins::moveSpeed, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Coins::collectDistance, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Coins::`value`, INT, LONG, "kotlin.Int", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
