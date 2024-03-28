package tmp;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Calendar;

public class TestMain {
    public static void main(String[] args) {
        StringBuilder afterStr = new StringBuilder();
//        afterStr.toString().subst
        Texture pic = new Texture(Gdx.files.internal("图片位置")); // 获取图片
        Music music = Gdx.audio.newMusic(Gdx.files.internal("音乐文件位置")) ;// 获取长音乐;
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("音乐文件位置")) ;// 获取短音效;
    }
}
