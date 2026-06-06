// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.entity.PlayerChrono
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
  "PlayerChrono",
  "Area2D",
  "game.entity.PlayerChrono",
  "src/main/java/game/entity/PlayerChrono.java",
  "gdj/game/entity/PlayerChrono.gdj",
  "project-3",
  "game.entity.Player,game.entity.BaseUnit,godot.api.Area2D,godot.api.CollisionObject2D,godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.entity.PlayerChrono.bombScene,game.entity.PlayerChrono.dashDuration,game.entity.PlayerChrono.dashSpeedMulti,game.entity.PlayerChrono.dashCooldown,game.entity.PlayerChrono.stats",
  "game.entity.PlayerChrono._process,game.entity.PlayerChrono._ready,game.entity.PlayerChrono.setFlashMaterial,game.entity.PlayerChrono._on_hurtbox_component_on_damage,game.entity.PlayerChrono._on_flash_timer_timeout,game.entity.PlayerChrono._on_dash_timer_timeout,game.entity.PlayerChrono.addWeapon",
  true,
)
public open class PlayerChronoRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<PlayerChrono>(listOf("Player","BaseUnit"), PlayerChrono::class, false, "Area2D", "PlayerChrono", "src/main/java/game/entity/PlayerChrono.java", "gdj/game/entity/PlayerChrono.gdj") {
        constructor(KtConstructor0(::PlayerChrono))
        notificationFunctions(listOf())
        function(PlayerChrono::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(PlayerChrono::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(PlayerChrono::setFlashMaterial, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(PlayerChrono::_on_hurtbox_component_on_damage, NIL, OBJECT, KtFunctionArgument(OBJECT, "game.components.HitBoxComponent", "hitbox"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(PlayerChrono::_on_flash_timer_timeout, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(PlayerChrono::_on_dash_timer_timeout, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(PlayerChrono::addWeapon, NIL, OBJECT, KtFunctionArgument(OBJECT, "game.resources.items.weapons.ItemWeapon", "data"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(PlayerChrono::bombScene, OBJECT, OBJECT, "godot.api.PackedScene", RESOURCE_TYPE, "PackedScene", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(PlayerChrono::dashDuration, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(PlayerChrono::dashSpeedMulti, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(PlayerChrono::dashCooldown, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(PlayerChrono::stats, OBJECT, OBJECT, "game.resources.units.UnitStats", RESOURCE_TYPE, "UnitStats", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
