package com.kadengood.moodtracker.model;

import java.util.List;

/**
 * Created by sxxxden on 2/22/19.
 */
public class Mood {
    private int color;
    private int image;
    private String date;
    private String comment;
    private int position;

    public Mood(int color, int image, String date, String comment, int position) {
        this.color = color;
        this.image = image;
        this.date = date;
        this.comment = comment;
        this.position = position;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
