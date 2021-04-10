package com.facebook.systrace;

import android.os.Build;
import com.facebook.androidinternals.android.os.TraceInternal;

public final class SystraceEnabledDetector {
    public static boolean computeIsTracing() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 18) {
            return TraceInternal.isTagEnabled(TraceInternal.TRACE_TAG_APP);
        }
        if (i >= 16) {
            return TraceConfigJB.isTracing();
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002a, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002d, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0030, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0035, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0036, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0039, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getMyCommandLine() {
        /*
            java.io.FileReader r0 = new java.io.FileReader     // Catch:{ IOException -> 0x003a }
            java.lang.String r1 = "/proc/self/cmdline"
            r0.<init>(r1)     // Catch:{ IOException -> 0x003a }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x002e }
            r1.<init>(r0)     // Catch:{ all -> 0x002e }
            java.lang.String r2 = r1.readLine()     // Catch:{ all -> 0x0022 }
            r3 = 0
            int r4 = r2.indexOf(r3)     // Catch:{ all -> 0x0022 }
            if (r4 < 0) goto L_0x001b
            java.lang.String r2 = r2.substring(r3, r4)     // Catch:{ all -> 0x0022 }
        L_0x001b:
            r1.close()
            r0.close()
            return r2
        L_0x0022:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0024 }
        L_0x0024:
            r3 = move-exception
            r1.close()     // Catch:{ all -> 0x0029 }
            goto L_0x002d
        L_0x0029:
            r1 = move-exception
            r2.addSuppressed(r1)
        L_0x002d:
            throw r3
        L_0x002e:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x0039:
            throw r2
        L_0x003a:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.systrace.SystraceEnabledDetector.getMyCommandLine():java.lang.String");
    }
}
