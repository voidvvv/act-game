package com.mygdx.game.myg2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import java.util.function.Consumer;
import java.util.function.Function;

public class MyShaderBatch {
    private ShaderProgram coverShaderProgram;

    private Mesh mesh;

    Matrix4 matrix4 = new Matrix4();

    Color color00 = new Color(1,1,1,1);

    Color color01 = new Color(1,1,1,1);
    Color color10 = new Color(1,1,1,1);
    Color color11 = new Color(1,0,1,1);
    public MyShaderBatch(float width, float height) {
        coverShaderProgram = new ShaderProgram(Gdx.files.internal("shader/tmp.vert"),Gdx.files.internal("shader/cover.frag"));
//        this.coverShaderProgram = shaderProgram;
        mesh = new Mesh(true, 4, 6, VertexAttribute.Position(), VertexAttribute.ColorUnpacked(), VertexAttribute.TexCoords(0));
        mesh.setVertices(new float[] {
                0f, 0f, 0, color00.r,color00.g,color00.b,color00.a, 0, 1,
                width, 0, 0,color01.r,color01.g,color01.b,color01.a,1, 1,
                width, height, 0, color10.r,color10.g,color10.b,color10.a, 1, 0,
                0, height, 0, color11.r,color11.g,color11.b,color11.a, 0, 0
        });
        mesh.setIndices(new short[] {0, 1, 2, 2, 3, 0});
//        this.matrix4 = matrix4;
    }

    public void draw(MyLight myLight){
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA);
        coverShaderProgram.bind();
        coverShaderProgram.setUniformMatrix("u_projTrans",matrix4);
        coverShaderProgram.setUniform4fv("coverColor",myLight.colorFloat,0,4);
        coverShaderProgram.setUniformf("minRange",myLight.minRange);
        coverShaderProgram.setUniformf("maxRange",myLight.maxRange);
        coverShaderProgram.setUniform2fv("u_light_position",myLight.position,0,2);
        coverShaderProgram.setUniformi("lightFlag",2);

        mesh.render(coverShaderProgram,GL20.GL_TRIANGLES);
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public void setProjectMetircs(Matrix4 combined) {
        this.matrix4.set(combined);
    }
}
