package com.mygdx.game;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.data.MyBob;
import com.mygdx.game.data.MapData;
import com.mygdx.game.manage.CharactorManager;
import com.mygdx.game.render.*;
import com.mygdx.game.render.character.GoblinRender;
import com.mygdx.game.render.enchantress.Skill1EffectRender;
import com.mygdx.game.render.enchantress.Skill1Render;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MainAsset {
    static final String mapName = "map/test02/downtown-three.tmx";

    SpriteBatch spriteBatch;

    AssetManager assetManager;

    ShapeRenderer shapeRenderer;

    Skill1Render skill1Render;

    MapData mapData;

    MyBob myBob;

    TmxMapLoader tmxMapLoader;

    TiledMap background;

    FileHandleResolver internal;

    ObjShadowRender shadowRender;
    Skill1EffectRender skill1EffectRender;

    Map<String, IRender<?>> renderMap;
    BitmapFont bitmapFont;

    TextRender textRender;

    CharactorManager charactorManager;

    public TextRender getTextRender() {
        return textRender;
    }

    public MainAsset() {
        bobProperties = new Properties();

    }

    public CharactorManager getCharactorManager() {
        return charactorManager;
    }

    public void init() {
        charactorManager = new CharactorManager();
        shadowRender = new ObjShadowRender();
        this.assetManager = new AssetManager();
        this.spriteBatch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        textRender = new TextRender();
//        initRenderMap();
        tmxMapLoader = new TmxMapLoader();
//        this.bobCamera.zoom = -0.5f;
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(internal = new InternalFileHandleResolver()));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(internal));
        renderMap = new HashMap<>();

        bitmapFont = new BitmapFont();
        textRender.init();
        load();
    }

    BobRender bobRender ;

    public Skill1EffectRender getSkill1EffectRender() {
        if (skill1EffectRender == null){
            skill1EffectRender = new Skill1EffectRender();
        }
        return skill1EffectRender;
    }

    public Skill1Render getSkill1Render() {
        if (skill1Render == null){
            skill1Render = new Skill1Render(this);
        }
        return skill1Render;
    }

    public BobRender getBobRender() {
        if (bobRender == null){
            bobRender = new BobRender(MyGdxGame.getGame(),this);
        }
        return bobRender;
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

    Properties bobProperties;

    public Properties getBobProperties() {
        return bobProperties;
    }

    public void load()  {
        try {
            FileHandle internal1 = Gdx.files.internal("props/bob.properties");

            bobProperties.load(internal1.read());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        assetManager.load("bob.png", Texture.class);
        assetManager.load("character/Enchantress/Idle.png",Texture.class);
        assetManager.load("character/Enchantress/Run.png",Texture.class);
        assetManager.load("character/Enchantress/Attack_1.png",Texture.class);
        assetManager.load("character/Enchantress/Attack_2.png",Texture.class);
        assetManager.load("character/Enchantress/Attack_3.png",Texture.class);
        assetManager.load("character/Enchantress/Attack_4.png",Texture.class);
        assetManager.load("character/Knight/Idle.png",Texture.class);
        assetManager.load(MyMapRender.mapGround01,Texture.class);
        assetManager.load(MyMapRender.mapGround02,Texture.class);
        assetManager.load(MyMapRender.mapGround03,Texture.class);
        assetManager.finishLoading();

        background = tmxMapLoader.load(mapName);

        mapData = new MapData();
        myMapRender = new MyMapRender(null,this);

    }
    public MyMapRender myMapRender;

    public void registRender(IRender render){
        renderMap.put(render.getClass().getSimpleName(),render);
    }

    public void registRender(String name,IRender render){
        renderMap.put(name,render);
    }

    public TiledMap getBackground() {
        return background;
    }

    public Texture getTexture(String textureName){
        return assetManager.get(textureName,Texture.class);
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

    public MyBob getActData() {
        return myBob;
    }

    GoblinRender goblinRender;
    public GoblinRender getGoblinRender() {
        if (goblinRender == null){
            goblinRender = new GoblinRender();
        }
        return goblinRender;
    }


    public BitmapFont getDefaultFont() {
        return bitmapFont;
    }

}
