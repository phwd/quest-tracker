package android.icu.impl;

import android.icu.text.StringPrep;

public final class IDNA2003 {
    private static char[] ACE_PREFIX = {'x', 'n', '-', '-'};
    private static final StringPrep namePrep = StringPrep.getInstance(0);

    private static boolean isLabelSeparator(int i) {
        return i == 46 || i == 12290 || i == 65294 || i == 65377;
    }

    private static int getSeparatorIndex(char[] cArr, int i, int i2) {
        while (i < i2 && !isLabelSeparator(cArr[i])) {
            i++;
        }
        return i;
    }

    public static StringBuffer convertIDNToASCII(String str, int i) {
        char[] charArray = str.toCharArray();
        new StringBuffer();
        new String(charArray, 0, getSeparatorIndex(charArray, 0, charArray.length) - 0);
        throw null;
    }
}
