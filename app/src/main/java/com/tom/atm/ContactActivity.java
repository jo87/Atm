package com.tom.atm;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        int permission =
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (permission != PackageManager.PERMISSION_GRANTED){
            //未
            
        }else{
            //已
            readContacts();
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
