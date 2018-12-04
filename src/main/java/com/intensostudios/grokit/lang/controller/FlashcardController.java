package com.intensostudios.grokit.lang.controller;

import com.intensostudios.grokit.lang.google.image.ImageService;
import com.intensostudios.grokit.lang.google.texttospeech.TextToSpeechService;
import com.intensostudios.grokit.lang.google.translate.TranslateService;
import com.intensostudios.grokit.lang.model.CreateFlashReq;
import com.intensostudios.grokit.lang.model.Flashcard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FlashcardController {


    private static final Logger logger = LoggerFactory.getLogger(FlashcardController.class);

    private TranslateService translateService;
    private TextToSpeechService textToSpeechService;
    private ImageService imageService;

    public FlashcardController(TranslateService translateService, TextToSpeechService textToSpeechService, ImageService imageService) {
        this.translateService = translateService;
        this.textToSpeechService = textToSpeechService;
        this.imageService = imageService;
    }

    @RequestMapping(value = "/create", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Flashcard> createFlashcard(@RequestBody CreateFlashReq req) {

        Flashcard flashcard = new Flashcard();
        flashcard.setSourceLanguage(req.getSourceLang());
        flashcard.setTargetLanguage(req.getTargetLang());
        flashcard.setSourceText(req.getPhrase());
        logger.info("Translating...");
        translateService.translate(req.getSourceLang(), req.getTargetLang(), req.getPhrase()).ifPresentOrElse((c) -> {
            flashcard.setTargetText(c);
        }, () -> {
            logger.error("Translate Service Unavailable");
        });
        logger.info("Getting Audio...");
        flashcard.setTargetVoice(textToSpeechService.transform(flashcard.getTargetText()));
        logger.info("Getting Image..");
        flashcard.setImageIcon(imageService.getFirstIcon(req.getPhrase()));
        return ResponseEntity.ok(flashcard);
    }
}
