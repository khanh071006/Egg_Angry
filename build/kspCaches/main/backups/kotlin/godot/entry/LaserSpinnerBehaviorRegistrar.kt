// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.entity.enemies.LaserSpinnerBehavior
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
  "LaserSpinnerBehavior",
  "Node2D",
  "game.entity.enemies.LaserSpinnerBehavior",
  "src/main/java/game/entity/enemies/LaserSpinnerBehavior.java",
  "gdj/game/entity/enemies/LaserSpinnerBehavior.gdj",
  "project-3",
  "godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.entity.enemies.LaserSpinnerBehavior.enemy,game.entity.enemies.LaserSpinnerBehavior.laserHitbox,game.entity.enemies.LaserSpinnerBehavior.laserRange,game.entity.enemies.LaserSpinnerBehavior.damage,game.entity.enemies.LaserSpinnerBehavior.cooldown,game.entity.enemies.LaserSpinnerBehavior.prepTime,game.entity.enemies.LaserSpinnerBehavior.laserDuration,game.entity.enemies.LaserSpinnerBehavior.rotationSpeed,game.entity.enemies.LaserSpinnerBehavior.laserCount,game.entity.enemies.LaserSpinnerBehavior.playerRadius,game.entity.enemies.LaserSpinnerBehavior.tickRate,game.entity.enemies.LaserSpinnerBehavior.lightningDamage,game.entity.enemies.LaserSpinnerBehavior.lightningInterval,game.entity.enemies.LaserSpinnerBehavior.lightningPrepTime,game.entity.enemies.LaserSpinnerBehavior.lightningRadius",
  "game.entity.enemies.LaserSpinnerBehavior._draw,game.entity.enemies.LaserSpinnerBehavior._process,game.entity.enemies.LaserSpinnerBehavior._ready",
  true,
)
public open class LaserSpinnerBehaviorRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<LaserSpinnerBehavior>(listOf(), LaserSpinnerBehavior::class, false, "Node2D", "LaserSpinnerBehavior", "src/main/java/game/entity/enemies/LaserSpinnerBehavior.java", "gdj/game/entity/enemies/LaserSpinnerBehavior.gdj") {
        constructor(KtConstructor0(::LaserSpinnerBehavior))
        notificationFunctions(listOf())
        function(LaserSpinnerBehavior::_draw, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(LaserSpinnerBehavior::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(LaserSpinnerBehavior::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(LaserSpinnerBehavior::enemy, OBJECT, OBJECT, "game.entity.enemies.Enemy", NODE_TYPE, "Enemy", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(LaserSpinnerBehavior::laserHitbox, OBJECT, OBJECT, "game.components.HitBoxComponent", NODE_TYPE, "HitBoxComponent", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(LaserSpinnerBehavior::laserRange, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(LaserSpinnerBehavior::damage, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(LaserSpinnerBehavior::cooldown, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(LaserSpinnerBehavior::prepTime, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(LaserSpinnerBehavior::laserDuration, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(LaserSpinnerBehavior::rotationSpeed, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(LaserSpinnerBehavior::laserCount, INT, LONG, "kotlin.Int", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(LaserSpinnerBehavior::playerRadius, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(LaserSpinnerBehavior::tickRate, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(LaserSpinnerBehavior::lightningDamage, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(LaserSpinnerBehavior::lightningInterval, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(LaserSpinnerBehavior::lightningPrepTime, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(LaserSpinnerBehavior::lightningRadius, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
