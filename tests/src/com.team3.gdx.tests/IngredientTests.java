package com.team3.gdx.tests;

import com.team3gdx.game.food.Ingredient;
import com.team3gdx.game.food.Ingredients;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Tests Ingredient.java and Ingredients.java without testing interactions with any cooking stations
 * NOT SURE IF ANYMORE OF Ingredient.java can be tested as methods take a SpriteBatch parameter
 */
@RunWith(GdxTestRunner.class)
public class IngredientTests {

    @Test
    public void testIngredientClone(){
        Ingredient formedPattyClone = new Ingredient(Ingredients.formedPatty);
        assertEquals("The cloned formedPatty ingredient should be the same as the original but isn't",
                Ingredients.formedPatty, formedPattyClone);
    }

    @Test
    public void testFlip(){
        Ingredient formedPattyClone = new Ingredient(Ingredients.formedPatty);
        assertFalse("The cloned formedPatty ingredient flip check should be false",
                formedPattyClone.flip());
        assertFalse("The cloned formedPatty ingredient should not be flipped",
                formedPattyClone.flipped);

        formedPattyClone.cookedTime = 20;

        assertTrue("The cloned formedPatty ingredient flip check should be true",
                formedPattyClone.flip());
        assertTrue("The cloned formedPatty ingredient should be flipped",
                formedPattyClone.flipped);

    }

    @Test
    public void testEquals(){
        Ingredient formedPattyClone = new Ingredient(Ingredients.formedPatty);
        assertTrue("formedPatty clone should be exactly the same as the origninal",
                formedPattyClone.equals(Ingredients.formedPatty));
    }

}
