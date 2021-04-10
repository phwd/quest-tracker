package com.oculus.platform;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Looper;
import android.os.Messenger;
import android.os.UserHandle;
import android.util.Log;
import java.util.concurrent.ExecutorService;

public class OVRServiceSynchronous {
    private static final long SERVICE_CONNECTION_TIMEOUT_MILLIS = 30000;
    private static final String TAG = OVRServiceSynchronous.class.getSimpleName();
    private String mComponent;
    private ConnectionStatus mConnectionStatus = ConnectionStatus.NOT_CONNECTED;
    private final Context mContext;
    private final Object mLock = new Object();
    private Messenger mMessenger;
    private String mPackage;
    private ServiceConnection mServiceConnection;
    ExecutorService mThreadPool;
    private UserHandle mUserHandle = null;

    /* access modifiers changed from: private */
    public enum ConnectionStatus {
        CONNECTED,
        CONNECTING,
        NOT_CONNECTED
    }

    public enum ServiceState {
        NOT_FOUND,
        SIGNATURE_INVALID,
        SIGNATURE_VERIFIED
    }

    public OVRServiceSynchronous(Context context, String servicePackage, String serviceComponent, UserHandle userHandle, ExecutorService threadPool) {
        this.mContext = context;
        this.mThreadPool = threadPool;
        this.mPackage = servicePackage;
        this.mComponent = serviceComponent;
        this.mUserHandle = userHandle;
    }

