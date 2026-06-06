// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.entity.enemies.Spawner
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NONE
import godot.core.PropertyHint.TYPE_STRING
import godot.core.VariantParser.ARRAY
import godot.core.VariantParser.NIL
import godot.core.VariantParser.VECTOR2
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "Spawner",
  "Node2D",
  "game.entity.enemies.Spawner",
  "src/main/java/game/entity/enemies/Spawner.java",
  "Khanhs/Egg_Angry/gdj/game/entity/enemies/Spawner.gdj",
  "project-3",
  "godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.entity.enemies.Spawner.wavesData,game.entity.enemies.Spawner.spawnAreaSize",
  "game.entity.enemies.Spawner._ready,game.entity.enemies.Spawner._on_spawn_timer_timeout",
  true,
)
public open class SpawnerRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<Spawner>(listOf(), Spawner::class, false, "Node2D", "Spawner", "src/main/java/game/entity/enemies/Spawner.java", "Khanhs/Egg_Angry/gdj/game/entity/enemies/Spawner.gdj") {
        constructor(KtConstructor0(::Spawner))
        notificationFunctions(listOf())
        function(Spawner::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Spawner::_on_spawn_timer_timeout, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(Spawner::wavesData, ARRAY, ARRAY, "godot.core.VariantArray", TYPE_STRING, "24/17:WaveData", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Spawner::spawnAreaSize, VECTOR2, VECTOR2, "godot.core.Vector2", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
