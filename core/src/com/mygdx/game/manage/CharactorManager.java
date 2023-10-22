package com.mygdx.game.manage;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.data.MyBob;
import com.mygdx.game.data.charact.AbstractAnimation;

public class CharactorManager {
    Array<AbstractAnimation> acts; // total

    Array<AbstractAnimation> zero_acts;
    Array<AbstractAnimation> one_acts;
    Array<AbstractAnimation> two_acts;

    public CharactorManager() {
        acts = new Array<>();
        zero_acts = new Array<>();
        one_acts = new Array<>();
        two_acts = new Array<>();
    }

    AbstractAnimation bob;
    AbstractAnimation.ActCompare compare = AbstractAnimation.instance();

    public void removeOne(AbstractAnimation c){
        acts.removeValue(c,true);
    }
    private void sortActs() {
        acts.sort(compare);
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

    public void resetBob(float x, float y) {
        if (bob == null){
            bob = new MyBob(20,15,5,null);
        }else {
            acts.removeValue(bob,true);
        }
        bob.pos().pos.set(x,y);
        acts.add(bob);
    }

    public void setBob(AbstractAnimation bob) {
        this.bob = bob;
    }

    public AbstractAnimation getBob() {

        return bob;
    }

    public void renderCharactor() {
        for(int x=0;x<acts.size;x++){
            acts.get(x).render();
        }
    }
}
