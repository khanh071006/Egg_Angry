// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.entity.enemies.SplitterBehavior
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NODE_TYPE
import godot.core.PropertyHint.NONE
import godot.core.PropertyHint.RESOURCE_TYPE
import godot.core.VariantCaster.INT
import godot.core.VariantParser.LONG
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "SplitterBehavior",
  "Node2D",
  "game.entity.enemies.SplitterBehavior",
  "src/main/java/game/entity/enemies/SplitterBehavior.java",
  "gdj/game/entity/enemies/SplitterBehavior.gdj",
  "project-3",
  "godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.entity.enemies.SplitterBehavior.enemy,game.entity.enemies.SplitterBehavior.spawnScene,game.entity.enemies.SplitterBehavior.spawnCount",
  "game.entity.enemies.SplitterBehavior._ready,game.entity.enemies.SplitterBehavior.on_parent_die",
  true,
)
public open class SplitterBehaviorRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<SplitterBehavior>(listOf(), SplitterBehavior::class, false, "Node2D", "SplitterBehavior", "src/main/java/game/entity/enemies/SplitterBehavior.java", "gdj/game/entity/enemies/SplitterBehavior.gdj") {
        constructor(KtConstructor0(::SplitterBehavior))
        notificationFunctions(listOf())
        function(SplitterBehavior::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(SplitterBehavior::on_parent_die, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(SplitterBehavior::enemy, OBJECT, OBJECT, "game.entity.enemies.Enemy", NODE_TYPE, "Enemy", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SplitterBehavior::spawnScene, OBJECT, OBJECT, "godot.api.PackedScene", RESOURCE_TYPE, "PackedScene", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(SplitterBehavior::spawnCount, INT, LONG, "kotlin.Int", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
