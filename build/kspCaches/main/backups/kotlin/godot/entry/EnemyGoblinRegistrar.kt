// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.entity.enemies.EnemyGoblin
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
import godot.core.VariantParser.VECTOR2
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "EnemyGoblin",
  "Area2D",
  "game.entity.enemies.EnemyGoblin",
  "src/main/java/game/entity/enemies/EnemyGoblin.java",
  "gdj/game/entity/enemies/EnemyGoblin.gdj",
  "project-3",
  "game.entity.enemies.Enemy,game.entity.BaseUnit,godot.api.Area2D,godot.api.CollisionObject2D,godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.entity.enemies.EnemyGoblin.flockPush,game.entity.enemies.EnemyGoblin.knockbackTimer,game.entity.enemies.EnemyGoblin.knockbackImmunityTime,game.entity.enemies.EnemyGoblin.stats",
  "game.entity.enemies.EnemyGoblin._physicsProcess,game.entity.enemies.EnemyGoblin._ready,game.entity.enemies.EnemyGoblin.setFlashMaterial,game.entity.enemies.EnemyGoblin._on_hurtbox_component_on_damage,game.entity.enemies.EnemyGoblin._on_flash_timer_timeout,game.entity.enemies.EnemyGoblin.applyKnockback,game.entity.enemies.EnemyGoblin.resetKnockback,game.entity.enemies.EnemyGoblin._on_knockback_timer_timeout,game.entity.enemies.EnemyGoblin.destroyEnemy,game.entity.enemies.EnemyGoblin._on_death_timer_timeout,game.entity.enemies.EnemyGoblin._on_health_component_on_unit_die",
  true,
)
public open class EnemyGoblinRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<EnemyGoblin>(listOf("Enemy","BaseUnit"), EnemyGoblin::class, false, "Area2D", "EnemyGoblin", "src/main/java/game/entity/enemies/EnemyGoblin.java", "gdj/game/entity/enemies/EnemyGoblin.gdj") {
        constructor(KtConstructor0(::EnemyGoblin))
        notificationFunctions(listOf())
        function(EnemyGoblin::_physicsProcess, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(EnemyGoblin::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(EnemyGoblin::setFlashMaterial, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(EnemyGoblin::_on_hurtbox_component_on_damage, NIL, OBJECT, KtFunctionArgument(OBJECT, "game.components.HitBoxComponent", "hitbox"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(EnemyGoblin::_on_flash_timer_timeout, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(EnemyGoblin::applyKnockback, NIL, VECTOR2, DOUBLE, KtFunctionArgument(VECTOR2, "godot.core.Vector2", "direction"), KtFunctionArgument(DOUBLE, "kotlin.Double", "power"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(EnemyGoblin::resetKnockback, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(EnemyGoblin::_on_knockback_timer_timeout, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(EnemyGoblin::destroyEnemy, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(EnemyGoblin::_on_death_timer_timeout, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(EnemyGoblin::_on_health_component_on_unit_die, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(EnemyGoblin::flockPush, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(EnemyGoblin::knockbackTimer, OBJECT, OBJECT, "godot.api.Timer", NODE_TYPE, "Timer", godot.core.PropertyUsageFlags.NONE.flag)
        property(EnemyGoblin::knockbackImmunityTime, DOUBLE, DOUBLE, "kotlin.Double", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(EnemyGoblin::stats, OBJECT, OBJECT, "game.resources.units.UnitStats", RESOURCE_TYPE, "UnitStats", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
