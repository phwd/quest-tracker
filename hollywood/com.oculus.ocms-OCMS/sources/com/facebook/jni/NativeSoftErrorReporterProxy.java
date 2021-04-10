package com.facebook.jni;

import com.facebook.common.errorreporting.ErrorReportingGkReader;
import com.facebook.common.errorreporting.FbErrorReporter;
import com.facebook.common.errorreporting.SoftError;
import com.facebook.common.util.TriState;
import com.facebook.proguard.annotations.DoNotStrip;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;

@DoNotStrip
public final class NativeSoftErrorReporterProxy {
    private static final int MAX_SOFT_ERROR_CACHE_SIZE = 50;
    private static final String NATIVE_SOFT_ERROR_TAG = "[Native] ";
    private static final int SEVERITY_MUSTFIX = 2;
    private static final String SEVERITY_MUSTFIX_TAG = "<level:mustfix> ";
    private static final String SEVERITY_UNKNOWN_TAG = "<level:unknown> ";
    private static final int SEVERITY_WARNING = 1;
    private static final String SEVERITY_WARNING_TAG = "<level:warning> ";
    private static ExecutorService sErrorReportingExecutorService;
    private static ErrorReportingGkReader sErrorReportingGkReader;
    private static WeakReference<FbErrorReporter> sFbErrorReporterWeakReference;
    private static final LinkedList<SoftError> sSoftErrorCache = new LinkedList<>();

    @DoNotStrip
    public static native void generateNativeSoftError();

    private static String getSeverityTag(int i) {
        return i != 1 ? i != 2 ? SEVERITY_UNKNOWN_TAG : SEVERITY_MUSTFIX_TAG : SEVERITY_WARNING_TAG;
    }

    private NativeSoftErrorReporterProxy() {
    }

    public static synchronized void registerErrorReporter(FbErrorReporter fbErrorReporter, ErrorReportingGkReader errorReportingGkReader, ExecutorService executorService) {
        synchronized (NativeSoftErrorReporterProxy.class) {
            sErrorReportingGkReader = errorReportingGkReader;
            sErrorReportingExecutorService = executorService;
            if (sFbErrorReporterWeakReference == null) {
                sFbErrorReporterWeakReference = new WeakReference<>(fbErrorReporter);
                flushSoftErrorCacheAsync();
            }
        }
    }

    @DoNotStrip
    public static void softReport(int i, String str, String str2, int i2) {
        softReport(i, str, str2, null, i2);
    }

    @DoNotStrip
    public static void softReport(int i, String str, String str2, Throwable th, int i2) {
        insertSoftErrorIntoCache(getNativeCategoryString(i, str), str2, th, i2);
        flushSoftErrorCacheAsync();
    }

    private static String getNativeCategoryString(int i, String str) {
        return NATIVE_SOFT_ERROR_TAG + getSeverityTag(i) + str;
    }

    /* access modifiers changed from: private */
    public static TriState shouldReportNativeSoftErrors() {
        ErrorReportingGkReader errorReportingGkReader = sErrorReportingGkReader;
        if (errorReportingGkReader == null) {
            return TriState.UNSET;
        }
        return errorReportingGkReader.shouldReportNativeSoftErrors();
    }

    private static synchronized void insertSoftErrorIntoCache(String str, String str2, Throwable th, int i) {
        synchronized (NativeSoftErrorReporterProxy.class) {
            synchronized (sSoftErrorCache) {
                sSoftErrorCache.addLast(SoftError.newBuilder(str, str2).setCause(th).setSamplingFrequency(i).build());
                while (sSoftErrorCache.size() >= 50) {
                    sSoftErrorCache.removeFirst();
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0007, code lost:
        r1 = com.facebook.jni.NativeSoftErrorReporterProxy.sFbErrorReporterWeakReference.get();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void flushSoftErrorCacheAsync() {
        /*
            java.lang.Class<com.facebook.jni.NativeSoftErrorReporterProxy> r0 = com.facebook.jni.NativeSoftErrorReporterProxy.class
            monitor-enter(r0)
            java.lang.ref.WeakReference<com.facebook.common.errorreporting.FbErrorReporter> r1 = com.facebook.jni.NativeSoftErrorReporterProxy.sFbErrorReporterWeakReference     // Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x003e
            java.lang.ref.WeakReference<com.facebook.common.errorreporting.FbErrorReporter> r1 = com.facebook.jni.NativeSoftErrorReporterProxy.sFbErrorReporterWeakReference     // Catch:{ all -> 0x0040 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0040 }
            com.facebook.common.errorreporting.FbErrorReporter r1 = (com.facebook.common.errorreporting.FbErrorReporter) r1     // Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x003e
            com.facebook.common.errorreporting.ErrorReportingGkReader r2 = com.facebook.jni.NativeSoftErrorReporterProxy.sErrorReportingGkReader     // Catch:{ all -> 0x0040 }
            if (r2 == 0) goto L_0x003e
            java.util.LinkedList<com.facebook.common.errorreporting.SoftError> r2 = com.facebook.jni.NativeSoftErrorReporterProxy.sSoftErrorCache     // Catch:{ all -> 0x0040 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0040 }
            if (r2 != 0) goto L_0x003e
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0040 }
            r2.<init>()     // Catch:{ all -> 0x0040 }
            java.util.LinkedList<com.facebook.common.errorreporting.SoftError> r3 = com.facebook.jni.NativeSoftErrorReporterProxy.sSoftErrorCache     // Catch:{ all -> 0x0040 }
            monitor-enter(r3)     // Catch:{ all -> 0x0040 }
            java.util.LinkedList<com.facebook.common.errorreporting.SoftError> r4 = com.facebook.jni.NativeSoftErrorReporterProxy.sSoftErrorCache     // Catch:{ all -> 0x003b }
            r2.addAll(r4)     // Catch:{ all -> 0x003b }
            java.util.LinkedList<com.facebook.common.errorreporting.SoftError> r4 = com.facebook.jni.NativeSoftErrorReporterProxy.sSoftErrorCache     // Catch:{ all -> 0x003b }
            r4.clear()     // Catch:{ all -> 0x003b }
            monitor-exit(r3)     // Catch:{ all -> 0x003b }
            java.util.concurrent.ExecutorService r3 = com.facebook.jni.NativeSoftErrorReporterProxy.sErrorReportingExecutorService
            com.facebook.jni.NativeSoftErrorReporterProxy$1 r4 = new com.facebook.jni.NativeSoftErrorReporterProxy$1
            r4.<init>(r2, r1)
            r3.execute(r4)
            goto L_0x003e
        L_0x003b:
            r1 = move-exception
            monitor-exit(r3)
            throw r1
        L_0x003e:
            monitor-exit(r0)
            return
        L_0x0040:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.jni.NativeSoftErrorReporterProxy.flushSoftErrorCacheAsync():void");
    }

    static synchronized List<SoftError> getCachedSoftErrors() {
        ArrayList arrayList;
        synchronized (NativeSoftErrorReporterProxy.class) {
            arrayList = new ArrayList(sSoftErrorCache);
        }
        return arrayList;
    }
}
