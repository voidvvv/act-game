package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.game.data.MapData;

public abstract class BaseResourceManager implements Screen {
    protected MyGdxGame game;
    protected MainAsset mainAsset;
    protected BatchManager batchManager;

    protected MapData mapData;

    public BaseResourceManager() {
        // test
    }

    @Override
    public void show() {
        game = MyGdxGame.getInstance();
        mainAsset = game.getMainAsset();
        batchManager = game.getBatchManager();
    }

    @Override
    public void render(float delta) {
        game.update(delta);
    }
}
