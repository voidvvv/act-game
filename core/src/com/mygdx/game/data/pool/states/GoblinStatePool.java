package com.mygdx.game.data.pool.states;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.utils.Pool;

public class GoblinStatePool extends Pool<State> {
    @Override
    protected State newObject() {
        return null;
    }
}
