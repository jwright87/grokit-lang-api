package com.intensostudios.grokit.lang.google.translate;

import java.util.Optional;

public interface TranslateService {

    public Optional<String> translate(String src, String target, String text);
}
