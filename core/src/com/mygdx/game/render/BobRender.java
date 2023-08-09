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
import com.mygdx.game.data.AbstractSkill;
import com.mygdx.game.data.ActData;

import java.util.List;

public class BobRender implements IRender<ActData>{
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

    Array<ActData> actDataList;

    public BobRender(MyGdxGame myGdxGame, MainAsset mainAsset) {
        this.myGdxGame = myGdxGame;
        this.mainAsset = mainAsset;
        bobCamera = mainAsset.getBobCamera();
        spriteBatch = mainAsset.getSpriteBatch();
        shapeRenderer = new ShapeRenderer(); // debug
        actDataList = new Array<>();

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

        mainAsset.registRender(this);
    }


    public void renderCharacter(ActData actData){
        fetchAnim(actData);
        int keyFrameIndex = anim.getKeyFrameIndex(actData.stateTime);
        TextureRegion keyFrame = anim.getKeyFrames()[keyFrameIndex];
        shadowRender.renderShadow(actData.shadowBox,bobCamera.combined,keyFrame);

        spriteBatch.begin();

        spriteBatch.draw(keyFrame,actData.position.x,actData.position.y,actData.width,actData.height);
        spriteBatch.end();

        // render shadow

    }
    Animation<TextureRegion> anim=null;
    private void fetchAnim(ActData actData) {
        if (actData.status == ActData.STATUS_IDOL){
            if (actData.directVect.x<0){
                anim = enchantressIdleLeft;
            }else {
                anim = enchantressIdleRight;
            }
        }
        if (actData.status == ActData.STATUS_RUN){
            if (actData.directVect.x<0){
                anim = enchantressRunLeft;
            }else {
                anim = enchantressRunRight;
            }
        }

        if (actData.status == ActData.STATUS_ATTACK1){
            if (actData.directVect.x<0){
                anim = enchantressAttack1Right;
            }else {
                anim = enchantressAttack1Left;
            }
        }

//        return anim;
//
    }


    private void renderSkills(ActData actData) {
        for (AbstractSkill skill : actData.skills) {
            skill.getRender().render(skill);
        }

    }

    @Override
    public void render(ActData actData) {
        renderSkills(actData);
        renderCharacter(actData);
    }
}
