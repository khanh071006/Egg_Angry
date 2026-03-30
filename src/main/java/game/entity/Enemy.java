package game.entity;

import game.autoloads.Global;
import game.resources.EnemyStats;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.AnimationPlayer;
import godot.api.Area2D;
import godot.api.Node2D;
import godot.api.Sprite2D;
import godot.core.VariantArray;
import godot.core.Vector2;

@RegisterClass
public class Enemy extends BaseUnit {

//    @Export
//    @RegisterProperty
//    public EnemyStats stats;

    @Export
    @RegisterProperty
    public float flockPush = 20.0f;

    private Area2D visionArea;
    private boolean canMove = true;

    @RegisterFunction
    @Override
    public void _ready() {
        super._ready();

        visuals = (Node2D) getNode("%Visuals");
        sprite = (Sprite2D) getNode("%Sprite");
        animPlayer = (AnimationPlayer) getNode("AnimationPlayer");
        visionArea = (Area2D) getNode("VisionArea");
    }

    // Dùng _physics_process để đồng bộ chuẩn với hệ thống quét Radar của Godot
    @RegisterFunction
        @Override
    public void _physicsProcess(double delta) {
        float fDelta = (float) delta;

        if (!canMove) return;

        // Tương đương hàm can_move_towards_player() trong video
        if (!canMoveTowardsPlayer()) {
            return;
        }

        Vector2 moveDir = getMoveDirection();

        float speed = (stats != null) ? stats.speed : 250.0f;
        Vector2 velocity = moveDir.times(speed);

        // Tương đương: position += move_direction * speed * delta
        Vector2 currentPos = getGlobalPosition();
        setGlobalPosition(currentPos.plus(velocity.times(fDelta)));

        updateRotation();
    }

    private boolean canMoveTowardsPlayer() {
        if (Global.player == null || !Global.player.isInsideTree()) {
            return false;
        }
        float dist = (float) getGlobalPosition().distanceTo(Global.player.getGlobalPosition());
        return dist > 60.0f;
    }

    private Vector2 getMoveDirection() {
        if (Global.player == null || !Global.player.isInsideTree()) {
            return new Vector2(0, 0);
        }

        Vector2 myPos = getGlobalPosition();
        Vector2 playerPos = Global.player.getGlobalPosition();

        // 1. Lấy hướng thẳng tới Player
        Vector2 direction = playerPos.minus(myPos).normalized();

        if (visionArea != null) {
            // SỬA LỖI CỦA YOUTUBER: Phải quét Bodies, KHÔNG PHẢI Areas!
            VariantArray<Area2D> overlapping = visionArea.getOverlappingAreas();

            for (int i = 0; i < overlapping.size(); i++) {
                Node2D other = overlapping.get(i);

                // Nếu không phải chính mình và kẻ đó còn sống
                if (other.getInstanceId() != this.getInstanceId() && other.isInsideTree()) {

                    // Tạo vector từ KẺ ĐÓ chĩa về MÌNH (Toán học giống hệt video)
                    Vector2 vector = myPos.minus(other.getGlobalPosition());
                    float length = (float) vector.length();

                    if (length > 0.0f) {
                        // direction += vector.normalized() / vector.length() * flock_push
                        Vector2 pushForce = vector.normalized().div(length).times(flockPush);
                        direction = direction.plus(pushForce);
                    }
                }
            }
        }

        // Trả về vector đã chuẩn hóa cuối cùng
        return direction.normalized();
    }

    private void updateRotation() {
        if (Global.player == null || visuals == null) return;

        Vector2 myPos = getGlobalPosition();
        Vector2 playerPos = Global.player.getGlobalPosition();

        // Xoay nhân vật dựa trên vị trí X của Player
        if (myPos.getX() < playerPos.getX()) {
            visuals.setScale(new Vector2(-1f, 1f));
        } else {
            visuals.setScale(new Vector2(1f, 1f));
        }
    }
}