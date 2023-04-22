package com.team3gdx.game.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team3gdx.game.MainGameClass;
import com.team3gdx.game.entity.Cook;
import com.team3gdx.game.entity.Customer;
import com.team3gdx.game.entity.CustomerController;
import com.team3gdx.game.entity.Entity;
import com.team3gdx.game.food.Menu;
import com.team3gdx.game.powerup.PowerupController;
import com.team3gdx.game.powerup.SpeedBoost;
import com.team3gdx.game.station.Station;
import com.team3gdx.game.station.StationManager;
import com.team3gdx.game.util.CollisionTile;
import com.team3gdx.game.util.Control;

import java.util.ArrayList;





import com.team3gdx.game.powerup.nukeLikeCodZombies;

public class GameScreen implements Screen {

	public static int NUMBER_OF_WAVES;

	public static boolean ENDLESS;

	final MainGameClass game;
	final MainScreen ms;

	public static int currentWave;
	public static int reputationPoints;

	Rectangle volSlideBackgr;
	Rectangle volSlide;
	Rectangle musSlideBackgr;
	Rectangle musSlide;
	Rectangle audioBackground;
	Rectangle optionsBackground;
	Texture ESC;
	Texture MENU;
	Texture BACKTOMAINSCREEN;
	Texture RESUME;
	Texture AUDIO;
	Texture audioEdit;
	Texture vControl;
	Texture vButton;
	Texture SHOP;
	Texture BUYCHEF2;
	Texture BUYCHEF3;
	Texture BUYBAKINGSTATION;
	Texture BUYCUTTINGSTATION;
	Texture REDX;
	Button mn;
	Button rs;
	Button rs2;
	Button ad;
	Button btms;
	Button end;
	Button shop;
	Button buyChef2;
	Button buyChef3;
	Button buyBakingStation;
	Button buyCuttingStation;
	public static CollisionTile[][] CLTiles;
	Viewport uiViewport;
	Viewport worldViewport;
	Stage stage;
	Stage stage2;
	Stage stage3;
	public Boolean thirdChef;
	public Boolean secondBaking;
	public Boolean secondCutting;
	OrthographicCamera uiCamera;
	public static OrthographicCamera worldCamera;

	//TESTING NUKE
	nukeLikeCodZombies nuker = new nukeLikeCodZombies(0,0);



	public static Customer currentWaitingCustomer = null;

	public void addReputationPoints(int i) {
		reputationPoints += i;
	}

	public enum STATE {
		Pause, Continue, main, audio, shop, Tutorial
	}

	public static STATE state1;
	float v;
	float s;
	int gameResolutionX;
	int gameResolutionY;
	float buttonwidth;
	float buttonheight;
	float xSliderMax;
	float xSliderMin;
	float sliderWidth;
	float shopX = gameResolutionX / 50.0f;

	float audioBackgroundWidth;
	float audioBackgroundHeight;
	float audioBackgroundx;
	float audioBackgroundy;
	long startTime;
	long timeOnStartup;
	long tempTime, tempThenTime;
	public static Control control;
	TiledMapRenderer tiledMapRenderer;
	public TiledMap map1;
	public static Cook[] cooks = new Cook[2];
	//public static ArrayList<Cook> cooks = new ArrayList<>();
	public static int currentCookIndex = 0;
	public static Cook cook;
	public static CustomerController cc;
	InputMultiplexer multi;
	StationManager stationManager = new StationManager();
	public String difficulty;
	String saveFile;


	PowerupController powerupController = new PowerupController(this);

