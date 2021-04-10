package X;

/* renamed from: X.hn  reason: case insensitive filesystem */
public final class C0431hn {
    public static AbstractC0432ho A00;

    public static void A00(String str) {
        synchronized (C0431hn.class) {
            if (A00 == null) {
                throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
            }
        }
        A00.A3P(str, 0);
    }
}
