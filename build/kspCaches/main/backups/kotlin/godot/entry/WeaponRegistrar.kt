// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.items.weapons.Weapon
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.RESOURCE_TYPE
import godot.core.VariantParser.BOOL
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
  "game.items.weapons.Weapon.data",
  "game.items.weapons.Weapon._ready,game.items.weapons.Weapon.setupWeapon,game.items.weapons.Weapon.canUseWeapon,game.items.weapons.Weapon._on_range_area_area_entered,game.items.weapons.Weapon._on_range_area_area_exited",
  true,
)
public open class WeaponRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<Weapon>(listOf(), Weapon::class, false, "Node2D", "Weapon", "src/main/java/game/items/weapons/Weapon.java", "gdj/game/items/weapons/Weapon.gdj") {
        constructor(KtConstructor0(::Weapon))
        notificationFunctions(listOf())
        function(Weapon::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::setupWeapon, NIL, OBJECT, KtFunctionArgument(OBJECT, "game.resources.items.weapons.ItemWeapon", "weaponData"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::canUseWeapon, BOOL, KtFunctionArgument(BOOL, "kotlin.Boolean"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::_on_range_area_area_entered, NIL, OBJECT, KtFunctionArgument(OBJECT, "godot.api.Area2D", "area"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Weapon::_on_range_area_area_exited, NIL, OBJECT, KtFunctionArgument(OBJECT, "godot.api.Area2D", "area"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(Weapon::`data`, OBJECT, OBJECT, "game.resources.items.weapons.ItemWeapon", RESOURCE_TYPE, "ItemWeapon", godot.core.PropertyUsageFlags.NONE.flag)
      }
    }
  }
}
