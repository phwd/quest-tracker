package com.oculus.panellib;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.facebook.acra.ErrorReporter;
import com.facebook.breakpad.BreakpadManager;
import com.oculus.crashreporter.JavascriptCrashReporter;
import javax.annotation.Nullable;

public abstract class CrashReporting {
    public static final String TAG = "CrashReporting";
    public static boolean mJSInitialized;
    public static boolean mNativeJavaInitialized;

    public static void shutdown() {
        JavascriptCrashReporter.mHandler = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0042, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0046, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void initialize(android.content.Context r6, android.os.Handler r7, java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            java.lang.String r5 = "CrashReporting"
            java.lang.String r0 = "initialize"
            com.oculus.panellib.SystraceBlock r4 = new com.oculus.panellib.SystraceBlock
            r4.<init>(r5, r0)
            boolean r0 = com.oculus.panellib.CrashReporting.mNativeJavaInitialized     // Catch:{ all -> 0x0040 }
            r3 = 1
            if (r0 != 0) goto L_0x0033
            com.oculus.panellib.CrashReporting.mNativeJavaInitialized = r3     // Catch:{ all -> 0x0040 }
            java.lang.String r0 = "SoLoader"
            com.oculus.panellib.SystraceBlock r2 = new com.oculus.panellib.SystraceBlock     // Catch:{ all -> 0x0040 }
            r2.<init>(r5, r0)     // Catch:{ all -> 0x0040 }
            r0 = 2
            X.AnonymousClass0l0.A04(r6, r0)     // Catch:{ IOException -> 0x001c }
            goto L_0x002a
        L_0x001c:
            r1 = move-exception
            java.lang.String r0 = "IOException during init"
            android.util.Log.e(r5, r0, r1)     // Catch:{ all -> 0x0023 }
            goto L_0x002a
        L_0x0023:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0025 }
        L_0x0025:
            r0 = move-exception
            r2.close()     // Catch:{ all -> 0x0029 }
        L_0x0029:
            throw r0
        L_0x002a:
            r2.close()
            initializeNativeCrashReporting(r6)
            initializeJavaCrashReporting(r6, r8, r9, r10)
        L_0x0033:
            boolean r0 = com.oculus.panellib.CrashReporting.mJSInitialized
            if (r0 != 0) goto L_0x003c
            com.oculus.panellib.CrashReporting.mJSInitialized = r3
            initializeJSCrashReporting(r7)
        L_0x003c:
            r4.close()
            return
        L_0x0040:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0042 }
        L_0x0042:
            r0 = move-exception
            r4.close()     // Catch:{ all -> 0x0046 }
        L_0x0046:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.CrashReporting.initialize(android.content.Context, android.os.Handler, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static void initializeJSCrashReporting(Handler handler) {
        if (handler != null) {
            JavascriptCrashReporter.init(handler);
        } else {
            Log.w(TAG, "JavascriptCrashReporter is disabled");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void initializeJavaCrashReporting(android.content.Context r13, java.lang.String r14, java.lang.String r15, java.lang.String r16) {
        /*
        // Method dump skipped, instructions count: 127
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.CrashReporting.initializeJavaCrashReporting(android.content.Context, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static void initializeNativeCrashReporting(Context context) {
        if (!BreakpadManager.isActive()) {
            BreakpadManager.start(context);
        }
    }

    public static void setUserId(@Nullable String str) {
        ErrorReporter.getInstance().setUserId(str);
    }
}
