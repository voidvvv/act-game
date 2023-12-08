package com.mygdx.game.data.states.goblin;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.data.charact.BossGoblin;

public class DyingState implements State<BossGoblin> {
    Logger logger = new Logger("BossGoblin_DyingState");
    @Override
    public void enter(BossGoblin entity) {
        logger.info(" enter ");
    }

    @Override
    public void update(BossGoblin entity) {
        if ( entity.stateTime > 1f){

            entity.reset();
            entity.beforeDied();
        }
    }

    @Override
    public void exit(BossGoblin entity) {

    }

    @Override
    public boolean onMessage(BossGoblin entity, Telegram telegram) {
        return false;
    }
}
