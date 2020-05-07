package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Player {

    public static final String TAG = Player.class.getName();
    Vector2 position;
    Viewport viewport;
    int deaths;
    public Player(Viewport viewport) {
        this.viewport = viewport;
        deaths = 0;
        init();
    }
    public void init(){
        position = new Vector2(viewport.getWorldWidth() / 2, Constants.PLAYER_HEAD_HEIGHT);
    }

    public void render(ShapeRenderer renderer){
        renderer.setColor(Constants.PLAYER_COLOR);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.circle(position.x, position.y, 0.5f, 9);
    }
    public void update(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            position.x -= delta * Constants.PLAYER_MOVEMENT_SPEED;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += delta * Constants.PLAYER_MOVEMENT_SPEED;
        }
        float accelerometerInput = -Gdx.input.getAccelerometerY() / (Constants.GRAVITATIONAL_ACCELERATION * Constants.ACCELEROMETER_SENSITIVITY);
        position.x += -delta * accelerometerInput * Constants.PLAYER_MOVEMENT_SPEED;

        ensureInBounds();
    }
    public void ensureInBounds(){
        if (position.x - Constants.PLAYER_HEAD_RADIUS < 0) {
            position.x = Constants.PLAYER_HEAD_RADIUS;
        }
        if (position.x + Constants.PLAYER_HEAD_RADIUS > viewport.getWorldWidth()) {
            position.x = viewport.getWorldWidth() - Constants.PLAYER_HEAD_RADIUS;
        }
    }

    public boolean hitByIcicle(Triangles triangles){
        boolean isHit = false;
        for (Triangle triangle : triangles.trianglesList) {
            if (triangle.position.dst(position) < Constants.PLAYER_HEAD_RADIUS) {
                isHit = true;
            }
        }
        if(isHit){
            deaths +=1;
        }
        return isHit;
    }
}
