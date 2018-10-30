package com.sirgar.kadill.osrs_rl_companion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

//https://www.youtube.com/watch?v=byLKoPgB7yA&t=10m15s

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    //Arrays
    //add splash icons here
    public int[] slide_icons = {
            R.drawable.splashtitle
    };
    //add headings here
    public String[] slide_headings = {
            "WELCOME",
            "CONNECT RUNELITE",
            "ADD AN ACCOUNT",
            "LET'S GO"
    };
    //add descriptions here
    public String[] slide_descs = {
            "This app is specifically for RuneLite. You will need both an OSRS account as well as a RuneLite account. " +
                    "This tool is not intended for OSRS Mobile, it should be used if you've stepped away from your desktop.",
            "Connect your RuneLite account by using OAuth for secure authentication. Your data will not be stored.",
            "Finally, add your account name (this is what you use as your login for OSRS). " +
                    "This allows RuneLite to fetch data from the correct account. DO NOT TYPE YOUR PASSWORD.",
            "Everything is setup! You can now use an array of RuneLite tools in real time."
    };
    //add splash backgrounds here
    public int[] slide_backgrounds = {
            R.drawable.splashbg1,
            R.drawable.splashbg2,
            R.drawable.splashbg3
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        return view;
    }
}
