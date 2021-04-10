package com.oculus.appsafety;

import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* access modifiers changed from: package-private */
public final class ExceptionLoggingFutureTask<V> extends FutureTask<V> {
    private final String mTag;

    public ExceptionLoggingFutureTask(Callable<V> callable, String tag) {
        super(callable);
        this.mTag = tag;
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.FutureTask
    public void done() {
        try {
            get();
        } catch (InterruptedException | ExecutionException e) {
            Log.e(this.mTag, "Uncaught exception.", e);
            throw new RuntimeException(e);
        }
    }
}
