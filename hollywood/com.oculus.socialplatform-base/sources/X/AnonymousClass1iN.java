package X;

import com.facebook.infer.annotation.Nullsafe;
import okhttp3.internal.ws.RealWebSocket;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1iN  reason: invalid class name */
public final class AnonymousClass1iN {
    public static final int A00;
    public static volatile AnonymousClass1iK A01;

    static {
        int i;
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (((long) min) > RealWebSocket.MAX_QUEUE_SIZE) {
            i = (min >> 2) * 3;
        } else {
            i = min >> 1;
        }
        A00 = i;
    }
}
