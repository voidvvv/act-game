package tmp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class SwordmanRender {

    OrthographicCamera camera;
    SpriteBatch spriteBatch;

    ShapeRenderer shapeRenderer;

    Animation<SwordsmanFrame> swordsmanFrameAnimation;

    float time = 0f;

    public void init(){
        Array<SwordsmanFrame> frames = new Array<>();
        for(int x=0; x<242;x++){
            SwordsmanFrame swordsmanFrame = new SwordsmanFrame(x);
            swordsmanFrame.init();
            frames.add(swordsmanFrame);
        }
        swordsmanFrameAnimation = new Animation<>(0.1f,frames, Animation.PlayMode.LOOP);
        camera = new OrthographicCamera();
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
    }


    public void render() {
//        spriteBatch.begin();
//        spriteBatch.setProjectionMatrix(camera.combined);
        SwordsmanFrame keyFrame = swordsmanFrameAnimation.getKeyFrame(time);
        keyFrame.render(spriteBatch,camera);
        keyFrame.renderDebug(shapeRenderer,camera);
//        spriteBatch.end();
    }

    public void update(float delta) {
        time+=delta;
        if (Gdx.input.isTouched()){
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

        }
    }

    public void resize(int width, int height) {
        camera.setToOrtho(true,width,height);
        camera.update();
    }
}
