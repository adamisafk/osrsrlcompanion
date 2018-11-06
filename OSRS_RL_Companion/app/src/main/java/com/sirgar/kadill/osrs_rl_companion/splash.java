package com.sirgar.kadill.osrs_rl_companion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class splash extends AppCompatActivity {
    @SuppressWarnings("deprecation")

    //Variables
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private Button mNextBtn;
    private Button mPrevBtn;
    private ImageView iconCheck;
    private ImageView iconCross;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mNextBtn = (Button) findViewById(R.id.nextBtn);
        mPrevBtn = (Button) findViewById(R.id.prevBtn);

        iconCheck = (ImageView) findViewById(R.id.iconCheck);
        iconCross = (ImageView) findViewById(R.id.iconCross);

        sliderAdapter = new SliderAdapter(this); //initialize SliderAdapter.java
        mSlideViewPager.setAdapter(sliderAdapter);

        addDotIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        //on click listeners for buttons - will update mCurrentPage to be used for layout changing
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
            mDots[i].setText(Html.fromHtml("&#8226;", Html.FROM_HTML_MODE_LEGACY)); //unicode for bullet symbol
            mDots[i].setTextSize(25); //sets size
            mDots[i].setTextColor(getResources().getColor(R.color.colorAccent)); //sets colour

            mDotLayout.addView(mDots[i]); //Sets result to bottom linear layer
        }

        if(mDots.length > 0) {
            //sets a coloured dot depending on current slide position
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }






    //'complicated stuff i cant put into a comment yet' below
    ViewPager.OnPageChangeListener viewListener;

    {
        viewListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            //method that changes layout of splash screen depending on the current page
            public void onPageSelected(int i) {
                addDotIndicator(i);
                mCurrentPage = i;

                switch (i) {
                    //first page of splash
                    case 0:
                        mNextBtn.setEnabled(true);
                        mPrevBtn.setEnabled(false);
                        mPrevBtn.setVisibility(View.INVISIBLE);

                        mNextBtn.setText("Next");
                        mPrevBtn.setText("");

                        //resets the function of the next button
                        mNextBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
                            }
                        });
                        break;
                    //second page of splash (oauthentication)
                    case 1:
                        mNextBtn.setEnabled(true);
                        mPrevBtn.setEnabled(true);
                        mPrevBtn.setVisibility(View.VISIBLE);
                        iconCross.setVisibility(View.VISIBLE);

                        mNextBtn.setText("Continue");
                        mPrevBtn.setText("Back");

                        //opens OAuth dialog
                        mNextBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                rlAuthDialog rlAuthDialog = new rlAuthDialog();
                                rlAuthDialog.show(getSupportFragmentManager(), "oauth dialog");
                            }
                        });
                        break;
                    //third page of splash (osrs name adding)
                    case 2:
                            mNextBtn.setEnabled(true);
                            mPrevBtn.setEnabled(true);
                            mPrevBtn.setVisibility(View.VISIBLE);
                            mPrevBtn.setText("Back");
                            iconCross.setVisibility(View.VISIBLE);

                            //if accounts has not been entered, make button say add accounts and set
                            //listener to next slide
                            mNextBtn.setText("Continue");
                            //makes the next button open the add osrs name dialog or next slide
                            mNextBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (accounts.initialOsrsName == null) {
                                        osrsNameDialog osrsNameDialog = new osrsNameDialog();
                                        osrsNameDialog.show(getSupportFragmentManager(), "osrs dialog");
                                    } else {
                                        mSlideViewPager.setCurrentItem(mCurrentPage + 1);
                                        iconCheck.setVisibility(View.VISIBLE);
                                        iconCross.setVisibility(View.INVISIBLE);
                                    }
                                }
                            });

                            break;
                    //last page of splash (links to main activity)
                    case 3: accounts accounts = new accounts();

                        if (accounts.initialOsrsName != null) {
                            mNextBtn.setEnabled(true);
                            mPrevBtn.setEnabled(true);
                            mNextBtn.setVisibility(View.VISIBLE);

                            mNextBtn.setText("Begin");
                            mPrevBtn.setText("Back");

                            //makes begin button link to main activity
                            mNextBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(splash.this, MainActivity.class));
                                    finish();
                                }
                            });
                        } else {
                            mSlideViewPager.setCurrentItem(mCurrentPage - 1);
                        }

                        break;
                    //in case something goes wrong
                    default:
                        mNextBtn.setEnabled(true);
                        mPrevBtn.setEnabled(true);
                        mPrevBtn.setVisibility(View.VISIBLE);

                        mNextBtn.setText("Next");
                        mPrevBtn.setText("Back");

                        //resets the function of the next button
                        mNextBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
                            }
                        });
                        break;

                }
            }

            //even though it's empty, don't delete it. its needed for some reason
            @Override
            public void onPageScrollStateChanged(int i) {

            }

        };
    }
}
