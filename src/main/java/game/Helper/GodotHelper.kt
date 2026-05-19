@file:JvmName("GodotHelper") // Đặt tên cầu nối để Java dễ gọi
package game.Helper // Đổi package theo chỗ sếp đặt file này nhé

import game.resources.waves.WaveData // Import kịch bản của sếp vào đây
import game.resources.items.upgrades.ItemUpgrade
import godot.core.VariantArray

// Hàm này đẻ ra cái mảng VariantArray hợp lệ 100% để quăng sang cho Java xài
fun createWaveDataArray(): VariantArray<WaveData> {
    return VariantArray()
}

fun createItemUpgradeArray(): VariantArray<ItemUpgrade> {
    return VariantArray()
}