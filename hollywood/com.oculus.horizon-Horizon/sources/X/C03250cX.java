package X;

/* renamed from: X.0cX  reason: invalid class name and case insensitive filesystem */
public final class C03250cX {
    public static AbstractC03260cY A00;

    public static void A01(String str) {
        synchronized (C03250cX.class) {
            if (A00 == null) {
                throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
            }
        }
        A00.A5F(str, 0);
    }

    public static synchronized void A00(AbstractC03260cY r3) {
        synchronized (C03250cX.class) {
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
        synchronized (C03250cX.class) {
            z = false;
            if (A00 != null) {
                z = true;
            }
        }
        return z;
    }
}
