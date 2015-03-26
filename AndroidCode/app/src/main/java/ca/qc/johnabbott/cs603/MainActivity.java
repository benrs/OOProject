package ca.qc.johnabbott.cs603;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/*
    Date: March 12th, 2015
    Author: Benjamin Barault
    Assignment: Extension of the drawing assignment
 */
public class MainActivity extends Activity {
    private DrawingView drawing;
    private Dialog current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            case R.id.Login:
                showLoginDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showToolsDialog() {
        new ToolSettingsDialog(this, drawing.getToolBox());
    }

    private void showLoginDialog() {
        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(i);
    }

    private void showMenuDialog() {
        current = new Dialog(this);
        current.setContentView(R.layout.dialog_menu);
        current.setTitle("Menu");
        current.setCanceledOnTouchOutside(true);

        Button buttonErase = (Button) current.findViewById(R.id.buttonErase);
        Button buttonUndo  = (Button) current.findViewById(R.id.buttonUndo);

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

        current.show();
    }
}
