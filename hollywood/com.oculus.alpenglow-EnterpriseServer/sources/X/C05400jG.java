package X;

/* renamed from: X.0jG  reason: invalid class name and case insensitive filesystem */
public final class C05400jG {
    public static C02750ak A00;

    public static void A00(String str) {
        synchronized (C05400jG.class) {
            if (A00 == null) {
                throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
            }
        }
        System.loadLibrary(str);
    }
}
