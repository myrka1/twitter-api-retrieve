package com.stuff.tweets.model;

import java.util.Date;

//class by Andy, author_id stuff added by me

public class Tweet {

    private String id;
    private String text;
    private String author_id;
    private String created_at;

    public String getId() {
        return id;
    }
    /*public void setId(String id) {
        this.id = id;
    } */
    public String getText() {
        return text;
    }
    /*public void setText(String text) {
        this.text = text;
    } */
    public String getAuthor_id() {
        return author_id;
    }
    /*public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    } */
    public String getCreated_at() {
        return created_at;
    }
    /* public void setCreated_at(String created_at) {
        this.created_at = created_at;
    } */

    public Tweet(String id, String text, String author_id, String created_at) {
        this.id = id;
        this.text = text;
        this.author_id = author_id;
        this.created_at = created_at;
    }

    @Override
    public String toString() { //doesn't do anything
        return "Tweet{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", author_id='" + author_id + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
