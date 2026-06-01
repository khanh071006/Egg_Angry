// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.ui.SelectionPanel
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.RESOURCE_TYPE
import godot.core.PropertyHint.TYPE_STRING
import godot.core.VariantCaster.INT
import godot.core.VariantParser.ARRAY
import godot.core.VariantParser.BOOL
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "SelectionPanel",
  "Node",
  "game.ui.SelectionPanel",
  "src/main/java/game/ui/SelectionPanel.java",
  "gdj/game/ui/SelectionPanel.gdj",
  "project-3",
  "godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.ui.SelectionPanel.players,game.ui.SelectionPanel.startWeapons,game.ui.SelectionPanel.selectionCardScene",
  "game.ui.SelectionPanel._ready,game.ui.SelectionPanel.showPlayerInfo,game.ui.SelectionPanel.loadPlayers,game.ui.SelectionPanel.on_player_selected_index,game.ui.SelectionPanel.onPlayerSelected",
  true,
)
public open class SelectionPanelRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<SelectionPanel>(listOf(), SelectionPanel::class, false, "Node", "SelectionPanel", "src/main/java/game/ui/SelectionPanel.java", "gdj/game/ui/SelectionPanel.gdj") {
        constructor(KtConstructor0(::SelectionPanel))
        notificationFunctions(listOf())
        function(SelectionPanel::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(SelectionPanel::showPlayerInfo, NIL, BOOL, KtFunctionArgument(BOOL, "kotlin.Boolean", "visible"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(SelectionPanel::loadPlayers, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(SelectionPanel::on_player_selected_index, NIL, INT, KtFunctionArgument(INT, "kotlin.Int", "index"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(SelectionPanel::onPlayerSelected, NIL, OBJECT, KtFunctionArgument(OBJECT, "game.resources.units.UnitStats", "player"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(SelectionPanel::players, ARRAY, ARRAY, "godot.core.VariantArray", TYPE_STRING, "24/17:Resource", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SelectionPanel::startWeapons, ARRAY, ARRAY, "godot.core.VariantArray", TYPE_STRING, "24/17:Resource", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SelectionPanel::selectionCardScene, OBJECT, OBJECT, "godot.api.PackedScene", RESOURCE_TYPE, "PackedScene", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
