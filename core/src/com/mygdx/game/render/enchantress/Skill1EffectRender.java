package com.mygdx.game.render.enchantress;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.enchantress.Skill1Effect;

public class Skill1EffectRender {
    ShapeRenderer shapeRenderer;

    public Skill1EffectRender() {
        this.shapeRenderer = MyGdxGame.getGame().getMainAsset().getLineShapeRender();
        shapeRenderer.setAutoShapeType(true);
    }

    public void render(Skill1Effect s1e){
        if (s1e.status != Skill1Effect.Enable){
            return;
        }

//        Rectangle rectangleShape = position.getRectangleShape();
//        Rectangle rectangleShapeZ = position.getRectangleShapeZ();
//        shapeRenderer.setProjectionMatrix(MyGdxGame.getGame().getMainAsset().getBobCamera().combined);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.RED);
//        shapeRenderer.rect(rectangleShape.x,rectangleShape.y,rectangleShape.width,rectangleShape.height);
//        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(Color.BLACK);
//
//        shapeRenderer.rect(rectangleShapeZ.x,rectangleShapeZ.y,rectangleShapeZ.width,rectangleShapeZ.height);
//
//        shapeRenderer.end();
    }
}
