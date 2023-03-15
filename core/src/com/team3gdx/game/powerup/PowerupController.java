package com.team3gdx.game.powerup;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team3gdx.game.entity.Cook;

import java.util.ArrayList;

public class PowerupController {
    SpeedBoost speedBoost;
    ArrayList<Powerup> powerups;
    private long timeOfLast;
    private int selectIndex;

    /***
     * Constructor for PowerupController
     */
    public PowerupController(){
        powerups = new ArrayList<>();

        // Speed boost
        SpeedBoost speedBoost = new SpeedBoost(0, 0);
        powerups.add(speedBoost);

        timeOfLast = System.currentTimeMillis();
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
        }

        // activate a random powerup at regular intervals, or deactivate if already active
        if(System.currentTimeMillis() - timeOfLast >= 30000){
            selectIndex = (int)(Math.random() * powerups.size());
            if(!powerups.get(selectIndex).active) powerups.get(selectIndex).activate();
            else powerups.get(selectIndex).active = false;
            timeOfLast = System.currentTimeMillis();
        }

    }
}
