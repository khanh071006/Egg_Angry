// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.components.HurtBoxComponent
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "HurtBoxComponent",
  "Area2D",
  "game.components.HurtBoxComponent",
  "src/main/java/game/components/HurtBoxComponent.java",
  "gdj/game/components/HurtBoxComponent.gdj",
  "project-3",
  "godot.api.Area2D,godot.api.CollisionObject2D,godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "game.components.HurtBoxComponent.onDamage",
  "",
  "game.components.HurtBoxComponent._on_area_entered",
  true,
)
public open class HurtBoxComponentRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<HurtBoxComponent>(listOf(), HurtBoxComponent::class, false, "Area2D", "HurtBoxComponent", "src/main/java/game/components/HurtBoxComponent.java", "gdj/game/components/HurtBoxComponent.gdj") {
        constructor(KtConstructor0(::HurtBoxComponent))
        notificationFunctions(listOf())
        function(HurtBoxComponent::_on_area_entered, NIL, OBJECT, KtFunctionArgument(OBJECT, "godot.api.Area2D", "area"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        signal(HurtBoxComponent::onDamage, KtFunctionArgument(OBJECT, "game.components.HitBoxComponent", "p0"))
      }
    }
  }
}
