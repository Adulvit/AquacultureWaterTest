package com.betagro.adulvitc.aquaculturewatertest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        pieChart = (PieChart) findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setHoleColor(Color.WHITE);

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
        description.setText("Water Quality Result");
        description.setTextSize(50);
        pieChart.setDescription(description);

        pieChart.animateY(100, Easing.EasingOption.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(yValue, "Water Quality");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);
    }   //Main Method


}   //Main Class
