// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.resources.EnemyStats
import game.resources.UnitStats.UnitType
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
  "EnemyStats",
  "Resource",
  "game.resources.EnemyStats",
  "src/main/java/game/resources/EnemyStats.java",
  "gdj/game/resources/EnemyStats.gdj",
  "project-3",
  "game.resources.UnitStats,godot.api.Resource,godot.api.RefCounted,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.resources.EnemyStats.goldDrop,game.resources.EnemyStats.blockChance,game.resources.EnemyStats.unitName,game.resources.EnemyStats.type,game.resources.EnemyStats.icon,game.resources.EnemyStats.health,game.resources.EnemyStats.healthIncreasePerWave,game.resources.EnemyStats.damage,game.resources.EnemyStats.damageIncreasePerWave,game.resources.EnemyStats.speed,game.resources.EnemyStats.blockchance",
  "",
  true,
)
public open class EnemyStatsRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<EnemyStats>(listOf("UnitStats"), EnemyStats::class, false, "Resource", "EnemyStats", "src/main/java/game/resources/EnemyStats.java", "gdj/game/resources/EnemyStats.gdj") {
        constructor(KtConstructor0(::EnemyStats))
        notificationFunctions(listOf())
        property(EnemyStats::goldDrop, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(EnemyStats::blockChance, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(EnemyStats::unitName, STRING, STRING, "kotlin.String", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(EnemyStats::type, ENUM<UnitType>(UnitType.entries.toTypedArray()), ENUM<UnitType>(UnitType.entries.toTypedArray()), "Int", godot.core.PropertyHint.ENUM, "PLAYER,ENEMY", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(EnemyStats::icon, OBJECT, OBJECT, "godot.api.Texture2D", RESOURCE_TYPE, "Texture2D", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(EnemyStats::health, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(EnemyStats::healthIncreasePerWave, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(EnemyStats::damage, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(EnemyStats::damageIncreasePerWave, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(EnemyStats::speed, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(EnemyStats::blockchance, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
