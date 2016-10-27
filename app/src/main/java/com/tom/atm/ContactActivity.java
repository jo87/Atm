package com.tom.atm;

import static android.Manifest.permission.*;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ContactActivity extends AppCompatActivity {

    private static final int REQUEST_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        int permission =
                ActivityCompat.checkSelfPermission(this, READ_CONTACTS);
        if (permission != PackageManager.PERMISSION_GRANTED){
            //未
            ActivityCompat.requestPermissions(this,
                    new String[]{READ_CONTACTS},
                    REQUEST_CONTACTS );
        }else{
            //已
            readContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode==REQUEST_CONTACTS){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                readContacts();
            }
        }
    }

    private void readContacts() {
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(Contacts.CONTENT_URI, null, null, null, null);
        while(c.moveToNext()){
            String name = c.getString(c.getColumnIndex(Contacts.DISPLAY_NAME));
            int id = c.getInt(c.getColumnIndex(Contacts._ID));
            Log.d("CC", id+"/"+name);
        }
    }
}
