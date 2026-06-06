// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.ui.TutorialMenu
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
  "TutorialMenu",
  "Control",
  "game.ui.TutorialMenu",
  "src/main/java/game/ui/TutorialMenu.java",
  "gdj/game/ui/TutorialMenu.gdj",
  "project-3",
  "godot.api.Control,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "",
  "game.ui.TutorialMenu._ready,game.ui.TutorialMenu._on_back_pressed",
  true,
)
public open class TutorialMenuRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<TutorialMenu>(listOf(), TutorialMenu::class, false, "Control", "TutorialMenu", "src/main/java/game/ui/TutorialMenu.java", "gdj/game/ui/TutorialMenu.gdj") {
        constructor(KtConstructor0(::TutorialMenu))
        notificationFunctions(listOf())
        function(TutorialMenu::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(TutorialMenu::_on_back_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
      }
    }
  }
}
