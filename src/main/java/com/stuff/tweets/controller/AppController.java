package com.stuff.tweets.controller;

import com.stuff.tweets.model.Timeline;
import com.stuff.tweets.model.Tweet;
import com.stuff.tweets.model.User;
import com.stuff.tweets.services.PaginationService;
import com.stuff.tweets.services.TimelineService;
import com.stuff.tweets.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//singletweet endpoint thing by Andy

@RestController
public class AppController {

    @Autowired
    TwitterService service;

    @Autowired
    TimelineService timeline;

    @Autowired
    PaginationService pagination;

    @RequestMapping(path="/singletweet", method= RequestMethod.GET)
    public Tweet getATweet(@RequestParam String tweetId) {
        return service.getATweet(tweetId);
    }

    @RequestMapping(path="/singleuser", method= RequestMethod.GET)
    public User getAUser(@RequestParam String username) {
        return service.getAUser(username);
    }

    @RequestMapping(path="/timeline", method= RequestMethod.GET) //timeline endpoint
    public Timeline getATimeline(@RequestParam String username) {
        return timeline.getATimeline(username);
    }

    @RequestMapping(path="/pagination", method= RequestMethod.GET) //pagination
    public Timeline getAPagination(@RequestParam String username) {
        return pagination.getAPagination(username);
    }

}
