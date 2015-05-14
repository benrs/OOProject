package com.example.astdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyAsyncTask extends AsyncTask<String, Integer, ArrayList<String>> {
	
	public AysncResponse delegate=null;
    
	@Override
	protected ArrayList<String> doInBackground(String... sizes) {
    	ArrayList<String> realms = new ArrayList<String>();

        try {
            URL url = new URL("https://us.api.battle.net/wow/realm/status?locale=en_US&apikey=mk7ysyyrwcd24s5hvyfx9jd3dgjta9pa");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setChunkedStreamingMode(0);

            if(conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                StringBuilder sb = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null){
                    sb.append(line + "\n");
                }
                reader.close();

                JSONObject result;
                result = new JSONObject(sb.toString());
                JSONArray theRealms = result.getJSONArray("realms");

                for (int current = 0; current < theRealms.length(); current++) {
                    JSONObject thisRealm = theRealms.getJSONObject(current);
                    realms.add(thisRealm.getString("name"));

                    publishProgress((current + 1) * 100 / theRealms.length());

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return realms;
	}
	
    protected void onProgressUpdate(Integer... progress) 
    {
    	delegate.processUpdate(progress[0]);
    }

    protected void onPostExecute(ArrayList<String> list)
    {
    	delegate.processFinish(list);
    }
}