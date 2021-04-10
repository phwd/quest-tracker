package com.oculus.vrapi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;

abstract class BinderConnection<T extends IInterface> implements IBinder.DeathRecipient {
    private static final String TAG = "BinderConnection";
    private final int mMaxRetries;
    private final int mRetryInterval;
    private T mService;
    private final String mServiceName;
    private boolean mShouldReconnect;

    /* access modifiers changed from: protected */
    public abstract T asInterface(IBinder iBinder);

    public BinderConnection(String serviceName, int maxRetries, int retryIntervalMs) {
        this.mService = null;
        this.mShouldReconnect = true;
        this.mServiceName = serviceName;
        this.mMaxRetries = maxRetries;
        this.mRetryInterval = retryIntervalMs;
    }

    public BinderConnection(String serviceName) {
        this(serviceName, 1, 0);
    }

    public void binderDied() {
        Log.d(TAG, "binderDied: Resetting " + this.mServiceName + " binder objects.");
        doDisconnect();
        if (this.mShouldReconnect) {
            try {
                doConnect();
            } catch (Exception e) {
                Log.e(TAG, "Failed to reconnect after service death", e);
            }
        }
    }

    public synchronized T getService() throws RemoteException {
        if (this.mService == null) {
            doConnect();
        }
        return this.mService;
    }

    public void disconnect() {
        doDisconnect();
    }

    /* access modifiers changed from: protected */
    public boolean validate(T t) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onConnected(T t) {
    }

    /* access modifiers changed from: protected */
    public void onDisconnected(T t) {
    }

    /* access modifiers changed from: protected */
    public void setShouldReconnect(boolean shouldReconnect) {
        this.mShouldReconnect = shouldReconnect;
    }

    private T connectToService() {
        int i = 0;
        while (true) {
            int i2 = this.mMaxRetries;
            if (i < i2 || i2 < 0) {
                try {
                    T service = asInterface((IBinder) Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, this.mServiceName));
                    if (service != null) {
                        return service;
                    }
                    Log.d(TAG, "Failed to get service " + this.mServiceName + ", retrying");
                    Thread.sleep((long) this.mRetryInterval);
                    i++;
                } catch (ReflectiveOperationException e) {
                    Log.e(TAG, "Unable to call ServiceManager", e);
                    return null;
                } catch (Exception e2) {
                    Log.w(TAG, "Encountered exception connecting to " + this.mServiceName, e2);
                }
            } else {
                Log.d(TAG, "Failed to get service " + this.mServiceName + " after " + this.mMaxRetries + " retrys at " + this.mRetryInterval + "ms intervals... ABORTING");
                return null;
            }
        }
    }

    private synchronized void doConnect() throws RemoteException {
        Log.d(TAG, "Connecting to " + this.mServiceName);
        if (this.mService != null) {
            Log.w(TAG, "doConnect: Already connected!");
            return;
        }
        this.mService = connectToService();
        if (this.mService == null) {
            Log.e(TAG, "Failed to connect to service");
            throw new RemoteException("Timed out connecting to service " + this.mServiceName);
        } else if (validate(this.mService)) {
            try {
                this.mService.asBinder().linkToDeath(this, 0);
                try {
                    onConnected(this.mService);
                } catch (Exception e) {
                    if (this.mService != null) {
                        this.mService.asBinder().unlinkToDeath(this, 0);
                        this.mService = null;
                    }
                    throw new RemoteException("Exception in onConnected: " + e.getMessage());
                }
            } catch (RemoteException e2) {
                this.mService = null;
                throw e2;
            }
        } else {
            this.mService = null;
            throw new RemoteException("Service interface validation failure");
        }
    }

    private synchronized void doDisconnect() {
        Log.d(TAG, "Disconnecting from " + this.mServiceName + " service");
        if (this.mService != null) {
            onDisconnected(this.mService);
            this.mService.asBinder().unlinkToDeath(this, 0);
            this.mService = null;
        }
    }
}
