package com.betagro.adulvitc.aquaculturewatertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private ImageView innerImageView;
    private Button nextButton, backButton;
    private TextView titleTextView, subtitleTextView, detailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final ImageView innerImageView = (ImageView) findViewById(R.id.imgInner);
        final Button nextButton = (Button) findViewById(R.id.btnNext);
        final Button backButton = (Button) findViewById(R.id.btnBack);
        final TextView titleTextView = (TextView) findViewById(R.id.txtTitle);
        final TextView subtitleTextView = (TextView) findViewById(R.id.txtSubTitle);
        final TextView detailTextView = (TextView) findViewById(R.id.txtDetail);

        final int imageInts = getIntent().getIntExtra("imgID",R.drawable.do2);
        innerImageView.setImageResource(imageInts);


        final int titleIndex = getIntent().getIntExtra("title", 0);
        String[] titleStrings = getResources().getStringArray(R.array.title);
        titleTextView.setText(titleStrings[titleIndex]);

        final int intLongdetail = getIntent().getIntExtra("longDetail", 0);
        String[] longStrings = getResources().getStringArray(R.array.long_detail);
        detailTextView.setText(longStrings[intLongdetail]);

        final int subtitle = getIntent().getIntExtra("subtitle", 0);
        String[] subtitleStrings = getResources().getStringArray(R.array.subtitle);
        subtitleTextView.setText(subtitleStrings[subtitle]);



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }   //Main Method


}   //Main Class
