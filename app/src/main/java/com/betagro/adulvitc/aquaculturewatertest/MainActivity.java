package com.betagro.adulvitc.aquaculturewatertest;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    private GridView gridView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        final int[] imageInts = new int[]{R.drawable.do1, R.drawable.temp1,
                R.drawable.ph1, R.drawable.alk1, R.drawable.amo1,
                R.drawable.nitrite1, R.drawable.hard1, R.drawable.disk1};


        final int[] arrImg = {
                R.drawable.do2,
                R.drawable.temp2,
                R.drawable.ph2,
                R.drawable.alk2,
                R.drawable.amo2,
                R.drawable.nitrite2,
                R.drawable.hard2,
                R.drawable.disk2
        };


        GridViewAdapter gridAdapter = new GridViewAdapter(this, imageInts);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, Detail2Activity.class);
                intent.putExtra("Image", imageInts[position]);
                intent.putExtra("title", position);
                intent.putExtra("Unit", position);
                intent.putExtra("imgID", arrImg[position]);

                startActivity(intent);

            }
        });


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000,4000);


    }   //Main Method

    public class MyTimerTask extends TimerTask {


        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new TimerTask() {
                @Override
                public void run() {

                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else if (viewPager.getCurrentItem() == 2) {
                        viewPager.setCurrentItem(3);
                    } else if (viewPager.getCurrentItem() == 3) {
                        viewPager.setCurrentItem(4);
                    } else if (viewPager.getCurrentItem() == 4) {
                        viewPager.setCurrentItem(5);
                    } else {
                        viewPager.setCurrentItem(0);

                    }
                }
            });

        }
    }


}   //Main Class
