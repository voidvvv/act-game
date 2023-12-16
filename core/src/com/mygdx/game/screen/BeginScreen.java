package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.KeyRenderUpdater;
import com.mygdx.game.MyGdxGame;

public class BeginScreen implements Screen, KeyRenderUpdater {
    OrthographicCamera camera;
    SpriteBatch spriteBatch;
    BitmapFont font;
    public static final Logger log = new Logger("beginScreen");

    float time;
    @Override
    public void show() {
        camera = new OrthographicCamera();
        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        update(delta);
        render();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,width,height);
        camera.update();
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
        spriteBatch.dispose();
        font.dispose();
    }

    @Override
    public void update(float delta) {
        log.info("update  ! begin Screen");
        time+=delta;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            log.info("!!!!");
            MyGdxGame.getGame().setGameScreen();
        }else if (time > 1f){
            if (Gdx.input.isTouched()){
                log.info("!!!!");
                MyGdxGame.getGame().setGameScreen();
            }
        }
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.CORAL,true);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        font.draw(spriteBatch,"please press space to start!",20,20);
        spriteBatch.end();
    }
}
