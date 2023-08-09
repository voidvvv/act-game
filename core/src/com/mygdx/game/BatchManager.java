package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class BatchManager implements Disposable {
    SpriteBatch batch;

    GLFieldRenderer glFieldRenderer;

    public BatchManager() {
        this.batch = new SpriteBatch();
        this.glFieldRenderer = new GLFieldRenderer();
    }

    @Override
    public void dispose() {
        batch.dispose();

    }
}
