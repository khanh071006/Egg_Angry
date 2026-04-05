package game.items.weapons.melee;

import game.components.HitBoxComponent;
import game.items.weapons.WeaponBehavior;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Node;
import godot.api.Node2D;
import godot.api.Tween;
import godot.core.Callable;
import godot.core.StringName;
import godot.core.Vector2;
import godot.global.GD;

@RegisterClass
public class MeleeBehavior extends WeaponBehavior {

    @Export
    @RegisterProperty
    public HitBoxComponent hitbox;

    private double attackTimer = 0.0;
    private boolean isWaiting = false;


    @RegisterFunction

    public void executeAttack(){
        weapon.isAttacking = true;

        // Tạo Tween để làm animation chuyển động
        Tween tween = createTween();

//        // Kết nối signal trước khi bắt đầu chạy animation cho chắc cú
//        Callable cleanup = Callable.create(this, new StringName("onAttackFinished"));
//        tween.getFinished().connect(cleanup,0);

        Vector2 startPos = weapon.attackStartPosition;

        // 1. Tính toán điểm lùi lại (Recoil)
        Vector2 recoilPos = new Vector2(
                startPos.getX() - weapon.data.stats.recoil,
                startPos.getY()
        );

        // 2. Tính toán điểm chọc tới (Attack / Max Range)
        Vector2 attackPos = new Vector2(
                startPos.getX() + weapon.data.stats.maxRange,
                startPos.getY()
        );

        // --- BẮT ĐẦU CHẠY TWEEN ---

        // Pha 1: Giật lùi về sau
        tween.tweenProperty(
                weapon.getSprite(), // Cái Sprite2D của vũ khí
                "position",
                recoilPos,
                weapon.data.stats.recoilDuration
        );

        // Bật Hitbox và nhét Data vào (Sát thương, lực đẩy lùi)
        hitbox.enable();
        // Giả sử hàm setupHitbox của sếp nhận (Damage, Knockback, Kẻ ra đòn)
        hitbox.setup((float) this.getDamage(), this.critical ,weapon.data.stats.knockback, (Node2D) weapon.getParent());

        // Pha 2: Chọc mạnh tới trước
        tween.tweenProperty(
                weapon.getSprite(),
                "position",
                attackPos,
                weapon.data.stats.attackDuration
        );

        // Pha 3: Thu tay về vị trí cũ
        tween.tweenProperty(
                weapon.getSprite(),
                "position",
                startPos,
                weapon.data.stats.backDuration
        );

        // Cài đặt đồng hồ và bật cờ chờ
        double totalTime = weapon.data.stats.recoilDuration + weapon.data.stats.attackDuration + weapon.data.stats.backDuration;
        this.attackTimer = totalTime;
        this.isWaiting = true;
    }
    @Override
    @RegisterFunction
    public void _process(double delta) {
        // Tự đếm giờ
        if (isWaiting) {
            attackTimer -= delta;
            if (attackTimer <= 0) {
                isWaiting = false;
                onAttackFinished(); // Tự gọi hàm, không nhờ thằng Godot nào hết!
            }
        }
    }
    // Hàm gọi khi đánh xong
    @RegisterFunction
    public void onAttackFinished() {
        this.critical = false;
        weapon.isAttacking = false; // TRẢ TỰ DO cho vũ khí
        hitbox.disable();
    }
}
