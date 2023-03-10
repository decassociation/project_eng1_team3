package com.team3gdx.game.powerup;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team3gdx.game.entity.Cook;

import java.util.ArrayList;

public class PowerupController {
    SpeedBoost speedBoost;
    ArrayList<Powerup> powerups;

    /***
     * Constructor for PowerupController
     */
    public PowerupController(){
        powerups = new ArrayList<>();
        SpeedBoost speedBoost = new SpeedBoost(0, 0);
        powerups.add(speedBoost);
    }

    /***
     * Draw powerups, check collision, manage activation
     *
     * @param batch the SpriteBatch to draw the powerup to
     * @param cook the cook to check collision with
     */
    public void updatePowerups(SpriteBatch batch, Cook cook){
        for(Powerup powerup: powerups){
            powerup.draw(batch);
            powerup.checkCollision(cook);
            if(!powerup.active){
                powerup.activate();
            }
        }
    }
}
