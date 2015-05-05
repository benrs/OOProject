package ca.qc.johnabbott.cs603;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import ca.qc.johnabbott.cs603.Shapes.Shape;

/**
 * Created by dylanfernandes on 15-05-04.
 */
public class PictureInfo {

    private String picName;
    private int picNum;
    private List<Shape> shapes;

    public PictureInfo(String picName, int picNum, List<Shape> shapes) {
        this.picName = picName;
        this.picNum = picNum;
        this.shapes = shapes;
    }

    public PictureInfo(String picName, int picNum) {
        this.picName = picName;
        this.picNum = picNum;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public int getPicNum() {
        return picNum;
    }

    public void setPicNum(int picNum) {
        this.picNum = picNum;
    }

    public Bitmap getPicture()
    {
        //Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap thePic = Bitmap.createBitmap(50,50,Bitmap.Config.RGB_565);
        //Bitmap thePic = new BitmapFactory.decodeFile();
        Canvas canvas = new Canvas(thePic);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        for(Shape shape : shapes)
            shape.draw(paint, canvas);
        return thePic;
    }
}