	/**
	 * Constructor to initialise game screen;
	 * 
	 * @param game - Main entry point class
	 * @param ms   - Title screen class
	 */
	public GameScreen(MainGameClass game, MainScreen ms, String difficulty, int num_waves) {
		this.game = game;
		this.ms = ms;
		this.difficulty = difficulty;
		this.calculateBoxMaths();
		currentWave = 0;
		reputationPoints = 3;
		cooks = new Cook[2];
		cooks[0] = new Cook(new Vector2(64 * 5, 64 * 3), 1);
		cooks[1] = new Cook(new Vector2(64 * 5, 64 * 5), 2);
		cook = cooks[currentCookIndex];
		control = new Control();
		// map = new TmxMapLoader().load("map/art_map/prototype_map.tmx");
		map1 = new TmxMapLoader().load("map/art_map/customertest.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(map1);
		constructCollisionData(map1);
		cc = new CustomerController(map1, difficulty);
		cc.spawnCustomer();
		stationManager = new StationManager();
		NUMBER_OF_WAVES = num_waves;
		ENDLESS = false;
		thirdChef = false;
		secondBaking = false;
		secondCutting = false;
		saveFile = "save";
		startTime = System.currentTimeMillis();
	}

	public GameScreen(MainGameClass game, MainScreen ms, String difficulty) {
		this.game = game;
		this.ms = ms;
		this.difficulty = difficulty;
		this.calculateBoxMaths();
		currentWave = 0;
		reputationPoints = 3;
		cooks = new Cook[2];
		cooks[0] = new Cook(new Vector2(64 * 5, 64 * 3), 1);
		cooks[1] = new Cook(new Vector2(64 * 5, 64 * 5), 2);
		cook = cooks[currentCookIndex];
		control = new Control();
		// map = new TmxMapLoader().load("map/art_map/prototype_map.tmx");
		map1 = new TmxMapLoader().load("map/art_map/customertest.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(map1);
		constructCollisionData(map1);
		cc = new CustomerController(map1, difficulty);
		cc.spawnCustomer();
		NUMBER_OF_WAVES = -1;
		ENDLESS = true;
		thirdChef = false;
		secondBaking = false;
		secondCutting = false;
		saveFile = "save";
		startTime = System.currentTimeMillis();
	}

	// load saved game
	public GameScreen(MainGameClass game, MainScreen ms, Boolean testSave) {
		if(testSave) saveFile = "testSave";
		else saveFile = "save";
		Preferences prefs = Gdx.app.getPreferences(saveFile);
		this.game = game;
		this.ms = ms;
		this.difficulty = prefs.getString("difficulty", "normal");
		this.calculateBoxMaths();
		control = new Control();
		// map = new TmxMapLoader().load("map/art_map/prototype_map.tmx");
		map1 = new TmxMapLoader().load("map/art_map/customertest.tmx");
		if (!testSave) tiledMapRenderer = new OrthogonalTiledMapRenderer(map1);
		constructCollisionData(map1);
		cc = new CustomerController(map1, difficulty);
		cc.spawnCustomer();
		NUMBER_OF_WAVES = prefs.getInteger("NUMBER_OF_WAVES", -1);
		ENDLESS = prefs.getBoolean("ENDLESS", true);
		reputationPoints = prefs.getInteger("reputationPoints", 3);
		cc.totalServed = prefs.getInteger("totalServed", 0);
		currentWave = prefs.getInteger("currentWave", 0);
		thirdChef = prefs.getBoolean("thirdChef", false);
		cooks = new Cook[2];
		cooks[0] = new Cook(new Vector2(64 * 5, 64 * 3), 1);
		cooks[1] = new Cook(new Vector2(64 * 5, 64 * 5), 2);
		if(thirdChef && cooks.length < 3) {
			Cook[] cooks2 = new Cook[cooks.length + 1];
			System.arraycopy(cooks, 0, cooks2, 0, cooks.length);
			cooks2[cooks.length] = new Cook(new Vector2(64 * 5, 64 * 5), 3);
			cooks = cooks2;
		}
		cook = cooks[currentCookIndex];
		secondBaking = prefs.getBoolean("secondBaking", false);
		secondCutting = prefs.getBoolean("secondCutting", false);
		Tutorial.complete = prefs.getBoolean("tutorialComplete", false);
		startTime = prefs.getLong("lastTimeOnStartup") - prefs.getLong("lastStartTime") + System.currentTimeMillis();
	}

	/***
	 * Used for updating button positions when one of the button is clicked, saves code duplication
	 */
	private void setShopButtonPositions(){
		shop.setPosition(gameResolutionX / 40.0f, (18 * gameResolutionY / 20.0f) - 60);
		//buyChef2.setPosition(gameResolutionX / 10.f, 20 * gameResolutionY / 10.0f - 10);
		buyChef3.setPosition(shopX + buttonwidth + (gameResolutionX / 25f), (18 * gameResolutionY / 20.0f) - 60);
		buyBakingStation.setPosition(shopX + (buttonwidth + (gameResolutionX / 25f))*2, (18 * gameResolutionY / 20.0f) - 60);
		buyCuttingStation.setPosition(shopX + (buttonwidth + (gameResolutionX / 25f))*3, (18 * gameResolutionY / 20.0f) - 60);
	}

	/**
	 * Things that should be done while the game screen is shown
	 */
	public void show() {
		// =======================================LOAD=STATIONS==========================================================
		loadStations();
		stationManager.stations.get(new Vector2(7,5)).active = secondBaking;		// deactivate second baking station
		stationManager.stations.get(new Vector2(11, 8)).active = secondCutting;	// deactivate second cutting station

		// =======================================START=FRAME=TIMER======================================================
		//startTime = System.currentTimeMillis();
		timeOnStartup = startTime;
		tempThenTime = startTime;
		// =======================================SET=POSITIONS=OF=SLIDERS===============================================
		float currentMusicVolumeSliderX = (MainGameClass.musicVolumeScale * sliderWidth) + xSliderMin;
		float currentGameVolumeSliderX = (MainGameClass.gameVolumeScale * sliderWidth) + xSliderMin;
		musSlide.setPosition(currentMusicVolumeSliderX, audioBackgroundy + 4 * audioBackgroundHeight / 6
				+ musSlideBackgr.getHeight() / 2 - musSlide.getHeight() / 2);
		volSlide.setPosition(currentGameVolumeSliderX, audioBackgroundy + audioBackgroundHeight / 6
				+ volSlideBackgr.getHeight() / 2 - volSlide.getHeight() / 2);
		// ======================================INHERIT=TEXTURES=FROM=MAIN=SCREEN=======================================
		vButton = ms.vButton;
		vControl = ms.vControl;
		// ======================================START=CAMERAS===========================================================
		uiCamera = new OrthographicCamera();
		worldCamera = new OrthographicCamera();
		uiCamera.setToOrtho(false, gameResolutionX, gameResolutionY);
		worldCamera.setToOrtho(false, gameResolutionX, gameResolutionY);
		// ======================================SET=INITAL=STATE========================================================
		if (Tutorial.complete) state1 = STATE.Continue;
		else state1 = STATE.Tutorial;
		// ======================================START=VIEWPORTS=========================================================
		worldViewport = new FitViewport(gameResolutionX, gameResolutionY, worldCamera);
		uiViewport = new FitViewport(gameResolutionX, gameResolutionY, uiCamera);
		// ======================================START=STAGES============================================================
		stage = new Stage(uiViewport);
		stage2 = new Stage(uiViewport);
		stage3 = new Stage(uiViewport);
		// ======================================CREATE=INPUTMULTIPLEXER=================================================
		multi = new InputMultiplexer(stage, control);
		// ======================================LOAD=TEXTURES===========================================================
		MENU = new Texture(Gdx.files.internal("uielements/settings.png"));
		ESC = new Texture(Gdx.files.internal("uielements/background.png"));
		BACKTOMAINSCREEN = new Texture(Gdx.files.internal("uielements/exitmenu.png"));
		RESUME = new Texture(Gdx.files.internal("uielements/resume.png"));
		AUDIO = new Texture(Gdx.files.internal("uielements/audio.png"));
		audioEdit = new Texture(Gdx.files.internal("uielements/background.png"));
		SHOP = new Texture(Gdx.files.internal("uielements/shop.png"));
		//BUYCHEF2 = new Texture(Gdx.files.internal("uielements/buychef2.png"));
		BUYCHEF3 = new Texture(Gdx.files.internal("uielements/buychef3.png"));
		BUYBAKINGSTATION = new Texture(Gdx.files.internal("uielements/buyBakingStation.png"));
		BUYCUTTINGSTATION = new Texture(Gdx.files.internal("uielements/buyCuttingStation.png"));
		REDX = new Texture(Gdx.files.internal("uielements/redX.png"));
		// ======================================CREATE=BUTTONS==========================================================
		mn = new Button(new TextureRegionDrawable(MENU));
		ad = new Button(new TextureRegionDrawable(AUDIO));
		rs = new Button(new TextureRegionDrawable(RESUME));
		rs2 = new Button(new TextureRegionDrawable(RESUME));
		btms = new Button(new TextureRegionDrawable(BACKTOMAINSCREEN));
		shop = new Button(new TextureRegionDrawable(SHOP));
		//buyChef2 = new Button(new TextureRegionDrawable(BUYCHEF2));
		buyChef3 = new Button(new TextureRegionDrawable(BUYCHEF3));
		buyBakingStation = new Button(new TextureRegionDrawable(BUYBAKINGSTATION));
		buyCuttingStation = new Button(new TextureRegionDrawable(BUYCUTTINGSTATION));
		// ======================================POSITION=AND=SCALE=BUTTONS==============================================
		mn.setPosition(gameResolutionX / 40.0f, 18 * gameResolutionY / 20.0f);
		mn.setSize(buttonwidth, buttonheight);
		rs.setPosition(gameResolutionX / 40.0f, 18 * gameResolutionY / 20.0f);
		rs.setSize(buttonwidth, buttonheight);
		rs2.setPosition(gameResolutionX / 40.0f, (18 * gameResolutionY / 20.0f) - 60);
		rs2.setSize(buttonwidth, buttonheight);
		ad.setPosition(rs.getX() + rs.getWidth() + 2 * (gameResolutionX / 40.0f - gameResolutionX / 50.0f), rs.getY());
		ad.setSize(buttonwidth, buttonheight);
		btms.setPosition(ad.getX() + ad.getWidth() + 2 * (gameResolutionX / 40.0f - gameResolutionX / 50.0f),
				ad.getY());
		btms.setSize(buttonwidth, buttonheight);
		shop.setSize(buttonwidth, buttonheight);
		//buyChef2.setSize(buttonwidth, buttonheight);
		buyChef3.setSize(buttonwidth, buttonheight);
		buyBakingStation.setSize(buttonwidth, buttonheight);
		buyCuttingStation.setSize(buttonwidth, buttonheight);
		setShopButtonPositions();
		// ======================================ADD=LISTENERS=TO=BUTTONS================================================
		mn.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state1 = STATE.Pause;
				super.touchUp(event, x, y, pointer, button);
			}
		});
		rs.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state1 = STATE.Continue;
				super.touchUp(event, x, y, pointer, button);
			}
		});
		rs2.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state1 = STATE.Continue;
				super.touchUp(event, x, y, pointer, button);
			}
		});
		ad.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state1 = STATE.audio;
				super.touchUp(event, x, y, pointer, button);
			}
		});
		btms.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state1 = STATE.main;
				super.touchUp(event, x, y, pointer, button);
			}
		});
		shop.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state1 = STATE.shop;
				super.touchUp(event, x, y, pointer, button);
			}
		});
		buyChef3.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(reputationPoints > 5) {
					Cook[] cooks2 = new Cook[cooks.length + 1];
					System.arraycopy(cooks, 0, cooks2, 0, cooks.length);
					cooks2[cooks.length] = new Cook(new Vector2(64 * 5, 64 * 5), 3);
					cooks = cooks2;
					buyChef3.remove();
					reputationPoints -= 5;
					thirdChef = true;
				}
				super.touchUp(event, x, y, pointer, button);
			}
		});
		buyBakingStation.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(reputationPoints > 7) {
					stationManager.stations.get(new Vector2(7,5)).active = true;
					reputationPoints -= 7;
					buyBakingStation.remove();
					secondBaking = true;
				}
				super.touchUp(event, x, y, pointer, button);
			}
		});
		buyCuttingStation.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(reputationPoints > 9) {		// deactivate second baking station
					stationManager.stations.get(new Vector2(11, 8)).active = true;
					reputationPoints -= 9;
					buyCuttingStation.remove();
					secondCutting = true;
				}
				super.touchUp(event, x, y, pointer, button);
			}
		});
		// ======================================ADD=BUTTONS=TO=STAGES===================================================
		stage.addActor(mn);
		stage.addActor(shop);
		stage2.addActor(rs);
		stage2.addActor(btms);
		stage2.addActor(ad);
		if(!thirdChef) stage3.addActor(buyChef3);
		stage3.addActor(rs2);
		if(!secondBaking) stage3.addActor(buyBakingStation);
		if(!secondCutting) stage3.addActor(buyCuttingStation);
	}

	static ShapeRenderer _selectedPlayerBox = null;

	private static ShapeRenderer getSelectedPlayerBox() {
		if (_selectedPlayerBox == null) {
			_selectedPlayerBox = new ShapeRenderer();
		}
		return _selectedPlayerBox;
	}


	/**
	 * Render method for main game
	 * 
	 * @param delta - some change in time
	 */

	public void render(float delta) {
		// =====================================CLEAR=SCREEN=============================================================
		ScreenUtils.clear(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// =====================================SET=INPUT=PROCESSOR======================================================
		Gdx.input.setInputProcessor(multi);
		// =====================================SET=PROJECTION=MATRICES=FOR=GAME=RENDERING===============================
		game.shapeRenderer.setProjectionMatrix(worldCamera.combined);
		game.batch.setProjectionMatrix(worldCamera.combined);
		// =====================================RENDER=BOTTOM=MAP=LAYER==================================================
		tiledMapRenderer.setView(worldCamera);
		tiledMapRenderer.render(new int[] { 0 });
		// =====================================DRAW=COOK=LEGS===========================================================
		game.batch.begin();
		for (Cook curCook : cooks)
			curCook.draw_bot(game.batch);
		game.batch.end();
		// =====================================RENDER=TOP=MAP=LAYER=====================================================
		tiledMapRenderer.render(new int[] { 1 });
		// =====================================DRAW=RED=X=ON=INACTIVE=STATIONS==========================================
		game.batch.begin();
		for(Station station: stationManager.stations.values()){
			if(!station.active){
				game.batch.draw(REDX, station.pos.x * 64, station.pos.y * 64);
			}
		}
		game.batch.end();
		// =====================================DRAW=COOK=TOP=HALF=======================================================
		stationManager.handleStations(game.batch);
		drawHeldItems();
		game.batch.begin();
		for (Cook curCook : cooks)
			curCook.draw_top(game.batch);
		cc.drawCustTop(game.batch); // todo fix customer z ordering
		game.batch.end();
		// =====================================UPDATE=POWERUPS==========================================================
		powerupController.updatePowerups(game.batch, cook);
		// ==================================MOVE=COOK===================================================================
		tempTime = System.currentTimeMillis();
		if (!cook.locked && Tutorial.complete)
			cook.update(control, (tempTime - tempThenTime), CLTiles);
		tempThenTime = tempTime;
		checkInteraction(cook, game.shapeRenderer);
		// =====================================SET=MATRIX=FOR=UI=ELEMENTS===============================================
		Matrix4 uiMatrix = worldCamera.combined.cpy();
		uiMatrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.setProjectionMatrix(uiMatrix);
		// =====================================DRAW=UI=ELEMENTS=========================================================
		drawUI();

		// =====================================SET=MATRIX=BACK=TO=GAME=MATRIX===========================================

		setCameraLerp(delta);

		game.batch.setProjectionMatrix(worldCamera.combined);
		// ==================================MOVE=CAMERA=================================================================

		worldCamera.update();
		uiCamera.update();
		// ==================================PLAY=MUSIC==================================================================
		game.gameMusic.play();
		// ==================================DRAW=INTERACTIVE=UI=ELEMENTS================================================
		stage.act();
		stage.draw();
		// ==================================JUMP=TO=STATE=SPECIFIC=LOGIC================================================
		game.batch.setProjectionMatrix(uiMatrix);
		changeScreen(state1);
		game.batch.setProjectionMatrix(worldCamera.combined);

		checkCookSwitch();
		checkCustomerWaitTime();
		// =========================================CHECK=GAME=OVER======================================================
		checkGameOver();





		// ====================================Nuke=Stuff===============================================================
		if(Gdx.input.isKeyJustPressed(Input.Keys.M)){
			nuker.nukeEm();
		}

















	}

    /**
     * Change selected cook
     */
	public static void checkCookSwitch() {
		if (control.tab && Tutorial.complete) {
			cook.locked = false;
			currentCookIndex += currentCookIndex < cooks.length - 1 ? 1 : -currentCookIndex;
			cook = cooks[currentCookIndex];
		}
		if (control.shift && Tutorial.complete) {
			cook.locked = false;
			currentCookIndex -= currentCookIndex > 0 ? 1 : -cooks.length + 1;
			cook = cooks[currentCookIndex];
		}

		control.interact = false;
		control.drop = false;
		control.flip = false;
		control.tab = false;
		control.shift = false;
	}

	private void checkCustomerWaitTime() { //Removes a customer if wait time is exceeded
		for(Customer customer: cc.customers) {
			if (customer != null && customer.waitTime() > MAX_WAIT_TIME && customer.locked) {
				cc.delCustomer(customer);

			/*  dont spawn new ones on timeout
			if (ENDLESS || currentWave < NUMBER_OF_WAVES - 1) {
				cc.spawnCustomer();
			}
			*/

				currentWave++;
				reputationPoints--;
				currentWaitingCustomer = null;
			}
		}
	}

	public static final float MAX_WAIT_TIME = 60000; //Customer wait time in ms

    /**
     * Draw UI elements
     */
	private void drawUI() {
		ShapeRenderer selectedPlayerBox = getSelectedPlayerBox();
		if (currentWaitingCustomer != null && currentWaitingCustomer.waitTime() < MAX_WAIT_TIME) {
			Menu.RECIPES.get(currentWaitingCustomer.order).displayRecipe(game.batch, new Vector2(64, 256));
		}
		for (int i = 0; i < cooks.length; i++) {
			if (i == currentCookIndex) {
				selectedPlayerBox.setAutoShapeType(true);
				selectedPlayerBox.begin(ShapeType.Line);

				selectedPlayerBox.setColor(Color.GREEN);
				selectedPlayerBox.rect(Gdx.graphics.getWidth() - 128 * cooks.length + i * 128,
						Gdx.graphics.getHeight() - 128 - 8, 128, 128);
				selectedPlayerBox.end();
			}
			game.batch.begin();
			cooks[i].draw_top(game.batch, new Vector2(Gdx.graphics.getWidth() - 128 * cooks.length + i * 128,
					Gdx.graphics.getHeight() - 256));
			game.batch.end();
		}

		game.batch.begin();
		game.font.draw(game.batch, Long.toString((startTime - timeOnStartup) / 1000),
				gameResolutionX / 2f + gameResolutionX / 10f, 19 * gameResolutionY / 20f);
		game.font.draw(game.batch, "Time in s:", gameResolutionX / 2f, 19 * gameResolutionY / 20f);
		game.font.draw(game.batch, Integer.toString(reputationPoints),
				gameResolutionX / 2f + gameResolutionX / 9f, 18 * gameResolutionY / 20f);
		game.font.draw(game.batch, "Reputation:", gameResolutionX / 2f, 18 * gameResolutionY / 20f);
		game.batch.end();
	}

    /**
     * Change camera movement type depending on position to cook
     * @param delta - some change in time
     */
	private void setCameraLerp(float delta) {
		if (!Tutorial.complete) {
			worldCamera.position.lerp(new Vector3(Tutorial.getStagePos(), 0), .065f);
			if (control.tab) {
				Tutorial.nextStage();
			} else if (control.shift) {
				Tutorial.previousStage();
			}
			Tutorial.drawBox(game.batch, delta * 20);
		} else {
			if(state1 == STATE.Tutorial) state1 = STATE.Continue;
			if (Math.abs(worldCamera.position.x - cook.pos.x) < 2
					&& Math.abs(worldCamera.position.y - cook.pos.y) < 2) {
				worldCamera.position.x = cook.pos.x;
				worldCamera.position.y = cook.pos.y;
			} else {
				worldCamera.position.lerp(new Vector3(cook.pos.x, cook.pos.y, 0), .065f);
			}
		}
	}

	/**
	 * Draws the held items for all cooks on the screen
	 */
	private void drawHeldItems() {
		for (Cook ck : cooks) {
			int itemIndex = 0;
			for (Entity ingredient : ck.heldItems) {
				ingredient.pos = new Vector2(ck.pos.x + 16, ck.pos.y + 112 + itemIndex * 8);
				ingredient.draw(game.batch);
				itemIndex++;
			}
		}
	}

	long nowTime = 0;
	long thenTime = 0;

	/**
	 * Changes game window state
	 * 
	 * @param state1 - the state to change to
	 */
	public void changeScreen(STATE state1) {
		if (state1 == STATE.main) {
			// SAVE THE GAME
			Preferences prefs = Gdx.app.getPreferences(saveFile);
			prefs.putInteger("reputationPoints", reputationPoints);
			prefs.putBoolean("ENDLESS", ENDLESS);
			prefs.putInteger("totalServed", cc.totalServed);
			prefs.putInteger("currentWave", currentWave);
			prefs.putInteger("NUMBER_OF_WAVES", NUMBER_OF_WAVES);
			prefs.putString("difficulty", difficulty);
			prefs.putBoolean("thirdChef", thirdChef);
			prefs.putBoolean("secondBaking", secondBaking);
			prefs.putBoolean("secondCutting", secondCutting);
			prefs.putBoolean("tutorialComplete", Tutorial.complete);
			prefs.putLong("lastTimeOnStartup", timeOnStartup);
			prefs.putLong("lastStartTime", startTime);
			prefs.flush();

			game.gameMusic.dispose();
			//game.resetGameScreen();
			game.setScreen(game.getMainScreen());

		}
		if (state1 == STATE.Pause) {
			thenTime = System.currentTimeMillis() - timeOnStartup;
			Gdx.input.setInputProcessor(stage2);
			game.batch.begin();
			game.batch.draw(ESC, optionsBackground.getX(), optionsBackground.getY(), optionsBackground.getWidth(),
					optionsBackground.getHeight());
			game.batch.end();
			stage2.act();
			stage2.draw();
		}
		if (state1 == STATE.audio) {
			musicVolumeUpdate();
			gameVolumeUpdate();
			checkState();

			Gdx.input.setInputProcessor(stage2);
			game.batch.begin();
			game.batch.draw(ESC, optionsBackground.getX(), optionsBackground.getY(), optionsBackground.getWidth(),
					optionsBackground.getHeight());
			game.batch.end();
			stage2.act();
			stage2.draw();
			game.batch.begin();
			game.batch.draw(audioEdit, audioBackground.getX(), audioBackground.getY(), audioBackground.getWidth(),
					audioBackground.getHeight());
			game.batch.draw(vControl, volSlideBackgr.getX(), volSlideBackgr.getY(), volSlideBackgr.getWidth(),
					volSlideBackgr.getHeight());
			game.batch.draw(vButton, volSlide.getX() - volSlide.getWidth() / 2, volSlide.getY(), volSlide.width,
					volSlide.height);
			game.batch.draw(vControl, musSlideBackgr.getX(), musSlideBackgr.getY(), musSlideBackgr.getWidth(),
					musSlideBackgr.getHeight());
			game.batch.draw(vButton, musSlide.getX() - musSlide.getWidth() / 2, musSlide.getY(), musSlide.width,
					musSlide.height);
			game.batch.end();
		}
		if (state1 == STATE.Continue) {
			nowTime = System.currentTimeMillis() - timeOnStartup;
			startTime += nowTime - thenTime;
			cc.updateCustomers();
			thenTime = System.currentTimeMillis() - timeOnStartup;
		}
		if(state1 == STATE.shop){
			thenTime = System.currentTimeMillis() - timeOnStartup;
			Gdx.input.setInputProcessor(stage3);
			stage3.act();
			stage3.draw();
		}
		if(state1 == STATE.Tutorial){
			thenTime = System.currentTimeMillis() - timeOnStartup;
		}
	}

	/**
	 * Checks to see whether escape has been pressed to pause the game
	 */
	public void checkState() {
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			state1 = STATE.Pause;
		}
	}

	/**
	 * Updates the music volume slider
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
	 * Updates the game volume slider
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
				// game.sound.setVolume(game.soundid, s);
				MainGameClass.gameVolumeScale = s;
			}
		}
	}

	/**
	 * Calculates coordinates for UI element scaling;
	 */
	private void calculateBoxMaths() {
		this.gameResolutionX = ms.gameResolutionX;
		this.gameResolutionY = ms.gameResolutionY;
		this.buttonwidth = gameResolutionX / 10.0f;
		this.buttonheight = gameResolutionY / 20.0f;

		this.audioBackgroundWidth = gameResolutionX / 6.0f;
		this.audioBackgroundHeight = gameResolutionY / 6.0f;

		this.audioBackgroundx = gameResolutionX / 40.0f + buttonwidth
				+ 2 * (gameResolutionX / 40.0f - gameResolutionX / 50.0f);
		this.audioBackgroundy = 7 * gameResolutionY / 10.0f;

		this.optionsBackground = new Rectangle();
		optionsBackground.setPosition(gameResolutionX / 50.0f, 35 * gameResolutionY / 40.0f);
		optionsBackground.width = 3 * (buttonwidth + 2 * (gameResolutionX / 40.0f - gameResolutionX / 50.0f));
		optionsBackground.height = 4 * gameResolutionY / 40.0f;

		this.audioBackground = new Rectangle();
		audioBackground.setPosition(audioBackgroundx, audioBackgroundy);
		audioBackground.width = audioBackgroundWidth;
		audioBackground.height = audioBackgroundHeight;

		this.volSlide = new Rectangle();
		volSlide.width = audioBackgroundHeight / 4.0f;
		volSlide.height = audioBackgroundHeight / 4.0f;

		this.volSlideBackgr = new Rectangle();
		volSlideBackgr.width = 2 * audioBackgroundWidth / 3.0f;
		volSlideBackgr.height = audioBackgroundHeight / 6.0f;
		volSlideBackgr.setPosition(audioBackgroundx + audioBackgroundWidth / 6,
				audioBackgroundy + audioBackgroundHeight / 6);

		this.musSlide = new Rectangle();
		musSlide.width = audioBackgroundHeight / 4.0f;
		musSlide.height = audioBackgroundHeight / 4.0f;

		this.musSlideBackgr = new Rectangle();
		musSlideBackgr.width = 2 * audioBackgroundWidth / 3.0f;
		musSlideBackgr.height = audioBackgroundHeight / 6.0f;
		musSlideBackgr.setPosition(audioBackgroundx + audioBackgroundWidth / 6,
				audioBackgroundy + 4 * audioBackgroundHeight / 6);

		this.xSliderMin = audioBackgroundx + audioBackgroundWidth / 6;
		this.xSliderMax = xSliderMin + volSlideBackgr.width;
		this.sliderWidth = volSlideBackgr.width;
	}

	/**
	 * Construct an array of CollisionTile objects for collision detection
	 * 
	 * @param mp- game tilemap
	 */
	private void constructCollisionData(TiledMap mp) {
		TiledMapTileLayer botlayer = (TiledMapTileLayer) mp.getLayers().get(0);
		int mapwidth = botlayer.getWidth();
		int mapheight = botlayer.getHeight();
		CLTiles = new CollisionTile[mapwidth][mapheight];
		TiledMapTileLayer toplayer = (TiledMapTileLayer) mp.getLayers().get(1);
		int topwidth = toplayer.getWidth();
		int topheight = toplayer.getHeight();
		for (int y = 0; y < topheight; y++) {
			for (int x = 0; x < topwidth; x++) {
				TiledMapTileLayer.Cell tl2 = toplayer.getCell(x, y);
				if (tl2 != null) {
					CLTiles[x][y] = new CollisionTile(x * 64, y * 64, 64, 64);
				}
			}
		}
		for (int y = 0; y < mapheight; y++) {
			for (int x = 0; x < mapwidth; x++) {
				TiledMapTileLayer.Cell tl = botlayer.getCell(x, y);
				if (tl != null) {
					TiledMapTile tlt = tl.getTile();
					MapProperties mpr = tlt.getProperties();
                    //Checks if tile have the "name" custom property
                    //In this implementation only the floor tiles have "name" property
					if (mpr.get("name") == null) {
						CLTiles[x][y] = new CollisionTile(x * 64, y * 64, 64, 64);
					} else {
						if (y != 0) {
							if (CLTiles[x][y - 1] != null) {
								CLTiles[x][y - 1] = null;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Check the tile the cook is looking at for interaction
	 * 
	 * @param ck - Selected cook
	 * @param sr - ShapeRenderer to draw the coloured box
	 */
	public void checkInteraction(Cook ck, ShapeRenderer sr) {
		float centralCookX = ck.getX() + ck.getWidth() / 2;
		float centralCookY = ck.getY();
		int cellx = (int) Math.floor(centralCookX / 64);
		int celly = (int) Math.floor(centralCookY / 64);
		int checkCellX = cellx;
		int checkCellY = celly;
		checkCellX += ck.getDirection().x;
		checkCellY += ck.getDirection().y + 1;
		Cell viewedTile = ((TiledMapTileLayer) map1.getLayers().get(1)).getCell(checkCellX, checkCellY);

		if (viewedTile != null) {
			Object stationType = viewedTile.getTile().getProperties().get("Station");
			if (stationType != null) {
				stationManager.checkInteractedTile((String) viewedTile.getTile().getProperties().get("Station"),
						new Vector2(checkCellX, checkCellY));
			} else {
				stationManager.checkInteractedTile("", new Vector2(checkCellX, checkCellY));
			}
		}
		sr.begin(ShapeRenderer.ShapeType.Line);
		sr.setColor(new Color(1, 0, 1, 1));
		sr.rect(checkCellX * 64, checkCellY * 64, 64, 64);
		sr.end();
	}

	public void checkGameOver() {
		if (currentWave == NUMBER_OF_WAVES || reputationPoints == 0) {
			if (ENDLESS) {
				game.getLeaderBoardScreen().addLeaderBoardData("PLAYER1", cc.totalServed);
			}
			//game.resetGameScreen();
			this.resetStatic();
			game.setScreen(game.getLeaderBoardScreen());
		}
	}

	public void resetStatic() {
		currentWave = 0;
		reputationPoints = 3;
	}
	public int getReputationPoints() {
		return reputationPoints;
	}
	public StationManager getStationManager(){
		return stationManager;
	}
	public Long getTimePlayed(){
		return startTime - timeOnStartup;
	}
	public void incStartTime(Long l){
		startTime += l;
	}

	/**
	 * Load all of the stations at once instead of when viewed
	 */
	public void loadStations(){
		stationManager.handleStations(game.batch);
		TiledMapTileLayer layer = (TiledMapTileLayer) map1.getLayers().get(0);
		for(int x = 0; x < layer.getWidth(); x++) {
			for(int y = 0; y < layer.getHeight(); y++) {
				TiledMapTileLayer.Cell viewedTile = ((TiledMapTileLayer) map1.getLayers().get(1)).getCell(x, y);

				if (viewedTile != null) {
					Object stationType = viewedTile.getTile().getProperties().get("Station");
					if (stationType != null) {
						getStationManager().checkInteractedTile((String) viewedTile.getTile().getProperties().get("Station"),
								new Vector2(x, y));
					} else {
						getStationManager().checkInteractedTile("", new Vector2(x, y));
					}
				}
			}
		}
	}

	/**
	 * Resize game screen - Not used in fullscreen mode
	 * 
	 * @param width  - width to resize to
	 * @param height - height to resize to
	 */
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		worldViewport.update(width, height);
		uiViewport.update(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
}
