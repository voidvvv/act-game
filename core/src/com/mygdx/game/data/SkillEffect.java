package com.mygdx.game.data;

import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.data.charact.AbstractAnimation;

public interface SkillEffect extends Pool.Poolable{

    int type();
    public abstract AbstractAnimation getAct();

    void end();
}
