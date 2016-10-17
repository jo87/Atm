package com.tom.atm;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.data;

public class TransActivity extends AppCompatActivity {

    private static final String TAG = "TransActivity";
    OkHttpClient client = new OkHttpClient();
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);
        list = (ListView) findViewById(R.id.list);
        //http://atm201605.appspot.com/h
//        new TransTask().execute("http://atm201605.appspot.com/h");
        //OkHttp
        Request request = new Request.Builder()
                .url("http://atm201605.appspot.com/h")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.d(TAG, "JSON:"+json);
                parseJSON(json);
            }
        });
    }

    class TransTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(params[0]);
                InputStream is = url.openStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader in = new BufferedReader(isr);
                String line = in.readLine();
                while (line!=null){
                    Log.d(TAG, "line:"+line);
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
            Log.d(TAG, "JSON:"+s);
            parseJSON(s);
        }
    }

    private void parseJSON(String s) {
        try {
            ArrayList<Map<String, String>> data = new ArrayList();
            JSONArray array = new JSONArray(s);
            for (int i=0; i<array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                String date = obj.getString("date");
                int amount = obj.getInt("amount");
                int type = obj.getInt("type");
                Log.d(TAG, "OBJ:"+date+"/"+amount+"/"+type);
                Map<String, String> row = new HashMap<>();
                row.put("date", date);
                row.put("amount", amount+"");
                row.put("type", type+"");
                data.add(row);
            }
            int[] to = {R.id.tran_date, R.id.tran_amount, R.id.tran_type};
            String[] from = {"date", "amount", "type"};
            final SimpleAdapter adapter = new SimpleAdapter(this,
                    data,
                    R.layout.trans_row
                    , from, to);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    list.setAdapter(adapter);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
