# Tweets

Tweets is a Java program that retrieves tweet and user information from the Twitter API using OAuth 2.0 App-Only authentication.
It is built using Spring Boot and requires access to the Twitter API through the Twitter Developer platform.
## Features

    - Retrieves tweets using tweet ID and returns tweet text, tweet ID, tweet author ID, and tweet creation date
    - Retrieves a user based on username input and returns user username, user account name, user ID, and user creation date
    - Retrieves the first five recent tweets from a user's timeline using Twitter username
    - Retrieves the whole timeline of a user using Twitter username

## Usage

To use Tweets, follow these steps:

    Clone or download the repository to your local machine
    Import the project into your Java IDE
    Obtain access to the Twitter API through the Twitter Developer platform and obtain the Bearer Token
    Update the application.properties file located in the resources folder with the obtained Bearer Token
    Run the TweetsApplication class to start the Spring Boot application
    Access the endpoints in a web browser or REST client to retrieve tweet and user information

## Endpoints

    /singletweet: Retrieves information on a single tweet using tweet ID (GET method). The Twitter API endpoint used is "https://api.twitter.com/2/tweets/{TWEET ID}?tweet.fields=created_at,author_id".
			To access this endpoint, replace {TWEET ID HERE} in the local host link localhost:8080/singletweet?tweetId={TWEET ID HERE} with a 19-digit tweet ID, which can be found at the end of the URL of the tweet's page.
    /singleuser: Retrieves information on a single user using Twitter username (GET method). The Twitter API endpoint used is "https://api.twitter.com/2/users/by?usernames={TWITTER USERNAME}&user.fields=created_at".
			To access this endpoint, replace {TWITTER USERNAME} in the local host link localhost:8080/singleuser?username={TWITTER USERNAME} with a valid Twitter username.
    /timeline: Retrieves the first five recent tweets in a user's timeline using Twitter username (GET method). The Twitter API endpoint used is "https://api.twitter.com/2/users/{TWITTER USER ID}/tweets?tweet.fields=created_at&expansions=author_id&user.fields=created_at&max_results=5".
			Note that even though a Twitter username is accepted by the endpoint, the program uses the /singleuser endpoint to find the user ID from the username input.
    /pagination: Retrieves the whole timeline of a user using Twitter username (GET method). The Twitter API endpoint used is "https://api.twitter.com/2/users/{TWITTER USER ID}/tweets?tweet.fields=created_at,author_id&max_results=100".
			Note that even though a Twitter username is accepted by the endpoint, the program uses the /singleuser endpoint to find the user ID from the username input.

## Requirements

To use Tweets, you will need:

    Java 8 or later
    Spring Boot 2.0 or later
    Access to the Twitter API through the Twitter Developer platform
    Jackson library for object mapping and JSON processing
    Bearer Token obtained from the Twitter Developer platform

Note

This program is for demonstration purposes only and not meant to be used for any serious applications.
Make sure to follow Twitter's Developer Policy and Guidelines while using the Twitter API.

