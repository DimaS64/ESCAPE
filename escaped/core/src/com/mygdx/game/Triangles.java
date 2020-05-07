package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Triangles {

    public static final String TAG = Triangles.class.getName();

    Constants.Difficulty difficulty;

    int trDodged;
    DelayedRemovalArray<Triangle> trianglesList;
    Viewport viewport;
    public Triangles(Viewport viewport, Constants.Difficulty difficulty){
        this.viewport = viewport;
        this.difficulty = difficulty;
        init();
    }
    public void init(){
        trianglesList = new DelayedRemovalArray<Triangle>(false, 100);
        trDodged = 0;
    }
    public void update(float delta){
        if (MathUtils.random() < delta * difficulty.spawnRate) {
            Vector2 newIciclePosition = new Vector2(
                    MathUtils.random() * viewport.getWorldWidth(),
                    viewport.getWorldHeight()
            );
            Triangle newIcicle = new Triangle(newIciclePosition);
            trianglesList.add(newIcicle);
        }
        for (Triangle icicle : trianglesList) {
            icicle.update(delta);
        }

        trianglesList.begin();

        for (int i = 0; i < trianglesList.size; i++) {
            if (trianglesList.get(i).position.y < -Constants.TRIANGLE_HEIGHT) {
                trDodged += 1;
                trianglesList.removeIndex(i);
            }
        }

        trianglesList.end();
    }

    public void render(ShapeRenderer renderer){
        renderer.setColor(Constants.TRIANGLE_COLOR);
        for (Triangle triangle : trianglesList) {
            triangle.render(renderer);
        }
    }
}
