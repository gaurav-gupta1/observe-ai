package com.observeai.tinyurl.model;

import com.observeai.tinyurl.util.TinyUrlUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TinyUrlModel {

    private static Map<String, TinyUrl> tinyUrlMapping = new HashMap<>();
    private long id = 10000000000000L;

    public String getTinyUrl(String url) {
        TinyUrl tinyUrl = new TinyUrl();
        String base62EncodeUrl = TinyUrlUtil.getTinyUrl(id);
        tinyUrlMapping.put(base62EncodeUrl, tinyUrl);
        tinyUrl.setId(id++);
        tinyUrl.setFullUrl(url);
        tinyUrl.setTinyUrl(base62EncodeUrl);
        return base62EncodeUrl;
    }

    public String getUrl(String tinyUrl) {
        TinyUrl url = tinyUrlMapping.get(tinyUrl);
        if(url == null) {
            return null;
        }
        url.incrementNumberOfAccess();
        return url.getFullUrl();
    }

    public int getHitCount(String tinyUrl) {
        TinyUrl url = tinyUrlMapping.get(tinyUrl);
        if(url == null) {
            return -1;
        }
        return url.getNumberOfAccess();
    }
}
