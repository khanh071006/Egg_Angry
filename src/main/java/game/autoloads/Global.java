package game.autoloads;

import game.components.HitBoxComponent;
import game.entity.Player;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.annotation.RegisterSignal;
import godot.api.*;
import godot.annotation.RegisterClass;
import godot.core.Signal1;
import godot.core.Signal2;
import godot.global.GD;

import java.util.Random;

@RegisterClass
public class Global extends Node {

    // ĐÂY LÀ BIẾN TOÀN CẦU! Ai cũng có thể truy cập nó!
    public static Player player;
    public static ShaderMaterial FLASH_MATERIAL;
    public static Global instance;
    public static PackedScene floatingTextScene;

    @RegisterSignal
    public Signal1<Node2D> onCreateBlockText = Signal1.create(this, "onCreateBlockText");

    @RegisterSignal
    public Signal2<Node2D, HitBoxComponent> onCreateDamageText = Signal2.create(this, "onCreateDamageText");


    @RegisterFunction
    @Override
    public void _ready() {
        // Load file .tres mà bạn đã tạo từ Shader ở bước 1
        FLASH_MATERIAL = (ShaderMaterial) ResourceLoader.load("res://effects/flash_material.tres");
        floatingTextScene = (PackedScene) ResourceLoader.load("res://effects/floating_text.tscn");


        get_chance_sucess(0.5f);
        instance = this;
    }

    @RegisterFunction
    public static boolean get_chance_sucess(float chance){
        Random random = new Random();
        float randomFloat = random.nextFloat();
        if (randomFloat <= chance) return true;
        return false;
    }

    public enum UpgradeTier {
        COMMON,
        RARE,
        EPIC,
        LEGENDARY
    }

}