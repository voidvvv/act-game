package tmp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class TextScreen1 implements Screen {
    BitmapFont font01;
    BitmapFont font02;
    SpriteBatch batch;
    OrthographicCamera camera;
    @Override
    public void show() {
        // batch
        batch = new SpriteBatch();
        // camera
        camera = new OrthographicCamera();
        // font1
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/song_01.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.WHITE;
        StringBuilder chineseChars = new StringBuilder(FreeTypeFontGenerator.DEFAULT_CHARS);
        // append chinese characters from txt file (too many)
        apppendChineseSet(chineseChars);
        System.out.println(chineseChars);
        parameter.characters = chineseChars.toString();
        font01 = generator.generateFont(parameter);
        generator.dispose();
        // font2
        FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("font/song_01.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.color = Color.GREEN;
        StringBuilder chineseChars2 = new StringBuilder(FreeTypeFontGenerator.DEFAULT_CHARS);
        // append a few chinese characters
        chineseChars2.append("你好世界");
        System.out.println(chineseChars2);
        parameter2.characters = chineseChars2.toString();
        font02 = generator2.generateFont(parameter2);
        generator2.dispose();
    }

    private void apppendChineseSet(StringBuilder chineseChars) {
        FileHandle internal1 = Gdx.files.internal("font/ChineseText.txt");
        Reader reader = internal1.reader();
        BufferedReader br = new BufferedReader(reader);
        String s = null;
        while (true){
            try {
                if (!((s = br.readLine())!=null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (s.length()>0){
                chineseChars.append(s);
            }
        }
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        font01.draw(batch,"你好世界libgdx",50,200);
        font02.draw(batch,"你好世界libgdx",50,250);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,width,height);
        camera.update();
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
