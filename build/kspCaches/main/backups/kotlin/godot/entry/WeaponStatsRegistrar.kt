// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.resources.items.weapons.WeaponStats
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
  "WeaponStats",
  "Resource",
  "game.resources.items.weapons.WeaponStats",
  "src/main/java/game/resources/items/weapons/WeaponStats.java",
  "gdj/game/resources/items/weapons/WeaponStats.gdj",
  "project-3",
  "godot.api.Resource,godot.api.RefCounted,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.resources.items.weapons.WeaponStats.damage,game.resources.items.weapons.WeaponStats.accuracy,game.resources.items.weapons.WeaponStats.cooldown,game.resources.items.weapons.WeaponStats.critChance,game.resources.items.weapons.WeaponStats.critDamage,game.resources.items.weapons.WeaponStats.maxRange,game.resources.items.weapons.WeaponStats.knockback,game.resources.items.weapons.WeaponStats.lifesteal,game.resources.items.weapons.WeaponStats.recoil,game.resources.items.weapons.WeaponStats.recoilDuration,game.resources.items.weapons.WeaponStats.attackDuration,game.resources.items.weapons.WeaponStats.backDuration,game.resources.items.weapons.WeaponStats.projectileScene,game.resources.items.weapons.WeaponStats.projectileSpeed",
  "",
  true,
)
public open class WeaponStatsRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<WeaponStats>(listOf(), WeaponStats::class, false, "Resource", "WeaponStats", "src/main/java/game/resources/items/weapons/WeaponStats.java", "gdj/game/resources/items/weapons/WeaponStats.gdj") {
        constructor(KtConstructor0(::WeaponStats))
        notificationFunctions(listOf())
        property(WeaponStats::damage, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WeaponStats::accuracy, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WeaponStats::cooldown, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WeaponStats::critChance, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WeaponStats::critDamage, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WeaponStats::maxRange, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WeaponStats::knockback, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WeaponStats::lifesteal, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WeaponStats::recoil, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WeaponStats::recoilDuration, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WeaponStats::attackDuration, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WeaponStats::backDuration, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WeaponStats::projectileScene, OBJECT, OBJECT, "godot.api.PackedScene", RESOURCE_TYPE, "PackedScene", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(WeaponStats::projectileSpeed, FLOAT, DOUBLE, "kotlin.Float", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
