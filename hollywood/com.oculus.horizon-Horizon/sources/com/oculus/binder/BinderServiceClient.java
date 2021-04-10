package com.oculus.binder;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Looper;
import java.util.concurrent.CompletableFuture;

@SuppressLint({"BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.e"})
@TargetApi(24)
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
    public final CompletableFuture<T> mFuture;
    public final Runnable mNotMainThreadVerifier;

    public final class BinderConnection implements ServiceConnection {
        public final /* synthetic */ BinderServiceClient this$0;
    }
}
