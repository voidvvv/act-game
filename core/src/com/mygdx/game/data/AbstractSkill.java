package com.mygdx.game.data;

import com.mygdx.game.data.charact.AbstractAnimation;
import com.mygdx.game.render.IRender;

public abstract class AbstractSkill {
    public static final int STATUS_NONE = 0; // not be use
    public static final int STATUS_RUNNING = 1;
    public abstract void apply(AbstractAnimation myBob) ;

    public abstract void update(float delta);

    public abstract void render();

    public abstract void end();

    public abstract  boolean runningFlag();

}
