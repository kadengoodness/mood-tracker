package com.kadengood.moodtracker.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.kadengood.moodtracker.model.Mood;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by kadengood on 3/16/19.
 */

public class Storage {

    public static void store(Context context, Mood mood, String moodKey){
        SharedPreferences mPreferences = context.getSharedPreferences(moodKey, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mood);
        prefsEditor.putString(moodKey,json);
        prefsEditor.apply();
    }

    public static Mood load(Context context, String moodKey){
        SharedPreferences mPreferences = context.getSharedPreferences(moodKey, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPreferences.getString(moodKey, "");
        Mood mood = gson.fromJson(json, Mood.class);
        return mood;
    }

}




