package com.tom.atm;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class StockActivity extends AppCompatActivity {
    private static final String TAG = "StockActivity";
    Handler handler = new Handler();
    Runnable job = new Runnable() {
        @Override
        public void run() {
            new StockTask().execute("2330", "3008", "2498", "1201", "1202");
        }
    };
    //http://finance.google.com/finance/info?client=ig&q=TPE:3008,2330,2498,1201
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        new StockTask().execute("2330", "3008", "2498", "1201", "1202");
    }
    class StockTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            StringBuilder sb = new StringBuilder("http://finance.google.com/finance/info?client=ig&q=TPE:");
            for (String sid : params){
                sb.append(sid);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            try {
                URL url = new URL(sb.toString());
                sb = new StringBuilder();
                InputStream is = url.openStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader in = new BufferedReader(isr);
                String line = in.readLine();
                while (line != null) {
//                    Log.d(TAG, "line:" + line);
                    sb.append(line);
                    line = in.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            String json = s.substring(3);
            try {
                JSONArray array = new JSONArray(json);
                for (int i=0; i<array.length();i++){
                    JSONObject obj = array.getJSONObject(i);
                    String symbol = obj.getString("t");
                    String current = obj.getString("l");
                    String lt = obj.getString("lt");
                    String change = obj.getString("c");
                    Log.d(TAG, symbol+"/"+current+"/"+lt+"/"+change);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            handler.postDelayed(job, 5000);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(job);
    }
}
