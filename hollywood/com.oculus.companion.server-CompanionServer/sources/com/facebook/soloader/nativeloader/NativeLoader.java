package com.facebook.soloader.nativeloader;

public class NativeLoader {
    private static NativeLoaderDelegate sDelegate;

    private NativeLoader() {
    }

    public static boolean loadLibrary(String str) {
        return loadLibrary(str, 0);
    }

    public static boolean loadLibrary(String str, int i) {
        synchronized (NativeLoader.class) {
            if (sDelegate == null) {
                throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
            }
        }
        return sDelegate.loadLibrary(str, i);
    }

    public static int getSoSourcesVersion() {
        synchronized (NativeLoader.class) {
            if (sDelegate == null) {
                throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
            }
        }
        return sDelegate.getSoSourcesVersion();
    }

    public static synchronized boolean isInitialized() {
        boolean z;
        synchronized (NativeLoader.class) {
            z = sDelegate != null;
        }
        return z;
    }
}
