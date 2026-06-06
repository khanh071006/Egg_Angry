package game.entity.enemies.types;

import game.autoloads.Global;
import game.entity.enemies.core.Enemy;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.api.AnimatedSprite2D;
import godot.api.Node2D;
import godot.api.PackedScene;
import godot.core.StringName;
import godot.core.Vector2;
import java.util.Random;

@RegisterClass
public class EnemyEgg extends Enemy {

    private AnimatedSprite2D anim;
    private double hatchTimer = 3.0; // Chờ 3s trước khi nứt vỏ
    private boolean isHatching = false;
    private Random random = new Random();

    // Danh sách các quái vật có thể sinh ra (chỉ dùng các quái đã tạo)
    private static final String[] ENEMY_SCENES = {
        "res://scenes/unit/EnemyCreate/enemy_bomber.tscn",
        "res://scenes/unit/EnemyCreate/enemy_ice_mage.tscn",
        "res://scenes/unit/EnemyCreate/enemy_meteor_mage.tscn"
    };

    @RegisterFunction
    @Override
    public void _ready() {
        super._ready();
        
        godot.api.Node animNode = getNode("%EggAnimation");
        if (animNode instanceof AnimatedSprite2D) {
            anim = (AnimatedSprite2D) animNode;
        } else {
            animNode = getNode("Visuals/EggAnimation");
            if (animNode instanceof AnimatedSprite2D) {
                anim = (AnimatedSprite2D) animNode;
            }
        }
        
        // Kết nối tín hiệu animation_finished đã được thực hiện trong scene tscn
        
        // Vô hiệu hóa VisionArea để trứng không đẩy các quái vật khác
        godot.api.Node visionArea = getNode("VisionArea");
        if (visionArea != null) {
            visionArea.setProcessMode(godot.api.Node.ProcessMode.DISABLED);
        }
        
        canMove = false; // Trứng thì không biết đi
    }

    @RegisterFunction
    @Override
    public void _physicsProcess(double delta) {
        if (Global.gamePaused) return;
        
        // Vẫn gọi super để trứng có thể bị đẩy lui hoặc nhận sát thương
        super._physicsProcess(delta);
        
        if (!isHatching) {
            hatchTimer -= delta;
            if (hatchTimer <= 0.0) {
                startHatching();
            }
        }
    }

    private void startHatching() {
        isHatching = true;
        if (anim != null) {
            anim.play(new StringName("hatch"), 1.0f, false);
        }
    }

    @RegisterFunction
    public void _on_animation_finished() {
        if (anim != null && anim.getAnimation().toString().equals("hatch")) {
            spawnRandomEnemy();
            queueFree(); // Hủy quả trứng sau khi nở
        }
    }

    private void spawnRandomEnemy() {
        String randomScenePath = ENEMY_SCENES[random.nextInt(ENEMY_SCENES.length)];
        PackedScene scene = (PackedScene) godot.global.GD.load(randomScenePath);
        if (scene != null) {
            godot.api.Node instance = scene.instantiate();
            if (instance instanceof Node2D) {
                getTree().getRoot().addChild(instance);
                Node2D enemyNode = (Node2D) instance;
                enemyNode.setGlobalPosition(getGlobalPosition());
            }
        }
    }
}
