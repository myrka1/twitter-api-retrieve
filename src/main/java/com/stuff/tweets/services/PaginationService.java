package com.stuff.tweets.services;

import com.stuff.tweets.model.Timeline;
import com.stuff.tweets.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.ArrayList;
import java.util.List;

//Pagination service is like timeline but it uses a next_token

@Component
public class PaginationService {

    @Value("${twitter.token}")
    private String token;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    TwitterService service;

    @Autowired
    TimelineService timelinePrint;

    public Timeline getAPagination(String username) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(this.token);
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);

        Timeline pagination = null;
        User user;
        List<Tweet> tweets = new ArrayList<>();
        int count = 0; //keeps count of how many paginations have happened
        int rtCount = 0; //keeps count of retweets that were not added to the Timeline object
        int tweetCount = 0; //keeps count of tweets that were added to the Timeline object

        user = service.getAUser(username);
        String userId = user.getId();
        String url = "https://api.twitter.com/2/users/" + userId + "/tweets?tweet.fields=created_at,author_id&max_results=100";
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class
        );

        try{
            Tweet tweet;
            String pagination_token = "";
            do {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.getBody());
                JsonNode data = jsonNode.path("data"); //this gets us inside the data array?
                JsonNode includes = jsonNode.path("includes"); //to access user info inside includes
                JsonNode meta = jsonNode.path("meta"); //will use later for next_token

                for(JsonNode currentTweet : data) {
                    String testRt = currentTweet.path("text").asText();
                    String text = "";
                    String id = "";
                    String author_id = "";
                    String created_at = "";
                    if(testRt.contains("@") && testRt.contains(":")) {
                        System.out.println("retweet: " + testRt);
                        rtCount++;
                    }
                    else {
                        id = currentTweet.path("id").asText();
                        text = currentTweet.path("text").asText();
                        author_id = currentTweet.path("author_id").asText();
                        created_at = currentTweet.path("created_at").asText();
                        tweet = new Tweet(id, text, author_id, created_at);
                        tweets.add(tweet);
                        tweetCount++;
                    }
                }
                pagination_token = meta.path("next_token").asText();
                System.out.println("pagination token: " + pagination_token);
                if(pagination_token.length() > 0) {
                    String url2 = url + "&pagination_token=" + pagination_token;
                    response = restTemplate.exchange(url2,HttpMethod.GET, request, String.class);
                }
                count++;
            } while(pagination_token.length() > 0);

            System.out.println("Pagination COUNT: " + count);
            System.out.println("Retweet count: " + rtCount);
            System.out.println("Tweet count: " + tweetCount);
            pagination = new Timeline(user, tweets);
            timelinePrint.printTimeline(pagination);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return pagination;
    }
}
