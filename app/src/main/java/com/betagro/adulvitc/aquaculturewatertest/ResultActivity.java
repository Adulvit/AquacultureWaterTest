package com.betagro.adulvitc.aquaculturewatertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;


public class ResultActivity extends AppCompatActivity {

    private TextView DOTextView,TempTextView,pHTextView,AlkTextView,NH3TextView,NitTextView,HardTextView,TurbTextView;
    private TextView RSDoTextView,RSTempTextView,RSpHTextView,RSAlkTextView,RSNH3TextView,RSNitTextView,RSHardTextView,RSTurbTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final TextView DOTextView = (TextView) findViewById(R.id.ValueDo);
        final TextView TempTextView = (TextView) findViewById(R.id.ValueTemp);
        final TextView pHTextView = (TextView) findViewById(R.id.ValuepH);
        final TextView AlkTextView = (TextView) findViewById(R.id.ValueAlk);
        final TextView NH3TextView = (TextView) findViewById(R.id.ValueTAN);
        final TextView NitTextView = (TextView) findViewById(R.id.ValueNit);
        final TextView HardTextView = (TextView) findViewById(R.id.ValueHard);
        final TextView TurbTextView = (TextView) findViewById(R.id.ValueTurb);

        final TextView RSDoTextView = (TextView) findViewById(R.id.DoResult);
        final TextView RSTempTextView = (TextView) findViewById(R.id.TempResult);
        final TextView RSpHTextView = (TextView) findViewById(R.id.pHResult);
        final TextView RSAlkTextView = (TextView) findViewById(R.id.AlkResult);
        final TextView RSNH3TextView = (TextView) findViewById(R.id.TANResult);
        final TextView RSNitTextView = (TextView) findViewById(R.id.NitriteResult);
        final TextView RSHardTextView = (TextView) findViewById(R.id.HardResult);
        final TextView RSTurbTextView = (TextView) findViewById(R.id.TurbResult);





        // Date&Time
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(10);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.date);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy\nhh:mm a");
                                String dateString = sdf.format(date);
                                tdate.setText(dateString);

                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }

        };
        t.start();


