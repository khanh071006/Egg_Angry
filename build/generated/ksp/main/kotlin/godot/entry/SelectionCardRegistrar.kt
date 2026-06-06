// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.ui.SelectionCard
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.VariantCaster.INT
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "SelectionCard",
  "Button",
  "game.ui.SelectionCard",
  "src/main/java/game/ui/SelectionCard.java",
  "gdj/game/ui/SelectionCard.gdj",
  "project-3",
  "godot.api.Button,godot.api.BaseButton,godot.api.Control,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "game.ui.SelectionCard.onCardSelected",
  "",
  "game.ui.SelectionCard._on_pressed,game.ui.SelectionCard.setIconTexture",
  true,
)
public open class SelectionCardRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<SelectionCard>(listOf(), SelectionCard::class, false, "Button", "SelectionCard", "src/main/java/game/ui/SelectionCard.java", "gdj/game/ui/SelectionCard.gdj") {
        constructor(KtConstructor0(::SelectionCard))
        notificationFunctions(listOf())
        function(SelectionCard::_on_pressed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(SelectionCard::setIconTexture, NIL, OBJECT, KtFunctionArgument(OBJECT, "godot.api.Texture2D", "texture"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        signal(SelectionCard::onCardSelected, KtFunctionArgument(INT, "kotlin.Int", "p0"))
      }
    }
  }
}
