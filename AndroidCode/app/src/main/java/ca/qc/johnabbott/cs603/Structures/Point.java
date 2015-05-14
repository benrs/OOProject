package ca.qc.johnabbott.cs603.Structures;

/**
 * Created by benjamin on 5/8/2015.
 */
public class Point {
    private float x;
    private float y;

    public Point(){
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }
}
