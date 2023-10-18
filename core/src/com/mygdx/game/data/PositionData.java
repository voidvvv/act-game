package com.mygdx.game.data;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class PositionData {
    public final Vector2 pos = new Vector2(); // position

    public final Vector2 rectangle = new Vector2(); // shape

    public final Vector2 directVect = new Vector2(1, 0);

    private final Rectangle rectangleShape = new Rectangle();

    public Rectangle getRectangleShape() {
        return rectangleShape.setPosition(pos).setWidth(rectangle.x).setHeight(rectangle.y);
    }

    public boolean overlaps(PositionData pos) {
        return getRectangleShape().overlaps(pos.getRectangleShape());
    }
}
