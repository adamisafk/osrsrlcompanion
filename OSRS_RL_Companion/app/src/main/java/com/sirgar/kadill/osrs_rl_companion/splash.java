package com.sirgar.kadill.osrs_rl_companion;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    //Variables
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private Button mNextBtn;
    private Button mPrevBtn;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mNextBtn = (Button) findViewById(R.id.nextBtn);
        mPrevBtn = (Button) findViewById(R.id.prevBtn);

        sliderAdapter = new SliderAdapter(this); //initialize SliderAdapter.java
        mSlideViewPager.setAdapter(sliderAdapter);

        addDotIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        //on click listeners for buttons
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        mPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

    }

    //method that generates the dots
    public void addDotIndicator(int position) {
        mDots = new TextView[4]; //Update this if max no. of slides change
        mDotLayout.removeAllViews();


        //This loop generates the corresponding no. of dots for each slide
        for(int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;")); //unicode for bullet symbol
            mDots[i].setTextSize(25); //sets size
            mDots[i].setTextColor(getResources().getColor(R.color.colorAccent)); //sets colour

            mDotLayout.addView(mDots[i]); //Sets result to bottom linear layer
        }

        if(mDots.length > 0) {
            //sets a coloured dot depending on current slide position
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotIndicator(i);
            mCurrentPage = i;

            //this if statement detects if it's the first current page, where it'll hide the back button
            if (i== 0){
                mNextBtn.setEnabled(true);
                mPrevBtn.setEnabled(false);
                mPrevBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mPrevBtn.setText("");
            } else if (i == mDots.length - 1) {
                //this else if detects if it's the last page and will rename next button
                mNextBtn.setEnabled(true);
                mPrevBtn.setEnabled(true);
                mNextBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Begin");
                mPrevBtn.setText("Back");
            } else {
                //if page isn't on first or last, show both buttons normally
                mNextBtn.setEnabled(true);
                mPrevBtn.setEnabled(true);
                mPrevBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Next");
                mPrevBtn.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
