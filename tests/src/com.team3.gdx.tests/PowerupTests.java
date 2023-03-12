package com.team3.gdx.tests;

import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.entity.Cook;
import com.team3gdx.game.powerup.Powerup;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class PowerupTests {
    @Test
    public void testCollision(){
        Powerup powerup = new Powerup(15, 15);
        Cook cook = new Cook(new Vector2(15, 15), 1);
        powerup.active = true;
        powerup.checkCollision(cook);
        assertFalse(powerup.active);
    }
}
