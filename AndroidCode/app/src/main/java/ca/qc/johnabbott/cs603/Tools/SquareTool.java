package ca.qc.johnabbott.cs603.Tools;

import android.graphics.Color;

import ca.qc.johnabbott.cs603.Shapes.Square;
import ca.qc.johnabbott.cs603.Controllers.ToolBox;

/**
 * Created by benjamin on 3/19/2015.
 */
public class SquareTool extends RectangleBasedTool {
    public SquareTool(ToolBox tbox, ToolName name) {
        super(tbox, name);
    }

    @Override
    public void addToDrawing() {
        Square square = new Square(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
        toolbox.getDrawingView().addShape(square);
        toolbox.getDrawingView().invalidate();
    }

    @Override
    public void addRubberBand(){
        Square square = new Square(x1, y1, x2, y2, Color.GRAY, 1, Color.TRANSPARENT);
        toolbox.getDrawingView().addRubberBand(square);
        toolbox.getDrawingView().invalidate();
    }
}
