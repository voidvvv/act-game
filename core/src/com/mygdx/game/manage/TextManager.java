package com.mygdx.game.manage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MainAsset;
import com.mygdx.game.MyGdxGame;

import java.util.Map;

public class TextManager {
    SpriteBatch spriteBatch;
    OrthographicCamera screenCamera;
    BitmapFont font;

    FreeTypeFontGenerator generator;

    GlyphLayout layout;

    CameraManager cameraManager;

    Map<Integer,BitmapFont> fontSize;
    public TextManager() {
        layout = new GlyphLayout();

    }

    public void init(){
        fontSize = MyGdxGame.getGame().getMainAsset().getFontSizeMap();
        this.spriteBatch = MyGdxGame.getGame().getMainAsset().getSpriteBatch();
        screenCamera = MyGdxGame.getGame().getCameraManager().getScreenCamera();;
//        this.font = MyGdxGame.getGame().getMainAsset().getDefaultFont();
        cameraManager = MyGdxGame.getGame().getCameraManager();
        generator  = new FreeTypeFontGenerator(Gdx.files.internal("font/song_01.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 50;

//        param.color = Color.LIGHT_GRAY.cpy();
        param.characters = FreeTypeFontGenerator.DEFAULT_CHARS + "中文";
        font = MyGdxGame.getGame().getMainAsset().getTtfBitmapFont();
    }

    public void render(String text, float x, float y){
        layout.setText(font,text);
        v3.x -= layout.width/2;
        v3.y += layout.height/2;
        screenCamera.unproject(v3.set(x,y,0));
        font.draw(spriteBatch,layout,v3.x,v3.y);
    }
    Vector3 v3 = new Vector3();
    public void render(String text, float x, float y, Camera camera){
        layout.setText(font,text);
        cameraManager.convertCoordinate(v3.set(x,y,0),camera,screenCamera);
//        camera.project(v3.set(x,y,0));
//
//
//        v3.y = Gdx.graphics.getHeight()-v3.y;
//        screenCamera.unproject(v3);
        v3.x -= layout.width/2;
        v3.y += layout.height/2;
        font.draw(spriteBatch,layout,v3.x,v3.y);
        font.draw(spriteBatch,"中啊abc",v3.x,v3.y+100);
    }

    public void render(String text, float x, float y, Camera camera,int size){
        BitmapFont font = fontSize.get(size);
        layout.setText(font,text);
        cameraManager.convertCoordinate(v3.set(x,y,0),camera,screenCamera);
//        camera.project(v3.set(x,y,0));
//
//
//        v3.y = Gdx.graphics.getHeight()-v3.y;
//        screenCamera.unproject(v3);
        v3.x -= layout.width/2;
        v3.y += layout.height/2;
        font.draw(spriteBatch,layout,v3.x,v3.y);
        font.draw(spriteBatch,"中啊abc",v3.x,v3.y+100);
    }

    public void update(float delta){

    }

    public void begin() {
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(screenCamera.combined);
    }

    public void end() {
        spriteBatch.end();
    }
}
