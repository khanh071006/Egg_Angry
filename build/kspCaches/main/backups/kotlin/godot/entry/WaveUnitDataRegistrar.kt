// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.resources.waves.WaveUnitData
import godot.`annotation`.RegisteredClassMetadata
import godot.core.KtConstructor0
import godot.core.PropertyHint.NONE
import godot.core.PropertyHint.RESOURCE_TYPE
import godot.core.VariantCaster.FLOAT
import godot.core.VariantParser.DOUBLE
import godot.core.VariantParser.OBJECT
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "WaveUnitData",
  "Resource",
  "game.resources.waves.WaveUnitData",
  "src/main/java/game/resources/waves/WaveUnitData.kt",
  "gdj/game/resources/waves/WaveUnitData.gdj",
  "project-3",
  "godot.api.Resource,godot.api.RefCounted,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.resources.waves.WaveUnitData.unitScene,game.resources.waves.WaveUnitData.weight",
  "",
  true,
)
public open class WaveUnitDataRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<WaveUnitData>(listOf(), WaveUnitData::class, false, "Resource", "WaveUnitData", "src/main/java/game/resources/waves/WaveUnitData.kt", "gdj/game/resources/waves/WaveUnitData.gdj") {
        constructor(KtConstructor0(::WaveUnitData))
        notificationFunctions(listOf())
        property(WaveUnitData::unitScene, OBJECT, OBJECT, "godot.api.PackedScene?", RESOURCE_TYPE, "PackedScene", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WaveUnitData::weight, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
