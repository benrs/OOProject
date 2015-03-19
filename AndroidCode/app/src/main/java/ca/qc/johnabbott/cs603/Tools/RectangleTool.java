package ca.qc.johnabbott.cs603.Tools;

import android.graphics.Color;
import android.util.Log;
import ca.qc.johnabbott.cs603.ToolBox;
import ca.qc.johnabbott.cs603.Shapes.Rectangle;

public class RectangleTool extends RectangleBasedTool {
    public RectangleTool(ToolBox tbox, ToolName name) {
        super(tbox, name);
    }

    @Override
    public void addToDrawing() {
        Rectangle rectangle = new Rectangle(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
        toolbox.getDrawingView().addShape(rectangle);
        toolbox.getDrawingView().invalidate();
    }

    @Override
    public void addRubberBand(){
        Rectangle rectangle = new Rectangle(x1, y1, x2, y2, Color.GRAY, 1, Color.TRANSPARENT);
        toolbox.getDrawingView().addRubberBand(rectangle);
        toolbox.getDrawingView().invalidate();
    }
}
