package ca.qc.johnabbott.cs603;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import ca.qc.johnabbott.cs603.Shapes.Shape;
import ca.qc.johnabbott.cs603.Tools.ToolName;

/**
 * Created by dylanfernandes on 15-05-05.
 */
public class PreviewImageView extends ImageView{
    private Picture picture;
    private ToolBox toolbox;
    private Paint paint;

    public PreviewImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.picture = new Picture();
        this.toolbox = new ToolBox(this);
        toolbox.changeTool(ToolName.LINE);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        picture.draw(paint, canvas);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawRect(0, 0, 20, 20, p);
    }
    public void addShape(Shape s) {
        picture.add(s);
    }


}
