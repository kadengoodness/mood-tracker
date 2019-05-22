package com.kadengood.moodtracker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.kadengood.moodtracker.R;
import com.kadengood.moodtracker.model.Mood;
import com.kadengood.moodtracker.utils.Storage;
import com.kadengood.moodtracker.utils.Utils;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HistoryActivity extends AppCompatActivity {
    private FrameLayout mDayOneLeft;
    private FrameLayout mDayTwoLeft;
    private FrameLayout mDayThreeLeft;
    private FrameLayout mDayFourLeft;
    private FrameLayout mDayFiveLeft;
    private FrameLayout mDaySixLeft;
    private FrameLayout mDaySevenLeft;

    private FrameLayout mDayOneRight;
    private FrameLayout mDayTwoRight;
    private FrameLayout mDayThreeRight;
    private FrameLayout mDayFourRight;
    private FrameLayout mDayFiveRight;
    private FrameLayout mDaySixRight;
    private FrameLayout mDaySevenRight;

    private ImageView mDayOneButton;
    private ImageView mDayTwoButton;
    private ImageView mDayThreeButton;
    private ImageView mDayFourButton;
    private ImageView mDayFiveButton;
    private ImageView mDaySixButton;
    private ImageView mDaySevenButton;

    private Mood mYesterday;
    private Mood mTwoDaysAgo;
    private Mood mThreeDaysAgo;
    private Mood mFourDaysAgo;
    private Mood mFiveDaysAgo;
    private Mood mSixDaysAgo;
    private Mood mSevenDaysAgo;

    private Calendar mCalendar;
    private SimpleDateFormat sdf;

    private FrameLayout mEmptyHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // LEFT FRAME LAYOUTS
        mDayOneLeft = findViewById(R.id.dayOneLeft);
        mDayTwoLeft = findViewById(R.id.dayTwoLeft);
        mDayThreeLeft = findViewById(R.id.dayThreeLeft);
        mDayFourLeft = findViewById(R.id.dayFourLeft);
        mDayFiveLeft = findViewById(R.id.dayFiveLeft);
        mDaySixLeft = findViewById(R.id.daySixLeft);
        mDaySevenLeft = findViewById(R.id.daySevenLeft);

        // RIGHT FRAME LAYOUTS
        mDayOneRight = findViewById(R.id.dayOneRight);
        mDayTwoRight = findViewById(R.id.dayTwoRight);
        mDayThreeRight = findViewById(R.id.dayThreeRight);
        mDayFourRight = findViewById(R.id.dayFourRight);
        mDayFiveRight = findViewById(R.id.dayFiveRight);
        mDaySixRight = findViewById(R.id.daySixRight);
        mDaySevenRight = findViewById(R.id.daySevenRight);

        // COMMENT BUTTONS
        mDayOneButton = findViewById(R.id.dayOneButton);
        mDayTwoButton = findViewById(R.id.dayTwoButton);
        mDayThreeButton = findViewById(R.id.dayThreeButton);
        mDayFourButton = findViewById(R.id.dayFourButton);
        mDayFiveButton = findViewById(R.id.dayFiveButton);
        mDaySixButton = findViewById(R.id.daySixButton);
        mDaySevenButton = findViewById(R.id.daySevenButton);

        // EMPTY HISTORY
        mEmptyHistory = findViewById(R.id.emptyHistory);

        // GET DAYS FROM PAST WEEK
        sdf = new SimpleDateFormat("ddMMyyyy");
        mCalendar = Calendar.getInstance();

        // YESTERDAY
        mCalendar.add(Calendar.DATE, -1);
        String mPast1 = sdf.format(mCalendar.getTime());
        mYesterday = Storage.load(this, mPast1);

        // 2 DAYS AGO
        mCalendar.add(Calendar.DATE, -1);
        String mPast2 = sdf.format(mCalendar.getTime());
        mTwoDaysAgo = Storage.load(this,mPast2);

        // 3 DAYS AGO
        mCalendar.add(Calendar.DATE, -1);
        String mPast3 = sdf.format(mCalendar.getTime());
        mThreeDaysAgo = Storage.load(this,mPast3);

        // 4 DAYS AGO
        mCalendar.add(Calendar.DATE, -1);
        String mPast4 = sdf.format(mCalendar.getTime());
        mFourDaysAgo = Storage.load(this,mPast4);

        // 5 DAYS AGO
        mCalendar.add(Calendar.DATE, -1);
        String mPast5 = sdf.format(mCalendar.getTime());
        mFiveDaysAgo = Storage.load(this,mPast5);

        // 6 DAYS AGO
        mCalendar.add(Calendar.DATE, -1);
        String mPast6 = sdf.format(mCalendar.getTime());
        mSixDaysAgo = Storage.load(this,mPast6);

        // 7 DAYS AGO
        mCalendar.add(Calendar.DATE, -1);
        String mPast7 = sdf.format(mCalendar.getTime());
        mSevenDaysAgo = Storage.load(this,mPast7);

        // CALL FUNCTION FOR EACH DAY
        manageView(mYesterday, mDayOneLeft, mDayOneRight, mDayOneButton);
        manageView(mTwoDaysAgo, mDayTwoLeft, mDayTwoRight, mDayTwoButton);
        manageView(mThreeDaysAgo, mDayThreeLeft, mDayThreeRight, mDayThreeButton);
        manageView(mFourDaysAgo, mDayFourLeft, mDayFourRight, mDayFourButton);
        manageView(mFiveDaysAgo, mDayFiveLeft, mDayFiveRight, mDayFiveButton);
        manageView(mSixDaysAgo, mDaySixLeft, mDaySixRight, mDaySixButton);
        manageView(mSevenDaysAgo, mDaySevenLeft, mDaySevenRight, mDaySevenButton);

        }


    ///////////////////
    //  MANAGE BARS  //
    ///////////////////
    private void manageView(final Mood mood, FrameLayout leftFrame, FrameLayout rightFrame, ImageView commentButton) {

        // MISSING DAY
        if (mood == null) {
            leftFrame.setVisibility(View.INVISIBLE);  // Turn bar invisible
        }

        // BARS' COLOR & LENGTH
        else {
            leftFrame.setBackgroundResource(Utils.color[mood.getPosition()]); // Colors
            mEmptyHistory.setVisibility(View.GONE); // FrameLayout for empty history


            switch (mood.getPosition()) {
                case 0:
                    leftFrame.setLayoutParams(new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 100));
                    rightFrame.setLayoutParams(new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 0));
                    break;
                case 1:
                    leftFrame.setLayoutParams(new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 80));
                    rightFrame.setLayoutParams(new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 20));
                    break;
                case 2:
                    leftFrame.setLayoutParams(new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 60));
                    rightFrame.setLayoutParams(new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 40));
                    break;
                case 3:
                    leftFrame.setLayoutParams(new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 40));
                    rightFrame.setLayoutParams(new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 60));
                    break;
                case 4:
                    leftFrame.setLayoutParams(new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 20));
                    rightFrame.setLayoutParams(new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 80));
                    break;
            }

            // MISSING COMMENT
            if (mood.getComment().isEmpty()) {
                commentButton.setVisibility(View.INVISIBLE); // Turn button invisible
            }
            // DISPLAY COMMENT
            else{
                commentButton.setVisibility(View.VISIBLE); // Turn button visible
                commentButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                         Toast toast=Toast.makeText(getApplicationContext(),mood.getComment(),Toast.LENGTH_SHORT);
                         toast.show();
                    }
                });
            }

        }
    }

}


