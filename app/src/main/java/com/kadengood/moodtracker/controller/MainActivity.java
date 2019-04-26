package com.kadengood.moodtracker.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kadengood.moodtracker.R;
import com.kadengood.moodtracker.component.MyViewPagerAdapter;
import com.kadengood.moodtracker.component.VerticalViewPager;
import com.kadengood.moodtracker.model.Mood;
import com.kadengood.moodtracker.utils.Storage;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ImageView mCommentButton;
    private ImageView mHistoryButton;
    private Mood mood;
    private SimpleDateFormat sdf;
    private Calendar mCalendar;
    private String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCommentButton = findViewById(R.id.noteButton);
        mHistoryButton = findViewById(R.id.historyButton);

        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // Comment
                openDialog();
            }
        });

        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // History
                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
            }
        });

        sdf = new SimpleDateFormat("ddMMyyyy");
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(new Date());
        mCalendar.add(Calendar.DATE, 0);
        final String currentDate = sdf.format(mCalendar.getTime());
        //currentDate = DateFormat.getDateInstance(SimpleDateFormat.FULL).format(mCalendar.getTime());


        // Loading mood of the day
        mood = Storage.load(this, currentDate);

        // If there's no mood yet
        if(mood == null){
            mood = new Mood();
        }

        // Linking VerticalViewPager
        VerticalViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyViewPagerAdapter(this));
        viewPager.setCurrentItem(mood.getPosition());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mood.setPosition(i);
                System.out.println(mood.getPosition());

                // Storing position in SharedPreferences
                Storage.store(getApplicationContext(), mood, currentDate);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
    // Calling pop-up comment
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

                        // Setting up comment
                        String comment = editTextComment.getText().toString();
                        mood.setComment(comment);

                        // Toast message
                        Toast toast=Toast.makeText(getApplicationContext(),comment,Toast.LENGTH_SHORT);
                        toast.show();

                        // Storing comment in SharedPreferences
                        Storage.store(getApplicationContext(), mood, currentDate);

                    }
                });
        builder.show();

    }
}
