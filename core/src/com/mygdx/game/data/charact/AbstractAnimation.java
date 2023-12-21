package com.mygdx.game.data.charact;

import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.data.*;
import com.mygdx.game.KeyRenderUpdater;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.enchantress.Skill1Effect;

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

    public void beforeDied(){
        // do something
    }

    public static class ActCompare implements Comparator<AbstractAnimation>{
        @Override
        public int compare(AbstractAnimation o1, AbstractAnimation o2) {
            return (int) o2.pos().pos.y - (int)o1.pos().pos.y;
        }
    }

    protected void fixPosition() {
        MapData mapData = MyGdxGame.getGame().getMainAsset().getMapData();
        float width = mapData.width;
        float height = mapData.height;
        if (this.pos().pos.x < this.pos().rectangle.x / 2) {
            this.pos().pos.x = this.pos().rectangle.x / 2;
        } else if (this.pos().pos.x > width - this.pos().rectangle.x / 2) {
            this.pos().pos.x = width - this.pos().rectangle.x / 2;
        }

        if (this.pos().pos.y < 0) {
            this.pos().pos.y = 0;
        } else if (this.pos().pos.y > height - this.pos().rectangle.y) {
            this.pos().pos.y = height - this.pos().rectangle.y;
        }
    }
}
