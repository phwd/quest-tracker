package java.lang;

import java.io.ObjectStreamField;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import libcore.util.CharsetUtils;

public final class String implements Serializable, Comparable, CharSequence {
    public static final Comparator CASE_INSENSITIVE_ORDER = new CaseInsensitiveComparator();
    private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[0];
    private static final long serialVersionUID = -6849794470754667710L;
    private final int count;
    private int hash;

    private native String doReplace(char c, char c2);

    private native String fastSubstring(int i, int i2);

    public static String valueOf(boolean z) {
        return z ? "true" : "false";
    }

    @Override // java.lang.CharSequence
    public native char charAt(int i);

    public native int compareTo(String str);

    public native String concat(String str);

    /* access modifiers changed from: package-private */
    public native void getCharsNoCheck(int i, int i2, char[] cArr, int i3);

    public native String intern();

    public native char[] toCharArray();

    @Override // java.lang.CharSequence
    public String toString() {
        return this;
    }

    public String() {
        throw new UnsupportedOperationException("Use StringFactory instead.");
    }

    public String(String str) {
        throw new UnsupportedOperationException("Use StringFactory instead.");
    }

    public String(char[] cArr) {
        throw new UnsupportedOperationException("Use StringFactory instead.");
    }

    public String(char[] cArr, int i, int i2) {
        throw new UnsupportedOperationException("Use StringFactory instead.");
    }

    public String(byte[] bArr, int i, int i2, int i3) {
        throw new UnsupportedOperationException("Use StringFactory instead.");
    }

    public String(byte[] bArr, int i, int i2, String str) {
        throw new UnsupportedOperationException("Use StringFactory instead.");
    }

    public String(byte[] bArr, int i, int i2, Charset charset) {
        throw new UnsupportedOperationException("Use StringFactory instead.");
    }

    public String(byte[] bArr, String str) {
        throw new UnsupportedOperationException("Use StringFactory instead.");
    }

    public String(byte[] bArr, Charset charset) {
        throw new UnsupportedOperationException("Use StringFactory instead.");
    }

    public String(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException("Use StringFactory instead.");
    }

    public String(byte[] bArr) {
        throw new UnsupportedOperationException("Use StringFactory instead.");
    }

