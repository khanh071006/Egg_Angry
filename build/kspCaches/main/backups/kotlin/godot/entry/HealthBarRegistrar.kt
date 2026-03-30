// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.ui.HealthBar
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NONE
import godot.core.VariantCaster.FLOAT
import godot.core.VariantParser.COLOR
import godot.core.VariantParser.NIL
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "HealthBar",
  "Control",
  "game.ui.HealthBar",
  "src/main/java/game/ui/HealthBar.java",
  "gdj/game/ui/HealthBar.gdj",
  "project-3",
  "godot.api.Control,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.ui.HealthBar.backColor,game.ui.HealthBar.fillColor",
  "game.ui.HealthBar._ready,game.ui.HealthBar.updateBar,game.ui.HealthBar._on_health_component_on_health_changed",
  true,
)
public open class HealthBarRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<HealthBar>(listOf(), HealthBar::class, false, "Control", "HealthBar", "src/main/java/game/ui/HealthBar.java", "gdj/game/ui/HealthBar.gdj") {
        constructor(KtConstructor0(::HealthBar))
        notificationFunctions(listOf())
        function(HealthBar::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(HealthBar::updateBar, NIL, FLOAT, FLOAT, KtFunctionArgument(FLOAT, "kotlin.Float", "value"), KtFunctionArgument(FLOAT, "kotlin.Float", "health"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(HealthBar::_on_health_component_on_health_changed, NIL, FLOAT, FLOAT, KtFunctionArgument(FLOAT, "kotlin.Float", "current"), KtFunctionArgument(FLOAT, "kotlin.Float", "max"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(HealthBar::backColor, COLOR, COLOR, "godot.core.Color", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(HealthBar::fillColor, COLOR, COLOR, "godot.core.Color", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
