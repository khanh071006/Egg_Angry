// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.items.weapons.WeaponBehavior
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
  "WeaponBehavior",
  "Node2D",
  "game.items.weapons.WeaponBehavior",
  "src/main/java/game/items/weapons/WeaponBehavior.java",
  "gdj/game/items/weapons/WeaponBehavior.gdj",
  "project-3",
  "godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.items.weapons.WeaponBehavior.weapon",
  "game.items.weapons.WeaponBehavior.getDamage,game.items.weapons.WeaponBehavior.executeAttack",
  true,
)
public open class WeaponBehaviorRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<WeaponBehavior>(listOf(), WeaponBehavior::class, false, "Node2D", "WeaponBehavior", "src/main/java/game/items/weapons/WeaponBehavior.java", "gdj/game/items/weapons/WeaponBehavior.gdj") {
        constructor(KtConstructor0(::WeaponBehavior))
        notificationFunctions(listOf())
        function(WeaponBehavior::getDamage, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(WeaponBehavior::executeAttack, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(WeaponBehavior::weapon, OBJECT, OBJECT, "game.items.weapons.Weapon", NODE_TYPE, "Weapon", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
