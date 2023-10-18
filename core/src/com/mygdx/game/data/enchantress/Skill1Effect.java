package com.mygdx.game.data.enchantress;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.KeyRenderUpdater;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.*;
import com.mygdx.game.data.charact.AbstractAnimation;
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
        PositionData posData = bob.pos();

        this.position.pos.set(posData.pos.x-10,posData.pos.y-2);
        this.position.rectangle.set(100,100);
    }

    @Override
    public void end() {

    }

    @Override
    public void reset() {
        this.status = Skill1Effect.Disable;
        targets.clear();
        log.info("effect reset");
    }

    @Override
    public void update(float delta) {
        MapData mapData = MyGdxGame.getInstance().getMainAsset().getMapData();
        Array<AbstractAnimation> acts = mapData.getActs();
        for(int x=0; x<acts.size;x++){
            AbstractAnimation act = acts.get(x);
            if (checkValid(act)){
                log.info(this.naame() + " check for " + act.naame() + " true");
                applyAct(act);
            }
        }
    }

    private boolean checkValid(AbstractAnimation act) {

        return !targets.contains(act,false) && this.position.overlaps(act.pos());
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
    public String naame() {
        return "skill1";
    }
}
