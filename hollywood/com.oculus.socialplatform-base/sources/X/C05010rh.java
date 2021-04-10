package X;

/* renamed from: X.0rh  reason: invalid class name and case insensitive filesystem */
public final class C05010rh implements AnonymousClass0JS {
    public static C05010rh A00;

    public static synchronized C05010rh A00() {
        C05010rh r0;
        synchronized (C05010rh.class) {
            r0 = A00;
            if (r0 == null) {
                r0 = new C05010rh();
                A00 = r0;
            }
        }
        return r0;
    }
}
