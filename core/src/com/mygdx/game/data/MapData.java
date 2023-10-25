package com.mygdx.game.data;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.charact.AbstractAnimation;
import com.mygdx.game.data.charact.Goblin;
import com.mygdx.game.data.pool.character.GoblinPool;
import com.mygdx.game.manage.CharactorManager;

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
        CharactorManager charactorManager = MyGdxGame.getInstance().getMainAsset().getCharactorManager();
        for(int x=0; x<charactorManager.getActs().size;x++){
            charactorManager.getActs().get(0).update(delta);
        }

    }

//
//



}
