package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

public class GLFieldRenderer {
    static final int CIRCLE_VERTICES = 10;
    ShapeRenderer renderer;

    public GLFieldRenderer() {
        renderer = new ShapeRenderer(500);
    }

    public void begin() {
        renderer.begin(ShapeRenderer.ShapeType.Line);
    }


    public void drawLine(float x1, float y1, float x2, float y2, int r, int g, int b) {
        float fr = r / 255f;
        float fg = g / 255f;
        float fb = b / 255f;
        renderer.setColor(fr, fg, fb, 1);
        renderer.line(x1, y1, x2, y2);
    }

    public void fillCircle(float cx, float cy, float radius, int r, int g, int b) {
        end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        float fr = r / 255f;
        float fg = g / 255f;
        float fb = b / 255f;
        renderer.setColor(fr, fg, fb, 1);
        renderer.circle(cx, cy, radius, 20);
        end();
        begin();
    }


    public void frameCircle(float cx, float cy, float radius, int r, int g, int b) {
        end();
        renderer.begin(ShapeRenderer.ShapeType.Line);
        float fr = r / 255f;
        float fg = g / 255f;
        float fb = b / 255f;
        renderer.setColor(fr, fg, fb, 1);
        renderer.circle(cx, cy, radius, 20);
        end();
        begin();
    }

    public void frameRecTangle(float x, float y, float width, float height, Color color) {
        end();
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(color);
        renderer.rect(x,y,width,height);
        renderer.end();
        renderer.begin();
    }

    public void end() {
        renderer.end();
    }

    public void setProjectionMatrix(Matrix4 matrix) {
        renderer.setProjectionMatrix(matrix);
    }
}
