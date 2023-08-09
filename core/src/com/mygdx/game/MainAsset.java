package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.data.ActData;
import com.mygdx.game.data.MapData;
import com.mygdx.game.render.IRender;
import com.mygdx.game.render.ObjShadowRender;

import java.util.HashMap;
import java.util.Map;

public class MainAsset {
    static final String mapName = "map/test02/downtown-three.tmx";

    SpriteBatch spriteBatch;

    AssetManager assetManager;

    OrthographicCamera bobCamera;

    ShapeRenderer shapeRenderer;

    MapData mapData;

    ActData actData;

    Sprite bob;
    TmxMapLoader tmxMapLoader;

    TiledMap background;

    FileHandleResolver internal;

    ObjShadowRender shadowRender;

    Map<String, IRender<?>> renderMap;

    public MainAsset() {
        shadowRender = new ObjShadowRender();
        this.assetManager = new AssetManager();
        this.spriteBatch = new SpriteBatch();
        this.bobCamera = new OrthographicCamera();
        renderMap = new HashMap<>();
        mapData = new MapData();
        actData = new ActData(30,30,10,10,mapData);
        tmxMapLoader = new TmxMapLoader();
//        this.bobCamera.zoom = -0.5f;
        this.bobCamera.position.z = -1.5f;
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(internal = new InternalFileHandleResolver()));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(internal));
        this.shapeRenderer = new ShapeRenderer();
    }



    public ObjShadowRender getShadowRender(){
        return shadowRender;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public ShapeRenderer getShapRender(){
        return this.shapeRenderer;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void load(){
        assetManager.load("bob.png", Texture.class);
        assetManager.load("character/Enchantress/Idle.png",Texture.class);
        assetManager.load("character/Enchantress/Run.png",Texture.class);
        assetManager.load("character/Enchantress/Attack_1.png",Texture.class);
        assetManager.load("character/Enchantress/Attack_2.png",Texture.class);
        assetManager.load("character/Enchantress/Attack_3.png",Texture.class);
        assetManager.load("character/Enchantress/Attack_4.png",Texture.class);
        assetManager.load("character/Knight/Idle.png",Texture.class);
        assetManager.finishLoading();

        background = tmxMapLoader.load(mapName);

//        actData.initSkill();
        mapData.setAct(actData);
    }

    public void registRender(IRender render){
        renderMap.put(render.getClass().getSimpleName(),render);
    }

    public void registRender(String name,IRender render){
        renderMap.put(name,render);
    }

    public TiledMap getBackground() {
        return background;
    }

    public TextureRegion[][] getEnchantressAttack1(){
        Texture texture = assetManager.get("character/Enchantress/Attack_1.png", Texture.class);
        TextureRegion[] idle = TextureRegion.split(texture, 128, 128)[0];
        resetUV(idle);
        TextureRegion[] idleMirror = TextureRegion.split(texture, 128, 128)[0];

        for(TextureRegion mirror:idleMirror){
            mirror.flip(true,false);
        }
        return new TextureRegion[][]{idle,idleMirror};
    }

    public TextureRegion[][] getEnchantressIdle(){
        Texture texture = assetManager.get("character/Enchantress/Idle.png", Texture.class);
        TextureRegion[] idle = TextureRegion.split(texture, 128, 128)[0];
        resetUV(idle);
        TextureRegion[] idleMirror = TextureRegion.split(texture, 128, 128)[0];

        for(TextureRegion mirror:idleMirror){
            mirror.flip(true,false);
        }
        return new TextureRegion[][]{idle,idleMirror};
    }

    public TextureRegion[][] getKnightIdle(){
        Texture texture = assetManager.get("character/Knight/Idle.png", Texture.class);
        TextureRegion[] idle = TextureRegion.split(texture, 128, 128)[0];
        resetUV(idle);
        TextureRegion[] idleMirror = TextureRegion.split(texture, 128, 128)[0];

        for(TextureRegion mirror:idleMirror){
            mirror.flip(true,false);
        }
        return new TextureRegion[][]{idle,idleMirror};
    }

    private void resetUV(TextureRegion[] idle) {
//        for(TextureRegion tr:idle){
//            tr.setU();
//        }
    }

    public TextureRegion[][] getEnchantressRun(){
        Texture texture = assetManager.get("character/Enchantress/Run.png", Texture.class);
        TextureRegion[] idle = TextureRegion.split(texture, 128, 128)[0];
        TextureRegion[] idleMirror = TextureRegion.split(texture, 128, 128)[0];

        for(TextureRegion mirror:idleMirror){
            mirror.flip(true,false);
        }
        return new TextureRegion[][]{idle,idleMirror};
    }

    public MapData getMapData() {
        return mapData;
    }

    public TmxMapLoader getTmxMapLoader() {
        return tmxMapLoader;
    }

    public OrthographicCamera getBobCamera() {
        return bobCamera;
    }

    public ActData getActData() {
        return actData;
    }
}
