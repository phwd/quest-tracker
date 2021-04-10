package com.facebook.systrace;

import android.annotation.SuppressLint;
import android.util.Log;
import com.facebook.androidinternals.android.os.SystemPropertiesInternal;
import com.facebook.common.util.TriState;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;

/* access modifiers changed from: package-private */
@DoNotStrip
@SuppressLint({"BadMethodUse-android.util.Log.v", "BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.i", "BadMethodUse-android.util.Log.w", "BadMethodUse-android.util.Log.e", "MissingNativeLoadLibrary", "MissingSoLoaderLibrary"})
public class TraceDirect {
    private static final String TAG = TraceDirect.class.getSimpleName();
    private static final boolean sForceJavaImpl = "true".equals(SystemPropertiesInternal.get("debug.fbsystrace.force_java"));
    private static volatile TriState sNativeAvailable = TriState.UNSET;
    private static volatile int sPrevSoLoaderSourcesVersion = -1;
    private static final boolean sTraceLoad = "true".equals(SystemPropertiesInternal.get("debug.fbsystrace.trace_load"));

    private static native void nativeBeginSection(String str);

    private static native void nativeEndSection();

    private static native void nativeSetEnabledTags(long j);

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

    public static void endSection() {
        if (checkNative()) {
            nativeEndSection();
        } else {
            TraceDirectJavaImpl.endSection();
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
