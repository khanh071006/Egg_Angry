package game.autoloads;

import game.entity.Player;
import godot.annotation.RegisterFunction;
import godot.api.Node;
import godot.annotation.RegisterClass;
import godot.api.ResourceLoader;
import godot.api.ShaderMaterial;
import godot.global.GD;

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
    }
}