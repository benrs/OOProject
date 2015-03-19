package ca.qc.johnabbott.cs603.Shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by benjamin on 3/8/2015.
 */
public class Oval extends Shape {
    private float x1, y1, x2, y2;

    public Oval(float x1, float y1, float x2, float y2, int strokeColor, int width) {
        this(x1, y1, x2, y2, strokeColor, width, Color.TRANSPARENT);
    }

    public Oval(float x1, float y1, float x2, float y2, int strokeColor, int strokeWidth, int fillColor) {
        super(strokeColor, fillColor, strokeWidth);
        createProperOval(x1, y1, x2, y2);
    }

    @Override
    public void draw(Paint paint, Canvas canvas) {
        if(fillColor != Color.TRANSPARENT) {
            paint.setColor(fillColor);
            paint.setStyle(Paint.Style.FILL);
            RectF rect = new RectF(x1, y1, x2, y2);
            canvas.drawOval(rect, paint);
        }
        if(strokeColor != Color.TRANSPARENT && strokeWidth > 0) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(strokeColor);
            paint.setStrokeWidth(strokeWidth);
            paint.setStrokeCap(Paint.Cap.ROUND);
            RectF rect = new RectF(x1, y1, x2, y2);
            canvas.drawOval(rect, paint);
        }
    }

    private void createProperOval(float x1, float y1, float x2, float y2){
        this.x1 = (x2<x1)?x2:x1;
        this.y1 = (y2<y1)?y2:y1;
        this.x2 = (x2<x1)?x1:x2;
        this.y2 = (y2<y1)?y1:y2;
    }
}
