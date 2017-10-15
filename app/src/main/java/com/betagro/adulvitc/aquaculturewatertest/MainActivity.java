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

        GridViewAdapter gridAdapter = new GridViewAdapter(this, imageInts);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("Image", imageInts);
                startActivity(intent);

            }
        });



    }   //Main Method


}   //Main Class
