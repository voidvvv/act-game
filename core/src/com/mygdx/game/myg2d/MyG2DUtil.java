package com.mygdx.game.myg2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class MyG2DUtil {

    public static void openBlender(){
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    public static void closeBlender(){
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }
}
