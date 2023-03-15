package com.team3gdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team3gdx.game.MainGameClass;

public class MainScreen implements Screen {
	final MainGameClass game;
	float v = 0;
	float s = 0;

	int gameResolutionX;
	int gameResolutionY;

	float buttonwidth;
	float buttonheight;

	float xSliderMin;
	float xSliderMax;

	float sliderWidth;

	Button sb;
	Button lb;
	Button ad;
	Button eg;
	Button end;
	Button load;
	Button web;
	Button cred;

	Rectangle volSlide;
	Rectangle volSlideBackgr;
	Rectangle musSlide;
	Rectangle musSlideBackgr;

	OrthographicCamera camera;
	Viewport viewport;

	Texture vButton;
	Texture vControl;
	Texture background;
	Texture startButton;
	Texture leaderBoard;
	Texture exitGame;
	Texture audio;
	Texture audioEdit;
	Texture endlessGame;
	Texture loadGame;
	Texture website;
	Texture credits;
	Stage stage;

	enum STATE {
		main, audio, leaderboard, new_game, endless_game, credits, testing
	}

	STATE state;

	/**
	 * Constructor for main menu screen
	 * 
	 * @param game - Entry point class
	 */
	public MainScreen(final MainGameClass game) {
		this.game = game;
		this.gameResolutionX = Gdx.graphics.getWidth();
		this.gameResolutionY = Gdx.graphics.getHeight();
		this.buttonwidth = (float) gameResolutionX / 3;
		this.buttonheight = (float) gameResolutionY / 6;

		this.volSlide = new Rectangle();
		volSlide.width = 3 * buttonheight / 12;
		volSlide.height = 3 * buttonheight / 12;

		this.volSlideBackgr = new Rectangle();
		volSlideBackgr.width = 2 * buttonwidth / 6;
		volSlideBackgr.height = buttonheight / 6;

		this.musSlide = new Rectangle();
		musSlide.width = 3 * buttonheight / 12;
		musSlide.height = 3 * buttonheight / 12;

		this.musSlideBackgr = new Rectangle();
		musSlideBackgr.width = 2 * buttonwidth / 6;
		musSlideBackgr.height = buttonheight / 6;

		this.xSliderMin = gameResolutionX / 2.0f + buttonwidth / 12;
		this.xSliderMax = xSliderMin + volSlideBackgr.width;
		this.sliderWidth = volSlideBackgr.width;
	}

	/**
	 * What should be done when the screen is shown
	 */
	@Override
	public void show() {
		float currentMusicVolumeSliderX = (MainGameClass.musicVolumeScale * sliderWidth) + xSliderMin;
		float currentGameVolumeSliderX = (MainGameClass.gameVolumeScale * sliderWidth) + xSliderMin;
		volSlide.setPosition(currentGameVolumeSliderX, 2 * gameResolutionY / 5.0f - buttonheight / 2 + buttonheight / 6
				+ volSlideBackgr.height / 2 - volSlide.height / 2);
		volSlideBackgr.setPosition((gameResolutionX / 2.0f) + buttonwidth / 12,
				2 * gameResolutionY / 5.0f - buttonheight / 2 + buttonheight / 6);
		musSlide.setPosition(currentMusicVolumeSliderX, 2 * gameResolutionY / 5.0f - buttonheight / 2
				+ 4 * buttonheight / 6 + musSlideBackgr.height / 2 - musSlide.height / 2);
		musSlideBackgr.setPosition((gameResolutionX / 2.0f) + buttonwidth / 12,
				2 * gameResolutionY / 5.0f - buttonheight / 2 + 4 * buttonheight / 6);

		state = STATE.main;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, gameResolutionX, gameResolutionY);
		viewport = new FitViewport(gameResolutionX, gameResolutionY, camera);

