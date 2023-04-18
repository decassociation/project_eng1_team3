package com.team3gdx.game.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.team3gdx.game.entity.Cook;
import com.team3gdx.game.screen.GameScreen;

public class Reputation extends Powerup {

    private GameScreen gameScreen;

    public Reputation(int x, int y, GameScreen gameScreen) {
        super(x, y);
        this.texture = new Texture(Gdx.files.internal("powerups/orb.png"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void applyPowerup(Cook cook) {
        gameScreen.addReputationPoints(1);
    }
}