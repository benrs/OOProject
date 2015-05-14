package ca.qc.johnabbott.cs603.Shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import ca.qc.johnabbott.cs603.Interfaces.Scalable;
import ca.qc.johnabbott.cs603.Structures.Point;

/**
 * Created by benjamin on 3/19/2015.
 */
public class Circle extends Shape {
    private Point p1, p2;

    public Circle(float x1, float y1, float x2, float y2, int strokeColor, float width) {
        this(x1, y1, x2, y2, strokeColor, width, Color.TRANSPARENT);
    }

    public Circle(float x1, float y1, float x2, float y2, int strokeColor, float strokeWidth, int fillColor) {
        super(strokeColor, fillColor, strokeWidth);
        createProperCircle(x1, y1, x2, y2);
    }

    @Override
    public void draw(Paint paint, Canvas canvas) {
        if(fillColor != Color.TRANSPARENT) {
            paint.setColor(fillColor);
            paint.setStyle(Paint.Style.FILL);
            RectF rect = new RectF(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            canvas.drawOval(rect, paint);
        }
        if(strokeColor != Color.TRANSPARENT && strokeWidth > 0) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(strokeColor);
            paint.setStrokeWidth(strokeWidth);
            paint.setStrokeCap(Paint.Cap.ROUND);
            RectF rect = new RectF(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            canvas.drawOval(rect, paint);
        }
    }

    // Function restricts the points from creating anything but a circle.
    private void createProperCircle(float x1, float y1, float x2, float y2){
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

        this.p1 = new Point(leftX, leftY);
        this.p2 = new Point(rightX, rightY);
    }

    public JSONObject JSONconvert() {
        JSONObject anObject = new JSONObject();
        try {
            anObject.put("type","Circle");
            anObject.put("x1",this.p1.getX());
            anObject.put("x2",this.p1.getY());
            anObject.put("y1",this.p2.getX());
            anObject.put("y2",this.p2.getY());
            anObject.put("strokeColor", this.strokeColor);
            anObject.put("fillColor",this.fillColor);
            anObject.put("strokeWidth", this.strokeWidth);
        } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
        }
        return anObject;
    }

    @Override
    public void scale(float number) {
        this.p1.setX(this.p1.getX() * number);
        this.p1.setY(this.p1.getY() * number);
        this.p2.setX(this.p2.getX() * number);
        this.p2.setY(this.p2.getY() * number);
        this.strokeWidth = this.getStrokeWidth() * number * 3;
    }

    @Override
    public void setPoints(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public Point getPointOne() {
        return this.p1;
    }

    @Override
    public Point getPointTwo() {
        return this.p2;
    }

    @Override
    public Shape clone() {
        return new Circle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), this.getStrokeColor(), this.getStrokeWidth(), this.getFillColor());
    }
}
