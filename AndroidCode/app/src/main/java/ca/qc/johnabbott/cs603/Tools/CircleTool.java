package ca.qc.johnabbott.cs603.Tools;

import ca.qc.johnabbott.cs603.Shapes.Circle;
import ca.qc.johnabbott.cs603.ToolBox;

/**
 * Created by benjamin on 3/19/2015.
 */
public class CircleTool extends RectangleBasedTool {
    public CircleTool(ToolBox tbox, ToolName name) {
        super(tbox, name);
    }

    @Override
    public void addToDrawing() {
        Circle circle = new Circle(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
        toolbox.getDrawingView().addShape(circle);
        toolbox.getDrawingView().invalidate();
    }

    @Override
    public void addRubberBand(){
        Circle circle = new Circle(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
        toolbox.getDrawingView().addRubberBand(circle);
        toolbox.getDrawingView().invalidate();
    }
}
