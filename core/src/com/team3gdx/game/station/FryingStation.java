package com.team3gdx.game.station;

import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.food.Ingredient;
import com.team3gdx.game.food.Ingredients;

public class FryingStation extends CookingStation {

	private final static Ingredient[] ALLOWED_INGREDIENTS = { Ingredients.formedPatty, Ingredients.beans};

	public FryingStation(Vector2 pos) {
		super(pos, 1, ALLOWED_INGREDIENTS, "particles/flames.party", "audio/soundFX/frying.mp3");
	}

	// dont need to flip beans
	@Override
	public boolean place(Ingredient ingredient) {
		if (super.place(ingredient)) {
			if(ingredient.equals(Ingredients.beans)) ingredient.flipped = true;
			return true;
		}

		return false;
	}



}
