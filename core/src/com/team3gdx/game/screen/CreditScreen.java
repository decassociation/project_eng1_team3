package com.team3gdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team3gdx.game.MainGameClass;


public class CreditScreen implements Screen {
    public SpriteBatch batch;
    public Texture creditsImg;
    public CreditScreen(MainGameClass mainGameClass) {
        this.batch = mainGameClass.batch;
        this.creditsImg = new Texture(Gdx.files.internal("uielements/Credits.png"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        this.batch.begin();
        this.batch.draw(this.creditsImg, 0.0F, 0.0F, 800.0F, 480.0F);
        this.batch.end();
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
}