		vButton = new Texture(Gdx.files.internal("uielements/vButton.jpg"));
		vControl = new Texture(Gdx.files.internal("uielements/vControl.png"));
		startButton = new Texture(Gdx.files.internal("uielements/newgame.png"));
		background = new Texture(Gdx.files.internal("uielements/MainScreenBackground.jpg"));
		leaderBoard = new Texture(Gdx.files.internal("uielements/leaderboard1.png"));
		audio = new Texture(Gdx.files.internal("uielements/audio.png"));
		audioEdit = new Texture(Gdx.files.internal("uielements/background.png"));
		exitGame = new Texture(Gdx.files.internal("uielements/exitgame.png"));
		endlessGame = new Texture(Gdx.files.internal("uielements/endlessgame.png"));
		loadGame = new Texture(Gdx.files.internal("uielements/loadgame.png"));
		website = new Texture(Gdx.files.internal("uielements/logo.png"));
		credits = new Texture(Gdx.files.internal("uielements/CreditsButton.png"));


		sb = new Button(new TextureRegionDrawable(startButton));
		lb = new Button(new TextureRegionDrawable(leaderBoard));
		ad = new Button(new TextureRegionDrawable(audio));
		eg = new Button(new TextureRegionDrawable(exitGame));
		end = new Button(new TextureRegionDrawable(endlessGame));
		load = new Button(new TextureRegionDrawable(loadGame));
		web = new Button(new TextureRegionDrawable(website));
		cred = new Button(new TextureRegionDrawable(credits));

		float totalButtonHeight = buttonheight * 6;
		float startY = (gameResolutionY - totalButtonHeight) / 2;
		sb.setPosition(gameResolutionX / 10.0f, startY + 5 * buttonheight);
		end.setPosition(gameResolutionX / 10.0f, startY + 4 * buttonheight);
		load.setPosition(gameResolutionX / 10.0f, startY + 3 * buttonheight);
		lb.setPosition(gameResolutionX / 10.0f, startY + 2 * buttonheight);
		eg.setPosition(gameResolutionX / 10.0f, startY + buttonheight);
		ad.setPosition(gameResolutionX / 10.0f, startY);
		web.setPosition(1130, 400);
		cred.setPosition(1000, 700);



		lb.setSize(buttonwidth, buttonheight);
		ad.setSize(buttonwidth, buttonheight);
		eg.setSize(buttonwidth, buttonheight);
		sb.setSize(buttonwidth, buttonheight);
		end.setSize(buttonwidth, buttonheight);
		load.setSize(buttonwidth, buttonheight);
		web.setSize(250, 250);
		cred.setSize(buttonwidth, buttonheight);

