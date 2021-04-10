package java.net;

import android.icu.text.IDNA;
import android.icu.text.StringPrepParseException;

public final class IDN {
    public static String toASCII(String str, int i) {
        try {
            IDNA.convertIDNToASCII(str, i);
            throw null;
        } catch (StringPrepParseException e) {
            if (".".equals(str)) {
                return str;
            }
            throw new IllegalArgumentException("Invalid input to toASCII: " + str, e);
        }
    }

    public static String toASCII(String str) {
        toASCII(str, 0);
        return str;
    }
}
