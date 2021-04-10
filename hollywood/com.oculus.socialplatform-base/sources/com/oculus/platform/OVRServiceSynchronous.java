package com.oculus.platform;

import X.AnonymousClass006;
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
    public static final long SERVICE_CONNECTION_TIMEOUT_MILLIS = 30000;
    public static final String TAG = "OVRServiceSynchronous";
    public AidlService<T> mAidlService;
    public String mComponent;
    public ConnectionStatus mConnectionStatus = ConnectionStatus.NOT_CONNECTED;
    public final Context mContext;
    public final Object mLock = new Object();
    public Messenger mMessenger;
    public String mPackage;
    public ServiceConnection mServiceConnection;
    public T mServiceInterface;
    public ExecutorService mThreadPool;

    public interface AidlService<T> {
        T serviceFromBinder(IBinder iBinder);
    }

    public enum ConnectionStatus {
        CONNECTED,
        CONNECTING,
        NOT_CONNECTED
    }

    public class ServiceBindResult {
        public boolean mBindError;
        public Messenger mMessenger;
        public T mServiceInterface;

        public OVRServiceSynchronous<T>.ServiceBindResult error() {
            this.mBindError = true;
            return this;
        }

        public ServiceBindResult() {
        }

        public OVRServiceSynchronous<T>.ServiceBindResult success(T t, Messenger messenger) {
            this.mServiceInterface = t;
            this.mMessenger = messenger;
            return this;
        }
    }

    public enum ServiceState {
        NOT_FOUND,
        SIGNATURE_INVALID,
        SIGNATURE_VERIFIED
    }

    public OVRServiceSynchronous<T>.ServiceBindResult waitForConnect() {
        return waitForConnectHelper(true);
    }

    public OVRServiceSynchronous<T>.ServiceBindResult waitForConnectPossiblyMainthread_DO_NOT_USE_OR_YOU_WILL_BE_FIRED() {
        return waitForConnectHelper(false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        r5.mServiceConnection = new com.oculus.platform.OVRServiceSynchronous.AnonymousClass2(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
        if (r5.mContext.bindService(getServiceIntent(), r5.mServiceConnection, 1) != false) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0043, code lost:
        r5.mServiceConnection = null;
        setConnectionStatus(com.oculus.platform.OVRServiceSynchronous.ConnectionStatus.NOT_CONNECTED);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004b, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean connect() {
        /*
            r5 = this;
            java.lang.Object r2 = r5.mLock
            monitor-enter(r2)
            com.oculus.platform.OVRServiceSynchronous$ConnectionStatus r1 = r5.getConnectionStatus()     // Catch:{ all -> 0x004e }
            com.oculus.platform.OVRServiceSynchronous$ConnectionStatus r0 = com.oculus.platform.OVRServiceSynchronous.ConnectionStatus.CONNECTING     // Catch:{ all -> 0x004e }
            r4 = 1
            if (r1 == r0) goto L_0x004c
            com.oculus.platform.OVRServiceSynchronous$ConnectionStatus r0 = com.oculus.platform.OVRServiceSynchronous.ConnectionStatus.CONNECTED     // Catch:{ all -> 0x004e }
            if (r1 == r0) goto L_0x004c
            android.content.Context r0 = r5.mContext     // Catch:{ all -> 0x004e }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ all -> 0x004e }
            com.oculus.platform.OVRServiceSynchronous$ServiceState r1 = r5.getServiceAvailability(r0)     // Catch:{ all -> 0x004e }
            com.oculus.platform.OVRServiceSynchronous$ServiceState r0 = com.oculus.platform.OVRServiceSynchronous.ServiceState.SIGNATURE_VERIFIED     // Catch:{ all -> 0x004e }
            r3 = 0
            if (r1 == r0) goto L_0x0028
            java.lang.String r1 = "OVRServiceSynchronous"
            java.lang.String r0 = "serviceState not verified"
            android.util.Log.e(r1, r0)     // Catch:{ all -> 0x004e }
            monitor-exit(r2)     // Catch:{ all -> 0x004e }
            return r3
        L_0x0028:
            com.oculus.platform.OVRServiceSynchronous$ConnectionStatus r0 = com.oculus.platform.OVRServiceSynchronous.ConnectionStatus.CONNECTING     // Catch:{ all -> 0x004e }
            r5.setConnectionStatus(r0)     // Catch:{ all -> 0x004e }
            monitor-exit(r2)     // Catch:{ all -> 0x004e }
            com.oculus.platform.OVRServiceSynchronous$2 r0 = new com.oculus.platform.OVRServiceSynchronous$2
            r0.<init>()
            r5.mServiceConnection = r0
            android.content.Context r2 = r5.mContext
            android.content.Intent r1 = r5.getServiceIntent()
            android.content.ServiceConnection r0 = r5.mServiceConnection
            boolean r0 = r2.bindService(r1, r0, r4)
            if (r0 != 0) goto L_0x004d
            r0 = 0
            r5.mServiceConnection = r0
            com.oculus.platform.OVRServiceSynchronous$ConnectionStatus r0 = com.oculus.platform.OVRServiceSynchronous.ConnectionStatus.NOT_CONNECTED
            r5.setConnectionStatus(r0)
            return r3
        L_0x004c:
            monitor-exit(r2)
        L_0x004d:
            return r4
        L_0x004e:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.platform.OVRServiceSynchronous.connect():boolean");
    }

    private ConnectionStatus getConnectionStatus() {
        ConnectionStatus connectionStatus;
        synchronized (this.mLock) {
            connectionStatus = this.mConnectionStatus;
        }
        return connectionStatus;
    }

    private Intent getServiceIntent() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this.mPackage, this.mComponent));
        return intent;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConnectionStatus(ConnectionStatus connectionStatus) {
        synchronized (this.mLock) {
            this.mConnectionStatus = connectionStatus;
            if (connectionStatus == ConnectionStatus.CONNECTED || connectionStatus == ConnectionStatus.NOT_CONNECTED) {
                this.mLock.notifyAll();
            }
        }
    }

    private OVRServiceSynchronous<T>.ServiceBindResult waitForConnectHelper(boolean z) {
        OVRServiceSynchronous<T>.ServiceBindResult serviceBindResult;
        if (z && Looper.myLooper() == Looper.getMainLooper()) {
            Log.e(TAG, "This call will fail if the service unbinds.", new Exception());
            throw new RuntimeException("You cannot bind to services on the main thread");
        } else if (!connect()) {
            OVRServiceSynchronous<T>.ServiceBindResult serviceBindResult2 = new ServiceBindResult();
            serviceBindResult2.mBindError = true;
            return serviceBindResult2;
        } else {
            synchronized (this.mLock) {
                if (getConnectionStatus() == ConnectionStatus.CONNECTING) {
                    try {
                        this.mLock.wait(30000);
                    } catch (InterruptedException e) {
                        Log.e(TAG, "There was an error connecting to the service.", e);
                    }
                }
                if (getConnectionStatus() != ConnectionStatus.CONNECTED) {
                    serviceBindResult = new ServiceBindResult();
                    serviceBindResult.mBindError = true;
                } else {
                    serviceBindResult = new ServiceBindResult();
                    T t = this.mServiceInterface;
                    Messenger messenger = this.mMessenger;
                    serviceBindResult.mServiceInterface = t;
                    serviceBindResult.mMessenger = messenger;
                }
            }
            return serviceBindResult;
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

    public void disconnect() {
        ServiceConnection serviceConnection = this.mServiceConnection;
        if (serviceConnection != null) {
            this.mContext.unbindService(serviceConnection);
        }
        this.mServiceConnection = null;
    }

    public OVRServiceSynchronous(Context context, String str, String str2, ExecutorService executorService, AidlService<T> aidlService) {
        this.mContext = context;
        this.mThreadPool = executorService;
        this.mPackage = str;
        this.mComponent = str2;
        this.mAidlService = aidlService;
    }

    public static /* synthetic */ String access$100() {
        return TAG;
    }

    public ServiceState getServiceAvailability(PackageManager packageManager) {
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(getServiceIntent(), 0);
        queryIntentServices.size();
        if (queryIntentServices.size() != 1) {
            return ServiceState.NOT_FOUND;
        }
        try {
            if (OVRPackageUtils.validateServiceSignature(packageManager, this.mPackage)) {
                return ServiceState.SIGNATURE_VERIFIED;
            }
            return ServiceState.SIGNATURE_INVALID;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, AnonymousClass006.A07("Impossible error, package not found: ", this.mPackage), e);
            return ServiceState.NOT_FOUND;
        }
    }
}
