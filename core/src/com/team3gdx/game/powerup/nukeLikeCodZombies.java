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

    @Override
    public void applyPowerup(Cook cook){
        System.out.println("delling people");
        int dels = GameScreen.cc.amountActiveCustomers;
        for(int x = 0; x < 5; x++){
            try {
                GameScreen.cc.nukeCustomer(x);
                boom.play();
            } catch (Exception e) {
                System.out.println("error");
            }
        }
    }
    public void nukeEm(){
        System.out.println("delling people");
        stage = 0;
//        int dels = GameScreen.cc.amountActiveCustomers;
//       // try {
//            System.out.println(GameScreen.cc.amountActiveCustomers);
//            for(int x = 0; x < dels; x++){
//                String zones = (GameScreen.cc.customers[x].posx + "," + GameScreen.cc.customers[x].posy);
//
//                System.out.println(x);
//                GameScreen.cc.nukeCustomer(x);
//                System.out.println("delling customer " + x);
//            }
////        } catch (Exception e){
////            System.out.println("error");
////        }
        for(int x = 0; x < 5; x++){
            try {
                GameScreen.cc.nukeCustomer(x);
                boom.play();
            } catch (Exception e) {
                System.out.println("error");
            }
        }




    }
    public int nukeStage(){

        return stage;
    }

    public void nukeAnimation(SpriteBatch b){
        if(nukeStage() == 0){
            for(int a = 0; a < nukeZones.size(); a ++ ){
                String splitter = nukeZones.get(a);
                String[] split = splitter.split(",");
                int xval = Integer.parseInt(split[0]);
                int yval = Integer.parseInt(split[1]);
                b.begin();
                b.draw(explosion1, xval, yval);
                b.end();
            }

        }


    }






}















