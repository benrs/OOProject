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
import java.util.LinkedList;
import java.util.List;

import ca.qc.johnabbott.cs603.AsyncTasks.AsynDone;
import ca.qc.johnabbott.cs603.AsyncTasks.AsyncGetAllPics;
import ca.qc.johnabbott.cs603.Globals.Environment;
import ca.qc.johnabbott.cs603.Shapes.Circle;
import ca.qc.johnabbott.cs603.Shapes.Line;
import ca.qc.johnabbott.cs603.Shapes.Oval;
import ca.qc.johnabbott.cs603.Shapes.Rectangle;
import ca.qc.johnabbott.cs603.Shapes.Shape;
import ca.qc.johnabbott.cs603.Shapes.Square;


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
        List<Shape> shapeList = new LinkedList<Shape>();
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
                JSONArray shapes = new JSONArray(pictureObject.getString("list"));
                for(int c =0;c<shapes.length();c++)
                {
                    JSONObject JSONShapeCursor =  new JSONObject(shapes.get(c).toString());
                    String shapeType = JSONShapeCursor.getString("type");
                    float x1 = Float.parseFloat(JSONShapeCursor.getString("x1"));
                    float x2 = Float.parseFloat(JSONShapeCursor.getString("x2"));
                    float y1 = Float.parseFloat(JSONShapeCursor.getString("y1"));
                    float y2 = Float.parseFloat(JSONShapeCursor.getString("y2"));
                    int strokeColor = Integer.parseInt(JSONShapeCursor.getString("strokeColor"));
                    int strokeWidth = Integer.parseInt(JSONShapeCursor.getString("strokeWidth"));
                    int fillColor;
                    switch (shapeType)
                    {
                        case "Rectangle":
                            fillColor = Integer.parseInt(JSONShapeCursor.getString("fillColor"));
                            shapeList.add(new Rectangle(x1,x2,y1,y2,strokeColor,strokeWidth,fillColor));
                            Log.d("SHAPE","Recatangle");
                            break;
                        case "Line":
                            shapeList.add(new Line(x1,x2,y1,y2,strokeColor,strokeWidth));
                            Log.d("SHAPE","Line");
                            break;
                        case "Square":
                            fillColor = Integer.parseInt(JSONShapeCursor.getString("fillColor"));
                            shapeList.add(new Square(x1,x2,y1,y2,strokeColor,strokeWidth,fillColor));
                            Log.d("SHAPE","Sqaure");
                            break;
                        case "Oval":
                            fillColor = Integer.parseInt(JSONShapeCursor.getString("fillColor"));
                            shapeList.add(new Oval(x1,x2,y1,y2,strokeColor,strokeWidth,fillColor));
                            Log.d("SHAPE","Oval");
                            break;
                        case "Circle":
                            fillColor = Integer.parseInt(JSONShapeCursor.getString("fillColor"));
                            shapeList.add(new Circle(x1,x2,y1,y2,strokeColor,strokeWidth,fillColor));
                            Log.d("SHAPE","Circle");
                            break;
                    }
                }
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
