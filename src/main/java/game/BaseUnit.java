package game;

import godot.api.Node2D;
import godot.api.Sprite2D;
import godot.api.AnimationPlayer;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;

@RegisterClass
public class BaseUnit extends Node2D { // Đổi tên class ở đây
    protected Node2D visuals;
    protected Sprite2D sprite;
    protected AnimationPlayer animPlayer;

    @RegisterFunction
    @Override
    public void _ready() {
        visuals = (Node2D) getNode("%Visuals");
        sprite = (Sprite2D) getNode("%Sprite");
        animPlayer = (AnimationPlayer) getNode("AnimationPlayer");
    }
}