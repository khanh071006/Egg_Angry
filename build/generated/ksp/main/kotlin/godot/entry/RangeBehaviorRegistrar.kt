// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.items.weapons.range.RangeBehavior
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
  "RangeBehavior",
  "Node2D",
  "game.items.weapons.range.RangeBehavior",
  "src/main/java/game/items/weapons/range/RangeBehavior.java",
  "gdj/game/items/weapons/range/RangeBehavior.gdj",
  "project-3",
  "game.items.weapons.WeaponBehavior,godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.items.weapons.range.RangeBehavior.muzzle,game.items.weapons.range.RangeBehavior.weapon",
  "game.items.weapons.range.RangeBehavior._process,game.items.weapons.range.RangeBehavior.getDamage,game.items.weapons.range.RangeBehavior.executeAttack,game.items.weapons.range.RangeBehavior.onAttackFinished,game.items.weapons.range.RangeBehavior.createProjectile",
  true,
)
public open class RangeBehaviorRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<RangeBehavior>(listOf("WeaponBehavior"), RangeBehavior::class, false, "Node2D", "RangeBehavior", "src/main/java/game/items/weapons/range/RangeBehavior.java", "gdj/game/items/weapons/range/RangeBehavior.gdj") {
        constructor(KtConstructor0(::RangeBehavior))
        notificationFunctions(listOf())
        function(RangeBehavior::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(RangeBehavior::getDamage, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(RangeBehavior::executeAttack, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(RangeBehavior::onAttackFinished, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(RangeBehavior::createProjectile, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(RangeBehavior::muzzle, OBJECT, OBJECT, "godot.api.Marker2D", NODE_TYPE, "Marker2D", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(RangeBehavior::weapon, OBJECT, OBJECT, "game.items.weapons.Weapon", NODE_TYPE, "Weapon", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
