// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.ui.FloatingText
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.VariantParser.COLOR
import godot.core.VariantParser.NIL
import godot.core.VariantParser.STRING
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "FloatingText",
  "Node2D",
  "game.ui.FloatingText",
  "src/main/java/game/ui/FloatingText.java",
  "gdj/game/ui/FloatingText.gdj",
  "project-3",
  "godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "",
  "game.ui.FloatingText.setup",
  true,
)
public open class FloatingTextRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<FloatingText>(listOf(), FloatingText::class, false, "Node2D", "FloatingText", "src/main/java/game/ui/FloatingText.java", "gdj/game/ui/FloatingText.gdj") {
        constructor(KtConstructor0(::FloatingText))
        notificationFunctions(listOf())
        function(FloatingText::setup, NIL, STRING, COLOR, KtFunctionArgument(STRING, "kotlin.String", "value"), KtFunctionArgument(COLOR, "godot.core.Color", "color"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
      }
    }
  }
}
