package com.intensostudios.grokit.lang.spring;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.intensostudios.grokit.lang.google.image.GoogleImageService;
import com.intensostudios.grokit.lang.google.image.ImageService;
import com.intensostudios.grokit.lang.google.texttospeech.GoogleTextToSpeech;
import com.intensostudios.grokit.lang.google.texttospeech.TextToSpeechService;
import com.intensostudios.grokit.lang.google.translate.GoogleTranslate;
import com.intensostudios.grokit.lang.google.translate.TranslateService;
import com.intensostudios.grokit.lang.http.HTTPService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@PropertySource("classpath:image-api-creds.properties")
public class SpringConfig {


    @Value( "${apiKey}" )
    private String apiKey;

    @Value("${cx}")
    private String cx;

    @Bean
    public ImageService imageService() {
        return new GoogleImageService(cx,apiKey,new HTTPService(new RestTemplate()));
    }

    @Bean
    public Credentials googleCredentials() {
        try {
            return GoogleCredentials.fromStream(
                    new ClassPathResource("google_key1.json").getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Bean
    public String googleApiKey() {
        try {
            return IOUtils.toString(new ClassPathResource("google_key1.json").getInputStream(),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public TranslateService translateService() {
        return new GoogleTranslate(googleCredentials());
    }

    @Bean
    public TextToSpeechService textToSpeechService() {
        return new GoogleTextToSpeech(googleCredentials());
    }

}


