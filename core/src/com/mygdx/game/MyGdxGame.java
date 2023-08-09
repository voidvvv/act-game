package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.input.ActInputProcessor;
import com.mygdx.game.screen.ActScreen;

public class MyGdxGame extends Game {
	public static MyGdxGame game;

	private ActScreen actScreen;
	private MainAsset mainAsset;
	private BatchManager batchManager;
	ActInputProcessor actInputProcessor;
	public static MyGdxGame getInstance(){
		if (game == null){
			game = new MyGdxGame();
		}
		return game;
	}
	private MyGdxGame() {

	}

	@Override
	public void create () {
		init();
		setScreen(actScreen);
	}

	public void init(){
		mainAsset = new MainAsset();
		mainAsset.load();
		actScreen = new ActScreen();
		batchManager = new BatchManager();
		actInputProcessor = new ActInputProcessor(mainAsset);
		Gdx.input.setInputProcessor(actInputProcessor);
	}

	public static MyGdxGame getGame() {
		return game;
	}

	public ActScreen getActScreen() {
		return actScreen;
	}

	public BatchManager getBatchManager() {
		return batchManager;
	}

	public ActInputProcessor getActInputProcessor() {
		return actInputProcessor;
	}

	public MainAsset getMainAsset() {
		return mainAsset;
	}
}
