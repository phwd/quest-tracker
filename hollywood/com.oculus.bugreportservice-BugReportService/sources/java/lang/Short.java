package java.lang;

public final class Short extends Number implements Comparable {
    public static final Class TYPE = Class.getPrimitiveClass("short");
    private static final long serialVersionUID = 7515723908773894738L;
    private final short value;

    public static int compare(short s, short s2) {
        return s - s2;
    }

    public static short reverseBytes(short s) {
        return (short) ((s << 8) | ((65280 & s) >> 8));
    }

    public static short parseShort(String str, int i) {
        int parseInt = Integer.parseInt(str, i);
        if (parseInt >= -32768 && parseInt <= 32767) {
            return (short) parseInt;
        }
        throw new NumberFormatException("Value out of range. Value:\"" + str + "\" Radix:" + i);
    }

    public static Short valueOf(String str, int i) {
        return valueOf(parseShort(str, i));
    }

    public static Short valueOf(String str) {
        return valueOf(str, 10);
    }

    /* access modifiers changed from: private */
    public static class ShortCache {
        static final Short[] cache = new Short[256];

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

    public Short(short s) {
        this.value = s;
    }

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
    public double doubleValue() {
        return (double) this.value;
    }

    public String toString() {
        return Integer.toString(this.value);
    }

    public int hashCode() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Short) || this.value != ((Short) obj).shortValue()) {
            return false;
        }
        return true;
    }

    public int compareTo(Short sh) {
        return compare(this.value, sh.value);
    }
}
