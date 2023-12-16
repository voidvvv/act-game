package com.mygdx.game.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;

public class ObjShadowRender {
    Mesh mesh;

    Color defaultColor = Color.ORANGE;

    ShaderProgram shaderProgram;

    float[] vertices = new float[1000];

    int idx = 36;

    public ObjShadowRender() {
    }

    public void init(){

        mesh = new Mesh(true, 4, 6, VertexAttribute.Position(), VertexAttribute.ColorUnpacked(), VertexAttribute.TexCoords(0));
//        mesh.setVertices(new float[] {
//                shadowBox[0], shadowBox[1], -0.5f, color.r,color.g,color.b,color.a, 0, 1,
//                shadowBox[0]+shadowBox[2], shadowBox[1], -0.5f,color.r,color.g,color.b,color.a,1, 1,
//                shadowBox[0]+shadowBox[2], shadowBox[1]+shadowBox[3], -0.5f, color.r,color.g,color.b,color.a, 1, 0,
//                shadowBox[0], shadowBox[1]+shadowBox[3], -0.5f, color.r,color.g,color.b,color.a, 0, 0
//        },0,36);
//        mesh.set

        mesh.setIndices(new short[] {0, 1, 2, 2, 3, 0});

        shaderProgram = new ShaderProgram(Gdx.files.internal("shader/tmp.vert"),Gdx.files.internal("shader/shadow_normal.frag"));
        if(!shaderProgram.isCompiled()){
            String log = shaderProgram.getLog();
            System.out.println("shader:   error:   "+log);
        }
    }


    public void renderShadow(Color color, float[] shadowBox, Matrix4 matrix4, TextureRegion tr){
//        tr.getTexture().bind();
        shaderProgram.bind();
//
        int index = 0;
        vertices[index++] = shadowBox[0];
        vertices[index++] = shadowBox[1];
        vertices[index++] = 0 ;
        vertices[index++] = color.r;
        vertices[index++] = color.g;
        vertices[index++] = color.b;
        vertices[index++] = color.a;

        vertices[index++] = 0;
        vertices[index++] = 1;

        vertices[index++] = shadowBox[0]+shadowBox[2];
        vertices[index++] = shadowBox[1];
        vertices[index++] = 0 ;
        vertices[index++] = color.r;
        vertices[index++] = color.g;
        vertices[index++] = color.b;
        vertices[index++] = color.a;

        vertices[index++] = 1;
        vertices[index++] = 1;

        vertices[index++] = shadowBox[0]+shadowBox[2];
        vertices[index++] = shadowBox[1]+shadowBox[3];
        vertices[index++] = 0 ;
        vertices[index++] = color.r;
        vertices[index++] = color.g;
        vertices[index++] = color.b;
        vertices[index++] = color.a;

        vertices[index++] = 1;
        vertices[index++] = 0;

        vertices[index++] = shadowBox[0];
        vertices[index++] = shadowBox[1]+shadowBox[3];
        vertices[index++] = 0 ;
        vertices[index++] = color.r;
        vertices[index++] = color.g;
        vertices[index++] = color.b;
        vertices[index++] = color.a;

        vertices[index++] = 0;
        vertices[index++] = 0;
        shaderProgram.setUniformMatrix("u_projTrans",matrix4);
//        shaderProgram.setUniformi("u_texture",0);
        mesh.setVertices(vertices,0,index);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA);
        mesh.render(shaderProgram, GL20.GL_TRIANGLES);
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public void renderShadow(float[] shadowBox, Matrix4 matrix4, TextureRegion tr){
        renderShadow(defaultColor,shadowBox,matrix4, tr);
    }
}
