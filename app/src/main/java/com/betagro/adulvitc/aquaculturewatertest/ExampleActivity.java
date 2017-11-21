package com.betagro.adulvitc.aquaculturewatertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ExampleActivity extends AppCompatActivity {

    private ImageView innerImageView, detailImageView;
    private Button nextButton;
    private TextView titleTextView, subtitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        final ImageView innerImageView = (ImageView) findViewById(R.id.imgInner);
        final Button nextButton = (Button) findViewById(R.id.btnNext);
        final TextView titleTextView = (TextView) findViewById(R.id.txtTitle);
        final TextView subtitleTextView = (TextView) findViewById(R.id.txtSubTitle);
        final ImageView detailImageView = (ImageView) findViewById(R.id.imvDetail);

        final int imageInts = getIntent().getIntExtra("imgID",R.drawable.do2);
        innerImageView.setImageResource(imageInts);


        final int titleIndex = getIntent().getIntExtra("title", 0);
        String[] titleStrings = getResources().getStringArray(R.array.title);
        titleTextView.setText(titleStrings[titleIndex]);




        final int subtitle = getIntent().getIntExtra("subtitle", 0);
        String[] subtitleStrings = getResources().getStringArray(R.array.subtitle);
        subtitleTextView.setText(subtitleStrings[subtitle]);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExampleActivity.this, Detail2Activity.class);

                startActivity(intent);
            }
        });






    }   //Main Method


}   //Main Class

