package com.tom.atm;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    private Cursor cursor;
    private GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detector = new GestureDetector(this, this);
        int pos = getIntent().getIntExtra("POSITION", -1);
        CursorLoader cursorLoader = new CursorLoader(this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);
        cursor = cursorLoader.loadInBackground();
        cursor.moveToPosition(pos);
        updateImage();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    private void updateImage() {
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("GEST", "onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d("GEST", "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("GEST", "onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d("GEST", "onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("GEST", "onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("GEST", "onFling");
        float distance = e2.getX()-e1.getX();
        if (distance > 100){ // to the right
            if (!cursor.moveToPrevious()){
                cursor.moveToLast();
            }
            updateImage();
        }else if (distance<-100){ // to the left
            if (!cursor.moveToNext()){
                cursor.moveToFirst();
            }
            updateImage();
        }
        return false;
    }
}
