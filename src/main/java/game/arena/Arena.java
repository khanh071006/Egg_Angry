package game.arena;

import game.autoloads.Global;
import game.components.HitBoxComponent;
import game.entity.Player;
import game.ui.FloatingText;
import godot.api.Node2D;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.Export;
import godot.annotation.RegisterProperty;
import godot.core.Callable;
import godot.core.Color;
import godot.core.StringName;
import godot.core.Vector2;
import godot.global.GD;



@RegisterClass
public class Arena extends Node2D {
    @Export
    @RegisterProperty
    public Color normalColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);     // Trắng

    @Export
    @RegisterProperty
    public Color blockColor = new Color(1.0f, 0.0f, 0.0f, 1.0f);      // Đỏ

    @Export
    @RegisterProperty
    public Color critColor = new Color(1.0f, 1.0f, 0.0f, 1.0f);       // Vàng


    @Export
    @RegisterProperty
    public Player player;

    @RegisterFunction
    @Override
    public void _ready() {
        Global.player = this.player;

        // BÍ QUYẾT LÀ ĐÂY: Dùng Callable.create(...) và new StringName(...)
        godot.core.Error errBlock = Global.instance.onCreateBlockText.connect(Callable.create(this, new StringName("show_block_text")), 0);

        godot.core.Error errDamage = Global.instance.onCreateDamageText.connect(Callable.create(this, new StringName("show_damage_text")), 0);
    }

    // 2. ĐỔI TÊN HÀM THẬT ĐƠN GIẢN ĐỂ GODOT KHÔNG BÓP MÉO ĐƯỢC
    @RegisterFunction
    public void show_block_text(Node2D unit) {
        FloatingText textInstance = spawnTextAroundUnit(unit);
        textInstance.setup("Blocked", blockColor);
    }

    @RegisterFunction
    public void show_damage_text(Node2D unit, HitBoxComponent hitbox) {
        FloatingText textInstance = spawnTextAroundUnit(unit);
        String damageStr = String.valueOf((int) hitbox.damage);
        textInstance.setup(damageStr, normalColor);
    }

    // HÀM HỖ TRỢ: Tính toán vị trí văng ra để số không đè lên nhau
    private FloatingText spawnTextAroundUnit(Node2D unit) {
        // Đúc chữ từ khuôn
        FloatingText instance = (FloatingText) Global.instance.floatingTextScene.instantiate();

        getTree().getRoot().addChild(instance);
        // Thêm vào Root để chữ không dính vào con quái khi nó di chuyển
        double randomAngle = GD.randfRange(0f, (float) Math.PI * 2);
        Vector2 offset = new Vector2(1, 0).rotated(randomAngle).times(35);

        // Đặt vị trí
        instance.setGlobalPosition(unit.getGlobalPosition().plus(offset));

        return instance;
    }



}