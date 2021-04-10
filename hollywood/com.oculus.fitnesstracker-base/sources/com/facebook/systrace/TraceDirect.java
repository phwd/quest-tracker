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

    private static native void nativeBeginSection(String str);

    private static native void nativeEndSection();

    private static native void nativeSetEnabledTags(long j);

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
        return sNativeAvailable == TriState.YES;
    }

    public static void beginSection(String str) {
        if (checkNative()) {
            nativeBeginSection(str);
        } else {
            TraceDirectJavaImpl.beginSection(str);
        }
    }

    public static void endSection() {
        if (checkNative()) {
            nativeEndSection();
        } else {
            TraceDirectJavaImpl.endSection();
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
