package com.mygdx.game.data;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.utils.MyLocalUtil;

public class PositionData {
    public final Vector2 pos = new Vector2(); // position

    public float z = 0f;

    public final Vector2 posCenter = new Vector2();

    public final Vector2 rectangle = new Vector2(); // shape

    public float height = 0.0f;

    public final Vector2 directVect = new Vector2(1, 0);

    private final Rectangle rectangleShape = new Rectangle();
    private final Rectangle rectangleShapeZ = new Rectangle();
    public void update(float delta){
        posCenter.set(pos.x,pos.y+height/2);
    }

    public Rectangle getRectangleShape() {
//        float dx = directVect.x>=0f?1f:-1f;
        rectangleShape.setPosition(pos.x-(1f*rectangle.x)/2f,pos.y).setWidth(rectangle.x).setHeight(rectangle.y);

        return MyLocalUtil.fixRectangle(rectangleShape);
    }

    public Rectangle getRectangleShapeZ() {
//        float dx = directVect.x>=0f?1f:-1f;
        rectangleShapeZ.setPosition(pos.x-(1f*rectangle.x)/2f,pos.y).setWidth(rectangle.x).setHeight(height);

        return MyLocalUtil.fixRectangle(rectangleShapeZ);
    }

    public boolean overlaps(PositionData pos) {
        return getRectangleShape().overlaps(pos.getRectangleShape()) && getRectangleShapeZ().overlaps(pos.getRectangleShapeZ());
    }
}
