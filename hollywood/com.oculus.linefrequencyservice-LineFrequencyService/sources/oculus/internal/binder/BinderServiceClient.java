package oculus.internal.binder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class BinderServiceClient<T> implements AutoCloseable {
    private static final int DEFAULT_TIMEOUT_MILLIS = 5000;
    private static final String TAG = "BinderServiceClient";
    private final ServiceConnection mConnection = new BinderConnection();
    private final Context mContext;
    private final CompletableFuture<T> mFuture = new CompletableFuture<>();

    /* access modifiers changed from: protected */
    public abstract T asInterface(IBinder iBinder);

    protected BinderServiceClient(Context context, Intent intent) throws RemoteException {
        this.mContext = context;
        if (!context.bindService(intent, this.mConnection, 1)) {
            RemoteException e = new RemoteException("Error binding to service");
            Log.e(TAG, "Error binding to service: " + intent, e);
            throw e;
        }
    }

    public T awaitService() throws RemoteException, InterruptedException {
        return awaitService(5000);
    }

    public T awaitService(long timeoutMillis) throws RemoteException, InterruptedException {
        verifyNotMainThread();
        try {
            return this.mFuture.get(timeoutMillis, TimeUnit.MILLISECONDS);
        } catch (ExecutionException | TimeoutException e) {
            RemoteException remoteException = new RemoteException("Error waiting for service");
            remoteException.initCause(e);
            Log.e(TAG, "Error awaiting service", e);
            throw remoteException;
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.mContext.unbindService(this.mConnection);
    }

    private static void verifyNotMainThread() {
        if (Looper.getMainLooper().isCurrentThread()) {
            throw new IllegalStateException("Called on main thread");
        }
    }

    private final class BinderConnection implements ServiceConnection {
        private BinderConnection() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: java.util.concurrent.CompletableFuture */
        /* JADX WARN: Multi-variable type inference failed */
        public void onServiceConnected(ComponentName name, IBinder binder) {
            if (Log.isLoggable(BinderServiceClient.TAG, 3)) {
                Log.d(BinderServiceClient.TAG, "Service connected: " + name);
            }
            BinderServiceClient.this.mFuture.complete(BinderServiceClient.this.asInterface(binder));
        }

        public void onServiceDisconnected(ComponentName name) {
            if (Log.isLoggable(BinderServiceClient.TAG, 3)) {
                Log.d(BinderServiceClient.TAG, "Service disconnected: " + name);
            }
        }
    }
}
