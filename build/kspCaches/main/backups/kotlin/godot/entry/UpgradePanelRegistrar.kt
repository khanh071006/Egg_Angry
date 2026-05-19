// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.ui.UpgradePanel
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.RESOURCE_TYPE
import godot.core.PropertyHint.TYPE_STRING
import godot.core.VariantParser.ARRAY
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "UpgradePanel",
  "Panel",
  "game.ui.UpgradePanel",
  "src/main/java/game/ui/UpgradePanel.java",
  "gdj/game/ui/UpgradePanel.gdj",
  "project-3",
  "godot.api.Panel,godot.api.Control,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.ui.UpgradePanel.upgradeCardScene,game.ui.UpgradePanel.upgradeList",
  "game.ui.UpgradePanel._ready,game.ui.UpgradePanel.loadUpgrades",
  true,
)
public open class UpgradePanelRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<UpgradePanel>(listOf(), UpgradePanel::class, false, "Panel", "UpgradePanel", "src/main/java/game/ui/UpgradePanel.java", "gdj/game/ui/UpgradePanel.gdj") {
        constructor(KtConstructor0(::UpgradePanel))
        notificationFunctions(listOf())
        function(UpgradePanel::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(UpgradePanel::loadUpgrades, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(UpgradePanel::upgradeCardScene, OBJECT, OBJECT, "godot.api.PackedScene", RESOURCE_TYPE, "PackedScene", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(UpgradePanel::upgradeList, ARRAY, ARRAY, "godot.core.VariantArray", TYPE_STRING, "24/17:ItemUpgrade", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
