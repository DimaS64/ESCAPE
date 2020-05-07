package com.mygdx.game;

import com.badlogic.gdx.Game;


public class EscapeGame extends Game {
    @Override
    public void create() {
        showDifficultyScreen();
    }


    public void showDifficultyScreen() {
        setScreen(new DifficultyScreen(this));
    }

    public void showEscapeScreen(Constants.Difficulty difficulty) {
        setScreen(new EscapeScreen(this, difficulty));
    }
}
