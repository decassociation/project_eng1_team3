package com.team3gdx.game.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.team3gdx.game.entity.Cook;
import com.team3gdx.game.screen.GameScreen;
import com.team3gdx.game.station.IngredientStation;
import com.team3gdx.game.station.Station;

public class CleanUp extends Powerup{
    private GameScreen gameScreen;

    public CleanUp(int x, int y, GameScreen gameScreen){
        super(x, y);
        this.texture = new Texture(Gdx.files.internal("powerups/reputation.png"));
        this.gameScreen = gameScreen;
    }

    public CleanUp(int x, int y) {
        super(x, y);
    }

    @Override
    public void applyPowerup(Cook cook){
        for(Station station: gameScreen.getStationManager().stations.values()){
            for(int i = 0; i < 4; i++) {
                if(!station.getClass().equals(IngredientStation.class)) {
                    station.place(null);
                }
            }
        }
    }
}
