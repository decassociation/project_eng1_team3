package com.team3gdx.game.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.team3gdx.game.entity.Cook;
import com.team3gdx.game.entity.CustomerController;
import com.team3gdx.game.screen.GameScreen;

public class nukeLikeCodZombies extends Powerup{
    public static CustomerController cc;


    /***
     * Powerup constructor
     *  @param x the x coordinate
     * @param y the y coordinate
     */
    public nukeLikeCodZombies(int x, int y) {
        super(x, y);
        this.texture = new Texture(Gdx.files.internal("powerups/nuke.png"));
    }

    @Override
    public void applyPowerup(Cook cook){
        System.out.println("delling people");
        int dels = GameScreen.cc.amountActiveCustomers;
        try {
            System.out.println(GameScreen.cc.amountActiveCustomers);
            for(int x = 0; x < dels; x++){
                GameScreen.cc.delCustomer(x);
                System.out.println("delling customer " + x);
            }
        } catch (Exception e){
            System.out.println("error");
        }
    }
    public void nukeEm(){
        System.out.println("delling people");
        int dels = GameScreen.cc.amountActiveCustomers;
        try {
            System.out.println(GameScreen.cc.amountActiveCustomers);
            for(int x = 0; x < dels; x++){
                GameScreen.cc.delCustomer(x);
                System.out.println("delling customer " + x);
            }
        } catch (Exception e){
            System.out.println("error");
        }
    }


}















