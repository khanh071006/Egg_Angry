package game.helper

import game.resources.waves.WaveData
import game.resources.items.ItemBase
import game.resources.items.upgrades.ItemUpgrade
import game.resources.units.UnitStats
import game.resources.items.weapons.ItemWeapon
import godot.api.Resource
import godot.core.VariantArray

object GodotHelper {
    @JvmStatic
    fun createWaveDataArray(): VariantArray<WaveData> {
        return VariantArray()
    }

    @JvmStatic
    fun createItemUpgradeArray(): VariantArray<ItemUpgrade> {
        return VariantArray()
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun createItemBaseArray(): VariantArray<ItemBase> {
        return VariantArray<Any?>() as VariantArray<ItemBase>
    }

    @JvmStatic
    fun createResourceArray(): VariantArray<Resource> {
        return VariantArray()
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun createUnitStatsArray(): VariantArray<UnitStats> {
        return VariantArray<Any?>() as VariantArray<UnitStats>
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun createItemWeaponArray(): VariantArray<ItemWeapon> {
        return VariantArray<Any?>() as VariantArray<ItemWeapon>
    }
}