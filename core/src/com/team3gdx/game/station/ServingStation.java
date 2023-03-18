package com.team3gdx.game.station;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.entity.Customer;
import com.team3gdx.game.food.Ingredient;
import com.team3gdx.game.food.Menu;
import com.team3gdx.game.food.Recipe;
import com.team3gdx.game.screen.GameScreen;

public class ServingStation extends Station {

	String[] possibleOrders = new String[] { "Burger", "Salad", "Potato beans", "Pizza" };

	/**
	 * Configure allowed ingredient to be those on the menu.
	 */
	static Ingredient[] allowedIngredients = new Ingredient[Menu.RECIPES.size()];
	static {
		int i = 0;
		for (Recipe recipe : Menu.RECIPES.values()) {
			allowedIngredients[i] = new Ingredient(recipe);
			i++;
		}
	};

	public ServingStation(Vector2 pos) {
		super(pos, 1, false, allowedIngredients, "audio/soundFX/money-collect.mp3");
	}

	/**
	 * Check if there is a customer waiting, get their order and check if the
	 * serving station contains it.
	 */
	public void serveCustomer() {
		Customer waitingCustomer = GameScreen.cc.isCustomerAtPos(new Vector2(pos.x - 1, pos.y));
		if (waitingCustomer != null && waitingCustomer.locked) {
			if (GameScreen.currentWaitingCustomer == null) {
				waitingCustomer.order = possibleOrders[new Random().nextInt(possibleOrders.length)];
				waitingCustomer.arrived();
				GameScreen.currentWaitingCustomer = waitingCustomer;
			}
			if (waitingCustomer == GameScreen.currentWaitingCustomer && !slots.empty()
					&& slots.peek().equals(Menu.RECIPES.get(waitingCustomer.order))) {
				slots.pop();
				GameScreen.cc.delCustomer(waitingCustomer);

				/*  dont spawn new ones on serve
				if (GameScreen.ENDLESS || GameScreen.currentWave < GameScreen.NUMBER_OF_WAVES - 1){
					GameScreen.cc.spawnCustomer();
				}
				*/

				GameScreen.cc.totalServed++;

				GameScreen.currentWave++;
				waitingCustomer.locked = false;
				GameScreen.currentWaitingCustomer = null;
			}

		}

	}
	
}
