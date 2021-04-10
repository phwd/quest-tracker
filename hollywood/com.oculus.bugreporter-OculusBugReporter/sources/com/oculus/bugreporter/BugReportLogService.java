package com.oculus.bugreporter;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import java.util.concurrent.CountDownLatch;
import oculus.internal.ILogCollector;

public class BugReportLogService extends IntentService {
    private static final Uri AUTHORITY = Uri.parse("content://com.oculus.bugreporter.provider");
    private static final Uri LOGFILE = Uri.parse(((Object) AUTHORITY) + "/report/logfile/id/");
    private static final String LOG_COLLECTOR_SERVICE_NAME = ".LogCollectorService";
    private static final String OCULUS_OS_LOG_COLLECTOR = "com.oculus.os.logcollector";
    private static final Uri REPORT = Uri.parse(((Object) AUTHORITY) + "/report/id/");
    private static final String TAG = "BugReportLogService";
    ILogCollector mCollector = null;
    private final ServiceConnection mConnection = new ServiceConnection() {
        /* class com.oculus.bugreporter.BugReportLogService.AnonymousClass1 */

        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(BugReportLogService.TAG, "connected with the log collector");
            BugReportLogService.this.mCollector = ILogCollector.Stub.asInterface(service);
            BugReportLogService.this.mConnectionLatch.countDown();
        }

        public void onServiceDisconnected(ComponentName name) {
            Log.d(BugReportLogService.TAG, "disconnected from log collector");
            BugReportLogService.this.mConnectionLatch.countDown();
        }
    };
    private CountDownLatch mConnectionLatch;

    public BugReportLogService() {
        super(TAG);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00a4, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        android.util.Log.e(com.oculus.bugreporter.BugReportLogService.TAG, "Failed to collect bug report logs", r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00ac, code lost:
        r3 = com.oculus.bugreporter.BugReportLogService.REPORT.buildUpon().appendPath(r1).build();
        r5 = new android.content.ContentValues();
        r5.put(com.oculus.bugreporter.BugReportProvider.LOGS, java.lang.String.valueOf(false));
        getContentResolver().update(r3, r5, null, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00d0, code lost:
        if (r11.mCollector == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00a4 A[ExcHandler: RemoteException | IOException | IllegalArgumentException (r3v11 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:7:0x004b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onHandleIntent(android.content.Intent r12) {
        /*
        // Method dump skipped, instructions count: 260
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.bugreporter.BugReportLogService.onHandleIntent(android.content.Intent):void");
    }
}
