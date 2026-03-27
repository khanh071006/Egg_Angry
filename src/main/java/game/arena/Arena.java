package game.arena;

import game.Player;
import game.autoloads.Global;
import godot.api.Node2D;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.Export;
import godot.annotation.RegisterProperty;

@RegisterClass
public class Arena extends Node2D {

    @Export
    @RegisterProperty
    public Player player;

    @RegisterFunction
    @Override
    public void _ready() {
        // Khi Võ đài mở cửa, báo cho Tổng đài biết Player là thằng nào!
        Global.player = this.player;
    }
}