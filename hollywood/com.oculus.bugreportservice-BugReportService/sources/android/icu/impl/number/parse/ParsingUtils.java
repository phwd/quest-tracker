package android.icu.impl.number.parse;

import android.icu.text.UnicodeSet;

public class ParsingUtils {
    public static boolean safeContains(UnicodeSet unicodeSet, CharSequence charSequence) {
        return charSequence.length() != 0 && unicodeSet.contains(charSequence);
    }
}
