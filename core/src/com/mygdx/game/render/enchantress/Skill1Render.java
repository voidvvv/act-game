package com.mygdx.game.render.enchantress;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MainAsset;
import com.mygdx.game.data.AbstractSkill;
import com.mygdx.game.data.enchantress.Skill1;
import com.mygdx.game.render.MyRenderer;

public class Skill1Render implements MyRenderer<Skill1> {

    SpriteBatch spriteBatch;

    ShapeRenderer shapeRenderer;

    Camera camera;

    public Skill1Render(MainAsset mainAsset) {

        spriteBatch = mainAsset.getSpriteBatch();
        shapeRenderer = mainAsset.getShapRender();
        camera = mainAsset.getBobCamera();
    }

    @Override
    public void render(Skill1 skill) {

//        this.shapeRenderer.setProjectionMatrix(camera.combined);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(Color.CHARTREUSE);
//        shapeRenderer.rect(skill.box[0], skill.box[1], skill.box[2], skill.box[3] );
//        shapeRenderer.end();
    }
}
