// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.entity.WeaponContainer
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.VariantParser.NIL
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "WeaponContainer",
  "Node2D",
  "game.entity.WeaponContainer",
  "src/main/java/game/entity/WeaponContainer.java",
  "gdj/game/entity/WeaponContainer.gdj",
  "project-3",
  "godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "",
  "game.entity.WeaponContainer._ready",
  true,
)
public open class WeaponContainerRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<WeaponContainer>(listOf(), WeaponContainer::class, false, "Node2D", "WeaponContainer", "src/main/java/game/entity/WeaponContainer.java", "gdj/game/entity/WeaponContainer.gdj") {
        constructor(KtConstructor0(::WeaponContainer))
        notificationFunctions(listOf())
        function(WeaponContainer::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
      }
    }
  }
}
