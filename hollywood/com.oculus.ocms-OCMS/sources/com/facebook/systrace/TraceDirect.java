package com.facebook.systrace;

import android.annotation.SuppressLint;
import android.util.Log;
import com.facebook.androidinternals.android.os.SystemPropertiesInternal;
import com.facebook.common.util.TriState;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;

/* access modifiers changed from: package-private */
@DoNotStrip
@SuppressLint({"BadMethodUse-android.util.Log.v", "BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.i", "BadMethodUse-android.util.Log.w", "BadMethodUse-android.util.Log.e", "MissingNativeLoadLibrary", "MissingSoLoaderLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class TraceDirect {
    private static final String TAG = "TraceDirect";
    private static final boolean sForceJavaImpl = "true".equals(SystemPropertiesInternal.get("debug.fbsystrace.force_java"));
    private static volatile TriState sNativeAvailable = TriState.UNSET;
    private static volatile int sPrevSoLoaderSourcesVersion = -1;
    private static final boolean sTraceLoad = "true".equals(SystemPropertiesInternal.get("debug.fbsystrace.trace_load"));

    private static native void nativeAsyncTraceBegin(String str, int i, long j);

    private static native void nativeAsyncTraceCancel(String str, int i);

    private static native void nativeAsyncTraceEnd(String str, int i, long j);

    private static native void nativeAsyncTraceRename(String str, String str2, int i);

    private static native void nativeAsyncTraceStageBegin(String str, int i, long j, String str2);

    private static native void nativeBeginSection(String str);

    private static native void nativeBeginSectionWithArgs(String str, String[] strArr, int i);

    private static native void nativeEndAsyncFlow(String str, int i);

    private static native void nativeEndSection();

    private static native void nativeEndSectionWithArgs(String[] strArr, int i);

    private static native void nativeSetEnabledTags(long j);

    private static native void nativeStartAsyncFlow(String str, int i);

    private static native void nativeStepAsyncFlow(String str, int i);

    private static native void nativeTraceCounter(String str, int i);

    private static native void nativeTraceInstant(String str, String str2, char c);

    private static native void nativeTraceMetadata(String str, String str2, int i);

    TraceDirect() {
    }

    @SuppressLint({"LogMethodNoExceptionInCatch", "StringFormatUse", "ThrowException"})
    private static boolean checkNative() {
        if (sNativeAvailable == TriState.UNSET) {
            if (sForceJavaImpl) {
                Log.i(TAG, "Forcing java implementation.");
                sNativeAvailable = TriState.NO;
            } else {
                int soSourcesVersion = NativeLoader.isInitialized() ? NativeLoader.getSoSourcesVersion() : -1;
                if (soSourcesVersion != sPrevSoLoaderSourcesVersion) {
                    sPrevSoLoaderSourcesVersion = soSourcesVersion;
                    Log.d(TAG, String.format("Attempting to load fbsystrace.so [%d|%d].", Integer.valueOf(sPrevSoLoaderSourcesVersion), Integer.valueOf(soSourcesVersion)), sTraceLoad ? new Exception() : null);
                    try {
                        NativeLoader.loadLibrary("fbsystrace");
                        nativeSetEnabledTags(TraceConfig.computeTraceTags());
                        nativeBeginSection("fbsystrace.so loaded");
                        nativeEndSection();
                        sNativeAvailable = TriState.YES;
                        Log.i(TAG, "fbsystrace.so loaded.");
                    } catch (UnsatisfiedLinkError unused) {
                        Log.w(TAG, "fbsystrace.so could not be loaded - switching to Java implementation.");
                    }
                }
            }
        }
        if (sNativeAvailable == TriState.YES) {
            return true;
        }
        return false;
    }

    public static void beginSection(String str) {
        if (checkNative()) {
            nativeBeginSection(str);
        } else {
            TraceDirectJavaImpl.beginSection(str);
        }
    }

    public static void beginSectionWithArgs(String str, String[] strArr, int i) {
        if (checkNative()) {
            nativeBeginSectionWithArgs(str, strArr, i);
        } else {
            TraceDirectJavaImpl.beginSectionWithArgs(str, strArr, i);
        }
    }

    public static void endSection() {
        if (checkNative()) {
            nativeEndSection();
        } else {
            TraceDirectJavaImpl.endSection();
        }
    }

    public static void endSectionWithArgs(String[] strArr, int i) {
        if (checkNative()) {
            nativeEndSectionWithArgs(strArr, i);
        } else {
            TraceDirectJavaImpl.endSectionWithArgs(strArr, i);
        }
    }

    public static void traceCounter(String str, int i) {
        if (checkNative()) {
            nativeTraceCounter(str, i);
        } else {
            TraceDirectJavaImpl.traceCounter(str, i);
        }
    }

    public static void asyncTraceBegin(String str, int i, long j) {
        if (checkNative()) {
            nativeAsyncTraceBegin(str, i, j);
        } else {
            TraceDirectJavaImpl.asyncTraceBegin(str, i, j);
        }
    }

    public static void asyncTraceStageBegin(String str, int i, long j, String str2) {
        if (checkNative()) {
            nativeAsyncTraceStageBegin(str, i, j, str2);
        } else {
            TraceDirectJavaImpl.asyncTraceStageBegin(str, i, j, str2);
        }
    }

    public static void asyncTraceEnd(String str, int i, long j) {
        if (checkNative()) {
            nativeAsyncTraceEnd(str, i, j);
        } else {
            TraceDirectJavaImpl.asyncTraceEnd(str, i, j);
        }
    }

    public static void asyncTraceCancel(String str, int i) {
        if (checkNative()) {
            nativeAsyncTraceCancel(str, i);
        } else {
            TraceDirectJavaImpl.asyncTraceCancel(str, i);
        }
    }

    public static void asyncTraceRename(String str, String str2, int i) {
        if (checkNative()) {
            nativeAsyncTraceRename(str, str2, i);
        } else {
            TraceDirectJavaImpl.asyncTraceRename(str, str2, i);
        }
    }

    public static void startAsyncFlow(String str, int i) {
        if (checkNative()) {
            nativeStartAsyncFlow(str, i);
        } else {
            TraceDirectJavaImpl.startAsyncFlow(str, i);
        }
    }

    public static void stepAsyncFlow(String str, int i) {
        if (checkNative()) {
            nativeStepAsyncFlow(str, i);
        } else {
            TraceDirectJavaImpl.stepAsyncFlow(str, i);
        }
    }

    public static void endAsyncFlow(String str, int i) {
        if (checkNative()) {
            nativeEndAsyncFlow(str, i);
        } else {
            TraceDirectJavaImpl.endAsyncFlow(str, i);
        }
    }

    public static void traceMetadata(String str, String str2, int i) {
        if (checkNative()) {
            nativeTraceMetadata(str, str2, i);
        } else {
            TraceDirectJavaImpl.traceMetadata(str, str2, i);
        }
    }

    public static void traceInstant(String str, String str2, char c) {
        if (checkNative()) {
            nativeTraceInstant(str, str2, c);
        } else {
            TraceDirectJavaImpl.traceInstant(str, str2, c);
        }
    }

    public static void setEnabledTags(long j) {
        if (checkNative()) {
            nativeSetEnabledTags(j);
        }
    }
}
