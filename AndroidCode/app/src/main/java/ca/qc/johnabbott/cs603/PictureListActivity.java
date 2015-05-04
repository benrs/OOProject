package ca.qc.johnabbott.cs603;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ca.qc.johnabbott.cs603.AsyncTasks.AsynDone;
import ca.qc.johnabbott.cs603.AsyncTasks.AsyncGetAllPics;
import ca.qc.johnabbott.cs603.Globals.Environment;
import ca.qc.johnabbott.cs603.Shapes.Shape;


public class PictureListActivity extends Activity implements AsynDone {
    final private AsynDone callback = this;

    private int CREATED_IMAGE = 1;

    private ProgressBar loader;
    private ListView lv;
    ArrayList<PictureInfo> pictureArray = new ArrayList<PictureInfo>();

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
        String name;
        String shapeList;
        int numShapes;
        try {
            JSONArray ar = new JSONArray(jsonArray);
            for (int i = 0;i < ar.length();i++)
            {
                JSONObject JSONCursor = new JSONObject(ar.get(i).toString());
                String pictureString = JSONCursor.getString("encoded_pic");
                JSONObject pictureObject = new JSONObject(pictureString);
                name = pictureObject.getString("name");
                numShapes = Integer.parseInt(pictureObject.getString("size"));

                pictureArray.add(new PictureInfo(name,numShapes));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        ArrayAdapter<PictureInfo> adapter = new MyListAdapter();
        lv.setAdapter(adapter);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameArray);
       // lv.setAdapter(adapter);
        loader.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CREATED_IMAGE) {
            loadPictures();
        }
    }
    private class MyListAdapter extends ArrayAdapter<PictureInfo>
    {
        public MyListAdapter()
        {
            super(PictureListActivity.this,R.layout.item_view,pictureArray);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //make sure they is a view to work with
            View itemView = convertView;
            if(itemView == null)
            {
                itemView = getLayoutInflater().inflate(R.layout.item_view,parent,false);
            }
            //find the picture
            PictureInfo currentPic = pictureArray.get(position);

            //fill the view
            TextView txtName= (TextView)itemView.findViewById(R.id.lv_txtName);
            txtName.setText(currentPic.getPicName());
            TextView txtNum = (TextView)itemView.findViewById(R.id.lv_txtNum);
            txtNum.setText(Integer.toString(currentPic.getPicNum()));

            return itemView;
           // return super.getView(position, convertView, parent);
        }
    }
}
