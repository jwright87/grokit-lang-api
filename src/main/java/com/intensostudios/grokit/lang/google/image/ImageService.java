package com.intensostudios.grokit.lang.google.image;

import java.util.Optional;

public interface ImageService {

    /**
     * Retrieves an image icon associated with the given phrase
     * @return
     */
    byte[] getFirstIcon(String phrase);
}
