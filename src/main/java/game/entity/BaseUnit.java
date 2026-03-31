package game.entity;

import game.autoloads.Global;
import game.components.HealthComponent;
import game.components.HitBoxComponent;
import game.resources.PlayerStats;
import game.resources.UnitStats;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.*;
import godot.global.GD;

@RegisterClass
public class BaseUnit extends Area2D { // Đổi tên class ở đây
    //add stat
    @Export
    @RegisterProperty
    public UnitStats stats;

    // --- CÁC BIẾN CHO FlashEffects ---
    private Timer flashTimer;

	protected Node2D visuals;
	protected Sprite2D sprite;
	protected AnimationPlayer animPlayer;
    private HealthComponent healthComponent;
	@RegisterFunction
	@Override
	public void _ready() {
		visuals = (Node2D) getNode("%Visuals");
		sprite = (Sprite2D) getNode("%Sprite");
		animPlayer = (AnimationPlayer) getNode("AnimationPlayer");


        healthComponent = (HealthComponent) getNode("HealthComponent");


        // Gọi setup và truyền biến stats của Unit vào (như video)
        if (healthComponent != null && this.stats != null) {
            healthComponent.setup(this.stats);
        }

        // Setup Timer bằng code hoặc kéo thả trong Editor
        flashTimer = (Timer) getNode("FlashTimer");
        flashTimer.setWaitTime(0.2);
        flashTimer.setOneShot(true);

	}

    @RegisterFunction
    public void setFlashMaterial() {
        // 1. Gắn Shader trắng vào Sprite
        if (sprite != null && Global.FLASH_MATERIAL != null) {
            sprite.setMaterial(Global.FLASH_MATERIAL);
        }

        // 2. Chạy đồng hồ đếm ngược 0.2 giây
        flashTimer.start();
    }

    @RegisterFunction
    public void _on_hurtbox_component_on_damage(HitBoxComponent hitbox) {
        // 2. Kiểm tra máu trước khi trừ
        if (healthComponent.currentHealth <= 0) {
            return;
        }
        //block
        PlayerStats playerStats;
        boolean blocked = Global.get_chance_sucess(stats.blockchance / 100);
        if (blocked){
            GD.print("Blocked");
            return;
        }


        healthComponent.takeDamage(hitbox.damage);

        // Gọi hiệu ứng chớp trắng
        setFlashMaterial();

        GD.print(getName()+" "+ healthComponent.currentHealth);
    }

    @RegisterFunction
    public void _on_flash_timer_timeout(){
        if (sprite != null) {
            sprite.setMaterial(null);
        }
    }
}
