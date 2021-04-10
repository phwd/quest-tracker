package com.oculus.panellib;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.facebook.acra.ErrorReporter;
import com.facebook.breakpad.BreakpadManager;
import com.oculus.crashreporter.JavascriptCrashReporter;
import javax.annotation.Nullable;

public abstract class CrashReporting {
    private static final String TAG = CrashReporting.class.getSimpleName();
    private static boolean mJSInitialized = false;
    private static boolean mNativeJavaInitialized = false;

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0057, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0058, code lost:
        r5 = r3;
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0067, code lost:
        r3 = th;
     */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0067 A[ExcHandler: all (th java.lang.Throwable)] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void initialize(android.content.Context r8, android.os.Handler r9, java.lang.String r10, java.lang.String r11, java.lang.String r12) {
        /*
        // Method dump skipped, instructions count: 139
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.CrashReporting.initialize(android.content.Context, android.os.Handler, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static void shutdown() {
        JavascriptCrashReporter.shutdown();
    }

    public static void setUserId(@Nullable String userId) {
        ErrorReporter.getInstance().setUserId(userId);
    }

    public static void initializeNativeCrashReporting(Context context) {
        Log.d(TAG, "Breakpad for Native crashes enabled");
        if (!BreakpadManager.isActive()) {
            BreakpadManager.start(context);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00cf, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00d0, code lost:
        r2 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00e4, code lost:
        r1 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void initializeJavaCrashReporting(android.content.Context r15, java.lang.String r16, java.lang.String r17, java.lang.String r18) {
        /*
        // Method dump skipped, instructions count: 230
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.CrashReporting.initializeJavaCrashReporting(android.content.Context, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static void initializeJSCrashReporting(Handler handler) {
        if (handler != null) {
            Log.d(TAG, "JavascriptCrashReporter is enabled");
            JavascriptCrashReporter.init(handler);
            return;
        }
        Log.w(TAG, "JavascriptCrashReporter is disabled");
    }
}
