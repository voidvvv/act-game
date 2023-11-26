package com.mygdx.game.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.manage.TextManager;

public class UIRender {
    BitmapFont baseFont;
    SpriteBatch spriteBatch ;
    OrthographicCamera screenCamera;
    ShapeRenderer lineShapeRender ;
    TextManager textManager;
    public void init(){
        baseFont = MyGdxGame.getInstance().getMainAsset().getTtfBitmapFont();
        spriteBatch = MyGdxGame.getInstance().getMainAsset().getSpriteBatch();
        screenCamera = MyGdxGame.getInstance().getCameraManager().getScreenCamera();
        lineShapeRender =  MyGdxGame.getInstance().getMainAsset().getLineShapeRender();
        textManager = MyGdxGame.getInstance().getMainAsset().getTextRender();

    }

    public void render(){
        textManager.begin();
        textManager.render("这是默认中文abc,.;",400,350,screenCamera,140);
        textManager.end();

        lineShapeRender.begin();
        lineShapeRender.setColor(Color.BLACK);
        lineShapeRender.set(ShapeRenderer.ShapeType.Filled);
        lineShapeRender.circle(150,250,20);
        lineShapeRender.end();
    }
}
