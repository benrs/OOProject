package ca.qc.johnabbott.cs603.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import ca.qc.johnabbott.cs603.Controllers.Picture;
import ca.qc.johnabbott.cs603.Shapes.Shape;
import ca.qc.johnabbott.cs603.Controllers.ToolBox;
import ca.qc.johnabbott.cs603.Tools.ToolName;

/**
 * Created by dylanfernandes on 15-05-05.
 */
public class PreviewView extends View {
    private Picture picture;
    private ToolBox toolbox;
    private Paint paint;

    public PreviewView(Context context, AttributeSet attrs) {
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

    public void addShape(Shape s) {
        picture.add(s);
    }

    public void erase() { picture.clear(); }

    public void scaleImage(float scale) {
        picture.scalePicture(scale);
    }
}
