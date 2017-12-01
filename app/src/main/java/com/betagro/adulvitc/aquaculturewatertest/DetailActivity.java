package com.betagro.adulvitc.aquaculturewatertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private ImageView innerImageView,detail1ImageView, detail12ImageView,detail13ImageView;
    private Button nextButton;
    private TextView titleTextView, subtitleTextView, detailTextView,detail2TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final ImageView innerImageView = (ImageView) findViewById(R.id.imgInner);
        final ImageView detail1ImageView = (ImageView) findViewById(R.id.imgDetail1);
        final ImageView detail2ImageView = (ImageView) findViewById(R.id.imgDetail2);
        final ImageView detail3ImageView = (ImageView) findViewById(R.id.imgDetail3);
        final Button nextButton = (Button) findViewById(R.id.btnNext);
        final TextView titleTextView = (TextView) findViewById(R.id.txtTitle);
        final TextView subtitleTextView = (TextView) findViewById(R.id.txtSubTitle);
        final TextView detailTextView = (TextView) findViewById(R.id.txtDetail);
        final TextView detail2TextView = (TextView) findViewById(R.id.txtDetail2);
        final TextView detail3TextView = (TextView) findViewById(R.id.txtDetail3);

        final int imageInts = getIntent().getIntExtra("imgID",R.drawable.do2);
        innerImageView.setImageResource(imageInts);

        final int detail1Ints = getIntent().getIntExtra("detail1IMG",R.drawable.detail_do1);
        detail1ImageView.setImageResource(detail1Ints);

        final int detail2Ints = getIntent().getIntExtra("detail2IMG",R.drawable.detail_do2);
        detail2ImageView.setImageResource(detail2Ints);

        final int detail3Ints = getIntent().getIntExtra("detail3IMG",R.drawable.detail_alk3);
        detail3ImageView.setImageResource(detail3Ints);


        final int titleIndex = getIntent().getIntExtra("title", 0);
        String[] titleStrings = getResources().getStringArray(R.array.title);
        titleTextView.setText(titleStrings[titleIndex]);

        final int intLongdetail = getIntent().getIntExtra("longDetail", 0);
        String[] longStrings = getResources().getStringArray(R.array.long_detail);
        detailTextView.setText(longStrings[intLongdetail]);

        final int intLongdetail2 = getIntent().getIntExtra("longDetail2", 0);
        String[] longStrings2 = getResources().getStringArray(R.array.long_detail2);
        detail2TextView.setText(longStrings2[intLongdetail2]);

        final int intLongdetail3 = getIntent().getIntExtra("longDetail3", 0);
        String[] longStrings3 = getResources().getStringArray(R.array.long_detail3);
        detail3TextView.setText(longStrings3[intLongdetail3]);

        final int subtitle = getIntent().getIntExtra("subtitle", 0);
        String[] subtitleStrings = getResources().getStringArray(R.array.subtitle);
        subtitleTextView.setText(subtitleStrings[subtitle]);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, Detail2Activity.class);

                startActivity(intent);
            }
        });






    }   //Main Method


}   //Main Class
