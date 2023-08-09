package com.mygdx.game.path;


import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;

public interface IndexNode<N extends IndexNode<N>> {
    int getIndex();

    Array<Connection<N>> getConnections();
}
