package android.icu.impl;

import android.icu.lang.UCharacter;
import android.icu.text.Replaceable;
import android.icu.text.UTF16;
import android.icu.text.UnicodeMatcher;
import java.io.IOException;
import java.util.Locale;

public final class Utility {
    static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    static final char[] HEX_DIGIT = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final char[] UNESCAPE_MAP = {'a', 7, 'b', '\b', 'e', 27, 'f', '\f', 'n', '\n', 'r', '\r', 't', '\t', 'v', 11};

    public static boolean isUnprintable(int i) {
        return i < 32 || i > 126;
    }

    public static final boolean sameObjects(Object obj, Object obj2) {
        return obj == obj2;
    }

    public static final boolean arrayEquals(Object[] objArr, Object obj) {
        if (objArr == null) {
            return obj == null;
        }
        if (!(obj instanceof Object[])) {
            return false;
        }
        Object[] objArr2 = (Object[]) obj;
        return objArr.length == objArr2.length && arrayRegionMatches(objArr, 0, objArr2, 0, objArr.length);
    }

    public static final boolean arrayEquals(int[] iArr, Object obj) {
        if (iArr == null) {
            return obj == null;
        }
        if (!(obj instanceof int[])) {
            return false;
        }
        int[] iArr2 = (int[]) obj;
        return iArr.length == iArr2.length && arrayRegionMatches(iArr, 0, iArr2, 0, iArr.length);
    }

    public static final boolean arrayEquals(double[] dArr, Object obj) {
        if (dArr == null) {
            return obj == null;
        }
        if (!(obj instanceof double[])) {
            return false;
        }
        double[] dArr2 = (double[]) obj;
        return dArr.length == dArr2.length && arrayRegionMatches(dArr, 0, dArr2, 0, dArr.length);
    }

    public static final boolean arrayEquals(byte[] bArr, Object obj) {
        if (bArr == null) {
            return obj == null;
        }
        if (!(obj instanceof byte[])) {
            return false;
        }
        byte[] bArr2 = (byte[]) obj;
        return bArr.length == bArr2.length && arrayRegionMatches(bArr, 0, bArr2, 0, bArr.length);
    }

