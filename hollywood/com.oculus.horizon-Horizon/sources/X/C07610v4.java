package X;

import java.nio.charset.Charset;

/* renamed from: X.0v4  reason: invalid class name and case insensitive filesystem */
public final class C07610v4 {
    public static final Charset A00 = Charset.forName("UTF-8");

    public static void A00(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)));
        }
    }
}
