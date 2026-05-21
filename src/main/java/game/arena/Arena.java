package game.arena;

import game.autoloads.Global;
import game.components.HitBoxComponent;
import game.entity.Player;
import game.entity.enemies.Spawner;
import game.ui.FloatingText;
import godot.api.Label;
import godot.api.Node2D;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.Export;
import godot.annotation.RegisterProperty;
import godot.core.*;
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
    public Color hpColor = new Color(0.0f, 1.0f, 0.0f, 1.0f);         // Xanh lá


    @Export
    @RegisterProperty
    public Player player;

    //Wave Information
    private Label waveIndexLabel;
    private Label waveTimeLabel;
    private Spawner spawner;

    @Export
    @RegisterProperty
    public game.ui.UpgradePanel upgradePanel;

    @RegisterFunction
    @Override
    public void _ready() {
        Global.player = this.player;

        // BÍ QUYẾT LÀ ĐÂY: Dùng Callable.create(...) và new StringName(...)
        godot.core.Error errBlock = Global.instance.onCreateBlockText.connect(Callable.create(this, new StringName("show_block_text")), 0);

        godot.core.Error errDamage = Global.instance.onCreateDamageText.connect(Callable.create(this, new StringName("show_damage_text")), 0);

        godot.core.Error errHeal = Global.instance.onCreateHealText.connect(Callable.create(this, new StringName("show_heal_text")), 0);

        // Dùng getNode bắt thẳng mấy cái UI vừa tạo và tóm lấy Spawner
        waveIndexLabel = (Label) getNode("GameUI/WaveIndexLabel");
        waveTimeLabel = (Label) getNode("GameUI/WaveTimeLabel");
        spawner = (Spawner) getNode("Spawner");

        if (spawner == null) GD.printErr("Arena LỖI: Không tìm thấy Spawner!");
        if (waveIndexLabel == null) GD.printErr("Arena LỖI: Không tìm thấy WaveIndexLabel!");

        Global.instance.onUpgradeSelected.connect(Callable.create(this, new StringName("_on_upgrade_selected")), 0);
        
        if (spawner != null) {
            spawner.onWaveCompleted.connect(Callable.create(this, new StringName("_on_wave_completed")), 0);
            spawner.startWave();
        }
    }



    @RegisterFunction
    @Override
    public void _process(double delta) {
        if (Global.gamePaused) return;
        // Hàm này chạy liên tục mỗi khung hình (60 FPS)
        // Cập nhật text liên tục từ Spawner lên Màn hình
        if (spawner != null) {
            if (waveIndexLabel != null) {
                waveIndexLabel.setText(spawner.getWaveText());
            }
            if (waveTimeLabel != null) {
                waveTimeLabel.setText(spawner.getWaveTimerText());
            }
        }
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

    @RegisterFunction
    public void show_heal_text(Node2D unit, Float heal) {
        FloatingText textInstance = spawnTextAroundUnit(unit);
        String healStr = "+" + String.valueOf((int) (float) heal);
        textInstance.setup(healStr, hpColor);
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

    @RegisterFunction
    public void _on_wave_completed() {
        getTree().createTimer(1.0).getTimeout().connect(Callable.create(this, new StringName("show_upgrades")), 0);
    }

    @RegisterFunction
    public void show_upgrades() {
        if (!godot.global.GD.isInstanceValid(Global.player)) return;

        // --- GỌI HÀM TÍNH XÁC SUẤT ĐỂ IN RA DEBUG NHƯ TRONG VIDEO ---
        int currentWave = 1;
        if (spawner != null) {
            currentWave = spawner.waveIndex;
            Global.instance.calculateTierProbability(currentWave);
        }

        if (upgradePanel != null) {
            upgradePanel.loadUpgrades(currentWave);
            upgradePanel.show();
        }
    }

    @RegisterFunction
    public void _on_upgrade_selected() {
        if (upgradePanel != null) {
            upgradePanel.hide();
        }
        startNewWave();
    }

    @RegisterFunction
    public void startNewWave() {
        Global.gamePaused = false;
        if (spawner != null) {
            spawner.waveIndex += 1;
            spawner.startWave();
        }
        if (Global.player != null) {
            Global.player.updatePlayerNewWave();
        }
    }
}