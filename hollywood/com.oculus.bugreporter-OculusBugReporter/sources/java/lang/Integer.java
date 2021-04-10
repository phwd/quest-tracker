package java.lang;

import android.icu.impl.UCharacterProperty;
import android.icu.impl.number.RoundingUtils;
import android.support.v4.view.MotionEventCompat;
import java.time.Year;
import sun.misc.VM;

public final class Integer extends Number implements Comparable<Integer> {
    public static final int BYTES = 4;
    static final char[] DigitOnes = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static final char[] DigitTens = {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
    public static final int MAX_VALUE = Integer.MAX_VALUE;
    public static final int MIN_VALUE = Integer.MIN_VALUE;
    public static final int SIZE = 32;
    private static final String[] SMALL_NEG_VALUES = new String[100];
    private static final String[] SMALL_NONNEG_VALUES = new String[100];
    public static final Class<Integer> TYPE = Class.getPrimitiveClass("int");
    static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', UCharacterProperty.LATIN_SMALL_LETTER_I_, 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final long serialVersionUID = 1360826667806852920L;
    static final int[] sizeTable = {9, 99, RoundingUtils.MAX_INT_FRAC_SIG, 9999, 99999, 999999, 9999999, 99999999, Year.MAX_VALUE, Integer.MAX_VALUE};
    private final int value;

    public static String toString(int i, int radix) {
        if (radix < 2 || radix > 36) {
            radix = 10;
        }
        if (radix == 10) {
            return toString(i);
        }
        char[] buf = new char[33];
        boolean negative = i < 0;
        int charPos = 32;
        if (!negative) {
            i = -i;
        }
        while (i <= (-radix)) {
            buf[charPos] = digits[-(i % radix)];
            i /= radix;
            charPos--;
        }
        buf[charPos] = digits[-i];
        if (negative) {
            charPos--;
            buf[charPos] = '-';
        }
        return new String(buf, charPos, 33 - charPos);
    }

    public static String toUnsignedString(int i, int radix) {
        return Long.toUnsignedString(toUnsignedLong(i), radix);
    }

    public static String toHexString(int i) {
        return toUnsignedString0(i, 4);
    }

    public static String toOctalString(int i) {
        return toUnsignedString0(i, 3);
    }

    public static String toBinaryString(int i) {
        return toUnsignedString0(i, 1);
    }

    private static String toUnsignedString0(int val, int shift) {
        int chars = Math.max(((shift - 1) + (32 - numberOfLeadingZeros(val))) / shift, 1);
        char[] buf = new char[chars];
        formatUnsignedInt(val, shift, buf, 0, chars);
        return new String(buf);
    }

    static int formatUnsignedInt(int val, int shift, char[] buf, int offset, int len) {
        int charPos = len;
        int mask = (1 << shift) - 1;
        do {
            charPos--;
            buf[offset + charPos] = digits[val & mask];
            val >>>= shift;
            if (val == 0) {
                break;
            }
        } while (charPos > 0);
        return charPos;
    }

    public static String toString(int i) {
        String str;
        String str2;
        if (i == Integer.MIN_VALUE) {
            return "-2147483648";
        }
        boolean negative = i < 0;
        if (!negative ? i < 100 : i > -100) {
            String[] smallValues = negative ? SMALL_NEG_VALUES : SMALL_NONNEG_VALUES;
            if (negative) {
                i = -i;
                if (smallValues[i] == null) {
                    if (i < 10) {
                        str2 = new String(new char[]{'-', DigitOnes[i]});
                    } else {
                        str2 = new String(new char[]{'-', DigitTens[i], DigitOnes[i]});
                    }
                    smallValues[i] = str2;
                }
            } else if (smallValues[i] == null) {
                if (i < 10) {
                    str = new String(new char[]{DigitOnes[i]});
                } else {
                    str = new String(new char[]{DigitTens[i], DigitOnes[i]});
                }
                smallValues[i] = str;
            }
            return smallValues[i];
        }
        int size = negative ? stringSize(-i) + 1 : stringSize(i);
        char[] buf = new char[size];
        getChars(i, size, buf);
        return new String(buf);
    }

    public static String toUnsignedString(int i) {
        return Long.toString(toUnsignedLong(i));
    }

    static void getChars(int i, int index, char[] buf) {
        int charPos = index;
        char sign = 0;
        if (i < 0) {
            sign = '-';
            i = -i;
        }
        while (i >= 65536) {
            int q = i / 100;
            int r = i - (((q << 6) + (q << 5)) + (q << 2));
            i = q;
            int charPos2 = charPos - 1;
            buf[charPos2] = DigitOnes[r];
            charPos = charPos2 - 1;
            buf[charPos] = DigitTens[r];
        }
        do {
            int q2 = (52429 * i) >>> 19;
            charPos--;
            buf[charPos] = digits[i - ((q2 << 3) + (q2 << 1))];
            i = q2;
        } while (i != 0);
        if (sign != 0) {
            buf[charPos - 1] = sign;
        }
    }

    static int stringSize(int x) {
        int i = 0;
        while (x > sizeTable[i]) {
            i++;
        }
        return i + 1;
    }

    public static int parseInt(String s, int radix) throws NumberFormatException {
        if (s == null) {
            throw new NumberFormatException("s == null");
        } else if (radix < 2) {
            throw new NumberFormatException("radix " + radix + " less than Character.MIN_RADIX");
        } else if (radix <= 36) {
            int result = 0;
            boolean negative = false;
            int i = 0;
            int len = s.length();
            int limit = -2147483647;
            if (len > 0) {
                char firstChar = s.charAt(0);
                if (firstChar < '0') {
                    if (firstChar == '-') {
                        negative = true;
                        limit = Integer.MIN_VALUE;
                    } else if (firstChar != '+') {
                        throw NumberFormatException.forInputString(s);
                    }
                    if (len != 1) {
                        i = 0 + 1;
                    } else {
                        throw NumberFormatException.forInputString(s);
                    }
                }
                int multmin = limit / radix;
                while (i < len) {
                    int i2 = i + 1;
                    int digit = Character.digit(s.charAt(i), radix);
                    if (digit < 0) {
                        throw NumberFormatException.forInputString(s);
                    } else if (result >= multmin) {
                        int result2 = result * radix;
                        if (result2 >= limit + digit) {
                            result = result2 - digit;
                            i = i2;
                        } else {
                            throw NumberFormatException.forInputString(s);
                        }
                    } else {
                        throw NumberFormatException.forInputString(s);
                    }
                }
                return negative ? result : -result;
            }
            throw NumberFormatException.forInputString(s);
        } else {
            throw new NumberFormatException("radix " + radix + " greater than Character.MAX_RADIX");
        }
    }

    public static int parseInt(String s) throws NumberFormatException {
        return parseInt(s, 10);
    }

    public static int parseUnsignedInt(String s, int radix) throws NumberFormatException {
        if (s != null) {
            int len = s.length();
            if (len <= 0) {
                throw NumberFormatException.forInputString(s);
            } else if (s.charAt(0) == '-') {
                throw new NumberFormatException(String.format("Illegal leading minus sign on unsigned string %s.", s));
            } else if (len <= 5 || (radix == 10 && len <= 9)) {
                return parseInt(s, radix);
            } else {
                long ell = Long.parseLong(s, radix);
                if ((-4294967296L & ell) == 0) {
                    return (int) ell;
                }
                throw new NumberFormatException(String.format("String value %s exceeds range of unsigned int.", s));
            }
        } else {
            throw new NumberFormatException("null");
        }
    }

    public static int parseUnsignedInt(String s) throws NumberFormatException {
        return parseUnsignedInt(s, 10);
    }

    public static Integer valueOf(String s, int radix) throws NumberFormatException {
        return valueOf(parseInt(s, radix));
    }

    public static Integer valueOf(String s) throws NumberFormatException {
        return valueOf(parseInt(s, 10));
    }

    /* access modifiers changed from: private */
    public static class IntegerCache {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final Integer[] cache = new Integer[((high + 128) + 1)];
        static final int high;
        static final int low = -128;

        static {
            int h = 127;
            String integerCacheHighPropValue = VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
            if (integerCacheHighPropValue != null) {
                try {
                    h = Math.min(Math.max(Integer.parseInt(integerCacheHighPropValue), 127), 2147483518);
                } catch (NumberFormatException e) {
                }
            }
            high = h;
            int j = low;
            int k = 0;
            while (true) {
                Integer[] numArr = cache;
                if (k < numArr.length) {
                    numArr[k] = new Integer(j);
                    k++;
                    j++;
                } else {
                    return;
                }
            }
        }

        private IntegerCache() {
        }
    }

    public static Integer valueOf(int i) {
        if (i < -128 || i > IntegerCache.high) {
            return new Integer(i);
        }
        return IntegerCache.cache[i + 128];
    }

    public Integer(int value2) {
        this.value = value2;
    }

    public Integer(String s) throws NumberFormatException {
        this.value = parseInt(s, 10);
    }

    @Override // java.lang.Number
    public byte byteValue() {
        return (byte) this.value;
    }

    @Override // java.lang.Number
    public short shortValue() {
        return (short) this.value;
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
    public float floatValue() {
        return (float) this.value;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return (double) this.value;
    }

    public String toString() {
        return toString(this.value);
    }

    public int hashCode() {
        return hashCode(this.value);
    }

    public static int hashCode(int value2) {
        return value2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Integer) || this.value != ((Integer) obj).intValue()) {
            return false;
        }
        return true;
    }

