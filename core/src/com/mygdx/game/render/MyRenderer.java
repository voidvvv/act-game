package com.mygdx.game.render;

import com.mygdx.game.data.AbstractSkill;

public interface MyRenderer <T extends AbstractSkill> extends IRender<T>{

    void render(T skill);
}
