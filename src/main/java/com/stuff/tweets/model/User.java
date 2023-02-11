package com.stuff.tweets.model;

public class User {
    private String username;
    private String id;
    private String created_at;
    private String name;

    public String getUsername() {
        return username;
    }
    /*public void setUsername(String username) {
        this.username = username;
    } */
    public String getId() {
        return id;
    }
    /*public void setId(String id) {
        this.id = id;
    } */
    public String getCreated_at() {
        return created_at;
    }
   /* public void setCreated_at(String created_at) {
        this.created_at = created_at;
    } */
    public String getName() {
        return name;
    }
    /*public void setName(String name) {
        this.name = name;
    } */

    public User(String id, String name, String username, String created_at) {
        this.username = username;
        this.id = id;
        this.created_at = created_at;
        this.name = name;
    }

}
