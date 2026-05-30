package game.helper

import game.resources.waves.WaveData
import godot.core.VariantArray

/**
 * Lớp Helper để hỗ trợ Java gọi các hàm của Kotlin dễ dàng hơn.
 * Chuyển sang dạng object để Java gọi trực tiếp qua GodotHelper.createWaveDataArray()
 */
object GodotHelper {
    @JvmStatic
    fun createWaveDataArray(): VariantArray<WaveData> {
        return VariantArray()
    }
}