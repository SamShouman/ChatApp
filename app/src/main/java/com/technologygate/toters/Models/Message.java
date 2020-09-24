package com.technologygate.toters.Models;

public class Message {

    private int id;
    private int receiver;
    private String timeStamp;
    private String content;

    public Message(int id, int receiver, String timeStamp, String content) {
        this.id = id;
        this.receiver = receiver;
        this.timeStamp = timeStamp;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
