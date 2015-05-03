package ca.qc.johnabbott.cs603;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ca.qc.johnabbott.cs603.AsyncTasks.AsynDone;
import ca.qc.johnabbott.cs603.AsyncTasks.AsyncGetAllPics;
import ca.qc.johnabbott.cs603.Globals.Environment;


public class PictureListActivity extends Activity implements AsynDone {
    final private AsynDone callback = this;

    private int CREATED_IMAGE = 1;

    private ProgressBar loader;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_list);

        loader = (ProgressBar) findViewById(R.id.loadingPanel);
        lv = (ListView) findViewById(R.id.listView);

        loadPictures();
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
        if (id == R.id.action_create) {
            Intent newPicture = new Intent(this.getBaseContext(), DrawActivity.class);
            startActivityForResult(newPicture, CREATED_IMAGE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadPictures(){
        loader.setVisibility(View.VISIBLE);
        AsyncGetAllPics pics = new AsyncGetAllPics(callback);
        pics.execute();
    }

    @Override
    public void done(String message){
        // Don't do anything for now
    }

    @Override
    public void populateView(String jsonArray){
        ArrayList<String> nameArray = new ArrayList<String>();
        String name;
        try {
            JSONArray ar = new JSONArray(jsonArray);
            for (int i = 0;i < ar.length();i++)
            {
                JSONObject JSONCursor = new JSONObject(ar.get(i).toString());
                String pictureString = JSONCursor.getString("encoded_pic");
                JSONObject pictureObject = new JSONObject(pictureString);
                name = pictureObject.getString("name");
                nameArray.add(name);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameArray);
        lv.setAdapter(adapter);
        loader.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CREATED_IMAGE) {
            loadPictures();
        }
    }
}
