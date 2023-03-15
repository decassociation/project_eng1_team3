
package com.team3gdx.game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class Save {
    private final String fileName = "save.txt";
    private MainGameClass game;

    public Save(MainGameClass game) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(game.getScore() + "\n");
            writer.write(game.getWave() + "\n");
            writer.write(game.getCustomersServed() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}