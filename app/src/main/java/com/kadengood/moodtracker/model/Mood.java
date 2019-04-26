package com.kadengood.moodtracker.model;

/**
 * Created by kadengood on 2/22/19.
 */
// Mood Class
public class Mood {
    private String comment;
    private int position;

//    Constructor 1
    public Mood(String comment, int position) {
        this.comment = comment;
        this.position = position;
    }

    // Constructor 2
    public Mood() {
        position = 1;
        comment = "";
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
