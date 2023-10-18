package com.mygdx.game.manage;

import com.badlogic.gdx.utils.Array;

public class CharactorManager {
    public final Array<Character> characters = new Array<>();

//    public Character createNew(){
//        Character c = new Character();
//        characters.add(c);
//        return c;
//    }

    public void removeOne(Character c){
        characters.removeValue(c,true);
    }
}
