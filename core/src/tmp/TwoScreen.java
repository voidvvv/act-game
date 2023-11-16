package tmp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class TwoScreen implements Screen {
    SwordmanRender swordmanRender;

    public TwoScreen() {
        swordmanRender = new SwordmanRender();
    }

    @Override
    public void show() {
        swordmanRender.init();

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        swordmanRender.render();

        update(delta);
    }

    private void update(float delta) {
        swordmanRender.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        swordmanRender.resize(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
