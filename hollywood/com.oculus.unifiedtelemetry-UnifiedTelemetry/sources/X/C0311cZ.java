package X;

import com.facebook.acra.LogCatCollector;
import java.nio.charset.Charset;

/* renamed from: X.cZ  reason: case insensitive filesystem */
public final class C0311cZ {
    public static final Charset A00 = Charset.forName(LogCatCollector.UTF_8_ENCODING);

    public static void A00(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)));
        }
    }
}
