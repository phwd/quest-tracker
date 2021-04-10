package X;

import java.util.Arrays;

public class DM extends Wr {
    public static final char[] A00;
    public static final DM A01 = new DM();

    static {
        try {
            System.getProperty("line.separator");
        } catch (Throwable unused) {
        }
        char[] cArr = new char[64];
        A00 = cArr;
        Arrays.fill(cArr, ' ');
    }
}
