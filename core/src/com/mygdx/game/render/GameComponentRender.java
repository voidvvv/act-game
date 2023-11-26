package com.mygdx.game.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FightPropData;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.charact.AbstractAnimation;
import com.mygdx.game.manage.CameraManager;
import com.mygdx.game.manage.TextManager;
import com.mygdx.game.utils.MyLocalUtil;

public class GameComponentRender {
    public static final float BAR_LENGTH = 15;
    SpriteBatch spriteBatch;
    OrthographicCamera screenCamera;
    TextManager textManager;

    CameraManager cameraManager;

    ShapeRenderer shapRender;

    public void init(){
        this.spriteBatch = MyGdxGame.getGame().getMainAsset().getSpriteBatch();
        screenCamera = MyGdxGame.getGame().getCameraManager().getScreenCamera();;
        textManager = MyGdxGame.getGame().getMainAsset().getTextRender();
        shapRender = MyGdxGame.getGame().getMainAsset().getLineShapeRender();
        cameraManager = MyGdxGame.getInstance().getCameraManager();
    }

    public void render(){
        Array<AbstractAnimation> acts = MyGdxGame.getInstance().getMainAsset().getCharactorManager().getActs();
        textManager.begin();
        for(int x=0; x<acts.size; x++){
            // render name
            renderName(acts.get(x));
        }
        textManager.end();
        shapRender.setAutoShapeType(true);
        // render hp and mp
        shapRender.begin(ShapeRenderer.ShapeType.Line);
        shapRender.setProjectionMatrix(screenCamera.combined);
        for(int x=0; x<acts.size; x++){
            // render name
            renderPropBar(acts.get(x));
        }
        shapRender.end();
    }
    Vector2 hp_start = new Vector2();
    Vector2 hp_cur_end = new Vector2();
    Vector2 hp_end = new Vector2();
    Vector2 mp_start = new Vector2();

    Vector2 mp_cur_end = new Vector2();
    Vector2 mp_end = new Vector2();
    private void renderPropBar(AbstractAnimation abstractAnimation) {

        OrthographicCamera bobCamera = MyGdxGame.getInstance().getCameraManager().getBobCamera();
        FightPropData fightPropData = abstractAnimation.fightProp();
        Vector2 pos = abstractAnimation.pos().pos;
        hp_start.set(pos.x-BAR_LENGTH/2,pos.y-2);
        cameraManager.convertCoordinate(hp_start,bobCamera,screenCamera);

        hp_cur_end.set(pos.x-BAR_LENGTH/2 + (BAR_LENGTH * MyLocalUtil.divideFloat(fightPropData.hp,fightPropData.maxHP)), pos.y-2);
        cameraManager.convertCoordinate(hp_cur_end,bobCamera,screenCamera);

        hp_end.set(pos.x+BAR_LENGTH/2,pos.y-2);
        cameraManager.convertCoordinate(hp_end,bobCamera,screenCamera);

        mp_start.set(pos.x-BAR_LENGTH/2,pos.y-8);
        cameraManager.convertCoordinate(mp_start,bobCamera,screenCamera);

        mp_cur_end.set(pos.x-BAR_LENGTH/2 + (BAR_LENGTH * MyLocalUtil.divideFloat(fightPropData.mp,fightPropData.maxMP)), pos.y-8);
        cameraManager.convertCoordinate(mp_cur_end,bobCamera,screenCamera);


        mp_end.set(pos.x+BAR_LENGTH/2,pos.y-8);
        cameraManager.convertCoordinate(mp_end,bobCamera,screenCamera);
        // hp
        shapRender.set(ShapeRenderer.ShapeType.Filled);
        shapRender.setColor(Color.RED);
        shapRender.rectLine(hp_start,hp_cur_end,15);
        shapRender.setColor(Color.PURPLE);
        shapRender.rectLine(mp_start,mp_cur_end,15);

        shapRender.set(ShapeRenderer.ShapeType.Line);
        shapRender.setColor(Color.BLACK);
        shapRender.rectLine(hp_start,hp_end,15);
        shapRender.rectLine(mp_start,mp_end,15);
    }

    Vector3 tmp3 = new Vector3();
    private void renderName(AbstractAnimation act) {
        String name = act.name();
        Vector2 pos = act.pos().pos;

        textManager.render(name,pos.x,pos.y+act.pos().height*1.2f,MyGdxGame.getGame().getCameraManager().getBobCamera(),50);
    }
}
