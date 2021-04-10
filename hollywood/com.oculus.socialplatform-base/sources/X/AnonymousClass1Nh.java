package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1Nh  reason: invalid class name */
public final class AnonymousClass1Nh {
    public static boolean A00;

    public static synchronized void A00() {
        synchronized (AnonymousClass1Nh.class) {
            if (!A00) {
                AnonymousClass0l0.A06("msysjniinfranosqlite");
                A00 = true;
            }
        }
    }
}
