package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.BaseResourceManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.MyBob;
import com.mygdx.game.data.MapData;
import com.mygdx.game.data.charact.AbstractAnimation;
import com.mygdx.game.manage.CameraManager;
import com.mygdx.game.manage.CharactorManager;
import com.mygdx.game.manage.MusicManager;
import com.mygdx.game.render.GameComponentRender;
import com.mygdx.game.render.MyMapRender;
import com.mygdx.game.render.TestRender;
import com.mygdx.game.render.UIRender;

public class ActScreen extends BaseResourceManager implements Screen {
    SpriteBatch spriteBatch;

    OrthographicCamera bobCamera;

    ShapeRenderer worldCollideRender;

    AbstractAnimation myBob;
    MapData mapData;

    MyMapRender myMapRender;

    boolean debug = false;


    // test
//    TestRender testRender;
    CharactorManager charactorManager ;
    Music music ;
    GameComponentRender gameComponentRender;

    UIRender uiRender;
    @Override
    public void show() {
        super.show();

        worldCollideRender = this.mainAsset.getFilledShapeRender();
        spriteBatch = this.mainAsset.getSpriteBatch();

        bobCamera = game.getCameraManager().getBobCamera();

        myMapRender = game.getMainAsset().myMapRender;

        mapData = this.game.getMainAsset().getMapData();
        charactorManager = this.game.getMainAsset().getCharactorManager();
        charactorManager.clearAllAct();
        charactorManager.resetBob();
        myBob = charactorManager.getBob();

        Gdx.input.setInputProcessor(this.game.getActInputProcessor());
        music = game.getMainAsset().getMusicManager().getMusic(MusicManager.bgm_01);

        music.setLooping(true);
        music.setVolume(0.5f);
//        music.play();
        System.out.println(music.getPosition());
        gameComponentRender = MyGdxGame.getInstance().getGameComponentRender();
        gameComponentRender.init();
//        myLight.setColor();
        uiRender = mainAsset.getUiRender();
    }
float t=0;

    @Override
    public void render(float delta) {
        super.render(delta);
        t+=delta;
        game.getCameraManager().update(delta);
        update(delta);
        draw(delta);
        uiRender.render();

    }


    private void draw(float delta) {
        ScreenUtils.clear(Color.CORAL,true);
        renderMap();
        renderDebugBox();
        gameComponentRender.render();
    }

    private void renderDebugBox() {
        if (debug){
            worldCollideRender.begin(ShapeRenderer.ShapeType.Line);
            worldCollideRender.setColor(Color.BLUE);
            // bob box
            Rectangle rectangleShape = myBob.pos().getRectangleShape();
            worldCollideRender.rect(rectangleShape.x, rectangleShape.y, rectangleShape.width,rectangleShape.height);
            worldCollideRender.setColor(Color.YELLOW);
            Rectangle rectangleShapeZ = myBob.pos().getRectangleShapeZ();
            worldCollideRender.rect(rectangleShapeZ.x, rectangleShapeZ.y, rectangleShapeZ.width,rectangleShapeZ.height);



            worldCollideRender.end();
        }
    }

    private void renderMap() {
//        myMapRender.render();
//        new Texture().bind();
        myMapRender.draw();

    }


    private void update(float delta) {
//        mapData.update(delta);
        myMapRender.act(delta);

        worldCollideRender.setProjectionMatrix(bobCamera.combined);
    }


    @Override
    public void resize(int width, int height) {
        game.getCameraManager().resize(width,height);

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
