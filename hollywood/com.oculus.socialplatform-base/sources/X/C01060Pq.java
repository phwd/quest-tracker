package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0Pq  reason: invalid class name and case insensitive filesystem */
public final class C01060Pq {
    public static final AnonymousClass0mS A00 = new AnonymousClass0mS();
    @Nullable
    public static volatile AbstractC03400mT A01;

    public static void A00() {
        if (A01 == null) {
            synchronized (C01060Pq.class) {
                if (A01 == null) {
                    A01 = new AbstractC03400mT();
                }
            }
        }
    }
}
