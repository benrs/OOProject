package ca.qc.johnabbott.cs603;

import java.util.LinkedList;
import java.util.List;
import ca.qc.johnabbott.cs603.Shapes.Shape;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;

public class Picture {
    private List<Shape> shapes;
    private Shape rubberBand;
    private Paint previewPaint;

    public Picture() {
        super();
        shapes = new LinkedList<Shape>();
        previewPaint = new Paint();
        previewPaint.setStrokeCap(Paint.Cap.ROUND);
        previewPaint.setPathEffect(new DashPathEffect(new float[]{4.0f, 4.0f}, 1.0f));
    }

    public void draw(Paint paint, Canvas canvas) {
        if(rubberBand != null){
            rubberBand.draw(previewPaint, canvas);
            rubberBand = null;
        }
        for(Shape shape : shapes)
            shape.draw(paint, canvas);
    }

    public void add(Shape s) {
        shapes.add(s);
    }

    public void addRubberBand(Shape s) { rubberBand = s; }

    public void clear() {
        shapes.clear();
    }

    public void undo() { shapes.remove(shapes.size()-1); }

    public int amountOfShapes() { return shapes.size(); }
}
