// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.ui.MainMenu
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
  "MainMenu",
  "Control",
  "game.ui.MainMenu",
  "src/main/java/game/ui/MainMenu.java",
  "gdj/game/ui/MainMenu.gdj",
  "project-3",
  "godot.api.Control,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "",
  "game.ui.MainMenu._ready,game.ui.MainMenu._on_battle_pressed,game.ui.MainMenu._on_tutorial_pressed,game.ui.MainMenu._on_option_pressed,game.ui.MainMenu._on_close_option_pressed,game.ui.MainMenu._on_credits_pressed,game.ui.MainMenu._on_quit_pressed,game.ui.MainMenu._on_battle_mouse_entered,game.ui.MainMenu._on_battle_mouse_exited,game.ui.MainMenu._on_tutorial_mouse_entered,game.ui.MainMenu._on_tutorial_mouse_exited,game.ui.MainMenu._on_option_mouse_entered,game.ui.MainMenu._on_option_mouse_exited,game.ui.MainMenu._on_credits_mouse_entered,game.ui.MainMenu._on_credits_mouse_exited,game.ui.MainMenu._on_quit_mouse_entered,game.ui.MainMenu._on_quit_mouse_exited",
  true,
)
public open class MainMenuRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<MainMenu>(listOf(), MainMenu::class, false, "Control", "MainMenu", "src/main/java/game/ui/MainMenu.java", "gdj/game/ui/MainMenu.gdj") {
        constructor(KtConstructor0(::MainMenu))
        notificationFunctions(listOf())
        function(MainMenu::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MainMenu::_on_battle_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MainMenu::_on_tutorial_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MainMenu::_on_option_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MainMenu::_on_close_option_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MainMenu::_on_credits_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MainMenu::_on_quit_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MainMenu::_on_battle_mouse_entered, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MainMenu::_on_battle_mouse_exited, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MainMenu::_on_tutorial_mouse_entered, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MainMenu::_on_tutorial_mouse_exited, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MainMenu::_on_option_mouse_entered, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MainMenu::_on_option_mouse_exited, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MainMenu::_on_credits_mouse_entered, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MainMenu::_on_credits_mouse_exited, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MainMenu::_on_quit_mouse_entered, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(MainMenu::_on_quit_mouse_exited, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
      }
    }
  }
}
