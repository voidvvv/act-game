package com.mygdx.game.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.MainAsset;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.AbstractSkill;
import com.mygdx.game.data.MyBob;
import com.mygdx.game.data.PositionData;
import com.mygdx.game.data.enchantress.Skill1;
import com.mygdx.game.utils.MyLocalUtil;

import java.util.Properties;

public class BobRender implements IRender<MyBob>{
    public static final Logger log = new Logger("BobRender",Logger.INFO);
    MyGdxGame myGdxGame;
    MainAsset mainAsset;
    SpriteBatch spriteBatch;

    ShapeRenderer shapeRenderer; // debug
    ObjShadowRender shadowRender;

    Animation<TextureRegion> enchantressIdleRight;
    Animation<TextureRegion> enchantressIdleLeft;

    Animation<TextureRegion> enchantressRunRight;
    Animation<TextureRegion> enchantressRunLeft;

    Animation<TextureRegion> enchantressAttack1Left;
    Animation<TextureRegion> enchantressAttack1Right;

    ShaderProgram shaderProgram;

    float widthRatio;
    float heightRatio;


    public BobRender(MyGdxGame myGdxGame, MainAsset mainAsset) {
        this.myGdxGame = myGdxGame;
        this.mainAsset = mainAsset;

        spriteBatch = mainAsset.getSpriteBatch();
        shapeRenderer = new ShapeRenderer(); // debug

        Properties bobProperties = mainAsset.getBobProperties();
        widthRatio = MyLocalUtil.convertFloat(bobProperties.getProperty("width-ratio"));
        // height-ratio
        heightRatio = MyLocalUtil.convertFloat( bobProperties.getProperty("height-ratio"));
//        shaderProgram = new ShaderProgram(Gdx.files.internal("shader/tmp.vert"),Gdx.files.internal("shader/shadow_normal.frag"));
        shadowRender = mainAsset.getShadowRender();
        TextureRegion[][] enchantressIdle = mainAsset.getEnchantressIdle();
        TextureRegion[][] enchantressRun = mainAsset.getEnchantressRun();
        TextureRegion[][] enchantressAttack1 = mainAsset.getEnchantressAttack1();
        enchantressIdleRight=new Animation<TextureRegion>(0.2f, Array.with(enchantressIdle[0]), Animation.PlayMode.LOOP);
        enchantressIdleLeft=new Animation<TextureRegion>(0.2f,Array.with(enchantressIdle[1]), Animation.PlayMode.LOOP);
        enchantressRunRight=new Animation<TextureRegion>(0.1f,Array.with(enchantressRun[0]), Animation.PlayMode.LOOP);
        enchantressRunLeft=new Animation<TextureRegion>(0.1f,Array.with(enchantressRun[1]), Animation.PlayMode.LOOP);

        enchantressAttack1Left=new Animation<TextureRegion>(Skill1.MAX_DURATION /(float) enchantressAttack1[0].length,Array.with(enchantressAttack1[0]), Animation.PlayMode.LOOP);
        enchantressAttack1Right=new Animation<TextureRegion>(Skill1.MAX_DURATION/(float) enchantressAttack1[1].length,Array.with(enchantressAttack1[1]), Animation.PlayMode.LOOP);

        mainAsset.registRender("bobRender",this);
    }

    Vector3 tmp = new Vector3();
    float[] shadow = new float[4];
    public void renderBob(MyBob myBob){
        fetchAnim(myBob);
        OrthographicCamera bobCamera = MyGdxGame.getGame().getCameraManager().getBobCamera();
        PositionData pos = myBob.pos();
        float width = pos.rectangle.x/widthRatio;
        float height = pos.height/heightRatio;
        int keyFrameIndex = anim.getKeyFrameIndex(myBob.stateTime);
        TextureRegion keyFrame = anim.getKeyFrames()[keyFrameIndex];
        shadow[0] = myBob.pos().getRectangleShape().x ;
        shadow[1] = myBob.pos().getRectangleShape().y - myBob.pos().getRectangleShape().height/2;
        shadow[2] = myBob.pos().getRectangleShape().width;
        shadow[3] = myBob.pos().getRectangleShape().height;
        shadowRender.renderShadow(shadow,bobCamera.combined,keyFrame);
        tmp.set(pos.pos.x, pos.pos.y,0);
//        bobCamera.project(tmp);
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(bobCamera.combined);
        spriteBatch.draw(keyFrame, tmp.x-width/2, tmp.y, width, height);
        spriteBatch.end();

        // render shadow

    }
    Animation<TextureRegion> anim=null;
    protected void fetchAnim(MyBob myBob) {
        if (myBob.status == MyBob.STATUS_IDOL){
            if (myBob.pos().directVect.x<0){
                anim = enchantressIdleLeft;
            }else {
                anim = enchantressIdleRight;
            }
        }
        if (myBob.status == MyBob.STATUS_RUN){
            if (myBob.pos().directVect.x<0){
                anim = enchantressRunLeft;
            }else {
                anim = enchantressRunRight;
            }
        }
        if (myBob.status == MyBob.STATUS_ATTACK1){
            if (myBob.pos().directVect.x<0){
                anim = enchantressAttack1Right;
            }else {
                anim = enchantressAttack1Left;
            }
        }

//        return anim;
//
    }

    @Override
    public void render(MyBob myBob) {
//        renderSkills(myBob);
        renderBob(myBob);
    }
}
