package com.betagro.adulvitc.aquaculturewatertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail2Activity extends AppCompatActivity {

    private ImageView innerImageView;
    private EditText valueEditText;
    private Button nextButton, backButton;
    private TextView titleTextView, unitTextView,detailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        final ImageView innerImageView = (ImageView) findViewById(R.id.imgInner);
        final EditText valueEditText = (EditText) findViewById(R.id.edtValue);
        final Button nextButton = (Button) findViewById(R.id.btnNext);
        final Button backButton = (Button) findViewById(R.id.btnBack);
        final TextView titleTextView = (TextView) findViewById(R.id.txtTitle);
        final TextView unitTextView = (TextView) findViewById(R.id.txtUnit);
        final TextView detailTextView = (TextView) findViewById(R.id.txtDetail);


        final int imageInts = getIntent().getIntExtra("imgID",R.drawable.do2);
        innerImageView.setImageResource(imageInts);

        final int intIndex = getIntent().getIntExtra("Unit", 0);
        String[] detailStrings = getResources().getStringArray(R.array.unit);
        unitTextView.setText(detailStrings[intIndex]);

        final int titleIndex = getIntent().getIntExtra("title", 0);
        String[] titleStrings = getResources().getStringArray(R.array.title);
        titleTextView.setText(titleStrings[titleIndex]);



        final int longDetail = getIntent().getIntExtra("longDetail", 0);
        final String[] longStrings = getResources().getStringArray(R.array.long_detail);

        final int subtitle = getIntent().getIntExtra("subtitle", 0);
        String[] subtitleStrings = getResources().getStringArray(R.array.subtitle);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detail2Activity.this, DetailActivity.class);
                intent.putExtra("title", titleIndex);
                intent.putExtra("imgID", imageInts);
                intent.putExtra("longDetail", longDetail);
                intent.putExtra("subtitle", subtitle);

                startActivity(intent);


            }
        });
        */


    }   //Main Method


}   //Main Class
