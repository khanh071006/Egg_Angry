package game.entity.enemies;

import game.components.HitBoxComponent;
import game.components.HurtBoxComponent;
import game.entity.Player;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.api.Node;
import godot.core.Callable;

@RegisterClass
public class IceSpikeEffect extends Node {
    
    private HitBoxComponent hitbox;

    @RegisterFunction
    @Override
    public void _ready() {
        // Không cần connect qua code nữa, nối trực tiếp từ tscn
    }

    @RegisterFunction
    public void onAreaEntered(godot.api.Area2D area) {
        if (area instanceof HurtBoxComponent) {
            HurtBoxComponent hurtbox = (HurtBoxComponent) area;
            onHitPlayer(hurtbox);
        }
    }

    @RegisterFunction
    public void onHitPlayer(HurtBoxComponent hurtbox) {
        if (hurtbox == null || hurtbox.getParent() == null) return;
        
        // Cọc băng đâm trúng -> Làm chậm Player
        if (hurtbox.getParent() instanceof Player) {
            Player player = (Player) hurtbox.getParent();
            player.applySlow(2.0f, 0.5f); // Làm chậm 50% trong 2 giây
        }
    }
}
