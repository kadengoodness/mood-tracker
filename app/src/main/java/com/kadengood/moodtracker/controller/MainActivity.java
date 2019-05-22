package com.kadengood.moodtracker.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.kadengood.moodtracker.R;
import com.kadengood.moodtracker.component.MyViewPagerAdapter;
import com.kadengood.moodtracker.component.VerticalViewPager;
import com.kadengood.moodtracker.model.Mood;
import com.kadengood.moodtracker.utils.Storage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ImageView mCommentButton;
    private ImageView mHistoryButton;
    private Mood mood;
    private SimpleDateFormat sdf;
    private Calendar mCalendar;
    private String mCurrentDate;
    private MediaPlayer mSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCommentButton = findViewById(R.id.noteButton);
        mHistoryButton = findViewById(R.id.historyButton);

        /////////////////////////////
        //  SET UP COMMENT BUTTON  //
        /////////////////////////////
        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openDialog();
            }
        });

        ////////////////////////////
        //  SET UP HISTORY BUTTON //
        ////////////////////////////
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
            }
        });

        /////////////////////////
        //  GET DATE AND TIME  //
        /////////////////////////
        sdf = new SimpleDateFormat("ddMMyyyy");
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(new Date());
        mCalendar.add(Calendar.DATE, 0);
        mCurrentDate = sdf.format(mCalendar.getTime());


        ////////////////////////////
        //  LOAD MOOD OF THE DAY  //
        ////////////////////////////
        mood = Storage.load(this, mCurrentDate);

        // NEW DAY
        if(mood == null){
            mood = new Mood();
        }

        //////////////////////////
        //  VERTICAL VIEWPAGER  //
        //////////////////////////
        VerticalViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyViewPagerAdapter(this));
        viewPager.setCurrentItem(mood.getPosition());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            // MANAGE POSITION
            @Override
            public void onPageSelected(int i) {
                mood.setPosition(i);
                System.out.println(mood.getPosition());

                // STORE POSITION
                Storage.store(getApplicationContext(), mood, mCurrentDate);

                // PLAY MUSIC NOTES
                switch (i) {
                    case 0:
                        mSound = MediaPlayer.create(getApplicationContext(), R.raw.notea);
                        mSound.start();
                        break;
                    case 1:
                        mSound = MediaPlayer.create(getApplicationContext(), R.raw.noteb);
                        mSound.start();
                        break;
                    case 2:
                        mSound = MediaPlayer.create(getApplicationContext(), R.raw.notec);
                        mSound.start();
                        break;
                    case 3:
                        mSound = MediaPlayer.create(getApplicationContext(), R.raw.noted);
                        mSound.start();
                        break;
                    case 4:
                        mSound = MediaPlayer.create(getApplicationContext(), R.raw.notee);
                        mSound.start();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
    //////////////////////////
    //  CALL POP-UP WINDOW  //
    //////////////////////////
    public void openDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_comment, null);

        final EditText editTextComment = view.findViewById(R.id.edit_comment);

        builder.setView(view)
                .setTitle(R.string.comment)
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // SET UP COMMENT
                        String comment = editTextComment.getText().toString();
                        mood.setComment(comment);

                        // DISPLAY COMMENT
                        Toast toast=Toast.makeText(getApplicationContext(),comment,Toast.LENGTH_SHORT);
                        toast.show();

                        // STORE COMMENT
                        Storage.store(getApplicationContext(), mood, mCurrentDate);
                    }
                });
        builder.show();
    }
}