package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1NZ  reason: invalid class name */
public final class AnonymousClass1NZ {
    public static boolean A00;

    public static synchronized void A00() {
        synchronized (AnonymousClass1NZ.class) {
            if (!A00) {
                AnonymousClass0l0.A06("msysjni");
                A00 = true;
            }
        }
    }
}
