package com.betagro.adulvitc.aquaculturewatertest;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Created by Adulvitc on 16/10/2560.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] image = {R.drawable.do_meter,
            R.drawable.do_meter1, R.drawable.ph_measure,
            R.drawable.ph_measure2, R.drawable.water_temp,
            R.drawable.alkaline,R.drawable.amo_cartoon,R.drawable.amo_cartoon2};

    public ViewPagerAdapter(Context context1) {
        this.context = context1;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {



        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.viewpager, null);
        ImageView imageView = view.findViewById(R.id.imvSlide);
        imageView.setImageResource(image[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;




    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);


    }
}