    private ConnectionStatus getConnectionStatus() {
        ConnectionStatus connectionStatus;
        synchronized (this.mLock) {
            connectionStatus = this.mConnectionStatus;
        }
        return connectionStatus;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConnectionStatus(ConnectionStatus newConnectionStatus) {
        synchronized (this.mLock) {
            this.mConnectionStatus = newConnectionStatus;
            if (newConnectionStatus == ConnectionStatus.CONNECTED || newConnectionStatus == ConnectionStatus.NOT_CONNECTED) {
                this.mLock.notifyAll();
            }
        }
    }

    public void connectInBackground() {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceSynchronous.AnonymousClass1 */

            public void run() {
                OVRServiceSynchronous.this.connect();
            }
        });
    }

    private Intent getServiceIntent() {
        Intent serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName(this.mPackage, this.mComponent));
        return serviceIntent;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
        r7.mServiceConnection = new com.oculus.platform.OVRServiceSynchronous.AnonymousClass2(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        if (r7.mUserHandle != null) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        r0 = r7.mContext.bindService(getServiceIntent(), r7.mServiceConnection, 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0031, code lost:
        r0 = r7.mContext.bindServiceAsUser(getServiceIntent(), r7.mServiceConnection, 1, r7.mUserHandle);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
        if (r0 != false) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
        android.util.Log.d(com.oculus.platform.OVRServiceSynchronous.TAG, "Service connection failed.");
        r7.mServiceConnection = null;
        setConnectionStatus(com.oculus.platform.OVRServiceSynchronous.ConnectionStatus.NOT_CONNECTED);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0051, code lost:
        return 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0052, code lost:
        return 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int connect() {
        /*
            r7 = this;
            java.lang.Object r0 = r7.mLock
            monitor-enter(r0)
            com.oculus.platform.OVRServiceSynchronous$ConnectionStatus r1 = r7.getConnectionStatus()     // Catch:{ all -> 0x0055 }
            com.oculus.platform.OVRServiceSynchronous$ConnectionStatus r2 = com.oculus.platform.OVRServiceSynchronous.ConnectionStatus.CONNECTING     // Catch:{ all -> 0x0055 }
            r3 = 0
            if (r1 == r2) goto L_0x0053
            com.oculus.platform.OVRServiceSynchronous$ConnectionStatus r2 = com.oculus.platform.OVRServiceSynchronous.ConnectionStatus.CONNECTED     // Catch:{ all -> 0x0055 }
            if (r1 != r2) goto L_0x0011
            goto L_0x0053
        L_0x0011:
            com.oculus.platform.OVRServiceSynchronous$ConnectionStatus r2 = com.oculus.platform.OVRServiceSynchronous.ConnectionStatus.CONNECTING     // Catch:{ all -> 0x0055 }
            r7.setConnectionStatus(r2)     // Catch:{ all -> 0x0055 }
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            com.oculus.platform.OVRServiceSynchronous$2 r0 = new com.oculus.platform.OVRServiceSynchronous$2
            r0.<init>()
            r7.mServiceConnection = r0
            r0 = 0
            android.os.UserHandle r1 = r7.mUserHandle
            r2 = 1
            if (r1 != 0) goto L_0x0031
            android.content.Context r1 = r7.mContext
            android.content.Intent r4 = r7.getServiceIntent()
            android.content.ServiceConnection r5 = r7.mServiceConnection
            boolean r0 = r1.bindService(r4, r5, r2)
            goto L_0x003f
        L_0x0031:
            android.content.Context r1 = r7.mContext
            android.content.Intent r4 = r7.getServiceIntent()
            android.content.ServiceConnection r5 = r7.mServiceConnection
            android.os.UserHandle r6 = r7.mUserHandle
            boolean r0 = r1.bindServiceAsUser(r4, r5, r2, r6)
        L_0x003f:
            if (r0 != 0) goto L_0x0052
            java.lang.String r1 = com.oculus.platform.OVRServiceSynchronous.TAG
            java.lang.String r3 = "Service connection failed."
            android.util.Log.d(r1, r3)
            r1 = 0
            r7.mServiceConnection = r1
            com.oculus.platform.OVRServiceSynchronous$ConnectionStatus r1 = com.oculus.platform.OVRServiceSynchronous.ConnectionStatus.NOT_CONNECTED
            r7.setConnectionStatus(r1)
            return r2
        L_0x0052:
            return r3
        L_0x0053:
            monitor-exit(r0)
            return r3
        L_0x0055:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.platform.OVRServiceSynchronous.connect():int");
    }

    public void disconnect() {
        ServiceConnection serviceConnection = this.mServiceConnection;
        if (serviceConnection != null) {
            this.mContext.unbindService(serviceConnection);
        }
        this.mServiceConnection = null;
    }

    public ServiceBindResult waitForConnect() {
        return waitForConnectHelper(true);
    }

    public ServiceBindResult waitForConnectPossiblyMainthread_DO_NOT_USE_OR_YOU_WILL_BE_FIRED() {
        return waitForConnectHelper(false);
    }

    private ServiceBindResult waitForConnectHelper(boolean checkMainThread) {
        if (!checkMainThread || Looper.myLooper() != Looper.getMainLooper()) {
            int error = connect();
            if (error != 0) {
                return new ServiceBindResult().error(error);
            }
            synchronized (this.mLock) {
                if (getConnectionStatus() == ConnectionStatus.CONNECTING) {
                    try {
                        this.mLock.wait(SERVICE_CONNECTION_TIMEOUT_MILLIS);
                    } catch (InterruptedException exception) {
                        Log.e(TAG, "There was an error connecting to the service.", exception);
                    }
                }
                if (getConnectionStatus() != ConnectionStatus.CONNECTED) {
                    return new ServiceBindResult().error(1);
                }
                return new ServiceBindResult().success(this.mMessenger);
            }
        }
        Log.e(TAG, "This call will fail if the service unbinds.", new Exception());
        throw new RuntimeException("You cannot bind to services on the main thread");
    }

    public class ServiceBindResult {
        public int mBindError;
        public Messenger mMessenger;

        public ServiceBindResult() {
        }

        public ServiceBindResult success(Messenger messenger) {
            this.mMessenger = messenger;
            return this;
        }

        public ServiceBindResult error(int error) {
            this.mBindError = error;
            return this;
        }
    }
}
