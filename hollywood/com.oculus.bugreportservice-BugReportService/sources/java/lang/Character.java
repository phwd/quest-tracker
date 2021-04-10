package java.lang;

import java.io.Serializable;

public final class Character implements Serializable, Comparable {
    private static final byte[] DIRECTIONALITY = {0, 1, 3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 15, 2, 16, 17, 18, 8, 9};
    public static final Class TYPE = Class.getPrimitiveClass("char");
    private static final long serialVersionUID = 3786198910865385080L;
    private final char value;

    public static int charCount(int i) {
        return i >= 65536 ? 2 : 1;
    }

    public static int compare(char c, char c2) {
        return c - c2;
    }

    static native int digitImpl(int i, int i2);

    public static char forDigit(int i, int i2) {
        if (i >= i2 || i < 0 || i2 < 2 || i2 > 36) {
            return 0;
        }
        return (char) (i < 10 ? i + 48 : i + 87);
    }

    static native int getTypeImpl(int i);

    public static char highSurrogate(int i) {
        return (char) ((i >>> 10) + 55232);
    }

    public static boolean isBmpCodePoint(int i) {
        return (i >>> 16) == 0;
    }

    static native boolean isDigitImpl(int i);

    public static boolean isHighSurrogate(char c) {
        return c >= 55296 && c < 56320;
    }

    public static boolean isISOControl(int i) {
        return i <= 159 && (i >= 127 || (i >>> 5) == 0);
    }

    static native boolean isLetterImpl(int i);

    static native boolean isLetterOrDigitImpl(int i);

    public static boolean isLowSurrogate(char c) {
        return c >= 56320 && c < 57344;
    }

    static native boolean isLowerCaseImpl(int i);

    static native boolean isSpaceCharImpl(int i);

    public static boolean isSurrogate(char c) {
        return c >= 55296 && c < 57344;
    }

    static native boolean isTitleCaseImpl(int i);

    static native boolean isUpperCaseImpl(int i);

    public static boolean isValidCodePoint(int i) {
        return (i >>> 16) < 17;
    }

    static native boolean isWhitespaceImpl(int i);

    public static char lowSurrogate(int i) {
        return (char) ((i & 1023) + 56320);
    }

    public static int toCodePoint(char c, char c2) {
        return ((c << '\n') + c2) - 56613888;
    }

    static native int toLowerCaseImpl(int i);

    static native int toUpperCaseImpl(int i);

    public Character(char c) {
        this.value = c;
    }

    private static class CharacterCache {
        static final Character[] cache = new Character[128];

        static {
            int i = 0;
            while (true) {
                Character[] chArr = cache;
                if (i < chArr.length) {
                    chArr[i] = new Character((char) i);
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public static Character valueOf(char c) {
        if (c <= 127) {
            return CharacterCache.cache[c];
        }
        return new Character(c);
    }

    public char charValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Character) || this.value != ((Character) obj).charValue()) {
            return false;
        }
        return true;
    }

    public String toString() {
        String.valueOf(new char[]{this.value});
        throw null;
    }

    public static String toString(char c) {
        return String.valueOf(c);
    }

    public static int codePointAt(CharSequence charSequence, int i) {
        int i2;
        char charAt = charSequence.charAt(i);
        if (isHighSurrogate(charAt) && (i2 = i + 1) < charSequence.length()) {
            char charAt2 = charSequence.charAt(i2);
            if (isLowSurrogate(charAt2)) {
                return toCodePoint(charAt, charAt2);
            }
        }
        return charAt;
    }

    public static int codePointAt(char[] cArr, int i, int i2) {
        if (i < i2 && i2 >= 0 && i2 <= cArr.length) {
            return codePointAtImpl(cArr, i, i2);
        }
        throw new IndexOutOfBoundsException();
    }

    static int codePointAtImpl(char[] cArr, int i, int i2) {
        int i3;
        char c = cArr[i];
        if (isHighSurrogate(c) && (i3 = i + 1) < i2) {
            char c2 = cArr[i3];
            if (isLowSurrogate(c2)) {
                return toCodePoint(c, c2);
            }
        }
        return c;
    }

    public static int codePointBefore(CharSequence charSequence, int i) {
        int i2 = i - 1;
        char charAt = charSequence.charAt(i2);
        if (isLowSurrogate(charAt) && i2 > 0) {
            char charAt2 = charSequence.charAt(i2 - 1);
            if (isHighSurrogate(charAt2)) {
                return toCodePoint(charAt2, charAt);
            }
        }
        return charAt;
    }

