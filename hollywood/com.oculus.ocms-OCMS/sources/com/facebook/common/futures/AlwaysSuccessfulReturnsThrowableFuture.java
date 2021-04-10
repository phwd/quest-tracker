package com.facebook.common.futures;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.CancellationException;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
class AlwaysSuccessfulReturnsThrowableFuture extends AbstractFuture<Object> {
    private final ListenableFuture<?> mUnderlyingFuture;

    static AlwaysSuccessfulReturnsThrowableFuture create(ListenableFuture<?> listenableFuture) {
        AlwaysSuccessfulReturnsThrowableFuture alwaysSuccessfulReturnsThrowableFuture = new AlwaysSuccessfulReturnsThrowableFuture(listenableFuture);
        Futures.addCallback(listenableFuture, new FutureCallback<Object>() {
            /* class com.facebook.common.futures.AlwaysSuccessfulReturnsThrowableFuture.AnonymousClass1 */

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onSuccess(@Nullable Object obj) {
                AlwaysSuccessfulReturnsThrowableFuture.this.set(obj);
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable th) {
                if (!(th instanceof CancellationException)) {
                    AlwaysSuccessfulReturnsThrowableFuture.this.set(th);
                }
            }
        }, MoreExecutors.directExecutor());
        return alwaysSuccessfulReturnsThrowableFuture;
    }

    private AlwaysSuccessfulReturnsThrowableFuture(ListenableFuture<?> listenableFuture) {
        this.mUnderlyingFuture = listenableFuture;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public boolean cancel(boolean z) {
        if (super.cancel(z)) {
            return this.mUnderlyingFuture.cancel(z);
        }
        return false;
    }
}
