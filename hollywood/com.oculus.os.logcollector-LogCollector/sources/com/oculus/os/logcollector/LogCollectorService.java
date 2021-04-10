package com.oculus.os.logcollector;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.SystemProperties;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import oculus.internal.ILogCollector;

public class LogCollectorService extends Service {
    private static final String INIT_SERVICE_NAME = "bugreporter";
    private static final int NB_MAX_CONNECTION_ATTEMPTS = 5;
    private static final String TAG = "LogCollectorService";
    private static final Object mDumpStateLock = new Object();
    private final ILogCollector.Stub mBinder = new ILogCollector.Stub() {
        /* class com.oculus.os.logcollector.LogCollectorService.AnonymousClass1 */

        /*  JADX ERROR: StackOverflowError in pass: MarkFinallyVisitor
            java.lang.StackOverflowError
            	at jadx.core.dex.nodes.InsnNode.isSame(InsnNode.java:303)
            	at jadx.core.dex.visitors.MarkFinallyVisitor.sameInsns(MarkFinallyVisitor.java:451)
            	at jadx.core.dex.visitors.MarkFinallyVisitor.compareBlocks(MarkFinallyVisitor.java:436)
            	at jadx.core.dex.visitors.MarkFinallyVisitor.checkBlocksTree(MarkFinallyVisitor.java:408)
            	at jadx.core.dex.visitors.MarkFinallyVisitor.checkBlocksTree(MarkFinallyVisitor.java:411)
            */
        public boolean collectLogArchive(android.os.ParcelFileDescriptor r5) {
            /*
            // Method dump skipped, instructions count: 144
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.os.logcollector.LogCollectorService.AnonymousClass1.collectLogArchive(android.os.ParcelFileDescriptor):boolean");
        }

        public boolean collectScreenshot(ParcelFileDescriptor parcelFileDescriptor) {
            Bitmap takeScreenshot = Screenshooter.takeScreenshot();
            if (takeScreenshot == null) {
                Log.e(LogCollectorService.TAG, "could not collect screenshot");
                return false;
            }
            takeScreenshot.compress(Bitmap.CompressFormat.PNG, 100, new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptor));
            return true;
        }
    };
    private Context mContext;

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0047 A[SYNTHETIC, Splitter:B:30:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x004f A[Catch:{ Exception -> 0x004b }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x005c A[SYNTHETIC, Splitter:B:39:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0064 A[Catch:{ Exception -> 0x0060 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean copyFileIntoOutputStream(java.io.FileInputStream r9, java.io.FileOutputStream r10) {
        /*
        // Method dump skipped, instructions count: 110
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.os.logcollector.LogCollectorService.copyFileIntoOutputStream(java.io.FileInputStream, java.io.FileOutputStream):boolean");
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    public void onCreate() {
        super.onCreate();
        this.mContext = getApplicationContext();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0060  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File takeSystemDumpState() {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.os.logcollector.LogCollectorService.takeSystemDumpState():java.io.File");
    }

    private LocalSocket connectWithBugReportInitService() {
        LocalSocketAddress localSocketAddress = new LocalSocketAddress("dumpstate", LocalSocketAddress.Namespace.RESERVED);
        for (int i = 0; i < NB_MAX_CONNECTION_ATTEMPTS; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                LocalSocket localSocket = new LocalSocket();
                localSocket.connect(localSocketAddress);
                if (localSocket.isConnected()) {
                    return localSocket;
                }
            } catch (Exception e) {
                Log.i(TAG, "could not connect to dumpstate", e);
            }
        }
        return null;
    }

    private File readBugReportFromInitService(LocalSocket localSocket) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(localSocket.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return null;
                }
                String[] split = readLine.split(":");
                if (split.length == 0) {
                    String str = TAG;
                    Log.d(str, "malformed status: " + readLine);
                } else if (split[0].equals("FAIL")) {
                    throw new RuntimeException(split[1]);
                } else if (split[0].equals("OK")) {
                    Log.d(TAG, String.format("temporary archive generated at: %s", split[1]));
                    return new File(split[1]);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "could not read bug report from the init service", e);
            return null;
        }
    }

    private boolean startBugReportInitService() {
        return setSystemProperty("ctl.start", INIT_SERVICE_NAME);
    }

    private boolean stopBugReportInitService() {
        return setSystemProperty("ctl.stop", INIT_SERVICE_NAME);
    }

    private static boolean setSystemProperty(String str, String str2) {
        try {
            SystemProperties.set(str, str2);
            return true;
        } catch (Exception e) {
            String str3 = TAG;
            Log.e(str3, "Could not set property " + str + " to " + str2, e);
            return false;
        }
    }
}
