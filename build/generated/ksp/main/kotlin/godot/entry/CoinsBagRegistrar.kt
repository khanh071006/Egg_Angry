// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.ui.CoinsBag
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
  "CoinsBag",
  "BoxContainer",
  "game.ui.CoinsBag",
  "src/main/java/game/ui/CoinsBag.java",
  "gdj/game/ui/CoinsBag.gdj",
  "project-3",
  "godot.api.BoxContainer,godot.api.Container,godot.api.Control,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "",
  "game.ui.CoinsBag._process,game.ui.CoinsBag._ready",
  true,
)
public open class CoinsBagRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<CoinsBag>(listOf(), CoinsBag::class, false, "BoxContainer", "CoinsBag", "src/main/java/game/ui/CoinsBag.java", "gdj/game/ui/CoinsBag.gdj") {
        constructor(KtConstructor0(::CoinsBag))
        notificationFunctions(listOf())
        function(CoinsBag::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(CoinsBag::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
      }
    }
  }
}
