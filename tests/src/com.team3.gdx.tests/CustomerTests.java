package com.team3.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.team3gdx.game.entity.CustomerController;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class CustomerTests {

    // This tests customer creation through the spawning of a customer
    // Need to test all 5 customers
    @Test
    public void testCustomerSpawn(){

        TiledMap map1 = new TmxMapLoader().load("map/art_map/customertest.tmx");
        CustomerController cc = new CustomerController(map1);
        // assertEquals("", );
        cc.spawnCustomer();


    }

}
