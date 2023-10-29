package com.mygdx.game.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class MyLocalUtil {

    public static float convertFloat(String s){
        try {
            if (s!=null){
                return Float.parseFloat(s);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0.0f;
    }

    public static Rectangle fixRectangle(Rectangle rectangleShape) {
        float height = rectangleShape.getHeight();
        float width = rectangleShape.getWidth();
        if (height<0){
            rectangleShape.setY(rectangleShape.y + height);
            rectangleShape.setHeight(-height);
        }

        if (width<0){
            rectangleShape.setX(rectangleShape.x + width);
            rectangleShape.setWidth(-width);
        }
        return rectangleShape;
    }

    public static float divideFloat(float f, float f2) {
        if (MathUtils.isZero(f2)){
            return 0f;
        }
        return f/f2;
    }

    public static float range(float v, float min, float max) {
        if (v<=min){
            return min;
        }
        if (v>=max){
            return max;
        }
        return v;
    }
}
