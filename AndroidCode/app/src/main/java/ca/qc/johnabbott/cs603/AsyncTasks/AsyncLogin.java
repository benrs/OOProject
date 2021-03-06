package ca.qc.johnabbott.cs603.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import ca.qc.johnabbott.cs603.Globals.Environment;
import ca.qc.johnabbott.cs603.Interfaces.AsynDone;
import ca.qc.johnabbott.cs603.R;

/**
 * Created by dylanfernandes on 15-04-24.
 */
public class AsyncLogin extends AsyncTask<String, Integer, String> {
    AsynDone callback;
    View mainView;

    public AsyncLogin(View root, AsynDone callback){
        mainView = root;
        mainView.findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL("http://www.oop.barault.ca/api/users/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setChunkedStreamingMode(0);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("charset", "utf-8");

            //send the POST out
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write("{ \"username\": \""+params[0]+"\", \"password\": \""+params[1]+"\" }");
            out.flush();

            int statusCode = conn.getResponseCode();
            Log.v("STATUS CODE",Integer.toString(statusCode));
            if(statusCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                StringBuilder sb = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null){
                    sb.append(line + "\n");
                }
                reader.close();

                JSONObject result;
                result = new JSONObject(sb.toString());
                int code = result.getInt("protocolCode");
                if(code != 100){
                    return result.getString("error");
                }else{
                    Environment.setToken(result.getString("token"));
                    Log.d("test",Environment.getToken());
                    return result.getString("success");
                }
            }else{
                return "Something went wrong";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Malformed URL";
        } catch (ProtocolException e) {
            e.printStackTrace();
            return "Protocol Error";
        } catch (IOException e) {
            e.printStackTrace();
            return "Input output exception";
        } catch (JSONException e) {
            e.printStackTrace();
            return "JSON Parse error";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mainView.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        callback.done(result);
    }
}
