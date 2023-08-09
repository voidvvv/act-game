package com.mygdx.game.myg2d;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("NewApi")
public enum InternalFunctions implements Consumer<ShaderProgram> {
    DefaultCover(){
        @Override
        public void accept(ShaderProgram shaderProgram) {

        }
    },
    ;


    @Override
    public abstract void accept(ShaderProgram shaderProgram);
}
