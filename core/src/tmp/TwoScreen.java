package tmp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Set;

public class TwoScreen implements Screen {
    SwordmanRender swordmanRender;

    public PerspectiveCamera cam;
    public Model model;
    public ModelInstance instance;

    public ModelBatch modelBatch;
    public TwoScreen() {
        swordmanRender = new SwordmanRender();
    }
    Model arrow;
    ModelInstance arrowInstance;
    public Environment environment;
    @Override
    public void show() {
        Set<Integer>[] sets = new Set[20];
        swordmanRender.init();
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
        cam.update();

        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(5f, 5f, 5f,
                new Material(TextureAttribute.createDiffuse(new Texture(Gdx.files.internal("badlogic.jpg")))),
                VertexAttributes.Usage.Position |VertexAttributes.Usage.TextureCoordinates| VertexAttributes.Usage.Normal);

        Vector3 start = new Vector3(0,0,0);
        Vector3 end = new Vector3(010,10,10);
        arrow = modelBuilder.createArrow(start, end, new Material(TextureAttribute.createDiffuse(new Texture(Gdx.files.internal("badlogic.jpg")))),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.TextureCoordinates | VertexAttributes.Usage.Normal);
        instance = new ModelInstance(model);
        arrowInstance = new ModelInstance(arrow);
        modelBatch = new ModelBatch();

        Model model = new Model();
        ModelInstance newInstance = new ModelInstance(model);
        com.badlogic.gdx.graphics.g3d.model.Node node = new Node();


        cam.lookAt(new Vector3(3,2,1));
    }
    Vector3 rov = new Vector3();
    @Override
    public void render(float delta) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
//        ScreenViewport
//        swordmanRender.render();
//        model.calculateTransforms();
        modelBatch.begin(cam);

//        modelBatch.getRenderContext().setDepthTest();
        instance.transform.rotate(rov.set(1f,0f,0f),0.2f);
//        modelBatch.render(instance,environment);
        modelBatch.render(arrowInstance,environment);
        modelBatch.end();
        Matrix4 m4 = new Matrix4();

//        m4.mulLeft()
        update(delta);
    }

    private void update(float delta) {
        swordmanRender.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        cam.update();
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