    String(int i, int i2, char[] cArr) {
        throw new UnsupportedOperationException("Use StringFactory instead.");
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.count >>> 1;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public int codePointAt(int i) {
        if (i >= 0 && i < length()) {
            return Character.codePointAt(this, i);
        }
        throw new StringIndexOutOfBoundsException(i);
    }

    public int codePointBefore(int i) {
        int i2 = i - 1;
        if (i2 >= 0 && i2 < length()) {
            return Character.codePointBefore(this, i);
        }
        throw new StringIndexOutOfBoundsException(i);
    }

    public int codePointCount(int i, int i2) {
        if (i >= 0 && i2 <= length() && i <= i2) {
            return Character.codePointCount(this, i, i2);
        }
        throw new IndexOutOfBoundsException();
    }

    public int offsetByCodePoints(int i, int i2) {
        if (i >= 0 && i <= length()) {
            return Character.offsetByCodePoints(this, i, i2);
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public void getChars(char[] cArr, int i) {
        getCharsNoCheck(0, length(), cArr, i);
    }

    public void getChars(int i, int i2, char[] cArr, int i3) {
        if (cArr == null) {
            throw new NullPointerException("dst == null");
        } else if (i < 0) {
            throw new StringIndexOutOfBoundsException(this, i);
        } else if (i2 <= length()) {
            int i4 = i2 - i;
            if (i2 < i) {
                throw new StringIndexOutOfBoundsException(this, i, i4);
            } else if (i3 < 0) {
                throw new ArrayIndexOutOfBoundsException("dstBegin < 0. dstBegin=" + i3);
            } else if (i3 > cArr.length) {
                throw new ArrayIndexOutOfBoundsException("dstBegin > dst.length. dstBegin=" + i3 + ", dst.length=" + cArr.length);
            } else if (i4 <= cArr.length - i3) {
                getCharsNoCheck(i, i2, cArr, i3);
            } else {
                throw new ArrayIndexOutOfBoundsException("n > dst.length - dstBegin. n=" + i4 + ", dst.length=" + cArr.length + "dstBegin=" + i3);
            }
        } else {
            throw new StringIndexOutOfBoundsException(this, i2);
        }
    }

    public byte[] getBytes(String str) {
        if (str != null) {
            return getBytes(Charset.forNameUEE(str));
        }
        throw new NullPointerException();
    }

    public byte[] getBytes(Charset charset) {
        if (charset != null) {
            int length = length();
            String name = charset.name();
            if ("UTF-8".equals(name)) {
                return CharsetUtils.toUtf8Bytes(this, 0, length);
            }
            if ("ISO-8859-1".equals(name)) {
                return CharsetUtils.toIsoLatin1Bytes(this, 0, length);
            }
            if ("US-ASCII".equals(name)) {
                return CharsetUtils.toAsciiBytes(this, 0, length);
            }
            if ("UTF-16BE".equals(name)) {
                return CharsetUtils.toBigEndianUtf16Bytes(this, 0, length);
            }
            ByteBuffer encode = charset.encode(this);
            byte[] bArr = new byte[encode.limit()];
            encode.get(bArr);
            return bArr;
        }
        throw new NullPointerException("charset == null");
    }

    public byte[] getBytes() {
        return getBytes(Charset.defaultCharset());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            int length = length();
            if (length == str.length()) {
                int i = 0;
                while (true) {
                    int i2 = length - 1;
                    if (length == 0) {
                        return true;
                    }
                    if (charAt(i) != str.charAt(i)) {
                        return false;
                    }
                    i++;
                    length = i2;
                }
            }
        }
        return false;
    }

    private boolean nonSyncContentEquals(AbstractStringBuilder abstractStringBuilder) {
        char[] value = abstractStringBuilder.getValue();
        int length = length();
        if (length != abstractStringBuilder.length()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (charAt(i) != value[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean contentEquals(CharSequence charSequence) {
        boolean nonSyncContentEquals;
        if (charSequence instanceof AbstractStringBuilder) {
            if (!(charSequence instanceof StringBuffer)) {
                return nonSyncContentEquals((AbstractStringBuilder) charSequence);
            }
            synchronized (charSequence) {
                nonSyncContentEquals = nonSyncContentEquals((AbstractStringBuilder) charSequence);
            }
            return nonSyncContentEquals;
        } else if (charSequence instanceof String) {
            return equals(charSequence);
        } else {
            int length = length();
            if (length != charSequence.length()) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                if (charAt(i) != charSequence.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean equalsIgnoreCase(String str) {
        int length = length();
        if (this == str) {
            return true;
        }
        if (str == null || str.length() != length || !regionMatches(true, 0, str, 0, length)) {
            return false;
        }
        return true;
    }

    private static class CaseInsensitiveComparator implements Comparator, Serializable {
        private static final long serialVersionUID = 8575799808933029326L;

        private CaseInsensitiveComparator() {
        }

        public int compare(String str, String str2) {
            char upperCase;
            char upperCase2;
            char lowerCase;
            char lowerCase2;
            int length = str.length();
            int length2 = str2.length();
            int min = Math.min(length, length2);
            for (int i = 0; i < min; i++) {
                char charAt = str.charAt(i);
                char charAt2 = str2.charAt(i);
                if (!(charAt == charAt2 || (upperCase = Character.toUpperCase(charAt)) == (upperCase2 = Character.toUpperCase(charAt2)) || (lowerCase = Character.toLowerCase(upperCase)) == (lowerCase2 = Character.toLowerCase(upperCase2)))) {
                    return lowerCase - lowerCase2;
                }
            }
            return length - length2;
        }

        private Object readResolve() {
            return String.CASE_INSENSITIVE_ORDER;
        }
    }

    public int compareToIgnoreCase(String str) {
        return CASE_INSENSITIVE_ORDER.compare(this, str);
    }

    public boolean regionMatches(int i, String str, int i2, int i3) {
        if (i2 >= 0 && i >= 0) {
            long j = (long) i3;
            if (((long) i) <= ((long) length()) - j && ((long) i2) <= ((long) str.length()) - j) {
                while (true) {
                    int i4 = i3 - 1;
                    if (i3 <= 0) {
                        return true;
                    }
                    int i5 = i + 1;
                    int i6 = i2 + 1;
                    if (charAt(i) != str.charAt(i2)) {
                        return false;
                    }
                    i = i5;
                    i3 = i4;
                    i2 = i6;
                }
            }
        }
        return false;
    }

    public boolean regionMatches(boolean z, int i, String str, int i2, int i3) {
        char upperCase;
        char upperCase2;
        if (i2 >= 0 && i >= 0) {
            long j = (long) i3;
            if (((long) i) <= ((long) length()) - j && ((long) i2) <= ((long) str.length()) - j) {
                while (true) {
                    int i4 = i3 - 1;
                    if (i3 <= 0) {
                        return true;
                    }
                    int i5 = i + 1;
                    char charAt = charAt(i);
                    int i6 = i2 + 1;
                    char charAt2 = str.charAt(i2);
                    if (charAt != charAt2 && (!z || ((upperCase = Character.toUpperCase(charAt)) != (upperCase2 = Character.toUpperCase(charAt2)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)))) {
                        return false;
                    }
                    i = i5;
                    i3 = i4;
                    i2 = i6;
                }
                return false;
            }
        }
        return false;
    }

    public boolean startsWith(String str, int i) {
        int length = str.length();
        if (i < 0 || i > length() - length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            length--;
            if (length < 0) {
                return true;
            }
            int i3 = i + 1;
            int i4 = i2 + 1;
            if (charAt(i) != str.charAt(i2)) {
                return false;
            }
            i = i3;
            i2 = i4;
        }
    }

    public boolean startsWith(String str) {
        return startsWith(str, 0);
    }

    public boolean endsWith(String str) {
        return startsWith(str, length() - str.length());
    }

    public int hashCode() {
        int i = this.hash;
        int length = length();
        if (i == 0 && length > 0) {
            for (int i2 = 0; i2 < length; i2++) {
                i = (i * 31) + charAt(i2);
            }
            this.hash = i;
        }
        return i;
    }

    public int indexOf(int i) {
        return indexOf(i, 0);
    }

    public int indexOf(int i, int i2) {
        int length = length();
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 >= length) {
            return -1;
        }
        if (i >= 65536) {
            return indexOfSupplementary(i, i2);
        }
        while (i2 < length) {
            if (charAt(i2) == i) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    private int indexOfSupplementary(int i, int i2) {
        if (!Character.isValidCodePoint(i)) {
            return -1;
        }
        char highSurrogate = Character.highSurrogate(i);
        char lowSurrogate = Character.lowSurrogate(i);
        int length = length() - 1;
        while (i2 < length) {
            if (charAt(i2) == highSurrogate && charAt(i2 + 1) == lowSurrogate) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public int lastIndexOf(int i) {
        return lastIndexOf(i, length() - 1);
    }

    public int lastIndexOf(int i, int i2) {
        if (i >= 65536) {
            return lastIndexOfSupplementary(i, i2);
        }
        for (int min = Math.min(i2, length() - 1); min >= 0; min--) {
            if (charAt(min) == i) {
                return min;
            }
        }
        return -1;
    }

    private int lastIndexOfSupplementary(int i, int i2) {
        if (!Character.isValidCodePoint(i)) {
            return -1;
        }
        char highSurrogate = Character.highSurrogate(i);
        char lowSurrogate = Character.lowSurrogate(i);
        for (int min = Math.min(i2, length() - 2); min >= 0; min--) {
            if (charAt(min) == highSurrogate && charAt(min + 1) == lowSurrogate) {
                return min;
            }
        }
        return -1;
    }

    public int indexOf(String str) {
        return indexOf(str, 0);
    }

    public int indexOf(String str, int i) {
        return indexOf(this, str, i);
    }

    private static int indexOf(String str, String str2, int i) {
        int length = str.length();
        int length2 = str2.length();
        if (i < length) {
            if (i < 0) {
                i = 0;
            }
            if (length2 == 0) {
                return i;
            }
            char charAt = str2.charAt(0);
            int i2 = length - length2;
            while (i <= i2) {
                int i3 = 1;
                if (str.charAt(i) != charAt) {
                    do {
                        i++;
                        if (i > i2) {
                            break;
                        }
                    } while (str.charAt(i) != charAt);
                }
                if (i <= i2) {
                    int i4 = i + 1;
                    int i5 = (i4 + length2) - 1;
                    while (i4 < i5 && str.charAt(i4) == str2.charAt(i3)) {
                        i4++;
                        i3++;
                    }
                    if (i4 == i5) {
                        return i;
                    }
                }
                i++;
            }
            return -1;
        } else if (length2 == 0) {
            return length;
        } else {
            return -1;
        }
    }

    static int indexOf(char[] cArr, int i, int i2, String str, int i3) {
        return indexOf(cArr, i, i2, str.toCharArray(), 0, str.length(), i3);
    }

    static int indexOf(char[] cArr, int i, int i2, char[] cArr2, int i3, int i4, int i5) {
        if (i5 < i2) {
            if (i5 < 0) {
                i5 = 0;
            }
            if (i4 == 0) {
                return i5;
            }
            char c = cArr2[i3];
            int i6 = (i2 - i4) + i;
            int i7 = i5 + i;
            while (i7 <= i6) {
                if (cArr[i7] != c) {
                    do {
                        i7++;
                        if (i7 > i6) {
                            break;
                        }
                    } while (cArr[i7] != c);
                }
                if (i7 <= i6) {
                    int i8 = i7 + 1;
                    int i9 = (i8 + i4) - 1;
                    int i10 = i3 + 1;
                    while (i8 < i9 && cArr[i8] == cArr2[i10]) {
                        i8++;
                        i10++;
                    }
                    if (i8 == i9) {
                        return i7 - i;
                    }
                }
                i7++;
            }
            return -1;
        } else if (i4 == 0) {
            return i2;
        } else {
            return -1;
        }
    }

    public int lastIndexOf(String str) {
        return lastIndexOf(str, length());
    }

    public int lastIndexOf(String str, int i) {
        return lastIndexOf(this, str, i);
    }

    private static int lastIndexOf(String str, String str2, int i) {
        int length = str.length();
        int length2 = str2.length();
        int i2 = length - length2;
        if (i < 0) {
            return -1;
        }
        if (i > i2) {
            i = i2;
        }
        if (length2 == 0) {
            return i;
        }
        int i3 = length2 - 1;
        char charAt = str2.charAt(i3);
        int i4 = i + i3;
        while (true) {
            if (i4 < i3 || str.charAt(i4) == charAt) {
                if (i4 < i3) {
                    return -1;
                }
                int i5 = i4 - 1;
                int i6 = i5 - i3;
                int i7 = i3 - 1;
                while (i5 > i6) {
                    int i8 = i5 - 1;
                    int i9 = i7 - 1;
                    if (str.charAt(i5) == str2.charAt(i7)) {
                        i5 = i8;
                        i7 = i9;
                    }
                }
                return i6 + 1;
            }
            i4--;
        }
    }

    static int lastIndexOf(char[] cArr, int i, int i2, String str, int i3) {
        return lastIndexOf(cArr, i, i2, str.toCharArray(), 0, str.length(), i3);
    }

    static int lastIndexOf(char[] cArr, int i, int i2, char[] cArr2, int i3, int i4, int i5) {
        int i6 = i2 - i4;
        if (i5 < 0) {
            return -1;
        }
        if (i5 <= i6) {
            i6 = i5;
        }
        if (i4 == 0) {
            return i6;
        }
        int i7 = (i3 + i4) - 1;
        char c = cArr2[i7];
        int i8 = (i + i4) - 1;
        int i9 = i6 + i8;
        while (true) {
            if (i9 < i8 || cArr[i9] == c) {
                if (i9 < i8) {
                    return -1;
                }
                int i10 = i9 - 1;
                int i11 = i10 - (i4 - 1);
                int i12 = i7 - 1;
                while (i10 > i11) {
                    int i13 = i10 - 1;
                    int i14 = i12 - 1;
                    if (cArr[i10] == cArr2[i12]) {
                        i10 = i13;
                        i12 = i14;
                    }
                }
                return (i11 - i) + 1;
            }
            i9--;
        }
    }

    public String substring(int i) {
        if (i >= 0) {
            int length = length() - i;
            if (length >= 0) {
                return i == 0 ? this : fastSubstring(i, length);
            }
            throw new StringIndexOutOfBoundsException(this, i);
        }
        throw new StringIndexOutOfBoundsException(this, i);
    }

    public String substring(int i, int i2) {
        if (i < 0) {
            throw new StringIndexOutOfBoundsException(this, i);
        } else if (i2 <= length()) {
            int i3 = i2 - i;
            if (i3 < 0) {
                throw new StringIndexOutOfBoundsException(i3);
            } else if (i == 0 && i2 == length()) {
                return this;
            } else {
                return fastSubstring(i, i3);
            }
        } else {
            throw new StringIndexOutOfBoundsException(this, i2);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return substring(i, i2);
    }

    public String replace(char c, char c2) {
        if (c != c2) {
            int length = length();
            for (int i = 0; i < length; i++) {
                if (charAt(i) == c) {
                    return doReplace(c, c2);
                }
            }
        }
        return this;
    }

    public boolean matches(String str) {
        return Pattern.matches(str, this);
    }

    public boolean contains(CharSequence charSequence) {
        return indexOf(charSequence.toString()) > -1;
    }

    public String replaceAll(String str, String str2) {
        return Pattern.compile(str).matcher(this).replaceAll(str2);
    }

    public String replace(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null) {
            throw new NullPointerException("target == null");
        } else if (charSequence2 != null) {
            String charSequence3 = charSequence2.toString();
            String charSequence4 = charSequence.toString();
            int length = length();
            int i = 0;
            if (charSequence4.isEmpty()) {
                StringBuilder sb = new StringBuilder((charSequence3.length() * (length + 2)) + length);
                sb.append(charSequence3);
                while (i < length) {
                    sb.append(charAt(i));
                    sb.append(charSequence3);
                    i++;
                }
                return sb.toString();
            }
            StringBuilder sb2 = null;
            while (true) {
                int indexOf = indexOf(this, charSequence4, i);
                if (indexOf == -1) {
                    break;
                }
                if (sb2 == null) {
                    sb2 = new StringBuilder(length);
                }
                sb2.append((CharSequence) this, i, indexOf);
                sb2.append(charSequence3);
                i = charSequence4.length() + indexOf;
            }
            if (sb2 == null) {
                return this;
            }
            sb2.append((CharSequence) this, i, length);
            return sb2.toString();
        } else {
            throw new NullPointerException("replacement == null");
        }
    }

    public String[] split(String str, int i) {
        String[] fastSplit = Pattern.fastSplit(str, this, i);
        if (fastSplit != null) {
            return fastSplit;
        }
        return Pattern.compile(str).split(this, i);
    }

    public String[] split(String str) {
        return split(str, 0);
    }

    public static String join(CharSequence charSequence, Iterable iterable) {
        Objects.requireNonNull(charSequence);
        Objects.requireNonNull(iterable);
        StringJoiner stringJoiner = new StringJoiner(charSequence);
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            stringJoiner.add((CharSequence) it.next());
        }
        return stringJoiner.toString();
    }

    public String toLowerCase(Locale locale) {
        return CaseMapper.toLowerCase(locale, this);
    }

    public String toLowerCase() {
        return toLowerCase(Locale.getDefault());
    }

    public String toUpperCase(Locale locale) {
        return CaseMapper.toUpperCase(locale, this, length());
    }

    public String toUpperCase() {
        return toUpperCase(Locale.getDefault());
    }

    public String trim() {
        int length = length();
        int i = 0;
        while (i < length && charAt(i) <= ' ') {
            i++;
        }
        while (i < length && charAt(length - 1) <= ' ') {
            length--;
        }
        return (i > 0 || length < length()) ? substring(i, length) : this;
    }

    public static String format(String str, Object... objArr) {
        Formatter formatter = new Formatter();
        formatter.format(str, objArr);
        return formatter.toString();
    }

    public static String format(Locale locale, String str, Object... objArr) {
        Formatter formatter = new Formatter(locale);
        formatter.format(str, objArr);
        return formatter.toString();
    }

    public static String valueOf(Object obj) {
        return obj == null ? "null" : obj.toString();
    }

    public static String valueOf(char[] cArr) {
        new String(cArr);
        throw null;
    }

    public static String valueOf(char[] cArr, int i, int i2) {
        new String(cArr, i, i2);
        throw null;
    }

    public static String valueOf(char c) {
        return StringFactory.newStringFromChars(0, 1, new char[]{c});
    }

    public static String valueOf(int i) {
        return Integer.toString(i);
    }

    public static String valueOf(long j) {
        return Long.toString(j);
    }

    public static String valueOf(float f) {
        return Float.toString(f);
    }

    public static String valueOf(double d) {
        return Double.toString(d);
    }
}
