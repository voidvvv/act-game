package com.mygdx.game.path;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;

public class TestNode implements IndexNode<TestNode>{
    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public Array<Connection<TestNode>> getConnections() {
        return null;
    }
}
