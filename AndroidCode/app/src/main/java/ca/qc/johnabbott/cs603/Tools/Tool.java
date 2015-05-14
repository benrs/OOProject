package ca.qc.johnabbott.cs603.Tools;

import ca.qc.johnabbott.cs603.Controllers.ToolBox;
import android.view.MotionEvent;

public abstract class Tool {
    protected ToolBox toolbox;
    protected ToolName name;

    public Tool(ToolBox toolbox, ToolName name) {
        super();
        this.toolbox = toolbox;
        this.name = name;
    }

    public ToolName getName() {
        return name;
    }

    public abstract void touchStart(MotionEvent event);
    public abstract void touchEnd(MotionEvent event);
    public abstract void touchMove(MotionEvent event);
    public abstract void addToDrawing();
    public abstract void addRubberBand();
}
