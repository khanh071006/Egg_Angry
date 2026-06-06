// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.entity.enemies.SlamBehavior
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
  "SlamBehavior",
  "Node2D",
  "game.entity.enemies.SlamBehavior",
  "src/main/java/game/entity/enemies/SlamBehavior.java",
  "gdj/game/entity/enemies/SlamBehavior.gdj",
  "project-3",
  "godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.entity.enemies.SlamBehavior.enemy,game.entity.enemies.SlamBehavior.slamHitbox,game.entity.enemies.SlamBehavior.slamRange,game.entity.enemies.SlamBehavior.cooldown,game.entity.enemies.SlamBehavior.prepTime,game.entity.enemies.SlamBehavior.damage,game.entity.enemies.SlamBehavior.knockbackPower",
  "game.entity.enemies.SlamBehavior._draw,game.entity.enemies.SlamBehavior._process,game.entity.enemies.SlamBehavior._ready",
  true,
)
public open class SlamBehaviorRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<SlamBehavior>(listOf(), SlamBehavior::class, false, "Node2D", "SlamBehavior", "src/main/java/game/entity/enemies/SlamBehavior.java", "gdj/game/entity/enemies/SlamBehavior.gdj") {
        constructor(KtConstructor0(::SlamBehavior))
        notificationFunctions(listOf())
        function(SlamBehavior::_draw, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(SlamBehavior::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(SlamBehavior::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(SlamBehavior::enemy, OBJECT, OBJECT, "game.entity.enemies.Enemy", NODE_TYPE, "Enemy", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SlamBehavior::slamHitbox, OBJECT, OBJECT, "game.components.HitBoxComponent", NODE_TYPE, "HitBoxComponent", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SlamBehavior::slamRange, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SlamBehavior::cooldown, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SlamBehavior::prepTime, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SlamBehavior::damage, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SlamBehavior::knockbackPower, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