    public static Integer getInteger(String nm) {
        return getInteger(nm, (Integer) null);
    }

    public static Integer getInteger(String nm, int val) {
        Integer result = getInteger(nm, (Integer) null);
        return result == null ? valueOf(val) : result;
    }

    public static Integer getInteger(String nm, Integer val) {
        String v = null;
        try {
            v = System.getProperty(nm);
        } catch (IllegalArgumentException | NullPointerException e) {
        }
        if (v != null) {
            try {
                return decode(v);
            } catch (NumberFormatException e2) {
            }
        }
        return val;
    }

    /* JADX INFO: Multiple debug info for r4v7 java.lang.Integer: [D('constant' java.lang.String), D('result' java.lang.Integer)] */
    public static Integer decode(String nm) throws NumberFormatException {
        String constant;
        int radix = 10;
        int index = 0;
        boolean negative = false;
        if (nm.length() != 0) {
            char firstChar = nm.charAt(0);
            if (firstChar == '-') {
                negative = true;
                index = 0 + 1;
            } else if (firstChar == '+') {
                index = 0 + 1;
            }
            if (nm.startsWith("0x", index) || nm.startsWith("0X", index)) {
                index += 2;
                radix = 16;
            } else if (nm.startsWith("#", index)) {
                index++;
                radix = 16;
            } else if (nm.startsWith(AndroidHardcodedSystemProperties.JAVA_VERSION, index) && nm.length() > index + 1) {
                index++;
                radix = 8;
            }
            if (nm.startsWith("-", index) || nm.startsWith("+", index)) {
                throw new NumberFormatException("Sign character in wrong position");
            }
            try {
                Integer result = valueOf(nm.substring(index), radix);
                if (negative) {
                    return valueOf(-result.intValue());
                }
                return result;
            } catch (NumberFormatException e) {
                if (negative) {
                    constant = "-" + nm.substring(index);
                } else {
                    constant = nm.substring(index);
                }
                return valueOf(constant, radix);
            }
        } else {
            throw new NumberFormatException("Zero length string");
        }
    }

