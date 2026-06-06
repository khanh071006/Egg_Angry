package game.entity.enemies.spawner;

import game.entity.enemies.core.Enemy; // Sếp đổi package theo project nha

import game.Helper.GodotHelper;
import game.autoloads.Global;
import game.entity.BaseUnit;
import game.resources.units.UnitStats;
import game.resources.waves.WaveUnitData;
import godot.annotation.*;
import godot.api.*;
import godot.core.VariantArray;
import godot.core.Vector2;
import game.resources.waves.WaveData; // Import file Kotlin vào đây
import game.resources.waves.WaveData; // Import file Kotlin vào đây
import godot.global.GD;
import godot.core.Signal0;

import java.util.ArrayList;

@RegisterClass
public class Spawner extends Node2D {

    // --- CHỈ LÀ BIẾN NỘI BỘ, KHÔNG HIỆN LÊN INSPECTOR NỮA ---
    private Timer spawnTimer;
    private Timer waveTimer;

    // Gọi hàm khởi tạo mảng của Kotlin để không bị lỗi KSP!
    // Java thò tay sang nhà Kotlin để gọi cái hàm tạo mảng về! Xong phim!
    @Export
    @RegisterProperty
    public VariantArray<WaveData> wavesData = GodotHelper.createWaveDataArray();

    // Spawn Area Size (1000, 500) như trong video
    @Export @RegisterProperty
    public Vector2 spawnAreaSize = new Vector2(1000, 500);

    // --- BIẾN LOGIC (Chỉ chạy ngầm trong Java) ---
    public int waveIndex = 1; // Bắt đầu từ Wave 1
    private WaveData currentWaveData; // Lưu kịch bản hiện tại

    @RegisterSignal
    public Signal0 onWaveCompleted = Signal0.create(this, "onWaveCompleted");

    // Lưu quái vật đang sống bằng ArrayList của Java cho tốc độ bàn thờ
    private ArrayList<Node> spawnedEnemies = new ArrayList<>();

    // Lớp nội bộ để lưu trữ quái đang chờ spawn
    private static class PendingSpawn {
        public Node spawnAnim;
        public Node enemy;
        public double timeLeft;

        public PendingSpawn(Node spawnAnim, Node enemy, double timeLeft) {
            this.spawnAnim = spawnAnim;
            this.enemy = enemy;
            this.timeLeft = timeLeft;
        }
    }
    // Danh sách chờ đẻ quái
    private ArrayList<PendingSpawn> pendingSpawns = new ArrayList<>();

    public String getWaveText() {
        return "Wave " + waveIndex;
    }

    public String getWaveTimerText() {
        if (waveTimer == null) return "0";
        // Lấy thời gian còn lại, ép kiểu về số nguyên, không cho phép âm
        int timeLeft = (int) Math.max(0, waveTimer.getTimeLeft());
        return String.valueOf(timeLeft);
    }

    //Tọa độ random
    private Vector2 getRandomSpawnPosition() {
        // Trừ 0.5 để nó lấy Random cả mảng âm và dương xung quanh điểm 0
        float randomX = (float) (2 * (Math.random() - 0.5) * spawnAreaSize.getX());
        float randomY = (float) (2 * (Math.random() - 0.5) * spawnAreaSize.getY());
        return new Vector2(randomX, randomY);
    }


    @RegisterFunction
    @Override
    public void _ready() {
        // 1. DÙNG GETNODE ĐỂ TỰ BẮT CON (Phân biệt chữ hoa chữ thường sếp nhé!)
        spawnTimer = (Timer) getNode("SpawnTimer");
        waveTimer = (Timer) getNode("WaveTimer");

        if (spawnTimer == null) GD.printErr("LỖI: Không tìm thấy node tên 'SpawnTimer'!");
        if (waveTimer == null) GD.printErr("LỖI: Không tìm thấy node tên 'WaveTimer'!");

        // CHECK 1: Xem sếp đã kéo thả biến ở Inspector chưa
        if (spawnTimer == null) GD.printErr("LỖI NẶNG: Chưa kéo SpawnTimer vào Inspector!");
        if (waveTimer == null) GD.printErr("LỖI NẶNG: Chưa kéo WaveTimer vào Inspector!");
        if (wavesData.isEmpty()) GD.printErr("LỖI NẶNG: Mảng Waves Data trống trơn! Chưa kéo file .tres vào!");
    }

