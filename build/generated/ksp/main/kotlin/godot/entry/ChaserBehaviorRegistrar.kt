// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.entity.enemies.behaviors.ChaserBehavior
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NODE_TYPE
import godot.core.PropertyHint.NONE
import godot.core.VariantCaster.FLOAT
import godot.core.VariantParser.DOUBLE
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "ChaserBehavior",
  "Node2D",
  "game.entity.enemies.behaviors.ChaserBehavior",
  "src/main/java/game/entity/enemies/behaviors/ChaserBehavior.java",
  "gdj/game/entity/enemies/behaviors/ChaserBehavior.gdj",
  "project-3",
  "godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.entity.enemies.behaviors.ChaserBehavior.enemy,game.entity.enemies.behaviors.ChaserBehavior.animEffects,game.entity.enemies.behaviors.ChaserBehavior.cooldown,game.entity.enemies.behaviors.ChaserBehavior.prepTime",
  "game.entity.enemies.behaviors.ChaserBehavior._process,game.entity.enemies.behaviors.ChaserBehavior._ready",
  true,
)
public open class ChaserBehaviorRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<ChaserBehavior>(listOf(), ChaserBehavior::class, false, "Node2D", "ChaserBehavior", "src/main/java/game/entity/enemies/behaviors/ChaserBehavior.java", "gdj/game/entity/enemies/behaviors/ChaserBehavior.gdj") {
        constructor(KtConstructor0(::ChaserBehavior))
        notificationFunctions(listOf())
        function(ChaserBehavior::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(ChaserBehavior::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(ChaserBehavior::enemy, OBJECT, OBJECT, "game.entity.enemies.core.Enemy", NODE_TYPE, "Enemy", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ChaserBehavior::animEffects, OBJECT, OBJECT, "godot.api.AnimationPlayer", NODE_TYPE, "AnimationPlayer", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ChaserBehavior::cooldown, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(ChaserBehavior::prepTime, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
