package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Triangle {
    public static final String TAG = Triangle.class.getName();
    Vector2 position;
    Vector2 velocity;
    public Triangle(Vector2 position){
        this.position = position;
        this.velocity = new Vector2();
    }
    public void update(float delta){
        velocity.mulAdd(Constants.TRIANGLE_ACCELERATION, delta);
        position.mulAdd(velocity, delta);
    }
    public void render(ShapeRenderer renderer){
        renderer.setColor(Constants.TRIANGLE_COLOR);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.triangle(
                position.x, position.y,
                position.x - Constants.TRIANGLE_WIDTH / 2, position.y + Constants.TRIANGLE_HEIGHT,
                position.x + Constants.TRIANGLE_WIDTH / 2, position.y + Constants.TRIANGLE_HEIGHT
        );
    }
}
