package com.d4vinci.stories.models;

/**
 * Created by D4Vinci on 7/17/2017.
 */

public class Story {
    private String text;
    private String author;
    private String photo;
    private String[] likes;
    private Comment[] comments;

    public Story() {
        //for firebase
    }

    public Story(String text, String author, String photo) {
        this.text = text;
        this.author = author;
        this.photo = photo;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String[] getLikes() {
        return likes;
    }

    public void setLikes(String[] likes) {
        this.likes = likes;
    }

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }
}
