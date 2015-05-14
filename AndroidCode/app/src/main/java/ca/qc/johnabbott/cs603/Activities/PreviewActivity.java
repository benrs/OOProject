package ca.qc.johnabbott.cs603.Activities;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;

import ca.qc.johnabbott.cs603.Globals.Environment;
import ca.qc.johnabbott.cs603.Structures.PictureInfo;
import ca.qc.johnabbott.cs603.Views.PreviewView;
import ca.qc.johnabbott.cs603.R;
import ca.qc.johnabbott.cs603.Shapes.Shape;

/**
 * Created by benjamin on 5/6/2015.
 */
public class PreviewActivity extends Activity {
    private PreviewView drawing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        drawing = (PreviewView)this.findViewById(R.id.preview_view);
        if(this.getIntent().hasExtra("picPosition"))
        {
            PictureInfo aPic = Environment.getPicture(this.getIntent().getIntExtra("picPosition", 2));
            List<Shape> picShapes= aPic.getShapes();
            drawing.erase();
            for(Shape shape : picShapes)
                drawing.addShape(shape);
        }
    }
}
