package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.data.MyBob;
import com.mygdx.game.data.MapData;
import com.mygdx.game.manage.CharactorManager;
import com.mygdx.game.manage.MusicManager;
import com.mygdx.game.manage.TextManager;
import com.mygdx.game.render.*;
import com.mygdx.game.render.character.GoblinBossRender;
import com.mygdx.game.render.character.GoblinRender;
import com.mygdx.game.render.enchantress.Skill1EffectRender;
import com.mygdx.game.render.enchantress.Skill1Render;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
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

    MusicManager musicManager;

    ObjShadowRender shadowRender;
    Skill1EffectRender skill1EffectRender;

    Map<String, IRender<?>> renderMap;
    BitmapFont bitmapFont;
    BitmapFont ttfBitmapFont;

    TextManager textManager;

    CharactorManager charactorManager;

    UIRender uiRender;

    Map<Integer, BitmapFont> fontSizeMap;

    public TextManager getTextRender() {
        return textManager;
    }

    public MainAsset() {
        fontSizeMap = new HashMap<>();
        uiRender = new UIRender();
        bobProperties = new Properties();
        charactorManager = new CharactorManager();
        shadowRender = new ObjShadowRender();
        this.assetManager = new AssetManager();
        this.spriteBatch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();

        bobRender = new BobRender();
        textManager = new TextManager();
//        initRenderMap();
        tmxMapLoader = new TmxMapLoader();
        renderMap = new HashMap<>();
        myMapRender = new MyMapRender();
        mapData = new MapData();
        goblinBossRender = new GoblinBossRender();
        newFont();
    }

    private void newFont() {
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(internal));
        bitmapFont = new BitmapFont();

//        assetManager.load("font/song_01.ttf",BitmapFont.class,param);
//        assetManager.finishLoading();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/song_01.ttf"));
        // C:\Windows\Fonts\BASKVILL.TTF
//        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.absolute("C:\\Windows\\Fonts\\simsunb.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        StringBuilder chineseChars = new StringBuilder(FreeTypeFontGenerator.DEFAULT_CHARS);
        apppendChineseSet(chineseChars);
