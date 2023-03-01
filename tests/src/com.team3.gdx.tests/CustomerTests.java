package com.team3.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.team3gdx.game.entity.Customer;
import com.team3gdx.game.entity.CustomerController;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class CustomerTests {

    // This tests customer creation through the spawning of a customer
    // Need to test all 5 customers
    @Test
    public void testCustomerSpawn(){

        TiledMap map1 = new TmxMapLoader().load("map/art_map/customertest.tmx");
        CustomerController cc = new CustomerController(map1);
        // Test when no customers exist:
        assertTrue("There should not be any customers spawned/existing but at least one exists", cc.customers[0]==null
                && cc.customers[1]==null && cc.customers[2]==null && cc.customers[3]==null && cc.customers[4]==null);

        // Test when 1 customer exists
        cc.spawnCustomer();
        assertTrue("A customer should have been spawned so X.customers[0] should be of type Customer" +
                "but is not", cc.customers[0] instanceof Customer ); // This is highlighted as redundant but ISN'T!

        assertTrue("A single customer should have been spawned but either a customer hasn't been spawned" +
                "or multiple customers have been spawned", cc.customers[0] instanceof Customer
                && cc.customers[1]==null && cc.customers[2]==null && cc.customers[3]==null && cc.customers[4]==null);
                // This is highlighted as redundant but ISN'T!

        // Test when 2 customers exists
        cc.spawnCustomer();
        assertTrue("A customer should have been spawned so X.customers[1] should be of type Customer" +
                "but is not", cc.customers[1] instanceof Customer );

        // Test when 3 customers exists
        cc.spawnCustomer();
        assertTrue("A customer should have been spawned so X.customers[2] should be of type Customer" +
                "but is not", cc.customers[2] instanceof Customer );

        // Test when 4 customers exists
        cc.spawnCustomer();
        assertTrue("A customer should have been spawned so X.customers[3] should be of type Customer" +
                "but is not", cc.customers[3] instanceof Customer );

        // Test when 5 customers exists
        cc.spawnCustomer();
        assertTrue("A customer should have been spawned so X.customers[4] should be of type Customer" +
                "but is not", cc.customers[4] instanceof Customer );
    }



}
