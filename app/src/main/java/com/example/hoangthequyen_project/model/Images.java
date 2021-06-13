package com.example.hoangthequyen_project.model;

import android.media.Image;

import java.io.Serializable;

public class Images implements Serializable {
    private String Topic;
    private String Title;
    private String Url;

    public Images(){}

    public Images( String topic, String title, String url) {

        this.Topic = topic;
        this.Title = title;
        this.Url = url;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }




}
