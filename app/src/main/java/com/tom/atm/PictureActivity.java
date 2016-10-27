package com.tom.atm;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class PictureActivity extends AppCompatActivity {

    private static final int REQUEST_STORAGE = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        int permission = ActivityCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE);
        if (permission== PackageManager.PERMISSION_GRANTED){
            readPictures();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE},
            REQUEST_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_STORAGE &&
                grantResults[0]==PackageManager.PERMISSION_GRANTED){
            readPictures();
        }
    }

    private void readPictures() {
        ContentResolver cr = getContentResolver();
        Cursor c =
                cr.query(Media.EXTERNAL_CONTENT_URI,null, null, null, null, null);
    }
}
