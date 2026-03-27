// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.animation.Trail
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NODE_TYPE
import godot.core.PropertyHint.NONE
import godot.core.VariantCaster.FLOAT
import godot.core.VariantCaster.INT
import godot.core.VariantParser.DOUBLE
import godot.core.VariantParser.LONG
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "Trail",
  "Line2D",
  "game.animation.Trail",
  "src/main/java/game/animation/Trail.java",
  "gdj/game/animation/Trail.gdj",
  "project-3",
  "godot.api.Line2D,godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.animation.Trail.player,game.animation.Trail.trailLength,game.animation.Trail.trailDuration",
  "game.animation.Trail._process,game.animation.Trail._ready,game.animation.Trail._on_trail_timer_timeout",
  true,
)
public open class TrailRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<Trail>(listOf(), Trail::class, false, "Line2D", "Trail", "src/main/java/game/animation/Trail.java", "gdj/game/animation/Trail.gdj") {
        constructor(KtConstructor0(::Trail))
        notificationFunctions(listOf())
        function(Trail::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Trail::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Trail::_on_trail_timer_timeout, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(Trail::player, OBJECT, OBJECT, "godot.api.Node2D", NODE_TYPE, "Node2D", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Trail::trailLength, INT, LONG, "kotlin.Int", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Trail::trailDuration, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
