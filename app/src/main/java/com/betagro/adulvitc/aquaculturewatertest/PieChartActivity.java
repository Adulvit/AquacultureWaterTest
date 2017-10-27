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
        pieChart.setTouchEnabled(true);


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
        if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 7.0 && pH2Number < 7.2)) {
            double NH3 = (0.29 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 7.0 && pH2Number < 7.2)) {
            double NH3 = (0.34 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 7.0 && pH2Number < 7.2)) {
            double NH3 = (0.39 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 7.0 && pH2Number < 7.2)) {
            double NH3 = (0.46 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 7.0 && pH2Number < 7.2)) {
            double NH3 = (0.52 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 7.0 && pH2Number < 7.2)) {
            double NH3 = (0.60 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 7.0 && pH2Number < 7.2)) {
            double NH3 = (0.69 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 7.0 && pH2Number < 7.2)) {
            double NH3 = (0.80 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 7.0 && pH2Number < 7.2)) {
            double NH3 = (0.91 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));

//        pH 7.2 - 7.4 and Temp 16 - 34 C
        } else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 7.2 && pH2Number < 7.4)) {
            double NH3 = (0.46 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 7.2 && pH2Number < 7.4)) {
            double NH3 = (0.54 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 7.2 && pH2Number < 7.4)) {
            double NH3 = (0.62 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 7.2 && pH2Number < 7.4)) {
            double NH3 = (0.82 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 7.2 && pH2Number < 7.4)) {
            double NH3 = (0.83 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 7.2 && pH2Number < 7.4)) {
            double NH3 = (0.96 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 7.2 && pH2Number < 7.4)) {
            double NH3 = (1.10 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 7.2 && pH2Number < 7.4)) {
            double NH3 = (1.26 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 7.2 && pH2Number < 7.4)) {
            double NH3 = (1.44 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));


//       pH 7.4 - 7.6 and Temp 16 - 34 C
        } else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 7.4 && pH2Number < 7.6)) {
            double NH3 = (0.73 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 7.4 && pH2Number < 7.6)) {
            double NH3 = (0.85 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 7.4 && pH2Number < 7.6)) {
            double NH3 = (0.98 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 7.4 && pH2Number < 7.6)) {
            double NH3 = (1.14 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 7.4 && pH2Number < 7.6)) {
            double NH3 = (1.31 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 7.4 && pH2Number < 7.6)) {
            double NH3 = (1.50 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 7.4 && pH2Number < 7.6)) {
            double NH3 = (1.73 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 7.4 && pH2Number < 7.6)) {
            double NH3 = (1.98 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 7.4 && pH2Number < 7.6)) {
            double NH3 = (2.26 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));

//       pH 7.6 - 7.8 and Temp 16 - 34
        } else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 7.6 && pH2Number < 7.8)) {
            double NH3 = (1.16 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 7.6 && pH2Number < 7.8)) {
            double NH3 = (1.34 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 7.6 && pH2Number < 7.8)) {
            double NH3 = (1.55 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 7.6 && pH2Number < 7.8)) {
            double NH3 = (1.79 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 7.6 && pH2Number < 7.8)) {
            double NH3 = (2.06 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 7.6 && pH2Number < 7.8)) {
            double NH3 = (2.36 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 7.6 && pH2Number < 7.8)) {
            double NH3 = (2.71 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 7.6 && pH2Number < 7.8)) {
            double NH3 = (3.10 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 7.6 && pH2Number < 7.8)) {
            double NH3 = (3.53 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));


//       pH 7.8 - 8.0 and Temp 16 - 34
        } else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 7.8 && pH2Number < 8.0)) {
            double NH3 = (1.82 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 7.8 && pH2Number < 8.0)) {
            double NH3 = (2.11 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 7.8 && pH2Number < 8.0)) {
            double NH3 = (2.44 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 7.8 && pH2Number < 8.0)) {
            double NH3 = (2.81 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 7.8 && pH2Number < 8.0)) {
            double NH3 = (3.22 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 7.8 && pH2Number < 8.0)) {
            double NH3 = (3.70 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 7.8 && pH2Number < 8.0)) {
            double NH3 = (4.23 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 7.8 && pH2Number < 8.0)) {
            double NH3 = (4.82 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 7.8 && pH2Number < 8.0)) {
            double NH3 = (5.48 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));


//       pH 8.0 - 8.2 and Temp 16 - 34
        } else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 8.0 && pH2Number < 8.2)) {
            double NH3 = (2.86 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 8.0 && pH2Number < 8.2)) {
            double NH3 = (3.30 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 8.0 && pH2Number < 8.2)) {
            double NH3 = (3.81 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 8.0 && pH2Number < 8.2)) {
            double NH3 = (4.38 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 8.0 && pH2Number < 8.2)) {
            double NH3 = (5.02 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 8.0 && pH2Number < 8.2)) {
            double NH3 = (5.74 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 8.0 && pH2Number < 8.2)) {
            double NH3 = (6.54 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 8.0 && pH2Number < 8.2)) {
            double NH3 = (7.43 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 8.0 && pH2Number < 8.2)) {
            double NH3 = (8.42 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));


