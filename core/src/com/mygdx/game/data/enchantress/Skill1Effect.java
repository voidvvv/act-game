package com.mygdx.game.data.enchantress;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.KeyRenderUpdater;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.*;
import com.mygdx.game.data.charact.AbstractAnimation;
import com.mygdx.game.manage.CharactorManager;
import com.mygdx.game.render.enchantress.Skill1EffectRender;

public class Skill1Effect implements SkillEffect, KeyRenderUpdater, Pool.Poolable, NameAdaptor {
    Logger log = new Logger("Skill1Effect",Logger.INFO);
    public static final int Disable = 0;
    public static final int Enable = 1;

    public AbstractAnimation bob;
    public int status;

    Array<AbstractAnimation> targets = new Array<>();

    // position and range
    public PositionData position = new PositionData();

    public AbstractAnimation getBob() {
        return bob;
    }

    public void setBob(AbstractAnimation bob) {
        this.bob = bob;
    }

    @Override
    public int type() {
        return 0;
    }

    @Override
    public AbstractAnimation getAct() {
        return bob;
    }

    public void init(AbstractAnimation act){
        log.info("skill1 effect init");
        this.status = Skill1Effect.Enable;
        setBob(act);
        initCheckRange();
    }

    private void initCheckRange() {
        PositionData posData = bob.pos();
        float dx = posData.directVect.x>=0? 1f: -1f;

        this.position.pos.set(posData.posCenter.x+(dx*6),posData.posCenter.y-10);
        this.position.rectangle.set(dx*10,3);
        this.position.height = posData.height+20;
        this.position.directVect.set(posData.directVect);
    }


    @Override
    public void end() {

    }

    @Override
    public void reset() {

        this.status = Skill1Effect.Disable;
        if (targets.isEmpty()){
            log.info("skill miss!!");
        }else {
            targets.clear();
        }
        log.info("effect reset");
    }

    @Override
    public void update(float delta) {
        CharactorManager manager = MyGdxGame.getInstance().getMainAsset().getCharactorManager();
        Array<AbstractAnimation> acts = manager.getActs();
        for(int x=0; x<acts.size;x++){
            AbstractAnimation act = acts.get(x);
            if (checkValid(act)){
                log.info(this.name() + " check for " + act.name() + " true");
                applyAct(act);
            }
        }
    }

    private boolean checkValid(AbstractAnimation act) {
        boolean b = !targets.contains(act, false)
                && checkEnemy(act)
                && this.position.overlaps(act.pos());
        afterCheck(act , b);
        return b;
    }

    private boolean checkEnemy(AbstractAnimation act) {

        return bob.camp() != act.camp() && !act.died();
    }

    private void afterCheck(AbstractAnimation act, boolean result) {

    }

    private void applyAct(AbstractAnimation act) {
        targets.add(act);
        // 特殊处理
        float d = calculateDamage(act);
        act.beDamaged(this,d);
        act.beAttacked(bob,this);
    }

    private float calculateDamage(AbstractAnimation act) {
        return 10;
    }

    @Override
    public void render() {
        Skill1EffectRender render = MyGdxGame.getGame().getMainAsset().getSkill1EffectRender();
        render.render(this);
    }

    @Override
    public String name() {
        return "skill1";
    }
}