		ad.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state = STATE.audio;
				super.touchUp(event, x, y, pointer, button);
			}
		});
		sb.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state = STATE.new_game;
				super.touchUp(event, x, y, pointer, button);
			}
		});
		lb.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state = STATE.leaderboard;
				super.touchUp(event, x, y, pointer, button);
			}
		});
		eg.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (state == STATE.main) {
					Gdx.app.exit();
				}
				super.touchUp(event, x, y, pointer, button);
			}
		});
		end.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state = STATE.endless_game;
				super.touchUp(event, x, y, pointer, button);
			}
		});
		load.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state = STATE.new_game;
				super.touchUp(event, x, y, pointer, button);
			}
		});
		web.addListener(new ClickListener(){
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.net.openURI("https://decassociation.github.io/project_eng1_team3/");
				super.touchUp(event, x, y, pointer, button);
			}
		});
		cred.addListener(new ClickListener(){
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state = STATE.credits;
				super.touchUp(event, x, y, pointer, button);
			}
		});
		stage = new Stage(viewport, game.batch);
		Gdx.input.setInputProcessor(stage);

		stage.addActor(sb);
		stage.addActor(lb);
		stage.addActor(ad);
		stage.addActor(eg);
		stage.addActor(end);
		stage.addActor(load);
		stage.addActor(web);
		stage.addActor(cred);
	}

	/**
	 * Main menu render method
	 * 
	 * @param delta - some change in time
	 */
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		ScreenUtils.clear(0, 0, 0, 0);
		game.batch.setProjectionMatrix(camera.combined);
		game.mainScreenMusic.play();

		game.batch.begin();
		game.batch.draw(background, 0, 0, gameResolutionX, gameResolutionY);
		game.batch.end();
		stage.act();
		stage.draw();
		changeScreen(state);

		if (Gdx.input.isKeyPressed(Keys.T)){
			state = STATE.testing;
		}

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			state = STATE.main;
		}
	}

	/**
	 * Change screen to specified state
	 * 
	 * @param state - state to change screen to
	 */
	public void changeScreen(STATE state) {
		if (state == STATE.testing){
			game.mainScreenMusic.dispose();
			game.setScreen(game.getTestingScreen());
		}
		if (state == STATE.new_game) {
			game.mainScreenMusic.dispose();
			//game.setScreen(new GameScreen(game, game.getMainScreen(), 5));
			game.setScreen(game.getWaveSelectScreen());
		}

		if (state == STATE.endless_game) {
			game.mainScreenMusic.dispose();
			game.setScreen(game.getGameScreen());
		}

		if (state == STATE.leaderboard) {
			game.mainScreenMusic.dispose();
			game.setScreen(game.getLeaderBoardScreen());
		}
		if (state == STATE.audio) {
			musicVolumeUpdate();
			gameVolumeUpdate();

			game.batch.begin();
			game.batch.draw(audioEdit, (float) gameResolutionX / 2, (float) 2 * gameResolutionY / 5 - buttonheight / 2,
					buttonwidth / 2, buttonheight);

			game.batch.draw(vControl, volSlideBackgr.getX(), volSlideBackgr.getY(), volSlideBackgr.width,
					volSlideBackgr.height);
			game.batch.draw(vButton, volSlide.getX() - volSlide.width / 2, volSlide.getY(), volSlide.width,
					volSlide.height);

			game.batch.draw(vControl, musSlideBackgr.getX(), musSlideBackgr.getY(), musSlideBackgr.width,
					musSlideBackgr.height);
			game.batch.draw(vButton, musSlide.getX() - musSlide.width / 2, musSlide.getY(), musSlide.width,
					musSlide.height);

			game.batch.end();
		}
		if (state == STATE.credits) {
			game.mainScreenMusic.dispose();
			game.setScreen(game.getCreditsScreen());
		}
	}

	/**
	 * Update music volume slider
	 */
	public void musicVolumeUpdate() {
		float fromTopy = Gdx.input.getY();
		float fromBottomy = gameResolutionY - fromTopy;
		float x = Gdx.input.getX();
		boolean change = musSlide.getY() <= fromBottomy & fromBottomy <= musSlide.getY() + musSlide.getHeight();
		if (Gdx.input.isTouched() & change) {
			if (x >= musSlideBackgr.getX() & x <= musSlideBackgr.getX() + musSlideBackgr.getWidth()) {
				musSlide.setPosition(Gdx.input.getX(), musSlide.getY());
				v = (musSlide.getX() - musSlideBackgr.getX()) / musSlideBackgr.getWidth();
				if (v < 0.01) {
					v = 0;
				}
				game.mainScreenMusic.setVolume(v);
				game.gameMusic.setVolume(v);
				MainGameClass.musicVolumeScale = v;
			}
		}
	}

	/**
	 * Update game volume slider
	 */
	public void gameVolumeUpdate() {
		float fromTopy = Gdx.input.getY();
		float fromBottomy = gameResolutionY - fromTopy;
		float x = Gdx.input.getX();
		boolean change = volSlide.getY() <= fromBottomy & fromBottomy <= volSlide.getY() + volSlide.getHeight();
		if (Gdx.input.isTouched() & change) {
			if (x >= volSlideBackgr.getX() & x <= volSlideBackgr.getX() + volSlideBackgr.getWidth()) {
				volSlide.setPosition(Gdx.input.getX(), volSlide.getY());
				s = (volSlide.getX() - volSlideBackgr.getX()) / volSlideBackgr.getWidth();
				if (s < 0.01) {
					s = 0;
				}
				MainGameClass.gameVolumeScale = s;
			}
		}
	}

	/**
	 * Resize window
	 * 
	 * @param width  - new window width
	 * @param height - new window height
	 */
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		viewport.update(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

}
