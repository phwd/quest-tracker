package com.facebook.quicklog.identifiers;

public class AndroidTransliterationKeyboard {
    public static final int BIGRAM_GET_TRANSLITERATIONS = 15073283;
    public static final int BIGRAM_LANGUAGE_MODEL_LOAD = 15073282;
    public static final short MODULE_ID = 230;
    public static final int TRANSLITERATION_SUGGESTIONS_RETRIEVED = 15073281;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "ANDROID_TRANSLITERATION_KEYBOARD_BIGRAM_GET_TRANSLITERATIONS" : "ANDROID_TRANSLITERATION_KEYBOARD_BIGRAM_LANGUAGE_MODEL_LOAD" : "ANDROID_TRANSLITERATION_KEYBOARD_TRANSLITERATION_SUGGESTIONS_RETRIEVED";
    }
}
