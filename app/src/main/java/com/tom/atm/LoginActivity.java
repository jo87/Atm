package com.tom.atm;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private EditText edUserid;
    private EditText edPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        String userid = getSharedPreferences("atm", MODE_PRIVATE)
                .getString("PREF_USERID", "");
        edUserid.setText(userid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_help:
                Log.d(TAG, "action_help");
                break;
            case R.id.action_blabla:
                Log.d(TAG, "action_blabla");
                break;
            case R.id.action_settings:
                Log.d(TAG, "action_settings");
                break;
        }
        return false;
    }

    private void findViews() {
        edUserid = (EditText) findViewById(R.id.userid);
        edPasswd = (EditText) findViewById(R.id.passwd);
    }

    public void login(View v){
        String userid = edUserid.getText().toString();
        String passwd = edPasswd.getText().toString();
        try {
            URL url = new URL("http://atm201605.appspot.com/login?uid="+
                    userid+"&pw="+passwd);
            InputStream in = url.openStream();
            int data = in.read();
            Log.d(TAG, "data:"+data);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*if (userid.equals("jack") && passwd.equals("1234")){
            SharedPreferences pref = getSharedPreferences("atm", MODE_PRIVATE);
            pref.edit()
                    .putString("PREF_USERID", userid)
                    .commit();

            setResult(RESULT_OK);
            finish();
        }else{
        }*/
    }
    public void cancel(View v){

    }
}
