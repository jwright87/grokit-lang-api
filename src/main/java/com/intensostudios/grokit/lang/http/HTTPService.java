package com.intensostudios.grokit.lang.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class HTTPService implements HTTP {

    private RestTemplate restTemplate;


    public HTTPService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> ResponseEntity<T> getForEntity(URI uri, Class<T> clazz) {
        return restTemplate.getForEntity(uri,clazz);
    }
}
