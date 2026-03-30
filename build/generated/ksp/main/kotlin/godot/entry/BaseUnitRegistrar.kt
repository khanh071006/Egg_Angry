// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.entity.BaseUnit
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.RESOURCE_TYPE
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "BaseUnit",
  "Area2D",
  "game.entity.BaseUnit",
  "src/main/java/game/entity/BaseUnit.java",
  "gdj/game/entity/BaseUnit.gdj",
  "project-3",
  "godot.api.Area2D,godot.api.CollisionObject2D,godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.entity.BaseUnit.stats",
  "game.entity.BaseUnit._ready,game.entity.BaseUnit._on_hurtbox_component_on_damage",
  true,
)
public open class BaseUnitRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<BaseUnit>(listOf(), BaseUnit::class, false, "Area2D", "BaseUnit", "src/main/java/game/entity/BaseUnit.java", "gdj/game/entity/BaseUnit.gdj") {
        constructor(KtConstructor0(::BaseUnit))
        notificationFunctions(listOf())
        function(BaseUnit::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(BaseUnit::_on_hurtbox_component_on_damage, NIL, OBJECT, KtFunctionArgument(OBJECT, "game.components.HitBoxComponent", "hitbox"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(BaseUnit::stats, OBJECT, OBJECT, "game.resources.UnitStats", RESOURCE_TYPE, "UnitStats", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
