package X;

/* renamed from: X.1uQ  reason: invalid class name */
public final class AnonymousClass1uQ implements AbstractC10691uo {
    public static AnonymousClass1uQ A00;

    public static synchronized AnonymousClass1uQ A00() {
        AnonymousClass1uQ r0;
        synchronized (AnonymousClass1uQ.class) {
            r0 = A00;
            if (r0 == null) {
                r0 = new AnonymousClass1uQ();
                A00 = r0;
            }
        }
        return r0;
    }
}
