package com.mygdx.game.manage;

import com.badlogic.gdx.math.Vector2;

public class Character {
    public Vector2 position = new Vector2();
    public float z;


    public Character() {
    }

    public Character(Vector2 pos, float z) {
        this();
        this.position.set(pos);
        this.z = z;
    }


}
