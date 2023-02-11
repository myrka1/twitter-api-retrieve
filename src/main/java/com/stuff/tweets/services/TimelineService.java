package com.stuff.tweets.services;

import com.stuff.tweets.model.Timeline;
import com.stuff.tweets.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stuff.tweets.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Timeline service online returns about 5 tweets each time

@Component
public class TimelineService {

    @Value("${twitter.token}")
    private String token;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    TwitterService service;

    public Timeline getATimeline(String username) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(this.token);
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);

        Timeline timeline = null;
        User user;
        List<Tweet> tweets = new ArrayList<>();

        user = service.getAUser(username);
        String userId = user.getId();

        String url = "https://api.twitter.com/2/users/" + userId + "/tweets?tweet.fields=created_at&expansions=author_id&user.fields=created_at&max_results=5";
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class
        );

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            JsonNode data = jsonNode.path("data"); //this gets us inside the data array?
            JsonNode includes = jsonNode.path("includes"); //to access user info inside includes

            Tweet tweet;
            for(JsonNode currentTweet : data) {
                String id = currentTweet.path("id").asText();
                tweet = service.getATweet(id);
                tweets.add(tweet);
            }

            timeline = new Timeline(user, tweets);

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return timeline;
    }

    //buffer writer?
    public void printTimeline(Timeline timeline) {
        File stuffFile = new File("pagination4.txt");
        if (!stuffFile.exists()) {
            try {
                stuffFile.createNewFile();
            } catch (IOException e) {
                System.out.println("File not created");
            }
        }
        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(stuffFile, true))) {
            dataOutput.println("USERNAME: " + timeline.getUser().getUsername());
            dataOutput.println("NAME: " + timeline.getUser().getName());
            dataOutput.println("USER ID: " + timeline.getUser().getId());
            dataOutput.println("USER CREATED AT: " + timeline.getUser().getCreated_at());
            dataOutput.println();
            for(Tweet tweet : timeline.getTweetList()) {
                dataOutput.println("TWEET TEXT: " + tweet.getText());
                dataOutput.print("CREATED AT: " + tweet.getCreated_at() + "      ");
                dataOutput.println("TWEET ID: " + tweet.getId());
                //dataOutput.println("TWEET ID: " + tweet.getId());
                //dataOutput.println("AUTHOR ID: " + tweet.getAuthor_id());
                dataOutput.println();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open the file for writing.");
        }
    }

}


