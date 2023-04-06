package com.team3gdx.game.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
//import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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
import com.team3gdx.game.food.Ingredient;
import com.team3gdx.game.food.Ingredients;
import com.team3gdx.game.station.IngredientStation;
import com.team3gdx.game.station.ServingStation;
import com.team3gdx.game.station.StationManager;
import com.team3gdx.game.util.CollisionTile;
import com.team3gdx.game.util.Control;



public class miniWorldTest implements Screen {





    final MainGameClass game;
    public static int NUMBER_OF_WAVES;

    public static boolean ENDLESS;

    public Texture background;

    public static int currentWave = 0;
    public static int reputationPoints = 3;

    Texture ESC;
    Texture MENU;

    Button mn;

    public static CollisionTile[][] CLTiles;
    Viewport uiViewport;
    Viewport worldViewport;
    Stage stage;
    Stage stage2;
    boolean flagTop;
    boolean flagBot;
    OrthographicCamera uiCamera;
    public static OrthographicCamera worldCamera;

    public static Customer currentWaitingCustomer = null;



    public enum STATE {
        Pause, Continue, main, audio
    }

    public static GameScreen.STATE state1;
    float v;
    float s;
    int gameResolutionX;
    int gameResolutionY;
    float buttonwidth;
    float buttonheight;


    long startTime;
    long timeOnStartup;
    long tempTime, tempThenTime;
    public static Control control;
    TiledMapRenderer tiledMapRenderer;
    public TiledMap map1;
    public static Cook[] cooks = { new Cook(new Vector2(150 * 5, 120 * 5), 1), new Cook(new Vector2(160 * 5, 125 * 5), 2), new Cook(new Vector2(170 * 5, 130 * 5), 3) };

    public static int currentCookIndex = 0;
    public static Cook cook = cooks[currentCookIndex];
    public static CustomerController cc;
    //InputMultiplexer multi;
    StationManager stationManager = new StationManager();

    Customer testCustomer;
    Customer testCustomer2;

    Ingredient testIng = new Ingredient(Ingredients.bun);
    IngredientStation testIS;

    boolean showIngs;

    public miniWorldTest(MainGameClass game) {
        this.game = game;


        map1 = new TmxMapLoader().load("map/art_map/customertest.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map1);
        cc = new CustomerController(map1, "easy");
        cc.spawnCustomer();
        testCustomer = cc.customers[0];
        testCustomer.posy = 640;
        testCustomer.posx = 1000;
        cc.spawnCustomer();
        testCustomer2 = cc.customers[1];
        testCustomer2.posy = 640;
        testCustomer2.posx = 1100;
        showIngs = false;


        Vector2 testPosA = new Vector2(12,13);

        testIS = new IngredientStation(testPosA, testIng);

        StationManager.stations.put(testPosA, testIS);



    }



    public void show() {
        // =======================================START=FRAME=TIMER======================================================
        startTime = System.currentTimeMillis();
        timeOnStartup = startTime;
        tempThenTime = startTime;


        uiCamera = new OrthographicCamera();
        worldCamera = new OrthographicCamera();
        uiCamera.setToOrtho(false, gameResolutionX, gameResolutionY);
        worldCamera.setToOrtho(false, gameResolutionX, gameResolutionY);


        worldViewport = new FitViewport(gameResolutionX, gameResolutionY, worldCamera);
        uiViewport = new FitViewport(gameResolutionX, gameResolutionY, uiCamera);
        // ======================================START=STAGES============================================================
        stage = new Stage(uiViewport);
        stage2 = new Stage(uiViewport);
        // ======================================CREATE=INPUTMULTIPLEXER=================================================
        //multi = new InputMultiplexer(stage, control);
        // ======================================LOAD=TEXTURES===========================================================
        MENU = new Texture(Gdx.files.internal("uielements/settings.png"));
        ESC = new Texture(Gdx.files.internal("uielements/background.png"));
        mn = new Button(new TextureRegionDrawable(MENU));


        mn.setPosition(gameResolutionX / 40.0f, 18 * gameResolutionY / 20.0f);
        mn.setSize(buttonwidth, buttonheight);



        mn.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state1 = GameScreen.STATE.Pause;
                super.touchUp(event, x, y, pointer, button);
            }
        });
//
//
//
//
//           stage.addActor(mn);
//        stage2.addActor(rs);
//        stage2.addActor(btms);
//        stage2.addActor(ad);

    }

    @Override
    public void render(float delta) {

        // TODO Auto-generated method stub
        ScreenUtils.clear(250, 0, 0, 150);
//        game.batch.setProjectionMatrix(camera.combined);

        //background = new Texture(Gdx.files.internal("testingimage.png"));


        // =====================================CLEAR=SCREEN=============================================================
        ScreenUtils.clear(150, 110, 0, 150);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.shapeRenderer.setProjectionMatrix(worldCamera.combined);
        game.batch.setProjectionMatrix(worldCamera.combined);


        Matrix4 uiMatrix = worldCamera.combined.cpy();
        uiMatrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.setProjectionMatrix(uiMatrix);

        worldCamera.update();
        uiCamera.update();

        // ==================================DRAW=INTERACTIVE=UI=ELEMENTS================================================
        stage.act();
        stage.draw();


        // =====================================DRAW=COOK=TOP=HALF=======================================================
        stationManager.handleStations(game.batch);
        drawHeldItems();

        cookDrawTesting();
        custDrawTesting();
        testCookHolding();
        //stationTesting();
        //        cc.drawCustTop(game.batch); // todo fix customer z ordering




    }

    public void custDrawTesting(){
        game.batch.begin();
        cc.drawCustTop(game.batch);
        game.batch.end();
        if(Gdx.input.isKeyJustPressed(Input.Keys.F)){
            testCustomer2.setFront();
            testCustomer.setFront();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
            testCustomer2.setBack();
            testCustomer.setBack();
        }
    }

    public void cookDrawTesting(){


        if (Gdx.input.isKeyJustPressed(Input.Keys.T)){
            flagTop = !flagTop;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.B)){
            flagBot = !flagBot;
        }

        if(flagTop) {
            game.batch.begin();
            for (Cook curCook : cooks)
                curCook.draw_top(game.batch);
            game.batch.end();
        }
        if(flagBot) {
            game.batch.begin();
            for (Cook curCook : cooks)
                curCook.draw_bot(game.batch);
            game.batch.end();
        }
    }


    public void testCookHolding(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.U)){
            cooks[1].pickUpItem(testIng);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
            cooks[1].dropItem();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
            showIngs = !showIngs;
        }


    }

    private void drawHeldItems() {
        if(showIngs) {
            for (Cook ck : cooks) {
                int itemIndex = 0;
                for (Entity ingredient : ck.heldItems) {
                    ingredient.pos = new Vector2(ck.pos.x + 16, ck.pos.y + 112 + itemIndex * 8);
                    ingredient.draw(game.batch);
                    itemIndex++;
                }
            }
        }
    }




    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
