package com.mygdx.game.data.states.goblin;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.data.charact.BossGoblin;


public class InitialState implements State<BossGoblin> {
    Logger logger = new Logger("InitialState",Logger.INFO);

    @Override
    public void enter(BossGoblin entity) {
        // init
        logger.info("enter ! for : " + entity.name());
        entity.stateTime = 0f;
        entity.time = 0f;
    }

    @Override
    public void update(BossGoblin entity) {
        if (entity.stateTime >= 1f) {
            entity.stateMachine.changeState(entity.NORMAL);
        }

    }

    @Override
    public void exit(BossGoblin entity) {
        logger.info("状态结束");
    }

    @Override
    public boolean onMessage(BossGoblin entity, Telegram telegram) {
        return false;
    }
}
