package com.technologygate.toters.Models;

public class User {

    private int id;
    private int profile;
    private String timeStamp;
    private String name;
    private String lastMessage;


    public User() {
    }

    public User(int id, String timeStamp, String name, String lastMessage, int profile) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.name = name;
        this.lastMessage = lastMessage;
        this.profile = profile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }
}
