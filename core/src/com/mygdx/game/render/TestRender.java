package com.mygdx.game.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MainAsset;

public class TestRender {
    private PerspectiveCamera camera;
    private ModelBatch modelBatch;
    private ModelInstance modelInstance;
    private ShaderProgram shader;

    private Environment environment;;


    public void create() {
        // 创建透视投影相机
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0f, 0f, 10f);
        camera.lookAt(0f, 0f, 0f);
        camera.near = 0.1f;
        camera.far = 100f;

        // 创建模型批次
        modelBatch = new ModelBatch();
        environment = new Environment();
        environment.add(new DirectionalLight().set(Color.WHITE, -1, -0.8f, -0.2f));
        environment.add(new PointLight().set(Color.WHITE, new Vector3(5f, 5f, 5f), 12f));

        // 创建一个简单的立方体模型
        ModelBuilder modelBuilder = new ModelBuilder();
        Model model = modelBuilder.createBox(5f, 5f, 5f,
                new Material(ColorAttribute.createDiffuse(Color.BLUE)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
//        Model model = new Model();
//        model.meshes.add(mesh);
//        Model model2 = new Model();
        // 创建模型实例

        modelInstance = new ModelInstance(model);

        // 创建着色器程序
        String vertexShader = Gdx.files.internal("shader/tmp.vert").readString();
        String fragmentShader = Gdx.files.internal("shader/cover.frag").readString();
        shader = new ShaderProgram(vertexShader, fragmentShader);
        if (!shader.isCompiled()) {
            Gdx.app.error("Shader", "Shader compilation failed:\n" + shader.getLog());
        }
    }


    public void render() {
        // 清除屏幕并设置背景色
//        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1f);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        camera.position.x+=0.01f;
        camera.position.y+=0.01f;
        // 更新相机
        camera.update();

        // 应用着色器程序
//        shader.begin();
//        shader.setUniformMatrix("u_projectionViewMatrix", camera.combined);
//        shader.end();

        // 开始模型批次渲染
        modelBatch.begin(camera);

        // 设置全局着色器
//        modelBatch.set

        // 渲染模型实例
        modelBatch.render(modelInstance,environment);

        // 结束模型批次渲染
        modelBatch.end();
    }


    public void dispose() {
        // 释放资源
        modelBatch.dispose();
        shader.dispose();
    }
}
