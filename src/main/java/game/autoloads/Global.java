package game.autoloads;

import game.entity.Player;
import godot.api.Node;
import godot.annotation.RegisterClass;

@RegisterClass
public class Global extends Node {

    // ĐÂY LÀ BIẾN TOÀN CẦU! Ai cũng có thể truy cập nó!
    public static Player player;
}