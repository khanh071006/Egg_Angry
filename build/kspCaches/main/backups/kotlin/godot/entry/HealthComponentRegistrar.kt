// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.components.HealthComponent
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.VariantCaster.FLOAT
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "HealthComponent",
  "Node",
  "game.components.HealthComponent",
  "src/main/java/game/components/HealthComponent.java",
  "gdj/game/components/HealthComponent.gdj",
  "project-3",
  "godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "game.components.HealthComponent.onUnitHit,game.components.HealthComponent.onUnitDie,game.components.HealthComponent.onHealthChanged",
  "",
  "game.components.HealthComponent.setup,game.components.HealthComponent.takeDamage,game.components.HealthComponent.Die,game.components.HealthComponent.heal",
  true,
)
public open class HealthComponentRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<HealthComponent>(listOf(), HealthComponent::class, false, "Node", "HealthComponent", "src/main/java/game/components/HealthComponent.java", "gdj/game/components/HealthComponent.gdj") {
        constructor(KtConstructor0(::HealthComponent))
        notificationFunctions(listOf())
        function(HealthComponent::setup, NIL, OBJECT, KtFunctionArgument(OBJECT, "game.resources.UnitStats", "stats"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(HealthComponent::takeDamage, NIL, FLOAT, KtFunctionArgument(FLOAT, "kotlin.Float", "value"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(HealthComponent::Die, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(HealthComponent::heal, NIL, FLOAT, KtFunctionArgument(FLOAT, "kotlin.Float", "amount"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        signal(HealthComponent::onUnitHit)
        signal(HealthComponent::onUnitDie)
        signal(HealthComponent::onHealthChanged, KtFunctionArgument(FLOAT, "kotlin.Float", "p0"), KtFunctionArgument(FLOAT, "kotlin.Float", "p1"))
      }
    }
  }
}
