package game.resources;

import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterProperty;

@RegisterClass
public class EnemyStats extends UnitStats{
    @Export
    @RegisterProperty
    public float goldDrop = 0.0f;

    @Export
    @RegisterProperty
    public float blockChance = 0.0f;

    public EnemyStats() {
        super(); // Gọi hàm khởi tạo của Cha
        this.type = UnitType.ENEMY; // Tự động khóa chặt loại là PLAYER, khỏi sợ điền nhầm!
    }
}
