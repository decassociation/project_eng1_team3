package com.team3gdx.game.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.team3gdx.game.entity.Cook;
import com.team3gdx.game.food.Ingredients;
import com.team3gdx.game.screen.GameScreen;
import com.team3gdx.game.station.IngredientStation;
import com.team3gdx.game.station.Station;
import com.team3gdx.game.station.StationManager;

/**
 * This powerup adds spilled beans to all stations.
 * New
 */
public class GetBeaned extends Powerup{
    private GameScreen gameScreen;

    public GetBeaned(int x, int y, GameScreen gameScreen){
        super(x, y);
        this.texture = new Texture(Gdx.files.internal("powerups/getbeaned.png"));
        this.gameScreen = gameScreen;
    }

    // This method is called when the powerup is used, and it adds spilled beans to all stations.
    @Override
    public void applyPowerup(Cook cook){
        for(Station station: StationManager.stations.values()){
            for(int i = 0; i < 4; i++) {
                if(!station.getClass().equals(IngredientStation.class)) {
                    station.place(Ingredients.cooked_beans_with_spill);
                }
            }
        }
    }
}
