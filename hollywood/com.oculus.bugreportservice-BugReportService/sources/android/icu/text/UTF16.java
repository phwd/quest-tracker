package android.icu.text;

public final class UTF16 {
    public static int getCharCount(int i) {
        return i < 65536 ? 1 : 2;
    }

    public static char getLeadSurrogate(int i) {
        if (i >= 65536) {
            return (char) ((i >> 10) + 55232);
        }
        return 0;
    }

    public static char getTrailSurrogate(int i) {
        return i >= 65536 ? (char) ((i & 1023) + 56320) : (char) i;
    }

    public static boolean isLeadSurrogate(char c) {
        return (c & 64512) == 55296;
    }

    public static boolean isSurrogate(char c) {
        return (c & 63488) == 55296;
    }

    public static boolean isTrailSurrogate(char c) {
        return (c & 64512) == 56320;
    }

    public static int charAt(String str, int i) {
        char charAt = str.charAt(i);
        if (charAt < 55296) {
            return charAt;
        }
        return _charAt(str, i, charAt);
    }

    private static int _charAt(String str, int i, char c) {
        char charAt;
        char charAt2;
        if (c > 57343) {
            return c;
        }
        if (c <= 56319) {
            int i2 = i + 1;
            if (str.length() != i2 && (charAt2 = str.charAt(i2)) >= 56320 && charAt2 <= 57343) {
                return Character.toCodePoint(c, charAt2);
            }
        } else {
            int i3 = i - 1;
            if (i3 >= 0 && (charAt = str.charAt(i3)) >= 55296 && charAt <= 56319) {
                return Character.toCodePoint(charAt, c);
            }
        }
        return c;
    }

    public static int charAt(CharSequence charSequence, int i) {
        char charAt = charSequence.charAt(i);
        if (charAt < 55296) {
            return charAt;
        }
        return _charAt(charSequence, i, charAt);
    }

    private static int _charAt(CharSequence charSequence, int i, char c) {
        char charAt;
        char charAt2;
        if (c > 57343) {
            return c;
        }
        if (c <= 56319) {
            int i2 = i + 1;
            if (charSequence.length() != i2 && (charAt2 = charSequence.charAt(i2)) >= 56320 && charAt2 <= 57343) {
                return Character.toCodePoint(c, charAt2);
            }
        } else {
            int i3 = i - 1;
            if (i3 >= 0 && (charAt = charSequence.charAt(i3)) >= 55296 && charAt <= 56319) {
                return Character.toCodePoint(charAt, c);
            }
        }
        return c;
    }

    public static int charAt(StringBuffer stringBuffer, int i) {
        if (i < 0 || i >= stringBuffer.length()) {
            throw new StringIndexOutOfBoundsException(i);
        }
        char charAt = stringBuffer.charAt(i);
        if (!isSurrogate(charAt)) {
            return charAt;
        }
        if (charAt <= 56319) {
            int i2 = i + 1;
            if (stringBuffer.length() != i2) {
                char charAt2 = stringBuffer.charAt(i2);
                if (isTrailSurrogate(charAt2)) {
                    return Character.toCodePoint(charAt, charAt2);
                }
            }
        } else {
            int i3 = i - 1;
            if (i3 >= 0) {
                char charAt3 = stringBuffer.charAt(i3);
                if (isLeadSurrogate(charAt3)) {
                    return Character.toCodePoint(charAt3, charAt);
                }
            }
        }
        return charAt;
    }

    public static int charAt(char[] cArr, int i, int i2, int i3) {
        int i4 = i3 + i;
        if (i4 < i || i4 >= i2) {
            throw new ArrayIndexOutOfBoundsException(i4);
        }
        char c = cArr[i4];
        if (!isSurrogate(c)) {
            return c;
        }
        if (c <= 56319) {
            int i5 = i4 + 1;
            if (i5 >= i2) {
                return c;
            }
            char c2 = cArr[i5];
            if (isTrailSurrogate(c2)) {
                return Character.toCodePoint(c, c2);
            }
        } else if (i4 == i) {
            return c;
        } else {
            char c3 = cArr[i4 - 1];
            if (isLeadSurrogate(c3)) {
                return Character.toCodePoint(c3, c);
            }
        }
        return c;
    }

    public static int charAt(Replaceable replaceable, int i) {
        if (i < 0 || i >= replaceable.length()) {
            throw new StringIndexOutOfBoundsException(i);
        }
        char charAt = replaceable.charAt(i);
        if (!isSurrogate(charAt)) {
            return charAt;
        }
        if (charAt <= 56319) {
            int i2 = i + 1;
            if (replaceable.length() != i2) {
                char charAt2 = replaceable.charAt(i2);
                if (isTrailSurrogate(charAt2)) {
                    return Character.toCodePoint(charAt, charAt2);
                }
            }
        } else {
            int i3 = i - 1;
            if (i3 >= 0) {
                char charAt3 = replaceable.charAt(i3);
                if (isLeadSurrogate(charAt3)) {
                    return Character.toCodePoint(charAt3, charAt);
                }
            }
        }
        return charAt;
    }

    public static String valueOf(int i) {
        if (i >= 0 && i <= 1114111) {
            return toString(i);
        }
        throw new IllegalArgumentException("Illegal codepoint");
    }

    public static StringBuffer append(StringBuffer stringBuffer, int i) {
        if (i < 0 || i > 1114111) {
            new StringBuilder().append("Illegal codepoint: ");
            Integer.toHexString(i);
            throw null;
        }
        if (i >= 65536) {
            stringBuffer.append(getLeadSurrogate(i));
            stringBuffer.append(getTrailSurrogate(i));
        } else {
            stringBuffer.append((char) i);
        }
        return stringBuffer;
    }

    private static String toString(int i) {
        if (i < 65536) {
            return String.valueOf((char) i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getLeadSurrogate(i));
        sb.append(getTrailSurrogate(i));
        return sb.toString();
    }
}
