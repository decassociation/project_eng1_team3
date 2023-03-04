package com.team3gdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team3gdx.game.MainGameClass;

import static com.badlogic.gdx.Gdx.input;

public class CreditScreen implements Screen {
    public SpriteBatch batch;
    public Texture creditsImg;
    public MainGameClass mainGameClass;
    public MainScreen mainScreen;

    public CreditScreen(MainGameClass mainGameClass) {
        this.batch = mainGameClass.batch;
        this.creditsImg = new Texture(Gdx.files.internal("uielements/Credits.png"));
        this.mainScreen = mainScreen;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        this.batch.begin();
        this.batch.draw(this.creditsImg, 0.0F, 0.0F, 1600.0F, 900.0F);
        this.batch.end();
        if (input.isKeyJustPressed(Input.Keys.SPACE)) {
            this.mainGameClass.setScreen(this.mainScreen);
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
        this.creditsImg.dispose();
    }

    public void setMainGameClass(MainGameClass mainGameClass) {
        this.mainGameClass = mainGameClass;
    }

    public void setMainScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }

    public MainScreen getMainScreen() {
        return mainScreen;
    }
}