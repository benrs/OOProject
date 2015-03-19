package ca.qc.johnabbott.cs603;

import ca.qc.johnabbott.cs603.Shapes.Shape;
import ca.qc.johnabbott.cs603.Tools.ToolName;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {
    private Picture picture;
    private ToolBox toolbox;
    private Paint paint;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.picture = new Picture();
        this.toolbox = new ToolBox(this);
        toolbox.changeTool(ToolName.LINE);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        picture.draw(paint, canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                toolbox.getCurrentTool().touchStart(event);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                toolbox.getCurrentTool().touchEnd(event);
                break;

            default:
                toolbox.getCurrentTool().touchMove(event);
        }
        return true;
    }

    public void addShape(Shape s) {
        picture.add(s);
    }

    public void addRubberBand(Shape rubber) { picture.addRubberBand(rubber); }

    public ToolBox getToolBox() {
        return toolbox;
    }

    public void erase() {
        picture.clear();
    }

    public void undo() { picture.undo(); }

    public Picture getPicture() {
        return picture;
    }
}
