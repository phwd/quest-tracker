package libcore.util;

public final class ArrayUtils {
    public static void throwsIfOutOfBounds(int i, int i2, int i3) {
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Negative length: " + i);
        } else if ((i2 | i3) < 0 || i2 > i - i3) {
            throw new ArrayIndexOutOfBoundsException("length=" + i + "; regionStart=" + i2 + "; regionLength=" + i3);
        }
    }
}
