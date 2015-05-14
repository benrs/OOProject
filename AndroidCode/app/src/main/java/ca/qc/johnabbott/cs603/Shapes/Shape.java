package ca.qc.johnabbott.cs603.Shapes;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import ca.qc.johnabbott.cs603.Structures.Point;

public abstract class Shape {
    protected int strokeColor;
    protected int fillColor;
    protected float strokeWidth;

    public Shape(int strokeColor, int fillColor, float lineWidth) {
        super();
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.strokeWidth = lineWidth;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public abstract void draw(Paint paint, Canvas canvas);

    public abstract JSONObject JSONconvert();

    public abstract void scale(float number);

    public abstract void setPoints(Point p1, Point p2);

    public abstract Point getPointOne();

    public abstract Point getPointTwo();

    public abstract Shape clone();
}
