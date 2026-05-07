package game.entity.enemies; // Sếp đổi package theo project nha

import game.Helper.GodotHelper;
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
    // Mảng lưu trữ quái vật (Dùng Java ArrayList cho sướng)
    // YouTuber bảo lưu lại để sau này tăng máu/đam khi qua màn
    private ArrayList<Node> spawnEnemies = new ArrayList<>();

    // --- BIẾN LOGIC (Chỉ chạy ngầm trong Java) ---
    private int waveIndex = 1; // Bắt đầu từ Wave 1
    private WaveData currentWaveData; // Lưu kịch bản hiện tại

    // Lưu quái vật đang sống bằng ArrayList của Java cho tốc độ bàn thờ
    private ArrayList<Node> spawnedEnemies = new ArrayList<>();

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
    @RegisterFunction
    public void _on_spawn_timer_timeout() {
        GD.print("=== [6] TÍN HIỆU TIMEOUT KÊU! HÀM SPAWN ĐÃ CHẠY! ===");
        if (currentWaveData == null) return;

        // Bốc ngẫu nhiên 1 file quái từ Kịch bản (Kotlin lo tính xác suất)
        PackedScene enemyScene = currentWaveData.getRandomUnit();

        if (enemyScene != null) {
            // Tạo ra con quái thật sự
            Node enemyInstance = enemyScene.instantiate();

            // Tạm thời để quái xuất hiện ở giữa map (Vector2.ZERO)
            if (enemyInstance instanceof Node2D) {
                ((Node2D) enemyInstance).setGlobalPosition(new Vector2(0, 0));
            }

            // Gắn vào Arena (Node cha của Spawner)
            getParent().addChild(enemyInstance);

            // Thêm vào danh sách quản lý
            spawnedEnemies.add(enemyInstance);
        }

        // Đẻ xong thì lên dây cót cho con tiếp theo
        setSpawnTimer();
    }
}