package com.betagro.adulvitc.aquaculturewatertest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

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

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(45f);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setHoleRadius(40);
        //pieChart.setCenterText("Parameter");
        pieChart.setCenterTextSize(20);
        pieChart.setTouchEnabled(false);

        ArrayList<PieEntry> yValue = new ArrayList<>();

        yValue.add(new PieEntry(34f, "DO"));
        yValue.add(new PieEntry(34f, "Temp"));
        yValue.add(new PieEntry(34f, "pH"));
        yValue.add(new PieEntry(34f, "Alk"));
        yValue.add(new PieEntry(34f, "TAN"));
        yValue.add(new PieEntry(34f, "Nitrite"));
        yValue.add(new PieEntry(34f, "Hardness"));
        yValue.add(new PieEntry(34f, "Turb"));






        Description description = new Description();
        description.setText("");
        description.setTextSize(50);
        pieChart.setDescription(description);

        pieChart.animateY(100, Easing.EasingOption.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(yValue, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        //dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(0f);
        //data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);


        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(79,129,189));
        colors.add(Color.rgb(0,176,80));
        colors.add(Color.rgb(0,176,240));
        colors.add(Color.rgb(247,150,70));
        colors.add(Color.rgb(128,100,162));
        colors.add(Color.rgb(255,0,0));
        colors.add(Color.rgb(155,187,89));
        colors.add(Color.rgb(64,64,64));


        dataSet.setColors(colors);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }   //Main Method


}   //Main Class
