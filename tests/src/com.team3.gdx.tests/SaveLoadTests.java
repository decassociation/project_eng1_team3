package com.team3.gdx.tests;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.team3gdx.game.MainGameClass;
import com.team3gdx.game.screen.GameScreen;
import com.team3gdx.game.screen.MainScreen;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class SaveLoadTests {
    @Test
    public void testSaveLoadReputationPoints(){
        MainGameClass game = new MainGameClass();
        GameScreen gameScreen = new GameScreen(game, game.getMainScreen(), true);

        int initRepPoints = gameScreen.getReputationPoints();
        int toAdd = 7357;
        gameScreen.addReputationPoints(toAdd);

        gameScreen.changeScreen(GameScreen.STATE.main); // save the game

        // load
        GameScreen gameScreen1 = new GameScreen(game, game.getMainScreen(), true);
        assertTrue(gameScreen1.getReputationPoints() == initRepPoints + toAdd);

        // reset save file
        Preferences prefs = Gdx.app.getPreferences("testSave");
        prefs.clear();
        prefs.flush();
    }

    @Test
    public void testSaveLoadTotalServed(){
        MainGameClass game = new MainGameClass();
        GameScreen gameScreen = new GameScreen(game, new MainScreen(new MainGameClass()), true);

        int totalServed = gameScreen.cc.totalServed;
        int toAdd = 7357;
        gameScreen.cc.totalServed += toAdd;

        gameScreen.changeScreen(GameScreen.STATE.main); // save the game

        // load
        GameScreen gameScreen1 = new GameScreen(game, game.getMainScreen(), true);
        assertTrue(gameScreen1.cc.totalServed == totalServed + toAdd);

        // reset save file
        Preferences prefs = Gdx.app.getPreferences("testSave");
        prefs.clear();
        prefs.flush();
    }

    @Test
    public void testSaveLoadCurrentWave(){
        MainGameClass game = new MainGameClass();
        GameScreen gameScreen = new GameScreen(game, game.getMainScreen(), true);

        int currentWave = gameScreen.currentWave;
        int toAdd = 7357;
        gameScreen.currentWave += toAdd;

        gameScreen.changeScreen(GameScreen.STATE.main); // save the game

        // load
        GameScreen gameScreen1 = new GameScreen(game, game.getMainScreen(), true);
        assertTrue(gameScreen1.currentWave == currentWave + toAdd);

        // reset save file
        Preferences prefs = Gdx.app.getPreferences("testSave");
        prefs.clear();
        prefs.flush();
    }

    @Test
    public void testSaveLoadNumberOfWaves(){
        MainGameClass game = new MainGameClass();
        GameScreen gameScreen = new GameScreen(game, game.getMainScreen(), true);

        int numberOfWaves = gameScreen.NUMBER_OF_WAVES;
        int toAdd = 7357;
        gameScreen.NUMBER_OF_WAVES += toAdd;

        gameScreen.changeScreen(GameScreen.STATE.main); // save the game

        // load
        GameScreen gameScreen1 = new GameScreen(game, game.getMainScreen(), true);
        assertTrue(gameScreen1.NUMBER_OF_WAVES == numberOfWaves + toAdd);

        // reset save file
        Preferences prefs = Gdx.app.getPreferences("testSave");
        prefs.clear();
        prefs.flush();
    }

    @Test
    public void testSaveLoadEndless(){
        MainGameClass game = new MainGameClass();
        GameScreen gameScreen = new GameScreen(game, game.getMainScreen(), true);

        Boolean endless = gameScreen.ENDLESS;
        gameScreen.ENDLESS = !endless;

        gameScreen.changeScreen(GameScreen.STATE.main); // save the game

        // load
        GameScreen gameScreen1 = new GameScreen(game, game.getMainScreen(), true);
        assertTrue(gameScreen1.ENDLESS == !endless);

        // reset save file
        Preferences prefs = Gdx.app.getPreferences("testSave");
        prefs.clear();
        prefs.flush();
    }

    @Test
    public void testSaveLoadThirdChef(){
        MainGameClass game = new MainGameClass();
        GameScreen gameScreen = new GameScreen(game, game.getMainScreen(), true);

        Boolean thirdChef = gameScreen.thirdChef;
        gameScreen.thirdChef = !thirdChef;

        gameScreen.changeScreen(GameScreen.STATE.main); // save the game

        // load
        GameScreen gameScreen1 = new GameScreen(game, game.getMainScreen(), true);
        assertTrue(gameScreen1.thirdChef == !thirdChef);

        // reset save file
        Preferences prefs = Gdx.app.getPreferences("testSave");
        prefs.clear();
        prefs.flush();
    }
    @Test
    public void testSaveLoadSecondBaking(){
        MainGameClass game = new MainGameClass();
        GameScreen gameScreen = new GameScreen(game, game.getMainScreen(), true);

        Boolean secondBaking = gameScreen.secondBaking;
        gameScreen.secondBaking = !secondBaking;

        gameScreen.changeScreen(GameScreen.STATE.main); // save the game

        // load
        GameScreen gameScreen1 = new GameScreen(game, game.getMainScreen(), true);
        assertTrue(gameScreen1.secondBaking == !secondBaking);

        // reset save file
        Preferences prefs = Gdx.app.getPreferences("testSave");
        prefs.clear();
        prefs.flush();
    }

    @Test
    public void testSaveLoadSecondCutting(){
        MainGameClass game = new MainGameClass();
        GameScreen gameScreen = new GameScreen(game, game.getMainScreen(), true);

        Boolean secondCutting = gameScreen.secondCutting;
        gameScreen.secondCutting = !secondCutting;

        gameScreen.changeScreen(GameScreen.STATE.main); // save the game

        // load
        GameScreen gameScreen1 = new GameScreen(game, game.getMainScreen(), true);
        assertTrue(gameScreen1.secondCutting == !secondCutting);

        // reset save file
        Preferences prefs = Gdx.app.getPreferences("testSave");
        prefs.clear();
        prefs.flush();
    }

    @Test
    public void testSaveLoadDifficulty(){
        MainGameClass game = new MainGameClass();
        GameScreen gameScreen = new GameScreen(game, game.getMainScreen(), true);

        String difficulty = gameScreen.difficulty;
        if(difficulty != "normal") gameScreen.difficulty = "normal";
        else gameScreen.difficulty = "hard";

        gameScreen.changeScreen(GameScreen.STATE.main); // save the game

        // load
        GameScreen gameScreen1 = new GameScreen(game, game.getMainScreen(), true);
        assertTrue(gameScreen1.difficulty != difficulty);

        // reset save file
        Preferences prefs = Gdx.app.getPreferences("testSave");
        prefs.clear();
        prefs.flush();
    }

}
