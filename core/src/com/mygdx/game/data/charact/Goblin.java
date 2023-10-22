package com.mygdx.game.data.charact;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.FightPropData;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.MapData;
import com.mygdx.game.data.PositionData;
import com.mygdx.game.data.SkillEffect;
import com.mygdx.game.render.character.GoblinRender;

public class Goblin extends AbstractAnimation implements Pool.Poolable {
    String name;
    int camp;

    public float stateTime = 0;

    public void init(String name, float x, float y, float width, float height, float lengthZ){
        this.name = name;
        this.positionData.pos.set(x,y);
        this.positionData.rectangle.set(width,height);
        this.positionData.height = lengthZ;
        camp = 1;
    }

    @Override
    public void update(float delta) {

        this.positionData.update(delta);
        stateTime+=delta;
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
    public void makeBobIdolForce() {

    }

    @Override
    public void makeBobAttacking1() {

    }

    @Override
    public void reset() {

    }
}
