package com.oculus.avatar.loader;

import X.AnonymousClass006;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class EntryPoint {
    public static final String AVATAR_SDK_BINARY = "ovravatar";
    public static final String AVATAR_SDK_BINARY_64 = "ovravatar_64";
    public static String LoadedLibraryName = null;
    public static final String TAG = "EntryPoint";

    public static long loadGeneric(Context context, Context context2, int i, int i2, int i3, int i4, int i5) {
        if (load(context, context2, i, i2, i3, i4, i5, false) != null) {
            return nativeGetFunctionAccessor();
        }
        return 0;
    }

    public static long loadHorizon64(Context context, Context context2, int i, int i2, int i3, int i4, int i5) {
        if (load(context, context2, i, i2, i3, i4, i5, true) != null) {
            return nativeGetFunctionAccessor();
        }
        return 0;
    }

    public static native boolean nativeCheckVersion(int i, int i2, int i3, int i4, int i5);

    public static native long nativeGetFunctionAccessor();

    public static native void nativeSetClassLoader(ClassLoader classLoader);

    public static String load(Context context, final Context context2, int i, int i2, int i3, int i4, int i5, boolean z) {
        String str;
        if (context2 != null && context2.getPackageName().equals("com.oculus.scimitar")) {
            new Handler(context2.getMainLooper()).post(new Runnable() {
                /* class com.oculus.avatar.loader.EntryPoint.AnonymousClass1 */

                public void run() {
                    Toast.makeText(context2, "Scimitar is installed!!!!!", 1).show();
                }
            });
        }
        String str2 = LoadedLibraryName;
        if (str2 == null || str2.isEmpty()) {
            if (z) {
                str = AVATAR_SDK_BINARY_64;
            } else {
                str = AVATAR_SDK_BINARY;
            }
            System.loadLibrary(str);
            if (nativeCheckVersion(i, i2, i3, i4, i5)) {
                str2 = System.mapLibraryName(str);
                LoadedLibraryName = str2;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append(".");
                sb.append(i2);
                sb.append(".");
                sb.append(i3);
                sb.append(".");
                sb.append(i4);
                String A05 = AnonymousClass006.A05("Requested AvatarSDK version (api=", sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append(A05);
                sb2.append(", driver=");
                sb2.append(i5);
                sb2.append(") is incompatible with the ");
                throw new RuntimeException(AnonymousClass006.A05(sb2.toString(), "currently installed version."));
            }
        }
        if (str2 != null && !str2.isEmpty()) {
            nativeSetClassLoader(EntryPoint.class.getClassLoader());
        }
        return LoadedLibraryName;
    }

    public static long load32(Context context, Context context2, int i, int i2, int i3, int i4, int i5) {
        return loadGeneric(context, context2, i, i2, i3, i4, i5);
    }
}
