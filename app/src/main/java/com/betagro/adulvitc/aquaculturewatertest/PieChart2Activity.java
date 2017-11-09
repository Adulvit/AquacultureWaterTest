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

public class PieChart2Activity extends AppCompatActivity {


    private TextView FarmNameTextView, PondTextView, NH3TextView, NH4TextView;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart2);

        final TextView FarmNameTextView = (TextView) findViewById(R.id.txtFarm);
        final TextView PondTextView = (TextView) findViewById(R.id.txtPond);
        final TextView NH3TextView = (TextView) findViewById(R.id.txtNH3);
        final TextView NH4TextView = (TextView) findViewById(R.id.txtNH4);


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
        //pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(45f);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setHoleRadius(40);

        pieChart.setCenterText("");
        pieChart.setCenterTextSize(13);
        pieChart.setTouchEnabled(true);


//        รับค่า String จากหน้า Detail2Activity มาเก็บไว้ในตัวแปร
        Intent DOIndex = getIntent();
        String DOstr = DOIndex.getStringExtra("DO1");

        Intent TempIndex = getIntent();
        String Tempstr = TempIndex.getStringExtra("Temp1");

        Intent pHIndex = getIntent();
        String pHpstr = pHIndex.getStringExtra("pH1");

        Intent AlkIndex = getIntent();
        String Alkpstr = AlkIndex.getStringExtra("Alk1");

        Intent TANIndex = getIntent();
        String TANpstr = TANIndex.getStringExtra("Ammo1");

        Intent NitIndex = getIntent();
        String Nitpstr = NitIndex.getStringExtra("Nit1");

        Intent HardIndex = getIntent();
        String Hardpstr = HardIndex.getStringExtra("Hard1");

        Intent TurbIndex = getIntent();
        String Turbstr = TurbIndex.getStringExtra("Turb1");

        //รับค่า String FarmName และ Pond No. มาแสดงใน TextView
        Intent FarmIndex = getIntent();
        String FNstr = FarmIndex.getStringExtra("FarmName");
        FarmNameTextView.setText(FNstr);

        Intent PondIndex = getIntent();
        String Pondstr = PondIndex.getStringExtra("Pond");
        PondTextView.setText(Pondstr);



        ArrayList<PieEntry> yValue = new ArrayList<>();

        yValue.add(new PieEntry(34f, "DO\n" + DOstr + "\nmg/L"));
        yValue.add(new PieEntry(34f, "Temp\n" + Tempstr  + "\nC"));
        yValue.add(new PieEntry(34f, "pH\n" + pHpstr));
        yValue.add(new PieEntry(34f, "Alk\n" + Alkpstr + "\nppm"));
        yValue.add(new PieEntry(34f, "TAN\n" + TANpstr  + "\nppm"));
        yValue.add(new PieEntry(34f, "Nitrite\n" + Nitpstr + "\nppm"));
        yValue.add(new PieEntry(34f, "Hard\n" + Hardpstr + "\nppm"));
        yValue.add(new PieEntry(34f, "Turb\n" + Turbstr + "\ncm"));


//        Set Description
        Description description = new Description();
        description.setText("developed by Adulvit, Betagro Group");
        description.setTextSize(12);
        description.setTextColor(Color.BLACK);

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



        double TAN2Number = Double.parseDouble(TANpstr);
        double Temp2Number = Double.parseDouble(Tempstr);
        double pH2Number = Double.parseDouble(pHpstr);



//        กรณีที่ 1 เมื่อค่า ( TAN != ค่าว่าง และ Temp == ค่าว่าง และ pH != ค่าว่าง) และ (ค่า pH  7.0 - 10.4 )
        if ((TANpstr != null && Tempstr == null && pHpstr != null ) && (DOstr != null || Alkpstr != null ||Nitpstr != null ||
                Hardpstr != null || Turbstr != null ) && (Temp2Number >= 16 && Temp2Number < 34) &&
                (pH2Number >= 7.0 && pH2Number < 10.4)) {

            if (pH2Number >= 7.0 && pH2Number < 7.2) {
                double NH3 = (0.52 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));

            } else if (pH2Number >= 7.2 && pH2Number < 7.4) {
                double NH3 = (0.83 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));
            } else if (pH2Number >= 7.4 && pH2Number < 7.6) {
                double NH3 = (1.31 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));

            } else if (pH2Number >= 7.6 && pH2Number < 7.8) {
                double NH3 = (2.06 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));

            } else if (pH2Number >= 7.8 && pH2Number < 8.0) {
                double NH3 = (3.22 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));

            } else if (pH2Number >= 8.0 && pH2Number < 8.2) {
                double NH3 = (5.02 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));

            } else if (pH2Number >= 8.2 && pH2Number < 8.4) {
                double NH3 = (7.72 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));

            } else if (pH2Number >= 8.4 && pH2Number < 8.6) {
                double NH3 = (11.71 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));

            } else if (pH2Number >= 8.6 && pH2Number < 8.8) {
                double NH3 = (17.37 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));

            } else if (pH2Number >= 8.8 && pH2Number < 9.0) {
                double NH3 = (25.00 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));

            } else if (pH2Number >= 9.0 && pH2Number < 9.2) {
                double NH3 = (34.56 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));

            } else if (pH2Number >= 9.2 && pH2Number < 9.4) {
                double NH3 = (45.57 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));

            } else if (pH2Number >= 9.4 && pH2Number < 9.6) {
                double NH3 = (57.02 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));

            } else if (pH2Number >= 9.6 && pH2Number < 9.8) {
                double NH3 = (67.77 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));

            } else if (pH2Number >= 9.8 && pH2Number < 10.0) {
                double NH3 = (76.92 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));

            } else if (pH2Number >= 10.0 && pH2Number < 10.2) {
                double NH3 = (84.08 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));

            } else if (pH2Number >= 10.2 && pH2Number < 10.4) {
                double NH3 = (89.33 * TAN2Number / 100);
                NH3TextView.setText(String.valueOf(NH3));
                double NH4 = Math.abs(TAN2Number - NH3);
                NH4TextView.setText(String.valueOf(NH4));
            }else
                NH3TextView.setText(String.valueOf(false));
                NH4TextView.setText(String.valueOf(false));



        }











    }   //Main Method


}   //Main Class

