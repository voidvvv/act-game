package com.mygdx.game.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MainAsset;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.charact.AbstractAnimation;
import com.mygdx.game.data.MyBob;
import com.mygdx.game.data.MapData;
import com.mygdx.game.myg2d.MyLight;
import com.mygdx.game.myg2d.MyShaderBatch;

import java.util.Iterator;

public class MyMapRender {
    TiledMap map;

    PerspectiveCamera perspectiveCamera;

    OrthogonalTiledMapRenderer tiledMapRenderer;

    SpriteBatch spriteBatch;

    ShapeRenderer shapeRenderer;

    Array<AbstractAnimation> acts;

    MyBob myBob;

    MapData mapData;

    // light
    MyShaderBatch myShaderBatch;
    MyLight myLight;



    public MyMapRender(OrthographicCamera camera, MainAsset mainAsset) {
        shapeRenderer = new ShapeRenderer();
        map = mainAsset.getBackground();
        mapData = mainAsset.getMapData();
        acts = mainAsset.getCharactorManager().getActs();
        spriteBatch = mainAsset.getSpriteBatch();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

        perspectiveCamera = new PerspectiveCamera(73f,200,200);
        myBob = mainAsset.getActData();
        dealMap(map);
//        tmp = mainAsset.getKnightIdle()[0][0];
        initLightCover();
    }

    private void initLightCover() {
        myShaderBatch = new MyShaderBatch(mapData.width,mapData.height);
        myLight = new MyLight(20f,50f,20,50);
    }

    private void dealMap(TiledMap map) {
        MapLayers layers = map.getLayers();
        TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer) layers.get("pics01");

        MapProperties properties = tiledMapTileLayer.getProperties();
        Iterator<String> keys = properties.getKeys();
        while (keys.hasNext()){
            String next = keys.next();

            System.out.println(next+" == "+properties.get(next));
        }

        MapObject obj01 = map.getLayers().get("obj01").getObjects().get(0);
        RectangleMapObject tangle = (RectangleMapObject) obj01;
        MyGdxGame.getGame().getMainAsset().getCharactorManager().resetBob(tangle.getRectangle().x,tangle.getRectangle().y);

    }

    public void render(){
//        myLight.position[0] = mapData.lightPosition[0];
//        myLight.position[1] = mapData.lightPosition[1];
        OrthographicCamera bobCamera = MyGdxGame.getGame().getCameraManager().getBobCamera();
        shapeRenderer.setProjectionMatrix(bobCamera.combined);

        tiledMapRenderer.setView(bobCamera);

        tiledMapRenderer.render();

        Gdx.gl.glDepthMask(true);


        renderCharacter();

//        drawOrigiPoint();
        if (mapData.mapLightFlag){
            myShaderBatch.setProjectMetircs(bobCamera.combined);
            myShaderBatch.draw(myLight);
        }

    }

    private void drawOrigiPoint() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(myBob.pos().pos.x, myBob.pos().pos.y,3);
        shapeRenderer.end();
    }

    private void renderCharacter() {
        for(AbstractAnimation anim:acts){
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
