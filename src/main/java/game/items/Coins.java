package game.items;

import game.autoloads.Global;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Area2D;
import godot.api.Node2D;
import godot.core.Transform2D;
import godot.core.Vector2;
import godot.global.GD;

@RegisterClass
public class Coins extends Area2D {

    @Export
    @RegisterProperty
    public float moveSpeed = 1000.0f;

    @Export
    @RegisterProperty
    public float collectDistance = 15.0f;

    @Export
    @RegisterProperty
    public int value = 1;

    // Vector2.INF tương đương với Float.POSITIVE_INFINITY trong Java
    private Vector2 targetScreenPosition = new Vector2(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
    private Vector2 targetPosition = new Vector2(0, 0);
    private boolean collected = false;

    @RegisterFunction
    public void setCollectionTarget(Vector2 screenPosition) {
        this.targetScreenPosition = screenPosition;
    }

    @RegisterFunction
    @Override
    public void _ready() {
        // Cưỡng chế phát Animation ngay khi xuất hiện bằng code
        godot.api.AnimatedSprite2D sprite = (godot.api.AnimatedSprite2D) getNodeOrNull("AnimatedSprite2D");
        if (sprite != null) {
            sprite.play("default", 1.0f, false);
        }
    }

    @RegisterFunction
    public void playSpawnAnimation() {
        godot.api.Tween tween = createTween();
        if (tween == null) return;
        
        Vector2 startPos = getGlobalPosition();
        Vector2 peakPos = startPos.plus(new Vector2(0, -25)); // Nhảy lên 25 pixel
        
        // Nhảy lên
        tween.tweenProperty(this, "global_position", peakPos, 0.15);
             
        // Rớt xuống nảy nảy
        tween.tweenProperty(this, "global_position", startPos, 0.25);
    }

    @RegisterFunction
    @Override
    public void _process(double delta) {
        // Nếu đã được nhặt và KHÔNG CÓ targetScreenPosition cụ thể (vẫn là vô cực)
        // -> Bay vào người chơi
        if (collected && targetScreenPosition.getX() == Float.POSITIVE_INFINITY) {
            if (godot.global.GD.isInstanceValid(Global.player)) {
                targetPosition = Global.player.getGlobalPosition();
            }
        }

        // Nếu có targetScreenPosition cụ thể -> Bay vào góc màn hình (CoinsBag)
        if (targetScreenPosition.getX() != Float.POSITIVE_INFINITY) {
            Transform2D canvasTransform = getCanvasTransform();
            Transform2D inverse = canvasTransform.affineInverse();
            // Nhân ma trận với vector để chuyển tọa độ màn hình sang tọa độ thế giới (world)
            targetPosition = inverse.times(targetScreenPosition);
        }

        // Nếu targetPosition hợp lệ (khác mặc định) thì bắt đầu di chuyển
        if (targetPosition.getX() != 0 || targetPosition.getY() != 0) {
            Vector2 currentPos = getGlobalPosition();
            setGlobalPosition(currentPos.moveToward(targetPosition, moveSpeed * (float) delta));

            // Kiểm tra xem đã tới nơi chưa
            if (getGlobalPosition().distanceTo(targetPosition) < collectDistance) {
                addCoins();
            }
        }
    }

    // Kết nối hàm này với signal body_entered hoặc area_entered của đồng xu
    @RegisterFunction
    public void _on_area_entered(godot.api.Area2D area) {
        // Kiểm tra xem người chạm vào có phải là Player không
        if (area == Global.player) {
            collected = true;
        }
    }

    private void addCoins() {
        Global.coins += value;
        Global.instance.playSfx("res://assets/audio/coin_sound.mp3");
        queueFree(); // Xóa đồng xu khỏi bản đồ
    }
}
