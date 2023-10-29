package com.mygdx.game.data.enchantress;

import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.charact.AbstractAnimation;
import com.mygdx.game.data.AbstractSkill;
import com.mygdx.game.data.MyBob;

public class Skill1 extends AbstractSkill {
    public static float MAX_DURATION = 0.5F;
    public static float CHECK_PERCENT = 0.5f;
    public static final Logger log = new Logger("Skill1",Logger.INFO);
    public int status = STATUS_NONE;

    private Skill1Effect effect;

    public float time = 0;

    public float maxTime;

    public AbstractAnimation holder; // current holder

    public AbstractAnimation trigger; // current trig, most time equals holder

    public float width = 20;
    public float height = 20;
    public final float[] box = new float[4]; // x, y, width, height

    public boolean endFlag = true;

    public Skill1(float maxTime, AbstractAnimation holder) {
        this.maxTime = maxTime;
        this.holder = holder;
//        this.mapData = mapData;
        effect = new Skill1Effect();
    }

    @Override
    public void apply(AbstractAnimation myBob) {

        if (myBob.status == MyBob.STATUS_ATTACK1){
            return;
        }
        if (this.status == STATUS_NONE){
            // run!
            log.info("skill1 ! run");
            time = 0f;
            this.trigger = myBob;
            this.status = STATUS_RUNNING;
            endFlag = false;
            myBob.makeIdolForce();
            myBob.makeBobAttacking1();
            initBox();

            this.endFlag =false;
        }else {
            System.out.println(this.status+" == status");
            log.info(this.status+" == status");
        }
    }

    private void initBox() {
//        this.box[0] = this.holder.box[0]+10;
//        this.box[1] = this.holder.box[1]+10;
        this.box[2] = width;
        this.box[3] = height;

    }

    @Override
    public void update(float delta) {
//        log.info("skill1 update!");
        if(endFlag){
            return;
        }
        move(delta);
        checkTrig(delta);

        time+=delta;
        checkEnd();
    }

    @Override
    public void render() {
        MyGdxGame.getInstance().getMainAsset().getSkill1Render().render(this);
        effect.render();
    }

    private void checkTrig(float delta) {
        // todo check and trig attack logic
        if (this.time>= MAX_DURATION*CHECK_PERCENT){
            if (effect.status == Skill1Effect.Disable){
                effect.init(this.holder);
            }
            effect.update(delta);
        }
    }

    private void checkEnd() {
//        log.info("check end");
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
        effect.reset();
    }

    @Override
    public boolean runningFlag() {
        return status == STATUS_RUNNING;
    }


}
