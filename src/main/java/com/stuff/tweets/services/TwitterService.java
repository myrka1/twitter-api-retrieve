package com.stuff.tweets.services;

import com.stuff.tweets.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stuff.tweets.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//getATweet by Andy

@Component
public class TwitterService {

    @Value("${twitter.token}")
    private String token;

    private RestTemplate restTemplate = new RestTemplate();

    public Tweet getATweet(String tweetId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(this.token);
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);

        Tweet tweet = null;

        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.twitter.com/2/tweets/" + tweetId + "?tweet.fields=created_at,author_id",
                HttpMethod.GET,
                request,
                String.class
        );
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            String id = jsonNode.path("data").path("id").asText();
            String text = jsonNode.path("data").path("text").asText();
            String author_id = jsonNode.path("data").path("author_id").asText();
            String created_at = jsonNode.path("data").path("created_at").asText();
            tweet = new Tweet(id, text, author_id, created_at);

        } catch (JsonMappingException e) {
            //e.printStackTrace();
        } catch (JsonProcessingException e) {
            //e.printStackTrace();
        }
        return tweet;
    }


//******************************************************GET A USER ******************************************************

    public User getAUser(String username) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(this.token);
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);

        User user = null;

        String url = "https://api.twitter.com/2/users/by?usernames=" + username + "&user.fields=created_at";

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class
        );

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            JsonNode data = jsonNode.path("data"); //this gets us inside the array?

            String userName = "";
            String id = "";
            String name = "";
            String created_at = "";
            for(JsonNode x : data) {
                userName = x.path("username").asText();
                id = x.path("id").asText();
                created_at = x.path("created_at").asText();
                name = x.path("name").asText();
            }
            user = new User(id, name, userName, created_at);

        } catch (JsonMappingException e) {
            //e.printStackTrace();
        } catch (JsonProcessingException e) {
            //e.printStackTrace();
        }
        return user;
    }
}
