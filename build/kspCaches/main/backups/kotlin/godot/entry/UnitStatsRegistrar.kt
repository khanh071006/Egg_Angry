// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.resources.units.UnitStats
import game.resources.units.UnitStats.UnitType
import godot.`annotation`.RegisteredClassMetadata
import godot.core.KtConstructor0
import godot.core.PropertyHint.NONE
import godot.core.PropertyHint.RESOURCE_TYPE
import godot.core.VariantCaster.ENUM
import godot.core.VariantCaster.FLOAT
import godot.core.VariantParser.DOUBLE
import godot.core.VariantParser.OBJECT
import godot.core.VariantParser.STRING
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "UnitStats",
  "Resource",
  "game.resources.units.UnitStats",
  "src/main/java/game/resources/units/UnitStats.java",
  "gdj/game/resources/UnitStats.gdj",
  "project-3",
  "godot.api.Resource,godot.api.RefCounted,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.resources.units.UnitStats.unitName,game.resources.units.UnitStats.type,game.resources.units.UnitStats.icon,game.resources.units.UnitStats.health,game.resources.units.UnitStats.healthIncreasePerWave,game.resources.units.UnitStats.damage,game.resources.units.UnitStats.damageIncreasePerWave,game.resources.units.UnitStats.speed,game.resources.units.UnitStats.blockchance",
  "",
  true,
)
public open class UnitStatsRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<UnitStats>(listOf(), UnitStats::class, false, "Resource", "UnitStats", "src/main/java/game/resources/units/UnitStats.java", "gdj/game/resources/UnitStats.gdj") {
        constructor(KtConstructor0(::UnitStats))
        notificationFunctions(listOf())
        property(UnitStats::unitName, STRING, STRING, "kotlin.String", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(UnitStats::type, ENUM<UnitType>(UnitType.entries.toTypedArray()), ENUM<UnitType>(UnitType.entries.toTypedArray()), "Int", godot.core.PropertyHint.ENUM, "PLAYER,ENEMY", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(UnitStats::icon, OBJECT, OBJECT, "godot.api.Texture2D", RESOURCE_TYPE, "Texture2D", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(UnitStats::health, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(UnitStats::healthIncreasePerWave, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(UnitStats::damage, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(UnitStats::damageIncreasePerWave, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(UnitStats::speed, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(UnitStats::blockchance, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
