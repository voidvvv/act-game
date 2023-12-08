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
import com.mygdx.game.myg2d.MyG2DUtil;

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

        bg.a = 0.35f;
    }
    Color bg = Color.WHITE.cpy();
    public void render(){


        renderProfile();
        if (MyGdxGame.getInstance().fail){
            MyG2DUtil.openBlender();
            float screenWidth = cameraManager.screenWidth;
            float screenHeight = cameraManager.screenHeight;

            lineShapeRender.begin();
            lineShapeRender.setColor(bg);
            lineShapeRender.setProjectionMatrix(screenCamera.combined);
            lineShapeRender.set(ShapeRenderer.ShapeType.Filled);
            lineShapeRender.rect(0,0,screenWidth,screenHeight);
            lineShapeRender.end();
            MyG2DUtil.closeBlender();
        }
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
