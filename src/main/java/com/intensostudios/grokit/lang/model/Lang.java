package com.intensostudios.grokit.lang.model;

public enum Lang {
    ENGLISH("en", "English"), FRENCH("fr", "French");

    private String code, name;

    Lang(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
