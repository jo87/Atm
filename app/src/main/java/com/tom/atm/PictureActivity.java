package com.tom.atm;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class PictureActivity extends AppCompatActivity {

    private static final int REQUEST_STORAGE = 110;
    private GridView grid;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        grid = (GridView) findViewById(R.id.grid);
        int permission = ActivityCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE);
        if (permission== PackageManager.PERMISSION_GRANTED){
            readThumbnails();
//            readPictures();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE},
            REQUEST_STORAGE);
        }
    }

    private void readThumbnails() {
        Cursor c = getContentResolver().query(Thumbnails.EXTERNAL_CONTENT_URI,
                null, null, null, null);
        String[] from = {Thumbnails.DATA, Thumbnails.IMAGE_ID};
        int[] to = {R.id.thumb_image, R.id.thumb_text};
        adapter = new SimpleCursorAdapter(this,
                R.layout.thumb_item, c, from , to, 0);
        grid.setAdapter(adapter);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_STORAGE &&
                grantResults[0]==PackageManager.PERMISSION_GRANTED){
//            readPictures();
            readThumbnails();
        }
    }

    private void readPictures() {
        ContentResolver cr = getContentResolver();
        Cursor c =
                cr.query(Media.EXTERNAL_CONTENT_URI,null, null, null, null, null);
        while (c.moveToNext()){
            int id = c.getInt(c.getColumnIndex(Media._ID));
            String name = c.getString(c.getColumnIndex(Media.DISPLAY_NAME));
            String data = c.getString(c.getColumnIndex(Media.DATA));
            Log.d("CC", id+","+name+","+data);
        }
        Cursor c2 = cr.query(Thumbnails.EXTERNAL_CONTENT_URI,null, null, null, null, null);
        while (c2.moveToNext()){
            int id = c2.getInt(c2.getColumnIndex(Thumbnails._ID));
            String imageId = c2.getString(c2.getColumnIndex(Thumbnails.IMAGE_ID));
            String data = c2.getString(c2.getColumnIndex(Thumbnails.DATA));
            Log.d("CC", id+","+imageId+","+data);
        }

    }
}
