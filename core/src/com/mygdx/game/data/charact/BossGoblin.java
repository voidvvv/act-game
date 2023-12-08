package com.mygdx.game.data.charact;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.SkillEffect;
import com.mygdx.game.data.enchantress.Skill1Effect;
import com.mygdx.game.data.states.goblin.DyingState;
import com.mygdx.game.data.states.goblin.InitialState;
import com.mygdx.game.data.states.goblin.NormalState;

import java.security.PublicKey;

public class BossGoblin extends AbstractAnimation{
    // static
    public static final State<BossGoblin> INIT = new InitialState() ;
    public static final State<BossGoblin> NORMAL = new NormalState();

    public static final State<BossGoblin> DYING = new DyingState();

    public static final State<BossGoblin> DIED = new NormalState();

//    public static final int DYING = 2;
//    public static final int DIED = 3;

    public final StateMachine<BossGoblin, State<BossGoblin>> stateMachine;

    // props


    public BossGoblin() {
        stateMachine = new DefaultStateMachine<>(this);
    }
    int camp;
    public void init(String name, float x, float y, float width, float height, float lengthZ){

        this.positionData.pos.set(x,y);
        this.positionData.rectangle.set(width,lengthZ);
        this.positionData.height = height;
        camp = 1;
        stateTime = 0;
        time = 0;
        stateMachine.setInitialState(INIT);
        fightProp().init(230,10000);
    }


    @Override
    public void update(float delta) {
        time+=delta;
        stateTime+=delta;
        checkStatus();
    }

    private void checkStatus() {
        stateMachine.update();

    }

    @Override
    public void render() {
        MyGdxGame.getInstance().getMainAsset().goblinBossRender().render(this);
    }

    @Override
    public String name() {
        return "大东西";
    }

    public boolean died() {
        State<BossGoblin> currentState = stateMachine.getCurrentState();
        return currentState == DYING || currentState == DIED;
    }
    @Override
    public int camp() {
        return 1 ;
    }

    @Override
    public void beAttacked(AbstractAnimation anim, SkillEffect skillEffect) {

    }

    @Override
    public void beforeDied() {
        MyGdxGame.getInstance().fail = true;
    }

    @Override
    public void beDamaged(Skill1Effect skill1Effect, float d) {
        super.beDamaged(skill1Effect, d);
        this.fightProp().subHp(d);
        if (this.fightProp().hp <= 0f){
            log.info(name() + " 受到致命伤害，进入dying！");
            State<BossGoblin> currentState = stateMachine.getCurrentState();
            if (currentState!=DYING && currentState!=DIED){
                stateMachine.changeState(DYING);
                stateTime = 0f;
            }
        }
    }

    @Override
    public void makeIdolForce() {

    }

    @Override
    public void makeBobAttacking1() {

    }

    @Override
    public void reset() {
        super.reset();
        MyGdxGame.getInstance().getMainAsset().getCharactorManager().resetGoblin(this);
    }
}
