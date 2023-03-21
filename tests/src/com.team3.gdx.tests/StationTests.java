package com.team3.gdx.tests;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.entity.Customer;
import com.team3gdx.game.entity.CustomerController;
import com.team3gdx.game.food.Ingredient;
import com.team3gdx.game.food.Ingredients;
import com.team3gdx.game.food.Menu;
import com.team3gdx.game.food.Recipe;
import com.team3gdx.game.screen.GameScreen;
import com.team3gdx.game.station.BakingStation;
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


    /**
     * This tests the creation of a serving station *AND* the serving of a customer because it's the same block of code
     * to test both; a few extra lines are needed to test the serving functionality.
     */
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
        GameScreen.currentWaitingCustomer = GameScreen.cc.customers[0];
        GameScreen.cc.customers[0].locked = true;
        GameScreen.control = new Control();
        Vector2 testPosB = new Vector2(20,20);
        StationManager testSM = new StationManager();
        testSM.checkInteractedTile("Service", testPosB);
        assertTrue("Serving station should have been instantiated at (20, 20)",
                testSM.stations.get(testPosB) instanceof ServingStation);

        //----------------------------- TEST LINES 40 & 41 IN ServingStation.serveCustomer() ---------------------------
        GameScreen.cc.customers[0].order = null;
        GameScreen.currentWaitingCustomer = null;

        ((ServingStation) testSM.stations.get(testPosB)).serveCustomer();
        assertTrue("GameScreen.currentWaitingCustomer (i.e. the test customer should have had their order " +
                        "changed from null to a random order but didn't)",
                GameScreen.cc.customers[0].order != null);

        //---------------------------------------- TEST CUSTOMER SERVING -----------------------------------------------
        // To test customer serving, the code from the above section is built upon so no duplicated code
        GameScreen.cc.customers[0].order = "Burger";
        testSM.stations.get(testPosB).slots.push(Menu.RECIPES.get(GameScreen.cc.customers[0].order));
        // (Above line) pushes same order as the customer used to test this, to the serving stations stack
        ((ServingStation) testSM.stations.get(testPosB)).serveCustomer();
        assertNull("There should be no existing customers but there is one at GameScreen.cc.customers[0]",
                GameScreen.cc.customers[0]);
    }

    @Test
    public void testCreateBakingStation(){
        Vector2 testPos = new Vector2(15,16);
        BakingStation testBS = new BakingStation(testPos);
        StationManager.stations.put(testPos, testBS);
        assertEquals("A baking station should have been created at (15,16) but hasn't",
                StationManager.stations.get(testPos), testBS);
    }

    @Test
    public void testBakingStationPlace(){
        Vector2 testPos = new Vector2(15,16);
        BakingStation testBS = new BakingStation(testPos);
        StationManager.stations.put(testPos, testBS);
        Ingredient testPotato = Ingredients.potato;
        assertTrue("A potato should have been placed on the baking station but wasn't",
                testBS.place(testPotato));
        assertTrue("The potato placed onto the baking station stack should be flipped but isn't",
                testPotato.flipped);


        Ingredient testBeans = Ingredients.beans;
        assertFalse("Beans should have been stopped from being placed on the baking station but weren't",
                testBS.place(testBeans));
    }
}
