package com.eflexsoft.chooigbowords.model;

public class User {

    String email;
    String name;
    String profileImageUrl;
    String userId;

    public User() {
    }

    public User(String email, String name, String profileImageUrl, String userId) {
        this.email = email;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
