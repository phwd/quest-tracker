package java.lang;

import sun.misc.VM;

public final class Integer extends Number implements Comparable {
    static final char[] DigitOnes = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static final char[] DigitTens = {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
    private static final String[] SMALL_NEG_VALUES = new String[100];
    private static final String[] SMALL_NONNEG_VALUES = new String[100];
    public static final Class TYPE = Class.getPrimitiveClass("int");
    static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final long serialVersionUID = 1360826667806852920L;
    static final int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};
    private final int value;

    public static int bitCount(int i) {
        int i2 = i - ((i >>> 1) & 1431655765);
        int i3 = (i2 & 858993459) + ((i2 >>> 2) & 858993459);
        int i4 = 252645135 & (i3 + (i3 >>> 4));
        int i5 = i4 + (i4 >>> 8);
        return (i5 + (i5 >>> 16)) & 63;
    }

    public static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public static int highestOneBit(int i) {
        int i2 = i | (i >> 1);
        int i3 = i2 | (i2 >> 2);
        int i4 = i3 | (i3 >> 4);
        int i5 = i4 | (i4 >> 8);
        int i6 = i5 | (i5 >> 16);
        return i6 - (i6 >>> 1);
    }

    public static int numberOfLeadingZeros(int i) {
        if (i == 0) {
            return 32;
        }
        int i2 = 1;
        if ((i >>> 16) == 0) {
            i2 = 17;
            i <<= 16;
        }
        if ((i >>> 24) == 0) {
            i2 += 8;
            i <<= 8;
        }
        if ((i >>> 28) == 0) {
            i2 += 4;
            i <<= 4;
        }
        if ((i >>> 30) == 0) {
            i2 += 2;
            i <<= 2;
        }
        return i2 - (i >>> 31);
    }

    public static int numberOfTrailingZeros(int i) {
        int i2;
        if (i == 0) {
            return 32;
        }
        int i3 = i << 16;
        if (i3 != 0) {
            i2 = 15;
            i = i3;
        } else {
            i2 = 31;
        }
        int i4 = i << 8;
        if (i4 != 0) {
            i2 -= 8;
            i = i4;
        }
        int i5 = i << 4;
        if (i5 != 0) {
            i2 -= 4;
            i = i5;
        }
        int i6 = i << 2;
        if (i6 != 0) {
            i2 -= 2;
            i = i6;
        }
        return i2 - ((i << 1) >>> 31);
    }

    public static int reverseBytes(int i) {
        return (i << 24) | (i >>> 24) | ((i >> 8) & 65280) | ((i << 8) & 16711680);
    }

