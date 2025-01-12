package com.team3.gdx.tests;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.entity.Cook;
import com.team3gdx.game.entity.Customer;
import com.team3gdx.game.entity.CustomerController;
import com.team3gdx.game.food.Ingredient;
import com.team3gdx.game.food.Ingredients;
import com.team3gdx.game.food.Menu;
import com.team3gdx.game.food.Recipe;
import com.team3gdx.game.screen.GameScreen;
import com.team3gdx.game.station.*;
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
        GameScreen.cook = new Cook(new Vector2(15, 15), 2);
        // Have to create a cook above otherwise there will be a null pointer exception in Station.drawDropText()
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
        //------------------------------------------ ISOLATED CREATION -------------------------------------------------
        Vector2 testPos = new Vector2(15,16);
        BakingStation testBS = new BakingStation(testPos);
        StationManager.stations.put(testPos, testBS);
        assertEquals("A baking station should have been created at (15,16) but hasn't",
                StationManager.stations.get(testPos), testBS);


        //----------------------------- CREATION THROUGH StationManager's checkInteractedTile --------------------------

        TiledMap testMap = new TmxMapLoader().load("map/art_map/customertest.tmx");
        GameScreen.cc = new CustomerController(testMap, "easy");
        GameScreen.cc.customers[0] = new Customer(19, 20, 20, 0);
        GameScreen.currentWaitingCustomer = GameScreen.cc.customers[0];
        GameScreen.cc.customers[0].locked = true;
        GameScreen.control = new Control();

        Vector2 testPosB = new Vector2(20,20);
        StationManager testSM = new StationManager();
        GameScreen.cook = new Cook(new Vector2(15, 15), 2);
        // Have to create a cook above otherwise there will be a null pointer exception in Station.drawDropText()
        testSM.checkInteractedTile("Baking", testPosB);
        assertTrue("Baking station should have been instantiated at (20, 20)",
                testSM.stations.get(testPosB) instanceof BakingStation);
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

    @Test
    public void testCreateFryingStation(){
        Vector2 testPos = new Vector2(15,16);
        FryingStation testFS = new FryingStation(testPos);
        StationManager.stations.put(testPos, testFS);
        assertEquals("A frying station should have been created at (15,16) but hasn't",
                StationManager.stations.get(testPos), testFS);
        //----------------------------- CREATION THROUGH StationManager's checkInteractedTile --------------------------

        TiledMap testMap = new TmxMapLoader().load("map/art_map/customertest.tmx");
        GameScreen.cc = new CustomerController(testMap, "easy");
        GameScreen.cc.customers[0] = new Customer(19, 20, 20, 0);
        GameScreen.currentWaitingCustomer = GameScreen.cc.customers[0];
        GameScreen.cc.customers[0].locked = true;
        GameScreen.control = new Control();

        Vector2 testPosB = new Vector2(20,20);
        StationManager testSM = new StationManager();
        GameScreen.cook = new Cook(new Vector2(15, 15), 2);
        // Have to create a cook above otherwise there will be a null pointer exception in Station.drawDropText()
        testSM.checkInteractedTile("Frying", testPosB);
        assertTrue("Frying station should have been instantiated at (20, 20)",
                testSM.stations.get(testPosB) instanceof FryingStation);
    }

    @Test
    public void testFryingStationPlace() {
        Vector2 testPos = new Vector2(15,16);
        FryingStation testFS = new FryingStation(testPos);
        StationManager.stations.put(testPos, testFS);
        Ingredient testFormedPatty = Ingredients.formedPatty;
        assertTrue("A formed patty should have been placed on the frying station but wasn't",
                testFS.place(testFormedPatty));

        Ingredient testBeans = Ingredients.beans;
        assertFalse("Beans should have been stopped from being placed on the frying station but weren't",
                testFS.place(testBeans));
    }

    @Test
    public void testCookingStationLockCook(){
        // *** FryingStation extends CookingStation so can just create a frying station ***
        Vector2 testPos = new Vector2(15,16);
        FryingStation testFS = new FryingStation(testPos);
        StationManager.stations.put(testPos, testFS);

        // Tests when all if statement conditions are False in lockCook()
        testFS.lockedCook = null;
        assertFalse("The frying station's slots stack should be empty and it's lockedCook attribute should be " +
                        "null so False should be returned by testFS.lockCook() but wasn't", testFS.lockCook());

        // Tests when (!slots.isEmpty() = False) and ((lockedCook == null) = False) so (lockedCook != null) = True
        // in lockCook()
        testFS.lockedCook = GameScreen.cooks[0];
        assertFalse("lockCook() should have returned False",
                testFS.lockCook());

        // Tests when (!slots.isEmpty() = True) and ((lockedCook == null) = True) in lockCook()
        testFS.place(Ingredients.formedPatty);
        testFS.place(Ingredients.formedPatty);
        GameScreen.cook = new Cook(new Vector2(15, 15), 2);
        // Have to create a cook above otherwise there will be a null pointer exception in Station.drawDropText()
        assertTrue("lockCook() should have returned True",
                testFS.lockCook());

        // Tests when (!slots.isEmpty() = True) and ((lockedCook == null) = False) in lockCook()
        testFS.lockedCook = GameScreen.cooks[0];
        testFS.place(Ingredients.formedPatty);
        testFS.place(Ingredients.formedPatty);
        assertTrue("lockCook() should have returned True",
                testFS.lockCook());

//        NOT ENTIRELY SURE WHAT THE BELOW CODE TESTS AND WHETHER IT'S USEFUL
//        testFS.lockedCook = GameScreen.cooks[0];
//        assertTrue("testFS.lockedCook should be set to a Cook type value but isn't (it's likely to be null" +
//                " instead)", testFS.lockedCook == GameScreen.cook);
//        assertTrue("lockCook() should have returned True for the test Frying Station now slots isn't empty " +
//                "and the station's locked cook attribute is not empty, but True was not returned", testFS.lockCook());
    }

    @Test
    public void testCreateCuttingStation(){
        Vector2 testPos = new Vector2(10,11);
        CuttingStation testCS = new CuttingStation(testPos, 2);
        StationManager.stations.put(testPos, testCS);
        assertTrue("A new cutting station should have been created but hasn't",
                StationManager.stations.get(testPos) instanceof  CuttingStation);


        //----------------------------- CREATION THROUGH StationManager's checkInteractedTile --------------------------

        TiledMap testMap = new TmxMapLoader().load("map/art_map/customertest.tmx");
        GameScreen.cc = new CustomerController(testMap, "easy");
        GameScreen.cc.customers[0] = new Customer(19, 20, 20, 0);
        GameScreen.currentWaitingCustomer = GameScreen.cc.customers[0];
        GameScreen.cc.customers[0].locked = true;
        GameScreen.control = new Control();

        Vector2 testPosB = new Vector2(20,20);
        StationManager testSM = new StationManager();
        GameScreen.cook = new Cook(new Vector2(15, 15), 2);
        // Have to create a cook above otherwise there will be a null pointer exception in Station.drawDropText()
        testSM.checkInteractedTile("Chopping", testPosB);
        assertTrue("Cutting station should have been instantiated at (20, 20)",
                testSM.stations.get(testPosB) instanceof CuttingStation);

    }

    @Test
    public void testCuttingStationLockCook(){
        Vector2 testPos = new Vector2(15,16);
        CuttingStation testCS = new CuttingStation(testPos, 2);
        StationManager.stations.put(testPos, testCS);

        // Tests when all if statement conditions are False in lockCook()
        testCS.lockedCook = null;
        assertFalse("The cutting station's slots stack should be empty and it's lockedCook attribute should be " +
                "null so False should be returned by testCS.lockCook() but wasn't", testCS.lockCook());

        // Tests when (!slots.isEmpty() = False) and ((lockedCook == null) = False) so (lockedCook != null) = True
        // in lockCook()
        testCS.lockedCook = GameScreen.cooks[0];
        assertFalse("lockCook() should have returned False",
                testCS.lockCook());

        // Tests when (!slots.isEmpty() = True) and ((lockedCook == null) = True) in lockCook()
        testCS.place(Ingredients.lettuce);
        testCS.place(Ingredients.lettuce);
        assertTrue("lockCook() should have returned True",
                testCS.lockCook());

        // Tests when (!slots.isEmpty() = True) and ((lockedCook == null) = False) in lockCook()
        testCS.lockedCook = GameScreen.cooks[0];
        testCS.place(Ingredients.lettuce);
        testCS.place(Ingredients.lettuce);
        assertTrue("lockCook() should have returned True",
                testCS.lockCook());
    }

    @Test
    public void testCreateIngredientStation(){
        //------------------------------------------ ISOLATED CREATION -------------------------------------------------
        Vector2 testPos = new Vector2(24, 25);
        IngredientStation testIS = new IngredientStation(testPos, Ingredients.beans);
        StationManager.stations.put(testPos, testIS);
        assertTrue("A new Ingredient Station should have been created and added to the stations hashmap" +
                " but wasn't", StationManager.stations.get(testPos) == testIS);

        // CANNOT TEST CREATION THROUGH StationManager's checkInteractedTile BECAUSE SpriteBatch is needed
    }

    @Test
    public void testCreatePrepStation(){
        //------------------------------------------ ISOLATED CREATION -------------------------------------------------
        Vector2 testPos = new Vector2(30, 31);
        PrepStation testPS = new PrepStation(testPos);
        StationManager.stations.put(testPos, testPS);
        assertSame("A new Prep Station should have been created and added to the stations hashmap" +
                " but wasn't", StationManager.stations.get(testPos), testPS);


        //----------------------------- CREATION THROUGH StationManager's checkInteractedTile --------------------------

        TiledMap testMap = new TmxMapLoader().load("map/art_map/customertest.tmx");
        GameScreen.cc = new CustomerController(testMap, "easy");
        GameScreen.cc.customers[0] = new Customer(19, 20, 20, 0);
        GameScreen.currentWaitingCustomer = GameScreen.cc.customers[0];
        GameScreen.cc.customers[0].locked = true;
        GameScreen.control = new Control();

        Vector2 testPosB = new Vector2(20,20);
        StationManager testSM = new StationManager();
        GameScreen.cook = new Cook(new Vector2(15, 15), 2);
        // Have to create a cook above otherwise there will be a null pointer exception in Station.drawDropText()
        testSM.checkInteractedTile("Prep", testPosB);
        assertTrue("Prep station should have been instantiated at (20, 20)",
                testSM.stations.get(testPosB) instanceof PrepStation);
    }

    @Test
    public void testPrepSlotsToRecipe(){
        Vector2 testPos = new Vector2(30, 31);
        PrepStation testPS = new PrepStation(testPos);
        StationManager.stations.put(testPos, testPS);

        // ----------------------------------- WHEN THERE IS A CORRECT RECIPE ------------------------------------------
        testPS.place(Ingredients.cookedPatty);
        testPS.place(Ingredients.cooked_bun);
        assertTrue("A burger recipe should have been placed on the Prep Station but hasn't been",
                testPS.slotsToRecipe());


        // ---------------------------------- WHEN THERE IS AN INCORRECT RECIPE ----------------------------------------
        testPS.slots.clear();
        testPS.place(Ingredients.unformedPatty);
        testPS.place(Ingredients.cooked_bun);
        GameScreen.cook = new Cook(new Vector2(15, 15), 2);
        // Have to create a cook above otherwise there will be a null pointer exception in PrepStation.slotsToRecipe()
        testPS.slotsToRecipe();
        assertFalse("An incorrect burger recipe should have been rejected but hasn't",
                testPS.slotsToRecipe());


        // ---------------------------------- COVER ALL IF STATEMENT CONDITIONS ----------------------------------------

        // Correct recipe:
        testPS.slots.clear();
        testPS.place(Ingredients.cookedPatty);
        testPS.place(Ingredients.cooked_bun);
        testPS.progress = 1;
        assertTrue("A burger recipe should have been placed on the Prep Station but hasn't been",
                testPS.slotsToRecipe());
        assertEquals("The Prep Station's slots should have been cleared and just 1 recipe should have been added" +
                " but there isn't exactly 1 item on the slots stack", 1, testPS.slots.size());

        // Unprepared ingredient:
        testPS.slots.clear();
        testPS.place(Ingredients.unformedPatty);
        testPS.progress = 1;
        assertTrue("...", testPS.slotsToRecipe());
        assertEquals("...", 1, testPS.slots.size());
    }

    @Test
    public void testPrepStationLockCook() {
        Vector2 testPos = new Vector2(15,16);
        PrepStation testPS = new PrepStation(testPos);
        StationManager.stations.put(testPos, testPS);

        // Tests when all if statement conditions are False in lockCook()
        testPS.lockedCook = null;
        assertFalse("The prep station's slots stack should be empty and it's lockedCook attribute should be " +
                "null so False should be returned by testPS.lockCook() but wasn't", testPS.lockCook());

        // Tests when (!slots.isEmpty() = False) and ((lockedCook == null) = False) so (lockedCook != null) = True
        // in lockCook()
        testPS.lockedCook = GameScreen.cooks[0];
        assertFalse("lockCook() should have returned False",
                testPS.lockCook());

        // Tests when (!slots.isEmpty() = True) and ((lockedCook == null) = True) in lockCook()
        testPS.lockedCook = null;
        testPS.place(Ingredients.cookedPatty);
        testPS.place(Ingredients.cooked_bun);
        GameScreen.cook = new Cook(new Vector2(15, 15), 2);
        // Have to create a cook above otherwise there will be a null pointer exception in PrepStation.slotsToRecipe()
        assertTrue("lockCook() should have returned True",
                testPS.lockCook());
        assertSame("The if branch of (if (lockedCook == null)) should have been taken but hasn't",
                testPS.lockedCook,  GameScreen.cook);

        // Tests when (!slots.isEmpty() = True) and ((lockedCook == null) = False) in lockCook()
        // Don't need to change the ingredient stack again since there is already a valid recipe on it!
        testPS.lockedCook = GameScreen.cook;
        assertTrue("lockCook() should have returned True",
                testPS.lockCook());
        assertTrue("The else branch of (if (lockedCook == null)) should have been taken but hasn't",
                testPS.lockedCook.locked = true);

        // Tests when (!slots.isEmpty() = False), (slotsToRecipe() = False)
        // and ((lockedCook != null) = True) in lockCook()
        testPS.take();
        testPS.take();
        //lockedCook is already != null
        assertFalse("lockCook() should have returned False", testPS.lockCook());
        assertNull("The (if (lockedCook != null)) branch hasn't been taken but should have been",
                testPS.lockedCook);
    }

    @Test
    public void testStationTake(){
        Vector2 testPos = new Vector2(15,16);
        PrepStation testPS = new PrepStation(testPos);
        StationManager.stations.put(testPos, testPS);
        assertNull("The ingredient stack should be empty so null should be returned but wasn't", testPS.take());

        testPS.infinite = true;
        testPS.place(Ingredients.cookedPatty);
        testPS.place(Ingredients.cooked_bun);
        assertTrue("A new ingredient instance should have been returned but wasn't",
                testPS.take() instanceof  Ingredient);
    }

    /**
     * Tests lots of the checkInteractedTile() cases; some have been covered in above tests
     */
    @Test
    public void testStationManagerCheckInteractedTile() {
        // Can't test handleStations() because the method would have to be static or something else would
        // have to change but it would impact how other code works

        // Can't test checkInteractedTile with the "Bin" string because batch needs to not be null but it would be

        // placeIngredientStation() is a private method so can't easily test here

        StationManager testSM = new StationManager();

        GameScreen.cook = new Cook(new Vector2(15, 15), 2);

        // Need GameScreen.cook.slots to be the max size (5) or greater
        GameScreen.cook.heldItems.add(Ingredients.lettuce);
        GameScreen.cook.heldItems.add(Ingredients.lettuce);
        GameScreen.cook.heldItems.add(Ingredients.lettuce);
        GameScreen.cook.heldItems.add(Ingredients.lettuce);
        GameScreen.cook.heldItems.add(Ingredients.lettuce);

        GameScreen.control = new Control(); // GameScreen.control.interact = false currently

        Vector2 testPos1 = new Vector2(2, 2);
        testSM.checkInteractedTile("Buns", testPos1);
        assertNotNull("A new Ingredient station at position (2,2) should have been created but wasn't",
                StationManager.stations.get(testPos1));
        assertTrue("A bun ingredient should have been added to the new Ingredient station's slots but wasn't",
                StationManager.stations.get(testPos1).slots.peek() == Ingredients.bun);


        // Tests the same as above but with a different ingredient, and when
        // GameScreen.control.interact = true instead of false
        Vector2 testPos2 = new Vector2(4, 4);
        GameScreen.control.interact = true;
        testSM.checkInteractedTile("Patties", testPos2);
        assertNotNull("A new Ingredient station at position (4,4) should have been created but wasn't",
                StationManager.stations.get(testPos2));
        assertTrue("An unformed patty ingredient should have been added to the new" +
                        " Ingredient station's slots but wasn't",
                StationManager.stations.get(testPos2).slots.peek() == Ingredients.unformedPatty);

        // Tests the same as above but with a different ingredient
        Vector2 testPos3 = new Vector2(6, 6);
        testSM.checkInteractedTile("Lettuces", testPos3);
        assertNotNull("A new Ingredient station at position (6,6) should have been created but wasn't",
                StationManager.stations.get(testPos3));
        assertTrue("An lettuce ingredient should have been added to the new" +
                        " Ingredient station's slots but wasn't",
                StationManager.stations.get(testPos3).slots.peek() == Ingredients.lettuce);

        // Tests the same as above but with a different ingredient
        Vector2 testPos4 = new Vector2(8, 8);
        testSM.checkInteractedTile("Tomatoes", testPos4);
        assertNotNull("A new Ingredient station at position (8,8) should have been created but wasn't",
                StationManager.stations.get(testPos4));
        assertTrue("A tomato ingredient should have been added to the new" +
                        " Ingredient station's slots but wasn't",
                StationManager.stations.get(testPos4).slots.peek() == Ingredients.tomato);

        // Tests the same as above but with a different ingredient
        Vector2 testPos5 = new Vector2(10, 10);
        testSM.checkInteractedTile("Onions", testPos5);
        assertNotNull("A new Ingredient station at position (10,10) should have been created but wasn't",
                StationManager.stations.get(testPos5));
        assertTrue("An onion ingredient should have been added to the new" +
                        " Ingredient station's slots but wasn't",
                StationManager.stations.get(testPos5).slots.peek() == Ingredients.onion);

        // Tests the same as above but with a different ingredient
        Vector2 testPos6 = new Vector2(12, 12);
        testSM.checkInteractedTile("Potatoes", testPos6);
        assertNotNull("A new Ingredient station at position (12,12) should have been created but wasn't",
                StationManager.stations.get(testPos6));
        assertTrue("A potato ingredient should have been added to the new" +
                        " Ingredient station's slots but wasn't",
                StationManager.stations.get(testPos6).slots.peek() == Ingredients.potato);

        // Tests the same as above but with a different ingredient
        Vector2 testPos7 = new Vector2(14, 14);
        testSM.checkInteractedTile("Beans", testPos7);
        assertNotNull("A new Ingredient station at position (14,14) should have been created but wasn't",
                StationManager.stations.get(testPos7));
        assertTrue("A bean ingredient should have been added to the new" +
                        " Ingredient station's slots but wasn't",
                StationManager.stations.get(testPos7).slots.peek() == Ingredients.beans);

        // Tests the same as above but with a different ingredient
        Vector2 testPos8 = new Vector2(16, 16);
        testSM.checkInteractedTile("Cheese", testPos8);
        assertNotNull("A new Ingredient station at position (16, 16) should have been created but wasn't",
                StationManager.stations.get(testPos8));
        assertTrue("A cheese ingredient should have been added to the new" +
                        " Ingredient station's slots but wasn't",
                StationManager.stations.get(testPos8).slots.peek() == Ingredients.cheese);

        // Tests the same as above but with a different ingredient
        Vector2 testPos9 = new Vector2(18, 18);
        testSM.checkInteractedTile("Dough", testPos9);
        assertNotNull("A new Ingredient station at position (18, 18) should have been created but wasn't",
                StationManager.stations.get(testPos9));
        assertTrue("A dough ingredient should have been added to the new" +
                        " Ingredient station's slots but wasn't",
                StationManager.stations.get(testPos9).slots.peek() == Ingredients.dough);
    }
}
