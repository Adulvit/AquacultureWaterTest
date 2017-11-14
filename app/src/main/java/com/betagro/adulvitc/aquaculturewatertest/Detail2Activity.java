package com.betagro.adulvitc.aquaculturewatertest;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail2Activity extends AppCompatActivity {


    private EditText FarmNameEditText, PondEditText, DOEditText, TempEditText, pHEditText, AlkEditText,
            AmmoEditText, NiteEditText, HardEditText, TurbEditText;
    private Button nextButton;
    private String farmnameString, pondString, TempString, pHString, TANString;


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

                //TempString = TempEditText.getText().toString().trim();

                final AlertDialog.Builder ad = new AlertDialog.Builder(Detail2Activity.this);


//                    กรณีไม่กรอกชื่อฟาร์มหรือชื่อบ่อ แต่กรอกค่าพารามิเตอร์อย่างน้อย 1 อย่าง
                if (FarmNameEditText.getText().length() == 0 || PondEditText.getText().length() == 0) {

                    if ((DOEditText.getText().length() != 0) || (TempEditText.getText().length() != 0) ||
                            (pHEditText.getText().length() != 0) || (AlkEditText.getText().length() != 0) ||
                            (AmmoEditText.getText().length() != 0) || (NiteEditText.getText().length() != 0) ||
                            (HardEditText.getText().length() != 0) || (TurbEditText.getText().length() != 0)) {

                        ad.setTitle("Error! ");
                        ad.setIcon(R.drawable.icon_question);
                        ad.setNegativeButton("Close", null);
                        ad.setMessage("Please insert NameFarm and Pond");
                        ad.show();
                        return;

//                    กรณีไม่กรอกชื่อฟาร์มหรือชื่อบ่อ หรือค่าคุณภาพน้ำใดๆทั้งสิ้น Every All of Blank
                    } else if ((DOEditText.getText().length() == 0) || (TempEditText.getText().length() == 0) ||
                            (pHEditText.getText().length() == 0) || (AlkEditText.getText().length() == 0) ||
                            (AmmoEditText.getText().length() == 0) || (NiteEditText.getText().length() == 0) ||
                            (HardEditText.getText().length() == 0) || (TurbEditText.getText().length() == 0)) {

                        ad.setTitle("Error! ");
                        ad.setIcon(R.drawable.icon_question);
                        ad.setNegativeButton("Close", null);
                        ad.setMessage("Please Insert NameFarm, Pond No. and Data of Water Quality");
                        ad.show();
                        return;
                    }


                } else if (FarmNameEditText.getText().length() != 0 || PondEditText.getText().length() != 0) {

//                Every All Blank
                    if ((DOEditText.getText().length() == 0) && (TempEditText.getText().length() == 0) &&
                            (pHEditText.getText().length() == 0) && (AlkEditText.getText().length() == 0) &&
                            (AmmoEditText.getText().length() == 0) && (NiteEditText.getText().length() == 0) &&
                            (HardEditText.getText().length() == 0) && (TurbEditText.getText().length() == 0)) {

                        ad.setTitle("Error! ");
                        ad.setIcon(R.drawable.icon_question);
                        ad.setNegativeButton("Close", null);
                        ad.setMessage("Please Insert Data of Water Quality");
                        ad.show();
                        return;

/*
//                Normal Case Intent
                    } else if ((DOEditText.getText().length() != 0) || (TempEditText.getText().length() != 0) ||
                            (pHEditText.getText().length() != 0) || (AlkEditText.getText().length() != 0) ||
                            (AmmoEditText.getText().length() != 0) || (NiteEditText.getText().length() != 0) ||
                            (HardEditText.getText().length() != 0) || (TurbEditText.getText().length() != 0)) {


                        Intent intent = new Intent(Detail2Activity.this, PieChartActivity.class);
                        intent.putExtra("FarmName", FarmNameEditText.getText().toString());
                        intent.putExtra("Pond", PondEditText.getText().toString());
                        intent.putExtra("DO1", DOEditText.getText().toString());
                        intent.putExtra("Temp1", TempEditText.getText().toString());
                        intent.putExtra("pH1", pHEditText.getText().toString());
                        intent.putExtra("Alk1", AlkEditText.getText().toString());
                        intent.putExtra("Ammo1", AmmoEditText.getText().toString());
                        intent.putExtra("Nit1", NiteEditText.getText().toString());
                        intent.putExtra("Hard1", HardEditText.getText().toString());
                        intent.putExtra("Turb1", TurbEditText.getText().toString());
                        startActivity(intent);
*/

//                Temp not Input
                    } else if ((TempEditText.getText().length() != 0) || (DOEditText.getText().length() != 0) ||
                            (pHEditText.getText().length() != 0) || (AlkEditText.getText().length() != 0) ||
                            (AmmoEditText.getText().length() != 0) || (NiteEditText.getText().length() != 0) ||
                            (HardEditText.getText().length() != 0) || (TurbEditText.getText().length() != 0)) {


                        //String Tempstr = TempEditText.getText().toString();
                        //double TempNumber = Tempstr != null && Tempstr.trim().length() > 0 ? Double.parseDouble(Tempstr) : 25;
                        //double Temp2Number = (TempNumber + 25);


                        Intent intent = new Intent(Detail2Activity.this, PieChartActivity.class);
                        intent.putExtra("FarmName", FarmNameEditText.getText().toString());
                        intent.putExtra("Pond", PondEditText.getText().toString());
                        intent.putExtra("DO1", DOEditText.getText().toString());
                        intent.putExtra("Temp1", TempEditText.getText().toString());
                        intent.putExtra("pH1", pHEditText.getText().toString());
                        intent.putExtra("Alk1", AlkEditText.getText().toString());
                        intent.putExtra("Ammo1", AmmoEditText.getText().toString());
                        intent.putExtra("Nit1", NiteEditText.getText().toString());
                        intent.putExtra("Hard1", HardEditText.getText().toString());
                        intent.putExtra("Turb1", TurbEditText.getText().toString());
                        startActivity(intent);

                    }


                }
            }

        });

    }   //Main Method


}   //Main Class
