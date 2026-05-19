// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.ui.StatsContainer
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NODE_TYPE
import godot.core.VariantParser.DOUBLE
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "StatsContainer",
  "Panel",
  "game.ui.StatsContainer",
  "src/main/java/game/ui/StatsContainer.java",
  "gdj/game/ui/StatsContainer.gdj",
  "project-3",
  "godot.api.Panel,godot.api.Control,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.ui.StatsContainer.healthLabel,game.ui.StatsContainer.regenLabel,game.ui.StatsContainer.lifestealLabel,game.ui.StatsContainer.damageLabel,game.ui.StatsContainer.luckLabel,game.ui.StatsContainer.speedLabel,game.ui.StatsContainer.blockLabel,game.ui.StatsContainer.harvestingLabel",
  "game.ui.StatsContainer._process,game.ui.StatsContainer._ready",
  true,
)
public open class StatsContainerRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<StatsContainer>(listOf(), StatsContainer::class, false, "Panel", "StatsContainer", "src/main/java/game/ui/StatsContainer.java", "gdj/game/ui/StatsContainer.gdj") {
        constructor(KtConstructor0(::StatsContainer))
        notificationFunctions(listOf())
        function(StatsContainer::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(StatsContainer::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(StatsContainer::healthLabel, OBJECT, OBJECT, "godot.api.Label", NODE_TYPE, "Label", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(StatsContainer::regenLabel, OBJECT, OBJECT, "godot.api.Label", NODE_TYPE, "Label", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(StatsContainer::lifestealLabel, OBJECT, OBJECT, "godot.api.Label", NODE_TYPE, "Label", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(StatsContainer::damageLabel, OBJECT, OBJECT, "godot.api.Label", NODE_TYPE, "Label", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(StatsContainer::luckLabel, OBJECT, OBJECT, "godot.api.Label", NODE_TYPE, "Label", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(StatsContainer::speedLabel, OBJECT, OBJECT, "godot.api.Label", NODE_TYPE, "Label", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(StatsContainer::blockLabel, OBJECT, OBJECT, "godot.api.Label", NODE_TYPE, "Label", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(StatsContainer::harvestingLabel, OBJECT, OBJECT, "godot.api.Label", NODE_TYPE, "Label", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
