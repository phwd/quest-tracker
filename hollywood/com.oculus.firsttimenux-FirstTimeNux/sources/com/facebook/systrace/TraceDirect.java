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
    private static final String TAG = TraceDirect.class.getSimpleName();
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
        Exception exc;
        if (sNativeAvailable == TriState.UNSET) {
            if (sForceJavaImpl) {
                Log.i(TAG, "Forcing java implementation.");
                sNativeAvailable = TriState.NO;
            } else {
                int currentSoLoaderSourcesVersion = NativeLoader.isInitialized() ? NativeLoader.getSoSourcesVersion() : -1;
                if (currentSoLoaderSourcesVersion != sPrevSoLoaderSourcesVersion) {
                    sPrevSoLoaderSourcesVersion = currentSoLoaderSourcesVersion;
                    String str = TAG;
                    String format = String.format("Attempting to load fbsystrace.so [%d|%d].", Integer.valueOf(sPrevSoLoaderSourcesVersion), Integer.valueOf(currentSoLoaderSourcesVersion));
                    if (sTraceLoad) {
                        exc = new Exception();
                    } else {
                        exc = null;
                    }
                    Log.d(str, format, exc);
                    try {
                        NativeLoader.loadLibrary("fbsystrace");
                        nativeSetEnabledTags(TraceConfig.computeTraceTags());
                        nativeBeginSection("fbsystrace.so loaded");
                        nativeEndSection();
                        sNativeAvailable = TriState.YES;
                        Log.i(TAG, "fbsystrace.so loaded.");
                    } catch (UnsatisfiedLinkError e) {
                        Log.w(TAG, "fbsystrace.so could not be loaded - switching to Java implementation.");
                    }
                }
            }
        }
        return sNativeAvailable == TriState.YES;
    }

    public static void beginSection(String sectionName) {
        if (checkNative()) {
            nativeBeginSection(sectionName);
        } else {
            TraceDirectJavaImpl.beginSection(sectionName);
        }
    }

    public static void beginSectionWithArgs(String sectionName, String[] args, int argsLength) {
        if (checkNative()) {
            nativeBeginSectionWithArgs(sectionName, args, argsLength);
        } else {
            TraceDirectJavaImpl.beginSectionWithArgs(sectionName, args, argsLength);
        }
    }

    public static void endSection() {
        if (checkNative()) {
            nativeEndSection();
        } else {
            TraceDirectJavaImpl.endSection();
        }
    }

    public static void endSectionWithArgs(String[] args, int argsLength) {
        if (checkNative()) {
            nativeEndSectionWithArgs(args, argsLength);
        } else {
            TraceDirectJavaImpl.endSectionWithArgs(args, argsLength);
        }
    }

    public static void traceCounter(String counterName, int counterValue) {
        if (checkNative()) {
            nativeTraceCounter(counterName, counterValue);
        } else {
            TraceDirectJavaImpl.traceCounter(counterName, counterValue);
        }
    }

    public static void asyncTraceBegin(String sectionName, int cookie, long deltaNanos) {
        if (checkNative()) {
            nativeAsyncTraceBegin(sectionName, cookie, deltaNanos);
        } else {
            TraceDirectJavaImpl.asyncTraceBegin(sectionName, cookie, deltaNanos);
        }
    }

    public static void asyncTraceStageBegin(String sectionName, int cookie, long deltaNanos, String stageName) {
        if (checkNative()) {
            nativeAsyncTraceStageBegin(sectionName, cookie, deltaNanos, stageName);
        } else {
            TraceDirectJavaImpl.asyncTraceStageBegin(sectionName, cookie, deltaNanos, stageName);
        }
    }

    public static void asyncTraceEnd(String sectionName, int cookie, long deltaNanos) {
        if (checkNative()) {
            nativeAsyncTraceEnd(sectionName, cookie, deltaNanos);
        } else {
            TraceDirectJavaImpl.asyncTraceEnd(sectionName, cookie, deltaNanos);
        }
    }

    public static void asyncTraceCancel(String sectionName, int cookie) {
        if (checkNative()) {
            nativeAsyncTraceCancel(sectionName, cookie);
        } else {
            TraceDirectJavaImpl.asyncTraceCancel(sectionName, cookie);
        }
    }

    public static void asyncTraceRename(String oldSectionName, String newSectionName, int cookie) {
        if (checkNative()) {
            nativeAsyncTraceRename(oldSectionName, newSectionName, cookie);
        } else {
            TraceDirectJavaImpl.asyncTraceRename(oldSectionName, newSectionName, cookie);
        }
    }

    public static void startAsyncFlow(String sectionName, int cookie) {
        if (checkNative()) {
            nativeStartAsyncFlow(sectionName, cookie);
        } else {
            TraceDirectJavaImpl.startAsyncFlow(sectionName, cookie);
        }
    }

    public static void stepAsyncFlow(String sectionName, int cookie) {
        if (checkNative()) {
            nativeStepAsyncFlow(sectionName, cookie);
        } else {
            TraceDirectJavaImpl.stepAsyncFlow(sectionName, cookie);
        }
    }

    public static void endAsyncFlow(String sectionName, int cookie) {
        if (checkNative()) {
            nativeEndAsyncFlow(sectionName, cookie);
        } else {
            TraceDirectJavaImpl.endAsyncFlow(sectionName, cookie);
        }
    }

    public static void traceMetadata(String name, String value, int tid) {
        if (checkNative()) {
            nativeTraceMetadata(name, value, tid);
        } else {
            TraceDirectJavaImpl.traceMetadata(name, value, tid);
        }
    }

    public static void traceInstant(String category, String title, char scope) {
        if (checkNative()) {
            nativeTraceInstant(category, title, scope);
        } else {
            TraceDirectJavaImpl.traceInstant(category, title, scope);
        }
    }

    public static void setEnabledTags(long enabledTags) {
        if (checkNative()) {
            nativeSetEnabledTags(enabledTags);
        }
    }
}
