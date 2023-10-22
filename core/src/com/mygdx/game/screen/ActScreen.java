package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.BaseResourceManager;
import com.mygdx.game.data.MyBob;
import com.mygdx.game.data.MapData;
import com.mygdx.game.data.charact.AbstractAnimation;
import com.mygdx.game.manage.CameraManager;
import com.mygdx.game.manage.CharactorManager;
import com.mygdx.game.render.MyMapRender;
import com.mygdx.game.render.TestRender;

public class ActScreen extends BaseResourceManager implements Screen {
    SpriteBatch spriteBatch;

    OrthographicCamera bobCamera;

    ShapeRenderer worldCollideRender;

    AbstractAnimation myBob;
    MapData mapData;

    MyMapRender myMapRender;

    boolean debug = true;


    // test
//    TestRender testRender;
    CharactorManager charactorManager ;
    @Override
    public void show() {
        super.show();

        worldCollideRender = new ShapeRenderer();
        spriteBatch = this.mainAsset.getSpriteBatch();
        CameraManager cameraManager = new CameraManager();
        bobCamera = cameraManager.getBobCamera();

        myMapRender = game.getMainAsset().myMapRender;

        mapData = this.game.getMainAsset().getMapData();
        charactorManager = this.game.getMainAsset().getCharactorManager();
        charactorManager.clearAllAct();
        charactorManager.resetBob(20,20);
        myBob = charactorManager.getBob();
        game.setCameraManager(cameraManager);
        Gdx.input.setInputProcessor(this.game.getActInputProcessor());
//        myLight.setColor();
    }
float t=0;
    Vector3 v3 = new Vector3();

    Color color = Color.FIREBRICK.cpy();
    float[] lightPosition = new float[]{100,100};
    @Override
    public void render(float delta) {
        t+=delta;
        game.getCameraManager().update(delta);

//        lightingShader.setUniformf("iResolution",v3.set(2000,2000,1.0f));
        update(delta);
//        lightingShader.bind();
//        spriteBatch.setShader(lightingShader);
        draw(delta);
//        testRender.render();
    }


    private void draw(float delta) {
        ScreenUtils.clear(Color.CORAL,true);
//        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

        renderMap();
        renderDebugBox();
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
        charactorManager.renderCharactor();
    }


    private void update(float delta) {
//        mapData.update(delta);
        myMapRender.act(delta);

//        spriteBatch.setProjectionMatrix(bobCamera.combined);

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
