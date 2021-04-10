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
    public static final long DEFAULT_TIMEOUT_MILLIS = 5000;
    public static final Runnable NOT_MAIN_THREAD_VERIFIER = new Runnable() {
        /* class com.oculus.binder.BinderServiceClient.AnonymousClass1 */

        public void run() {
            if (Looper.getMainLooper().isCurrentThread()) {
                throw new IllegalStateException("Called on main thread");
            }
        }
    };
    public static final String TAG = "BinderServiceClient";
    public final ServiceConnection mConnection;
    public final Context mContext;
    public final CompletableFuture<T> mFuture;
    public final Runnable mNotMainThreadVerifier;

    public final class BinderConnection implements ServiceConnection {
        /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.util.concurrent.CompletableFuture<T> */
        /* JADX WARN: Multi-variable type inference failed */
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BinderServiceClient binderServiceClient = BinderServiceClient.this;
            binderServiceClient.mFuture.complete(binderServiceClient.asInterface(iBinder));
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public BinderConnection() {
        }
    }

    public abstract T asInterface(IBinder iBinder);

    @Override // java.lang.AutoCloseable
    public void close() {
        this.mContext.unbindService(this.mConnection);
    }

    public static /* synthetic */ String access$100() {
        return TAG;
    }

    public BinderServiceClient(Context context, Intent intent) throws RemoteException {
        this(context, intent, BindingStrategy.DEFAULT, NOT_MAIN_THREAD_VERIFIER);
    }

    public BinderServiceClient(Context context, Intent intent, BindingStrategy bindingStrategy) throws RemoteException {
        this(context, intent, bindingStrategy, NOT_MAIN_THREAD_VERIFIER);
    }

    public BinderServiceClient(Context context, Intent intent, BindingStrategy bindingStrategy, Runnable runnable) throws RemoteException {
        this.mFuture = new CompletableFuture<>();
        BinderConnection binderConnection = new BinderConnection();
        this.mConnection = binderConnection;
        this.mContext = context;
        this.mNotMainThreadVerifier = runnable;
        if (!bindingStrategy.bindService(context, intent, binderConnection, 1)) {
            RemoteException remoteException = new RemoteException("Error binding to service");
            StringBuilder sb = new StringBuilder("Error binding to service: ");
            sb.append(intent);
            Log.e(TAG, sb.toString(), remoteException);
            throw remoteException;
        }
    }

    public T awaitService() throws RemoteException, InterruptedException {
        return awaitService(5000);
    }

    public T awaitService(long j) throws RemoteException, InterruptedException {
        this.mNotMainThreadVerifier.run();
        try {
            return this.mFuture.get(j, TimeUnit.MILLISECONDS);
        } catch (ExecutionException | TimeoutException e) {
            RemoteException remoteException = new RemoteException("Error waiting for service");
            remoteException.initCause(e);
            Log.e(TAG, "Error awaiting service", e);
            throw remoteException;
        }
    }
}
