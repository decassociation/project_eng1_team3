package com.team3gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.team3gdx.game.screen.*;
import com.team3gdx.game.util.AudioController;

import java.io.*;

public class MainGameClass extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public Music mainScreenMusic;
	public Music gameMusic;
	public static float musicVolumeScale;
	public static float gameVolumeScale;
	private MainScreen mainScreen1;
	private GameScreen gameScreen1;
	private LeaderBoard leaderBoardScreen1;
	private WaveSelectScreen waveSelectScreen1;
	private CreditScreen CreditScreen1;
	public AudioController sounds;
	public ShapeRenderer shapeRenderer;
	private int customersServed;
	private int wave;
	private int score;

	@Override
	public void create() {
		// ====================================MUSIC=INITIALISATION======================================================
		musicVolumeScale = 0.4f;
		gameVolumeScale = 0.4f;
		mainScreenMusic = Gdx.audio.newMusic(Gdx.files.internal("uielements/MainScreenMusic.ogg"));
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("uielements/GameMusic.ogg"));
		mainScreenMusic.setLooping(false);
		mainScreenMusic.setVolume(musicVolumeScale);
		gameMusic.setLooping(false);
		gameMusic.setVolume(musicVolumeScale);
		// =================================SPRITEBATCH=AND=SHAPERENDERER================================================
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		// ==================================FONT=INITIALISATION=========================================================
		font = new BitmapFont(Gdx.files.internal("uielements/font.fnt"), Gdx.files.internal("uielements/font.png"),
				false);
		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		// ====================================GAME=SCREEN=INITIALISATION================================================
		mainScreen1 = new MainScreen(this);
		//gameScreen1 = new GameScreen(this, mainScreen1);
		leaderBoardScreen1 = new LeaderBoard(this, mainScreen1);
		waveSelectScreen1 = new WaveSelectScreen(this, mainScreen1);
		CreditScreen1 = new CreditScreen(this);
		this.setScreen(mainScreen1);
		// ==============================================================================================================
		load();
	}

	public MainScreen getMainScreen() {
		return mainScreen1;
	}

	public LeaderBoard getLeaderBoardScreen() {
		return leaderBoardScreen1;
	}

	public WaveSelectScreen getWaveSelectScreen() {
		return waveSelectScreen1;
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		saveGame(); // Save the game when the player exits
		super.dispose();
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setWave(int wave) {
		this.wave = wave;
	}

	public void setCustomersServed(int customersServed) {
		this.customersServed = customersServed;
	}

	public void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
			String line;
			if ((line = reader.readLine()) != null) {
				setScore(Integer.parseInt(line.trim()));
			}
			if ((line = reader.readLine()) != null) {
				setWave(Integer.parseInt(line.trim()));
			}
			if ((line = reader.readLine()) != null) {
				setCustomersServed(Integer.parseInt(line.trim()));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveGame() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt", false));
			writer.write(score + "\n");
			writer.write(wave + "\n");
			writer.write(customersServed + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Screen getCreditsScreen() {
		return (Screen) CreditScreen1;
	}

	public int getCustomersServed() {
		return customersServed;
	}

	public int getWave() {
		return wave;
	}

	public int getScore() {
		return score;
	}
}