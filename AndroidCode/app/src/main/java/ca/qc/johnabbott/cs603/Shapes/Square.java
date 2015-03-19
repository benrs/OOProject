package ca.qc.johnabbott.cs603.Shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by benjamin on 3/19/2015.
 */
public class Square extends Shape{
    private float x1, y1, x2, y2;

    public Square(float x1, float y1, float x2, float y2, int strokeColor, int width) {
        this(x1, y1, x2, y2, strokeColor, width, Color.TRANSPARENT);
    }

    public Square(float x1, float y1, float x2, float y2, int strokeColor, int strokeWidth, int fillColor) {
        super(strokeColor, fillColor, strokeWidth);
        createProperSquare(x1, y1, x2, y2);
    }

    @Override
    public void draw(Paint paint, Canvas canvas) {
        if(fillColor != Color.TRANSPARENT) {
            paint.setColor(fillColor);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(x1, y1, x2, y2, paint);
        }
        if(strokeColor != Color.TRANSPARENT && strokeWidth > 0) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(strokeColor);
            paint.setStrokeWidth(strokeWidth);
            paint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawRect(x1, y1, x2, y2, paint);
        }
    }

    // Functions restricts the points and makes sure the outcome
    // is a square
    private void createProperSquare(float x1, float y1, float x2, float y2){
        float diffX = Math.abs(x2-x1);
        float diffY = Math.abs(y2-y1);
        // Get the top left point
        float leftX = (x1<x2)?x1:
                (diffX<diffY)?x2:x1-diffY;
        float leftY = (y1<y2)?y1:
                (diffY<diffX)?y2:y1-diffX;
        // Get the bottom right point
        float rightX = (diffX<diffY)?leftX+diffX:leftX+diffY;
        float rightY = (diffY<diffX)?leftY+diffY:leftY+diffX;

        this.x1 = leftX;
        this.y1 = leftY;
        this.x2 = rightX;
        this.y2 = rightY;
    }
}
