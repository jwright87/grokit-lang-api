package com.intensostudios.grokit.lang.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.function.Function;

public interface HTTP {

    <T> ResponseEntity<T> getForEntity(URI uri,Class<T> clazz);
}
