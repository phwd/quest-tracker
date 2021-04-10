package java.lang;

import sun.misc.FloatingDecimal;

public final class Float extends Number implements Comparable {
    public static final Class TYPE = Class.getPrimitiveClass("float");
    private static final long serialVersionUID = -2671257302660747028L;
    private final float value;

    public static native int floatToRawIntBits(float f);

    public static native float intBitsToFloat(int i);

    public static boolean isNaN(float f) {
        return f != f;
    }

    public static String toString(float f) {
        return FloatingDecimal.toJavaFormatString(f);
    }

    public String toString() {
        return toString(this.value);
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return (long) this.value;
    }

    public float floatValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return (double) this.value;
    }

    public int hashCode() {
        return hashCode(this.value);
    }

    public static int hashCode(float f) {
        return floatToIntBits(f);
    }

    public boolean equals(Object obj) {
        return (obj instanceof Float) && floatToIntBits(((Float) obj).value) == floatToIntBits(this.value);
    }

    public static int floatToIntBits(float f) {
        int floatToRawIntBits = floatToRawIntBits(f);
        if ((floatToRawIntBits & 2139095040) != 2139095040 || (8388607 & floatToRawIntBits) == 0) {
            return floatToRawIntBits;
        }
        return 2143289344;
    }

    public int compareTo(Float f) {
        return compare(this.value, f.value);
    }

    public static int compare(float f, float f2) {
        if (f < f2) {
            return -1;
        }
        if (f > f2) {
            return 1;
        }
        int floatToIntBits = floatToIntBits(f);
        int floatToIntBits2 = floatToIntBits(f2);
        if (floatToIntBits == floatToIntBits2) {
            return 0;
        }
        return floatToIntBits < floatToIntBits2 ? -1 : 1;
    }
}
