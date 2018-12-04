package com.intensostudios.grokit.lang.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class TestUtils {

    private static final Logger logger = LoggerFactory.getLogger(TestUtils.class);


    public static void writeFile(byte[] data, String path) {
        try (OutputStream out = new FileOutputStream(path)) {
            out.write(data);
            logger.debug("Content written to file {}", path);
        } catch (Exception e) {
            logger.error("Unable to write file", e);
        }
    }

    public static void openImage(String path) {
        try {
            Runtime.getRuntime().exec(new String[]{"eog", path});
        } catch (IOException e) {
            throw new RuntimeException("Unable to open image", e);
        }
    }

    public static void playMP3(String path) {
        try {
            Runtime.getRuntime().exec(new String[]{"vlc", path});
        } catch (IOException e) {
            throw new RuntimeException("Unable to play sound", e);
        }


    }
}
