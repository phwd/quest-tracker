package X;

public final class KV {
    public static KW A00;

    public static void A01(String str) {
        synchronized (KV.class) {
            if (A00 == null) {
                throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
            }
        }
        A00.A3i(str, 0);
    }

    public static synchronized void A00(KW kw) {
        synchronized (KV.class) {
            if (A00 == null) {
                A00 = kw;
            } else {
                throw new IllegalStateException("Cannot re-initialize NativeLoader.");
            }
        }
    }

    public static synchronized boolean A02() {
        boolean z;
        synchronized (KV.class) {
            z = false;
            if (A00 != null) {
                z = true;
            }
        }
        return z;
    }
}
