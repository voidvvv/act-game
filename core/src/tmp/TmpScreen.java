package tmp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class TmpScreen implements Screen {
    OrthographicCamera camera;
    Mesh mesh;

    ShaderProgram shaderProgram;
    ShaderProgram shaderProgram2;
    Texture texture;

    float[] meshVertices = new float[] {
            0,0, 0, 0, 0, 0, 1, 0f, 0f,
            10f, 0f, -0.4f, 0, 0, 0, 1, 1, 0,
            10f, 10, -0.3f, 0, 0, 0, 1, 1, 1,
            0f, 10, -0.1f, 0, 0, 0, 1, 0, 1,
    };
    @Override
    public void show() {
        texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        String vertexShader = "attribute vec4 a_position;\n" +
                "attribute vec4 a_color;\n" +
                "attribute vec2 a_texCoord0;\n" +
                "uniform mat4 u_projTrans;\n" +
                "varying vec4 v_color;\n" +
                "varying vec2 v_texCoords;\n" +
                "void main() {\n" +
                "v_color = a_color;\n" +
                "v_texCoords = a_texCoord0;\n" +
                "gl_Position =  u_projTrans * a_position;\n" +
                "}";
        String fragmentShader = "#ifdef GL_ES\n" +
                "precision mediump float;\n" +
                "#endif\n" +
                "varying vec4 v_color;\n" +
                "varying vec2 v_texCoords;\n" +
                "uniform sampler2D u_texture;\n" +
                "void main() {\n" +
                "gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n" +
                "}";
        camera = new OrthographicCamera();
        mesh = new Mesh(true, 4, 6, VertexAttribute.Position(), VertexAttribute.ColorUnpacked(), VertexAttribute.TexCoords(0));
        mesh.setVertices(meshVertices);
        mesh.setIndices(new short[] {2,1,0, 0,3,2,});
        shaderProgram = new ShaderProgram(Gdx.files.internal("test.vert"), Gdx.files.internal("test.frag"));
//        shaderProgram = new ShaderProgram(vertexShader,fragmentShader);

        shaderProgram2 = new ShaderProgram(Gdx.files.internal("test.vert"), Gdx.files.internal("test2.frag"));



    }
    float t = 0.0f;
    Vector3 v3 = new Vector3();
    float[] v3f = new float[3];


    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.GRAY);
        texture.bind();

        shaderProgram.bind();
//        Gdx.gl.glAttachShader();

        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            meshVertices[0]+=(delta);
            mesh.setVertices(meshVertices);
        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            meshVertices[0]-=(delta);
            mesh.setVertices(meshVertices);
        }
//        System.out.println(x);
        t+=delta;

        shaderProgram.setUniformf("iTime",t);
//        shaderProgram.setUniform3fv("iResolution",v3f,0,3);


        shaderProgram.setUniformMatrix("u_projTrans",camera.combined);

        shaderProgram.setUniformi("u_texture", 0);
//        shaderProgram.setUniform4fv("",Color.RED.);
//        shaderProgram.setUniformf("iTime",t);
//        shaderProgram.setUniform3fv("iResolution",v3f,0,3);


        mesh.render(shaderProgram, GL20.GL_TRIANGLES);
//        System.out.println(" = ==");
        shaderProgram2.bind();
                Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                shaderProgram2.setUniformMatrix("u_projTrans",camera.combined);

        mesh.render(shaderProgram2, GL20.GL_TRIANGLES);
                Gdx.gl.glDisable(GL20.GL_BLEND);


    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,20,20);
        camera.position.set(5,5,0);
        camera.update();
        v3f[0] = width;
        v3f[1] = height;


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
