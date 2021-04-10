package com.facebook.soloader.a;

public class a {
    private static b a;

    private a() {
    }

    public static synchronized void a(b bVar) {
        synchronized (a.class) {
            if (!a()) {
                b(bVar);
            }
        }
    }

    private static synchronized boolean a() {
        boolean z;
        synchronized (a.class) {
            z = a != null;
        }
        return z;
    }

    public static boolean a(String str) {
        return a(str, 0);
    }

    private static boolean a(String str, int i) {
        synchronized (a.class) {
            if (a == null) {
                throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
            }
        }
        return a.a(str, 0);
    }

    private static synchronized void b(b bVar) {
        synchronized (a.class) {
            if (a == null) {
                a = bVar;
            } else {
                throw new IllegalStateException("Cannot re-initialize NativeLoader.");
            }
        }
    }
}
