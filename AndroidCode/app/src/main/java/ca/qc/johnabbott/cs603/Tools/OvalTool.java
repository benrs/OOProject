package ca.qc.johnabbott.cs603.Tools;

import android.graphics.Color;

import ca.qc.johnabbott.cs603.Shapes.Oval;
import ca.qc.johnabbott.cs603.ToolBox;

/**
 * Created by benjamin on 3/8/2015.
 */
public class OvalTool extends RectangleBasedTool {
    public OvalTool(ToolBox tbox, ToolName name) {
        super(tbox, name);
    }

    @Override
    public void addToDrawing() {
        Oval oval = new Oval(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
        toolbox.getDrawingView().addShape(oval);
        toolbox.getDrawingView().invalidate();
    }

    @Override
    public void addRubberBand(){
        Oval oval = new Oval(x1, y1, x2, y2, Color.GRAY, 1, Color.TRANSPARENT);
        toolbox.getDrawingView().addRubberBand(oval);
        toolbox.getDrawingView().invalidate();
    }
}
