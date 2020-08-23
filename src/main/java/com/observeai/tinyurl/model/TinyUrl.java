package com.observeai.tinyurl.model;

public class TinyUrl {
    private long id;
    private String fullUrl;
    private String tinyUrl;
    private int numberOfAccess = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getTinyUrl() {
        return tinyUrl;
    }

    public void setTinyUrl(String tinyUrl) {
        this.tinyUrl = tinyUrl;
    }

    public int getNumberOfAccess() {
        return numberOfAccess;
    }

    public void incrementNumberOfAccess() {
        numberOfAccess++;
    }
}