//        chineseChars.append("这是默认中文");
        parameter.characters = chineseChars.toString();
        for (int x = 50; x < 60; x++) {
            parameter.size = x; // 设置字体大小
            BitmapFont bitmapFont1 = generator.generateFont(parameter);
            fontSizeMap.put(x, bitmapFont1);
        }
        parameter.size = 140; // 设置字体大小
        BitmapFont bitmapFontTmp = generator.generateFont(parameter);
        fontSizeMap.put(140, bitmapFontTmp);
        generator.dispose(); // 释放资源


    }

    public Map<Integer, BitmapFont> getFontSizeMap() {
        return fontSizeMap;
    }

    private void apppendChineseSet(StringBuilder chineseChars) {
        FileHandle internal1 = Gdx.files.internal("font/ChineseText.txt");
        Reader reader = internal1.reader();
        BufferedReader br = new BufferedReader(reader);
        String s = null;
        while (true) {
            try {
                if (!((s = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (s.length() > 0) {
                chineseChars.append(s);
            }
        }
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BitmapFont getTtfBitmapFont() {
        return ttfBitmapFont;
    }

    public CharactorManager getCharactorManager() {
        return charactorManager;
    }

    public void init() {
        shadowRender.init();
//        this.bobCamera.zoom = -0.5f;
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(internal = new InternalFileHandleResolver()));
        this.shapeRenderer.setAutoShapeType(true);

        textManager.init();
        load();
        musicManager = new MusicManager(this);
        bobRender.init(MyGdxGame.getGame(), this);
        uiRender.init();
        goblinBossRender.init();
    }

    public MusicManager getMusicManager() {
        return musicManager;
    }

    BobRender bobRender;

    public Skill1EffectRender getSkill1EffectRender() {
        if (skill1EffectRender == null) {
            skill1EffectRender = new Skill1EffectRender();
        }
        return skill1EffectRender;
    }

    public Skill1Render getSkill1Render() {
        if (skill1Render == null) {
            skill1Render = new Skill1Render(this);
        }
        return skill1Render;
    }

    public BobRender getBobRender() {

        return bobRender;
    }

    public ObjShadowRender getShadowRender() {
        return shadowRender;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public ShapeRenderer getLineShapeRender() {
        return this.shapeRenderer;
    }

    public ShapeRenderer getFilledShapeRender() {
        return this.shapeRenderer;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    Properties bobProperties;

    public Properties getBobProperties() {
        return bobProperties;
    }

    public void load() {
        try {
            FileHandle internal1 = Gdx.files.internal("props/bob.properties");

            bobProperties.load(internal1.read());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        assetManager.load("bob.png", Texture.class);
        assetManager.load("character/Enchantress/Idle.png", Texture.class);
        assetManager.load("character/Enchantress/Run.png", Texture.class);
        assetManager.load("character/Enchantress/Attack_1.png", Texture.class);
        assetManager.load("character/Enchantress/Attack_2.png", Texture.class);
        assetManager.load("character/Enchantress/Attack_3.png", Texture.class);
        assetManager.load("character/Enchantress/Attack_4.png", Texture.class);
        assetManager.load("profile/enhancer_profile.png", Texture.class);
        assetManager.load("character/Knight/Idle.png", Texture.class);
        assetManager.load("music/xiang_tai_moon_insect.mp3", Music.class);
        assetManager.load(MyMapRender.mapGround01, Texture.class);
        assetManager.load(MyMapRender.mapGround02, Texture.class);
        assetManager.load(MyMapRender.mapGround03, Texture.class);
        assetManager.finishLoading();

        background = tmxMapLoader.load(mapName);


        myMapRender.init(null, this);

    }

    public MyMapRender myMapRender;

    public void registRender(IRender render) {
        renderMap.put(render.getClass().getSimpleName(), render);
    }

    public void registRender(String name, IRender render) {
        renderMap.put(name, render);
    }

    public TiledMap getBackground() {
        return background;
    }

    public Texture getTexture(String textureName) {
        return assetManager.get(textureName, Texture.class);
    }

    public TextureRegion[][] getEnchantressAttack1() {
        Texture texture = assetManager.get("character/Enchantress/Attack_1.png", Texture.class);
        TextureRegion[] idle = TextureRegion.split(texture, 128, 128)[0];
        resetUV(idle);
        TextureRegion[] idleMirror = TextureRegion.split(texture, 128, 128)[0];

        for (TextureRegion mirror : idleMirror) {
            mirror.flip(true, false);
        }
        return new TextureRegion[][]{idle, idleMirror};
    }

    public TextureRegion profile01() {
        // enhancer_profile.png
        Texture texture = assetManager.get("profile/enhancer_profile.png", Texture.class);

        return new TextureRegion(texture);
    }

    public TextureRegion[][] getEnchantressIdle() {
        Texture texture = assetManager.get("character/Enchantress/Idle.png", Texture.class);
        TextureRegion[] idle = TextureRegion.split(texture, 128, 128)[0];
        resetUV(idle);
        TextureRegion[] idleMirror = TextureRegion.split(texture, 128, 128)[0];

        for (TextureRegion mirror : idleMirror) {
            mirror.flip(true, false);
        }
        return new TextureRegion[][]{idle, idleMirror};
    }

    public TextureRegion[][] getKnightIdle() {
        Texture texture = assetManager.get("character/Knight/Idle.png", Texture.class);
        TextureRegion[] idle = TextureRegion.split(texture, 128, 128)[0];
        resetUV(idle);
        TextureRegion[] idleMirror = TextureRegion.split(texture, 128, 128)[0];

        for (TextureRegion mirror : idleMirror) {
            mirror.flip(true, false);
        }
        return new TextureRegion[][]{idle, idleMirror};
    }

    private void resetUV(TextureRegion[] idle) {
//        for(TextureRegion tr:idle){
//            tr.setU();
//        }
    }

    public TextureRegion[][] getEnchantressRun() {
        Texture texture = assetManager.get("character/Enchantress/Run.png", Texture.class);
        TextureRegion[] idle = TextureRegion.split(texture, 128, 128)[0];
        TextureRegion[] idleMirror = TextureRegion.split(texture, 128, 128)[0];

        for (TextureRegion mirror : idleMirror) {
            mirror.flip(true, false);
        }
        return new TextureRegion[][]{idle, idleMirror};
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
        if (goblinRender == null) {
            goblinRender = new GoblinRender();
        }
        return goblinRender;
    }


    public BitmapFont getDefaultFont() {
        return bitmapFont;
    }

    public UIRender getUiRender() {
        return uiRender;
    }

    public Music getMusic(String s) {
        return assetManager.get(s, Music.class);
    }

    GoblinBossRender goblinBossRender;

    public GoblinBossRender goblinBossRender() {
        return this.goblinBossRender;
    }
}
