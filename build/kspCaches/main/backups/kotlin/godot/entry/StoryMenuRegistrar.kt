// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.ui.StoryMenu
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.VariantParser.DOUBLE
import godot.core.VariantParser.NIL
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "StoryMenu",
  "Control",
  "game.ui.StoryMenu",
  "src/main/java/game/ui/StoryMenu.java",
  "gdj/game/ui/StoryMenu.gdj",
  "project-3",
  "godot.api.Control,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "",
  "game.ui.StoryMenu._process,game.ui.StoryMenu._ready,game.ui.StoryMenu._on_back_pressed,game.ui.StoryMenu._on_next_pressed,game.ui.StoryMenu._on_prev_pressed,game.ui.StoryMenu._on_player_next_pressed,game.ui.StoryMenu._on_enemy_prev_pressed,game.ui.StoryMenu._on_enemy_next_pressed,game.ui.StoryMenu._on_wave_prev_pressed,game.ui.StoryMenu._on_play_pressed,game.ui.StoryMenu._on_back_mouse_entered,game.ui.StoryMenu._on_back_mouse_exited,game.ui.StoryMenu._on_next_mouse_entered,game.ui.StoryMenu._on_next_mouse_exited,game.ui.StoryMenu._on_prev_mouse_entered,game.ui.StoryMenu._on_prev_mouse_exited,game.ui.StoryMenu._on_player_next_mouse_entered,game.ui.StoryMenu._on_player_next_mouse_exited,game.ui.StoryMenu._on_enemy_prev_mouse_entered,game.ui.StoryMenu._on_enemy_prev_mouse_exited,game.ui.StoryMenu._on_enemy_next_mouse_entered,game.ui.StoryMenu._on_enemy_next_mouse_exited,game.ui.StoryMenu._on_wave_prev_mouse_entered,game.ui.StoryMenu._on_wave_prev_mouse_exited,game.ui.StoryMenu._on_play_mouse_entered,game.ui.StoryMenu._on_play_mouse_exited",
  true,
)
public open class StoryMenuRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<StoryMenu>(listOf(), StoryMenu::class, false, "Control", "StoryMenu", "src/main/java/game/ui/StoryMenu.java", "gdj/game/ui/StoryMenu.gdj") {
        constructor(KtConstructor0(::StoryMenu))
        notificationFunctions(listOf())
        function(StoryMenu::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_back_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_next_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_prev_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_player_next_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_enemy_prev_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_enemy_next_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_wave_prev_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_play_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_back_mouse_entered, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_back_mouse_exited, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_next_mouse_entered, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_next_mouse_exited, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_prev_mouse_entered, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_prev_mouse_exited, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_player_next_mouse_entered, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_player_next_mouse_exited, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_enemy_prev_mouse_entered, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_enemy_prev_mouse_exited, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_enemy_next_mouse_entered, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_enemy_next_mouse_exited, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_wave_prev_mouse_entered, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_wave_prev_mouse_exited, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_play_mouse_entered, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StoryMenu::_on_play_mouse_exited, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
      }
    }
  }
}
