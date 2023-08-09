package com.mygdx.game.data.enchantress;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.AbstractSkill;
import com.mygdx.game.data.ActData;
import com.mygdx.game.data.MapData;
import com.mygdx.game.render.IRender;
import com.mygdx.game.render.MyRenderer;
import com.mygdx.game.render.enchantress.Skill1Render;

public class Skill1 extends AbstractSkill {
    public static final Logger log = new Logger("Skill1");
    public int status = STATUS_NONE;

    public float time = 0;

    public float maxTime;

    public ActData holder; // current holder

    public ActData trigger; // current trig, most time equals holder

    public float width = 20;
    public float height = 20;
    public final float[] box = new float[4]; // x, y, width, height

    public boolean endFlag = true;

    MapData mapData;

    Skill1Render render;

    public Skill1(float maxTime, ActData holder,MapData mapData) {
        this.maxTime = maxTime;
        this.holder = holder;
        this.mapData = mapData;
        render = new Skill1Render(MyGdxGame.game.getMainAsset());

    }

    @Override
    public void apply(ActData actData) {
        if (actData.status == ActData.STATUS_ATTACK1){
            return;
        }
        if (this.status == STATUS_NONE){
            // run!
            System.out.println("skill1 ! run");
            log.info("skill1 ! run");
            time = 0f;
            this.trigger = actData;
            this.status = STATUS_RUNNING;
            endFlag = false;
            actData.makeBobIdolForce();
            actData.makeBobAttacking1(maxTime);
            initBox();


        }else {
            System.out.println(this.status+" == status");
            log.info(this.status+" == status");
        }
    }

    private void initBox() {
        this.box[0] = this.holder.box[0]+10;
        this.box[1] = this.holder.box[1]+10;
        this.box[2] = width;
        this.box[3] = height;

    }

    @Override
    public void update(float delta) {
        log.info("skill1 update!");

        move(delta);
        checkTrig();
        time+=delta;
        checkEnd();
    }

    private void checkTrig() {
        // todo check and trig attack logic
    }

    private void checkEnd() {
        log.info("check end");
        if (time>=maxTime || endFlag == true){
            log.info("end");
            end();
        }
    }

    private void move(float delta) {
        this.box[0]+=(5f*delta);
    }

    @Override
    public void end() {
        // refresh
        this.status = STATUS_NONE;
        time = 0f;
        endFlag = true;
        this.trigger = null;
    }

    @Override
    public boolean runningFlag() {
        return status == STATUS_RUNNING;
    }

    @Override
    public IRender getRender() {
        return render;
    }

}
