package com.mygdx.game.path;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;

public class MapGraph<M extends IndexNode<M>> implements IndexedGraph<M> {
    Array<M> nodes;



    @Override
    public int getIndex(M node) {
        return node.getIndex();
    }

    @Override
    public int getNodeCount() {
        return 0;
    }

    @Override
    public Array<Connection<M>> getConnections(M fromNode) {
        return fromNode.getConnections();
    }
}
