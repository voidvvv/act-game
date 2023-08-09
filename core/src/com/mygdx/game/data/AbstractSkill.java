package com.mygdx.game.data;

import com.mygdx.game.render.IRender;
import com.mygdx.game.render.MyRenderer;

public abstract class AbstractSkill {
    public static final int STATUS_NONE = 0; // not be use
    public static final int STATUS_RUNNING = 1;
    public abstract void apply(ActData actData) ;

    public abstract void update(float delta);

    public abstract void end();

    public abstract  boolean runningFlag();

    public abstract IRender getRender();
}