//       pH 8.2 - 8.4 and Temp 16 - 34
        } else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 8.2 && pH2Number < 8.4)) {
            double NH3 = (4.45 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 8.2 && pH2Number < 8.4)) {
            double NH3 = (5.14 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 8.2 && pH2Number < 8.4)) {
            double NH3 = (5.90 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 8.2 && pH2Number < 8.4)) {
            double NH3 = (6.76 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 8.2 && pH2Number < 8.4)) {
            double NH3 = (7.72 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 8.2 && pH2Number < 8.4)) {
            double NH3 = (8.80 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 8.2 && pH2Number < 8.4)) {
            double NH3 = (9.98 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 8.2 && pH2Number < 8.4)) {
            double NH3 = (11.29 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 8.2 && pH2Number < 8.4)) {
            double NH3 = (12.72 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));


//       pH 8.4 - 8.6 and Temp 16 - 34
        } else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 8.4 && pH2Number < 8.6)) {
            double NH3 = (6.88 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 8.4 && pH2Number < 8.6)) {
            double NH3 = (7.90 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 8.4 && pH2Number < 8.6)) {
            double NH3 = (9.04 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 8.4 && pH2Number < 8.6)) {
            double NH3 = (10.31 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 8.4 && pH2Number < 8.6)) {
            double NH3 = (11.71 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 8.4 && pH2Number < 8.6)) {
            double NH3 = (13.26 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 8.4 && pH2Number < 8.6)) {
            double NH3 = (14.95 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 8.4 && pH2Number < 8.6)) {
            double NH3 = (16.78 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 8.4 && pH2Number < 8.6)) {
            double NH3 = (18.77 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));


//       pH 8.6 - 8.8 and Temp 16 - 34
        } else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 8.6 && pH2Number < 8.8)) {
            double NH3 = (10.48 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 8.6 && pH2Number < 8.8)) {
            double NH3 = (11.97 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 8.6 && pH2Number < 8.8)) {
            double NH3 = (13.61 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 8.6 && pH2Number < 8.8)) {
            double NH3 = (15.41 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 8.6 && pH2Number < 8.8)) {
            double NH3 = (17.37 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 8.6 && pH2Number < 8.8)) {
            double NH3 = (19.50 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 8.6 && pH2Number < 8.8)) {
            double NH3 = (21.78 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 8.6 && pH2Number < 8.8)) {
            double NH3 = (24.22 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 8.6 && pH2Number < 8.8)) {
            double NH3 = (26.80 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));


//       pH 8.8 - 9.0 and Temp 16 - 34
        } else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 8.8 && pH2Number < 9.0)) {
            double NH3 = (15.66 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 8.8 && pH2Number < 9.0)) {
            double NH3 = (17.73 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 8.8 && pH2Number < 9.0)) {
            double NH3 = (19.98 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 8.8 && pH2Number < 9.0)) {
            double NH3 = (22.41 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 8.8 && pH2Number < 9.0)) {
            double NH3 = (25.00 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 8.8 && pH2Number < 9.0)) {
            double NH3 = (27.74 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 8.8 && pH2Number < 9.0)) {
            double NH3 = (30.62 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 8.8 && pH2Number < 9.0)) {
            double NH3 = (33.62 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 8.8 && pH2Number < 9.0)) {
            double NH3 = (36.72 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));


//       pH 9.0 - 9.2 and Temp 16 - 34
        } else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 9.0 && pH2Number < 9.2)) {
            double NH3 = (22.73 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 9.0 && pH2Number < 9.2)) {
            double NH3 = (25.46 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 9.0 && pH2Number < 9.2)) {
            double NH3 = (28.36 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 9.0 && pH2Number < 9.2)) {
            double NH3 = (31.40 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 9.0 && pH2Number < 9.2)) {
            double NH3 = (34.56 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 9.0 && pH2Number < 9.2)) {
            double NH3 = (37.83 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 9.0 && pH2Number < 9.2)) {
            double NH3 = (41.16 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 9.0 && pH2Number < 9.2)) {
            double NH3 = (44.53 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 9.0 && pH2Number < 9.2)) {
            double NH3 = (47.91 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));

//       pH 9.2 - 9.4 and Temp 16 - 34
        } else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 9.2 && pH2Number < 9.4)) {
            double NH3 = (31.80 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 9.2 && pH2Number < 9.4)) {
            double NH3 = (35.12 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 9.2 && pH2Number < 9.4)) {
            double NH3 = (38.55 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 9.2 && pH2Number < 9.4)) {
            double NH3 = (42.04 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 9.2 && pH2Number < 9.4)) {
            double NH3 = (45.57 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 9.2 && pH2Number < 9.4)) {
            double NH3 = (49.09 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 9.2 && pH2Number < 9.4)) {
            double NH3 = (52.58 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 9.2 && pH2Number < 9.4)) {
            double NH3 = (55.99 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 9.2 && pH2Number < 9.4)) {
            double NH3 = (59.31 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));

