package com.oculus.binder;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
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

@SuppressLint({"BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.e"})
@TargetApi(24)
public abstract class BinderServiceClient<T> implements AutoCloseable {
    private static final long DEFAULT_TIMEOUT_MILLIS = 5000;
    private static final Runnable NOT_MAIN_THREAD_VERIFIER = new Runnable() {
        /* class com.oculus.binder.BinderServiceClient.AnonymousClass1 */

        public void run() {
            if (Looper.getMainLooper().isCurrentThread()) {
                throw new IllegalStateException("Called on main thread");
            }
        }
    };
    private static final String TAG = BinderServiceClient.class.getSimpleName();
    private final ServiceConnection mConnection;
    private final Context mContext;
    private final CompletableFuture<T> mFuture;
    private final Runnable mNotMainThreadVerifier;

    /* access modifiers changed from: protected */
    public abstract T asInterface(IBinder iBinder);

    protected BinderServiceClient(Context context, Intent intent) throws RemoteException {
        this(context, intent, BindingStrategy.DEFAULT, NOT_MAIN_THREAD_VERIFIER);
    }

    protected BinderServiceClient(Context context, Intent intent, BindingStrategy bindingStrategy) throws RemoteException {
        this(context, intent, bindingStrategy, NOT_MAIN_THREAD_VERIFIER);
    }

    BinderServiceClient(Context context, Intent intent, BindingStrategy bindingStrategy, Runnable notMainThreadVerifier) throws RemoteException {
        this.mFuture = new CompletableFuture<>();
        this.mConnection = new BinderConnection();
        this.mContext = context;
        this.mNotMainThreadVerifier = notMainThreadVerifier;
        if (!bindingStrategy.bindService(context, intent, this.mConnection, 1)) {
            RemoteException e = new RemoteException("Error binding to service");
            Log.e(TAG, "Error binding to service: " + intent, e);
            throw e;
        }
    }

    public T awaitService() throws RemoteException, InterruptedException {
        return awaitService(DEFAULT_TIMEOUT_MILLIS);
    }

    public T awaitService(long timeoutMillis) throws RemoteException, InterruptedException {
        this.mNotMainThreadVerifier.run();
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

    private final class BinderConnection implements ServiceConnection {
        private BinderConnection() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v3, resolved type: java.util.concurrent.CompletableFuture */
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
