package com.team3.gdx.tests;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.entity.Customer;
import com.team3gdx.game.entity.CustomerController;
import com.team3gdx.game.screen.GameScreen;
import com.team3gdx.game.station.ServingStation;
import com.team3gdx.game.station.Station;
import com.team3gdx.game.station.StationManager;
import com.team3gdx.game.util.Control;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class StationTests {

    @Test
    public void testCreateStation() {
        //All stations are child classes of Station
        Vector2 pos = new Vector2(0,0);
        Station station = new Station(pos, 0, true, null, null);
        StationManager.stations.put(pos, station);
        assertTrue("Station should be instantiated at position 0,0",
                StationManager.stations.get(pos) == station);
    }

    @Test
    public void testCreateServingStation(){
        //------------------------------------------ ISOLATED CREATION -------------------------------------------------
        Vector2 testPosA = new Vector2(12,13);
        ServingStation testSS = new ServingStation(testPosA);
        StationManager.stations.put(testPosA, testSS);
        assertEquals("Serving station should have been instantiated at (12, 13)",
                StationManager.stations.get(testPosA), testSS);

        //----------------------------- CREATION THROUGH StationManager's checkInteractedTile --------------------------

        TiledMap testMap = new TmxMapLoader().load("map/art_map/customertest.tmx");
        GameScreen.cc = new CustomerController(testMap, "easy");
        GameScreen.cc.customers[0] = new Customer(19, 20, 20, 0);
        GameScreen.cc.customers[0].locked = true;
        GameScreen.control = new Control();
        Vector2 testPosB = new Vector2(20,20);
        StationManager testSM = new StationManager();
        testSM.checkInteractedTile("Service", testPosB);
        assertTrue("Serving station should have been instantiated at (20, 20)",
                testSM.stations.get(testPosB) instanceof ServingStation);
    }
}
