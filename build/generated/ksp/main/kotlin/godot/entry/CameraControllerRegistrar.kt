// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.arena.CameraController
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
  "CameraController",
  "Camera2D",
  "game.arena.CameraController",
  "src/main/java/game/arena/CameraController.java",
  "gdj/game/arena/CameraController.gdj",
  "project-3",
  "godot.api.Camera2D,godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "",
  "game.arena.CameraController._process",
  true,
)
public open class CameraControllerRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<CameraController>(listOf(), CameraController::class, false, "Camera2D", "CameraController", "src/main/java/game/arena/CameraController.java", "gdj/game/arena/CameraController.gdj") {
        constructor(KtConstructor0(::CameraController))
        notificationFunctions(listOf())
        function(CameraController::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
      }
    }
  }
}
