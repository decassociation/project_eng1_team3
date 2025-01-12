package com.team3gdx.game.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * All available ingredient.
 * 
 */
public class Ingredients {

	// Meats.
	public static Ingredient unformedPatty = new Ingredient(null, 32, 32, "unformed_patty", 0, .5f);
	public static Ingredient formedPatty = new Ingredient(null, 32, 32, "patty", 0, .5f);
	public static Ingredient cookedPatty = new Ingredient(null, 32, 32, "patty", 0, .5f);
	// Cooked meats.
	static {
		cookedPatty.status = Status.COOKED;
		cookedPatty.flipped = true;
	}
	public static Ingredient burnedPatty = new Ingredient(null, 32, 32, "patty", 0, .5f);
	static {
		burnedPatty.status = Status.BURNED;
		burnedPatty.flipped = true;
	}

	// Vegetables.
	public static Ingredient lettuce = new Ingredient(null, 32, 32, "lettuce", 1, 0);
	public static Ingredient tomato = new Ingredient(null, 32, 32, "tomato", 1, 0);
	public static Ingredient onion = new Ingredient(null, 32, 32, "onion", 1, 0);
	// Chopped vegetables.
	public static Ingredient lettuceChopped = new Ingredient(null, 32, 32, "lettuce", 1, 0);
	static {
		lettuceChopped.slices = 1;
		lettuceChopped.status = Status.CUT;
	}
	public static Ingredient tomatoChopped = new Ingredient(null, 32, 32, "tomato", 1, 0);
	static {
		tomatoChopped.slices = 1;
		tomatoChopped.status = Status.CUT;
	}
	public static Ingredient onionChopped = new Ingredient(null, 32, 32, "onion", 1, 0);
	static {
		onionChopped.slices = 1;
		onionChopped.status = Status.CUT;
	}

	public static Ingredient tomatoPaste = new Ingredient(null, 32, 32, "tomato_paste", 1, 0);

	// Breads.
	public static Ingredient bun = new Ingredient(new Vector2(0, 0), 32, 32, "burger_bun", 0, .5f);
	// Toasted breads.
	public static Ingredient cooked_bun = new Ingredient(new Vector2(0, 0), 32, 32, "burger_bun", 0, .5f);
	static {
		cooked_bun.status = Status.COOKED;
		cooked_bun.flipped = true;
	}

	/**
	 * New ingredients below
	 */
	// Potatoes
	public static Ingredient potato = new Ingredient(new Vector2(0, 0), 32, 32, "potato", 0, .5f);
	// Baked potatoes
	public static Ingredient cooked_potato = new Ingredient(new Vector2(0, 0), 32, 32, "potato", 0, .5f);
	static {
		cooked_potato.status = Status.COOKED;
	}

	// Beans
	public static Ingredient beans = new Ingredient(new Vector2(0, 0), 32, 32, "beans", 0, .5f);
	// Baked beans
	public static Ingredient cooked_beans = new Ingredient(new Vector2(0, 0), 32, 32, "beans", 0, .5f);
	public static Ingredient cooked_beans_with_spill = new Ingredient(new Vector2(0, 0), 32, 32, "beans", 0, .5f);
	static {
		cooked_beans.status = Status.COOKED;
		cooked_beans.texture = new Texture("items/" + cooked_beans.name + "_cooked.png");
		cooked_beans_with_spill.status = Status.COOKED;
		cooked_beans_with_spill.texture = new Texture("items/" + cooked_beans_with_spill.name + "pill.png");
	}

	// Cheese
	public static Ingredient cheese = new Ingredient(new Vector2(0, 0), 32, 32, "cheese", 0, .5f);

	// Dough and pizza base
	public static Ingredient dough = new Ingredient(null, 32, 32, "dough", 0, .5f);
	public static Ingredient pizzaBase = new Ingredient(null, 32, 32, "pizza_base", 0, .5f);
	public static Ingredient cookedPizzaBase = new Ingredient(null, 32, 32, "pizza_base", 0, .5f);
	static {
		cookedPizzaBase.status = Status.COOKED;
	}

}