    // --- HÀM TÌM KỊCH BẢN (find_wave_data) ---
    private WaveData findWaveData() {
        for (int i = 0; i < wavesData.size(); i++) {
            WaveData wave = wavesData.get(i);

            // THAY VÌ GỌI isValidIndex, JAVA TỰ DÙNG getFrom() VÀ getTo() ĐỂ SO SÁNH!
            if (wave != null && waveIndex >= wave.getFrom() && waveIndex <= wave.getTo()) {
                return wave;
            }
        }
        return null; // Không tìm thấy thì trả về null
    }

    // --- HÀM BẮT ĐẦU ĐỢT (start_wave) ---
    public void startWave() {
        Global.isAttack = true;
        currentWaveData = findWaveData();

        if (currentWaveData == null) {
            GD.printErr("No valid wave data! Không tìm thấy Wave: " + waveIndex);
            if (spawnTimer != null) spawnTimer.stop();
            if (waveTimer != null) waveTimer.stop();
            return;
        }
        // Cài đặt thời gian cho Wave (Mặc định 20s)
        waveTimer.setWaitTime(currentWaveData.getWaveTime());
        waveTimer.start();

        // Kích hoạt đồng hồ đẻ quái
        setSpawnTimer();
    }

    // --- HÀM CÀI ĐẶT NHỊP ĐẺ QUÁI (set_spawn_timer) ---
    private void setSpawnTimer() {
        if (currentWaveData == null || spawnTimer == null) return;

        // Kiểm tra loại đẻ quái: 0 là Fixed, 1 là Random
        if (currentWaveData.getSpawnType() == WaveData.SpawnType.FIXED) {
            // FIXED (Cố định)
            spawnTimer.setWaitTime(currentWaveData.getFixedSpawnTime());
        } else {
            // RANDOM (Ngẫu nhiên từ Min đến Max)
            double minT = currentWaveData.getMinSpawnTime();
            double maxT = currentWaveData.getMaxSpawnTime();
            // Hàm tính Random của Java
            double randomTime = minT + (Math.random() * (maxT - minT));
            spawnTimer.setWaitTime(randomTime);
        }

        spawnTimer.start();
    }

