package ca.qc.johnabbott.cs603.Shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by benjamin on 3/19/2015.
 */
public class Circle extends Shape  {
    private float x1, y1, x2, y2;

    public Circle(float x1, float y1, float x2, float y2, int strokeColor, int width) {
        this(x1, y1, x2, y2, strokeColor, width, Color.TRANSPARENT);
    }

    public Circle(float x1, float y1, float x2, float y2, int strokeColor, int strokeWidth, int fillColor) {
        super(strokeColor, fillColor, strokeWidth);
        createProperCircle(x1, y1, x2, y2);
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

        this.x1 = leftX;
        this.y1 = leftY;
        this.x2 = rightX;
        this.y2 = rightY;
    }

    public JSONObject JSONconvert() {
        JSONObject anObject = new JSONObject();
        try {
            anObject.put("type","Circle");
            anObject.put("x1",this.x1);
            anObject.put("x2",this.x2);
            anObject.put("y1",this.y1);
            anObject.put("y2",this.y2);
            anObject.put("strokeColor", this.strokeColor);
            anObject.put("fillColor",this.fillColor);
            anObject.put("strokeWidth", this.strokeWidth);
        } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
        }
        return anObject;
    }
}