//        Recieve Value/ Show from PieChart Activity

        Intent DOIndex = getIntent();
        String DOstr = DOIndex.getStringExtra("DO");
        DOTextView.setText(DOstr);

        Intent TempIndex = getIntent();
        String Tempstr = TempIndex.getStringExtra("Temp");
        TempTextView.setText(Tempstr);

        Intent pHIndex = getIntent();
        String pHstr = pHIndex.getStringExtra("pH");
        pHTextView.setText(pHstr);

        Intent AklIndex = getIntent();
        String Aklstr = AklIndex.getStringExtra("Akl");
        AlkTextView.setText(Aklstr);

        Intent NH3Index = getIntent();
        String NH3str = NH3Index.getStringExtra("NH3");
        NH3TextView.setText(NH3str);

        Intent NitIndex = getIntent();
        String Nitstr = NitIndex.getStringExtra("Nit");
        NitTextView.setText(Nitstr);

        Intent HardIndex = getIntent();
        String Hardstr = HardIndex.getStringExtra("Hard");
        HardTextView.setText(Hardstr);

        Intent TurbIndex = getIntent();
        String Turbstr = TurbIndex.getStringExtra("Turb");
        TurbTextView.setText(Turbstr);

        //  Set Text from Data

        double DONumber = DOstr != null && DOstr.trim().length() > 0 ? Double.parseDouble(DOstr) : 0;
        double TempNumber = Tempstr != null && Tempstr.trim().length() > 0 ? Double.parseDouble(Tempstr) : 25;
        double pHNumber = pHstr != null && pHstr.trim().length() > 0 ? Double.parseDouble(pHstr) : 0;
        double AklNumber = Aklstr != null && Aklstr.trim().length() > 0 ? Double.parseDouble(Aklstr) : 0;
        double NH3Number = NH3str != null && NH3str.trim().length() > 0 ? Double.parseDouble(NH3str) : 0;
        double NitNumber = Nitstr != null && Nitstr.trim().length() > 0 ? Double.parseDouble(Nitstr) : 0;
        double HardNumber = Hardstr != null && Hardstr.trim().length() > 0 ? Double.parseDouble(Hardstr) : 0;
        double TurbNumber = Turbstr != null && Turbstr.trim().length() > 0 ? Double.parseDouble(Turbstr) : 0;




        //  DO
        if (DONumber <= 1.0) {
            final int intIndex = getIntent().getIntExtra("result",0);
            String[] ValueStrings = getResources().getStringArray(R.array.result);
            RSDoTextView.setText(ValueStrings[intIndex]);


        } else if (DONumber > 1.0 & DONumber <= 5.0) {
            final int intIndex = getIntent().getIntExtra("result",1);
            String[] ValueStrings = getResources().getStringArray(R.array.result);
            RSDoTextView.setText(ValueStrings[intIndex]);


        }else if (DONumber > 5.0 & DONumber <= 8.0) {
            final int intIndex = getIntent().getIntExtra("result", 2);
            String[] ValueStrings = getResources().getStringArray(R.array.result);
            RSDoTextView.setText(ValueStrings[intIndex]);

        }else if (DONumber > 8.0 ) {
            final int intIndex = getIntent().getIntExtra("result", 3);
            String[] ValueStrings = getResources().getStringArray(R.array.result);
            RSDoTextView.setText(ValueStrings[intIndex]);
        }


        //  Temperature
        if (TempNumber <= 15.0) {
            final int intIndex = getIntent().getIntExtra("result_temp", 0);
            String[] ValueStrings = getResources().getStringArray(R.array.result_temp);
            RSTempTextView.setText(ValueStrings[intIndex]);


        } else if (TempNumber > 15.0 & TempNumber <= 25.0) {
            final int intIndex = getIntent().getIntExtra("result_temp", 1);
            String[] ValueStrings = getResources().getStringArray(R.array.result_temp);
            RSTempTextView.setText(ValueStrings[intIndex]);


        } else if (TempNumber > 25.0 & TempNumber <= 33.0) {
            final int intIndex = getIntent().getIntExtra("result_temp", 2);
            String[] ValueStrings = getResources().getStringArray(R.array.result_temp);
            RSTempTextView.setText(ValueStrings[intIndex]);


        } else if (TempNumber > 33.0) {
            final int intIndex = getIntent().getIntExtra("result_temp", 3);
            String[] ValueStrings = getResources().getStringArray(R.array.result_temp);
            RSTempTextView.setText(ValueStrings[intIndex]);


        }

        //pH
        if (pHNumber <= 4.5) {
            final int intIndex = getIntent().getIntExtra("result_ph", 0);
            String[] ValueStrings = getResources().getStringArray(R.array.result_ph);
            RSpHTextView.setText(ValueStrings[intIndex]);


        }else if (pHNumber > 4.5 & pHNumber <= 6.5) {
            final int intIndex = getIntent().getIntExtra("result_ph", 1);
            String[] ValueStrings = getResources().getStringArray(R.array.result_ph);
            RSpHTextView.setText(ValueStrings[intIndex]);

        } else if (pHNumber > 6.5 & pHNumber <= 9.5) {
            final int intIndex = getIntent().getIntExtra("result_ph", 2);
            String[] ValueStrings = getResources().getStringArray(R.array.result_ph);
            RSpHTextView.setText(ValueStrings[intIndex]);

        } else if (pHNumber > 9.5) {
            final int intIndex = getIntent().getIntExtra("result_ph", 3);
            String[] ValueStrings = getResources().getStringArray(R.array.result_ph);
            RSpHTextView.setText(ValueStrings[intIndex]);

        }

        // Alkalinity
        if (AklNumber <= 50) {
            final int intIndex = getIntent().getIntExtra("result_alk", 0);
            String[] ValueStrings = getResources().getStringArray(R.array.result_alk);
            RSAlkTextView.setText(ValueStrings[intIndex]);


        }else if (AklNumber > 50 & AklNumber <= 100) {
            final int intIndex = getIntent().getIntExtra("result_alk", 1);
            String[] ValueStrings = getResources().getStringArray(R.array.result_alk);
            RSAlkTextView.setText(ValueStrings[intIndex]);

        } else if (AklNumber > 100 & AklNumber <= 180) {
            final int intIndex = getIntent().getIntExtra("result_alk", 2);
            String[] ValueStrings = getResources().getStringArray(R.array.result_alk);
            RSAlkTextView.setText(ValueStrings[intIndex]);

        } else if (AklNumber > 180) {
            final int intIndex = getIntent().getIntExtra("result_alk", 3);
            String[] ValueStrings = getResources().getStringArray(R.array.result_alk);
            RSAlkTextView.setText(ValueStrings[intIndex]);

        }

        // NH3
        if (NH3Number <= 0.1) {
            final int intIndex = getIntent().getIntExtra("result_tan", 0);
            String[] ValueStrings = getResources().getStringArray(R.array.result_tan);
            RSNH3TextView.setText(ValueStrings[intIndex]);


        } else if (NH3Number > 0.1 & NH3Number <= 0.2) {
            final int intIndex = getIntent().getIntExtra("result_alk", 1);
            String[] ValueStrings = getResources().getStringArray(R.array.result_alk);
            RSNH3TextView.setText(ValueStrings[intIndex]);

        } else if (NH3Number > 0.2) {
            final int intIndex = getIntent().getIntExtra("result_alk", 2);
            String[] ValueStrings = getResources().getStringArray(R.array.result_alk);
            RSNH3TextView.setText(ValueStrings[intIndex]);

        }


        // Nitrite
        if (NitNumber <= 0.1) {
            final int intIndex = getIntent().getIntExtra("result_nit", 0);
            String[] ValueStrings = getResources().getStringArray(R.array.result_nit);
            RSNitTextView.setText(ValueStrings[intIndex]);


        } else if (NitNumber > 0.1 & NitNumber <= 0.3) {
            final int intIndex = getIntent().getIntExtra("result_nit", 1);
            String[] ValueStrings = getResources().getStringArray(R.array.result_nit);
            RSNitTextView.setText(ValueStrings[intIndex]);

        }else if (NitNumber > 0.3) {
            final int intIndex = getIntent().getIntExtra("result_nit", 2);
            String[] ValueStrings = getResources().getStringArray(R.array.result_nit);
            RSNitTextView.setText(ValueStrings[intIndex]);

        }

        // Hardness
        if (HardNumber <= 80) {
            final int intIndex = getIntent().getIntExtra("result_hard", 0);
            String[] ValueStrings = getResources().getStringArray(R.array.result_hard);
            RSHardTextView.setText(ValueStrings[intIndex]);


        } else if (HardNumber > 80 & HardNumber <= 200) {
            final int intIndex = getIntent().getIntExtra("result_hard", 1);
            String[] ValueStrings = getResources().getStringArray(R.array.result_hard);
            RSHardTextView.setText(ValueStrings[intIndex]);

        } else if (HardNumber > 200) {
            final int intIndex = getIntent().getIntExtra("result_hard", 2);
            String[] ValueStrings = getResources().getStringArray(R.array.result_hard);
            RSHardTextView.setText(ValueStrings[intIndex]);

        }

        // Turbidity
        if (TurbNumber <= 20) {
            final int intIndex = getIntent().getIntExtra("result_turb", 0);
            String[] ValueStrings = getResources().getStringArray(R.array.result_turb);
            RSTurbTextView.setText(ValueStrings[intIndex]);


        } else if (TurbNumber > 20 & TurbNumber <= 30) {
            final int intIndex = getIntent().getIntExtra("result_turb", 1);
            String[] ValueStrings = getResources().getStringArray(R.array.result_turb);
            RSTurbTextView.setText(ValueStrings[intIndex]);

        } else if (TurbNumber > 30 & TurbNumber <= 100) {
            final int intIndex = getIntent().getIntExtra("result_turb", 2);
            String[] ValueStrings = getResources().getStringArray(R.array.result_turb);
            RSTurbTextView.setText(ValueStrings[intIndex]);

        } else if (TurbNumber > 100) {
            final int intIndex = getIntent().getIntExtra("result_turb", 3);
            String[] ValueStrings = getResources().getStringArray(R.array.result_turb);
            RSTurbTextView.setText(ValueStrings[intIndex]);

        }



    }   //Main Method




}   //Main Class
