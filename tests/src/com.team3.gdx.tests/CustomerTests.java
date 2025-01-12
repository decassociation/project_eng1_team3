package com.team3.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.entity.Customer;
import com.team3gdx.game.entity.CustomerController;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class CustomerTests {

    // This tests customer creation through the spawning of a customer
    @Test
    public void testCustomerSpawn(){

        TiledMap map1 = new TmxMapLoader().load("map/art_map/customertest.tmx");
        CustomerController cc = new CustomerController(map1, "normal");
        // Test when no customers exist:
        assertTrue("There should not be any customers spawned/existing but at least one exists", cc.customers[0]==null
                && cc.customers[1]==null && cc.customers[2]==null && cc.customers[3]==null && cc.customers[4]==null);
        assertEquals("Number of active customers (amountActiveCustomers) should be 0 but isn't", 0, cc.amountActiveCustomers);

        // Test when 1 customer exists
        cc.spawnCustomer();
        assertTrue("A customer should have been spawned so X.customers[0] should be of type Customer" +
                "but is not", cc.customers[0] instanceof Customer ); // This is highlighted as redundant but ISN'T!
        assertTrue("A single customer should have been spawned but either a customer hasn't been spawned" +
                "or multiple customers have been spawned", cc.customers[0] instanceof Customer
                && cc.customers[1]==null && cc.customers[2]==null && cc.customers[3]==null && cc.customers[4]==null);
                // This is highlighted as redundant but ISN'T!
        assertEquals("Number of active customers (amountActiveCustomers) should be 1 but isn't", 1, cc.amountActiveCustomers);

        // Test when 2 customers exists
        cc.spawnCustomer();
        assertTrue("A customer should have been spawned so X.customers[1] should be of type Customer" +
                "but is not", cc.customers[1] instanceof Customer );
        assertEquals("Number of active customers (amountActiveCustomers) should be 2 but isn't", 2, cc.amountActiveCustomers);

        // Test when 3 customers exists
        cc.spawnCustomer();
        assertTrue("A customer should have been spawned so X.customers[2] should be of type Customer" +
                "but is not", cc.customers[2] instanceof Customer );
        assertEquals("Number of active customers (amountActiveCustomers) should be 3 but isn't", 3, cc.amountActiveCustomers);


        // Test when 4 customers exists
        cc.spawnCustomer();
        assertTrue("A customer should have been spawned so X.customers[3] should be of type Customer" +
                "but is not", cc.customers[3] instanceof Customer );
        assertEquals("Number of active customers (amountActiveCustomers) should be 4 but isn't", 4, cc.amountActiveCustomers);


        // Test when 5 customers exists
        cc.spawnCustomer();
        assertTrue("A customer should have been spawned so X.customers[4] should be of type Customer" +
                "but is not", cc.customers[4] instanceof Customer );
        assertTrue("Max number of customers should have been spawned so there should be a Customer type" +
                "variable at every index of customers", cc.customers[0] instanceof Customer
                && cc.customers[1] instanceof Customer && cc.customers[2] instanceof Customer
                && cc.customers[3] instanceof Customer && cc.customers[4] instanceof Customer);
        // This is highlighted as redundant but ISN'T!
        assertEquals("Number of active customers (amountActiveCustomers) should be 5 but isn't", 5, cc.amountActiveCustomers);
    }

    @Test
    public void testCustomerDeletion(){
        TiledMap map1 = new TmxMapLoader().load("map/art_map/customertest.tmx");
        CustomerController cc = new CustomerController(map1, "normal");

        // Testing the customer deletion method with signature, 'public void delCustomer(int num)':
        cc.spawnCustomer();
        assertEquals("Number of active customers (amountActiveCustomers) should be 1 but isn't", 1, cc.amountActiveCustomers);
        // A customer has to be locked for it to be deleted (think when a customer is locked it means that they're ordering)
        cc.customers[0].locked = true;
        cc.delCustomer(0);
        assertNull("First customer should have been deleted but it hasn't ", cc.customers[0]);
        assertEquals("Number of active customers (amountActiveCustomers) should be 0 but isn't", 0, cc.amountActiveCustomers);

        // Testing the customer deletion method with signature, 'public void delCustomer(Customer customer)':
        cc.spawnCustomer();
        assertEquals("Number of active customers (amountActiveCustomers) should be 1 but isn't", 1, cc.amountActiveCustomers);
        Customer testCustomer1 = cc.customers[0];
        // A customer has to be locked for it to be deleted (think when a customer is locked it means that they're ordering)
        testCustomer1.locked = true;
        cc.delCustomer(testCustomer1);
        assertEquals("Number of active customers (amountActiveCustomers) should be 0 but isn't", 0, cc.amountActiveCustomers);
        assertNull("There should be no existing customers but at least one exists", cc.customers[0]);

    }

    @Test
    public void testCustomerAtPosition(){
        TiledMap map1 = new TmxMapLoader().load("map/art_map/customertest.tmx");
        CustomerController cc = new CustomerController(map1, "normal");
        cc.spawnCustomer();
        Customer testCustomer = cc.customers[0];
        testCustomer.posy = 64;
        testCustomer.locked = true;
        assertEquals("", testCustomer, cc.isCustomerAtPos(new Vector2(testCustomer.posx, testCustomer.posy)));
        // This isn't going to work because  isCustomerAtPos() doesn't work as it should really

        // Test when there is no customer to check the position of:
//        cc.isCustomerAtPos(new Vector2(cc.customers[2].posx, cc.customers[2].posy));
    }


    /** NOTE: There are a few other exceptions that could be thrown by the computeCustomerZone method, but
     * we would have to create multiple, very specific tile maps to cover all of them. The one tested below is the
     * first exception that occurs */
    @Rule // Used by testIncorrectCustomerZone
    public ExpectedException exceptionRule = ExpectedException.none(); // .none() is deprecated

    /**
     * Tests that the correct exception is thrown when a tilemap is loaded which has no area for customers
     */
    @Test
    public void testIncorrectCustomerZone(){
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(CoreMatchers.equalTo("No customer zone was included in the tile map"));
        /** Have to use 'CoreMatchers.equalTo' as the version of Mockito we use doesn't have the containsString method
         * that expectMessage(String substring) uses.  We're using expectMessage(Matcher<String> matcher) instead */


        TiledMap wrongMap = new TmxMapLoader().load("map/customerTestMap.tmx");
        CustomerController cc = new CustomerController(wrongMap, "normal");
    }


}
