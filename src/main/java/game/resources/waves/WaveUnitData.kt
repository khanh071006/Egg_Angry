package game.resources.waves

import godot.annotation.Export
import godot.annotation.RegisterClass
import godot.annotation.RegisterProperty
import godot.api.PackedScene
import godot.api.Resource

@RegisterClass
class WaveUnitData : Resource() {

    @Export
    @RegisterProperty
    var unitScene: PackedScene? = null

    @Export
    @RegisterProperty
    var weight: Float = 0.0f
}