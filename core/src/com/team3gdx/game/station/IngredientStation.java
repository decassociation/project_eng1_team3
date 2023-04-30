package com.team3gdx.game.station;

import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.food.Ingredient;

public class IngredientStation extends Station {
	/**
	 * sets relevant parameters
	 * @param pos
	 * @param ingredient
	 */

	public IngredientStation(Vector2 pos, Ingredient ingredient) {
		super(pos, 1, true, null, null);
		slots.push(ingredient);
	}

	/**
	 * checks and returns the appropriate ingredient
	 * @return ingredient
	 */
	public Ingredient getIngredient() {
		if (slots.size() > 0) {
			return slots.peek();
		}
		return null;

	}

	/**
	 * Sets the ingredient that can be accessed from this station
	 * @param o
	 */
	public void setIngredient(Object o) {
		slots.push((Ingredient) o);
	}
}