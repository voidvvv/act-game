package com.mygdx.game.listener;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.data.MyBob;

public class ActInputListener extends InputListener {
    MyBob myBob;

    public ActInputListener(MyBob myBob) {
        this.myBob = myBob;
    }


    /** Called when a key goes down. When true is returned, the event is {@link Event#handle() handled}. */
    public boolean keyDown (InputEvent event, int keycode) {
        this.myBob.useSkill(0);
        return false;
    }

    /** Called when a key goes up. When true is returned, the event is {@link Event#handle() handled}. */
    public boolean keyUp (InputEvent event, int keycode) {
        return false;
    }

    /** Called when a key is typed. When true is returned, the event is {@link Event#handle() handled}.
     * @param character May be 0 for key typed events that don't map to a character (ctrl, shift, etc). */
    public boolean keyTyped (InputEvent event, char character) {
        return false;
    }

}
