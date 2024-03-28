package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import tmp.TmpGame;

import java.util.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "哈罗你好";
//		config.fullscreen = true;
//		config.resizable = false;
		config.width = 640;

		config.height = 480;

		config.useGL30 = true;


		new LwjglApplication(MyGdxGame.getInstance(), config);
//		new LwjglApplication(new TmpGame(),config);
	}


}
