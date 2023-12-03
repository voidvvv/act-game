package com.mygdx.game.data.charact;

import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.FightPropData;
import com.mygdx.game.KeyRenderUpdater;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.NameAdaptor;
import com.mygdx.game.data.PositionData;
import com.mygdx.game.data.SkillEffect;
import com.mygdx.game.data.enchantress.Skill1Effect;

import java.util.Arrays;
import java.util.Comparator;

public abstract class AbstractAnimation  implements KeyRenderUpdater, NameAdaptor {
    public static final Logger log = new Logger("AbstractAnimation",Logger.INFO);



    public int status;

    public float time;
    public float stateTime;


    protected PositionData positionData = new PositionData();

    protected FightPropData fightPropData = new FightPropData();

    public  PositionData pos(){
        return this.positionData;
    };

    public FightPropData fightProp(){
        return this.fightPropData;
    };

    public void reset(){
        MyGdxGame.getInstance().getMainAsset().getCharactorManager().reset(this);

    }

    public boolean bossFlag(){
        return false;
    }

    public abstract int camp();

    public abstract void beAttacked(AbstractAnimation anim, SkillEffect skillEffect);

    public abstract void makeIdolForce() ;


    public abstract void makeBobAttacking1();

    public void beDamaged(Skill1Effect skill1Effect, float d) {
        log.info(name()+"收到来自 "+skill1Effect.bob.name()+" 的 "+skill1Effect.name()+" 技能 " + d + "点伤害");
    }
    static AbstractAnimation.ActCompare compare;
    public static AbstractAnimation.ActCompare instance(){
        if (compare == null){
            compare = new ActCompare();
        }
        return compare;
    }

    public boolean died() {
        return false;
    }

    public static class ActCompare implements Comparator<AbstractAnimation>{
        @Override
        public int compare(AbstractAnimation o1, AbstractAnimation o2) {
            return (int) o2.pos().pos.y - (int)o1.pos().pos.y;
        }
    }
}
