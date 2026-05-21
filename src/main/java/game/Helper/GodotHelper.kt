@file:JvmName("GodotHelper") // Đặt tên cầu nối để Java dễ gọi
package game.Helper // Đổi package theo chỗ sếp đặt file này nhé

import game.resources.waves.WaveData // Import kịch bản của sếp vào đây
import game.resources.items.ItemBase
import game.resources.items.upgrades.ItemUpgrade
import godot.api.Resource
import godot.core.VariantArray

// Hàm này đẻ ra cái mảng VariantArray hợp lệ 100% để quăng sang cho Java xài
fun createWaveDataArray(): VariantArray<WaveData> {
    return VariantArray()
}

fun createItemUpgradeArray(): VariantArray<ItemUpgrade> {
    return VariantArray()
}

fun createItemBaseArray(): VariantArray<ItemBase> {
    // Ép kiểu mảng không định dạng (Any?) sang ItemBase để vượt qua Type Checker của Godot C++
    return VariantArray<Any?>() as VariantArray<ItemBase>
}

fun createResourceArray(): VariantArray<Resource> {
    return VariantArray()
}