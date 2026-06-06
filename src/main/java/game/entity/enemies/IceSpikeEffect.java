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
        godot.global.GD.print("IceSpikeEffect: onAreaEntered called with area = " + area);
        if (area instanceof HurtBoxComponent) {
            godot.global.GD.print("IceSpikeEffect: area IS HurtBoxComponent!");
            HurtBoxComponent hurtbox = (HurtBoxComponent) area;
            onHitPlayer(hurtbox);
        } else {
            godot.global.GD.print("IceSpikeEffect: area is NOT HurtBoxComponent. Type is " + (area != null ? area.getClass().getName() : "null"));
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
