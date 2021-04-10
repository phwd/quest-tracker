package com.facebook.systrace;

import X.AnonymousClass89;
import X.KV;
import android.util.Log;
import com.facebook.common.util.TriState;

public class TraceDirect {
    public static final boolean sForceJavaImpl = "true".equals(AnonymousClass89.A02("debug.fbsystrace.force_java"));
    public static volatile TriState sNativeAvailable = TriState.UNSET;
    public static volatile int sPrevSoLoaderSourcesVersion = -1;
    public static final boolean sTraceLoad = "true".equals(AnonymousClass89.A02("debug.fbsystrace.trace_load"));

    public static native void nativeAsyncTraceBegin(String str, int i, long j);

    public static native void nativeAsyncTraceCancel(String str, int i);

    public static native void nativeAsyncTraceEnd(String str, int i, long j);

    public static native void nativeAsyncTraceRename(String str, String str2, int i);

    public static native void nativeAsyncTraceStageBegin(String str, int i, long j, String str2);

    public static native void nativeBeginSection(String str);

    public static native void nativeBeginSectionWithArgs(String str, String[] strArr, int i);

    public static native void nativeEndAsyncFlow(String str, int i);

    public static native void nativeEndSection();

    public static native void nativeEndSectionWithArgs(String[] strArr, int i);

    public static native void nativeSetEnabledTags(long j);

    public static native void nativeStartAsyncFlow(String str, int i);

    public static native void nativeStepAsyncFlow(String str, int i);

    public static native void nativeTraceCounter(String str, int i);

    public static native void nativeTraceInstant(String str, String str2, char c);

    public static native void nativeTraceMetadata(String str, String str2, int i);

    public static boolean checkNative() {
        int i;
        Exception exc;
        if (sNativeAvailable == TriState.UNSET) {
            if (sForceJavaImpl) {
                Log.i("TraceDirect", "Forcing java implementation.");
                sNativeAvailable = TriState.NO;
            } else {
                if (KV.A02()) {
                    synchronized (KV.class) {
                        if (KV.A00 == null) {
                            throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
                        }
                    }
                    i = KV.A00.A2u();
                } else {
                    i = -1;
                }
                if (i != sPrevSoLoaderSourcesVersion) {
                    sPrevSoLoaderSourcesVersion = i;
                    String format = String.format("Attempting to load fbsystrace.so [%d|%d].", Integer.valueOf(sPrevSoLoaderSourcesVersion), Integer.valueOf(i));
                    if (sTraceLoad) {
                        exc = new Exception();
                    } else {
                        exc = null;
                    }
                    Log.d("TraceDirect", format, exc);
                    try {
                        KV.A01("fbsystrace");
                        nativeSetEnabledTags(AnonymousClass89.A00("debug.fbsystrace.tags"));
                        nativeBeginSection("fbsystrace.so loaded");
                        nativeEndSection();
                        sNativeAvailable = TriState.YES;
                        Log.i("TraceDirect", "fbsystrace.so loaded.");
                    } catch (UnsatisfiedLinkError unused) {
                        Log.w("TraceDirect", "fbsystrace.so could not be loaded - switching to Java implementation.");
                    }
                }
            }
        }
        if (sNativeAvailable != TriState.YES) {
            return false;
        }
        return true;
    }
}
