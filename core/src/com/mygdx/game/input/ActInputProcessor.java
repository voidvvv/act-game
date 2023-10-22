package com.mygdx.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MainAsset;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.MyBob;
import com.mygdx.game.data.MapData;

public class ActInputProcessor extends InputAdapter {
    private MainAsset mainAsset;
    public MyBob myBob;

    public MapData mapData;

    public ActInputProcessor(MainAsset mainAsset) {
        this.mainAsset = mainAsset;
        myBob = mainAsset.getActData();
        mapData = mainAsset.getMapData();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP){
            myBob.bobUp(true);
        }else if (keycode == Input.Keys.DOWN){
            myBob.bobDown(true);
        }
        if (keycode == Input.Keys.RIGHT){
            myBob.bobRight(true);
        }else if (keycode == Input.Keys.LEFT){
            myBob.bobLeft(true);
        };

        if (keycode == Input.Keys.Q){
            System.out.println(" key dowm ! ");
            myBob.useSkill(0);
        }
        if (keycode == Input.Keys.SPACE){
            mapData.mapLightFlag = !mapData.mapLightFlag;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.UP){
            myBob.bobUp(false);
        }else if (keycode == Input.Keys.DOWN){
            myBob.bobDown(false);
        }
        if (keycode == Input.Keys.RIGHT){
            myBob.bobRight(false);
        }else if (keycode == Input.Keys.LEFT){
            myBob.bobLeft(false);
        };
        return false;
    }
    Vector3 tmp = new Vector3();
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        tmp.set(screenX,screenY,0);
        MyGdxGame.getGame().getCameraManager().getBobCamera().unproject(tmp);
//        myBob.moveTo(tmp.x,tmp.y);
        return super.touchDown(screenX, screenY, pointer, button);

    }

    public InputProcessor replaceBob(MyBob myBob) {
        this.myBob = myBob;
        return this;
    }
}
