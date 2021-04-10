package sun.misc;

import java.util.Comparator;

public class ASCIICaseInsensitiveComparator implements Comparator {
    public static final Comparator CASE_INSENSITIVE_ORDER = new ASCIICaseInsensitiveComparator();

    static boolean isUpper(int i) {
        return ((90 - i) | (i + -65)) >= 0;
    }

    public int compare(String str, String str2) {
        char lower;
        char lower2;
        int length = str.length();
        int length2 = str2.length();
        int i = length < length2 ? length : length2;
        for (int i2 = 0; i2 < i; i2++) {
            char charAt = str.charAt(i2);
            char charAt2 = str2.charAt(i2);
            if (!(charAt == charAt2 || (lower = (char) toLower(charAt)) == (lower2 = (char) toLower(charAt2)))) {
                return lower - lower2;
            }
        }
        return length - length2;
    }

    public static int lowerCaseHashCode(String str) {
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i = (i * 31) + toLower(str.charAt(i2));
        }
        return i;
    }

    static int toLower(int i) {
        return isUpper(i) ? i + 32 : i;
    }
}
