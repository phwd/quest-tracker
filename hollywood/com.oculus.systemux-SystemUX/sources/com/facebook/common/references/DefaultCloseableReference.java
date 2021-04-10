package com.facebook.common.references;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;

public class DefaultCloseableReference<T> extends CloseableReference<T> {
    private static final String TAG = "DefaultCloseableReference";

    private DefaultCloseableReference(SharedReference<T> sharedReference, CloseableReference.LeakHandler leakHandler, @Nullable Throwable th) {
        super(sharedReference, leakHandler, th);
    }

    DefaultCloseableReference(T t, ResourceReleaser<T> resourceReleaser, CloseableReference.LeakHandler leakHandler, @Nullable Throwable th) {
        super(t, resourceReleaser, leakHandler, th);
    }

    @Override // com.facebook.common.references.CloseableReference, com.facebook.common.references.CloseableReference, java.lang.Object
    public CloseableReference<T> clone() {
        Preconditions.checkState(isValid());
        return new DefaultCloseableReference(this.mSharedReference, this.mLeakHandler, this.mStacktrace);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.common.references.CloseableReference, java.lang.Object
    public void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!this.mIsClosed) {
                    FLog.w(TAG, "Finalized without closing: %x %x (type = %s)", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(System.identityHashCode(this.mSharedReference)), this.mSharedReference.get().getClass().getName());
                    this.mLeakHandler.reportLeak(this.mSharedReference, this.mStacktrace);
                    close();
                    super.finalize();
                }
            }
        } finally {
            super.finalize();
        }
    }
}
