package game.arena;

import game.autoloads.Global;
import game.components.HitBoxComponent;
import game.entity.Player;
import game.entity.enemies.Spawner;
import game.ui.FloatingText;
import godot.api.Label;
import godot.api.Node;
import godot.api.Node2D;
import godot.api.Sprite2D;
import godot.api.Texture2D;
import godot.api.ResourceLoader;
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
    public Color normalColor = new Color(1.0f, 1.0f, 1.0f, 1.0f); // Trắng

    @Export
    @RegisterProperty
    public Color blockColor = new Color(1.0f, 0.0f, 0.0f, 1.0f); // Đỏ

    @Export
    @RegisterProperty
    public Color critColor = new Color(1.0f, 1.0f, 0.0f, 1.0f); // Vàng

    @Export
    @RegisterProperty
    public Color hpColor = new Color(0.0f, 1.0f, 0.0f, 1.0f); // Xanh lá

    // Player is spawned dynamically now

    // Wave Information
    private Label waveIndexLabel;
    private Label waveTimeLabel;
    private Spawner spawner;

    @Export
    @RegisterProperty
    public game.ui.UpgradePanel upgradePanel;

    @Export
    @RegisterProperty
    public game.ui.ShopPanel shopPanel;

    @Export
    @RegisterProperty
    public game.ui.CoinsBag coinsBag;

    private java.util.List<game.items.Coins> goldList = new java.util.ArrayList<>();

    @RegisterFunction
    @Override
    public void _ready() {

        // BÍ QUYẾT LÀ ĐÂY: Dùng Callable.create(...) và new StringName(...)
        godot.core.Error errBlock = Global.instance.onCreateBlockText
                .connect(Callable.create(this, new StringName("show_block_text")), 0);

        godot.core.Error errDamage = Global.instance.onCreateDamageText
                .connect(Callable.create(this, new StringName("show_damage_text")), 0);

        godot.core.Error errHeal = Global.instance.onCreateHealText
                .connect(Callable.create(this, new StringName("show_heal_text")), 0);

        // Dùng getNode bắt thẳng mấy cái UI vừa tạo và tóm lấy Spawner
        waveIndexLabel = (Label) getNode("GameUI/WaveIndexLabel");
        waveTimeLabel = (Label) getNode("GameUI/WaveTimeLabel");
        spawner = (Spawner) getNode("Spawner");

        if (spawner == null)
            GD.printErr("Arena LỖI: Không tìm thấy Spawner!");
        if (waveIndexLabel == null)
            GD.printErr("Arena LỖI: Không tìm thấy WaveIndexLabel!");

        // Tìm CoinsBag
        coinsBag = (game.ui.CoinsBag) getNodeOrNull("%CoinsBag");

        Global.instance.onUpgradeSelected.connect(Callable.create(this, new StringName("_on_upgrade_selected")), 0);
        Global.instance.onEnemyDied.connect(Callable.create(this, new StringName("spawn_coins")), 0);

        if (spawner != null) {
            spawner.onWaveCompleted.connect(Callable.create(this, new StringName("_on_wave_completed")), 0);
        }

        // --- KẾT NỐI SHOP PANEL ---
        if (shopPanel != null) {
            shopPanel.onShopNextWave.connect(Callable.create(this, new StringName("_on_shop_next_wave")), 0);
            shopPanel.hide(); // Ẩn shop lúc mới vào game
        }

        // --- SPAWN DECORATIONS ---
        spawnDecorations();
    }

    private void spawnDecorations() {
        java.io.File logFile = new java.io.File("d:\\Egg_Angry\\java_log.txt");
        try (java.io.FileWriter fw = new java.io.FileWriter(logFile, true);
             java.io.PrintWriter pw = new java.io.PrintWriter(fw)) {
            pw.println("--- spawnDecorations started at " + new java.util.Date() + " ---");

            Node decorationsNode = getNode("Decorations");
            if (decorationsNode == null) {
                pw.println("ERROR: Decorations node not found!");
                GD.printErr("Arena: Không tìm thấy node Decorations!");
                return;
            }
            pw.println("SUCCESS: Decorations node found: " + decorationsNode);

            // Load các texture trang trí mặt đất, thử cả 2 đường dẫn có thể có
            Texture2D pebbleTex = (Texture2D) ResourceLoader.load("res://assets/sprites/pebble.png");
            if (pebbleTex == null) {
                pw.println("WARNING: Failed to load from res://assets/sprites/pebble.png, trying fallback...");
                pebbleTex = (Texture2D) ResourceLoader.load("res://gdj/game/Game+Assets/assets/sprites/pebble.png");
            }
            
            Texture2D grassTex = (Texture2D) ResourceLoader.load("res://assets/sprites/grass_tuft.png");
            if (grassTex == null) {
                pw.println("WARNING: Failed to load from res://assets/sprites/grass_tuft.png, trying fallback...");
                grassTex = (Texture2D) ResourceLoader.load("res://gdj/game/Game+Assets/assets/sprites/grass_tuft.png");
            }

            if (pebbleTex == null) {
                pw.println("ERROR: pebbleTex is null after fallback!");
                GD.printErr("Arena: Không load được texture pebble.png!");
            } else {
                pw.println("SUCCESS: pebbleTex loaded successfully.");
            }
            
            if (grassTex == null) {
                pw.println("ERROR: grassTex is null after fallback!");
                GD.printErr("Arena: Không load được texture grass_tuft.png!");
            } else {
                pw.println("SUCCESS: grassTex loaded successfully.");
            }

            java.util.List<Texture2D> textures = new java.util.ArrayList<>();
            if (pebbleTex != null) textures.add(pebbleTex);
            if (grassTex != null) textures.add(grassTex);

            if (textures.isEmpty()) {
                pw.println("ERROR: No textures loaded. Aborting spawn.");
                GD.printErr("Arena: Không có texture trang trí nào được load thành công!");
                return;
            }

            // Sinh ngẫu nhiên khoảng 350 họa tiết trang trí nhỏ trên bản đồ
            int count = 350;
            pw.println("Spawning " + count + " decorations...");
            for (int i = 0; i < count; i++) {
                int texIdx = (int) (Math.random() * textures.size());
                Texture2D tex = textures.get(texIdx);

                Sprite2D sprite = new Sprite2D();
                sprite.setTexture(tex);

                // Tọa độ ngẫu nhiên trong khoảng [-Global.MAP_LIMIT_X, Global.MAP_LIMIT_X] và Y tương ứng
                // Chừa lề 100px để không sát rìa mép
                float limitX = Global.MAP_LIMIT_X - 100.0f;
                float limitY = Global.MAP_LIMIT_Y - 100.0f;
                float posX = (float) (2 * (Math.random() - 0.5) * limitX);
                float posY = (float) (2 * (Math.random() - 0.5) * limitY);

                sprite.setPosition(new Vector2(posX, posY));

                // Góc quay ngẫu nhiên 0 đến 360 độ
                float rotation = (float) (Math.random() * Math.PI * 2);
                sprite.setRotation(rotation);

                // Tỉ lệ scale hợp lý (0.10 đến 0.15) để họa tiết tinh tế nhưng vẫn nhìn rõ
                float scaleBase = (float) (0.10f + Math.random() * 0.05f);
                sprite.setScale(new Vector2(scaleBase, scaleBase));

                // Thêm vào node cha Decorations (không có collision vật lý để player đi xuyên qua)
                decorationsNode.addChild(sprite);
            }
            pw.println("SUCCESS: Spawned " + count + " decorations successfully.");
            GD.print("Arena: Đã tự sinh thành công " + count + " họa tiết trang trí mặt đất.");
        } catch (Exception e) {
            GD.printErr("Arena spawnDecorations Exception: " + e.getMessage());
        }
    }

    @RegisterFunction
    public void _on_selection_panel_selection_completed() {
        godot.global.GD.print("ARENA: Signal received! Bắt đầu spawn player...");
        Player p = Global.getSelectedPlayer();
        
        if (p != null) {
            godot.global.GD.print("ARENA: Spawn player thành công! Player: " + p.getName());
            this.addChild(p);
            
            // Đặt player ở giữa tâm bản đồ (0, 0)
            p.setGlobalPosition(new godot.core.Vector2(0, 0));
            
            if (Global.mainWeaponSelected != null) {
                godot.global.GD.print("ARENA: Đang thêm vũ khí: " + Global.mainWeaponSelected.itemName);
                p.addWeapon(Global.mainWeaponSelected);
            } else {
                godot.global.GD.printErr("ARENA LỖI: mainWeaponSelected bị NULL!");
            }
        } else {
            godot.global.GD.printErr("ARENA LỖI: getSelectedPlayer() trả về NULL! Kiểm tra lại Global.mainPlayerSelected");
        }

        if (shopPanel != null) {
            shopPanel.createItemWeapon(Global.mainWeaponSelected);
        }

        Global.instance.equippedWeapons.add(Global.mainWeaponSelected);

        if (spawner != null) {
            Global.instance.stopBgm();
            spawner.startWave();
        }
        Global.gamePaused = false;
    }

    @RegisterFunction
    @Override
    public void _process(double delta) {
        if (Global.gamePaused)
            return;
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
        if (textInstance != null) {
            textInstance.setup("Blocked", blockColor);
        }
    }

    @RegisterFunction
    public void show_damage_text(Node2D unit, HitBoxComponent hitbox) {
        FloatingText textInstance = spawnTextAroundUnit(unit);
        if (textInstance == null) return;
        
        String damageStr = String.valueOf((int) hitbox.damage);
        if (hitbox.critical) {
            textInstance.setup(damageStr, critColor);
        } else {
            textInstance.setup(damageStr, normalColor);
        }
    }

    @RegisterFunction
    public void show_heal_text(Node2D unit, Float heal) {
        FloatingText textInstance = spawnTextAroundUnit(unit);
        if (textInstance != null) {
            String healStr = "+" + String.valueOf((int) (float) heal);
            textInstance.setup(healStr, hpColor);
        }
    }

    // HÀM HỖ TRỢ: Tính toán vị trí văng ra để số không đè lên nhau
    private FloatingText spawnTextAroundUnit(Node2D unit) {
        if (!isInsideTree() || getTree() == null || getTree().getRoot() == null) {
            return null;
        }

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
        clean_arena();
        clean_arena();
        clean_arena(); // Gọi 2 lần như trong video để đề phòng rơi xu sát nút
        
        // Phát nhạc thắng wave
        Global.instance.playSfx("res://assets/audio/win-wave_sound.mp3");
        Global.instance.startBgm();

        getTree().createTimer(1.0).getTimeout().connect(Callable.create(this, new StringName("show_upgrades")), 0);
    }

    @RegisterFunction
    public void spawn_coins(game.entity.enemies.Enemy enemy) {
        if (Global.instance.coinsScene == null)
            return;

        double randomAngle = GD.randfRange(0, (float) Math.PI * 2);
        Vector2 offset = new Vector2(1, 0).rotated(randomAngle).times(35);
        Vector2 spawnPosition = enemy.getGlobalPosition().plus(offset);

        GD.print("Spawn Coins: Enemy Pos=" + enemy.getGlobalPosition() + ", Spawn Pos=" + spawnPosition);

        Node goldInstanceNode = Global.instance.coinsScene.instantiate();
        if (goldInstanceNode instanceof game.items.Coins) {
            game.items.Coins goldInstance = (game.items.Coins) goldInstanceNode;

            goldList.add(goldInstance);

            if (enemy.stats instanceof game.resources.units.EnemyStats) {
                goldInstance.value = (int) ((game.resources.units.EnemyStats) enemy.stats).goldDrop;
            }

            // Gọi hàm bọc (wrapper) để add_child xong mới setGlobalPosition
            this.callDeferred(new StringName("add_gold_deferred"), goldInstance, spawnPosition);
        }
    }

    @RegisterFunction
    public void add_gold_deferred(game.items.Coins gold, Vector2 pos) {
        addChild(gold);
        gold.setGlobalPosition(pos);
        GD.print("Vị trí của Xu SAU KHI addChild: " + gold.getGlobalPosition());
    }

    @RegisterFunction
    public void clean_arena() {
        if (spawner != null) {
            spawner.clearEnemies();
        }

        if (coinsBag == null)
            return;
        if (goldList.size() > 0) {
            Vector2 targetCenterPosition = coinsBag.getGlobalPosition().plus(coinsBag.getSize().div(2));

            for (game.items.Coins gold : goldList) {
                if (godot.global.GD.isInstanceValid(gold)) {
                    gold.setCollectionTarget(targetCenterPosition);
                }
            }
            goldList.clear();
        }
    }

    @RegisterFunction
    public void show_upgrades() {
        if (!godot.global.GD.isInstanceValid(Global.player))
            return;

        // --- GỌI HÀM TÍNH XÁC SUẤT ĐỂ IN RA DEBUG NHƯ TRONG VIDEO ---
        int currentWave = 1;
        if (spawner != null) {
            currentWave = spawner.waveIndex;
            Global.instance.calculateTierProbability(currentWave, Global.upgradeProbabilityConfig);
        }

        if (upgradePanel != null) {
            // Phát âm thanh lên cấp
            Global.instance.playSfx("res://assets/audio/level-up_sound.mp3");
            
            upgradePanel.loadUpgrades(currentWave);
            upgradePanel.show();
        }
    }

    @RegisterFunction
    public void _on_upgrade_selected() {
        if (upgradePanel != null) {
            upgradePanel.hide();
        }

        // Sau khi chọn xong Nâng cấp -> Mở Shop
        if (shopPanel != null) {
            int currentWave = 1;
            if (spawner != null) {
                currentWave = spawner.waveIndex;
            }
            shopPanel.loadShop(currentWave);
            shopPanel.show();
        }
    }

    @RegisterFunction
    public void _on_shop_next_wave() {
        if (shopPanel != null) {
            shopPanel.hide();
        }
        startNewWave();
    }

    @RegisterFunction
    public void startNewWave() {
        Global.gamePaused = false;

        // Phát âm thanh bắt đầu wave mới
        Global.instance.playSfx("res://assets/audio/start-wave_sound.mp3");
        Global.instance.stopBgm();

        if (spawner != null) {
            spawner.waveIndex += 1;
            spawner.startWave();
        }
        if (Global.player != null) {
            Global.player.updatePlayerNewWave();
        }
    }
}