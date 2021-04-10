package com.oculus.companion.server.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;
import java.util.concurrent.CompletableFuture;

public class LicenseManagementConnector {
    private static final ComponentName LICENSE_MANAGEMENT_SERVICE = new ComponentName("com.oculus.license", "com.oculus.license.LicenseManagementService");
    private ServiceConnection mConnection = null;
    private Context mContext;
    private CompletableFuture<Messenger> mFutureMessenger = null;

    public LicenseManagementConnector(Context context) {
        this.mContext = context;
    }

    public synchronized CompletableFuture<Messenger> getFutureMessenger() {
        if (this.mFutureMessenger != null) {
            return this.mFutureMessenger;
        }
        final CompletableFuture<Messenger> completableFuture = new CompletableFuture<>();
        this.mFutureMessenger = completableFuture;
        this.mConnection = new ServiceConnection() {
            /* class com.oculus.companion.server.utils.LicenseManagementConnector.AnonymousClass1 */

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                completableFuture.complete(new Messenger(iBinder));
            }

            public void onServiceDisconnected(ComponentName componentName) {
                LicenseManagementConnector.this.release();
            }
        };
        Intent intent = new Intent();
        intent.setComponent(LICENSE_MANAGEMENT_SERVICE);
        if (!this.mContext.bindService(intent, this.mConnection, 1)) {
            completableFuture.completeExceptionally(new RuntimeException("Failed to bind with LicenseManagementService"));
            this.mConnection = null;
            this.mFutureMessenger = null;
        }
        return completableFuture;
    }

    public synchronized void release() {
        if (this.mConnection != null) {
            this.mContext.unbindService(this.mConnection);
            this.mConnection = null;
            this.mFutureMessenger = null;
        }
    }
}
