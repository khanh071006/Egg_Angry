package game.entity.enemies; // Sếp đổi package theo project nha

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
import godot.global.GD;

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
    private int waveIndex = 1; // Bắt đầu từ Wave 1
    private WaveData currentWaveData; // Lưu kịch bản hiện tại

    // Lưu quái vật đang sống bằng ArrayList của Java cho tốc độ bàn thờ
    private ArrayList<Node> spawnedEnemies = new ArrayList<>();

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
        GD.print("=== [1] BẮT ĐẦU CHẠY SPAWNER ===");
        // 1. DÙNG GETNODE ĐỂ TỰ BẮT CON (Phân biệt chữ hoa chữ thường sếp nhé!)
        spawnTimer = (Timer) getNode("SpawnTimer");
        waveTimer = (Timer) getNode("WaveTimer");

        if (spawnTimer == null) GD.printErr("LỖI: Không tìm thấy node tên 'SpawnTimer'!");
        if (waveTimer == null) GD.printErr("LỖI: Không tìm thấy node tên 'WaveTimer'!");

        // CHECK 1: Xem sếp đã kéo thả biến ở Inspector chưa
        if (spawnTimer == null) GD.printErr("LỖI NẶNG: Chưa kéo SpawnTimer vào Inspector!");
        if (waveTimer == null) GD.printErr("LỖI NẶNG: Chưa kéo WaveTimer vào Inspector!");
        if (wavesData.isEmpty()) GD.printErr("LỖI NẶNG: Mảng Waves Data trống trơn! Chưa kéo file .tres vào!");
        startWave();
    }

    // --- HÀM TÌM KỊCH BẢN (find_wave_data) ---
    private WaveData findWaveData() {
        GD.print("- [2] Đang quét kịch bản cho Wave: " + waveIndex);
        for (int i = 0; i < wavesData.size(); i++) {
            WaveData wave = wavesData.get(i);

            // THAY VÌ GỌI isValidIndex, JAVA TỰ DÙNG getFrom() VÀ getTo() ĐỂ SO SÁNH!
            if (wave != null && waveIndex >= wave.getFrom() && waveIndex <= wave.getTo()) {
                GD.print("  -> ĐÃ TÌM THẤY KỊCH BẢN HỢP LỆ!");
                return wave;
            }
        }
        return null; // Không tìm thấy thì trả về null
    }

    // --- HÀM BẮT ĐẦU ĐỢT (start_wave) ---
    public void startWave() {
        currentWaveData = findWaveData();

        if (currentWaveData == null) {
            GD.printErr("No valid wave data! Không tìm thấy Wave: " + waveIndex);
            if (spawnTimer != null) spawnTimer.stop();
            if (waveTimer != null) waveTimer.stop();
            return;
        }
        GD.print("- [3] Khởi động WaveTimer với: " + currentWaveData.getWaveTime() + " giây");
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
        GD.print("- [5] ĐỒNG HỒ ĐẺ QUÁI ĐÃ BẤM GIỜ!");
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
            }

            // Gắn vào Arena (Node cha của Spawner)
            getParent().addChild(enemyInstanceNode);

            // Thêm vào danh sách quản lý
            spawnedEnemies.add(enemyInstanceNode);
        }
    }

    private void clearEnemies() {
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
        //waveIndex++;
        //startWave();
    }
}