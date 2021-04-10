package X;

/* renamed from: X.1il  reason: invalid class name and case insensitive filesystem */
public final class C09531il implements AnonymousClass1i3 {
    public static C09531il A00;

    public static synchronized C09531il A00() {
        C09531il r0;
        synchronized (C09531il.class) {
            r0 = A00;
            if (r0 == null) {
                r0 = new C09531il();
                A00 = r0;
            }
        }
        return r0;
    }
}
