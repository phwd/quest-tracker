package com.oculus.panelapp.keyboardv2;

public enum KeyboardSize {
    UNKNOWN,
    SMALL,
    LARGE;

    public static String getSizeSuffix(KeyboardSize keyboardSize) {
        return keyboardSize == LARGE ? "48" : "32";
    }

    public String toString() {
        return name().toLowerCase();
    }
}
