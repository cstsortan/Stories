package com.d4vinci.stories.models;

/**
 * Created by D4Vinci on 7/17/2017.
 */

public class Comment {
    private String text;
    private String author;

    public Comment() {
        //for firebase
    }

    public Comment(String text, String author) {
        this.text = text;
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
