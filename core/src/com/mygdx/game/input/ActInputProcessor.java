package com.mygdx.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MainAsset;
import com.mygdx.game.data.ActData;
import com.mygdx.game.data.MapData;
import com.mygdx.game.screen.ActScreen;

public class ActInputProcessor extends InputAdapter {
    private MainAsset mainAsset;
    public ActData actData;

    public MapData mapData;

    public ActInputProcessor(MainAsset mainAsset) {
        this.mainAsset = mainAsset;
        actData = mainAsset.getActData();
        mapData = mainAsset.getMapData();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP){
            actData.bobUp(true);
        }else if (keycode == Input.Keys.DOWN){
            actData.bobDown(true);
        }
        if (keycode == Input.Keys.RIGHT){
            actData.bobRight(true);
        }else if (keycode == Input.Keys.LEFT){
            actData.bobLeft(true);
        };

        if (keycode == Input.Keys.Q){
            System.out.println(" key dowm ! ");
            actData.useSkill(1);
        }
        if (keycode == Input.Keys.SPACE){
            mapData.mapLightFlag = !mapData.mapLightFlag;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.UP){
            actData.bobUp(false);
        }else if (keycode == Input.Keys.DOWN){
            actData.bobDown(false);
        }
        if (keycode == Input.Keys.RIGHT){
            actData.bobRight(false);
        }else if (keycode == Input.Keys.LEFT){
            actData.bobLeft(false);
        };
        return false;
    }
    Vector3 tmp = new Vector3();
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touch down!!!!");
        mainAsset.getBobCamera().unproject(tmp.set(screenX,screenY,0));
        actData.moveTo(tmp.x,tmp.y);
        return super.touchDown(screenX, screenY, pointer, button);

    }
}
