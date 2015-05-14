package ca.qc.johnabbott.cs603.Tools;

import ca.qc.johnabbott.cs603.Controllers.ToolBox;
import android.view.MotionEvent;

public abstract class RectangleBasedTool extends Tool {
    protected float x1;
    protected float y1;
    protected float x2;
    protected float y2;

    public RectangleBasedTool(ToolBox tbox, ToolName name) {
        super(tbox, name);
    }

    @Override
    public void touchStart(MotionEvent event) {
        x1 = x2 = event.getX();
        y1 = y2 = event.getY();
    }

    @Override
    public void touchEnd(MotionEvent event) {
        assignProperCoordinates(event);
        addToDrawing();
    }

    @Override
    public void touchMove(MotionEvent event) {
        assignProperCoordinates(event);
        addRubberBand();
    }

    private void assignProperCoordinates(MotionEvent event){
        x2 = event.getX();
        y2 = event.getY();
    }
}
