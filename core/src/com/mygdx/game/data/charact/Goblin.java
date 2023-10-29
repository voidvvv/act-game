package com.mygdx.game.data.charact;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.FightPropData;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.MapData;
import com.mygdx.game.data.PositionData;
import com.mygdx.game.data.SkillEffect;
import com.mygdx.game.data.enchantress.Skill1Effect;
import com.mygdx.game.render.character.GoblinRender;

public class Goblin extends AbstractAnimation implements Pool.Poolable {
    public static final Logger log = new Logger("Goblin",Logger.INFO);
    public static final float MAX_DYING_TIME = 1F;
    public static final float MAX_DIED_TIME = 0.5F;
    public static float MAX_HP = 100;
    public static float MAX_MP = 100;
    float time = 0f;
    public static final int BIRTHING =0;
    public static final int IDOL =1;
    public static final int BE_ATTACKED =2;

    public static final int DYING =3;

    public static final int DIED =4;
    String name;
    int camp;

    public float stateTime = 0;

    public void init(String name, float x, float y, float width, float height, float lengthZ){
        this.name = name;
        this.positionData.pos.set(x,y);
        this.positionData.rectangle.set(width,height);
        this.positionData.height = lengthZ;
        camp = 1;
        stateTime = 0;
        this.status = BIRTHING;
        fightProp().init(MAX_HP,MAX_MP);
    }

    @Override
    public void update(float delta) {
        time += delta;
        stateTime+=delta;
        this.positionData.update(delta);
        checkDied();


    }

    private void checkDied() {
        if (status == DYING && stateTime>=MAX_DYING_TIME){
            status = DIED;
            stateTime = 0f;
        }
        if (status == DIED && stateTime>=MAX_DIED_TIME){
            MyGdxGame.getInstance().getMainAsset().getCharactorManager().goblinPool.free(this);
        }
    }

    @Override
    public void render() {
        GoblinRender goblinRender = MyGdxGame.getGame().getMainAsset().getGoblinRender();
        goblinRender.render(this);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int camp() {
        return camp;
    }

    @Override
    public void beAttacked(AbstractAnimation anim, SkillEffect skillEffect) {

    }

    @Override
    public void makeIdolForce() {

    }

    @Override
    public void makeBobAttacking1() {

    }

    @Override
    public void beDamaged(Skill1Effect skill1Effect, float d) {
        super.beDamaged(skill1Effect, d);
        this.fightProp().subHp(d);
        if (this.fightProp().hp <= 0f){
            log.info(name() + " 受到致命伤害，进入dying！");
            if (status!=DYING && status!=DIED){
                status = DYING;
                stateTime = 0f;
            }
        }
    }

    @Override
    public boolean died() {
        return status == DYING || status == DIED;
    }

    @Override
    public void reset() {
        super.reset();
        MyGdxGame.getInstance().getMainAsset().getCharactorManager().resetGoblin(this);
    }
}
