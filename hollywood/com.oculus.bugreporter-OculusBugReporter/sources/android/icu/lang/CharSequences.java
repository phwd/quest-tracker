package android.icu.lang;

import android.icu.text.UTF16;

@Deprecated
public class CharSequences {
    @Deprecated
    public static int matchAfter(CharSequence a, CharSequence b, int aIndex, int bIndex) {
        int i = aIndex;
        int j = bIndex;
        int alen = a.length();
        int blen = b.length();
        while (i < alen && j < blen && a.charAt(i) == b.charAt(j)) {
            i++;
            j++;
        }
        int result = i - aIndex;
        if (result == 0 || onCharacterBoundary(a, i) || onCharacterBoundary(b, j)) {
            return result;
        }
        return result - 1;
    }

    @Deprecated
    public int codePointLength(CharSequence s) {
        return Character.codePointCount(s, 0, s.length());
    }

    @Deprecated
    public static final boolean equals(int codepoint, CharSequence other) {
        if (other == null) {
            return false;
        }
        int length = other.length();
        if (length != 1) {
            if (length == 2 && codepoint > 65535 && codepoint == Character.codePointAt(other, 0)) {
                return true;
            }
            return false;
        } else if (codepoint == other.charAt(0)) {
            return true;
        } else {
            return false;
        }
    }

    @Deprecated
    public static final boolean equals(CharSequence other, int codepoint) {
        return equals(codepoint, other);
    }

    @Deprecated
    public static int compare(CharSequence string, int codePoint) {
        int result;
        if (codePoint < 0 || codePoint > 1114111) {
            throw new IllegalArgumentException();
        }
        int stringLength = string.length();
        if (stringLength == 0) {
            return -1;
        }
        char firstChar = string.charAt(0);
        int offset = codePoint - 65536;
        if (offset < 0) {
            int result2 = firstChar - codePoint;
            if (result2 != 0) {
                return result2;
            }
            return stringLength - 1;
        }
        int result3 = firstChar - ((char) ((offset >>> 10) + 55296));
        if (result3 != 0) {
            return result3;
        }
        if (stringLength <= 1 || (result = string.charAt(1) - ((char) ((offset & 1023) + UTF16.TRAIL_SURROGATE_MIN_VALUE))) == 0) {
            return stringLength - 2;
        }
        return result;
    }

    @Deprecated
    public static int compare(int codepoint, CharSequence a) {
        int result = compare(a, codepoint);
        if (result > 0) {
            return -1;
        }
        return result < 0 ? 1 : 0;
    }

    @Deprecated
    public static int getSingleCodePoint(CharSequence s) {
        int length = s.length();
        boolean z = true;
        if (length < 1 || length > 2) {
            return Integer.MAX_VALUE;
        }
        int result = Character.codePointAt(s, 0);
        boolean z2 = result < 65536;
        if (length != 1) {
            z = false;
        }
        if (z2 == z) {
            return result;
        }
        return Integer.MAX_VALUE;
    }

    @Deprecated
    public static final <T> boolean equals(T a, T b) {
        if (a == null) {
            return b == null;
        }
        if (b == null) {
            return false;
        }
        return a.equals(b);
    }

    @Deprecated
    public static int compare(CharSequence a, CharSequence b) {
        int alength = a.length();
        int blength = b.length();
        int min = alength <= blength ? alength : blength;
        for (int i = 0; i < min; i++) {
            int diff = a.charAt(i) - b.charAt(i);
            if (diff != 0) {
                return diff;
            }
        }
        return alength - blength;
    }

    @Deprecated
    public static boolean equalsChars(CharSequence a, CharSequence b) {
        return a.length() == b.length() && compare(a, b) == 0;
    }

    @Deprecated
    public static boolean onCharacterBoundary(CharSequence s, int i) {
        return i <= 0 || i >= s.length() || !Character.isHighSurrogate(s.charAt(i + -1)) || !Character.isLowSurrogate(s.charAt(i));
    }

    @Deprecated
    public static int indexOf(CharSequence s, int codePoint) {
        int i = 0;
        while (i < s.length()) {
            int cp = Character.codePointAt(s, i);
            if (cp == codePoint) {
                return i;
            }
            i += Character.charCount(cp);
        }
        return -1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: int[] */
    /* JADX DEBUG: Multi-variable search result rejected for r4v4, resolved type: int */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: Multiple debug info for r4v1 int: [D('last' char), D('j' int)] */
    /* JADX WARN: Type inference failed for: r3v2, types: [char] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int[] codePoints(java.lang.CharSequence r7) {
        /*
            int r0 = r7.length()
            int[] r0 = new int[r0]
            r1 = 0
            r2 = 0
        L_0x0008:
            int r3 = r7.length()
            if (r2 >= r3) goto L_0x003e
            char r3 = r7.charAt(r2)
            r4 = 56320(0xdc00, float:7.8921E-41)
            if (r3 < r4) goto L_0x0036
            r4 = 57343(0xdfff, float:8.0355E-41)
            if (r3 > r4) goto L_0x0036
            if (r2 == 0) goto L_0x0036
            int r4 = r1 + -1
            r4 = r0[r4]
            char r4 = (char) r4
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r5) goto L_0x0036
            r5 = 56319(0xdbff, float:7.892E-41)
            if (r4 > r5) goto L_0x0036
            int r5 = r1 + -1
            int r6 = java.lang.Character.toCodePoint(r4, r3)
            r0[r5] = r6
            goto L_0x003b
        L_0x0036:
            int r4 = r1 + 1
            r0[r1] = r3
            r1 = r4
        L_0x003b:
            int r2 = r2 + 1
            goto L_0x0008
        L_0x003e:
            int r2 = r0.length
            if (r1 != r2) goto L_0x0042
            return r0
        L_0x0042:
            int[] r2 = new int[r1]
            r3 = 0
            java.lang.System.arraycopy(r0, r3, r2, r3, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.lang.CharSequences.codePoints(java.lang.CharSequence):int[]");
    }

    private CharSequences() {
    }
}
