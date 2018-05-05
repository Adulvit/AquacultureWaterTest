package com.betagro.adulvitc.aquaculturewatertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;

public class PhotoView3Activity extends AppCompatActivity {

    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view3);

        final PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);

        final int imageInts = getIntent().getIntExtra("detail3IMG", R.drawable.alk3);
        photoView.setImageResource(imageInts);


    }   //Main Method


}   //Main Class
