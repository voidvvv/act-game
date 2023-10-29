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

    public MapData mapData;

    public final TouchVector[] moveTo = {new TouchVector(),new TouchVector()};

    private InputStateData inputStateData;


    public ActInputProcessor(MainAsset mainAsset) {
        this.mainAsset = mainAsset;
        inputStateData = MyGdxGame.getInstance().getInputStateData();
        mapData = mainAsset.getMapData();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.Q){
            inputStateData.attack_key_0.key_down = true;
            inputStateData.attack_key_0.consume = false;
        }
        if (keycode == Input.Keys.SPACE){
            mapData.mapLightFlag = !mapData.mapLightFlag;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.Q){
            inputStateData.attack_key_0.key_down = false;
        }
        return false;
    }
    Vector3 tmp = new Vector3();
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (pointer<2){
            tmp.set(screenX,screenY,0);
            MyGdxGame.getGame().getCameraManager().getBobCamera().unproject(tmp);
//        myBob.moveTo(tmp.x,tmp.y);
            moveTo[pointer].x = tmp.x;
            moveTo[pointer].y = tmp.y;
            moveTo[pointer].touch = true;
            return true;
        }
        return false;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (pointer<2){
            tmp.set(screenX,screenY,0);
            MyGdxGame.getGame().getCameraManager().getBobCamera().unproject(tmp);
            moveTo[pointer].x = tmp.x;
            moveTo[pointer].y = tmp.y;
            moveTo[pointer].touch = false;
            moveTo[pointer].drag = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer<2){
            tmp.set(screenX,screenY,0);
            MyGdxGame.getGame().getCameraManager().getBobCamera().unproject(tmp);
            moveTo[pointer].x = tmp.x;
            moveTo[pointer].y = tmp.y;
            moveTo[pointer].drag = true;
            return true;
        }
        return false;
    }

}
