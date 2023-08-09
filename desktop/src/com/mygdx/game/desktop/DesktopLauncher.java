package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import tmp.TmpGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.fullscreen = true;
//		config.resizable = false;
		new LwjglApplication(MyGdxGame.getInstance(), config);
//		new LwjglApplication(new TmpGame(),config);
	}
}
