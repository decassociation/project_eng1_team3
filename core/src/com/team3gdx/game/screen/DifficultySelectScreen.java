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

public class DifficultySelectScreen implements Screen {

    final MainGameClass game;
    final MainScreen ms;

    private String difficulty;
    private int waves;

    float buttonwidth;
    float buttonheight;

    Button easy;
    Button normal;
    Button hard;
    Button back;
    Button go;

    Texture easyTexture;
    Texture normalTexture;
    Texture hardTexture;
    Texture backTexture;
    Texture goTexture;

    enum STATE {
        none, back, new_game
    }

    STATE state;
    Stage stage;

    OrthographicCamera camera;
    Viewport viewport;

    Texture background;

    BitmapFont font;

    public DifficultySelectScreen(MainGameClass game, MainScreen ms, int waves){
        this.game = game;
        this.ms = ms;
        this.waves = waves;
        this.buttonwidth = (float) Gdx.graphics.getWidth() / 3;
        this.buttonheight = (float) Gdx.graphics.getHeight() / 6;

        difficulty = "normal";

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        background = new Texture(Gdx.files.internal("uielements/MainScreenBackground.jpg"));

        easyTexture = new Texture(Gdx.files.internal("uielements/easy.png"));
        normalTexture = new Texture(Gdx.files.internal("uielements/normal.png"));
        hardTexture = new Texture(Gdx.files.internal("uielements/hard.png"));
        backTexture = new Texture(Gdx.files.internal("uielements/back.png"));
        goTexture = new Texture(Gdx.files.internal("uielements/go.png"));

        easy = new Button(new TextureRegionDrawable(easyTexture));
        normal = new Button(new TextureRegionDrawable(normalTexture));
        hard = new Button(new TextureRegionDrawable(hardTexture));
        back = new Button(new TextureRegionDrawable(backTexture));
        go = new Button(new TextureRegionDrawable(goTexture));

        float totalButtonHeight = buttonheight * 6;
        float startY = (Gdx.graphics.getHeight() - totalButtonHeight) / 2;

        easy.setPosition(Gdx.graphics.getWidth() / 2f, startY + 5 * buttonheight);
        normal.setPosition(Gdx.graphics.getWidth() / 2f, startY + 4 * buttonheight);
        hard.setPosition(Gdx.graphics.getWidth() / 2f, startY + 3 * buttonheight);
        back.setPosition(Gdx.graphics.getWidth() / 2f, startY + 2 * buttonheight);
        go.setPosition(Gdx.graphics.getWidth() / 2f, startY + 1 * buttonheight);

        easy.setSize(buttonwidth, buttonheight);
        normal.setSize(buttonwidth, buttonheight);
        hard.setSize(buttonwidth, buttonheight);
        back.setSize(buttonwidth, buttonheight);
        go.setSize(buttonwidth, buttonheight);

        easy.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                difficulty = "easy";
                super.touchUp(event, x, y, pointer, button);
            }
        });

        normal.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                difficulty = "normal";
                super.touchUp(event, x, y, pointer, button);
            }
        });

        hard.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                difficulty = "hard";
                super.touchUp(event, x, y, pointer, button);
            }
        });

        back.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.back;
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

    public String getDifficulty(){
        return difficulty;
    }

    /**
     * Change screen to specified state
     *
     * @param state - state to change screen to
     */
    public void changeScreen(STATE state) {
        if (state == STATE.new_game) {
            game.mainScreenMusic.dispose();
            if(waves == -1) game.setScreen(new GameScreen(game, game.getMainScreen(), difficulty));
            else game.setScreen(new GameScreen(game, game.getMainScreen(), difficulty, waves));
        }

        if (state == STATE.back) {
            game.gameMusic.dispose();
            game.setScreen(game.getWaveSelectScreen());
        }
    }

    @Override
    public void show() {
        state = STATE.none;
        waves = 5;

        stage = new Stage(viewport, game.batch);
        Gdx.input.setInputProcessor(stage);

        stage.addActor(easy);
        stage.addActor(normal);
        stage.addActor(hard);
        stage.addActor(back);
        stage.addActor(go);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        game.batch.setProjectionMatrix(camera.combined);
        game.mainScreenMusic.play();

        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.font.draw(game.batch, "Difficulty: " + difficulty, Gdx.graphics.getWidth() / 7f, 17 * Gdx.graphics.getHeight() / 20f);
        game.batch.end();
        stage.act();
        stage.draw();
        changeScreen(state);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            state = STATE.back;
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
