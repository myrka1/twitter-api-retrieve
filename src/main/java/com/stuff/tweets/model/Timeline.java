package com.stuff.tweets.model;

import com.stuff.tweets.services.TwitterService;

import java.io.*;
import java.util.List;

public class Timeline {

    private User user;
    private List<Tweet> tweetList;


    public Timeline(User user, List<Tweet> tweetList) {
        this.user = user;
        this.tweetList = tweetList;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Tweet> getTweetList() {
        return tweetList;
    }
    public void setTweetList(List<Tweet> tweetList) {
        this.tweetList = tweetList;
    }


}
