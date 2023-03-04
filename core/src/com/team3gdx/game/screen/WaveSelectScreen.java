package com.team3gdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team3gdx.game.MainGameClass;
import org.w3c.dom.Text;

public class WaveSelectScreen implements Screen {

    final MainGameClass game;
    final MainScreen ms;

    private int waves;

    float buttonwidth;
    float buttonheight;

    Button incrementWaves;
    Button decrementWaves;
    Button returnToMain;
    Button go;

    Texture incrementTexture;
    Texture decrementTexture;
    Texture returnToMainTexture;
    Texture goTexture;

    enum STATE {
        none, main, new_game
    }

    STATE state;
    Stage stage;

    OrthographicCamera camera;
    Viewport viewport;

    Texture background;

    BitmapFont font;

    public WaveSelectScreen(MainGameClass game, MainScreen ms){
        this.game = game;
        this.ms = ms;
        this.buttonwidth = (float) Gdx.graphics.getWidth() / 3;
        this.buttonheight = (float) Gdx.graphics.getHeight() / 6;

        waves = 5;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        background = new Texture(Gdx.files.internal("uielements/MainScreenBackground.jpg"));

        incrementTexture = new Texture(Gdx.files.internal("uielements/upArrow.png"));
        decrementTexture = new Texture(Gdx.files.internal("uielements/downArrow.png"));
        returnToMainTexture = new Texture(Gdx.files.internal("uielements/exitmenu.png"));
        goTexture = new Texture(Gdx.files.internal("uielements/go.png"));

        incrementWaves = new Button(new TextureRegionDrawable(incrementTexture));
        decrementWaves = new Button(new TextureRegionDrawable(decrementTexture));
        returnToMain = new Button(new TextureRegionDrawable(returnToMainTexture));
        go = new Button(new TextureRegionDrawable(goTexture));

        float totalButtonHeight = buttonheight * 6;
        float startY = (Gdx.graphics.getHeight() - totalButtonHeight) / 2;

        incrementWaves.setPosition(Gdx.graphics.getWidth() / 2f, startY + 5 * buttonheight);
        decrementWaves.setPosition(Gdx.graphics.getWidth() / 2f, startY + 4 * buttonheight);
        returnToMain.setPosition(Gdx.graphics.getWidth() / 2f, startY + 3 * buttonheight);
        go.setPosition(Gdx.graphics.getWidth() / 2f, startY + 2 * buttonheight);

        incrementWaves.setSize(buttonwidth, buttonheight);
        decrementWaves.setSize(buttonwidth, buttonheight);
        returnToMain.setSize(buttonwidth, buttonheight);
        go.setSize(buttonwidth, buttonheight);

        incrementWaves.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                waves++;
                super.touchUp(event, x, y, pointer, button);
            }
        });

        decrementWaves.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(waves > 1) waves--;
                super.touchUp(event, x, y, pointer, button);
            }
        });

        returnToMain.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.main;
                super.touchUp(event, x, y, pointer, button);
            }
        });

        go.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.new_game;
                super.touchUp(event, x, y, pointer, button);
            }
        });

    }

    public int getWaves(){
        return waves;
    }

    /**
     * Change screen to specified state
     *
     * @param state - state to change screen to
     */
    public void changeScreen(STATE state) {
        if (state == STATE.new_game) {
            game.mainScreenMusic.dispose();
            game.setScreen(new GameScreen(game, game.getMainScreen(), waves));
        }

        if (state == STATE.main) {
            game.gameMusic.dispose();
            game.resetGameScreen();
            game.setScreen(game.getMainScreen());
        }
    }

    @Override
    public void show() {
        state = STATE.none;

        stage = new Stage(viewport, game.batch);
        Gdx.input.setInputProcessor(stage);

        stage.addActor(incrementWaves);
        stage.addActor(decrementWaves);
        stage.addActor(returnToMain);
        stage.addActor(go);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        game.batch.setProjectionMatrix(camera.combined);
        game.mainScreenMusic.play();

        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.font.draw(game.batch, Integer.toString(waves) + " waves", Gdx.graphics.getWidth() / 4f, 11 * Gdx.graphics.getHeight() / 20f);
        game.batch.end();
        stage.act();
        stage.draw();
        changeScreen(state);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            state = STATE.main;
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
