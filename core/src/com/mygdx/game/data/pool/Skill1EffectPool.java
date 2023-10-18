package com.mygdx.game.data.pool;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.data.SkillEffect;
import com.mygdx.game.data.enchantress.Skill1Effect;

public class Skill1EffectPool extends Pool<SkillEffect> {
    @Override
    protected SkillEffect newObject() {
        return new Skill1Effect();
    }

    public Skill1EffectPool(int initialCapacity, int max) {
        super(initialCapacity, max);
        fill(Math.min(3,initialCapacity));
    }
}
