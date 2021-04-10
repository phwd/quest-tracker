package android.icu.impl.locale;

import android.icu.impl.Utility;

public final class AsciiUtil {
    public static boolean isAlpha(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public static boolean isNumeric(char c) {
        return c >= '0' && c <= '9';
    }

    public static char toLower(char c) {
        return (c < 'A' || c > 'Z') ? c : (char) (c + ' ');
    }

    public static char toUpper(char c) {
        return (c < 'a' || c > 'z') ? c : (char) (c - ' ');
    }

    public static boolean caseIgnoreMatch(String str, String str2) {
        if (Utility.sameObjects(str, str2)) {
            return true;
        }
        int length = str.length();
        if (length != str2.length()) {
            return false;
        }
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            char charAt2 = str2.charAt(i);
            if (charAt != charAt2 && toLower(charAt) != toLower(charAt2)) {
                break;
            }
            i++;
        }
        if (i == length) {
            return true;
        }
        return false;
    }

    public static int caseIgnoreCompare(String str, String str2) {
        if (Utility.sameObjects(str, str2)) {
            return 0;
        }
        return toLowerString(str).compareTo(toLowerString(str2));
    }

    public static String toLowerString(String str) {
        int i = 0;
        while (i < str.length() && ((r2 = str.charAt(i)) < 'A' || r2 > 'Z')) {
            i++;
        }
        if (i == str.length()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.substring(0, i));
        while (i < str.length()) {
            sb.append(toLower(str.charAt(i)));
            i++;
        }
        return sb.toString();
    }

    public static String toUpperString(String str) {
        int i = 0;
        while (i < str.length() && ((r2 = str.charAt(i)) < 'a' || r2 > 'z')) {
            i++;
        }
        if (i == str.length()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.substring(0, i));
        while (i < str.length()) {
            sb.append(toUpper(str.charAt(i)));
            i++;
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String toTitleString(java.lang.String r4) {
        /*
            int r0 = r4.length()
            if (r0 != 0) goto L_0x0007
            return r4
        L_0x0007:
            r0 = 0
            char r1 = r4.charAt(r0)
            r2 = 97
            if (r1 < r2) goto L_0x0017
            r2 = 122(0x7a, float:1.71E-43)
            if (r1 <= r2) goto L_0x0015
            goto L_0x0017
        L_0x0015:
            r2 = r0
            goto L_0x002a
        L_0x0017:
            r2 = 1
        L_0x0018:
            int r3 = r4.length()
            if (r2 >= r3) goto L_0x002a
            r3 = 65
            if (r1 < r3) goto L_0x0027
            r3 = 90
            if (r1 > r3) goto L_0x0027
            goto L_0x002a
        L_0x0027:
            int r2 = r2 + 1
            goto L_0x0018
        L_0x002a:
            int r1 = r4.length()
            if (r2 != r1) goto L_0x0031
            return r4
        L_0x0031:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = r4.substring(r0, r2)
            r1.<init>(r0)
            if (r2 != 0) goto L_0x0049
            char r0 = r4.charAt(r2)
            char r0 = toUpper(r0)
            r1.append(r0)
        L_0x0047:
            int r2 = r2 + 1
        L_0x0049:
            int r0 = r4.length()
            if (r2 >= r0) goto L_0x005b
            char r0 = r4.charAt(r2)
            char r0 = toLower(r0)
            r1.append(r0)
            goto L_0x0047
        L_0x005b:
            java.lang.String r4 = r1.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.locale.AsciiUtil.toTitleString(java.lang.String):java.lang.String");
    }

    public static boolean isAlphaString(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!isAlpha(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumericString(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!isNumeric(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isNumeric(c);
    }

    public static boolean isAlphaNumericString(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!isAlphaNumeric(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static class CaseInsensitiveKey {
        private int _hash;
        private String _key;

        public CaseInsensitiveKey(String str) {
            this._key = str;
            this._hash = AsciiUtil.toLowerString(str).hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof CaseInsensitiveKey) {
                return AsciiUtil.caseIgnoreMatch(this._key, ((CaseInsensitiveKey) obj)._key);
            }
            return false;
        }

        public int hashCode() {
            return this._hash;
        }
    }
}
