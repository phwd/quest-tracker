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
import com.facebook.acra.util.minidump.MinidumpReader;
import com.oculus.aidl.IAuthService2;
import java.util.concurrent.CompletableFuture;

@SuppressLint({"BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.e"})
@TargetApi(MinidumpReader.MODULE_LIST_OFFSET)
public abstract class BinderServiceClient<T> implements AutoCloseable {
    public static final long DEFAULT_TIMEOUT_MILLIS = 5000;
    public static final Runnable NOT_MAIN_THREAD_VERIFIER = new Runnable() {
        /* class com.oculus.binder.BinderServiceClient.AnonymousClass1 */

        public final void run() {
            if (Looper.getMainLooper().isCurrentThread()) {
                throw new IllegalStateException("Called on main thread");
            }
        }
    };
    public static final String TAG = "BinderServiceClient";
    public final ServiceConnection mConnection;
    public final Context mContext;
    public final CompletableFuture<T> mFuture = new CompletableFuture<>();
    public final Runnable mNotMainThreadVerifier;

    public final class BinderConnection implements ServiceConnection {
        public BinderConnection() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.util.concurrent.CompletableFuture<T> */
        /* JADX WARN: Multi-variable type inference failed */
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BinderServiceClient.this.mFuture.complete(IAuthService2.Stub.asInterface(iBinder));
        }

        public final void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public BinderServiceClient(Context context, Intent intent, BindingStrategy bindingStrategy) throws RemoteException {
        Runnable runnable = NOT_MAIN_THREAD_VERIFIER;
        BinderConnection binderConnection = new BinderConnection();
        this.mConnection = binderConnection;
        this.mContext = context;
        this.mNotMainThreadVerifier = runnable;
        if (!bindingStrategy.A15(context, intent, binderConnection, 1)) {
            RemoteException remoteException = new RemoteException("Error binding to service");
            StringBuilder sb = new StringBuilder("Error binding to service: ");
            sb.append(intent);
            Log.e(TAG, sb.toString(), remoteException);
            throw remoteException;
        }
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        this.mContext.unbindService(this.mConnection);
    }
}
