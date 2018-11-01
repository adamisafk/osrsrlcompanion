package com.sirgar.kadill.osrs_rl_companion;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    //Variables
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        sliderAdapter = new SliderAdapter(this); //initialize SliderAdapter.java
        mSlideViewPager.setAdapter(sliderAdapter);

        addDotIndicator();

    }

    public void addDotIndicator() {
        mDots = new TextView[3]; //Update this if max no. of slides change


        //This loop generates the corresponding no. of dots for each slide
        for(int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;")); //unicode for bullet symbol
            mDots[i].setTextSize(35); //sets size
            mDots[i].setTextColor(getResources().getColor(R.color.colorAccent)); //sets colour

            mDotLayout.addView(mDots[i]); //Sets result to bottom linear layer
        }
    }
}
