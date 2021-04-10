package java.lang;

import sun.misc.FloatingDecimal;

public final class Double extends Number implements Comparable {
    public static final Class TYPE = Class.getPrimitiveClass("double");
    private static final long serialVersionUID = -9172774392245257468L;
    private final double value;

    public static native long doubleToRawLongBits(double d);

    public static boolean isInfinite(double d) {
        return d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY;
    }

    public static boolean isNaN(double d) {
        return d != d;
    }

    public static native double longBitsToDouble(long j);

    public static String toString(double d) {
        return FloatingDecimal.toJavaFormatString(d);
    }

    public static String toHexString(double d) {
        if (!isFinite(d)) {
            return toString(d);
        }
        StringBuilder sb = new StringBuilder(24);
        if (Math.copySign(1.0d, d) == -1.0d) {
            sb.append("-");
        }
        sb.append("0x");
        double abs = Math.abs(d);
        if (abs == 0.0d) {
            sb.append("0.0p0");
            return sb.toString();
        }
        boolean z = abs < Double.MIN_NORMAL;
        long doubleToLongBits = (doubleToLongBits(abs) & 4503599627370495L) | 1152921504606846976L;
        sb.append(z ? "0." : "1.");
        Long.toHexString(doubleToLongBits);
        throw null;
    }

    public static Double valueOf(String str) {
        return new Double(parseDouble(str));
    }

    public static Double valueOf(double d) {
        return new Double(d);
    }

    public static double parseDouble(String str) {
        return FloatingDecimal.parseDouble(str);
    }

    public static boolean isFinite(double d) {
        return Math.abs(d) <= Double.MAX_VALUE;
    }

    public Double(double d) {
        this.value = d;
    }

    public Double(String str) {
        this.value = parseDouble(str);
    }

    public boolean isNaN() {
        return isNaN(this.value);
    }

    public boolean isInfinite() {
        return isInfinite(this.value);
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

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    public int hashCode() {
        return hashCode(this.value);
    }

    public static int hashCode(double d) {
        long doubleToLongBits = doubleToLongBits(d);
        return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    }

    public boolean equals(Object obj) {
        return (obj instanceof Double) && doubleToLongBits(((Double) obj).value) == doubleToLongBits(this.value);
    }

    public static long doubleToLongBits(double d) {
        long doubleToRawLongBits = doubleToRawLongBits(d);
        if ((doubleToRawLongBits & 9218868437227405312L) != 9218868437227405312L || (4503599627370495L & doubleToRawLongBits) == 0) {
            return doubleToRawLongBits;
        }
        return 9221120237041090560L;
    }

    public int compareTo(Double d) {
        return compare(this.value, d.value);
    }

    public static int compare(double d, double d2) {
        if (d < d2) {
            return -1;
        }
        if (d > d2) {
            return 1;
        }
        int i = (doubleToLongBits(d) > doubleToLongBits(d2) ? 1 : (doubleToLongBits(d) == doubleToLongBits(d2) ? 0 : -1));
        if (i == 0) {
            return 0;
        }
        return i < 0 ? -1 : 1;
    }
}
