package com.mygdx.game.render.enchantress;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.PositionData;
import com.mygdx.game.data.enchantress.Skill1Effect;

public class Skill1EffectRender {
    ShapeRenderer shapeRenderer;

    public Skill1EffectRender() {
        this.shapeRenderer = MyGdxGame.getGame().getMainAsset().getShapRender();
    }

    public void render(Skill1Effect s1e){
        if (s1e.status != Skill1Effect.Enable){
            return;
        }
        PositionData position = s1e.position;

        Vector2 v3 = position.pos;
        Vector2 rec = position.rectangle;
        shapeRenderer.setProjectionMatrix(MyGdxGame.getGame().getMainAsset().getBobCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(v3.x,v3.y,rec.x,rec.y);
        shapeRenderer.end();
    }
}
