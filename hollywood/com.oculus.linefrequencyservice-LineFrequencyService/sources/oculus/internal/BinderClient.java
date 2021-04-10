package oculus.internal;

import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import java.util.function.Function;

public class BinderClient<T extends IInterface> {
    private static final int DEFAULT_RETRY_DELAY_MS = 1000;
    private static final int MSG_CONNECT = 1;
    private static final String TAG = BinderClient.class.getSimpleName();
    private final Function<IBinder, T> convertMethod;
    private IBinder.DeathRecipient deathRecipient;
    private Handler handler;
    private final int retryDelay;
    private T service;
    private final String serviceName;

    public BinderClient(String serviceName2, Function<IBinder, T> convertMethod2, int retryDelayMs) {
        this.deathRecipient = new IBinder.DeathRecipient() {
            /* class oculus.internal.BinderClient.AnonymousClass1 */

            public void binderDied() {
                synchronized (BinderClient.this) {
                    Log.d(BinderClient.TAG, String.format("Lost connection to %s, reconnecting", BinderClient.this.serviceName));
                    if (BinderClient.this.service != null) {
                        BinderClient.this.service.asBinder().unlinkToDeath(this, 0);
                        BinderClient.this.service = null;
                    }
                    BinderClient.this.onServiceDisconnected();
                    BinderClient.this.handler.sendEmptyMessage(1);
                }
            }
        };
        this.handler = new Handler(Looper.getMainLooper()) {
            /* class oculus.internal.BinderClient.AnonymousClass2 */

            public void handleMessage(Message msg) {
                if (msg.what != 1) {
                    String str = BinderClient.TAG;
                    Log.e(str, "Invalid message received: " + msg.what);
                } else if (!BinderClient.this.connectToService()) {
                    String str2 = BinderClient.TAG;
                    Log.d(str2, "Failed to connect to service " + BinderClient.this.serviceName + " scheduling retry");
                    sendEmptyMessageDelayed(1, (long) BinderClient.this.retryDelay);
                }
            }
        };
        this.serviceName = serviceName2;
        this.convertMethod = convertMethod2;
        this.retryDelay = retryDelayMs;
        this.handler.sendEmptyMessage(1);
    }

    public BinderClient(String serviceName2, Function<IBinder, T> convertMethod2) {
        this(serviceName2, convertMethod2, DEFAULT_RETRY_DELAY_MS);
    }

    public synchronized T getService() {
        return this.service;
    }

    /* access modifiers changed from: protected */
    public void onServiceConnected(T t) {
    }

    /* access modifiers changed from: protected */
    public void onServiceDisconnected() {
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean connectToService() {
        IBinder binderService = ServiceManager.getService(this.serviceName);
        if (binderService == null) {
            return false;
        }
        synchronized (this) {
            try {
                this.service = this.convertMethod.apply(binderService);
                if (this.service == null) {
                    Log.e(TAG, "Failed to convert IBinder to expected service type");
                    return false;
                }
                binderService.linkToDeath(this.deathRecipient, 0);
                onServiceConnected(this.service);
                String str = TAG;
                Log.d(str, "Connected to " + this.serviceName);
                return true;
            } catch (RemoteException e) {
                String str2 = TAG;
                Log.e(str2, "Unexpected failure connecting to " + this.serviceName, e);
                this.service = null;
                return false;
            }
        }
    }
}
