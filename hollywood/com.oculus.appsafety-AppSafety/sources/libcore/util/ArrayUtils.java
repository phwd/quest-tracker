package libcore.util;

public final class ArrayUtils {
    private ArrayUtils() {
    }

    public static void throwsIfOutOfBounds(int len, int offset, int count) {
        if (len < 0) {
            throw new ArrayIndexOutOfBoundsException("Negative length: " + len);
        } else if ((offset | count) < 0 || offset > len - count) {
            throw new ArrayIndexOutOfBoundsException("length=" + len + "; regionStart=" + offset + "; regionLength=" + count);
        }
    }
}
