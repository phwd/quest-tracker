package com.facebook.systrace;

import X.AnonymousClass0I1;
import X.C03250cX;
import android.annotation.SuppressLint;
import android.util.Log;
import com.facebook.common.util.TriState;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@SuppressLint({"BadMethodUse-android.util.Log.v", "BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.i", "BadMethodUse-android.util.Log.w", "BadMethodUse-android.util.Log.e", "MissingNativeLoadLibrary", "MissingSoLoaderLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class TraceDirect {
    public static final boolean sForceJavaImpl = "true".equals(AnonymousClass0I1.A02("debug.fbsystrace.force_java"));
    public static volatile TriState sNativeAvailable = TriState.UNSET;
    public static volatile int sPrevSoLoaderSourcesVersion = -1;
    public static final boolean sTraceLoad = "true".equals(AnonymousClass0I1.A02("debug.fbsystrace.trace_load"));

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
        int i;
        if (sNativeAvailable == TriState.UNSET) {
            if (sForceJavaImpl) {
                sNativeAvailable = TriState.NO;
            } else {
                if (C03250cX.A02()) {
                    synchronized (C03250cX.class) {
                        if (C03250cX.A00 == null) {
                            throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
                        }
                    }
                    i = C03250cX.A00.A4O();
                } else {
                    i = -1;
                }
                if (i != sPrevSoLoaderSourcesVersion) {
                    sPrevSoLoaderSourcesVersion = i;
                    try {
                        C03250cX.A01("fbsystrace");
                        nativeSetEnabledTags(AnonymousClass0I1.A00("debug.fbsystrace.tags", 0));
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
