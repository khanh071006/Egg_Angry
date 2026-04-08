// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.items.weapons.melee.MeleeBehavior
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NODE_TYPE
import godot.core.VariantParser.DOUBLE
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "MeleeBehavior",
  "Node2D",
  "game.items.weapons.melee.MeleeBehavior",
  "src/main/java/game/items/weapons/melee/MeleeBehavior.java",
  "gdj/game/items/weapons/melee/MeleeBehavior.gdj",
  "project-3",
  "game.items.weapons.WeaponBehavior,godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.items.weapons.melee.MeleeBehavior.hitbox,game.items.weapons.melee.MeleeBehavior.weapon",
  "game.items.weapons.melee.MeleeBehavior._process,game.items.weapons.melee.MeleeBehavior.getDamage,game.items.weapons.melee.MeleeBehavior.executeAttack,game.items.weapons.melee.MeleeBehavior.onAttackFinished",
  true,
)
public open class MeleeBehaviorRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<MeleeBehavior>(listOf("WeaponBehavior"), MeleeBehavior::class, false, "Node2D", "MeleeBehavior", "src/main/java/game/items/weapons/melee/MeleeBehavior.java", "gdj/game/items/weapons/melee/MeleeBehavior.gdj") {
        constructor(KtConstructor0(::MeleeBehavior))
        notificationFunctions(listOf())
        function(MeleeBehavior::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MeleeBehavior::getDamage, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MeleeBehavior::executeAttack, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MeleeBehavior::onAttackFinished, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(MeleeBehavior::hitbox, OBJECT, OBJECT, "game.components.HitBoxComponent", NODE_TYPE, "HitBoxComponent", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(MeleeBehavior::weapon, OBJECT, OBJECT, "game.items.weapons.Weapon", NODE_TYPE, "Weapon", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
