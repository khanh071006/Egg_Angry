package game.items.weapons;

import game.autoloads.Global;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Node2D;

@RegisterClass
public class WeaponBehavior extends Node2D {
    @Export
    @RegisterProperty
    public Weapon weapon;

    public boolean critical = false;

    @RegisterFunction
    public double getDamage(){
        // 1. Tính sát thương cơ bản (Vũ khí + Player)
        // Sếp thay Global.playerStats bằng class quản lý stat của sếp nhé
        double damage = weapon.data.stats.damage + Global.player.stats.damage;

        // 2. Kiểm tra chí mạng (Crit Chance)
        double critChance = weapon.data.stats.critChance;

        // Giả sử sếp có hàm getChanceSuccess trả về true/false dựa trên tỉ lệ %
        if (Global.get_chance_sucess(critChance)) {
            critical = true;
            // Nhân thêm sát thương chí mạng
            damage = damage * weapon.data.stats.critDamage;

            // Dùng Math.ceil để làm tròn lên (VD: 1.45 -> 2.0) giống hàm ceil() trong video
            damage = Math.ceil(damage);
        }

        return damage;
    }

    // Hàm ảo để các class con tự viết logic đánh riêng
    @RegisterFunction
    public void executeAttack(){}

    @RegisterFunction
    public void applyLifesteal() {
        if (Global.player == null || weapon == null || weapon.data == null) return;

        float playerLifesteal = 0.0f;
        if (Global.player.stats != null) {
            playerLifesteal = Global.player.stats.lifesteal;
        }

        // lifesteal đi từ 1-100, nên chia cho 100 để ra số thập phân (0.0 - 1.0)
        float stealChance = (playerLifesteal / 100.0f) + weapon.data.stats.lifesteal;

        boolean canSteal = Global.get_chance_sucess(stealChance);

        if (canSteal && godot.global.GD.isInstanceValid(Global.player)) {
            Global.instance.onCreateHealText.emit(Global.player, 1.0f);
            if (Global.player.healthComponent != null) {
                Global.player.healthComponent.heal(1.0f);
            }
        }
    }
}
