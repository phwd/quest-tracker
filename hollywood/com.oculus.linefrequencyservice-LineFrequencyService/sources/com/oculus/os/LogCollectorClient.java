package com.oculus.os;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import java.io.File;
import oculus.internal.ILogCollector;

public class LogCollectorClient {
    private static final String COLLECT_LOG_ARCHIVE = "collect_log_archive";
    private static final String COLLECT_SCREENSHOT = "collect_screenshot";
    private static final String LOG_COLLECTOR_SERVICE_NAME = ".LogCollectorService";
    private static final String OCULUS_OS_LOG_COLLECTOR = "com.oculus.os.logcollector";
    private static final String TAG = LogCollectorClient.class.getSimpleName();
    private ILogCollector mCollector = null;
    private final ServiceConnection mConnection = new ServiceConnection() {
        /* class com.oculus.os.LogCollectorClient.AnonymousClass1 */

        public void onServiceConnected(ComponentName name, IBinder service) {
            synchronized (LogCollectorClient.this.mLock) {
                Log.d(LogCollectorClient.TAG, "connected with the log collector");
                LogCollectorClient.this.mCollector = ILogCollector.Stub.asInterface(service);
                LogCollectorClient.this.mLock.notify();
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            Log.d(LogCollectorClient.TAG, "disconnected from the log collector service");
            synchronized (LogCollectorClient.this.mLock) {
                LogCollectorClient.this.mCollector = null;
            }
        }
    };
    private final Object mLock = new Object();

    public static class CollectorResult {
        boolean error = false;
        Throwable ex = null;
        String message = "";
        Error reason = Error.NO_ERROR;

        /* access modifiers changed from: private */
        public enum Error {
            NO_ERROR,
            CANNOT_CONNECT_TO_LOG_COLLECTOR,
            FAILED_TO_COLLECT_LOG,
            FILE_NOT_FOUND,
            INTERRUPTED,
            REMOTE_EXCEPTION
        }

        CollectorResult() {
        }

        public boolean isError() {
            return this.error;
        }

        private Error getReason() {
            return this.reason;
        }

        private String getMessage() {
            return this.message;
        }

        private Throwable getEx() {
            return this.ex;
        }
    }

    public synchronized CollectorResult collectLogArchive(Context context, File file) {
        return collectLogData(COLLECT_LOG_ARCHIVE, context, file);
    }

    public synchronized CollectorResult collectScreenshot(Context context, File file) {
        return collectLogData(COLLECT_SCREENSHOT, context, file);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00e7, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x013a, code lost:
        r2 = th;
     */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00f4 A[SYNTHETIC, Splitter:B:77:0x00f4] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x012a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized com.oculus.os.LogCollectorClient.CollectorResult collectLogData(java.lang.String r11, android.content.Context r12, java.io.File r13) {
        /*
        // Method dump skipped, instructions count: 345
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.os.LogCollectorClient.collectLogData(java.lang.String, android.content.Context, java.io.File):com.oculus.os.LogCollectorClient$CollectorResult");
    }
}
