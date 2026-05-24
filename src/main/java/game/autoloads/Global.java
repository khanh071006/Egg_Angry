package game.autoloads;

import game.components.HitBoxComponent;
import game.entity.Player;
import godot.annotation.*;
import godot.api.*;
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
    public static ShaderMaterial OUTLINE_MATERIAL;
    public static Global instance;
    public static PackedScene floatingTextScene;
    public static boolean isAttack = true;
    public static boolean gamePaused = false;
    public static int coins = 0;

    // --- CÁC STYLE CHO THẺ NÂNG CẤP ---
    public godot.api.StyleBoxFlat commonStyle;
    public godot.api.StyleBoxFlat rareStyle;
    public godot.api.StyleBoxFlat epicStyle;
    public godot.api.StyleBoxFlat legendaryStyle;

    @RegisterSignal
    public Signal1<Node2D> onCreateBlockText = Signal1.create(this, "onCreateBlockText");

    @RegisterSignal
    public Signal2<Node2D, HitBoxComponent> onCreateDamageText = Signal2.create(this, "onCreateDamageText");

    @RegisterSignal
    public Signal0 onUpgradeSelected = Signal0.create(this, "onUpgradeSelected");

    @RegisterSignal
    public Signal2<Node2D, Float> onCreateHealText = Signal2.create(this, "onCreateHealText");

    @RegisterSignal
    public Signal1<game.entity.enemies.Enemy> onEnemyDied = Signal1.create(this, "onEnemyDied");

    public PackedScene itemCardScene;

    public PackedScene coinsScene;

    public java.util.List<game.resources.items.weapons.ItemWeapon> equippedWeapons = new java.util.ArrayList<>();
    public game.resources.items.weapons.ItemWeapon selectedWeapon;


    @RegisterFunction
    @Override
    public void _ready() {
        // Load file .tres mà bạn đã tạo từ Shader ở bước 1
        FLASH_MATERIAL = (ShaderMaterial) ResourceLoader.load("res://effects/flash_material.tres");
        OUTLINE_MATERIAL = (ShaderMaterial) ResourceLoader.load("res://shaders/outline_material.tres");
        floatingTextScene = (PackedScene) ResourceLoader.load("res://effects/floating_text.tscn");
        coinsScene = (PackedScene) ResourceLoader.load("res://scenes/coins/coins.tscn");
        itemCardScene = (PackedScene) ResourceLoader.load("res://scenes/ui/item_card/item_card.tscn");
        
        // Tự động load các Style màu thẻ từ thư mục styles (khỏi cần kéo thả tay)
        commonStyle = (godot.api.StyleBoxFlat) ResourceLoader.load("res://styles/common_style.tres");
        rareStyle = (godot.api.StyleBoxFlat) ResourceLoader.load("res://styles/rare_style.tres");
        epicStyle = (godot.api.StyleBoxFlat) ResourceLoader.load("res://styles/epic_style.tres");
        legendaryStyle = (godot.api.StyleBoxFlat) ResourceLoader.load("res://styles/legendary_style.tres");

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
    public static java.util.Map<String, TierConfig> shopProbabilityConfig = new java.util.HashMap<>();

    static {
        upgradeProbabilityConfig.put("rare", new TierConfig(2, 0.06f));
        upgradeProbabilityConfig.put("epic", new TierConfig(4, 0.02f));
        upgradeProbabilityConfig.put("legendary", new TierConfig(7, 0.0023f));

        shopProbabilityConfig.put("rare", new TierConfig(2, 0.1f));
        shopProbabilityConfig.put("epic", new TierConfig(4, 0.06f));
        shopProbabilityConfig.put("legendary", new TierConfig(7, 0.01f));
    }

    @RegisterFunction
    public float[] calculateTierProbability(int currentWave, java.util.Map<String, TierConfig> config) {
        float commonChance = 0.0f;
        float rareChance = 0.0f;
        float epicChance = 0.0f;
        float legendaryChance = 0.0f;

        // 1. Kiểm tra Rare
        TierConfig rare = config.get("rare");
        if (currentWave >= rare.startWave) {
            rareChance = Math.min(1.0f, (currentWave - (rare.startWave - 1)) * rare.baseMulti);
        }

        // 2. Kiểm tra Epic
        TierConfig epic = config.get("epic");
        if (currentWave >= epic.startWave) {
            epicChance = Math.min(1.0f, (currentWave - (epic.startWave - 3)) * epic.baseMulti);
        }

        // 3. Kiểm tra Legendary
        TierConfig legendary = config.get("legendary");
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

    // --- HÀM LẤY STYLE DỰA TRÊN ĐỘ HIẾM (TIER) ---
    public godot.api.StyleBoxFlat getTierStyle(UpgradeTier itemTier) {
        if (itemTier == null) return legendaryStyle;

        switch (itemTier) {
            case COMMON:
                return commonStyle;
            case RARE:
                return rareStyle;
            case EPIC:
                return epicStyle;
            default:
                return legendaryStyle;
        }
    }

    // --- HÀM LẤY MÀU VIỀN DỰA TRÊN ĐỘ HIẾM (TIER) ---
    public godot.core.Color getTierColor(UpgradeTier tier) {
        if (tier == null) return new godot.core.Color(1.0f, 1.0f, 1.0f, 1.0f); // Default white
        switch (tier) {
            case RARE:
                return new godot.core.Color(0.0f, 0.557f, 0.741f, 1.0f);
            case EPIC:
                return new godot.core.Color(0.478f, 0.251f, 0.71f, 1.0f);
            case LEGENDARY:
                return new godot.core.Color(0.906f, 0.212f, 0.212f, 1.0f);
            default:
                return new godot.core.Color(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    // --- LOGIC HARVESTING (THU THẬP XU) ---
    @RegisterFunction
    public void getHarvestingCoins() {
        if (player != null && player.stats != null) {
            coins += (int) player.stats.harvesting;
            GD.print("Đã thu thập: " + (int)player.stats.harvesting + " xu. Tổng xu: " + coins);
        }
    }

    @RegisterFunction
    public godot.core.VariantArray<game.resources.items.ItemBase> selectItemsForOffer(
            godot.core.VariantArray<? extends game.resources.items.ItemBase> itemPool, 
            int currentWave,
            java.util.Map<String, TierConfig> config) {

        float[] tierChances = calculateTierProbability(currentWave, config);

        float legendaryLimit = tierChances[3];
        float epicLimit = legendaryLimit + tierChances[2];
        float rareLimit = epicLimit + tierChances[1];

        java.util.List<game.resources.items.ItemBase> offerItemsList = new java.util.ArrayList<>();
        int failsafe = 0; // Để tránh vòng lặp vô tận nếu mảng ItemPool ít hơn 4 món

        GD.print("Debug: Bắt đầu selectItemsForOffer. itemPool size = " + itemPool.size());

        while (offerItemsList.size() < 4 && failsafe < 100) {
            failsafe++;
            float roll = (float) Math.random(); // Ngẫu nhiên từ 0.0 đến 1.0
            int chosenTierIndex = 0; // Mặc định là Common (0)

            if (roll < legendaryLimit) {
                chosenTierIndex = 3; // Legendary
            } else if (roll < epicLimit) {
                chosenTierIndex = 2; // Epic
            } else if (roll < rareLimit) {
                chosenTierIndex = 1; // Rare
            }

            int currentSearchTierIndex = chosenTierIndex;
            java.util.List<game.resources.items.ItemBase> potentialItemsList = new java.util.ArrayList<>();

            while (potentialItemsList.isEmpty() && currentSearchTierIndex >= 0) {
                // Lọc những thẻ có phẩm chất (tier) trùng với currentSearchTierIndex
                for (int i = 0; i < itemPool.size(); i++) {
                    game.resources.items.ItemBase item = itemPool.get(i);
                    if (item != null && item.itemTier != null && item.itemTier.ordinal() == currentSearchTierIndex) {
                        potentialItemsList.add(item);
                    }
                }

                if (potentialItemsList.isEmpty()) {
                    currentSearchTierIndex--; // Giảm 1 cấp nếu không tìm thấy thẻ ở phẩm chất đó
                } else {
                    break;
                }
            }

            if (!potentialItemsList.isEmpty()) {
                // Chọn ngẫu nhiên 1 phần tử
                int randomIndex = (int) (Math.random() * potentialItemsList.size());
                game.resources.items.ItemBase selectedItem = potentialItemsList.get(randomIndex);

                // Kiểm tra xem thẻ đã được chọn chưa (Chống trùng lặp)
                if (!offerItemsList.contains(selectedItem)) {
                    offerItemsList.add(selectedItem);
                }
            }
        }

        // Chuyển List thành VariantArray để Godot dùng
        godot.core.VariantArray<game.resources.items.ItemBase> finalArray = game.Helper.GodotHelper.createItemBaseArray();
        
        GD.print("Debug: Vòng lặp kết thúc, offerItemsList size = " + offerItemsList.size());
        
        for (game.resources.items.ItemBase item : offerItemsList) {
            finalArray.append(item);
        }
        
        GD.print("Debug: Sau khi copy sang finalArray, size = " + finalArray.size());
        
        return finalArray;
    }
}