package com.kadengood.moodtracker.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.kadengood.moodtracker.R;
import com.kadengood.moodtracker.utils.Utils;

/**
 * Created by kadengood on 3/4/19.
 */

public class MyViewPagerAdapter extends PagerAdapter {
    private Context context;
    // PICTURES FOR SMILEYS
    private int[] image = {
            R.drawable.smiley_super_happy,
            R.drawable.smiley_happy,
            R.drawable.smiley_normal,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_sad,
    };

    public MyViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.smiley_sample, collection, false);
        ImageView pagerImage = layout.findViewById(R.id.smiley);
        RelativeLayout pagerBackground = layout.findViewById(R.id.smileyLayout);
        pagerImage.setImageResource(image[position]);
        pagerBackground.setBackgroundResource(Utils.color[position]);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
        container.removeView((View) view);
    }

    @Override
    public int getCount() {
        return this.image.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

}
