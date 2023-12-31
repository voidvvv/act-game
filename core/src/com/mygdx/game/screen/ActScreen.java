package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.BaseResourceManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.ActData;
import com.mygdx.game.data.MapData;
import com.mygdx.game.myg2d.MyLight;
import com.mygdx.game.myg2d.MyShaderBatch;
import com.mygdx.game.render.MyMapRender;
import com.mygdx.game.render.TestRender;

import java.util.Arrays;

public class ActScreen extends BaseResourceManager implements Screen {
    SpriteBatch spriteBatch;

    OrthographicCamera bobCamera;

    ShapeRenderer worldCollideRender;

    ActData actData;
    MapData mapData;

    MyMapRender myMapRender;

    boolean debug = true;

    TestRender testRender;


    // test
//    TestRender testRender;

    @Override
    public void show() {
        super.show();
        testRender = new TestRender();
        testRender.create();
        worldCollideRender = new ShapeRenderer();
        spriteBatch = this.mainAsset.getSpriteBatch();
        bobCamera = this.mainAsset.getBobCamera();

        myMapRender = new MyMapRender(bobCamera,game.getMainAsset(),game);
        actData = this.game.getMainAsset().getActData();
        mapData = this.game.getMainAsset().getMapData();

//        myLight.setColor();
    }
float t=0;
    Vector3 v3 = new Vector3();

    Color color = Color.FIREBRICK.cpy();
    float[] lightPosition = new float[]{100,100};
    @Override
    public void render(float delta) {
        t+=delta;


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
            worldCollideRender.rect(actData.box[0],actData.box[1],actData.box[2],actData.box[3]);

//            worldCollideRender.rectLine(0,0,20,20,20);

            worldCollideRender.end();
        }
    }

    private void renderMap() {
        myMapRender.render();
//        new Texture().bind();

    }
    Vector3 lerpTarget = new Vector3();

    private void update(float delta) {
        mapData.update(delta);

        bobCamera.position.lerp(lerpTarget.set(actData.centre,0),5f*delta);
        fixCameraPosition();
        bobCamera.update();
        spriteBatch.setProjectionMatrix(bobCamera.combined);

        worldCollideRender.setProjectionMatrix(bobCamera.combined);
    }

    private void fixCameraPosition() {
        if (bobCamera.position.x<bobCamera.viewportWidth/2){
            bobCamera.position.x =bobCamera.viewportWidth/2;
        }else if (bobCamera.position.x>mapData.width-bobCamera.viewportWidth/2){
            bobCamera.position.x = mapData.width-bobCamera.viewportWidth/2;
        }
//        if (bobCamera.position.y)
        if (bobCamera.position.y<bobCamera.viewportHeight/2){
            bobCamera.position.y =bobCamera.viewportHeight/2;
        }else if (bobCamera.position.y>mapData.height-bobCamera.viewportHeight/2){
            bobCamera.position.y = mapData.height-bobCamera.viewportHeight/2;
        }

    }

    @Override
    public void resize(int width, int height) {
        bobCamera.setToOrtho(false, 125, 125);
        bobCamera.update(false);

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
