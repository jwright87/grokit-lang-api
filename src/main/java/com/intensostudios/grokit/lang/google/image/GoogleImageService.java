package com.intensostudios.grokit.lang.google.image;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intensostudios.grokit.lang.http.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoogleImageService implements ImageService {

    private String cx, apiKey;
    private static final Logger logger = LoggerFactory.getLogger(GoogleImageService.class);
    HTTP http;
    public GoogleImageService(String cx, String apiKey, HTTP http) {

        this.cx = cx;
        this.apiKey = apiKey;
        this.http =  http;
    }

    ResponseEntity<String> callCustomSearch(String phrase) {
        String url = "https://www.googleapis.com/customsearch/v1?" +
                "q=" + phrase +
                "&cx=" + cx + "%3Awp6o7xradek&" +
                "fileType=png&i" +
                "mgSize=icon&" +
                "num=4&" +
                "searchType=image&" +
                "key=" + apiKey;

        URI uri = URI.create(url);
        return http.getForEntity(uri, String.class);
    }

    private ResponseEntity<byte[]> fetchImage(String url) {
        return http.getForEntity(URI.create(url),byte[].class);
    }

    @Override
    public byte[] getFirstIcon(String phrase) {

        ResponseEntity<String> callResponse = callCustomSearch(phrase);
        if (callResponse.getStatusCode().is2xxSuccessful()) {
            HashMap<String, Object> result =
                    null;
            try {
                result = new ObjectMapper().readValue(callResponse.getBody(), HashMap.class);
            } catch (IOException e) {
                logger.error("Json mapping problem", e);
                return null;
            }
            List<Map<String, String>> items = (List<Map<String, String>>) result.get("items");
            String link = items.get(0).get("link");
            logger.debug("Link: {},", link);
            ResponseEntity<byte[]> imageResponse = fetchImage(link);
            if (imageResponse.getStatusCode().is2xxSuccessful()) {
                return imageResponse.getBody();
            }else {
                logger.error("Unable to fetch image",imageResponse.getBody());
                return null;
            }
        } else {
            logger.warn("Unable to call get image: {}", callResponse.getBody());
            return null;
        }

    }


}