    public int compareTo(Integer anotherInteger) {
        return compare(this.value, anotherInteger.value);
    }

    public static int compare(int x, int y) {
        if (x < y) {
            return -1;
        }
        return x == y ? 0 : 1;
    }

    public static int compareUnsigned(int x, int y) {
        return compare(x - 2147483648, Integer.MIN_VALUE + y);
    }

    public static long toUnsignedLong(int x) {
        return ((long) x) & 4294967295L;
    }

    public static int divideUnsigned(int dividend, int divisor) {
        return (int) (toUnsignedLong(dividend) / toUnsignedLong(divisor));
    }

    public static int remainderUnsigned(int dividend, int divisor) {
        return (int) (toUnsignedLong(dividend) % toUnsignedLong(divisor));
    }

    public static int highestOneBit(int i) {
        int i2 = i | (i >> 1);
        int i3 = i2 | (i2 >> 2);
        int i4 = i3 | (i3 >> 4);
        int i5 = i4 | (i4 >> 8);
        int i6 = i5 | (i5 >> 16);
        return i6 - (i6 >>> 1);
    }

    public static int lowestOneBit(int i) {
        return (-i) & i;
    }

    public static int numberOfLeadingZeros(int i) {
        if (i == 0) {
            return 32;
        }
        int n = 1;
        if ((i >>> 16) == 0) {
            n = 1 + 16;
            i <<= 16;
        }
        if ((i >>> 24) == 0) {
            n += 8;
            i <<= 8;
        }
        if ((i >>> 28) == 0) {
            n += 4;
            i <<= 4;
        }
        if ((i >>> 30) == 0) {
            n += 2;
            i <<= 2;
        }
        return n - (i >>> 31);
    }

    public static int numberOfTrailingZeros(int i) {
        if (i == 0) {
            return 32;
        }
        int n = 31;
        int y = i << 16;
        if (y != 0) {
            n = 31 - 16;
            i = y;
        }
        int y2 = i << 8;
        if (y2 != 0) {
            n -= 8;
            i = y2;
        }
        int y3 = i << 4;
        if (y3 != 0) {
            n -= 4;
            i = y3;
        }
        int y4 = i << 2;
        if (y4 != 0) {
            n -= 2;
            i = y4;
        }
        return n - ((i << 1) >>> 31);
    }

    public static int bitCount(int i) {
        int i2 = i - ((i >>> 1) & 1431655765);
        int i3 = (i2 & 858993459) + (858993459 & (i2 >>> 2));
        int i4 = ((i3 >>> 4) + i3) & 252645135;
        int i5 = i4 + (i4 >>> 8);
        return (i5 + (i5 >>> 16)) & 63;
    }

    public static int rotateLeft(int i, int distance) {
        return (i << distance) | (i >>> (-distance));
    }

    public static int rotateRight(int i, int distance) {
        return (i >>> distance) | (i << (-distance));
    }

    public static int reverse(int i) {
        int i2 = ((i & 1431655765) << 1) | (1431655765 & (i >>> 1));
        int i3 = ((i2 & 858993459) << 2) | (858993459 & (i2 >>> 2));
        int i4 = ((i3 & 252645135) << 4) | (252645135 & (i3 >>> 4));
        return (i4 << 24) | ((i4 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) << 8) | (65280 & (i4 >>> 8)) | (i4 >>> 24);
    }

    public static int signum(int i) {
        return (i >> 31) | ((-i) >>> 31);
    }

    public static int reverseBytes(int i) {
        return (i >>> 24) | ((i >> 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((i << 8) & 16711680) | (i << 24);
    }

    public static int sum(int a, int b) {
        return a + b;
    }

    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    public static int min(int a, int b) {
        return Math.min(a, b);
    }
}
