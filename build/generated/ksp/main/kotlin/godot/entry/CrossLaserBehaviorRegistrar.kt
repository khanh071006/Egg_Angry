// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.entity.enemies.CrossLaserBehavior
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NODE_TYPE
import godot.core.PropertyHint.NONE
import godot.core.VariantCaster.FLOAT
import godot.core.VariantParser.DOUBLE
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "CrossLaserBehavior",
  "Node2D",
  "game.entity.enemies.CrossLaserBehavior",
  "src/main/java/game/entity/enemies/CrossLaserBehavior.java",
  "gdj/game/entity/enemies/CrossLaserBehavior.gdj",
  "project-3",
  "godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.entity.enemies.CrossLaserBehavior.enemy,game.entity.enemies.CrossLaserBehavior.laserRange,game.entity.enemies.CrossLaserBehavior.laserWidth,game.entity.enemies.CrossLaserBehavior.triggerRange,game.entity.enemies.CrossLaserBehavior.cooldown,game.entity.enemies.CrossLaserBehavior.prepTime,game.entity.enemies.CrossLaserBehavior.laserDuration,game.entity.enemies.CrossLaserBehavior.playerRadius",
  "game.entity.enemies.CrossLaserBehavior._draw,game.entity.enemies.CrossLaserBehavior._process,game.entity.enemies.CrossLaserBehavior._ready",
  true,
)
public open class CrossLaserBehaviorRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<CrossLaserBehavior>(listOf(), CrossLaserBehavior::class, false, "Node2D", "CrossLaserBehavior", "src/main/java/game/entity/enemies/CrossLaserBehavior.java", "gdj/game/entity/enemies/CrossLaserBehavior.gdj") {
        constructor(KtConstructor0(::CrossLaserBehavior))
        notificationFunctions(listOf())
        function(CrossLaserBehavior::_draw, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(CrossLaserBehavior::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(CrossLaserBehavior::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(CrossLaserBehavior::enemy, OBJECT, OBJECT, "game.entity.enemies.Enemy", NODE_TYPE, "Enemy", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(CrossLaserBehavior::laserRange, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(CrossLaserBehavior::laserWidth, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(CrossLaserBehavior::triggerRange, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(CrossLaserBehavior::cooldown, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(CrossLaserBehavior::prepTime, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(CrossLaserBehavior::laserDuration, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(CrossLaserBehavior::playerRadius, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
