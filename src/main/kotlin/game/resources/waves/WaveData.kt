package game.resources.waves

import game.resources.waves.WaveUnitData
import godot.annotation.Export
import godot.annotation.RegisterClass
import godot.annotation.RegisterFunction
import godot.annotation.RegisterProperty
import godot.api.PackedScene
import godot.api.Resource
import godot.core.VariantArray
import kotlin.random.Random

@RegisterClass
class WaveData : Resource() {
    enum class SpawnType {
        FIXED,
        RANDOM
    }

    @JvmField
    @Export
    @RegisterProperty
    var from: Int = 1
    
    @JvmField
    @Export
    @RegisterProperty
    var to: Int = 5

    @JvmField
    @Export
    @RegisterProperty
    var waveTime: Int = 20

    @JvmField
    @Export
    @RegisterProperty
    var spawnType: SpawnType = SpawnType.FIXED

    @JvmField
    @Export
    @RegisterProperty
    var fixedSpawnTime: Float = 0.5f

    @JvmField
    @Export
    @RegisterProperty
    var minSpawnTime: Float = 0.5f
    
    @JvmField
    @Export
    @RegisterProperty
    var maxSpawnTime: Float = 2.0f

    @Export
    @RegisterProperty
    var units: VariantArray<WaveUnitData> = VariantArray()

    @RegisterFunction
    fun getRandomUnit(): PackedScene? {
        if (units.isEmpty()) return null

        var totalWeight = 0.0f
        for (i in 0 until units.size) {
            val unit = units[i]
            if (unit != null) totalWeight += unit.weight
        }

        val randomValue = Random.nextFloat() * totalWeight
        var currentWeight = 0.0f

        for (i in 0 until units.size) {
            val unit = units[i]
            if (unit != null) {
                currentWeight += unit.weight
                if (randomValue <= currentWeight) {
                    return unit.unitScene
                }
            }
        }
        return null
    }
}