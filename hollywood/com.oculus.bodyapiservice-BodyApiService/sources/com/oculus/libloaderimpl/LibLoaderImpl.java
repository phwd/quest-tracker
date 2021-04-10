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

    private static long internalGetAPIFunction(Context appContext, Context driverContext, int version, int ABI) {
        Log.v(TAG, "internalGetAPIFunction: loadLibrary 'loaderimpl'");
        try {
            Log.v(TAG, "Using System.loadLibrary");
            System.loadLibrary(LOADER_BINARY);
            nativeSetClassLoader(LibLoaderImpl.class.getClassLoader());
            Log.v(TAG, "internalGetAPIFunction: calling nativeGetAPIFunction");
            return nativeGetAPIFunction(appContext, driverContext);
        } catch (UnsatisfiedLinkError ex) {
            Log.v(TAG, "System.loadLibrary failed: " + ex.getMessage());
            return 0;
        } catch (Exception ex2) {
            Log.v(TAG, "Exception during System.loadLibrary: " + ex2.getMessage());
            return 0;
        }
    }

    public static long getAPIFunction32(Context appContext, Context driverContext, int version) {
        Log.v(TAG, "Loading 32-bit LibLoaderImpl.");
        return internalGetAPIFunction(appContext, driverContext, version, 32);
    }

    public static long getAPIFunction64(Context appContext, Context driverContext, int version) {
        Log.v(TAG, "Loading 64-bit LibLoaderImpl.");
        return internalGetAPIFunction(appContext, driverContext, version, 64);
    }
}
