// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.resources.PlayerStats
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
  "PlayerStats",
  "Resource",
  "game.resources.PlayerStats",
  "src/main/java/game/resources/PlayerStats.java",
  "gdj/game/resources/PlayerStats.gdj",
  "project-3",
  "game.resources.UnitStats,godot.api.Resource,godot.api.RefCounted,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.resources.PlayerStats.luck,game.resources.PlayerStats.blockChance,game.resources.PlayerStats.unitName,game.resources.PlayerStats.type,game.resources.PlayerStats.icon,game.resources.PlayerStats.health,game.resources.PlayerStats.healthIncreasePerWave,game.resources.PlayerStats.damage,game.resources.PlayerStats.damageIncreasePerWave,game.resources.PlayerStats.speed",
  "",
  true,
)
public open class PlayerStatsRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<PlayerStats>(listOf("UnitStats"), PlayerStats::class, false, "Resource", "PlayerStats", "src/main/java/game/resources/PlayerStats.java", "gdj/game/resources/PlayerStats.gdj") {
        constructor(KtConstructor0(::PlayerStats))
        notificationFunctions(listOf())
        property(PlayerStats::luck, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(PlayerStats::blockChance, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(PlayerStats::unitName, STRING, STRING, "kotlin.String", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(PlayerStats::type, ENUM<UnitType>(UnitType.entries.toTypedArray()), ENUM<UnitType>(UnitType.entries.toTypedArray()), "Int", godot.core.PropertyHint.ENUM, "PLAYER,ENEMY", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(PlayerStats::icon, OBJECT, OBJECT, "godot.api.Texture2D", RESOURCE_TYPE, "Texture2D", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(PlayerStats::health, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(PlayerStats::healthIncreasePerWave, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(PlayerStats::damage, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(PlayerStats::damageIncreasePerWave, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(PlayerStats::speed, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
