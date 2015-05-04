package ca.qc.johnabbott.cs603;

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
}
