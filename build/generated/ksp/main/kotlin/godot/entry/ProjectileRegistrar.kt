// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.items.weapons.projectiles.Projectile
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NODE_TYPE
import godot.core.VariantCaster.FLOAT
import godot.core.VariantParser.BOOL
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
  "Projectile",
  "Node2D",
  "game.items.weapons.projectiles.Projectile",
  "src/main/java/game/items/weapons/projectiles/Projectile.java",
  "gdj/game/items/weapons/projectiles/Projectile.gdj",
  "project-3",
  "godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.items.weapons.projectiles.Projectile.hitbox",
  "game.items.weapons.projectiles.Projectile._process,game.items.weapons.projectiles.Projectile.setProjectile,game.items.weapons.projectiles.Projectile._on_visible_on_screen_enabler_2d_screen_exited,game.items.weapons.projectiles.Projectile._on_hitbox_component_on_hit_hurtbox",
  true,
)
public open class ProjectileRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<Projectile>(listOf(), Projectile::class, false, "Node2D", "Projectile", "src/main/java/game/items/weapons/projectiles/Projectile.java", "gdj/game/items/weapons/projectiles/Projectile.gdj") {
        constructor(KtConstructor0(::Projectile))
        notificationFunctions(listOf())
        function(Projectile::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Projectile::setProjectile, NIL, VECTOR2, DOUBLE, BOOL, FLOAT, OBJECT, KtFunctionArgument(VECTOR2, "godot.core.Vector2", "velocity"), KtFunctionArgument(DOUBLE, "kotlin.Double", "damage"), KtFunctionArgument(BOOL, "kotlin.Boolean", "critical"), KtFunctionArgument(FLOAT, "kotlin.Float", "knockback"), KtFunctionArgument(OBJECT, "godot.api.Node2D", "unit"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Projectile::_on_visible_on_screen_enabler_2d_screen_exited, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Projectile::_on_hitbox_component_on_hit_hurtbox, NIL, OBJECT, KtFunctionArgument(OBJECT, "godot.api.Area2D", "hurtbox"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(Projectile::hitbox, OBJECT, OBJECT, "game.components.HitBoxComponent", NODE_TYPE, "HitBoxComponent", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