    public static final boolean arrayEquals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        if (obj instanceof Object[]) {
            return arrayEquals((Object[]) obj, obj2);
        }
        if (obj instanceof int[]) {
            return arrayEquals((int[]) obj, obj2);
        }
        if (obj instanceof double[]) {
            return arrayEquals((double[]) obj, obj2);
        }
        if (obj instanceof byte[]) {
            return arrayEquals((byte[]) obj, obj2);
        }
        return obj.equals(obj2);
    }

    public static final boolean arrayRegionMatches(Object[] objArr, int i, Object[] objArr2, int i2, int i3) {
        int i4 = i3 + i;
        int i5 = i2 - i;
        while (i < i4) {
            if (!arrayEquals(objArr[i], objArr2[i + i5])) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static final boolean arrayRegionMatches(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        int i4 = i3 + i;
        int i5 = i2 - i;
        while (i < i4) {
            if (iArr[i] != iArr2[i + i5]) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static final boolean arrayRegionMatches(double[] dArr, int i, double[] dArr2, int i2, int i3) {
        int i4 = i3 + i;
        int i5 = i2 - i;
        while (i < i4) {
            if (dArr[i] != dArr2[i + i5]) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static final boolean arrayRegionMatches(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4 = i3 + i;
        int i5 = i2 - i;
        while (i < i4) {
            if (bArr[i] != bArr2[i + i5]) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static final String escape(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < str.length()) {
            int codePointAt = Character.codePointAt(str, i);
            i += UTF16.getCharCount(codePointAt);
            if (codePointAt < 32 || codePointAt > 127) {
                boolean z = codePointAt <= 65535;
                sb.append(z ? "\\u" : "\\U");
                sb.append(hex((long) codePointAt, z ? 4 : 8));
            } else if (codePointAt == 92) {
                sb.append("\\\\");
            } else {
                sb.append((char) codePointAt);
            }
        }
        return sb.toString();
    }

    public static int unescapeAt(String str, int[] iArr) {
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = iArr[0];
        int length = str.length();
        if (i6 < 0 || i6 >= length) {
            return -1;
        }
        int codePointAt = Character.codePointAt(str, i6);
        int charCount = i6 + UTF16.getCharCount(codePointAt);
        int i7 = 4;
        if (codePointAt == 85) {
            i4 = 0;
            i3 = 0;
            z = false;
            i = 4;
            i7 = 8;
            i2 = 8;
        } else if (codePointAt == 117) {
            i4 = 0;
            i3 = 0;
            z = false;
            i2 = 4;
            i = 4;
        } else if (codePointAt != 120) {
            i4 = UCharacter.digit(codePointAt, 8);
            if (i4 >= 0) {
                z = false;
                i2 = 3;
                i = 3;
                i7 = 1;
                i3 = 1;
            } else {
                i4 = 0;
                i3 = 0;
                i2 = 0;
                z = false;
                i = 4;
                i7 = 0;
            }
        } else if (charCount >= length || UTF16.charAt(str, charCount) != 123) {
            i3 = 0;
            z = false;
            i2 = 2;
            i = 4;
            i7 = 1;
            i4 = 0;
        } else {
            charCount++;
            i4 = 0;
            i3 = 0;
            i = 4;
            i2 = 8;
            i7 = 1;
            z = true;
        }
        if (i7 != 0) {
            while (charCount < length && i3 < i2) {
                codePointAt = UTF16.charAt(str, charCount);
                int digit = UCharacter.digit(codePointAt, i == 3 ? 8 : 16);
                if (digit < 0) {
                    break;
                }
                i4 = (i4 << i) | digit;
                charCount += UTF16.getCharCount(codePointAt);
                i3++;
            }
            if (i3 < i7) {
                return -1;
            }
            if (z) {
                if (codePointAt != 125) {
                    return -1;
                }
                charCount++;
            }
            if (i4 < 0 || i4 >= 1114112) {
                return -1;
            }
            if (charCount < length) {
                char c = (char) i4;
                if (UTF16.isLeadSurrogate(c)) {
                    int i8 = charCount + 1;
                    int charAt = str.charAt(charCount);
                    if (charAt != 92 || i8 >= length) {
                        i5 = i8;
                    } else {
                        int[] iArr2 = {i8};
                        charAt = unescapeAt(str, iArr2);
                        i5 = iArr2[0];
                    }
                    char c2 = (char) charAt;
                    if (UTF16.isTrailSurrogate(c2)) {
                        i4 = Character.toCodePoint(c, c2);
                        iArr[0] = i5;
                        return i4;
                    }
                }
            }
            i5 = charCount;
            iArr[0] = i5;
            return i4;
        }
        int i9 = 0;
        while (true) {
            char[] cArr = UNESCAPE_MAP;
            if (i9 >= cArr.length) {
                break;
            } else if (codePointAt == cArr[i9]) {
                iArr[0] = charCount;
                return cArr[i9 + 1];
            } else if (codePointAt < cArr[i9]) {
                break;
            } else {
                i9 += 2;
            }
        }
        if (codePointAt != 99 || charCount >= length) {
            iArr[0] = charCount;
            return codePointAt;
        }
        int charAt2 = UTF16.charAt(str, charCount);
        iArr[0] = charCount + UTF16.getCharCount(charAt2);
        return charAt2 & 31;
    }

    public static String hex(long j, int i) {
        if (j == Long.MIN_VALUE) {
            return "-8000000000000000";
        }
        boolean z = j < 0;
        if (z) {
            j = -j;
        }
        String upperCase = Long.toString(j, 16).toUpperCase(Locale.ENGLISH);
        if (upperCase.length() < i) {
            upperCase = "0000000000000000".substring(upperCase.length(), i) + upperCase;
        }
        if (!z) {
            return upperCase;
        }
        return '-' + upperCase;
    }

    public static boolean parseChar(String str, int[] iArr, char c) {
        int i = iArr[0];
        iArr[0] = PatternProps.skipWhiteSpace(str, iArr[0]);
        if (iArr[0] == str.length() || str.charAt(iArr[0]) != c) {
            iArr[0] = i;
            return false;
        }
        iArr[0] = iArr[0] + 1;
        return true;
    }

    public static int parsePattern(String str, int i, int i2, String str2, int[] iArr) {
        int[] iArr2 = new int[1];
        int i3 = i;
        int i4 = 0;
        for (int i5 = 0; i5 < str2.length(); i5++) {
            char charAt = str2.charAt(i5);
            if (charAt != ' ') {
                if (charAt == '#') {
                    iArr2[0] = i3;
                    int i6 = i4 + 1;
                    iArr[i4] = parseInteger(str, iArr2, i2);
                    if (iArr2[0] == i3) {
                        return -1;
                    }
                    i3 = iArr2[0];
                    i4 = i6;
                } else if (charAt != '~') {
                    if (i3 >= i2) {
                        return -1;
                    }
                    int i7 = i3 + 1;
                    if (((char) UCharacter.toLowerCase(str.charAt(i3))) != charAt) {
                        return -1;
                    }
                    i3 = i7;
                }
            } else if (i3 >= i2) {
                return -1;
            } else {
                int i8 = i3 + 1;
                if (!PatternProps.isWhiteSpace(str.charAt(i3))) {
                    return -1;
                }
                i3 = i8;
            }
            i3 = PatternProps.skipWhiteSpace(str, i3);
        }
        return i3;
    }

    public static int parsePattern(String str, Replaceable replaceable, int i, int i2) {
        if (str.length() == 0) {
            return i;
        }
        int i3 = 0;
        int codePointAt = Character.codePointAt(str, 0);
        while (i < i2) {
            int char32At = replaceable.char32At(i);
            if (codePointAt != 126) {
                if (char32At != codePointAt) {
                    break;
                }
                int charCount = UTF16.getCharCount(char32At);
                i += charCount;
                i3 += charCount;
                if (i3 == str.length()) {
                    return i;
                }
            } else if (PatternProps.isWhiteSpace(char32At)) {
                i += UTF16.getCharCount(char32At);
            } else {
                i3++;
                if (i3 == str.length()) {
                    return i;
                }
            }
            codePointAt = UTF16.charAt(str, i3);
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046 A[EDGE_INSN: B:22:0x0046->B:17:0x0046 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int parseInteger(java.lang.String r8, int[] r9, int r10) {
        /*
            r0 = 0
            r7 = r9[r0]
            r2 = 1
            java.lang.String r4 = "0x"
            r5 = 0
            r6 = 2
            r1 = r8
            r3 = r7
            boolean r1 = r1.regionMatches(r2, r3, r4, r5, r6)
            if (r1 == 0) goto L_0x0015
            int r7 = r7 + 2
            r1 = 16
            goto L_0x0028
        L_0x0015:
            if (r7 >= r10) goto L_0x0026
            char r1 = r8.charAt(r7)
            r2 = 48
            if (r1 != r2) goto L_0x0026
            int r7 = r7 + 1
            r1 = 8
            r2 = 1
            r3 = r0
            goto L_0x002a
        L_0x0026:
            r1 = 10
        L_0x0028:
            r2 = r0
            r3 = r2
        L_0x002a:
            if (r7 >= r10) goto L_0x0046
            int r4 = r7 + 1
            char r5 = r8.charAt(r7)
            int r5 = android.icu.lang.UCharacter.digit(r5, r1)
            if (r5 >= 0) goto L_0x003b
            int r7 = r4 + -1
            goto L_0x0046
        L_0x003b:
            int r2 = r2 + 1
            int r6 = r3 * r1
            int r5 = r5 + r6
            if (r5 > r3) goto L_0x0043
            return r0
        L_0x0043:
            r7 = r4
            r3 = r5
            goto L_0x002a
        L_0x0046:
            if (r2 <= 0) goto L_0x004a
            r9[r0] = r7
        L_0x004a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.Utility.parseInteger(java.lang.String, int[], int):int");
    }

    public static String parseUnicodeIdentifier(String str, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        int i = iArr[0];
        while (i < str.length()) {
            int codePointAt = Character.codePointAt(str, i);
            if (sb.length() != 0) {
                if (!UCharacter.isUnicodeIdentifierPart(codePointAt)) {
                    break;
                }
                sb.appendCodePoint(codePointAt);
            } else if (!UCharacter.isUnicodeIdentifierStart(codePointAt)) {
                return null;
            } else {
                sb.appendCodePoint(codePointAt);
            }
            i += UTF16.getCharCount(codePointAt);
        }
        iArr[0] = i;
        return sb.toString();
    }

    private static void recursiveAppendNumber(Appendable appendable, int i, int i2, int i3) {
        try {
            int i4 = i % i2;
            if (i >= i2 || i3 > 1) {
                recursiveAppendNumber(appendable, i / i2, i2, i3 - 1);
            }
            appendable.append(DIGITS[i4]);
        } catch (IOException e) {
            throw new IllegalIcuArgumentException(e);
        }
    }

    public static Appendable appendNumber(Appendable appendable, int i, int i2, int i3) {
        if (i2 < 2 || i2 > 36) {
            throw new IllegalArgumentException("Illegal radix " + i2);
        }
        if (i < 0) {
            i = -i;
            try {
                appendable.append("-");
            } catch (IOException e) {
                throw new IllegalIcuArgumentException(e);
            }
        }
        recursiveAppendNumber(appendable, i, i2, i3);
        return appendable;
    }

    public static int parseNumber(String str, int[] iArr, int i) {
        int digit;
        int i2 = iArr[0];
        int i3 = 0;
        while (i2 < str.length() && (digit = UCharacter.digit(Character.codePointAt(str, i2), i)) >= 0) {
            i3 = (i3 * i) + digit;
            if (i3 < 0) {
                return -1;
            }
            i2++;
        }
        if (i2 == iArr[0]) {
            return -1;
        }
        iArr[0] = i2;
        return i3;
    }

    public static boolean escapeUnprintable(Appendable appendable, int i) {
        try {
            if (!isUnprintable(i)) {
                return false;
            }
            appendable.append('\\');
            if ((-65536 & i) != 0) {
                appendable.append('U');
                appendable.append(DIGITS[(i >> 28) & 15]);
                appendable.append(DIGITS[(i >> 24) & 15]);
                appendable.append(DIGITS[(i >> 20) & 15]);
                appendable.append(DIGITS[(i >> 16) & 15]);
            } else {
                appendable.append('u');
            }
            appendable.append(DIGITS[(i >> 12) & 15]);
            appendable.append(DIGITS[(i >> 8) & 15]);
            appendable.append(DIGITS[(i >> 4) & 15]);
            appendable.append(DIGITS[i & 15]);
            return true;
        } catch (IOException e) {
            throw new IllegalIcuArgumentException(e);
        }
    }

    public static int quotedIndexOf(String str, int i, int i2, String str2) {
        while (i < i2) {
            char charAt = str.charAt(i);
            if (charAt == '\\') {
                i++;
            } else if (charAt == '\'') {
                do {
                    i++;
                    if (i >= i2) {
                        break;
                    }
                } while (str.charAt(i) != '\'');
            } else if (str2.indexOf(charAt) >= 0) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void appendToRule(StringBuffer stringBuffer, int i, boolean z, boolean z2, StringBuffer stringBuffer2) {
        if (z || (z2 && isUnprintable(i))) {
            if (stringBuffer2.length() > 0) {
                while (stringBuffer2.length() >= 2 && stringBuffer2.charAt(0) == '\'' && stringBuffer2.charAt(1) == '\'') {
                    stringBuffer.append('\\');
                    stringBuffer.append('\'');
                    stringBuffer2.delete(0, 2);
                }
                int i2 = 0;
                while (stringBuffer2.length() >= 2 && stringBuffer2.charAt(stringBuffer2.length() - 2) == '\'' && stringBuffer2.charAt(stringBuffer2.length() - 1) == '\'') {
                    stringBuffer2.setLength(stringBuffer2.length() - 2);
                    i2++;
                }
                if (stringBuffer2.length() > 0) {
                    stringBuffer.append('\'');
                    stringBuffer.append(stringBuffer2);
                    stringBuffer.append('\'');
                    stringBuffer2.setLength(0);
                }
                while (true) {
                    int i3 = i2 - 1;
                    if (i2 <= 0) {
                        break;
                    }
                    stringBuffer.append('\\');
                    stringBuffer.append('\'');
                    i2 = i3;
                }
            }
            if (i == -1) {
                return;
            }
            if (i == 32) {
                int length = stringBuffer.length();
                if (length > 0 && stringBuffer.charAt(length - 1) != ' ') {
                    stringBuffer.append(' ');
                }
            } else if (!z2 || !escapeUnprintable(stringBuffer, i)) {
                stringBuffer.appendCodePoint(i);
            }
        } else if (stringBuffer2.length() == 0 && (i == 39 || i == 92)) {
            stringBuffer.append('\\');
            stringBuffer.append((char) i);
        } else if (stringBuffer2.length() > 0 || ((i >= 33 && i <= 126 && ((i < 48 || i > 57) && ((i < 65 || i > 90) && (i < 97 || i > 122)))) || PatternProps.isWhiteSpace(i))) {
            stringBuffer2.appendCodePoint(i);
            if (i == 39) {
                stringBuffer2.append((char) i);
            }
        } else {
            stringBuffer.appendCodePoint(i);
        }
    }

    public static void appendToRule(StringBuffer stringBuffer, String str, boolean z, boolean z2, StringBuffer stringBuffer2) {
        for (int i = 0; i < str.length(); i++) {
            appendToRule(stringBuffer, str.charAt(i), z, z2, stringBuffer2);
        }
    }

    public static void appendToRule(StringBuffer stringBuffer, UnicodeMatcher unicodeMatcher, boolean z, StringBuffer stringBuffer2) {
        if (unicodeMatcher != null) {
            appendToRule(stringBuffer, unicodeMatcher.toPattern(z), true, z, stringBuffer2);
        }
    }

    public static int addExact(int i, int i2) {
        int i3 = i + i2;
        if (((i ^ i3) & (i2 ^ i3)) >= 0) {
            return i3;
        }
        throw new ArithmeticException("integer overflow");
    }

    public static boolean charSequenceEquals(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == charSequence2) {
            return true;
        }
        if (charSequence == null || charSequence2 == null || charSequence.length() != charSequence2.length()) {
            return false;
        }
        for (int i = 0; i < charSequence.length(); i++) {
            if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static int charSequenceHashCode(CharSequence charSequence) {
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            i = (i * 31) + charSequence.charAt(i2);
        }
        return i;
    }
}
