// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.resources.waves.WaveData
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NONE
import godot.core.PropertyHint.TYPE_STRING
import godot.core.VariantCaster.FLOAT
import godot.core.VariantCaster.INT
import godot.core.VariantParser.ARRAY
import godot.core.VariantParser.DOUBLE
import godot.core.VariantParser.LONG
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "WaveData",
  "Resource",
  "game.resources.waves.WaveData",
  "src/main/java/game/resources/waves/WaveData.kt",
  "gdj/game/resources/waves/WaveData.gdj",
  "project-3",
  "godot.api.Resource,godot.api.RefCounted,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.resources.waves.WaveData.from,game.resources.waves.WaveData.to,game.resources.waves.WaveData.waveTime,game.resources.waves.WaveData.minSpawnTime,game.resources.waves.WaveData.maxSpawnTime,game.resources.waves.WaveData.units",
  "game.resources.waves.WaveData.getRandomUnit",
  true,
)
public open class WaveDataRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<WaveData>(listOf(), WaveData::class, false, "Resource", "WaveData", "src/main/java/game/resources/waves/WaveData.kt", "gdj/game/resources/waves/WaveData.gdj") {
        constructor(KtConstructor0(::WaveData))
        notificationFunctions(listOf())
        function(WaveData::getRandomUnit, OBJECT, KtFunctionArgument(OBJECT, "godot.api.PackedScene"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(WaveData::from, INT, LONG, "kotlin.Int", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WaveData::to, INT, LONG, "kotlin.Int", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WaveData::waveTime, INT, LONG, "kotlin.Int", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WaveData::minSpawnTime, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WaveData::maxSpawnTime, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WaveData::units, ARRAY, ARRAY, "godot.core.VariantArray", TYPE_STRING, "24/17:WaveUnitData", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
