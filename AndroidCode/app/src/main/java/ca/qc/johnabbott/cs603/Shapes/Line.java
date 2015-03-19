package ca.qc.johnabbott.cs603.Shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Line extends Shape {
    private float x1, x2, y1, y2;

    public Line(float x1, float y1, float x2, float y2, int strokeColor, int strokeWidth) {
        super(strokeColor, Color.TRANSPARENT, strokeWidth);
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public void draw(Paint paint, Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(strokeColor);
        paint.setStrokeWidth(strokeWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);

        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        canvas.drawPath(path, paint);
    }
}
