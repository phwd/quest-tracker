package com.oculus.panelapp.keyboardv2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum KeyCode {
    NONE(0),
    SHIFT(-1),
    ACTION_KEY(-2),
    LAYOUT(-3),
    DISMISS(-4),
    BACKSPACE(-5),
    RETURN_TO_CURRENT_LANGUAGE(-7),
    ARROW_UP(-8),
    ARROW_DOWN(-9),
    ARROW_LEFT(-10),
    ARROW_RIGHT(-11),
    PHONE_INPUT(-12),
    TRANSCRIBE(-13),
    WORD_PREDICTION(-14),
    JP_CONVERSION(-15),
    JP_SCROLL_LEFT(-16),
    JP_SCROLL_RIGHT(-17),
    JP_DIACRITIC(-18),
    JP_SELECT_KAOMOJI(-19);
    
    private static final Map<Integer, KeyCode> lookup;
    private static final Map<String, KeyCode> nameLookup;
    public final int value;

    static {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        KeyCode[] values = values();
        for (KeyCode keyCode : values) {
            hashMap.put(Integer.valueOf(keyCode.value), keyCode);
            hashMap2.put(keyCode.name(), keyCode);
        }
        lookup = Collections.unmodifiableMap(hashMap);
        nameLookup = Collections.unmodifiableMap(hashMap2);
    }

    private KeyCode(Integer num) {
        this.value = num.intValue();
    }

    public static KeyCode get(int i) {
        KeyCode keyCode = lookup.get(Integer.valueOf(i));
        return keyCode == null ? NONE : keyCode;
    }

    public static KeyCode get(String str) {
        KeyCode keyCode = nameLookup.get(str);
        return keyCode == null ? NONE : keyCode;
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return name().toLowerCase();
    }
}
