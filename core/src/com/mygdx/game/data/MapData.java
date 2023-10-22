package com.mygdx.game.data;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.data.charact.AbstractAnimation;
import com.mygdx.game.data.charact.Goblin;
import com.mygdx.game.data.pool.character.GoblinPool;

import java.util.Comparator;

public class MapData {
    public boolean mapLightFlag = false;
    public float width = 480;
    public float height = 320;

//    public float[] lightPosition = new float[2];

    public void update(float delta){
//        bob.update(delta);
//        fixBobPosition();
//
//        lightPosition[0] = bob.pos().posCenter.x;
//        lightPosition[1] = bob.pos().posCenter.y;
        // acts update

    }

//
//
//    private void fixBobPosition() {
//        if (bob.pos().pos.x<bob.pos().rectangle.x/2){
//            bob.pos().pos.x = bob.pos().rectangle.x/2;
//        }else if (bob.pos().pos.x>width-bob.pos().rectangle.x/2){
//            bob.pos().pos.x = width-bob.pos().rectangle.x/2;
//        }
//
//        if (bob.pos().pos.y<0){
//            bob.pos().pos.y = 0;
//        }else if (bob.pos().pos.y>height-bob.pos().rectangle.y){
//            bob.pos().pos.y = height-bob.pos().rectangle.y;
//        }
//    }


}
