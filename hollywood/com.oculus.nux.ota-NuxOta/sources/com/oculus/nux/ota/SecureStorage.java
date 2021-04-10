package com.oculus.nux.ota;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import com.oculus.os.ICompanionServer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SecureStorage {
    private static final String TAG = ("NuxOta" + SecureStorage.class.getSimpleName());
    private final Context mContext;

    /* access modifiers changed from: private */
    public static final class CompanionServerConnection implements ServiceConnection {
        private final CountDownLatch latch;
        private ICompanionServer server;

        private CompanionServerConnection() {
            this.latch = new CountDownLatch(1);
        }

        /* access modifiers changed from: package-private */
        public ICompanionServer awaitServer() throws InterruptedException {
            if (!this.latch.await(15, TimeUnit.SECONDS)) {
                Log.e(SecureStorage.TAG, "Timed out waiting to bind to CompanionServer.");
            }
            return this.server;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (NuxOta.DEBUG) {
                Log.d(SecureStorage.TAG, "Connected to CompanionServer.");
            }
            this.server = ICompanionServer.Stub.asInterface(iBinder);
            this.latch.countDown();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if (NuxOta.DEBUG) {
                Log.d(SecureStorage.TAG, "Disconnected from CompanionServer.");
            }
        }
    }

    public SecureStorage(Context context) {
        this.mContext = context;
    }

    public boolean getLegacyPreOtaComplete() {
        boolean z;
        CompanionServerConnection bind = bind();
        if (bind != null) {
            try {
                z = bind.awaitServer().getLegacyPreOtaComplete();
            } catch (RemoteException | InterruptedException e) {
                Log.e(TAG, "Failure while getting legacy pre-OTA complete flag.", e);
                Thread.currentThread().interrupt();
            }
        } else {
            Log.e(TAG, "Unable to get legacy pre-OTA complete flag due to a failed connection.");
            z = false;
        }
        this.mContext.unbindService(bind);
        return z;
    }

    public String getLegacyNuxOtaState() {
        String str;
        CompanionServerConnection bind = bind();
        if (bind != null) {
            try {
                str = bind.awaitServer().getLegacyNuxOtaState();
            } catch (RemoteException | InterruptedException e) {
                Log.e(TAG, "Failure while getting legacy NUX OTA state.", e);
                Thread.currentThread().interrupt();
            }
        } else {
            Log.e(TAG, "Unable to get legacy NUX OTA state due to a failed connection.");
            str = null;
        }
        this.mContext.unbindService(bind);
        return str;
    }

    private CompanionServerConnection bind() {
        CompanionServerConnection companionServerConnection = new CompanionServerConnection();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.companion.server", "com.oculus.companion.server.CompanionServer"));
        if (!this.mContext.bindServiceAsUser(intent, companionServerConnection, 1, UserHandle.SYSTEM)) {
            Log.e(TAG, "Unable to bind to CompanionServer");
        }
        return companionServerConnection;
    }
}
