package com.team3gdx.game.powerup;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team3gdx.game.entity.Cook;
import com.team3gdx.game.screen.GameScreen;

import java.util.ArrayList;

public class PowerupController {
    ArrayList<Powerup> powerups;
    private long timeOfLast;
    private int selectIndex;

    /***
     * Constructor for PowerupController
     */
    public PowerupController(GameScreen gameScreen){
        powerups = new ArrayList<>();

        // Speed boost
        SpeedBoost speedBoost = new SpeedBoost(0, 0);
        powerups.add(speedBoost);

        // Get beaned
        GetBeaned getBeaned = new GetBeaned(0, 0, gameScreen);
        powerups.add(getBeaned);

        // Reputation
        Reputation reputation = new Reputation(0, 0, gameScreen);
        powerups.add(reputation);

        // Nuke Like Cod Zombies
        nukeLikeCodZombies nuke = new nukeLikeCodZombies(0,0);

        //CleanUp Crew
        CleanUp cleanUp = new CleanUp(0,0);
        powerups.add(cleanUp);


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
        if(System.currentTimeMillis() - timeOfLast >= 10000){
            selectIndex = (int)(Math.random() * powerups.size());
            if(!powerups.get(selectIndex).active) powerups.get(selectIndex).activate();
            else powerups.get(selectIndex).active = false;
            timeOfLast = System.currentTimeMillis();
        }

    }
}
