package ca.qc.johnabbott.cs603.AsyncTasks;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;

import ca.qc.johnabbott.cs603.DrawActivity;
import ca.qc.johnabbott.cs603.R;

/**
 * Created by benjamin on 4/22/2015.
 */
public class AsyncRegister extends AsyncTask<String, Integer, String> {
    AsynDone callback;
    View mainView;

    public AsyncRegister(View root, AsynDone callback){
        mainView = root;
        mainView.findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params) {
        String urlParams = "";
        byte[] postData;
        if(params.length > 2) {
            urlParams = "username=" + params[0] + "&email=" + params[1] + "&password="+params[2];
            postData = urlParams.getBytes(Charset.forName("UTF-8"));
        }else{
            return "Insufficient data";
        }
        try {
            URL url = new URL("http://www.oop.barault.ca/api/createUser");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");

            //send the POST out
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(postData);
            out.close();

            int statusCode = conn.getResponseCode();
            if(statusCode == 200){
                System.out.println(conn.getResponseMessage());
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
                    return result.getString("success");
                }
            }else{
                return "Something went wrong";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Something went wrong";
        } catch (ProtocolException e) {
            e.printStackTrace();
            return "Something went wrong";
        } catch (IOException e) {
            e.printStackTrace();
            return "Something went wrong";
        } catch (JSONException e) {
            e.printStackTrace();
            return "Something went wrong";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mainView.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        callback.done(result);
    }
}
