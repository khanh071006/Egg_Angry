package game.resources.waves

import game.resources.waves.WaveUnitData
import godot.annotation.Export
import godot.annotation.RegisterClass
import godot.annotation.RegisterFunction
import godot.annotation.RegisterProperty
import godot.api.PackedScene
import godot.api.Resource
import godot.core.VariantArray
import kotlin.random.Random // Dùng Random của Kotlin rất xịn

@RegisterClass
class WaveData : Resource() {

    @Export
    @RegisterProperty
    var from: Int = 1
    @Export
    @RegisterProperty
    var to: Int = 5

    @Export
    @RegisterProperty
    var waveTime: Int = 20
    @Export
    @RegisterProperty
    var minSpawnTime: Float = 0.5f
    @Export
    @RegisterProperty
    var maxSpawnTime: Float = 2.0f

    // PHÉP THUẬT KOTLIN: Khởi tạo mảng VariantArray ăn ngay, KSP không bao giờ báo lỗi!
    @Export
    @RegisterProperty
    var units: VariantArray<WaveUnitData> = VariantArray()

    @RegisterFunction
    fun getRandomUnit(): PackedScene? {
        if (units.isEmpty()) return null

        // 1. Tính tổng điểm nhân phẩm
        var totalWeight = 0.0f
        for (i in 0 until units.size) {
            val unit = units[i]
            if (unit != null) totalWeight += unit.weight
        }

        // 2. Quay xổ số
        val randomValue = Random.nextFloat() * totalWeight
        var currentWeight = 0.0f

        // 3. Dò kết quả
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