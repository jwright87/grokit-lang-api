package com.intensostudios.grokit.lang.model;

public class Flashcard {

    private String sourceLanguage,targetLanguage;
    private String sourceText,targetText;
    private byte[] sourceVoice,targetVoice;
    private byte[] imageIcon;

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public String getTargetText() {
        return targetText;
    }

    public void setTargetText(String targetText) {
        this.targetText = targetText;
    }

    public byte[] getSourceVoice() {
        return sourceVoice;
    }

    public void setSourceVoice(byte[] sourceVoice) {
        this.sourceVoice = sourceVoice;
    }

    public byte[] getTargetVoice() {
        return targetVoice;
    }

    public void setTargetVoice(byte[] targetVoice) {
        this.targetVoice = targetVoice;
    }

    public byte[] getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(byte[] imageIcon) {
        this.imageIcon = imageIcon;
    }
}
