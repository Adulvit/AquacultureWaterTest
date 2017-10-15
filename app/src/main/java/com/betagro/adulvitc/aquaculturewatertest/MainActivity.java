package com.betagro.adulvitc.aquaculturewatertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {


    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);

        final int[] imageInts = new int[]{R.drawable.do1, R.drawable.temp1,
                R.drawable.ph1, R.drawable.alk1, R.drawable.amo1,
                R.drawable.nitrite1, R.drawable.hard1, R.drawable.disk1};


        final int[] image2Ints = new int[]{R.drawable.do2, R.drawable.temp2,
                R.drawable.ph2, R.drawable.alk2, R.drawable.amo2,
                R.drawable.nitrite2, R.drawable.hard2, R.drawable.disk2};


        GridViewAdapter gridAdapter = new GridViewAdapter(this, imageInts);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("Image", imageInts);
                intent.putExtra("image2", position);
                intent.putExtra("title", position);
                intent.putExtra("Unit", position);



                startActivity(intent);

            }
        });



    }   //Main Method


}   //Main Class
