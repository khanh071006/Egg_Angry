// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.items.weapons.Weapon
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NODE_TYPE
import godot.core.PropertyHint.RESOURCE_TYPE
import godot.core.VariantParser.BOOL
import godot.core.VariantParser.DOUBLE
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "Weapon",
  "Node2D",
  "game.items.weapons.Weapon",
  "src/main/java/game/items/weapons/Weapon.java",
  "gdj/game/items/weapons/Weapon.gdj",
  "project-3",
  "godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.items.weapons.Weapon.weaponBehavior,game.items.weapons.Weapon.data",
  "game.items.weapons.Weapon._process,game.items.weapons.Weapon._ready,game.items.weapons.Weapon.setupWeapon,game.items.weapons.Weapon.canUseWeapon,game.items.weapons.Weapon._on_range_area_area_entered,game.items.weapons.Weapon._on_range_area_area_exited,game.items.weapons.Weapon.updateClosestTarget,game.items.weapons.Weapon.getClosestTarget,game.items.weapons.Weapon.getIdleRotation,game.items.weapons.Weapon.calculateSpread,game.items.weapons.Weapon.getRotationToTarget,game.items.weapons.Weapon.getCustomRotationToTarget,game.items.weapons.Weapon.rotateToTarget,game.items.weapons.Weapon.useWeapon,game.items.weapons.Weapon.updateVisuals",
  true,
)
public open class WeaponRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<Weapon>(listOf(), Weapon::class, false, "Node2D", "Weapon", "src/main/java/game/items/weapons/Weapon.java", "gdj/game/items/weapons/Weapon.gdj") {
        constructor(KtConstructor0(::Weapon))
        notificationFunctions(listOf())
        function(Weapon::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::setupWeapon, NIL, OBJECT, KtFunctionArgument(OBJECT, "game.resources.items.weapons.ItemWeapon", "weaponData"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::canUseWeapon, BOOL, KtFunctionArgument(BOOL, "kotlin.Boolean"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::_on_range_area_area_entered, NIL, OBJECT, KtFunctionArgument(OBJECT, "godot.api.Area2D", "area"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::_on_range_area_area_exited, NIL, OBJECT, KtFunctionArgument(OBJECT, "godot.api.Area2D", "area"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::updateClosestTarget, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::getClosestTarget, OBJECT, KtFunctionArgument(OBJECT, "godot.api.Node2D"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::getIdleRotation, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::calculateSpread, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::getRotationToTarget, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::getCustomRotationToTarget, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::rotateToTarget, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::useWeapon, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::updateVisuals, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(Weapon::weaponBehavior, OBJECT, OBJECT, "godot.api.Node2D", NODE_TYPE, "Node2D", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Weapon::`data`, OBJECT, OBJECT, "game.resources.items.weapons.ItemWeapon", RESOURCE_TYPE, "ItemWeapon", godot.core.PropertyUsageFlags.NONE.flag)
      }
    }
  }
}
