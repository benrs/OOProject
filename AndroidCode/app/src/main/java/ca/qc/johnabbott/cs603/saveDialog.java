package ca.qc.johnabbott.cs603;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by dylanfernandes on 15-05-01.
 */
public class saveDialog extends Dialog{

    public saveDialog(Context context) {
        super(context);
        this.setContentView(R.layout.dialog_save);
        Button save = (Button) findViewById(R.id.btnSave);
        Button cancel = (Button) findViewById(R.id.btnCancel);
        TextView picName = (TextView) findViewById(R.id.txtPicName);
    }
}
