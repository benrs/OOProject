package ca.qc.johnabbott.cs603.Tools;

import android.graphics.Color;

import ca.qc.johnabbott.cs603.Controllers.ToolBox;
import ca.qc.johnabbott.cs603.Shapes.Line;

public class LineTool extends RectangleBasedTool {
    public LineTool(ToolBox tbox) {
        super(tbox, ToolName.LINE);
    }

    @Override
    public void addToDrawing() {
        Line line = new Line(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth());
        toolbox.getDrawingView().addShape(line);
        toolbox.getDrawingView().invalidate();
    }

    @Override
    public void addRubberBand(){
        Line line = new Line(x1, y1, x2, y2, Color.GRAY, 1);
        toolbox.getDrawingView().addRubberBand(line);
        toolbox.getDrawingView().invalidate();
    }
}
