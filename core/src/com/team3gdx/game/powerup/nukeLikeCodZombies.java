package com.team3gdx.game.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team3gdx.game.entity.Cook;
import com.team3gdx.game.entity.CustomerController;
import com.team3gdx.game.screen.GameScreen;
import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;
import java.util.List;

public class nukeLikeCodZombies extends Powerup{
    public static CustomerController cc;
    public int stage;
    public ArrayList<String> nukeZones;
    private Sound boom;
    Texture explosion1;
    Texture explosion2;
    Texture explosion3;

    /***
     * Powerup constructor
     *  @param x the x coordinate
     * @param y the y coordinate
     */
    public nukeLikeCodZombies(int x, int y) {
        super(x, y);
        nukeZones = new ArrayList<String>();
        boom = Gdx.audio.newSound(Gdx.files.internal("powerups/boom.wav"));
        this.texture = new Texture(Gdx.files.internal("powerups/nuke.png"));
        explosion1 = new Texture(Gdx.files.internal("powerups/explosion1.png"));
        explosion2 = new Texture(Gdx.files.internal("powerups/explosion2.png"));
        explosion3 = new Texture(Gdx.files.internal("powerups/explosion3.png"));
    }


    /**
     * Applies the powerup, not so much to the cook but to the game, but the powerup class requires
     * a cook variable to be passed in.
     * @param cook
     */
    @Override
    public void applyPowerup(Cook cook){
        System.out.println("delling people");
        for(int x = 0; x < 5; x++){
            try {
                GameScreen.cc.nukeCustomer(x);
                boom.play();
            } catch (Exception e) {
                System.out.println("error"); //This shouldn't ever actually be triggered, but it's there incase and will prevent the game from crashing.
            }
        }
        GameScreen.currentWaitingCustomer = null;
    }

    /**
     * Essentially the same as the powerup, but was used for testing.
     */

    public void nukeEm(){
        System.out.println("delling people");
        for(int x = 0; x < 5; x++){
            try {
                GameScreen.cc.nukeCustomer(x);
                boom.play();
            } catch (Exception e) {
                System.out.println("error");
            }
        }
    }




}















