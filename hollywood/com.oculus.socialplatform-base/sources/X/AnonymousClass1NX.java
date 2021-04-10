package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1NX  reason: invalid class name */
public final class AnonymousClass1NX {
    public static boolean A00;

    public static synchronized void A00() {
        synchronized (AnonymousClass1NX.class) {
            if (!A00) {
                AnonymousClass0l0.A06("msysjniinfra");
                A00 = true;
            }
        }
    }
}
