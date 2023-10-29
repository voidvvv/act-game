package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.input.ActInputProcessor;
import com.mygdx.game.input.InputStateData;
import com.mygdx.game.manage.CameraManager;
import com.mygdx.game.render.GameComponentRender;
import com.mygdx.game.screen.ActScreen;
import com.mygdx.game.screen.BeginScreen;

public class MyGdxGame extends Game {
	public static MyGdxGame game;

	private ActScreen actScreen;

	private BeginScreen beginScreen;
	private MainAsset mainAsset;
	private BatchManager batchManager;
	ActInputProcessor actInputProcessor;

	DataManager dataManager;
	public static MyGdxGame getInstance(){
		if (game == null){
			game = new MyGdxGame();
		}
		return game;
	}
	private MyGdxGame() {
		CameraManager cameraManager = new CameraManager();

		setCameraManager(cameraManager);
	}

	@Override
	public void create () {
		init();
		setScreen(beginScreen);
	}

	public DataManager getDataManager() {
		return dataManager;
	}

	GameComponentRender gameComponentRender;

	private InputStateData inputStateData;

	public void init(){
		inputStateData = new InputStateData();
		mainAsset = new MainAsset();
		mainAsset.init();
		actScreen = new ActScreen();
		beginScreen = new BeginScreen();
		batchManager = new BatchManager();
		actInputProcessor = new ActInputProcessor(mainAsset);
		dataManager = new DataManager();
		gameComponentRender = new GameComponentRender();
//		Gdx.input.setInputProcessor(actInputProcessor);


	}

	public InputStateData getInputStateData() {
		return inputStateData;
	}

	public GameComponentRender getGameComponentRender() {
		return gameComponentRender;
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

	public void setGameScreen() {
		setScreen(actScreen);
	}
	CameraManager cameraManager;
	public void setCameraManager(CameraManager cameraManager) {
		this.cameraManager = cameraManager;
	}

	public CameraManager getCameraManager() {
		return cameraManager;
	}

	public void update(float delta) {
		getMainAsset().getCharactorManager().update(delta);
	}
}