    public static int rotateLeft(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    public static int signum(int i) {
        return ((-i) >>> 31) | (i >> 31);
    }

    public static String toString(int i, int i2) {
        if (i2 < 2 || i2 > 36) {
            i2 = 10;
        }
        if (i2 == 10) {
            return toString(i);
        }
        char[] cArr = new char[33];
        boolean z = i < 0;
        int i3 = 32;
        if (!z) {
            i = -i;
        }
        while (i <= (-i2)) {
            cArr[i3] = digits[-(i % i2)];
            i /= i2;
            i3--;
        }
        cArr[i3] = digits[-i];
        if (z) {
            i3--;
            cArr[i3] = '-';
        }
        new String(cArr, i3, 33 - i3);
        throw null;
    }

    public static String toHexString(int i) {
        toUnsignedString0(i, 4);
        throw null;
    }

    public static String toBinaryString(int i) {
        toUnsignedString0(i, 1);
        throw null;
    }

    private static String toUnsignedString0(int i, int i2) {
        int max = Math.max(((32 - numberOfLeadingZeros(i)) + (i2 - 1)) / i2, 1);
        char[] cArr = new char[max];
        formatUnsignedInt(i, i2, cArr, 0, max);
        new String(cArr);
        throw null;
    }

    static int formatUnsignedInt(int i, int i2, char[] cArr, int i3, int i4) {
        int i5 = (1 << i2) - 1;
        do {
            i4--;
            cArr[i3 + i4] = digits[i & i5];
            i >>>= i2;
            if (i == 0) {
                break;
            }
        } while (i4 > 0);
        return i4;
    }

    public static String toString(int i) {
        if (i == Integer.MIN_VALUE) {
            return "-2147483648";
        }
        boolean z = i < 0;
        if (!z ? i < 100 : i > -100) {
            String[] strArr = z ? SMALL_NEG_VALUES : SMALL_NONNEG_VALUES;
            if (z) {
                i = -i;
                if (strArr[i] == null) {
                    if (i < 10) {
                        new String(new char[]{'-', DigitOnes[i]});
                        throw null;
                    }
                    new String(new char[]{'-', DigitTens[i], DigitOnes[i]});
                    throw null;
                }
            } else if (strArr[i] == null) {
                if (i < 10) {
                    new String(new char[]{DigitOnes[i]});
                    throw null;
                }
                new String(new char[]{DigitTens[i], DigitOnes[i]});
                throw null;
            }
            return strArr[i];
        }
        int stringSize = z ? stringSize(-i) + 1 : stringSize(i);
        char[] cArr = new char[stringSize];
        getChars(i, stringSize, cArr);
        new String(cArr);
        throw null;
    }

    static void getChars(int i, int i2, char[] cArr) {
        char c;
        if (i < 0) {
            c = '-';
            i = -i;
        } else {
            c = 0;
        }
        while (i >= 65536) {
            int i3 = i / 100;
            int i4 = i - (((i3 << 6) + (i3 << 5)) + (i3 << 2));
            int i5 = i2 - 1;
            cArr[i5] = DigitOnes[i4];
            i2 = i5 - 1;
            cArr[i2] = DigitTens[i4];
            i = i3;
        }
        while (true) {
            int i6 = (52429 * i) >>> 19;
            i2--;
            cArr[i2] = digits[i - ((i6 << 3) + (i6 << 1))];
            if (i6 == 0) {
                break;
            }
            i = i6;
        }
        if (c != 0) {
            cArr[i2 - 1] = c;
        }
    }

    static int stringSize(int i) {
        int i2 = 0;
        while (i > sizeTable[i2]) {
            i2++;
        }
        return i2 + 1;
    }

    public static int parseInt(String str, int i) {
        boolean z;
        if (str == null) {
            throw new NumberFormatException("s == null");
        } else if (i < 2) {
            throw new NumberFormatException("radix " + i + " less than Character.MIN_RADIX");
        } else if (i <= 36) {
            int length = str.length();
            int i2 = -2147483647;
            if (length > 0) {
                int i3 = 0;
                char charAt = str.charAt(0);
                int i4 = 1;
                if (charAt < '0') {
                    if (charAt == '-') {
                        i2 = Integer.MIN_VALUE;
                        z = true;
                    } else if (charAt == '+') {
                        z = false;
                    } else {
                        throw NumberFormatException.forInputString(str);
                    }
                    if (length == 1) {
                        throw NumberFormatException.forInputString(str);
                    }
                } else {
                    z = false;
                    i4 = 0;
                }
                int i5 = i2 / i;
                while (i4 < length) {
                    int i6 = i4 + 1;
                    int digit = Character.digit(str.charAt(i4), i);
                    if (digit < 0) {
                        throw NumberFormatException.forInputString(str);
                    } else if (i3 >= i5) {
                        int i7 = i3 * i;
                        if (i7 >= i2 + digit) {
                            i3 = i7 - digit;
                            i4 = i6;
                        } else {
                            throw NumberFormatException.forInputString(str);
                        }
                    } else {
                        throw NumberFormatException.forInputString(str);
                    }
                }
                return z ? i3 : -i3;
            }
            throw NumberFormatException.forInputString(str);
        } else {
            throw new NumberFormatException("radix " + i + " greater than Character.MAX_RADIX");
        }
    }

    public static int parseInt(String str) {
        return parseInt(str, 10);
    }

    public static Integer valueOf(String str, int i) {
        return valueOf(parseInt(str, i));
    }

    public static Integer valueOf(String str) {
        return valueOf(parseInt(str, 10));
    }

    /* access modifiers changed from: private */
    public static class IntegerCache {
        static final Integer[] cache = new Integer[((high - -128) + 1)];
        static final int high;

        static {
            String savedProperty = VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
            int i = 127;
            if (savedProperty != null) {
                try {
                    i = Math.min(Math.max(Integer.parseInt(savedProperty), 127), 2147483518);
                } catch (NumberFormatException unused) {
                }
            }
            high = i;
            int i2 = -128;
            int i3 = 0;
            while (true) {
                Integer[] numArr = cache;
                if (i3 < numArr.length) {
                    numArr[i3] = new Integer(i2);
                    i3++;
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    public static Integer valueOf(int i) {
        if (i < -128 || i > IntegerCache.high) {
            return new Integer(i);
        }
        return IntegerCache.cache[i + 128];
    }

    public Integer(int i) {
        this.value = i;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return (long) this.value;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return (double) this.value;
    }

    public String toString() {
        return toString(this.value);
    }

    public int hashCode() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Integer) || this.value != ((Integer) obj).intValue()) {
            return false;
        }
        return true;
    }

    public static Integer getInteger(String str, int i) {
        Integer integer = getInteger(str, (Integer) null);
        return integer == null ? valueOf(i) : integer;
    }

    public static Integer getInteger(String str, Integer num) {
        String str2;
        try {
            str2 = System.getProperty(str);
        } catch (IllegalArgumentException | NullPointerException unused) {
            str2 = null;
        }
        if (str2 != null) {
            try {
                return decode(str2);
            } catch (NumberFormatException unused2) {
            }
        }
        return num;
    }

    public static Integer decode(String str) {
        String str2;
        int i;
        if (str.length() != 0) {
            boolean z = false;
            char charAt = str.charAt(0);
            int i2 = 1;
            if (charAt == '-') {
                z = true;
            } else if (charAt != '+') {
                i2 = 0;
            }
            int i3 = 16;
            if (str.startsWith("0x", i2) || str.startsWith("0X", i2)) {
                i2 += 2;
            } else if (str.startsWith("#", i2)) {
                i2++;
            } else if (!str.startsWith("0", i2) || str.length() <= (i = i2 + 1)) {
                i3 = 10;
            } else {
                i2 = i;
                i3 = 8;
            }
            if (str.startsWith("-", i2) || str.startsWith("+", i2)) {
                throw new NumberFormatException("Sign character in wrong position");
            }
            try {
                Integer valueOf = valueOf(str.substring(i2), i3);
                if (z) {
                    return valueOf(-valueOf.intValue());
                }
                return valueOf;
            } catch (NumberFormatException unused) {
                if (z) {
                    str2 = "-" + str.substring(i2);
                } else {
                    str2 = str.substring(i2);
                }
                return valueOf(str2, i3);
            }
        } else {
            throw new NumberFormatException("Zero length string");
        }
    }

    public int compareTo(Integer num) {
        return compare(this.value, num.value);
    }
}
