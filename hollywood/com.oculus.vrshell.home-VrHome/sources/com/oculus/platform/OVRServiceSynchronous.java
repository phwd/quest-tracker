package com.oculus.platform;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Messenger;
import android.util.Log;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class OVRServiceSynchronous<T extends IInterface> {
    private static final long SERVICE_CONNECTION_TIMEOUT_MILLIS = 30000;
    private static final String TAG = OVRServiceSynchronous.class.getSimpleName();
    AidlService<T> mAidlService;
    private String mComponent;
    private ConnectionStatus mConnectionStatus = ConnectionStatus.NOT_CONNECTED;
    private final Context mContext;
    private final Object mLock = new Object();
    private Messenger mMessenger;
    private String mPackage;
    private ServiceConnection mServiceConnection;
    private T mServiceInterface;
    ExecutorService mThreadPool;

    interface AidlService<T> {
        T serviceFromBinder(IBinder iBinder);
    }

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

    public OVRServiceSynchronous(Context context, String servicePackage, String serviceComponent, ExecutorService threadPool, AidlService<T> aidlService) {
        this.mContext = context;
        this.mThreadPool = threadPool;
        this.mPackage = servicePackage;
        this.mComponent = serviceComponent;
        this.mAidlService = aidlService;
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

    public ServiceState getServiceAvailability(PackageManager packageManager) {
        List<ResolveInfo> matches = packageManager.queryIntentServices(getServiceIntent(), 0);
        Log.d(TAG, "isServiceAvailable matched: " + matches.size());
        if (matches.size() != 1) {
            return ServiceState.NOT_FOUND;
        }
        try {
            return OVRPackageUtils.validateServiceSignature(packageManager, this.mPackage) ? ServiceState.SIGNATURE_VERIFIED : ServiceState.SIGNATURE_INVALID;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Impossible error, package not found: " + this.mPackage, e);
            return ServiceState.NOT_FOUND;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        r7.mServiceConnection = new com.oculus.platform.OVRServiceSynchronous.AnonymousClass2(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        if (r7.mContext.bindService(getServiceIntent(), r7.mServiceConnection, 1) != false) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
        android.util.Log.d(com.oculus.platform.OVRServiceSynchronous.TAG, "Service connection failed.");
        r7.mServiceConnection = null;
        setConnectionStatus(com.oculus.platform.OVRServiceSynchronous.ConnectionStatus.NOT_CONNECTED);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean connect() {
        /*
            r7 = this;
            r2 = 0
            r3 = 1
            java.lang.Object r4 = r7.mLock
            monitor-enter(r4)
            com.oculus.platform.OVRServiceSynchronous$ConnectionStatus r0 = r7.getConnectionStatus()     // Catch:{ all -> 0x002b }
            com.oculus.platform.OVRServiceSynchronous$ConnectionStatus r5 = com.oculus.platform.OVRServiceSynchronous.ConnectionStatus.CONNECTING     // Catch:{ all -> 0x002b }
            if (r0 == r5) goto L_0x0011
            com.oculus.platform.OVRServiceSynchronous$ConnectionStatus r5 = com.oculus.platform.OVRServiceSynchronous.ConnectionStatus.CONNECTED     // Catch:{ all -> 0x002b }
            if (r0 != r5) goto L_0x0014
        L_0x0011:
            monitor-exit(r4)     // Catch:{ all -> 0x002b }
            r2 = r3
        L_0x0013:
            return r2
        L_0x0014:
            android.content.Context r5 = r7.mContext     // Catch:{ all -> 0x002b }
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch:{ all -> 0x002b }
            com.oculus.platform.OVRServiceSynchronous$ServiceState r1 = r7.getServiceAvailability(r5)     // Catch:{ all -> 0x002b }
            com.oculus.platform.OVRServiceSynchronous$ServiceState r5 = com.oculus.platform.OVRServiceSynchronous.ServiceState.SIGNATURE_VERIFIED     // Catch:{ all -> 0x002b }
            if (r1 == r5) goto L_0x002e
            java.lang.String r3 = com.oculus.platform.OVRServiceSynchronous.TAG     // Catch:{ all -> 0x002b }
            java.lang.String r5 = "serviceState not verified"
            android.util.Log.e(r3, r5)     // Catch:{ all -> 0x002b }
            monitor-exit(r4)     // Catch:{ all -> 0x002b }
            goto L_0x0013
        L_0x002b:
            r2 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x002b }
            throw r2
        L_0x002e:
            com.oculus.platform.OVRServiceSynchronous$ConnectionStatus r5 = com.oculus.platform.OVRServiceSynchronous.ConnectionStatus.CONNECTING
            r7.setConnectionStatus(r5)
            monitor-exit(r4)
            com.oculus.platform.OVRServiceSynchronous$2 r4 = new com.oculus.platform.OVRServiceSynchronous$2
            r4.<init>()
            r7.mServiceConnection = r4
            android.content.Context r4 = r7.mContext
            android.content.Intent r5 = r7.getServiceIntent()
            android.content.ServiceConnection r6 = r7.mServiceConnection
            boolean r4 = r4.bindService(r5, r6, r3)
            if (r4 != 0) goto L_0x0059
            java.lang.String r3 = com.oculus.platform.OVRServiceSynchronous.TAG
            java.lang.String r4 = "Service connection failed."
            android.util.Log.d(r3, r4)
            r3 = 0
            r7.mServiceConnection = r3
            com.oculus.platform.OVRServiceSynchronous$ConnectionStatus r3 = com.oculus.platform.OVRServiceSynchronous.ConnectionStatus.NOT_CONNECTED
            r7.setConnectionStatus(r3)
            goto L_0x0013
        L_0x0059:
            r2 = r3
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.platform.OVRServiceSynchronous.connect():boolean");
    }

    public void disconnect() {
        if (this.mServiceConnection != null) {
            this.mContext.unbindService(this.mServiceConnection);
        }
        this.mServiceConnection = null;
    }

    public OVRServiceSynchronous<T>.ServiceBindResult waitForConnect() {
        return waitForConnectHelper(true);
    }

    public OVRServiceSynchronous<T>.ServiceBindResult waitForConnectPossiblyMainthread_DO_NOT_USE_OR_YOU_WILL_BE_FIRED() {
        return waitForConnectHelper(false);
    }

    private OVRServiceSynchronous<T>.ServiceBindResult waitForConnectHelper(boolean checkMainThread) {
        if (checkMainThread && Looper.myLooper() == Looper.getMainLooper()) {
            Log.e(TAG, "This call will fail if the service unbinds.", new Exception());
            throw new RuntimeException("You cannot bind to services on the main thread");
        } else if (!connect()) {
            return new ServiceBindResult().error();
        } else {
            synchronized (this.mLock) {
                if (getConnectionStatus() == ConnectionStatus.CONNECTING) {
                    try {
                        this.mLock.wait(SERVICE_CONNECTION_TIMEOUT_MILLIS);
                    } catch (InterruptedException exception) {
                        Log.e(TAG, "There was an error connecting to the service.", exception);
                    }
                }
                if (getConnectionStatus() != ConnectionStatus.CONNECTED) {
                    return new ServiceBindResult().error();
                }
                return new ServiceBindResult().success(this.mServiceInterface, this.mMessenger);
            }
        }
    }

    public class ServiceBindResult {
        boolean mBindError;
        Messenger mMessenger;
        T mServiceInterface;

        public ServiceBindResult() {
        }

        public OVRServiceSynchronous<T>.ServiceBindResult success(T serviceInterface, Messenger messenger) {
            this.mServiceInterface = serviceInterface;
            this.mMessenger = messenger;
            return this;
        }

        public OVRServiceSynchronous<T>.ServiceBindResult error() {
            this.mBindError = true;
            return this;
        }
    }
}
