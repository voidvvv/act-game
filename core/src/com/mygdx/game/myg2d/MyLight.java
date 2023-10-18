package com.mygdx.game.myg2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class MyLight {
    public float[] position = new float[2];

    public float minRange;

    public float maxRange;

    Color color;

    float[] colorFloat = new float[4];

    public MyLight(Vector2 position, float minRange, float maxRange, Color color) {
        this.position[0] = position.x;
        this.position[1] = position.y;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.color = color;

        colorFloat[0] = color.r;
        colorFloat[1] = color.g;
        colorFloat[2] = color.b;
        colorFloat[3] = color.a;

    }

    public MyLight(Vector2 position, float minRange, float maxRange) {
        this.position[0] = position.x;
        this.position[1] = position.y;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.color = Color.SKY.cpy();
        this.color.a = 0.65f;
        colorFloat[0] = color.r;
        colorFloat[1] = color.g;
        colorFloat[2] = color.b;
        colorFloat[3] = color.a;

    }

    public MyLight(float x, float y, float minRange, float maxRange, Color color) {
        this.position[0] = x;
        this.position[1] = y;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.color = color;

        colorFloat[0] = color.r;
        colorFloat[1] = color.g;
        colorFloat[2] = color.b;
        colorFloat[3] = color.a;

    }

    public void setColor(Color color) {
        this.color.set(color);

        colorFloat[0] = this.color.r;
        colorFloat[1] = this.color.g;
        colorFloat[2] = this.color.b;
        colorFloat[3] = this.color.a;
    }


    public MyLight(float x, float y, float minRange, float maxRange) {
        this.position[0] = x;
        this.position[1] = y;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.color = Color.SKY.cpy();
        this.color.a = 0.45f;
        colorFloat[0] = color.r;
        colorFloat[1] = color.g;
        colorFloat[2] = color.b;
        colorFloat[3] = color.a;

    }
}
