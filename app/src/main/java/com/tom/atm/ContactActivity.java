package com.tom.atm;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(Contacts.CONTENT_URI, null, null, null, null);
        while(c.moveToNext()){
            String name = c.getString(c.getColumnIndex(Contacts.DISPLAY_NAME));
            int id = c.getInt(c.getColumnIndex(Contacts._ID));
            Log.d("CC", id+"/"+name);
        }

    }
}
