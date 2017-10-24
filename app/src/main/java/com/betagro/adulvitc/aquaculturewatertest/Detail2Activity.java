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


    private EditText FarmNameEditText,PondEditText,DOEditText,TempEditText,pHEditText,AlkEditText,
            AmmoEditText,NiteEditText,HardEditText,TurbEditText;
    private Button nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        final EditText PondEditText = (EditText) findViewById(R.id.txtPond);
        final EditText FarmNameEditText = (EditText) findViewById(R.id.txtFarm);
        final EditText DOEditText = (EditText) findViewById(R.id.edtValueDo);
        final EditText TempEditText = (EditText) findViewById(R.id.edtValueTem);
        final EditText pHEditText = (EditText) findViewById(R.id.edtValuepH);
        final EditText AlkEditText = (EditText) findViewById(R.id.edtValueAlk);
        final EditText AmmoEditText = (EditText) findViewById(R.id.edtValueAmmo);
        final EditText NiteEditText = (EditText) findViewById(R.id.edtValueNite);
        final EditText HardEditText = (EditText) findViewById(R.id.edtValueHard);
        final EditText TurbEditText = (EditText) findViewById(R.id.edtValueTurb);

        final Button nextButton = (Button) findViewById(R.id.btnNext);




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detail2Activity.this, PieChartActivity.class);
                intent.putExtra("Pond", PondEditText.getText().toString());
                intent.putExtra("FarmName", FarmNameEditText.getText().toString());
                startActivity(intent);
            }
        });











    }   //Main Method


}   //Main Class
