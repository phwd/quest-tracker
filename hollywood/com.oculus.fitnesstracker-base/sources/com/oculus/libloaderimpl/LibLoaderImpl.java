package com.oculus.libloaderimpl;

import android.content.Context;
import android.util.Log;

class LibLoaderImpl {
    private static final String LOADER_BINARY = "loaderimpl";
    private static final String TAG = "LibLoaderImpl";

    private static native long nativeGetAPIFunction(Context context, Context context2);

    private static native void nativeSetClassLoader(ClassLoader classLoader);

    LibLoaderImpl() {
    }

    private static long internalGetAPIFunction(Context context, Context context2, int i, int i2) {
        Log.v(TAG, "internalGetAPIFunction: loadLibrary 'loaderimpl'");
        try {
            Log.v(TAG, "Using System.loadLibrary");
            System.loadLibrary(LOADER_BINARY);
            nativeSetClassLoader(LibLoaderImpl.class.getClassLoader());
            Log.v(TAG, "internalGetAPIFunction: calling nativeGetAPIFunction");
            return nativeGetAPIFunction(context, context2);
        } catch (UnsatisfiedLinkError e) {
            Log.v(TAG, "System.loadLibrary failed: " + e.getMessage());
            return 0;
        } catch (Exception e2) {
            Log.v(TAG, "Exception during System.loadLibrary: " + e2.getMessage());
            return 0;
        }
    }

    public static long getAPIFunction32(Context context, Context context2, int i) {
        Log.v(TAG, "Loading 32-bit LibLoaderImpl.");
        return internalGetAPIFunction(context, context2, i, 32);
    }

    public static long getAPIFunction64(Context context, Context context2, int i) {
        Log.v(TAG, "Loading 64-bit LibLoaderImpl.");
        return internalGetAPIFunction(context, context2, i, 64);
    }
}
