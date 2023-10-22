package com.mygdx.game.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;

public class TextRender {
    SpriteBatch spriteBatch;
    BitmapFont font;

    FreeTypeFontGenerator generator;

    public TextRender() {


    }

    public void init(){
        this.spriteBatch = MyGdxGame.getGame().getMainAsset().getSpriteBatch();
        this.font = MyGdxGame.getGame().getMainAsset().getDefaultFont();

    }

    public void render(String text, float x, float y){
        spriteBatch.begin();
        font.draw(spriteBatch,text,x,y);
        spriteBatch.end();
    }
    public void render(String text, float x, float y, Camera camera){

        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(camera.combined);
        font.draw(spriteBatch,text,x,y);
        spriteBatch.end();
    }
}