    public static int codePointBefore(char[] cArr, int i, int i2) {
        if (i > i2 && i2 >= 0 && i2 < cArr.length) {
            return codePointBeforeImpl(cArr, i, i2);
        }
        throw new IndexOutOfBoundsException();
    }

    static int codePointBeforeImpl(char[] cArr, int i, int i2) {
        int i3 = i - 1;
        char c = cArr[i3];
        if (isLowSurrogate(c) && i3 > i2) {
            char c2 = cArr[i3 - 1];
            if (isHighSurrogate(c2)) {
                return toCodePoint(c2, c);
            }
        }
        return c;
    }

    public static int toChars(int i, char[] cArr, int i2) {
        if (isBmpCodePoint(i)) {
            cArr[i2] = (char) i;
            return 1;
        } else if (isValidCodePoint(i)) {
            toSurrogates(i, cArr, i2);
            return 2;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static char[] toChars(int i) {
        if (isBmpCodePoint(i)) {
            return new char[]{(char) i};
        } else if (isValidCodePoint(i)) {
            char[] cArr = new char[2];
            toSurrogates(i, cArr, 0);
            return cArr;
        } else {
            throw new IllegalArgumentException();
        }
    }

    static void toSurrogates(int i, char[] cArr, int i2) {
        cArr[i2 + 1] = lowSurrogate(i);
        cArr[i2] = highSurrogate(i);
    }

    public static int codePointCount(CharSequence charSequence, int i, int i2) {
        int length = charSequence.length();
        if (i < 0 || i2 > length || i > i2) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        while (i < i2) {
            int i4 = i + 1;
            if (!isHighSurrogate(charSequence.charAt(i)) || i4 >= i2 || !isLowSurrogate(charSequence.charAt(i4))) {
                i = i4;
            } else {
                i3--;
                i = i4 + 1;
            }
        }
        return i3;
    }

    public static int codePointCount(char[] cArr, int i, int i2) {
        if (i2 <= cArr.length - i && i >= 0 && i2 >= 0) {
            return codePointCountImpl(cArr, i, i2);
        }
        throw new IndexOutOfBoundsException();
    }

    static int codePointCountImpl(char[] cArr, int i, int i2) {
        int i3 = i + i2;
        while (i < i3) {
            int i4 = i + 1;
            if (!isHighSurrogate(cArr[i]) || i4 >= i3 || !isLowSurrogate(cArr[i4])) {
                i = i4;
            } else {
                i2--;
                i = i4 + 1;
            }
        }
        return i2;
    }

    public static int offsetByCodePoints(CharSequence charSequence, int i, int i2) {
        int length = charSequence.length();
        if (i < 0 || i > length) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 >= 0) {
            int i3 = 0;
            while (i < length && i3 < i2) {
                int i4 = i + 1;
                if (isHighSurrogate(charSequence.charAt(i)) && i4 < length && isLowSurrogate(charSequence.charAt(i4))) {
                    i4++;
                }
                i = i4;
                i3++;
            }
            if (i3 < i2) {
                throw new IndexOutOfBoundsException();
            }
        } else {
            while (i > 0 && i2 < 0) {
                i--;
                if (isLowSurrogate(charSequence.charAt(i)) && i > 0 && isHighSurrogate(charSequence.charAt(i - 1))) {
                    i--;
                }
                i2++;
            }
            if (i2 < 0) {
                throw new IndexOutOfBoundsException();
            }
        }
        return i;
    }

    static int offsetByCodePointsImpl(char[] cArr, int i, int i2, int i3, int i4) {
        if (i4 >= 0) {
            int i5 = i + i2;
            int i6 = 0;
            while (i3 < i5 && i6 < i4) {
                int i7 = i3 + 1;
                if (isHighSurrogate(cArr[i3]) && i7 < i5 && isLowSurrogate(cArr[i7])) {
                    i7++;
                }
                i3 = i7;
                i6++;
            }
            if (i6 < i4) {
                throw new IndexOutOfBoundsException();
            }
        } else {
            while (i3 > i && i4 < 0) {
                i3--;
                if (isLowSurrogate(cArr[i3]) && i3 > i && isHighSurrogate(cArr[i3 - 1])) {
                    i3--;
                }
                i4++;
            }
            if (i4 < 0) {
                throw new IndexOutOfBoundsException();
            }
        }
        return i3;
    }

    public static boolean isLowerCase(char c) {
        return isLowerCase((int) c);
    }

    public static boolean isLowerCase(int i) {
        return isLowerCaseImpl(i);
    }

    public static boolean isUpperCase(char c) {
        return isUpperCase((int) c);
    }

    public static boolean isUpperCase(int i) {
        return isUpperCaseImpl(i);
    }

    public static boolean isTitleCase(char c) {
        return isTitleCase((int) c);
    }

    public static boolean isTitleCase(int i) {
        return isTitleCaseImpl(i);
    }

    public static boolean isDigit(char c) {
        return isDigit((int) c);
    }

    public static boolean isDigit(int i) {
        return isDigitImpl(i);
    }

    public static boolean isLetter(char c) {
        return isLetter((int) c);
    }

    public static boolean isLetter(int i) {
        return isLetterImpl(i);
    }

    public static boolean isLetterOrDigit(char c) {
        return isLetterOrDigit((int) c);
    }

    public static boolean isLetterOrDigit(int i) {
        return isLetterOrDigitImpl(i);
    }

    public static boolean isJavaIdentifierStart(int i) {
        return i < 64 ? i == 36 : i < 128 ? (576460745995190270L & (1 << (i - 64))) != 0 : ((1 << getType(i)) & 75498558) != 0;
    }

    public static boolean isJavaIdentifierPart(int i) {
        return i < 64 ? ((1 << i) & 287948970162897407L) != 0 : i < 128 ? ((1 << (i - 64)) & -8646911290859585538L) != 0 : ((1 << getType(i)) & 75564926) != 0 || (i >= 0 && i <= 8) || ((i >= 14 && i <= 27) || (i >= 127 && i <= 159));
    }

    public static char toLowerCase(char c) {
        return (char) toLowerCase((int) c);
    }

    public static int toLowerCase(int i) {
        if (i < 65 || i > 90) {
            return i < 128 ? i : toLowerCaseImpl(i);
        }
        return i + 32;
    }

    public static char toUpperCase(char c) {
        return (char) toUpperCase((int) c);
    }

    public static int toUpperCase(int i) {
        if (i < 97 || i > 122) {
            return i < 128 ? i : toUpperCaseImpl(i);
        }
        return i - 32;
    }

    public static int digit(char c, int i) {
        return digit((int) c, i);
    }

    public static int digit(int i, int i2) {
        int i3;
        if (i2 < 2 || i2 > 36) {
            return -1;
        }
        if (i >= 128) {
            return digitImpl(i, i2);
        }
        if (48 > i || i > 57) {
            int i4 = 97;
            if (97 > i || i > 122) {
                i4 = 65;
                if (65 > i || i > 90) {
                    i3 = -1;
                }
            }
            i3 = (i - i4) + 10;
        } else {
            i3 = i - 48;
        }
        if (i3 < i2) {
            return i3;
        }
        return -1;
    }

    public static boolean isSpaceChar(char c) {
        return isSpaceChar((int) c);
    }

    public static boolean isSpaceChar(int i) {
        if (!(i == 32 || i == 160)) {
            if (i < 4096) {
                return false;
            }
            if (!(i == 5760 || i == 6158)) {
                if (i < 8192) {
                    return false;
                }
                if (i <= 65535) {
                    return i <= 8202 || i == 8232 || i == 8233 || i == 8239 || i == 8287 || i == 12288;
                }
                return isSpaceCharImpl(i);
            }
        }
        return true;
    }

    public static boolean isWhitespace(char c) {
        return isWhitespace((int) c);
    }

    public static boolean isWhitespace(int i) {
        if ((i >= 28 && i <= 32) || (i >= 9 && i <= 13)) {
            return true;
        }
        if (i < 4096) {
            return false;
        }
        if (i == 5760 || i == 6158) {
            return true;
        }
        if (i < 8192 || i == 8199 || i == 8239) {
            return false;
        }
        if (i <= 65535) {
            return i <= 8202 || i == 8232 || i == 8233 || i == 8287 || i == 12288;
        }
        return isWhitespaceImpl(i);
    }

    public static boolean isISOControl(char c) {
        return isISOControl((int) c);
    }

    public static int getType(int i) {
        int typeImpl = getTypeImpl(i);
        return typeImpl <= 16 ? typeImpl : typeImpl + 1;
    }

    public int compareTo(Character ch) {
        return compare(this.value, ch.value);
    }
}
