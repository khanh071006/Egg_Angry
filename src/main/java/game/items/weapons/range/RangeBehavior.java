package game.items.weapons.range;

import game.items.weapons.WeaponBehavior;
import game.items.weapons.projectiles.Projectile;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Marker2D;
import godot.api.Node;
import godot.api.Node2D;
import godot.api.Tween;
import godot.core.Vector2;
import godot.global.GD;

@RegisterClass
public class RangeBehavior extends WeaponBehavior {

    @Export
    @RegisterProperty
    public Marker2D muzzle;

    private double attackTimer = 0.0;
    private boolean isWaiting = false;

    @Override
    @RegisterFunction
    public void executeAttack(){
        weapon.isAttacking = true;
        createProjectile();

        Tween tween = createTween();
        Vector2 startPos = weapon.attackStartPosition;
        Vector2 recoilPos = new Vector2(
                startPos.getX() - weapon.data.stats.recoil,
                startPos.getY()
        );

        // Tween giật lùi và thu về
        tween.tweenProperty(weapon.getSprite(), "position", recoilPos, weapon.data.stats.recoilDuration);
        tween.tweenProperty(weapon.getSprite(), "position", startPos, weapon.data.stats.recoilDuration);

        //TỰ ĐẾM GIỜ ---
        // Vũ khí bắn xa chỉ có lùi và về (2 lần recoilDuration)
        double totalTime = weapon.data.stats.recoilDuration * 2;
        this.attackTimer = totalTime;
        this.isWaiting = true;

    }

    @Override
    @RegisterFunction
    public void _process(double delta) {
        if (isWaiting) {
            attackTimer -= delta;
            if (attackTimer <= 0) {
                isWaiting = false;
                onAttackFinished();
            }
        }
    }

    @RegisterFunction
    public void onAttackFinished() {
        weapon.isAttacking = false;
        this.critical = false;
    }

    @RegisterFunction
    public void createProjectile(){
        // Kiểm tra xem đã nạp scene đạn chưa
        if (weapon.data.stats.projectileScene == null) {
            GD.printErr("Sếp quên kéo file ProjectilePistol.tscn vào Stats rồi!");
            return;
        }

        Node instance = weapon.data.stats.projectileScene.instantiate();
        if (!(instance instanceof Projectile)){
            return;
        }

        Projectile projectile = (Projectile) instance;

        getTree().getRoot().addChild(projectile);

        // 3. Chỉnh vị trí xuất phát đúng ngay Muzzle (Nòng súng)
        projectile.setGlobalPosition(muzzle.getGlobalPosition());

        // 4. Tính toán đường bay
        double rot = weapon.getRotation(); // Lấy góc súng đang chĩa
        double speed = weapon.data.stats.projectileSpeed;

        // Bí quyết dùng Cos và Sin để tạo Vector bay mượt mà trong Java
        Vector2 vel = new Vector2(Math.cos(rot) * speed, Math.sin(rot) * speed);

        projectile.setProjectile(
                vel,
                getDamage(),        // Sát thương đã tính crit
                this.critical,      // Tỉ lệ crit
                weapon.data.stats.knockback,
                (Node2D) weapon.getParent() // Kẻ ra đòn (Player)
        );

    }
}
