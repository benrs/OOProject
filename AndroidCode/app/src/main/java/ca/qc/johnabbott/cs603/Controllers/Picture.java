package ca.qc.johnabbott.cs603.Controllers;

import java.util.LinkedList;
import java.util.List;
import ca.qc.johnabbott.cs603.Shapes.Shape;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Picture {
    private List<Shape> shapes;
    private Shape rubberBand;
    private Paint previewPaint;
    private String pictureName;

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

    public void setName(String name){
        pictureName = name;
    }

    public JSONObject JSONconvert()
    {
        JSONObject root = new JSONObject();
        JSONArray obj = new JSONArray();
        for (int i = 0;i < shapes.size();i++)
          obj.put(shapes.get(i).JSONconvert());

        try {
            root.put("list", obj);
            root.put("size", this.amountOfShapes());
            root.put("name", pictureName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return root;
    }

    public void scalePicture(float scale){
        for(Shape aShape:shapes){
            aShape.scale(scale);
        }
    }
}
