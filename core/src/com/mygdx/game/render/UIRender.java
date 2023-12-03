package com.mygdx.game.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.manage.CameraManager;
import com.mygdx.game.manage.TextManager;

public class UIRender {
    BitmapFont baseFont;
    SpriteBatch spriteBatch ;
    OrthographicCamera screenCamera;
    ShapeRenderer lineShapeRender ;
    TextManager textManager;
    CameraManager cameraManager;

    TextureRegion profile01;
    public void init(){
        baseFont = MyGdxGame.getInstance().getMainAsset().getTtfBitmapFont();
        spriteBatch = MyGdxGame.getInstance().getMainAsset().getSpriteBatch();
        cameraManager = MyGdxGame.getInstance().getCameraManager();
        screenCamera = cameraManager.getScreenCamera();
        lineShapeRender =  MyGdxGame.getInstance().getMainAsset().getLineShapeRender();
        textManager = MyGdxGame.getInstance().getMainAsset().getTextRender();
        profile01 = MyGdxGame.getInstance().getMainAsset().profile01();


    }

    public void render(){
//        textManager.begin();
//
//        textManager.render("这是默认中文abc,.;",400,350,screenCamera,140);
//        textManager.end();

        renderProfile();

    }

    private void renderProfile() {
        // shape
        lineShapeRender.begin();
        lineShapeRender.setColor(Color.CORAL);
        lineShapeRender.set(ShapeRenderer.ShapeType.Filled);
        lineShapeRender.rect(50,cameraManager.screenHeight-450-50,400,450);
        lineShapeRender.end();

        spriteBatch.begin();
        spriteBatch.draw(profile01,100,cameraManager.screenHeight-450-50,300,450);
        spriteBatch.end();
    }
}
