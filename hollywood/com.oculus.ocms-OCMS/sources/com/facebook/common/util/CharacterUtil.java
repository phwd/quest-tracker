package com.facebook.common.util;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class CharacterUtil {
    public static boolean isCombiningCodePoint(int i) {
        return i >= 8400 && i <= 8447;
    }

    public static boolean isPunctuation(char c) {
        return c == '.' || c == ',' || c == '?' || c == ';' || c == '!';
    }
}
