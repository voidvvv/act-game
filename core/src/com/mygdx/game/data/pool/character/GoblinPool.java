package com.mygdx.game.data.pool.character;

import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.data.charact.Goblin;

public class GoblinPool extends Pool<Goblin> {
    @Override
    protected Goblin newObject() {
        return new Goblin();
    }
}