//       pH 9.4 - 9.6 and Temp 16 - 34
        } else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 9.4 && pH2Number < 9.6)) {
            double NH3 = (42.49 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 9.4 && pH2Number < 9.6)) {
            double NH3 = (46.18 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 9.4 && pH2Number < 9.6)) {
            double NH3 = (49.85 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 9.4 && pH2Number < 9.6)) {
            double NH3 = (53.48 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 9.4 && pH2Number < 9.6)) {
            double NH3 = (57.02 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 9.4 && pH2Number < 9.6)) {
            double NH3 = (60.45 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 9.4 && pH2Number < 9.6)) {
            double NH3 = (63.73 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 9.4 && pH2Number < 9.6)) {
            double NH3 = (66.85 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 9.4 && pH2Number < 9.6)) {
            double NH3 = (69.79 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));


//       pH 9.6 - 9.8 and Temp 16 - 34
        } else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 9.6 && pH2Number < 9.8)) {
            double NH3 = (53.94 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 9.6 && pH2Number < 9.8)) {
            double NH3 = (57.62 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 9.6 && pH2Number < 9.8)) {
            double NH3 = (61.17 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 9.6 && pH2Number < 9.8)) {
            double NH3 = (64.56 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 9.6 && pH2Number < 9.8)) {
            double NH3 = (67.77 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 9.6 && pH2Number < 9.8)) {
            double NH3 = (70.78 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 9.6 && pH2Number < 9.8)) {
            double NH3 = (73.58 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 9.6 && pH2Number < 9.8)) {
            double NH3 = (76.17 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 9.6 && pH2Number < 9.8)) {
            double NH3 = (78.55 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));

//       pH 9.8 - 10.0 and Temp 16 - 34
        } else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 9.8 && pH2Number < 10.0)) {
            double NH3 = (64.99 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 9.8 && pH2Number < 10.0)) {
            double NH3 = (68.31 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 9.8 && pH2Number < 10.0)) {
            double NH3 = (71.40 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 9.8 && pH2Number < 10.0)) {
            double NH3 = (74.28 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 9.8 && pH2Number < 10.0)) {
            double NH3 = (76.92 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 9.8 && pH2Number < 10.0)) {
            double NH3 = (79.33 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 9.8 && pH2Number < 10.0)) {
            double NH3 = (81.53 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 9.8 && pH2Number < 10.0)) {
            double NH3 = (83.51 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 9.8 && pH2Number < 10.0)) {
            double NH3 = (85.30 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));


//       pH 10.0 - 10.2 and Temp 16 - 34
        } else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 10.0 && pH2Number < 10.2)) {
            double NH3 = (74.63 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 10.0 && pH2Number < 10.2)) {
            double NH3 = (77.35 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 10.0 && pH2Number < 10.2)) {
            double NH3 = (79.83 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 10.0 && pH2Number < 10.2)) {
            double NH3 = (82.07 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 10.0 && pH2Number < 10.2)) {
            double NH3 = (84.08 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 10.0 && pH2Number < 10.2)) {
            double NH3 = (85.88 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 10.0 && pH2Number < 10.2)) {
            double NH3 = (87.49 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 10.0 && pH2Number < 10.2)) {
            double NH3 = (88.92 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 10.0 && pH2Number < 10.2)) {
            double NH3 = (90.19 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));


//       pH 10.2 - 10.4 and Temp 16 - 34
        } else if ((Temp2Number >= 16 && Temp2Number < 18) && (pH2Number >= 10.2 && pH2Number < 10.4)) {
            double NH3 = (82.34 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 18 && Temp2Number < 20) && (pH2Number >= 10.2 && pH2Number < 10.4)) {
            double NH3 = (84.41 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 20 && Temp2Number < 22) && (pH2Number >= 10.2 && pH2Number < 10.4)) {
            double NH3 = (86.25 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 22 && Temp2Number < 24) && (pH2Number >= 10.2 && pH2Number < 10.4)) {
            double NH3 = (87.88 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 24 && Temp2Number < 26) && (pH2Number >= 10.2 && pH2Number < 10.4)) {
            double NH3 = (89.33 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 26 && Temp2Number < 28) && (pH2Number >= 10.2 && pH2Number < 10.4)) {
            double NH3 = (90.60 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 28 && Temp2Number < 30) && (pH2Number >= 10.2 && pH2Number < 10.4)) {
            double NH3 = (91.73 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 30 && Temp2Number < 32) && (pH2Number >= 10.2 && pH2Number < 10.4)) {
            double NH3 = (92.71 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        } else if ((Temp2Number >= 32 && Temp2Number < 34) && (pH2Number >= 10.2 && pH2Number < 10.4)) {
            double NH3 = (93.58 * TAN2Number / 100);
            NH3TextView.setText(String.valueOf(NH3));
            double NH4 = Math.abs(TAN2Number - NH3);
            NH4TextView.setText(String.valueOf(NH4));
        }else {
            NH3TextView.setText(String.valueOf(false));
            NH4TextView.setText(String.valueOf(false));
        }


    }   //Main Method


}   //Main Class
