package com.oculus.systemdriver;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class DriverLoader {
    private static final String FEATURE_STANDALONE_VR = "oculus.hardware.standalone_vr";
    private static final boolean FORCE_LOAD_LINKER = false;
    private static final String LINKER_BINARY = "vrapilinker";
    private static final String LINKER_BINARY_NAME = "libvrapilinker.so";
    private static final boolean LOAD_IN_PLACE = true;
    private static final String TAG = "VrDriver";
    private static final String VRRUNTIME_BINARY = "vrapiimpl";

    private static native boolean nativeCheckVersion(int i, int i2, int i3, int i4, int i5);

    private static native long nativeGetFunctionAccessor(Context context, Context context2, boolean z);

    private static native long nativeGetProcAddr(String str);

    private static native boolean nativeSetClassLoader(ClassLoader classLoader);

    private static native void nativeSetVrApiLayers(long j);

    DriverLoader() {
    }

    private static void copyFile(File src, File dst) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(dst);
            byte[] buffer = new byte[4096];
            while (true) {
                int length = is.read(buffer);
                if (length > 0) {
                    os.write(buffer, 0, length);
                } else {
                    return;
                }
            }
        } finally {
            is.close();
            os.close();
        }
    }

    private static boolean fileExists(String path) {
        return new File(path).isFile();
    }

    private static boolean checkForNamespacedLinker() {
        if (Build.VERSION.SDK_INT >= 24) {
            return LOAD_IN_PLACE;
        }
        if (Build.VERSION.SDK_INT == 23 && Build.VERSION.CODENAME.equals("N")) {
            return LOAD_IN_PLACE;
        }
        return false;
    }

    private static boolean isStandaloneDevice(Context context) {
        return context.getPackageManager().hasSystemFeature(FEATURE_STANDALONE_VR);
    }

    private static long loadInternal(Context appContext, Context driverContext, int productVersion, int majorVersion, int minorVersion, int patchVersion, int driverVersion) {
        System.loadLibrary(VRRUNTIME_BINARY);
        if (nativeCheckVersion(productVersion, majorVersion, minorVersion, patchVersion, driverVersion)) {
            return nativeGetFunctionAccessor(appContext, driverContext, nativeSetClassLoader(DriverLoader.class.getClassLoader()) ^ LOAD_IN_PLACE);
        }
        throw new RuntimeException("Requested API version (api=" + (productVersion + "." + majorVersion + "." + minorVersion + "." + patchVersion) + ", driver=" + driverVersion + ") is incompatible with the currently installed version.");
    }

    private static long loadInternalExt(Context appContext, Context driverContext, int productVersion, int majorVersion, int minorVersion, int patchVersion, int driverVersion, long initParms) {
        System.loadLibrary(VRRUNTIME_BINARY);
        if (nativeCheckVersion(productVersion, majorVersion, minorVersion, patchVersion, driverVersion)) {
            boolean alreadyInitialized = nativeSetClassLoader(DriverLoader.class.getClassLoader()) ^ LOAD_IN_PLACE;
            nativeSetVrApiLayers(initParms);
            return nativeGetFunctionAccessor(appContext, driverContext, alreadyInitialized);
        }
        throw new RuntimeException("Requested API version (api=" + (productVersion + "." + majorVersion + "." + minorVersion + "." + patchVersion) + ", driver=" + driverVersion + ") is incompatible with the currently installed version.");
    }

    private static String loadLegacyInternal(Context appContext, Context driverContext, int productVersion, int majorVersion, int minorVersion, int patchVersion, int driverVersion) {
        if (loadInternal(appContext, driverContext, productVersion, majorVersion, minorVersion, patchVersion, driverVersion) == 0) {
            throw new RuntimeException("Failed to load vrapiimpl");
        } else if (!checkForNamespacedLinker()) {
            return System.mapLibraryName(VRRUNTIME_BINARY);
        } else {
            Log.v(TAG, "!!! Android-N workaround mode, using VrApiLinker !!!");
            try {
                ApplicationInfo appInfo = driverContext.getApplicationInfo();
                String srcLibPath = appInfo.nativeLibraryDir + "/" + LINKER_BINARY_NAME;
                Log.v(TAG, "Attempting to load in-place...");
                String loadLibPath = srcLibPath;
                if (!fileExists(srcLibPath) && isStandaloneDevice(driverContext)) {
                    loadLibPath = appInfo.publicSourceDir + "!/lib/armeabi-v7a/" + LINKER_BINARY_NAME;
                }
                Log.v(TAG, "Load path: " + loadLibPath);
                return loadLibPath;
            } catch (Exception e) {
                throw new RuntimeException("Failed to load vrapilinker", e);
            }
        }
    }

    public static String loadLegacy(Context appContext, Context driverContext, int productVersion, int majorVersion, int minorVersion, int patchVersion, int driverVersion) {
        Log.v(TAG, "Loading Legacy VrDriver.");
        return loadLegacyInternal(appContext, driverContext, productVersion, majorVersion, minorVersion, patchVersion, driverVersion);
    }

    public static long load32(Context appContext, Context driverContext, int productVersion, int majorVersion, int minorVersion, int patchVersion, int driverVersion) {
        Log.v(TAG, "Loading 32-bit VrDriver.");
        return loadInternal(appContext, driverContext, productVersion, majorVersion, minorVersion, patchVersion, driverVersion);
    }

    public static long load64(Context appContext, Context driverContext, int productVersion, int majorVersion, int minorVersion, int patchVersion, int driverVersion) {
        Log.v(TAG, "Loading 64-bit VrDriver.");
        return loadInternal(appContext, driverContext, productVersion, majorVersion, minorVersion, patchVersion, driverVersion);
    }

    public static long load32Ext(Context appContext, Context driverContext, int productVersion, int majorVersion, int minorVersion, int patchVersion, int driverVersion, long initParms) {
        Log.v(TAG, "Loading Extended 32-bit VrDriver.");
        return loadInternalExt(appContext, driverContext, productVersion, majorVersion, minorVersion, patchVersion, driverVersion, initParms);
    }

    public static long load64Ext(Context appContext, Context driverContext, int productVersion, int majorVersion, int minorVersion, int patchVersion, int driverVersion, long initParms) {
        Log.v(TAG, "Loading Extended 64-bit VrDriver.");
        return loadInternalExt(appContext, driverContext, productVersion, majorVersion, minorVersion, patchVersion, driverVersion, initParms);
    }

    public static void loadXrRuntime(String runtimeName) {
        try {
            Log.d(TAG, "Loading xr runtime " + runtimeName);
            System.loadLibrary(runtimeName);
        } catch (UnsatisfiedLinkError e) {
            throw new RuntimeException("Failed to link VrRuntime library.");
        }
    }

    public static void loadXrRuntime() {
        loadXrRuntime(VRRUNTIME_BINARY);
    }

    public static long getProcAddr(String functionName) {
        return nativeGetProcAddr(functionName);
    }
}
