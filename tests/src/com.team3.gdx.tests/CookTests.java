package com.team3.gdx.tests;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.MainGameClass;
import com.team3gdx.game.entity.Cook;
import com.team3gdx.game.food.Ingredient;
import com.team3gdx.game.food.Ingredients;
import com.team3gdx.game.screen.GameScreen;
import com.team3gdx.game.screen.MainScreen;
import com.team3gdx.game.screen.Tutorial;
import com.team3gdx.game.util.CollisionTile;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.team3gdx.game.util.Control;

import java.util.Stack;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class CookTests {
    @Test
    public void testMovement(){
        Cook cook = new Cook(new Vector2(15, 15), 1);

        Control control = new Control();
        boolean[] bools = {false, true};
        for (boolean UP : bools){
            for (boolean DOWN : bools){
                for (boolean LEFT : bools){
                    for (boolean RIGHT : bools){
                        control.up = UP;
                        control.down = DOWN;
                        control.left = LEFT;
                        control.right = RIGHT;

                        float x = cook.pos.x;
                        float y = cook.pos.y;
                        CollisionTile[][] collisionTiles = new CollisionTile[5][5];
                        cook.update(control, 5f, collisionTiles);

                        if (control.up && !control.down) {
                            assertEquals("up and not down", cook.pos.y, y + cook.speed * 5f, 0.0);
                        }
                        else if (control.down && !control.up) {
                            assertEquals("down and not up", cook.pos.y, y - cook.speed * 5f, 0.0);
                        }

                        if (control.left && !control.right) {
                            assertEquals("left and not right", cook.pos.x, x - cook.speed * 5f, 0.0);
                        }
                        else if (control.right && !control.left) {
                            assertEquals("right and not left", cook.pos.x, x + cook.speed * 5f, 0.0);
                        }

                        if (!control.left && !control.right && !control.up && !control.down){
                            assertEquals("none pressed x", cook.pos.x, x, 0.0);
                            assertEquals("none pressed y", cook.pos.y, y, 0.0);
                        }

                        if (control.left && control.right){
                            assertEquals("left and right", cook.pos.x, x, 0.0);
                        }

                        if (control.up && control.down){
                            assertEquals("up and down", cook.pos.y, y, 0.0);
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testPickUp(){
        Cook cook = new Cook(new Vector2(15, 15), 1);
        cook.pickUpItem(Ingredients.tomato);
        assertTrue("Top item on stack should be a tomato", cook.heldItems.peek().equals(Ingredients.tomato));
    }

    @Test
    public void testCreateCook(){ //
        int testnum = 2; //just to have an easy comparison
        Cook cook = new Cook(new Vector2(15, 15), testnum);
        assertTrue("Checks if the cook is created by checking if the cookno is equal to the given testnum",cook.getCookNo() == testnum);
    }

    @Test
    public void testDropItem(){
        Cook cook = new Cook(new Vector2(15, 15), 1);

        // Test when the cook is holding 1 item and drops the item
        cook.heldItems.setSize(1);
        cook.dropItem();
        assertEquals("Cook should be holding 0 items after dropping one but is not",
                0, cook.heldItems.size(), 0.5f);
        assertFalse("Cook.holding should be false but is true", cook.holding);

        // Test when the cook is holding more than 1 items and drops an item
        cook.heldItems.setSize(2);
        cook.dropItem();
        assertEquals("Cook should be holding 1 item after dropping one but is not",
                1, cook.heldItems.size(), 0.5f);

        cook.heldItems.setSize(10);
        cook.dropItem();
        assertEquals("Cook should be holding 9 items after dropping one but is not",
                9, cook.heldItems.size(), 0.5f);

        // Test when the cook is holding 0 items and tries to drop an item
        cook.heldItems.setSize(0);
        cook.dropItem();
        assertEquals("Cook should be holding 0 items after dropping none but is not",
                0, cook.heldItems.size(), 0.5f);
        assertFalse("Cook.holding should be false but is true", cook.holding);

    }

    @Test
    public void testFullCookStack(){
        Cook cook = new Cook(new Vector2(15, 15), 1);

        // Test when cook stack is below max
        cook.heldItems.setSize(1);
        assertFalse("Cook stack / no. items held should be 1 -less than 5 (max)- but isn't", cook.full());
        // Test when cook stack is just below max threshold (so is okay)
        cook.heldItems.setSize(4);
        assertFalse("Cook stack / no. items held should be 3 -less than 5 (max)- but isn't", cook.full());
        // Test when cook stack is at max
        cook.heldItems.setSize(5);
        assertTrue("Cook stack / no. items held should be 5 (max) but isn't", cook.full());
        // Test when cook stack is above max
        cook.heldItems.setSize(10);
        assertTrue("Cook stack / no. items held should be 10 (above max) but isn't", cook.full());
    }

    public void testPlace(){

    }

    // This test doesn't work at the moment
    public void testCollisionAttempt(Cook cook, Control control, CollisionTile[][] cltiles, String assertMessage){
        cook.update(control, 1f, cltiles);
        assertTrue(assertMessage, cook.pos.x == 50 && cook.pos.y == 50);
        cook.pos.x = 50;
        cook.pos.y = 50;
        control.up = false;
        control.down = false;
        control.left = false;
        control.right = false;
    }
    @Test
    public void testCollision(){


        Cook cook = new Cook(new Vector2(50, 50), 1);
        Control control = new Control();
        CollisionTile[][] cltiles = new CollisionTile[3][3];

        // make all tiles same with and height as cook rectangle to make things easier
        int x = 22;
        int y = 65;
        for(int i = 0; i < cltiles.length; i++){
            x = 22;
            for(int j = 0; j < cltiles[i].length; j++){
                if(!(i == 1 && j == 1)){
                    cltiles[i][j] = new CollisionTile(x, y, 40, 25);
                    x += 40;
                }
            }
            y -= 25;
        }

        // no key pressed
        cook.update(control, 1f, cltiles);
        assertTrue("no key pressed", cook.pos.x == 50 && cook.pos.y == 50);

        // up and left
        control.up = true;
        control.left = true;
        testCollisionAttempt(cook, control, cltiles, "up and left pressed");

        // up
        control.up = true;
        testCollisionAttempt(cook, control, cltiles, "up pressed");

        // up and right
        control.up = true;
        control.right = true;
        testCollisionAttempt(cook, control, cltiles, "up and right pressed");

        // left
        control.left = true;
        testCollisionAttempt(cook, control, cltiles, "left pressed");

        // right
        control.right = true;
        testCollisionAttempt(cook, control, cltiles, "right pressed");

        // down and left
        control.down = true;
        control.left = true;
        testCollisionAttempt(cook, control, cltiles, "down and left pressed");

        // down
        control.down = true;
        testCollisionAttempt(cook, control, cltiles, "down pressed");

        // down and right
        control.down = true;
        control.right = true;
        testCollisionAttempt(cook, control, cltiles, "down and right pressed");
    }

    @Test
    public void testCookSwitch(){
        // This function uses the static instances of GameScreen and Tutorial so don't need to instantiate

        GameScreen.control = new Control();
        // Need to manually make a new Control instance bc when we test it hasn't already been created

        //-----------------------------------TESTS FOR TAB SWITCH-------------------------------------------------------
        // *Automatic version of cook switch tests*:
        for(int i = 0; i < GameScreen.cooks.length; i++){
            assertTrue("Current cook should be cook " + i, GameScreen.cook.equals(GameScreen.cooks[i]));
            GameScreen.control.tab = true;
            Tutorial.complete = true;
            GameScreen.checkCookSwitch();
        }
        // *Manual version of cook switch tests*:
        // Tests below test another cycle of changes after the automatic set to ensure you can go from cook 3 to 1
        assertTrue("Current cook should be gameScreen.cooks[0] (first one)", GameScreen.cook.equals(GameScreen.cooks[0]));
        GameScreen.control.tab = true;
        Tutorial.complete = true;
        GameScreen.checkCookSwitch();
        assertTrue("Current cook should be gameScreen.cooks[1] (second one)", GameScreen.cook.equals(GameScreen.cooks[1]));
        GameScreen.control.tab = true;
        Tutorial.complete = true;
        GameScreen.checkCookSwitch();
        assertTrue("Current cook should be gameScreen.cooks[2] (third one)", GameScreen.cook.equals(GameScreen.cooks[2]));
        GameScreen.control.tab = true;
        Tutorial.complete = true;
        GameScreen.checkCookSwitch();

        //-----------------------------------TESTS FOR TAB SWITCH-------------------------------------------------------
    }
}
