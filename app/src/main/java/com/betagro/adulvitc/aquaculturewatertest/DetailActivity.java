package com.betagro.adulvitc.aquaculturewatertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private ImageView innerImageView;
    private EditText valEditText;
    private Button nextButton, backButton;
    private TextView unitTextView, titleTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        final ImageView innerImageView = (ImageView) findViewById(R.id.imvInner);
        final EditText valEditText = (EditText) findViewById(R.id.edtValue);
        final Button nextButton = (Button) findViewById(R.id.btnNext);
        final Button backButton = (Button) findViewById(R.id.btnBack);
        final TextView unitTextView = (TextView) findViewById(R.id.txtUnit);
        final TextView titleTextView = (TextView) findViewById(R.id.txtTitle);


        int imageInts = getIntent().getIntExtra("imgID",R.drawable.do2);
        innerImageView.setImageResource(imageInts);

        final int intIndex = getIntent().getIntExtra("Unit", 0);
        String[] detailStrings = getResources().getStringArray(R.array.unit);
        unitTextView.setText(detailStrings[intIndex]);

        final int titleIndex = getIntent().getIntExtra("title", 0);
        String[] titleStrings = getResources().getStringArray(R.array.title);
        titleTextView.setText(titleStrings[titleIndex]);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();


            }
        });




    }   //Main Method


}   //Main Class
