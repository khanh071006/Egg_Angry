// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.autoloads.Global
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.VariantCaster.FLOAT
import godot.core.VariantCaster.INT
import godot.core.VariantParser.ARRAY
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "Global",
  "Node",
  "game.autoloads.Global",
  "src/main/java/game/autoloads/Global.java",
  "gdj/game/autoloads/Global.gdj",
  "project-3",
  "godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "game.autoloads.Global.onCreateBlockText,game.autoloads.Global.onCreateDamageText,game.autoloads.Global.onUpgradeSelected,game.autoloads.Global.onCreateHealText",
  "",
  "game.autoloads.Global._ready,game.autoloads.Global.calculateTierProbability,game.autoloads.Global.selectItemsForOffer",
  true,
)
public open class GlobalRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<Global>(listOf(), Global::class, false, "Node", "Global", "src/main/java/game/autoloads/Global.java", "gdj/game/autoloads/Global.gdj") {
        constructor(KtConstructor0(::Global))
        notificationFunctions(listOf())
        function(Global::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Global::calculateTierProbability, OBJECT, INT, KtFunctionArgument(INT, "kotlin.Int", "currentWave"), KtFunctionArgument(OBJECT, "kotlin.FloatArray"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Global::selectItemsForOffer, ARRAY, ARRAY, INT, KtFunctionArgument(ARRAY, "godot.core.VariantArray", "itemPool"), KtFunctionArgument(INT, "kotlin.Int", "currentWave"), KtFunctionArgument(ARRAY, "godot.core.VariantArray"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        signal(Global::onCreateBlockText, KtFunctionArgument(OBJECT, "godot.api.Node2D", "p0"))
        signal(Global::onCreateDamageText, KtFunctionArgument(OBJECT, "godot.api.Node2D", "p0"), KtFunctionArgument(OBJECT, "game.components.HitBoxComponent", "p1"))
        signal(Global::onUpgradeSelected)
        signal(Global::onCreateHealText, KtFunctionArgument(OBJECT, "godot.api.Node2D", "p0"), KtFunctionArgument(FLOAT, "kotlin.Float", "p1"))
      }
    }
  }
}