    // --- HÀM ĐẺ QUÁI VẬT (spawn_enemy) ---
    private void spawnEnemy() {
        if (currentWaveData == null) return;
        Vector2 spawn_pos = getRandomSpawnPosition();
        // Bốc ngẫu nhiên 1 file quái từ Kịch bản (Kotlin lo tính xác suất)
        PackedScene enemyScene = currentWaveData.getRandomUnit();

        if (enemyScene != null) {
            // Tạo ra con quái thật sự
            Node enemyInstanceNode = enemyScene.instantiate();

            if (enemyInstanceNode instanceof BaseUnit) {
                BaseUnit enemyInstance = (BaseUnit) enemyInstanceNode;
                UnitStats originalStats = enemyInstance.stats;

                // Tạo một bản sao của stats để không thay đổi file gốc
                UnitStats newStats = (UnitStats) originalStats.duplicate();

                // Tính toán và áp dụng chỉ số mới
                float healthIncrease = newStats.healthIncreasePerWave * (waveIndex - 1);
                float damageIncrease = newStats.damageIncreasePerWave * (waveIndex - 1);
                newStats.health += healthIncrease;
                newStats.damage += damageIncrease;

                // Gán stats mới cho quái
                enemyInstance.stats = newStats;
            }


            if (enemyInstanceNode instanceof Node2D) {
                ((Node2D) enemyInstanceNode).setGlobalPosition(spawn_pos);
                // Ẩn quái và vô hiệu hóa tạm thời trong lúc chờ animation
                ((Node2D) enemyInstanceNode).setVisible(false);
            }
            enemyInstanceNode.setProcessMode(Node.ProcessMode.DISABLED);

            // Gắn vào Arena (Node cha của Spawner)
            getParent().addChild(enemyInstanceNode);

            // Thêm vào danh sách quản lý
            spawnedEnemies.add(enemyInstanceNode);

            // --- SPAWN ANIMATION EFFECT ---
            PackedScene spawnEffectScene = (PackedScene) godot.api.ResourceLoader.load("res://scenes/effects/enemy_spawn_effect.tscn");
            if (spawnEffectScene != null) {
                Node spawnAnim = spawnEffectScene.instantiate();
                if (spawnAnim instanceof Node2D) {
                    ((Node2D) spawnAnim).setGlobalPosition(spawn_pos);
                }
                getParent().addChild(spawnAnim);
                
                // Thêm vào danh sách chờ xử lý (0.8 giây là thời gian của animation spawn)
                pendingSpawns.add(new PendingSpawn(spawnAnim, enemyInstanceNode, 0.8));
            } else {
                enemyInstanceNode.setProcessMode(Node.ProcessMode.INHERIT);
                if (enemyInstanceNode instanceof Node2D) ((Node2D) enemyInstanceNode).setVisible(true);
            }
        }
    }
    
    @RegisterFunction
    @Override
    public void _process(double delta) {
        if (Global.gamePaused) return;

        // Xử lý đếm ngược spawn
        for (int i = pendingSpawns.size() - 1; i >= 0; i--) {
            PendingSpawn p = pendingSpawns.get(i);
            p.timeLeft -= delta;
            
            if (p.timeLeft <= 0) {
                if (godot.global.GD.isInstanceValid(p.spawnAnim)) {
                    p.spawnAnim.queueFree();
                }
                if (godot.global.GD.isInstanceValid(p.enemy)) {
                    if (p.enemy instanceof Node2D) ((Node2D) p.enemy).setVisible(true);
                    p.enemy.setProcessMode(Node.ProcessMode.INHERIT);
                }
                pendingSpawns.remove(i);
            }
        }
    }

    public void clearEnemies() {
        // Dọn dẹp các quái đang chờ spawn (dấu X chưa ra quái)
        for (PendingSpawn p : pendingSpawns) {
            if (GD.isInstanceValid(p.spawnAnim)) {
                p.spawnAnim.queueFree();
            }
            if (GD.isInstanceValid(p.enemy)) {
                p.enemy.queueFree();
                spawnedEnemies.remove(p.enemy); // Xóa khỏi danh sách để tránh gọi destroyEnemy
            }
        }
        pendingSpawns.clear();

        if (spawnedEnemies.size() == 0) {
            return;
        }

        for (Node enemyNode : spawnedEnemies) {
            if (GD.isInstanceValid(enemyNode)) {
                Enemy enemy = (Enemy) enemyNode;
                enemy.destroyEnemy();
            }
        }
        spawnedEnemies.clear();
    }

    @RegisterFunction
    public void _on_spawn_timer_timeout() {

        if (currentWaveData == null || waveTimer.isStopped()) {
            spawnTimer.stop();
            return;
        }

        spawnEnemy();
        // Đẻ xong thì lên dây cót cho con tiếp theo
        setSpawnTimer();
    }

    @RegisterFunction
    public void _on_wave_timer_timeout() {
        if (spawnTimer != null) {
            spawnTimer.stop();
        }
        Global.gamePaused = true;
        clearEnemies();
        
        // --- GỌI HÀM HARVESTING NHẬN XU KHI HẾT WAVE ---
        Global.instance.getHarvestingCoins();
        
        onWaveCompleted.emit();
    }
}
