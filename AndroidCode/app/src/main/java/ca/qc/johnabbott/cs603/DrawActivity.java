package ca.qc.johnabbott.cs603;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Dialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ca.qc.johnabbott.cs603.AsyncTasks.AsynDone;
import ca.qc.johnabbott.cs603.AsyncTasks.AsyncSave;
import ca.qc.johnabbott.cs603.Globals.Environment;

/*
    Date: March 12th, 2015
    Author: Benjamin Barault
    Assignment: Extension of the drawing assignment
 */
public class DrawActivity extends Activity implements AsynDone {
    private DrawingView drawing;
    private Dialog current;
    final AsynDone callback = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        drawing = (DrawingView)this.findViewById(R.id.drawing_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_tools:
                showToolsDialog();
                return true;
            case R.id.menu_menu:
                showMenuDialog();
                return true;
            case R.id.menu_signout:
                signOut();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showToolsDialog() {
        new ToolSettingsDialog(this, drawing.getToolBox());
    }

    private void showMenuDialog() {
        current = new Dialog(this);
        current.setContentView(R.layout.dialog_menu);
        current.setTitle("Menu");
        current.setCanceledOnTouchOutside(true);

        Button buttonErase = (Button) current.findViewById(R.id.buttonErase);
        Button buttonUndo  = (Button) current.findViewById(R.id.buttonUndo);
        Button buttonSave  = (Button) current.findViewById(R.id.buttonSave);

        if(drawing.getPicture().amountOfShapes() <= 0){
            buttonUndo.setClickable(false);
        }else{
            buttonUndo.setClickable(true);
            buttonUndo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawing.undo();
                    drawing.invalidate();
                    current.dismiss();
                }
            });
        }

        buttonErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TEST",drawing.getPicture().JSONconvert().toString());
                drawing.erase();
                drawing.invalidate();
                current.dismiss();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("TEST",drawing.getPicture().JSONconvert().toString());
                AsyncSave save = new AsyncSave(view, callback);
                save.execute(Environment.getToken(),drawing.getPicture().JSONconvert().toString());
            }
        });
        current.show();
    }

    public void signOut(){
        Intent login = new Intent(this, startupActivity.class);
        this.startActivity(login);
        this.finish();
    }

    @Override
    public void done(String message){
        //this.findViewById(R.id.loadingPanel).setVisibility(View.INVISIBLE);
        Toast displayStatus = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        displayStatus.show();
    }
}
