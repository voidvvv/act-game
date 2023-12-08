package com.mygdx.game.data;

import com.badlogic.gdx.math.MathUtils;

public class FightPropData {
    public float hp = 0;

    public float maxHP = 0;

    public float mp = 0;

    public float maxMP = 0;

    public float attack; // 攻击力
    public float defence; // 防御力
    public float magicPower;// 法术强度
    public float magicDefence;// 法术抗性


    public void reset() {

    }

    public void init(float maxHp, float maxMp){
        init(maxHp,maxMp,0,0);
    }

    public void init(float maxHp, float maxMp, float hpInterval, float mpInterval){
        float hp_diff = MathUtils.random(-hpInterval,hpInterval);
        float mp_diff = MathUtils.random(-mpInterval,mpInterval);
        hp = maxHp + hp_diff;
        this.maxHP = maxHp + hp_diff;
        mp = maxMp + mp_diff;
        this.maxMP = maxMp + mp_diff;
    }

    public void subHp(float d) {
        this.hp-=d;
        if (hp<=0){
            hp = 0;
        }
    }
}
