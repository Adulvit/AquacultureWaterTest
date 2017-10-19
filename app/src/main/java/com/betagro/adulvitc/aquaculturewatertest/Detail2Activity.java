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


    private EditText doEditText,TempEditText,pHEditText,AlkEditText,
            AmmoEditText,NiteEditText,HardEditText,TurbEditText;
    private Button nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);


        final EditText doEditText = (EditText) findViewById(R.id.edtValueDo);
        final EditText TempEditText = (EditText) findViewById(R.id.edtValueTem);
        final EditText pHEditText = (EditText) findViewById(R.id.edtValuepH);
        final EditText AlkEditText = (EditText) findViewById(R.id.edtValueAlk);
        final EditText AmmoEditText = (EditText) findViewById(R.id.edtValueAmmo);
        final EditText NiteEditText = (EditText) findViewById(R.id.edtValueNite);
        final EditText HardEditText = (EditText) findViewById(R.id.edtValueHard);
        final EditText TurbEditText = (EditText) findViewById(R.id.edtValueTurb);

        final Button nextButton = (Button) findViewById(R.id.btnNext);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);











    }   //Main Method


}   //Main Class
