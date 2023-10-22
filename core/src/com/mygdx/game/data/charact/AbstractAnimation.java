package com.mygdx.game.data.charact;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.FightPropData;
import com.mygdx.game.KeyRenderUpdater;
import com.mygdx.game.data.MapData;
import com.mygdx.game.data.NameAdaptor;
import com.mygdx.game.data.PositionData;
import com.mygdx.game.data.SkillEffect;
import com.mygdx.game.data.enchantress.Skill1Effect;

import java.util.Comparator;

public abstract class AbstractAnimation  implements KeyRenderUpdater, NameAdaptor {
    public static final Logger log = new Logger("AbstractAnimation",Logger.INFO);

    public int status;

    protected PositionData positionData = new PositionData();

    protected FightPropData fightPropData = new FightPropData();

    public  PositionData pos(){
        return this.positionData;
    };

    public FightPropData fightProp(){
        return this.fightPropData;
    };


    public abstract int camp();

    public abstract void beAttacked(AbstractAnimation anim, SkillEffect skillEffect);

    public abstract void makeBobIdolForce() ;


    public abstract void makeBobAttacking1();

    public void beDamaged(Skill1Effect skill1Effect, float d) {
        log.info(name()+"收到来自 "+skill1Effect.bob.name()+" 的 "+skill1Effect.name()+" 技能 " + d + "点伤害");
        System.out.println(name()+"收到来自 "+skill1Effect.bob.name()+" 的 "+skill1Effect.name()+" 技能 " + d + "点伤害");
        this.fightProp().hp-=d;
    }
    static AbstractAnimation.ActCompare compare;
    public static AbstractAnimation.ActCompare instance(){
        if (compare == null){
            compare = new ActCompare();
        }
        return compare;
    }

    public static class ActCompare implements Comparator<AbstractAnimation>{
        @Override
        public int compare(AbstractAnimation o1, AbstractAnimation o2) {
            return (int) o2.pos().pos.y - (int)o1.pos().pos.y;
        }
    }
}
