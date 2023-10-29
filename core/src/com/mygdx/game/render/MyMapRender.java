package com.mygdx.game.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MainAsset;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.charact.AbstractAnimation;
import com.mygdx.game.data.MyBob;
import com.mygdx.game.data.MapData;
import com.mygdx.game.manage.CharactorManager;
import com.mygdx.game.myg2d.MyLight;
import com.mygdx.game.myg2d.MyShaderBatch;

import java.util.Iterator;

public class MyMapRender {
    public static final String mapGround01 = "map/pic/back_ocean_cloud_01.png";
    public static final String mapGround02 = "map/pic/back_ocean_cloud_02.png";
    public static final String mapGround03 = "map/pic/back_nature_tree_01.png";;

    TiledMap map;

    PerspectiveCamera perspectiveCamera;

    OrthogonalTiledMapRenderer tiledMapRenderer;

    SpriteBatch spriteBatch;

    ShapeRenderer shapeRenderer;

    MapData mapData;

    // light
    MyShaderBatch myShaderBatch;
    MyLight myLight;

    Texture oceanBackGround;
    Texture oceanBackGround2;

    Texture oceanBackGround3;

    TextureRegion tr ;


//         assetManager.load("map/pic/back_ocean_cloud_01.png",Texture.class);
//        assetManager.load("map/pic/back_ocean_cloud_02.png",Texture.class);
    public MyMapRender(OrthographicCamera camera, MainAsset mainAsset) {
        shapeRenderer = new ShapeRenderer();
        map = mainAsset.getBackground();
        mapData = mainAsset.getMapData();
        spriteBatch = mainAsset.getSpriteBatch();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        perspectiveCamera = new PerspectiveCamera(73f,200,200);
        dealMap(map);
        oceanBackGround = mainAsset.getTexture(mapGround01);
        oceanBackGround2 = mainAsset.getTexture(mapGround02);
        oceanBackGround3 = mainAsset.getTexture(mapGround03);

        tr = new TextureRegion(oceanBackGround);
        tr.flip(false,true);
//        tmp = mainAsset.getKnightIdle()[0][0];
        initLightCover();
    }

    private void initLightCover() {
        myShaderBatch = new MyShaderBatch(mapData.width,mapData.height);
        myLight = new MyLight(20f,50f,20,50);
    }

    private void dealMap(TiledMap map) {
        MapObjects objLayerObjs = map.getLayers().get("obj01").getObjects();
        MapObject obj01 = objLayerObjs.get(0);
        RectangleMapObject tangle = (RectangleMapObject) obj01;
        CharactorManager charactorManager = MyGdxGame.getGame().getMainAsset().getCharactorManager();
        charactorManager.initBobPosition(tangle.getRectangle().x,tangle.getRectangle().y);

        for(int x=1; x<objLayerObjs.getCount(); x++){
            RectangleMapObject mapObject = (RectangleMapObject)(objLayerObjs.get(x));
            Rectangle rectangle = mapObject.getRectangle();
            System.out.println("rectangle -  " + x +" : " + rectangle);
            charactorManager.goblinBirthPlace.add(new float[]{rectangle.x,rectangle.y,rectangle.width,rectangle.height});
        }
    }

    public void render(){
//        myLight.position[0] = mapData.lightPosition[0];
//        myLight.position[1] = mapData.lightPosition[1];
        OrthographicCamera bobCamera = MyGdxGame.getGame().getCameraManager().getBobCamera();

        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(bobCamera.combined);
        spriteBatch.draw(oceanBackGround3,0f,mapData.height*0.85f,mapData.width,mapData.height);
        spriteBatch.end();
//        shapeRenderer.setProjectionMatrix(bobCamera.combined);

        tiledMapRenderer.setView(bobCamera);

        tiledMapRenderer.render();

        renderCharacter();

        if (mapData.mapLightFlag){
            myShaderBatch.setProjectMetircs(bobCamera.combined);
            myShaderBatch.draw(myLight);
        }

    }

    private void drawOrigiPoint() {
        CharactorManager charactorManager = MyGdxGame.getInstance().getMainAsset().getCharactorManager();
        AbstractAnimation myBob = charactorManager.getBob();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(myBob.pos().pos.x, myBob.pos().pos.y,3);
        shapeRenderer.end();
    }

    private void renderCharacter() {
        CharactorManager charactorManager = MyGdxGame.getInstance().getMainAsset().getCharactorManager();
        for(int x=0; x< charactorManager.getActs().size; x++){
            AbstractAnimation anim = charactorManager.getActs().get(x);
            anim.render();
        }
    }

    public static void main(String[] args) {
        Array<String> array = new Array();
        array.add("aa");
        array.add("bb");

        for (String s :array){
            System.out.println(s);
        }

        for (String s :array){
            System.out.println(s);
        }
    }


    public void draw() {
        render();

    }


    public void act(float delta) {
//        myBob.update(delta);
        mapData.update(delta);


    }



}
