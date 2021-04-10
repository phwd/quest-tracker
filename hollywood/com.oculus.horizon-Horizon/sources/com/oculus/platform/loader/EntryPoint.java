package com.oculus.platform.loader;

import android.content.Context;

public class EntryPoint {
    public static String LoadedLibraryName = null;
    public static final String PLATFORM_SDK_32_BINARY = "ovrplatform";
    public static final String PLATFORM_SDK_64_BINARY = "ovrplatform_64";
    public static final String PLATFORM_SDK_P2P_32_BINARY = "oculus_p2p";
    public static final String PLATFORM_SDK_P2P_64_BINARY = "oculus_p2p_64";
    public static final String TAG = "EntryPoint";

    public static long load32(Context context, Context context2, int i, int i2, int i3, int i4, int i5) {
        if (load(context, context2, i, i2, i3, i4, i5, true) != null) {
            return nativeGetFunctionAccessor();
        }
        return 0;
    }

    public static long load64(Context context, Context context2, int i, int i2, int i3, int i4, int i5) {
        if (load(context, context2, i, i2, i3, i4, i5, false) != null) {
            return nativeGetFunctionAccessor();
        }
        return 0;
    }

    public static native boolean nativeCheckVersion(int i, int i2, int i3, int i4, int i5);

    public static native long nativeGetFunctionAccessor();

    public static native void nativeSetClassLoader(ClassLoader classLoader);

    public static String load(Context context, Context context2, int i, int i2, int i3, int i4, int i5, boolean z) {
        String str = LoadedLibraryName;
        if (str == null || str.isEmpty()) {
            String str2 = PLATFORM_SDK_32_BINARY;
            if (z) {
                System.loadLibrary(PLATFORM_SDK_P2P_32_BINARY);
                System.loadLibrary(str2);
            } else {
                System.loadLibrary(PLATFORM_SDK_P2P_64_BINARY);
                System.loadLibrary(PLATFORM_SDK_64_BINARY);
            }
            if (nativeCheckVersion(i, i2, i3, i4, i5)) {
                if (!z) {
                    str2 = PLATFORM_SDK_64_BINARY;
                }
                str = System.mapLibraryName(str2);
                LoadedLibraryName = str;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append(".");
                sb.append(i2);
                sb.append(".");
                sb.append(i3);
                sb.append(".");
                sb.append(i4);
                String obj = sb.toString();
                StringBuilder sb2 = new StringBuilder("Requested PlatformSDK version (api=");
                sb2.append(obj);
                sb2.append(", driver=");
                sb2.append(i5);
                sb2.append(") is incompatible with the currently installed version.");
                throw new RuntimeException(sb2.toString());
            }
        }
        if (str != null && !str.isEmpty()) {
            nativeSetClassLoader(EntryPoint.class.getClassLoader());
        }
        return LoadedLibraryName;
    }
}
