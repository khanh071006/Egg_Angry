// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.entity.Enemy
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
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
  "Enemy",
  "Area2D",
  "game.entity.Enemy",
  "src/main/java/game/entity/Enemy.java",
  "gdj/game/entity/Enemy.gdj",
  "project-3",
  "game.entity.BaseUnit,godot.api.Area2D,godot.api.CollisionObject2D,godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.entity.Enemy.stats,game.entity.Enemy.flockPush",
  "game.entity.Enemy._physicsProcess,game.entity.Enemy._ready",
  true,
)
public open class EnemyRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<Enemy>(listOf("BaseUnit"), Enemy::class, false, "Area2D", "Enemy", "src/main/java/game/entity/Enemy.java", "gdj/game/entity/Enemy.gdj") {
        constructor(KtConstructor0(::Enemy))
        notificationFunctions(listOf())
        function(Enemy::_physicsProcess, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Enemy::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(Enemy::stats, OBJECT, OBJECT, "game.resources.EnemyStats", RESOURCE_TYPE, "EnemyStats", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Enemy::flockPush, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
