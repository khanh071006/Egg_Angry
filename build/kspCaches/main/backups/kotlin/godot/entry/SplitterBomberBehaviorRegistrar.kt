// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.entity.enemies.SplitterBomberBehavior
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NODE_TYPE
import godot.core.PropertyHint.NONE
import godot.core.PropertyHint.RESOURCE_TYPE
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
  "SplitterBomberBehavior",
  "Node2D",
  "game.entity.enemies.SplitterBomberBehavior",
  "src/main/java/game/entity/enemies/SplitterBomberBehavior.java",
  "gdj/game/entity/enemies/SplitterBomberBehavior.gdj",
  "project-3",
  "godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.entity.enemies.SplitterBomberBehavior.enemy,game.entity.enemies.SplitterBomberBehavior.projectileScene,game.entity.enemies.SplitterBomberBehavior.triggerRange,game.entity.enemies.SplitterBomberBehavior.fuseTime,game.entity.enemies.SplitterBomberBehavior.explosionRadius,game.entity.enemies.SplitterBomberBehavior.explosionDamage,game.entity.enemies.SplitterBomberBehavior.projectileSpeed,game.entity.enemies.SplitterBomberBehavior.projectileDamage,game.entity.enemies.SplitterBomberBehavior.cooldown",
  "game.entity.enemies.SplitterBomberBehavior._process,game.entity.enemies.SplitterBomberBehavior._ready",
  true,
)
public open class SplitterBomberBehaviorRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<SplitterBomberBehavior>(listOf(), SplitterBomberBehavior::class, false, "Node2D", "SplitterBomberBehavior", "src/main/java/game/entity/enemies/SplitterBomberBehavior.java", "gdj/game/entity/enemies/SplitterBomberBehavior.gdj") {
        constructor(KtConstructor0(::SplitterBomberBehavior))
        notificationFunctions(listOf())
        function(SplitterBomberBehavior::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(SplitterBomberBehavior::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(SplitterBomberBehavior::enemy, OBJECT, OBJECT, "game.entity.enemies.Enemy", NODE_TYPE, "Enemy", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SplitterBomberBehavior::projectileScene, OBJECT, OBJECT, "godot.api.PackedScene", RESOURCE_TYPE, "PackedScene", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SplitterBomberBehavior::triggerRange, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SplitterBomberBehavior::fuseTime, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SplitterBomberBehavior::explosionRadius, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SplitterBomberBehavior::explosionDamage, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SplitterBomberBehavior::projectileSpeed, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SplitterBomberBehavior::projectileDamage, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SplitterBomberBehavior::cooldown, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
