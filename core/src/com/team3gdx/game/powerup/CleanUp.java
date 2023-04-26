package com.team3gdx.game.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.entity.Cook;
import com.team3gdx.game.food.Ingredients;
import com.team3gdx.game.screen.GameScreen;
import com.team3gdx.game.station.*;

public class CleanUp extends Powerup{
    private GameScreen gameScreen;

    public CleanUp(int x, int y, GameScreen gameScreen){
        super(x, y);
        this.texture = new Texture(Gdx.files.internal("powerups/cleanup.png"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void applyPowerup(Cook cook){
        for(Station station: StationManager.stations.values()){
            //if(station.getClass().equals(IngredientStation.class)) {
                station.clearStation();
                //if(ingredientStation.getIngredient() == Ingredients.cooked_beans_with_spill) {
                //    ingredientStation.setIngredient(null);
                //}
            //}
        }
    }
}
