package com.observeai.tinyurl.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TinyUrlService {

    @Autowired
    private TinyUrlModel tinyUrlModel;

    public String getTinyUrl(String fullUrl) {
        return tinyUrlModel.getTinyUrl(fullUrl);
    }

    public String getFullUrl(String tinyUrl) {
        return tinyUrlModel.getUrl(tinyUrl);
    }

    public int getHitCount(String tinyUrl) {
        return tinyUrlModel.getHitCount(tinyUrl);
    }
}
