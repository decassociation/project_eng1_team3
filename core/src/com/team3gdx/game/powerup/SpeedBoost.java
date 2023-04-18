package com.team3gdx.game.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.team3gdx.game.entity.Cook;

public class SpeedBoost extends Powerup{

    public SpeedBoost(int x, int y){
        super(x, y);
        this.texture = new Texture(Gdx.files.internal("powerups/orb.png"));
    }

    @Override
    public void applyPowerup(Cook cook){
        cook.speed = 0.5f;
    }
}
