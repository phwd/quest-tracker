package java.lang;

import android.icu.impl.UCharacterProperty;

public final class Byte extends Number implements Comparable<Byte> {
    public static final int BYTES = 1;
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', UCharacterProperty.LATIN_SMALL_LETTER_I_, 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static final byte MAX_VALUE = Byte.MAX_VALUE;
    public static final byte MIN_VALUE = Byte.MIN_VALUE;
    public static final int SIZE = 8;
    public static final Class<Byte> TYPE = Class.getPrimitiveClass("byte");
    private static final char[] UPPER_CASE_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final long serialVersionUID = -7183698231559129828L;
    private final byte value;

    public static String toString(byte b) {
        return Integer.toString(b, 10);
    }

    /* access modifiers changed from: private */
    public static class ByteCache {
        static final Byte[] cache = new Byte[256];

        private ByteCache() {
        }

        static {
            int i = 0;
            while (true) {
                Byte[] bArr = cache;
                if (i < bArr.length) {
                    bArr[i] = new Byte((byte) (i - 128));
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public static Byte valueOf(byte b) {
        return ByteCache.cache[b + 128];
    }

    public static byte parseByte(String s, int radix) throws NumberFormatException {
        int i = Integer.parseInt(s, radix);
        if (i >= -128 && i <= 127) {
            return (byte) i;
        }
        throw new NumberFormatException("Value out of range. Value:\"" + s + "\" Radix:" + radix);
    }

    public static byte parseByte(String s) throws NumberFormatException {
        return parseByte(s, 10);
    }

    public static Byte valueOf(String s, int radix) throws NumberFormatException {
        return valueOf(parseByte(s, radix));
    }

    public static Byte valueOf(String s) throws NumberFormatException {
        return valueOf(s, 10);
    }

    public static Byte decode(String nm) throws NumberFormatException {
        int i = Integer.decode(nm).intValue();
        if (i >= -128 && i <= 127) {
            return valueOf((byte) i);
        }
        throw new NumberFormatException("Value " + i + " out of range from input " + nm);
    }

    public Byte(byte value2) {
        this.value = value2;
    }

    public Byte(String s) throws NumberFormatException {
        this.value = parseByte(s, 10);
    }

    @Override // java.lang.Number
    public byte byteValue() {
        return this.value;
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
        return Integer.toString(this.value);
    }

    public int hashCode() {
        return hashCode(this.value);
    }

    public static int hashCode(byte value2) {
        return value2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Byte) || this.value != ((Byte) obj).byteValue()) {
            return false;
        }
        return true;
    }

    public int compareTo(Byte anotherByte) {
        return compare(this.value, anotherByte.value);
    }

    public static int compare(byte x, byte y) {
        return x - y;
    }

    public static int toUnsignedInt(byte x) {
        return x & 255;
    }

    public static long toUnsignedLong(byte x) {
        return ((long) x) & 255;
    }

    public static String toHexString(byte b, boolean upperCase) {
        char[] digits = upperCase ? UPPER_CASE_DIGITS : DIGITS;
        return new String(0, 2, new char[]{digits[(b >> 4) & 15], digits[b & 15]});
    }
}
