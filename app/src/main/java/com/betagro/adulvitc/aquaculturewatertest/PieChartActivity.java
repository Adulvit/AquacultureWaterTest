package com.betagro.adulvitc.aquaculturewatertest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {


    private TextView FarmNameTextView, PondTextView, NH3TextView, NH4TextView;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);


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


        pieChart = (PieChart) findViewById(R.id.piechart);

        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);


        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(45f);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(40);
        pieChart.setCenterText("developed by Adulvit\nTechnical Service D.\nBetagro Group");
        pieChart.setCenterTextSize(13);
        pieChart.setTouchEnabled(false);


        ArrayList<PieEntry> yValue = new ArrayList<>();


        Intent DOIndex = getIntent();
        String DOstr = DOIndex.getStringExtra("DO1");
        yValue.add(new PieEntry(34f, "DO\n" + DOstr + "\nmg/L"));

        Intent TempIndex = getIntent();
        String Tempstr = TempIndex.getStringExtra("Temp1");
        yValue.add(new PieEntry(34f, "Temp\n" + Tempstr + "\nC"));

        Intent pHIndex = getIntent();
        String pHpstr = pHIndex.getStringExtra("pH1");
        yValue.add(new PieEntry(34f, "pH\n" + pHpstr));

        Intent AlkIndex = getIntent();
        String Alkpstr = AlkIndex.getStringExtra("Alk1");
        yValue.add(new PieEntry(34f, "Alk\n" + Alkpstr + "\nppm"));

        Intent TANIndex = getIntent();
        String TANpstr = TANIndex.getStringExtra("Ammo1");
        yValue.add(new PieEntry(34f, "TAN\n" + TANpstr + "\nppm"));


        Intent NitIndex = getIntent();
        String Nitpstr = NitIndex.getStringExtra("Nit1");
        yValue.add(new PieEntry(34f, "Nitrite\n" + Nitpstr + "\nppm"));

        Intent HardIndex = getIntent();
        String Hardpstr = HardIndex.getStringExtra("Hard1");
        yValue.add(new PieEntry(34f, "Hard\n" + Hardpstr + "\nppm"));

        Intent TurbIndex = getIntent();
        String Turbstr = TurbIndex.getStringExtra("Turb1");
        yValue.add(new PieEntry(34f, "Turb\n" + Turbstr + "\ncm"));



//        Set Description
        Description description = new Description();
        description.setText("");
        description.setTextSize(50);
        pieChart.setDescription(description);

        pieChart.animateY(1500);

//        setting PieChart
        PieDataSet dataSet = new PieDataSet(yValue, "");
        dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);


//        Set Value in Other pie
        PieData data = new PieData(dataSet);
        data.setValueTextSize(0f);
        data.setValueTextColor(Color.YELLOW);
        pieChart.setData(data);


        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(79, 129, 189));
        colors.add(Color.rgb(0, 176, 80));
        colors.add(Color.rgb(0, 176, 240));
        colors.add(Color.rgb(247, 150, 70));
        colors.add(Color.rgb(128, 100, 162));
        colors.add(Color.rgb(255, 0, 0));
        colors.add(Color.rgb(155, 187, 89));
        colors.add(Color.rgb(64, 64, 64));
        dataSet.setColors(colors);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        final TextView FarmNameTextView = (TextView) findViewById(R.id.txtFarm);
        final TextView PondTextView = (TextView) findViewById(R.id.txtPond);

//        Set Header Text

        Intent FarmIndex = getIntent();
        String FNstr = FarmIndex.getStringExtra("FarmName");
        FarmNameTextView.setText(FNstr);

        Intent PondIndex = getIntent();
        String Pondstr = PondIndex.getStringExtra("Pond");
        PondTextView.setText(Pondstr);

        final TextView NH3TextView = (TextView) findViewById(R.id.txtNH3);
        final TextView NH4TextView = (TextView) findViewById(R.id.txtNH4);

        Intent Temp2Index = getIntent();
        String Temp2str = Temp2Index.getStringExtra("Temp1");

        Intent TAN2Index = getIntent();
        String TAN2pstr = TAN2Index.getStringExtra("Ammo1");

        Intent pH2Index = getIntent();
        String pH2pstr = pH2Index.getStringExtra("pH1");


        double TAN2Number = Double.parseDouble(TANpstr);
        double Temp2Number = Double.parseDouble(Tempstr);
        double pH2Number = Double.parseDouble(pHpstr);


//        pH 7.0 - 7.2 and Temp 16 - 34 C
        if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >=7.0 && pH2Number < 7.2)){
            double NH3 = 0.29 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >=7.0 && pH2Number < 7.2)) {
            double NH3 = 0.34 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >=7.0 && pH2Number < 7.2)) {
            double NH3 = 0.39 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >=7.0 && pH2Number < 7.2)) {
            double NH3 = 0.46 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >=7.0 && pH2Number < 7.2)) {
            double NH3 = 0.52 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >=7.0 && pH2Number < 7.2)) {
            double NH3 = 0.60 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >=7.0 && pH2Number < 7.2)) {
            double NH3 = 0.69 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >=7.0 && pH2Number < 7.2)) {
            double NH3 = 0.80 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >=7.0 && pH2Number < 7.2)) {
            double NH3 = 0.91 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));

//        pH 7.2 - 7.4 and Temp 16 - 34 C
        }else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >=7.2 && pH2Number < 7.4)) {
            double NH3 = 0.46 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >=7.2 && pH2Number < 7.4)) {
            double NH3 = 0.54 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >=7.2 && pH2Number < 7.4)) {
            double NH3 = 0.62 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >=7.2 && pH2Number < 7.4)) {
            double NH3 = 0.82 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >=7.2 && pH2Number < 7.4)) {
            double NH3 = 0.83 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >=7.2 && pH2Number < 7.4)) {
            double NH3 = 0.96 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >=7.2 && pH2Number < 7.4)) {
            double NH3 = 1.10 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >=7.2 && pH2Number < 7.4)) {
            double NH3 = 1.26 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >=7.2 && pH2Number < 7.4)) {
            double NH3 = 1.44 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));


//       pH 7.4 - 7.6 and Temp 16 - 34 C
        }else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >=7.4 && pH2Number < 7.6)) {
            double NH3 = 0.73 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >=7.4 && pH2Number < 7.6)) {
            double NH3 = 0.85 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >=7.4 && pH2Number < 7.6)) {
            double NH3 = 0.98 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >=7.4 && pH2Number < 7.6)) {
            double NH3 = 1.14 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >=7.4 && pH2Number < 7.6)) {
            double NH3 = 1.31 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >=7.4 && pH2Number < 7.6)) {
            double NH3 = 1.50 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >=7.4 && pH2Number < 7.6)) {
            double NH3 = 1.73 * TAN2Number;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = (TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >=7.4 && pH2Number < 7.6)) {
            double NH3 = (1.98 * TAN2Number/100)*1.2;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >=7.4 && pH2Number < 7.6)) {
            double NH3 = (2.26 * TAN2Number/100)*1.2;
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }






    }   //Main Method








}   //Main Class
