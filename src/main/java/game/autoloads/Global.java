package game.autoloads;

import game.components.HitBoxComponent;
import game.entity.Player;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.annotation.RegisterSignal;
import godot.api.*;
import godot.annotation.RegisterClass;
import godot.core.Signal0;
import godot.core.Signal1;
import godot.core.Signal2;
import godot.global.GD;

import java.util.Random;

@RegisterClass
public class Global extends Node {

    // ĐÂY LÀ BIẾN TOÀN CẦU! Ai cũng có thể truy cập nó!
    public static Player player;
    public static ShaderMaterial FLASH_MATERIAL;
    public static Global instance;
    public static PackedScene floatingTextScene;
    public static boolean isAttack = true;
    public static boolean gamePaused = false;

    @RegisterSignal
    public Signal1<Node2D> onCreateBlockText = Signal1.create(this, "onCreateBlockText");

    @RegisterSignal
    public Signal2<Node2D, HitBoxComponent> onCreateDamageText = Signal2.create(this, "onCreateDamageText");

    @RegisterSignal
    public Signal0 onUpgradeSelected = Signal0.create(this, "onUpgradeSelected");

    @RegisterSignal
    public Signal2<Node2D, Float> onCreateHealText = Signal2.create(this, "onCreateHealText");


    @RegisterFunction
    @Override
    public void _ready() {
        // Load file .tres mà bạn đã tạo từ Shader ở bước 1
        FLASH_MATERIAL = (ShaderMaterial) ResourceLoader.load("res://effects/flash_material.tres");
        floatingTextScene = (PackedScene) ResourceLoader.load("res://effects/floating_text.tscn");
        isAttack = true;
        gamePaused = false;


        get_chance_sucess(0.5f);
        instance = this;
    }

    @RegisterFunction
    public static boolean get_chance_sucess(double chance){
        Random random = new Random();
        double randomFloat = random.nextFloat();
        if (randomFloat <= chance) return true;
        return false;
    }

    public enum UpgradeTier {
        COMMON,
        RARE,
        EPIC,
        LEGENDARY
    }

    // --- BỘ CẤU HÌNH XÁC SUẤT XUẤT HIỆN THẺ NÂNG CẤP ---
    public static class TierConfig {
        public int startWave;
        public float baseMulti;

        public TierConfig(int startWave, float baseMulti) {
            this.startWave = startWave;
            this.baseMulti = baseMulti;
        }
    }

    public static java.util.Map<String, TierConfig> upgradeProbabilityConfig = new java.util.HashMap<>();

    static {
        upgradeProbabilityConfig.put("rare", new TierConfig(2, 0.06f));
        upgradeProbabilityConfig.put("epic", new TierConfig(4, 0.02f));
        upgradeProbabilityConfig.put("legendary", new TierConfig(7, 0.0023f));
    }

    @RegisterFunction
    public float[] calculateTierProbability(int currentWave) {
        float commonChance = 0.0f;
        float rareChance = 0.0f;
        float epicChance = 0.0f;
        float legendaryChance = 0.0f;

        // 1. Kiểm tra Rare
        TierConfig rare = upgradeProbabilityConfig.get("rare");
        if (currentWave >= rare.startWave) {
            rareChance = Math.min(1.0f, (currentWave - (rare.startWave - 1)) * rare.baseMulti);
        }

        // 2. Kiểm tra Epic
        TierConfig epic = upgradeProbabilityConfig.get("epic");
        if (currentWave >= epic.startWave) {
            epicChance = Math.min(1.0f, (currentWave - (epic.startWave - 3)) * epic.baseMulti);
        }

        // 3. Kiểm tra Legendary
        TierConfig legendary = upgradeProbabilityConfig.get("legendary");
        if (currentWave >= legendary.startWave) {
            legendaryChance = Math.min(1.0f, (currentWave - (legendary.startWave - 6)) * legendary.baseMulti);
        }

        // 4. Áp dụng hệ số Luck (May mắn) của người chơi
        float playerLuck = 0.0f;
        if (player != null && player.stats instanceof game.resources.units.PlayerStats) {
            playerLuck = ((game.resources.units.PlayerStats) player.stats).luck;
        }
        
        // Ví dụ: Luck = 10 -> luckFactor = 1.1 (Tăng 10% cơ hội)
        float luckFactor = 1.0f + (playerLuck / 100.0f);

        rareChance *= luckFactor;
        epicChance *= luckFactor;
        legendaryChance *= luckFactor;

        // 5. Chuẩn hóa xác suất (Normalize) để tổng không vượt quá 1 (100%)
        float totalNonCommonChances = rareChance + epicChance + legendaryChance;
        if (totalNonCommonChances > 1.0f) {
            float scaleDown = 1.0f / totalNonCommonChances;
            rareChance *= scaleDown;
            epicChance *= scaleDown;
            legendaryChance *= scaleDown;
            totalNonCommonChances = 1.0f;
        }

        // 6. Tính xác suất của Common
        commonChance = 1.0f - totalNonCommonChances;

        // --- DEBUG RA CONSOLE (In kết quả ra giống trong video) ---
        String message = String.format(java.util.Locale.US, 
            "Wave: %d | Luck: %.1f | Chances -> Common: %.2f | Rare: %.2f | Epic: %.2f | Legendary: %.4f",
            currentWave, playerLuck, commonChance, rareChance, epicChance, legendaryChance);
        GD.print(message);

        // Trả về mảng 4 giá trị tỉ lệ
        return new float[] {
                Math.max(0.0f, commonChance),
                Math.max(0.0f, rareChance),
                Math.max(0.0f, epicChance),
                Math.max(0.0f, legendaryChance)
        };
    }
}