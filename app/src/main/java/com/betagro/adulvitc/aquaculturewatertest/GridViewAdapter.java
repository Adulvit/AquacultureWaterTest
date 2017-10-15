package com.betagro.adulvitc.aquaculturewatertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Adulvitc on 15/10/2560.
 */

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private int[] imageInt;

    public GridViewAdapter(Context context,
                           int[] imageInt) {
        this.context = context;
        this.imageInt = imageInt;
    }

    @Override
    public int getCount() {
        return imageInt.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.activity_adapter, parent, false);

        ImageView imageView = view1.findViewById(R.id.imvIcon);

        imageView.setImageResource(imageInt[position]);




        return view1;
    }
}
