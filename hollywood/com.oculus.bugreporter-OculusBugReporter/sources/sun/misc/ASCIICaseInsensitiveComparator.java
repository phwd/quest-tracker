package sun.misc;

import java.util.Comparator;

public class ASCIICaseInsensitiveComparator implements Comparator<String> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Comparator<String> CASE_INSENSITIVE_ORDER = new ASCIICaseInsensitiveComparator();

    public int compare(String s1, String s2) {
        char c1;
        char c2;
        int n1 = s1.length();
        int n2 = s2.length();
        int minLen = n1 < n2 ? n1 : n2;
        for (int i = 0; i < minLen; i++) {
            char c12 = s1.charAt(i);
            char c22 = s2.charAt(i);
            if (!(c12 == c22 || (c1 = (char) toLower(c12)) == (c2 = (char) toLower(c22)))) {
                return c1 - c2;
            }
        }
        return n1 - n2;
    }

    public static int lowerCaseHashCode(String s) {
        int h = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            h = (h * 31) + toLower(s.charAt(i));
        }
        return h;
    }

    static boolean isLower(int ch) {
        return ((ch + -97) | (122 - ch)) >= 0;
    }

    static boolean isUpper(int ch) {
        return ((ch + -65) | (90 - ch)) >= 0;
    }

    static int toLower(int ch) {
        return isUpper(ch) ? ch + 32 : ch;
    }

    static int toUpper(int ch) {
        return isLower(ch) ? ch - 32 : ch;
    }
}
