package com.intensostudios.grokit.lang.google.translate;

import com.google.auth.Credentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.util.Optional;

public class GoogleTranslate implements TranslateService {

    private Translate translate;

    public GoogleTranslate(Credentials creds) {
        Credentials creds1 = creds;
        translate = TranslateOptions.newBuilder().setCredentials(creds).build().getService();
    }


    @Override
    public Optional<String> translate(String src, String target, String text) {

        try {
            Translation translation = translate.translate(text,
                    TranslateOption.sourceLanguage(src),
                    TranslateOption.targetLanguage(target));
            return Optional.of(translation.getTranslatedText());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }

    }
}
