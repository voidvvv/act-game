package com.mygdx.game.data;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.data.charact.AbstractAnimation;

public class MapData {
    public boolean mapLightFlag = false;
    public float width = 480;
    public float height = 320;
    MyBob bob;

    Array<AbstractAnimation> acts; // total

    Array<AbstractAnimation> zero_acts;
    Array<AbstractAnimation> one_acts;
    Array<AbstractAnimation> two_acts;


    public float[] lightPosition = new float[2];


    public MapData() {
        acts = new Array<>();
        zero_acts = new Array<>();
        one_acts = new Array<>();
        two_acts = new Array<>();
    }

    public Array<AbstractAnimation> getActs() {
        return acts;
    }

    public void setAct(MyBob bob){
        this.bob = bob;
        acts.add(bob);

        zero_acts.add(bob);

    }

    public void addAct(AbstractAnimation anim){
        acts.add(anim);
        if (anim.camp() == 0){
            zero_acts.add(anim);
        }
        if (anim.camp() == 1){
            one_acts.add(anim);
        }
        if (anim.camp() == 2){
            two_acts.add(anim);
        }
    }

    public void update(float delta){
//        bob.update(delta);
        fixBobPosition();

        lightPosition[0] = bob.centre.x;
        lightPosition[1] = bob.centre.y;
        // acts update
        for(int x=0;x<acts.size;x++){
            acts.get(x).update(delta);
        }
    }

    private void fixBobPosition() {
        if (bob.pos().pos.x<0){
            bob.pos().pos.x = 0;
        }else if (bob.pos().pos.x>width-bob.width){
            bob.pos().pos.x = width-bob.width;
        }

        if (bob.pos().pos.y<0){
            bob.pos().pos.y = 0;
        }else if (bob.pos().pos.y>height-bob.height){
            bob.pos().pos.y = height-bob.height;
        }
    }

}
