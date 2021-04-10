package com.oculus.panelapp.keyboardv2.japanese;

public class Prediction {
    private String candidate;
    private int frequency;
    private String stroke;

    public Prediction(String str, String str2, int i) {
        this.candidate = str;
        this.stroke = str2;
        this.frequency = i;
    }

    public String getCandidate() {
        return this.candidate;
    }

    public void setCandidate(String str) {
        if (str == null) {
            this.candidate = str;
            return;
        }
        throw new IllegalArgumentException("setCandidate can't accept a null candidate string");
    }

    public String getStroke() {
        return this.stroke;
    }

    public void setStroke(String str) {
        if (str != null) {
            this.stroke = str;
            return;
        }
        throw new IllegalArgumentException("setStroke can't accept a null candidate string");
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int i) {
        if (i > 0) {
            this.frequency = i;
            return;
        }
        throw new IllegalArgumentException("setFrequency cannot accept a frequency less than 0");
    }
}
