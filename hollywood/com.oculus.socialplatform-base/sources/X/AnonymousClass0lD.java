package X;

/* renamed from: X.0lD  reason: invalid class name */
public final class AnonymousClass0lD {
    public static AnonymousClass0lE A00;

    public static void A01(String str) {
        synchronized (AnonymousClass0lD.class) {
            if (A00 == null) {
                throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
            }
        }
        A00.A6I(str, 0);
    }

    public static synchronized void A00(AnonymousClass0lE r3) {
        synchronized (AnonymousClass0lD.class) {
            if (!A02()) {
                if (A00 == null) {
                    A00 = r3;
                } else {
                    throw new IllegalStateException("Cannot re-initialize NativeLoader.");
                }
            }
        }
    }

    public static synchronized boolean A02() {
        boolean z;
        synchronized (AnonymousClass0lD.class) {
            z = false;
            if (A00 != null) {
                z = true;
            }
        }
        return z;
    }
}
