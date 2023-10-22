package com.mygdx.game.manage;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.MapData;
import com.mygdx.game.data.charact.AbstractAnimation;

public class CameraManager {
    OrthographicCamera bobCamera;
    Vector3 lerpTarget = new Vector3();

    public CameraManager() {
        bobCamera = new OrthographicCamera();
    }

    public void resize(int width, int height) {
        bobCamera.setToOrtho(false, width, height);
        bobCamera.update(false);
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
        }else if (bobCamera.position.y>mapData.height-bobCamera.viewportHeight/2){
            bobCamera.position.y = mapData.height-bobCamera.viewportHeight/2;
        }

    }
}
