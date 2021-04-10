package java.lang;

public final class Short extends Number implements Comparable<Short> {
    public static final int BYTES = 2;
    public static final short MAX_VALUE = Short.MAX_VALUE;
    public static final short MIN_VALUE = Short.MIN_VALUE;
    public static final int SIZE = 16;
    public static final Class<Short> TYPE = Class.getPrimitiveClass("short");
    private static final long serialVersionUID = 7515723908773894738L;
    private final short value;

    public static String toString(short s) {
        return Integer.toString(s, 10);
    }

    public static short parseShort(String s, int radix) throws NumberFormatException {
        int i = Integer.parseInt(s, radix);
        if (i >= -32768 && i <= 32767) {
            return (short) i;
        }
        throw new NumberFormatException("Value out of range. Value:\"" + s + "\" Radix:" + radix);
    }

    public static short parseShort(String s) throws NumberFormatException {
        return parseShort(s, 10);
    }

    public static Short valueOf(String s, int radix) throws NumberFormatException {
        return valueOf(parseShort(s, radix));
    }

    public static Short valueOf(String s) throws NumberFormatException {
        return valueOf(s, 10);
    }

    /* access modifiers changed from: private */
    public static class ShortCache {
        static final Short[] cache = new Short[256];

        private ShortCache() {
        }

        static {
            int i = 0;
            while (true) {
                Short[] shArr = cache;
                if (i < shArr.length) {
                    shArr[i] = new Short((short) (i - 128));
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public static Short valueOf(short s) {
        if (s < -128 || s > 127) {
            return new Short(s);
        }
        return ShortCache.cache[s + 128];
    }

    public static Short decode(String nm) throws NumberFormatException {
        int i = Integer.decode(nm).intValue();
        if (i >= -32768 && i <= 32767) {
            return valueOf((short) i);
        }
        throw new NumberFormatException("Value " + i + " out of range from input " + nm);
    }

    public Short(short value2) {
        this.value = value2;
    }

    public Short(String s) throws NumberFormatException {
        this.value = parseShort(s, 10);
    }

    @Override // java.lang.Number
    public byte byteValue() {
        return (byte) this.value;
    }

    @Override // java.lang.Number
    public short shortValue() {
        return this.value;
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

    public static int hashCode(short value2) {
        return value2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Short) || this.value != ((Short) obj).shortValue()) {
            return false;
        }
        return true;
    }

    public int compareTo(Short anotherShort) {
        return compare(this.value, anotherShort.value);
    }

    public static int compare(short x, short y) {
        return x - y;
    }

    public static short reverseBytes(short i) {
        return (short) (((65280 & i) >> 8) | (i << 8));
    }

    public static int toUnsignedInt(short x) {
        return 65535 & x;
    }

    public static long toUnsignedLong(short x) {
        return ((long) x) & 65535;
    }
}
