package com.mygdx.game.manage;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.data.MyBob;
import com.mygdx.game.data.charact.AbstractAnimation;
import com.mygdx.game.data.charact.Goblin;
import com.mygdx.game.data.pool.character.GoblinPool;

public class CharactorManager {
    public static final float GOBLIN_BIRTH_INTERVAL = 5F;
    Array<AbstractAnimation> acts; // total

    public GoblinPool goblinPool;
    public  final Array<float[]> goblinBirthPlace = new Array<>();
    float goblinBirth = 0f;

    Array<AbstractAnimation> goblins = new Array<>();

    Array<AbstractAnimation> zero_acts;
    Array<AbstractAnimation> one_acts;
    Array<AbstractAnimation> two_acts;

    public CharactorManager() {
        acts = new Array<>();
        zero_acts = new Array<>();
        one_acts = new Array<>();
        two_acts = new Array<>();
        goblinPool = new GoblinPool();
    }

    AbstractAnimation bob;
    AbstractAnimation.ActCompare compare = AbstractAnimation.instance();

    public void removeOne(AbstractAnimation c){
        acts.removeValue(c,true);
    }
    private void sortActs() {
        acts.sort(compare);
    }

    public void update(float delta){
        goblinBirth+=delta;
        for(int x=0; x<this.getActs().size;x++){
            this.getActs().get(x).update(delta);
        }
        generateGoblin(delta);
        sortActs();
    }

    private void generateGoblin(float delta) {
        if (goblins.size <= 5 && goblinBirth> GOBLIN_BIRTH_INTERVAL){
            Goblin goblin = goblinPool.obtain();
            int random = MathUtils.random(goblinBirthPlace.size-1);
            float[] floats = goblinBirthPlace.get(random);
            float x = MathUtils.random(floats[2])+floats[0];
            float y = MathUtils.random(floats[3])+floats[1];
            goblin.init("Golin!"+goblins.size,x,y,20,20,20);
            System.out.println("第" + (goblins.size) + " 只 goblin： " + x +"____"+ y);
            acts.add(goblin);
            goblins.add(goblin);
            goblinBirth = 0;
        }
    }

    public void clearAllAct() {
        this.acts.clear();
        this.two_acts.clear();
        this.one_acts.clear();
        this.zero_acts.clear();
    }
    public Array<AbstractAnimation> getActs() {
        return acts;
    }

    public void setAct(MyBob bob){
        this.bob = bob;
        acts.add(bob);

        zero_acts.add(bob);

    }

    public void addAct(AbstractAnimation anim){
        acts.add(anim);
        if (anim.camp() == 0){
            zero_acts.add(anim);
        }
        if (anim.camp() == 1){
            one_acts.add(anim);
        }
        if (anim.camp() == 2){
            two_acts.add(anim);
        }
    }

    Vector2 v2 = new Vector2(20,20);
    public void resetBob() {
        if (bob == null){
            bob = new MyBob(20,15,5,null);
        }else {
            acts.removeValue(bob,true);
            bob = new MyBob(20,15,5,null);
        }
        bob.pos().pos.set(v2);
        acts.add(bob);
    }

    public void setBob(AbstractAnimation bob) {
        this.bob = bob;
    }

    public AbstractAnimation getBob() {

        return bob;
    }

    public void initBobPosition(float x, float y) {
        v2.set(x,y);
    }

    public void reset(AbstractAnimation abstractAnimation) {
        acts.removeValue(abstractAnimation,true);
        zero_acts.removeValue(abstractAnimation,true);

    }

    public void resetGoblin(Goblin goblin) {
        goblins.removeValue(goblin,true);
    }
}
