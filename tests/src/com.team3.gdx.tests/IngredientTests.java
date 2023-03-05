package com.team3.gdx.tests;

import com.team3gdx.game.food.Ingredient;
import com.team3gdx.game.food.Ingredients;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Stack;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class IngredientTests {

    @Test
    public void testIngredientClone(){
        Ingredient formedPattyClone = new Ingredient(Ingredients.formedPatty);
        assertEquals("The cloned formedPatty ingredient should be the same as the original but isn't",
                Ingredients.formedPatty, formedPattyClone);
    }

}
