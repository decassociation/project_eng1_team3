package com.team3gdx.game.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.team3gdx.game.entity.Cook;

import java.util.concurrent.ThreadLocalRandom;

public class Powerup {

    int x;
    int y;
    Texture texture;    // set this in a subclass
    Boolean active;
    private int size;

    /***
     * Powerup constructor
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Powerup(int x, int y){
        this.x = x;
        this.y = y;
        active = false;
        size = 48;
    }

    /***
     * Draw the powerup
     *
     * @param batch the SpriteBatch to draw to
     */
    public void draw(SpriteBatch batch){
        if(active) {
            batch.begin();
            batch.draw(texture, x, y);
            batch.end();
        }
    }

    /***
     * Get a rectangle for collision detection
     *
     * @return Rectangle object at x and y with a width and height of 48
     */
    Rectangle getCollideBox() {
        return new Rectangle(x, y, size, size);
    }

    /***
     * Apply the powerup to a cook that collides with the powerup and then deactivate
     *
     * @param cook the cook to check for collision and to apply the powerup to
     */
    public void checkCollision(Cook cook){
        if(active) {
            if (Intersector.overlaps(cook.getCollideBoxAtPosition(cook.getX(), cook.getY()), getCollideBox())) {
                System.out.println("collide");
                applyPowerup(cook);
                active = false;
            }
        }
    }

    /***
     * Set active flag and randomly select location
     */
    public void activate(){
        x = ThreadLocalRandom.current().nextInt(4*64, 14*64);
        y = ThreadLocalRandom.current().nextInt(64, 12*64);
        active = true;
    }

    /***
     * What to do when a cook collides with the powerup - override this in a subclass
     *
     * @param cook the cook which collides with the powerup
     */
    public void applyPowerup(Cook cook){

    }
}
