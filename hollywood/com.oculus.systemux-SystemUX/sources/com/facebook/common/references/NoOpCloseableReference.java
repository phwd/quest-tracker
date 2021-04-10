package com.facebook.common.references;

import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;

public class NoOpCloseableReference<T> extends CloseableReference<T> {
    @Override // com.facebook.common.references.CloseableReference, com.facebook.common.references.CloseableReference, java.lang.Object
    public CloseableReference<T> clone() {
        return this;
    }

    @Override // com.facebook.common.references.CloseableReference, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    NoOpCloseableReference(T t, ResourceReleaser<T> resourceReleaser, CloseableReference.LeakHandler leakHandler, @Nullable Throwable th) {
        super(t, resourceReleaser, leakHandler, th);
    }
}
