package com.mygdx.game.render.character;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.PositionData;
import com.mygdx.game.data.charact.BossGoblin;
import com.mygdx.game.myg2d.MyG2DUtil;

public class GoblinBossRender {
    ShapeRenderer shapeRenderer;
    OrthographicCamera camera;

    public GoblinBossRender() {
    }

    Color initColor;

    public void init() {
        shapeRenderer = MyGdxGame.getGame().getMainAsset().getFilledShapeRender();
        camera = MyGdxGame.getGame().getCameraManager().getBobCamera();
        initColor = Color.RED.cpy();
        initColor.a = 0.5f;
    }


    public void render(BossGoblin goblin) {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(camera.combined);
        PositionData pos = goblin.pos();
        StateMachine<BossGoblin, State<BossGoblin>> stateMachine = goblin.stateMachine;
        Rectangle rectangleShapeZ = pos.getRectangleShapeZ();
        Color color = null;
        if (stateMachine.getCurrentState() == BossGoblin.INIT) {
            MyG2DUtil.openBlender();
            color = initColor;
        } else {
            color = Color.RED;
        }
        shapeRenderer.setColor(color);
        shapeRenderer.rect(rectangleShapeZ.x, rectangleShapeZ.y, rectangleShapeZ.width, rectangleShapeZ.height);

        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(rectangleShapeZ.x, rectangleShapeZ.y, rectangleShapeZ.width, rectangleShapeZ.height);


        shapeRenderer.end();
        if (stateMachine.getCurrentState() == BossGoblin.INIT)
            MyG2DUtil.closeBlender();

    }
}
