package com.mygdx.game.data;

import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.data.charact.AbstractAnimation;

public interface SkillEffect extends Pool.Poolable{

    int type(); // 物理攻击  魔法攻击 .. 等等
    public abstract AbstractAnimation getAct();

    void end();


}
