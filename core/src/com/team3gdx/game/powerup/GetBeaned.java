package com.team3gdx.game.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.entity.Cook;
import com.team3gdx.game.food.Ingredients;
import com.team3gdx.game.screen.GameScreen;
import com.team3gdx.game.station.Station;

public class GetBeaned extends Powerup{
    private GameScreen gameScreen;

    public GetBeaned(int x, int y, GameScreen gameScreen){
        super(x, y);
        this.texture = new Texture(Gdx.files.internal("powerups/getbeaned.png"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void applyPowerup(Cook cook){
        TiledMapTileLayer layer = (TiledMapTileLayer) gameScreen.map1.getLayers().get(0);
        for(int x = 0; x < layer.getWidth(); x++) {
            for(int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell viewedTile = ((TiledMapTileLayer) gameScreen.map1.getLayers().get(1)).getCell(x, y);

                if (viewedTile != null) {
                    Object stationType = viewedTile.getTile().getProperties().get("Station");
                    if (stationType == null) {
                        try {
                            gameScreen.getStationManager().checkInteractedTile("", new Vector2(x, y));
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        for(Station station: gameScreen.getStationManager().stations.values()){
            for(int i = 0; i < 4; i++) {
                station.place(Ingredients.cooked_beans);
            }
        }
    }
}
