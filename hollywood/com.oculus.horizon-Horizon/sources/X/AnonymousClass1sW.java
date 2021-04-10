package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1sW  reason: invalid class name */
public final class AnonymousClass1sW {
    public static final int A00;
    public static volatile AnonymousClass1rU A01;

    static {
        int i;
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (((long) min) > 16777216) {
            i = (min >> 2) * 3;
        } else {
            i = min >> 1;
        }
        A00 = i;
    }
}
