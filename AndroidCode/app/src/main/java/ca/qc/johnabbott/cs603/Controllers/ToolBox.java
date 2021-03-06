package ca.qc.johnabbott.cs603.Controllers;

import ca.qc.johnabbott.cs603.Tools.*;
import ca.qc.johnabbott.cs603.Views.DrawingView;
import ca.qc.johnabbott.cs603.Views.PreviewView;

import android.graphics.Color;

public class ToolBox {
    private DrawingView drawing;
    private PreviewView preview;
    private Tool currentTool;
    private int strokeWidth;
    private int strokeColor;
    private int fillColor;

    public ToolBox(DrawingView drawing) {
        super();
        this.drawing = drawing;
        this.strokeWidth = 2;
        this.strokeColor = Color.BLACK;
        this.fillColor = Color.TRANSPARENT;
    }

    public ToolBox(PreviewView preview) {
        super();
        this.preview = preview;
        this.strokeWidth = 2;
        this.strokeColor = Color.BLACK;
        this.fillColor = Color.TRANSPARENT;
    }

    public void changeTool(ToolName name) {
        if (currentTool == null || currentTool.getName() != name) {
            switch (name) {
                case LINE:
                    currentTool = new LineTool(this);
                    break;
                case SQUARE:
                    currentTool = new SquareTool(this, ToolName.SQUARE);
                    break;
                case RECTANGLE:
                    currentTool = new RectangleTool(this, ToolName.RECTANGLE);
                    break;
                case CIRCLE:
                    currentTool = new CircleTool(this, ToolName.CIRCLE);
                    break;
                case OVAL:
                    currentTool = new OvalTool(this, ToolName.OVAL);
                    break;
                default:
                    currentTool = new LineTool(this);
                    break;
            }
        }
    }

    public Tool getCurrentTool() {
        return currentTool;
    }

    public ToolName getCurrentToolName() {
        return currentTool.getName();
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int color) {
        this.strokeColor = color;
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int color) {
        this.fillColor = color;
    }

    public DrawingView getDrawingView() {
        return drawing;
    }
}
