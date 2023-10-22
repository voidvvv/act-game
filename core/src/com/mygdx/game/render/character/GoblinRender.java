package com.mygdx.game.render.character;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.PositionData;
import com.mygdx.game.data.charact.Goblin;
import com.mygdx.game.render.TextRender;

public class GoblinRender {
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;

    OrthographicCamera bobCamera;

    TextRender textRender;

    public GoblinRender() {
        this.spriteBatch = MyGdxGame.getGame().getMainAsset().getSpriteBatch();
        this.shapeRenderer = MyGdxGame.getGame().getMainAsset().getShapRender();
        bobCamera = MyGdxGame.getGame().getCameraManager().getBobCamera();
        textRender = MyGdxGame.getGame().getMainAsset().getTextRender();
    }

    public void render(Goblin goblin){
        PositionData pos = goblin.pos();
        Rectangle rectangleShapeZ = pos.getRectangleShapeZ();
        // draw a rectangle
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(bobCamera.combined);
        shapeRenderer.setColor(Color.FOREST);
        shapeRenderer.rect(rectangleShapeZ.x,rectangleShapeZ.y,rectangleShapeZ.width,rectangleShapeZ.height);
        shapeRenderer.setColor(Color.RED);

        shapeRenderer.circle(pos.pos.x,pos.pos.y,1);
        shapeRenderer.end();

        textRender.render("Goblin",pos.pos.x,pos.pos.y);
    }
}
