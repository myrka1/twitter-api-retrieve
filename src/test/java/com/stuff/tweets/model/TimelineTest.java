package com.stuff.tweets.model;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class TimelineTest {
    /*user
    "id": "781581049885171712",
            "created_at": "2016-09-29T19:46:58.000Z",
            "username": "emwaiarkayeii",
            "name": "myrka🎆"
    //tweet list
     */
    /*{
        "id": "1609768907216416771",
            "text": "Gone girl is a scary movie because wtf",
            "author_id": "781581049885171712",
            "edit_history_tweet_ids": [
        "1609768907216416771"
            ],
        "created_at": "2023-01-02T04:29:50.000Z"
    },
    {
        "id": "1609455919993352197",
            "text": "I waited all year to tweet that😌",
            "author_id": "781581049885171712",
            "edit_history_tweet_ids": [
        "1609455919993352197"
            ],
        "created_at": "2023-01-01T07:46:08.000Z"
    },

     */

    @Test
    public void testTimeline() {
        User user = new User("781581049885171712", "myrka\uD83C\uDF86", "emwaiarkayeii", "2016-09-29T19:46:58.000Z");
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(new Tweet("1609768907216416771", "Gone girl is a scary movie because wtf", "781581049885171712", "2023-01-02T04:29:50.000Z"));
        tweets.add(new Tweet("1609455919993352197", "I waited all year to tweet that\uD83D\uDE0C", "781581049885171712", "2023-01-01T07:46:08.000Z"));

        Timeline timeline = new Timeline(user, tweets);
        Assert.assertEquals("emwaiarkayeii", timeline.getUser().getUsername());
        Assert.assertEquals("2016-09-29T19:46:58.000Z", timeline.getUser().getCreated_at());
        //testing tweet list
        System.out.println(timeline.getTweetList().get(0).getText());
        Assert.assertEquals("1609455919993352197", timeline.getTweetList().get(1).getId());
    }

    @Test
    void getUser() {
    }

    @Test
    void setUser() {
    }

    @Test
    void getTweetList() {
    }

    @Test
    void setTweetList() {
    }
}