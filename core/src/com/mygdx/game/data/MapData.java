package com.mygdx.game.data;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapData {
    public boolean mapLightFlag = false;
    public float width = 480;
    public float height = 320;
    ActData bob;
    public float[] lightPosition = new float[2];


    public MapData() {


    }

    public void setAct(ActData bob){
        this.bob = bob;
    }
    public void update(float delta){
        bob.update(delta);
        fixBobPosition();

        lightPosition[0] = bob.centre.x;
        lightPosition[1] = bob.centre.y;
    }

    private void fixBobPosition() {
        if (bob.position.x<0){
            bob.position.x = 0;
        }else if (bob.position.x>width-bob.width){
            bob.position.x = width-bob.width;
        }

        if (bob.position.y<0){
            bob.position.y = 0;
        }else if (bob.position.y>height-bob.height){
            bob.position.y = height-bob.height;
        }
    }

}
