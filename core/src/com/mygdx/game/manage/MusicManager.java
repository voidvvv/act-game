package com.mygdx.game.manage;

import com.badlogic.gdx.audio.Music;
import com.mygdx.game.MainAsset;

public class MusicManager {
    public static final String bgm_01 = "music/xiang_tai_moon_insect.mp3";
    private MainAsset mainAsset;

    public MusicManager(MainAsset mainAsset) {
        this.mainAsset = mainAsset;
    }

    public Music[] musicList(){
       return null;
    }

    public Music getMusic(String s){
        //         bgm = mainAsset.getMusic("music/xiang_tai_moon_insect.mp3");
        return mainAsset.getMusic(s);
    }
}
