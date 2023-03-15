package com.team3gdx.game;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Load {
    private static final String FILE_NAME = "save.txt";
    private MainGameClass game;

    public Load(MainGameClass game) {
        this.game = game;
    }

    public void load() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            if ((line = reader.readLine()) != null) {
                game.setScore(Integer.parseInt(line.trim()));
            }
            if ((line = reader.readLine()) != null) {
                game.setWave(Integer.parseInt(line.trim()));
            }
            if ((line = reader.readLine()) != null) {
                game.setCustomersServed(Integer.parseInt(line.trim()));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}