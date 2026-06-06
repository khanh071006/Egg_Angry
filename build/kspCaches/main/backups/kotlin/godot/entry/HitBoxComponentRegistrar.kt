// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.components.HitBoxComponent
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NONE
import godot.core.VariantCaster.FLOAT
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
  "HitBoxComponent",
  "Area2D",
  "game.components.HitBoxComponent",
  "src/main/java/game/components/HitBoxComponent.java",
  "gdj/game/components/HitBoxComponent.gdj",
  "project-3",
  "godot.api.Area2D,godot.api.CollisionObject2D,godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "game.components.HitBoxComponent.onHitHurtbox",
  "game.components.HitBoxComponent.damage,game.components.HitBoxComponent.critical,game.components.HitBoxComponent.knockbackPower",
  "game.components.HitBoxComponent.enable,game.components.HitBoxComponent.disable,game.components.HitBoxComponent.setup,game.components.HitBoxComponent._on_area_entered",
  true,
)
public open class HitBoxComponentRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<HitBoxComponent>(listOf(), HitBoxComponent::class, false, "Area2D", "HitBoxComponent", "src/main/java/game/components/HitBoxComponent.java", "gdj/game/components/HitBoxComponent.gdj") {
        constructor(KtConstructor0(::HitBoxComponent))
        notificationFunctions(listOf())
        function(HitBoxComponent::enable, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(HitBoxComponent::disable, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(HitBoxComponent::setup, NIL, FLOAT, BOOL, FLOAT, OBJECT, KtFunctionArgument(FLOAT, "kotlin.Float", "damage"), KtFunctionArgument(BOOL, "kotlin.Boolean", "critical"), KtFunctionArgument(FLOAT, "kotlin.Float", "knockback"), KtFunctionArgument(OBJECT, "godot.api.Node2D", "source"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(HitBoxComponent::_on_area_entered, NIL, OBJECT, KtFunctionArgument(OBJECT, "godot.api.Area2D", "area"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        signal(HitBoxComponent::onHitHurtbox, KtFunctionArgument(OBJECT, "game.components.HurtBoxComponent", "p0"))
        property(HitBoxComponent::damage, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.NONE.flag)
        property(HitBoxComponent::critical, BOOL, BOOL, "kotlin.Boolean", NONE, "", godot.core.PropertyUsageFlags.NONE.flag)
        property(HitBoxComponent::knockbackPower, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.NONE.flag)
      }
    }
  }
}
