package com.team3gdx.game.station;

import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.food.Ingredient;
import com.team3gdx.game.food.Ingredients;

public class BakingStation extends CookingStation {

	private final static Ingredient[] ALLOWED_INGREDIENTS = { Ingredients.bun, Ingredients.cooked_bun, Ingredients.potato, Ingredients.pizzaBase };

	/**
	 * More object specific attributes added
	 * @param pos
	 */
	public BakingStation(Vector2 pos) {
		super(pos, 4, ALLOWED_INGREDIENTS, "particles/smokes.party", "audio/soundFX/frying.mp3");
	}

	/**
	 * Facilitates placing of objects into this station
	 * @param ingredient The ingredient to be placed.
	 * @return if action was successful
	 */
	@Override
	public boolean place(Ingredient ingredient) {
		if (super.place(ingredient)) {
			ingredient.flipped = true;
			return true;
		}

		return false;
	}

}
