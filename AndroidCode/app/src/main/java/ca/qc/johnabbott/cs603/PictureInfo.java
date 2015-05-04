package ca.qc.johnabbott.cs603;

/**
 * Created by dylanfernandes on 15-05-04.
 */
public class PictureInfo {

    private String picName;
    private int picNum;

    public PictureInfo(String picName, int picNum) {
        this.picName = picName;
        this.picNum = picNum;
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
