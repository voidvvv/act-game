package com.mygdx.game.manage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.MapData;
import com.mygdx.game.data.charact.AbstractAnimation;

public class CameraManager {
    OrthographicCamera bobCamera;
    OrthographicCamera screenCamera;
    Vector3 lerpTarget = new Vector3();

    public CameraManager() {
        screenCamera = new OrthographicCamera();
        bobCamera = new OrthographicCamera();
    }
    Vector3 tmpv3 = new Vector3();
    public void resize(int width, int height) {
        tmpv3.set(bobCamera.position);
        bobCamera.setToOrtho(false, 200, 200);
        bobCamera.update(false);
        bobCamera.position.set(tmpv3);

        tmpv3.set(screenCamera.position);
        screenCamera.setToOrtho(false,600,600);
        screenCamera.update(false);
        screenCamera.position.set(tmpv3);
    }

    public OrthographicCamera getScreenCamera() {
        return screenCamera;
    }

    public OrthographicCamera getBobCamera(){
        return bobCamera;
    }

    public void update(float delta){
        CharactorManager charactorManager = MyGdxGame.getGame().getMainAsset().getCharactorManager();
        AbstractAnimation myBob = charactorManager.getBob();
        lerpTarget.set(myBob.pos().posCenter,0);
//        bobCamera.project(lerpTarget);
        bobCamera.position.lerp(lerpTarget,2.5f*delta);
        fixCameraPosition();
        bobCamera.update();
    }

    private void fixCameraPosition() {
        MapData mapData = MyGdxGame.getGame().getMainAsset().getMapData();
        if (bobCamera.position.x<bobCamera.viewportWidth/2){
            bobCamera.position.x =bobCamera.viewportWidth/2;
        }else if (bobCamera.position.x>mapData.width-bobCamera.viewportWidth/2){
            bobCamera.position.x = mapData.width-bobCamera.viewportWidth/2;
        }
//        if (bobCamera.position.y)
        if (bobCamera.position.y<bobCamera.viewportHeight/2){
            bobCamera.position.y =bobCamera.viewportHeight/2;
        }
    }

    public Vector3 convertCoordinate(Vector3 v3, Camera c1, Camera c2){
        c1.project(v3);
        v3.y = Gdx.graphics.getHeight()-v3.y;
        c2.unproject(v3);
        return v3;
    }

     Vector3 tmp = new Vector3();

    public Vector2 convertCoordinate(Vector2 v2, Camera c1, Camera c2){
        c1.project(tmp.set(v2,0));
        tmp.y = Gdx.graphics.getHeight()-tmp.y;
        c2.unproject(tmp);
        return v2.set(tmp.x,tmp.y);
    }
}
