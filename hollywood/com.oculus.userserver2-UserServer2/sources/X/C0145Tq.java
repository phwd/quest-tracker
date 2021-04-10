package X;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
/* renamed from: X.Tq  reason: case insensitive filesystem */
public final class C0145Tq {
    public static int A00(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }
}
