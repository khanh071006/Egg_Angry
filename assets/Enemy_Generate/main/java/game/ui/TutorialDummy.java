package game.ui;

import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.Export;
import godot.annotation.RegisterProperty;
import godot.api.Node2D;
import godot.api.Sprite2D;
import godot.api.AnimationPlayer;
import godot.api.Line2D;
import godot.core.Vector2;
import godot.core.Color;
import godot.core.PackedVector2Array;
import java.util.LinkedList;

@RegisterClass
public class TutorialDummy extends Node2D {

    @Export
    @RegisterProperty
    public int actionType = 0; // 0=W, 1=A, 2=S, 3=D, 4=Space

    @Export
    @RegisterProperty
    public float speed = 150.0f;

    @Export
    @RegisterProperty
    public float dummyScale = 0.6f;

    private Sprite2D sprite;
    private Node2D visuals;
    private AnimationPlayer animPlayer;
    private Line2D trail;
    
    private float timer = 0.0f;
    private Vector2 startPos;
    private final LinkedList<Vector2> trailPoints = new LinkedList<>();

    @RegisterFunction
    @Override
    public void _ready() {
        visuals = (Node2D) getNode("Visuals");
        if (visuals != null) {
            sprite = (Sprite2D) visuals.getNode("Sprite");
        }
        animPlayer = (AnimationPlayer) getNode("AnimationPlayer");
        trail = (Line2D) getNode("Trail");
        if (trail != null) {
            trail.setAsTopLevel(true);
            trail.setWidth(25.0f * dummyScale);
        }
        
        startPos = getPosition();

        if (animPlayer != null) {
            animPlayer.play("move");
        }

        // Set initial scale/flip based on action type
        if (visuals != null) {
            float scaleX = dummyScale;
            float scaleY = dummyScale;
            if (actionType == 1) { // Left (A)
                visuals.setScale(new Vector2(scaleX, scaleY));
            } else if (actionType == 3 || actionType == 4) { // Right (D or Space)
                visuals.setScale(new Vector2(-scaleX, scaleY));
            } else {
                visuals.setScale(new Vector2(scaleX, scaleY));
            }
        }
    }

    @RegisterFunction
    @Override
    public void _process(double delta) {
        float fDelta = (float) delta;
        timer += fDelta;

        switch (actionType) {
            case 0: // W - Move Up
                moveUp(fDelta);
                break;
            case 1: // A - Move Left
                moveLeft(fDelta);
                break;
            case 2: // S - Move Down
                moveDown(fDelta);
                break;
            case 3: // D - Move Right
                moveRight(fDelta);
                break;
            case 4: // Space - Dash
                moveDash(fDelta);
                break;
        }
        
        // Update trail if active
        updateTrail();
    }

    private void moveUp(float delta) {
        Vector2 pos = getPosition();
        pos = pos.plus(new Vector2(0.0f, -speed * delta));
        if (pos.getY() < startPos.getY() - 80.0f) {
            pos = new Vector2(pos.getX(), startPos.getY() + 80.0f);
        }
        setPosition(pos);
    }

    private void moveLeft(float delta) {
        Vector2 pos = getPosition();
        pos = pos.plus(new Vector2(-speed * delta, 0.0f));
        if (pos.getX() < startPos.getX() - 80.0f) {
            pos = new Vector2(startPos.getX() + 80.0f, pos.getY());
        }
        setPosition(pos);
    }

    private void moveDown(float delta) {
        Vector2 pos = getPosition();
        pos = pos.plus(new Vector2(0.0f, speed * delta));
        if (pos.getY() > startPos.getY() + 80.0f) {
            pos = new Vector2(pos.getX(), startPos.getY() - 80.0f);
        }
        setPosition(pos);
    }

    private void moveRight(float delta) {
        Vector2 pos = getPosition();
        pos = pos.plus(new Vector2(speed * delta, 0.0f));
        if (pos.getX() > startPos.getX() + 80.0f) {
            pos = new Vector2(startPos.getX() - 80.0f, pos.getY());
        }
        setPosition(pos);
    }

    private void moveDash(float delta) {
        // A full dash cycle takes 1.5 seconds
        float cycle = timer % 1.5f;
        if (cycle < 0.4f) {
            // Dashing phase: move from X = -100 to X = 100 very quickly
            float t = cycle / 0.4f;
            float newX = -100.0f + t * 200.0f;
            setPosition(new Vector2(startPos.getX() + newX, startPos.getY()));
            
            // Add points to trail
            if (trail != null) {
                trailPoints.add(getGlobalPosition());
                if (trailPoints.size() > 15) {
                    trailPoints.removeFirst();
                }
            }
            
            // Fade visual a bit to represent dash
            if (visuals != null) {
                visuals.setModulate(new Color(1.0f, 1.0f, 1.0f, 0.5f));
            }
        } else if (cycle < 1.3f) {
            // Rest phase: stay at target X
            setPosition(new Vector2(startPos.getX() + 100.0f, startPos.getY()));
            
            // Fade out trail
            if (trailPoints.size() > 0) {
                trailPoints.removeFirst();
            }
            
            // Normal opacity
            if (visuals != null) {
                visuals.setModulate(new Color(1.0f, 1.0f, 1.0f, 1.0f));
            }
        } else {
            // Reset phase: quickly fade out and reset position
            setPosition(new Vector2(startPos.getX() - 100.0f, startPos.getY()));
            trailPoints.clear();
            if (trail != null) {
                trail.clearPoints();
            }
        }
    }
    
    private void updateTrail() {
        if (trail == null) return;
        
        if (trailPoints.isEmpty()) {
            trail.clearPoints();
            return;
        }
        
        PackedVector2Array godotArray = new PackedVector2Array();
        for (Vector2 point : trailPoints) {
            godotArray.append(point);
        }
        trail.setPoints(godotArray);
    }
}
