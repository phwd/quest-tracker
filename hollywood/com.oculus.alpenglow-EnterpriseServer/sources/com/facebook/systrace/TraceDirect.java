package com.facebook.systrace;

import X.AnonymousClass0IH;
import X.C05400jG;
import android.annotation.SuppressLint;
import android.util.Log;
import com.facebook.common.util.TriState;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@SuppressLint({"BadMethodUse-android.util.Log.v", "BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.i", "BadMethodUse-android.util.Log.w", "BadMethodUse-android.util.Log.e", "MissingNativeLoadLibrary", "MissingSoLoaderLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class TraceDirect {
    public static final boolean sForceJavaImpl = "true".equals(AnonymousClass0IH.A02("debug.fbsystrace.force_java"));
    public static volatile TriState sNativeAvailable = TriState.UNSET;
    public static volatile int sPrevSoLoaderSourcesVersion = -1;
    public static final boolean sTraceLoad = "true".equals(AnonymousClass0IH.A02("debug.fbsystrace.trace_load"));

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

    @SuppressLint({"LogMethodNoExceptionInCatch", "StringFormatUse", "ThrowException"})
    public static boolean checkNative() {
        boolean z;
        int i;
        if (sNativeAvailable == TriState.UNSET) {
            if (sForceJavaImpl) {
                sNativeAvailable = TriState.NO;
            } else {
                synchronized (C05400jG.class) {
                    z = false;
                    if (C05400jG.A00 != null) {
                        z = true;
                    }
                }
                if (z) {
                    synchronized (C05400jG.class) {
                        if (C05400jG.A00 == null) {
                            throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
                        }
                    }
                    i = 0;
                } else {
                    i = -1;
                }
                if (i != sPrevSoLoaderSourcesVersion) {
                    sPrevSoLoaderSourcesVersion = i;
                    try {
                        C05400jG.A00("fbsystrace");
                        nativeSetEnabledTags(AnonymousClass0IH.A00("debug.fbsystrace.tags"));
                        nativeBeginSection("fbsystrace.so loaded");
                        nativeEndSection();
                        sNativeAvailable = TriState.YES;
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
