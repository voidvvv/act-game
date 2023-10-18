package com.mygdx.game.manage;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Character {

    public Vector3 position = new Vector3();

    public Character() {
    }

    public Character(Vector3 pos) {
        this.position.set(pos);
    }


}
