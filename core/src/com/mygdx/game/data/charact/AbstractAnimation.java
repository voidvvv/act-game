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

public abstract class AbstractAnimation  implements KeyRenderUpdater, NameAdaptor {
    public static final Logger log = new Logger("AbstractAnimation",Logger.INFO);

    public int status;

    public  abstract PositionData pos();

    public abstract FightPropData fightProp();

    public abstract Vector2 direct();

    public abstract MapData mapData();

    public abstract int camp();

    public abstract void beAttacked(AbstractAnimation anim, SkillEffect skillEffect);

    public abstract void makeBobIdolForce() ;

    public abstract void makeBobAttacking1();

    public void beDamaged(Skill1Effect skill1Effect, float d) {
        log.info(naame()+"收到来自 "+skill1Effect.bob.naame()+" 的 "+skill1Effect.naame()+" 技能 " + d + "点伤害");
        System.out.println(naame()+"收到来自 "+skill1Effect.bob.naame()+" 的 "+skill1Effect.naame()+" 技能 " + d + "点伤害");
        this.fightProp().hp-=d;
    }
}
