package com.mygdx.game.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.MainAsset;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.ActData;
import com.mygdx.game.data.MapData;
import com.mygdx.game.myg2d.MyLight;
import com.mygdx.game.myg2d.MyShaderBatch;

import java.util.Arrays;
import java.util.Iterator;

public class MyMapRender {
    TiledMap map;
    OrthographicCamera camera;

    PerspectiveCamera perspectiveCamera;

    OrthogonalTiledMapRenderer tiledMapRenderer;

    SpriteBatch spriteBatch;

    ShapeRenderer shapeRenderer;

    ActData actData;

    MapData mapData;

    // light
    MyShaderBatch myShaderBatch;
    MyLight myLight;

    BobRender bobRender;



    public MyMapRender(OrthographicCamera camera, MainAsset mainAsset, MyGdxGame myGdxGame) {
        shapeRenderer = new ShapeRenderer();
        map = mainAsset.getBackground();
        mapData = mainAsset.getMapData();
        spriteBatch = mainAsset.getSpriteBatch();
        this.camera =camera;
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        tiledMapRenderer.setView(camera);
        perspectiveCamera = new PerspectiveCamera(73f,200,200);
        actData = mainAsset.getActData();
        dealMap(map);
//        tmp = mainAsset.getKnightIdle()[0][0];
        initLightCover();
//        tiledMapRenderer.getBatch().setShader();
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
        System.out.println("obj01: "+obj01);
        RectangleMapObject tangle = (RectangleMapObject) obj01;
        actData.position.set(tangle.getRectangle().x,tangle.getRectangle().y);
        actData.reset();
    }

    public void render(){
        myLight.position[0] = mapData.lightPosition[0];
        myLight.position[1] = mapData.lightPosition[1];
        shapeRenderer.setProjectionMatrix(camera.combined);

        tiledMapRenderer.setView(camera);

        tiledMapRenderer.render();
        perspectiveCamera.position.x = actData.centre.x;
        perspectiveCamera.position.y = actData.centre.y;

        perspectiveCamera.update();
        Gdx.gl.glDepthMask(true);


        renderCharacter();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(actData.position.x,actData.position.y,3);
        shapeRenderer.end();
        if (mapData.mapLightFlag){
            myShaderBatch.setProjectMetircs(camera.combined);
            myShaderBatch.draw(myLight);
        }

    }

    private void renderCharacter() {


    }


}
