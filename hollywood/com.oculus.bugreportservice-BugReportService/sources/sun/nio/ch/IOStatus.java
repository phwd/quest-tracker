package sun.nio.ch;

public final class IOStatus {
    public static boolean checkAll(long j) {
        return j > -1 || j < -6;
    }

    public static int normalize(int i) {
        if (i == -2) {
            return 0;
        }
        return i;
    }

    public static long normalize(long j) {
        if (j == -2) {
            return 0;
        }
        return j;
    }
}
