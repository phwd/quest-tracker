package com.oculus.userserver.api;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.os.SettingsManager;
import com.oculus.os.Version;
import com.oculus.userserver.managerservice.IOculusUserManager;
import java.util.concurrent.CountDownLatch;

@TargetApi(19)
public class OculusUserManager implements AutoCloseable {
    public static final String SERVICE_IMPL_CLS = "com.oculus.userserver.managerservice.OculusUserManagerService";
    public static final String SERVICE_IMPL_PKG = "com.oculus.userserver2";
    public static final String TAG = "OculusUserManager";
    public final BlockingServiceConnection mConnection;
    public final Context mContext;

    public class BlockingServiceConnection implements ServiceConnection {
        public final CountDownLatch mLatch = new CountDownLatch(1);
        public volatile IOculusUserManager mService;

        public final void onServiceDisconnected(ComponentName componentName) {
        }

        public BlockingServiceConnection() {
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.mService = IOculusUserManager.Stub.asInterface(iBinder);
                this.mLatch.countDown();
                iBinder.linkToDeath(new IBinder.DeathRecipient() {
                    /* class com.oculus.userserver.api.OculusUserManager.BlockingServiceConnection.AnonymousClass1 */

                    public final void binderDied() {
                        Log.e(OculusUserManager.TAG, "Remote service died", null);
                    }
                }, 0);
            } catch (RemoteException e) {
                Log.e(OculusUserManager.TAG, "Remote service died", e);
            }
        }
    }

    public static IOculusUserManager A00(OculusUserManager oculusUserManager) throws RemoteException {
        BlockingServiceConnection blockingServiceConnection = oculusUserManager.mConnection;
        Utils.A00();
        try {
            blockingServiceConnection.mLatch.await();
            IOculusUserManager iOculusUserManager = blockingServiceConnection.mService;
            if (iOculusUserManager != null) {
                return iOculusUserManager;
            }
        } catch (InterruptedException e) {
            Log.e(TAG, "Exception during connecting to service", e);
            Thread.currentThread().interrupt();
        }
        Log.e(TAG, "Failed to connect to OUMS.");
        throw new RemoteException("Failed to connect to OUMS.");
    }

    public static boolean A01() {
        if (Version.CURRENT_SDK_VERSION >= 13) {
            return new SettingsManager().getBoolean("multi_user_enabled", false);
        }
        return false;
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        try {
            this.mContext.unbindService(this.mConnection);
        } catch (Exception e) {
            Log.e(TAG, "Error unbinding from service", e);
        }
    }

    public OculusUserManager(Context context) {
        BlockingServiceConnection blockingServiceConnection = new BlockingServiceConnection();
        this.mConnection = blockingServiceConnection;
        this.mContext = context;
        boolean z = true;
        String str = "Service is already connected.";
        if (blockingServiceConnection.mService == null) {
            str = "Does not support reconnecting to service.";
            if (blockingServiceConnection.mLatch.getCount() != 1 ? false : z) {
                OculusUserManager oculusUserManager = OculusUserManager.this;
                ComponentName componentName = new ComponentName(SERVICE_IMPL_PKG, SERVICE_IMPL_CLS);
                Intent intent = new Intent();
                intent.setComponent(componentName);
                if (!oculusUserManager.mContext.bindService(intent, oculusUserManager.mConnection, 1)) {
                    throw new RuntimeException("Could not connect to OculusUserManagerService");
                }
                return;
            }
        }
        throw new IllegalStateException(str);
    }
}
