package com.mygdx.game.data.states.goblin;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.data.charact.BossGoblin;

public class NormalState implements State<BossGoblin> {
    Logger logger = new Logger("NormalState",Logger.INFO);
    @Override
    public void enter(BossGoblin entity) {
        logger.info("enter ! for : " + entity.name());
        entity.stateTime = 0f;
    }

    @Override
    public void update(BossGoblin entity) {
//        logger.info("update !");
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
