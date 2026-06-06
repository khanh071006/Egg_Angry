// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.entity.enemies.behaviors.ShootingBehavior
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NODE_TYPE
import godot.core.PropertyHint.NONE
import godot.core.PropertyHint.RESOURCE_TYPE
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
  "ShootingBehavior",
  "Node2D",
  "game.entity.enemies.behaviors.ShootingBehavior",
  "src/main/java/game/entity/enemies/behaviors/ShootingBehavior.java",
  "gdj/game/entity/enemies/behaviors/ShootingBehavior.gdj",
  "project-3",
  "godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.entity.enemies.behaviors.ShootingBehavior.enemy,game.entity.enemies.behaviors.ShootingBehavior.firePosition,game.entity.enemies.behaviors.ShootingBehavior.projectileScene,game.entity.enemies.behaviors.ShootingBehavior.cooldown,game.entity.enemies.behaviors.ShootingBehavior.projectileCount,game.entity.enemies.behaviors.ShootingBehavior.arcAngle,game.entity.enemies.behaviors.ShootingBehavior.projectileSpeed",
  "game.entity.enemies.behaviors.ShootingBehavior._process,game.entity.enemies.behaviors.ShootingBehavior._ready",
  true,
)
public open class ShootingBehaviorRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<ShootingBehavior>(listOf(), ShootingBehavior::class, false, "Node2D", "ShootingBehavior", "src/main/java/game/entity/enemies/behaviors/ShootingBehavior.java", "gdj/game/entity/enemies/behaviors/ShootingBehavior.gdj") {
        constructor(KtConstructor0(::ShootingBehavior))
        notificationFunctions(listOf())
        function(ShootingBehavior::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(ShootingBehavior::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(ShootingBehavior::enemy, OBJECT, OBJECT, "game.entity.enemies.core.Enemy", NODE_TYPE, "Enemy", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ShootingBehavior::firePosition, OBJECT, OBJECT, "godot.api.Marker2D", NODE_TYPE, "Marker2D", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ShootingBehavior::projectileScene, OBJECT, OBJECT, "godot.api.PackedScene", RESOURCE_TYPE, "PackedScene", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ShootingBehavior::cooldown, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ShootingBehavior::projectileCount, INT, LONG, "kotlin.Int", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ShootingBehavior::arcAngle, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ShootingBehavior::projectileSpeed, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
