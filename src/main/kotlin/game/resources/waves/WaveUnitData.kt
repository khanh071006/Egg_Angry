package game.resources.waves

import godot.annotation.Export
import godot.annotation.RegisterClass
import godot.annotation.RegisterProperty
import godot.api.PackedScene
import godot.api.Resource

@RegisterClass
class WaveUnitData : Resource() {

    @JvmField
    @Export
    @RegisterProperty
    var unitScene: PackedScene? = null

    @JvmField
    @Export
    @RegisterProperty
    var weight: Float = 0.0f

    // THÊM DÒNG NÀY VÀO SẾP NHÉ: Để nó cầm luôn file chỉ số của con quái đó
    @Export
    @RegisterProperty
    var unitStats: Resource? = null
}