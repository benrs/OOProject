package ca.qc.johnabbott.cs603;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import ca.qc.johnabbott.cs603.AsyncTasks.AsynDone;
import ca.qc.johnabbott.cs603.AsyncTasks.AsyncGetAllPics;
import ca.qc.johnabbott.cs603.Globals.Environment;


public class PictureListActivity extends Activity implements AsynDone {
    final AsynDone callback = this;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_list);
        AsyncGetAllPics pics = new AsyncGetAllPics(callback);
        pics.execute();
        ListView lv = (ListView) findViewById(R.id.listView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_picture_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent newPicture = new Intent(this.getBaseContext(), DrawActivity.class);
            startActivity(newPicture);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void done(String message){
        Toast displayStatus = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        displayStatus.show();
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new String[] {"a","b","c"}));
    }
}
