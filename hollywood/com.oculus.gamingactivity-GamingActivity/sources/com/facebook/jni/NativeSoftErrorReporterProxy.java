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

    private NativeSoftErrorReporterProxy() {
    }

    public static synchronized void registerErrorReporter(FbErrorReporter fbErrorReporter, ErrorReportingGkReader errorReportingGkReader, ExecutorService errorReportingExecutorService) {
        synchronized (NativeSoftErrorReporterProxy.class) {
            sErrorReportingGkReader = errorReportingGkReader;
            sErrorReportingExecutorService = errorReportingExecutorService;
            if (sFbErrorReporterWeakReference == null) {
                sFbErrorReporterWeakReference = new WeakReference<>(fbErrorReporter);
                flushSoftErrorCacheAsync();
            }
        }
    }

    @DoNotStrip
    public static void softReport(int severity, String category, String message, int samplingFrequency) {
        softReport(severity, category, message, null, samplingFrequency);
    }

    @DoNotStrip
    public static void softReport(int severity, String category, String message, Throwable cause, int samplingFrequency) {
        insertSoftErrorIntoCache(getNativeCategoryString(severity, category), message, cause, samplingFrequency);
        flushSoftErrorCacheAsync();
    }

    private static String getSeverityTag(int severity) {
        switch (severity) {
            case 1:
                return SEVERITY_WARNING_TAG;
            case 2:
                return SEVERITY_MUSTFIX_TAG;
            default:
                return SEVERITY_UNKNOWN_TAG;
        }
    }

    private static String getNativeCategoryString(int severity, String category) {
        return NATIVE_SOFT_ERROR_TAG + getSeverityTag(severity) + category;
    }

    /* access modifiers changed from: private */
    public static TriState shouldReportNativeSoftErrors() {
        if (sErrorReportingGkReader == null) {
            return TriState.UNSET;
        }
        return sErrorReportingGkReader.shouldReportNativeSoftErrors();
    }

    private static synchronized void insertSoftErrorIntoCache(String category, String message, Throwable cause, int samplingFrequency) {
        synchronized (NativeSoftErrorReporterProxy.class) {
            synchronized (sSoftErrorCache) {
                sSoftErrorCache.addLast(SoftError.newBuilder(category, message).setCause(cause).setSamplingFrequency(samplingFrequency).build());
                while (sSoftErrorCache.size() >= 50) {
                    sSoftErrorCache.removeFirst();
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0007, code lost:
        r0 = com.facebook.jni.NativeSoftErrorReporterProxy.sFbErrorReporterWeakReference.get();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void flushSoftErrorCacheAsync() {
        /*
            java.lang.Class<com.facebook.jni.NativeSoftErrorReporterProxy> r3 = com.facebook.jni.NativeSoftErrorReporterProxy.class
            monitor-enter(r3)
            java.lang.ref.WeakReference<com.facebook.common.errorreporting.FbErrorReporter> r2 = com.facebook.jni.NativeSoftErrorReporterProxy.sFbErrorReporterWeakReference     // Catch:{ all -> 0x003f }
            if (r2 == 0) goto L_0x003a
            java.lang.ref.WeakReference<com.facebook.common.errorreporting.FbErrorReporter> r2 = com.facebook.jni.NativeSoftErrorReporterProxy.sFbErrorReporterWeakReference     // Catch:{ all -> 0x003f }
            java.lang.Object r0 = r2.get()     // Catch:{ all -> 0x003f }
            com.facebook.common.errorreporting.FbErrorReporter r0 = (com.facebook.common.errorreporting.FbErrorReporter) r0     // Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x003a
            com.facebook.common.errorreporting.ErrorReportingGkReader r2 = com.facebook.jni.NativeSoftErrorReporterProxy.sErrorReportingGkReader     // Catch:{ all -> 0x003f }
            if (r2 == 0) goto L_0x003a
            java.util.LinkedList<com.facebook.common.errorreporting.SoftError> r2 = com.facebook.jni.NativeSoftErrorReporterProxy.sSoftErrorCache     // Catch:{ all -> 0x003f }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x003f }
            if (r2 != 0) goto L_0x003a
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x003f }
            r1.<init>()     // Catch:{ all -> 0x003f }
            java.util.LinkedList<com.facebook.common.errorreporting.SoftError> r4 = com.facebook.jni.NativeSoftErrorReporterProxy.sSoftErrorCache     // Catch:{ all -> 0x003f }
            monitor-enter(r4)     // Catch:{ all -> 0x003f }
            java.util.LinkedList<com.facebook.common.errorreporting.SoftError> r2 = com.facebook.jni.NativeSoftErrorReporterProxy.sSoftErrorCache     // Catch:{ all -> 0x003c }
            r1.addAll(r2)     // Catch:{ all -> 0x003c }
            java.util.LinkedList<com.facebook.common.errorreporting.SoftError> r2 = com.facebook.jni.NativeSoftErrorReporterProxy.sSoftErrorCache     // Catch:{ all -> 0x003c }
            r2.clear()     // Catch:{ all -> 0x003c }
            monitor-exit(r4)     // Catch:{ all -> 0x003c }
            java.util.concurrent.ExecutorService r2 = com.facebook.jni.NativeSoftErrorReporterProxy.sErrorReportingExecutorService
            com.facebook.jni.NativeSoftErrorReporterProxy$1 r4 = new com.facebook.jni.NativeSoftErrorReporterProxy$1
            r4.<init>(r1, r0)
            r2.execute(r4)
        L_0x003a:
            monitor-exit(r3)
            return
        L_0x003c:
            r2 = move-exception
            monitor-exit(r4)
            throw r2
        L_0x003f:
            r2 = move-exception
            monitor-exit(r3)
            throw r2
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
