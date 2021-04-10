package X;

public final class g6 {
    public static void A00() {
        synchronized (g6.class) {
            throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
        }
    }
}
