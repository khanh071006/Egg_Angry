package game.entity.enemies;

import game.helper.GodotHelper;
import godot.annotation.*;
import godot.api.*;
import godot.core.VariantArray;
import godot.core.Vector2;
import game.resources.waves.WaveData;
import godot.global.GD;

import java.util.ArrayList;

@RegisterClass
public class Spawner extends Node2D {

    private Timer spawnTimer;
    private Timer waveTimer;

    @Export
    @RegisterProperty
    public VariantArray<WaveData> wavesData = GodotHelper.createWaveDataArray();

    @Export
    @RegisterProperty
    public Vector2 spawnAreaSize = new Vector2(1000, 500);

    private int waveIndex = 1;
    private WaveData currentWaveData;
    private ArrayList<Node> spawnedEnemies = new ArrayList<>();

    @RegisterFunction
    @Override
    public void _ready() {
        GD.print("=== [1] SPAWNER STARTED ===");
        spawnTimer = (Timer) getNode("SpawnTimer");
        waveTimer = (Timer) getNode("WaveTimer");

        if (spawnTimer == null || waveTimer == null) {
            GD.printErr("LỖI: Không tìm thấy node SpawnTimer hoặc WaveTimer!");
            return;
        }

        if (wavesData.isEmpty()) {
            GD.printErr("LỖI: Mảng Waves Data trống!");
        }
        startWave();
    }

    private WaveData findWaveData() {
        for (int i = 0; i < wavesData.size(); i++) {
            WaveData wave = wavesData.get(i);
            // Sử dụng trực tiếp wave.from và wave.to nhờ @JvmField trong Kotlin
            if (wave != null && waveIndex >= wave.from && waveIndex <= wave.to) {
                return wave;
            }
        }
        return null;
    }

    public void startWave() {
        currentWaveData = findWaveData();

        if (currentWaveData == null) {
            GD.printErr("No valid wave data for Wave: " + waveIndex);
            if (spawnTimer != null) spawnTimer.stop();
            if (waveTimer != null) waveTimer.stop();
            return;
        }
        
        waveTimer.setWaitTime(currentWaveData.waveTime);
        waveTimer.start();
        setSpawnTimer();
    }

    private void setSpawnTimer() {
        if (currentWaveData == null || spawnTimer == null)
            return;

        if (currentWaveData.spawnType == WaveData.SpawnType.FIXED) {
            spawnTimer.setWaitTime(currentWaveData.fixedSpawnTime);
        } else {
            double minT = currentWaveData.minSpawnTime;
            double maxT = currentWaveData.maxSpawnTime;
            double randomTime = minT + (Math.random() * (maxT - minT));
            spawnTimer.setWaitTime(randomTime);
        }

        spawnTimer.start();
    }

    @RegisterFunction
    public void _on_spawn_timer_timeout() {
        if (currentWaveData == null)
            return;

        PackedScene enemyScene = currentWaveData.getRandomUnit();

        if (enemyScene != null) {
            Node enemyInstance = enemyScene.instantiate();
            if (enemyInstance instanceof Node2D) {
                ((Node2D) enemyInstance).setGlobalPosition(new Vector2(0, 0));
            }
            getParent().addChild(enemyInstance);
            spawnedEnemies.add(enemyInstance);
        }

        setSpawnTimer();
    }
}