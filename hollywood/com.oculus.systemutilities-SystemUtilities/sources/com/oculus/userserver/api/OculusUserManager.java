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
import com.oculus.userserver.api.user.OculusUser;
import com.oculus.userserver.api.user.OculusUserBundler;
import com.oculus.userserver.managerservice.IOculusUserManager;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@TargetApi(19)
public class OculusUserManager implements AutoCloseable {
    private static final String TAG = OculusUserManager.class.getSimpleName();
    private final BlockingServiceConnection mConnection = new BlockingServiceConnection();
    private final Context mContext;

    /* access modifiers changed from: private */
    public class BlockingServiceConnection implements ServiceConnection {
        private final CountDownLatch mLatch;
        private volatile IOculusUserManager mService;

        private BlockingServiceConnection() {
            this.mLatch = new CountDownLatch(1);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void connect() {
            boolean z;
            boolean z2 = true;
            if (this.mService == null) {
                z = true;
            } else {
                z = false;
            }
            Utils.assertState(z, "Service is already connected.");
            if (this.mLatch.getCount() != 1) {
                z2 = false;
            }
            Utils.assertState(z2, "Does not support reconnecting to service.");
            if (!OculusUserManager.this.bindService("com.oculus.userserver2")) {
                throw new RuntimeException("Could not connect to OculusUserManagerService");
            }
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.mService = IOculusUserManager.Stub.asInterface(iBinder);
                this.mLatch.countDown();
                iBinder.linkToDeath(new IBinder.DeathRecipient() {
                    /* class com.oculus.userserver.api.OculusUserManager.BlockingServiceConnection.AnonymousClass1 */

                    public void binderDied() {
                        OculusUserManager.onRemoteServiceDied(null);
                    }
                }, 0);
            } catch (RemoteException e) {
                OculusUserManager.onRemoteServiceDied(e);
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private IOculusUserManager awaitService() {
            Utils.assertNonUiThread();
            try {
                this.mLatch.await();
                return this.mService;
            } catch (InterruptedException e) {
                Log.e(OculusUserManager.TAG, "Exception during connecting to service", e);
                Thread.currentThread().interrupt();
                return null;
            }
        }
    }

    public OculusUserManager(Context context) {
        this.mContext = context;
        this.mConnection.connect();
    }

    /* access modifiers changed from: private */
    public static void onRemoteServiceDied(RemoteException e) {
        Log.e(TAG, "Remote service died", e);
    }

    public static boolean isMultiUserEnabled() {
        if (Version.CURRENT_SDK_VERSION >= 13) {
            return new SettingsManager().getBoolean("multi_user_enabled", false);
        }
        return false;
    }

    private IOculusUserManager getService() throws RemoteException {
        IOculusUserManager service = this.mConnection.awaitService();
        if (service != null) {
            return service;
        }
        Log.e(TAG, "Failed to connect to OUMS.");
        throw new RemoteException("Failed to connect to OUMS.");
    }

    public boolean canAddMoreUsers() throws RemoteException {
        return getService().canAddMoreUsers();
    }

    public void createUserAndSwitch() throws RemoteException {
        getService().createUserAndSwitch();
    }

    public OculusUser getSelf() throws RemoteException {
        return OculusUserBundler.unbundleUser(getService().getSelf());
    }

    public List<OculusUser> getUsers() throws RemoteException {
        return OculusUserBundler.unbundleUsers(getService().getUsers());
    }

    public void removeUser(int userId) throws RemoteException {
        getService().removeUser(userId);
    }

    public void removeSelf() throws RemoteException {
        getService().removeSelf();
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        try {
            this.mContext.unbindService(this.mConnection);
        } catch (Exception e) {
            Log.e(TAG, "Error unbinding from service", e);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean bindService(String packageName) {
        return this.mContext.bindService(createServiceIntent(packageName), this.mConnection, 1);
    }

    private static Intent createServiceIntent(String packageName) {
        ComponentName component = new ComponentName(packageName, "com.oculus.userserver.managerservice.OculusUserManagerService");
        Intent intent = new Intent();
        intent.setComponent(component);
        return intent;
    }
}
