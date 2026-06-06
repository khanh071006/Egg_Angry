// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.entity.enemies.GoblinEffect
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
  "GoblinEffect",
  "Node2D",
  "game.entity.enemies.GoblinEffect",
  "src/main/java/game/entity/enemies/GoblinEffect.java",
  "gdj/game/entity/enemies/GoblinEffect.gdj",
  "project-3",
  "godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "",
  "game.entity.enemies.GoblinEffect._ready,game.entity.enemies.GoblinEffect._on_frame_changed,game.entity.enemies.GoblinEffect._on_animation_finished",
  true,
)
public open class GoblinEffectRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<GoblinEffect>(listOf(), GoblinEffect::class, false, "Node2D", "GoblinEffect", "src/main/java/game/entity/enemies/GoblinEffect.java", "gdj/game/entity/enemies/GoblinEffect.gdj") {
        constructor(KtConstructor0(::GoblinEffect))
        notificationFunctions(listOf())
        function(GoblinEffect::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(GoblinEffect::_on_frame_changed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(GoblinEffect::_on_animation_finished, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
      }
    }
  }
}
