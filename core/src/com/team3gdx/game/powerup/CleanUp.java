package com.team3gdx.game.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.team3gdx.game.entity.Cook;
import com.team3gdx.game.food.Ingredients;
import com.team3gdx.game.screen.GameScreen;
import com.team3gdx.game.station.IngredientStation;
import com.team3gdx.game.station.Station;

/**
 * This powerup cleans up all stations, removing all ingredients from them.
 * New
 */
public class CleanUp extends Powerup{
    private GameScreen gameScreen;

    public CleanUp(int x, int y, GameScreen gameScreen){
        super(x, y);
        this.texture = new Texture(Gdx.files.internal("powerups/cleanup.png"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void applyPowerup(Cook cook){
        for(Station station: gameScreen.getStationManager().stations.values()){
            for(int i = 0; i < 4; i++) {
                if(!station.getClass().equals(IngredientStation.class)) {
                    station.take();
                }
            }
        }
    }
}
