package game.autoloads;

import game.entity.Player;
import godot.annotation.RegisterFunction;
import godot.api.Node;
import godot.annotation.RegisterClass;
import godot.api.ResourceLoader;
import godot.api.ShaderMaterial;
import godot.global.GD;

import java.util.Random;

@RegisterClass
public class Global extends Node {

    // ĐÂY LÀ BIẾN TOÀN CẦU! Ai cũng có thể truy cập nó!
    public static Player player;
    public static ShaderMaterial FLASH_MATERIAL;

    @RegisterFunction
    @Override
    public void _ready() {
        // Load file .tres mà bạn đã tạo từ Shader ở bước 1
        FLASH_MATERIAL = (ShaderMaterial) ResourceLoader.load("res://effects/flash_material.tres");
        get_chance_sucess(0.5f);
    }

    @RegisterFunction
    public static boolean get_chance_sucess(float chance){
        Random random = new Random();
        float randomFloat = random.nextFloat();
        if (randomFloat <= chance) return true;
        return false;
    }
}