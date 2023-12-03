package com.mygdx.game.render.character;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.PositionData;
import com.mygdx.game.data.charact.Goblin;
import com.mygdx.game.manage.TextManager;
import com.mygdx.game.myg2d.MyG2DUtil;
import com.mygdx.game.utils.MyLocalUtil;

public class GoblinRender {
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;

    OrthographicCamera bobCamera;

    TextManager textManager;

    public GoblinRender() {
        this.spriteBatch = MyGdxGame.getGame().getMainAsset().getSpriteBatch();
        this.shapeRenderer = MyGdxGame.getGame().getMainAsset().getLineShapeRender();
        bobCamera = MyGdxGame.getGame().getCameraManager().getBobCamera();
        textManager = MyGdxGame.getGame().getMainAsset().getTextRender();
    }

    public void render(Goblin goblin){
        shapeRenderer.setAutoShapeType(true);
        PositionData pos = goblin.pos();
        Rectangle rectangleShapeZ = pos.getRectangleShapeZ();
        // draw a rectangle
        shapeRenderer.begin();
        shapeRenderer.setProjectionMatrix(bobCamera.combined);
//        dying.a = 0.1f;
        if (goblin.status == Goblin.DYING){
            MyG2DUtil.openBlender();
            dying.a = MyLocalUtil.range(1f- MyLocalUtil.divideFloat(goblin.stateTime,Goblin.MAX_DIED_TIME),0f,1f);
            shapeRenderer.setColor(dying);
        }else  if (goblin.status == Goblin.DIED){
            shapeRenderer.set(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.x(goblin.pos().posCenter.x,goblin.pos().posCenter.y,goblin.pos().rectangle.x);
        }else {
            shapeRenderer.setColor(Color.FOREST);
        }

        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(rectangleShapeZ.x,rectangleShapeZ.y,rectangleShapeZ.width,rectangleShapeZ.height);

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(rectangleShapeZ.x,rectangleShapeZ.y,rectangleShapeZ.width,rectangleShapeZ.height);

        shapeRenderer.end();
        if (goblin.status == Goblin.DYING) {
            MyG2DUtil.closeBlender();
        }
//        textManager.render(goblin.name(),pos.pos.x,pos.pos.y,bobCamera);
    }
    Color dying = Color.SALMON.cpy();
    private void chooseColor(Goblin goblin) {


    }
}
