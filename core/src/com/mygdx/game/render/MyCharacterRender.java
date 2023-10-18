package com.mygdx.game.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MainAsset;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.manage.Character;
import com.mygdx.game.manage.CharactorManager;

public class MyCharacterRender {
    MyGdxGame myGdxGame;
    MainAsset mainAsset;
    SpriteBatch spriteBatch;

    ShapeRenderer shapeRenderer; // debug

    OrthographicCamera bobCamera;

    ObjShadowRender shadowRender;

    Animation<TextureRegion> enchantressIdleRight;
    Animation<TextureRegion> enchantressIdleLeft;

    Animation<TextureRegion> enchantressRunRight;
    Animation<TextureRegion> enchantressRunLeft;

    Animation<TextureRegion> enchantressAttack1Left;
    Animation<TextureRegion> enchantressAttack1Right;

    ShaderProgram shaderProgram;

    CharactorManager charactorManager;


    public MyCharacterRender(MyGdxGame myGdxGame, MainAsset mainAsset) {
        this.myGdxGame = myGdxGame;
        this.mainAsset = mainAsset;
        bobCamera = mainAsset.getBobCamera();
        spriteBatch = mainAsset.getSpriteBatch();
        shapeRenderer = new ShapeRenderer(); // debug
        charactorManager = myGdxGame.getDataManager().charactorManager;
        shaderProgram = new ShaderProgram(Gdx.files.internal("shader/tmp.vert"),Gdx.files.internal("shader/shadow_normal.frag"));
        shadowRender = mainAsset.getShadowRender();
        TextureRegion[][] enchantressIdle = mainAsset.getEnchantressIdle();
        TextureRegion[][] enchantressRun = mainAsset.getEnchantressRun();
        TextureRegion[][] enchantressAttack1 = mainAsset.getEnchantressAttack1();
        enchantressIdleRight=new Animation<TextureRegion>(0.2f, Array.with(enchantressIdle[0]), Animation.PlayMode.LOOP);
        enchantressIdleLeft=new Animation<TextureRegion>(0.2f,Array.with(enchantressIdle[1]), Animation.PlayMode.LOOP);
        enchantressRunRight=new Animation<TextureRegion>(0.1f,Array.with(enchantressRun[0]), Animation.PlayMode.LOOP);
        enchantressRunLeft=new Animation<TextureRegion>(0.1f,Array.with(enchantressRun[1]), Animation.PlayMode.LOOP);

        enchantressAttack1Left=new Animation<TextureRegion>(0.5f/(float) enchantressAttack1[0].length,Array.with(enchantressAttack1[0]), Animation.PlayMode.LOOP);
        enchantressAttack1Right=new Animation<TextureRegion>(0.5f/(float) enchantressAttack1[1].length,Array.with(enchantressAttack1[1]), Animation.PlayMode.LOOP);
    }

    public void render(){
        for (Character character : charactorManager.characters) {
            renderCharacter(character);
        }

    }

    private void renderCharacter(Character character) {

    }
}
