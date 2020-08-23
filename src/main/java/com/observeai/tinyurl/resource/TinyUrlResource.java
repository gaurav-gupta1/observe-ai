package com.observeai.tinyurl.resource;

import com.observeai.tinyurl.model.TinyUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/api")
@RestController
public class TinyUrlResource {

    @Autowired
    private TinyUrlService tinyUrlService;

    @GetMapping("/{id}")
    public void getUrl(HttpServletResponse response, @PathVariable String id) throws IOException {
        String fullUrl = tinyUrlService.getFullUrl(id);
        if(fullUrl == null) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return;
        }
        response.sendRedirect(fullUrl);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody String url) {
        return new ResponseEntity<Object>(tinyUrlService.getTinyUrl(url), HttpStatus.OK);
    }

    @GetMapping("/hitcount/{id}")
    public String getHitCount(@PathVariable String id) {
        int hitCount = tinyUrlService.getHitCount(id);
        if(hitCount == -1) {
            return String.format("Tiny url %s not present in database", id);
        }
        return String.format("Tiny url %s accessed %s times", id, tinyUrlService.getHitCount(id));
    }
}
