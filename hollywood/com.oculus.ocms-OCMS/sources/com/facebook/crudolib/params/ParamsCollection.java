package com.facebook.crudolib.params;

import com.facebook.crudolib.appstrictmode.CloseGuard;
import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.NullsafeStrict;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NullsafeStrict
@NotThreadSafe
public abstract class ParamsCollection {
    @Nullable
    private CloseGuard mCloseGuard;
    private boolean mDetached = false;
    @Nullable
    private ParamsEncoder mEncoder;
    private boolean mExternallyMutable;
    @Nullable
    private ParamsCollection mParentCollection;
    @Nullable
    SimpleIntArrayMap<Class<? extends ParamsEncoder>> mParentEncoderFlags;
    private AtomicInteger mRefCount = new AtomicInteger(0);
    @Nullable
    private ParamsCollectionPool mSourcePool;

    /* access modifiers changed from: protected */
    public abstract void onClear();

    /* access modifiers changed from: protected */
    public abstract void onCompact(int i);

    /* access modifiers changed from: protected */
    public abstract void onRelease();

    /* access modifiers changed from: protected */
    public abstract void onReleaseReferences();

    ParamsCollection() {
    }

    /* access modifiers changed from: package-private */
    public void acquire(ParamsCollectionPool paramsCollectionPool) {
        int incrementAndGet = this.mRefCount.incrementAndGet();
        if (incrementAndGet == 1) {
            this.mSourcePool = paramsCollectionPool;
            this.mCloseGuard = CloseGuard.open(this.mCloseGuard, "release");
            markMutable();
            return;
        }
        throw new IllegalStateException("Acquired object with non-zero initial refCount current = " + incrementAndGet);
    }

    /* access modifiers changed from: package-private */
    public void addRef() {
        this.mRefCount.incrementAndGet();
    }

    public void release() {
        int decrementAndGet = this.mRefCount.decrementAndGet();
        if (decrementAndGet != 1) {
            if (decrementAndGet < 0) {
                throw new IllegalStateException("release() has been called with refCount == 0");
            } else if (this.mParentCollection == null) {
                releaseInternal();
            } else {
                throw new IllegalStateException("Trying to release, when added to " + this.mParentCollection);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void releaseFromParent() {
        int decrementAndGet = this.mRefCount.decrementAndGet();
        if (decrementAndGet == 1) {
            this.mDetached = true;
            this.mParentCollection = null;
            this.mParentEncoderFlags = null;
        } else if (decrementAndGet >= 0) {
            releaseInternal();
        } else {
            throw new IllegalStateException("releaseFromParent() has been called with refCount == 0");
        }
    }

    /* access modifiers changed from: protected */
    public void releaseInternal() {
        if (this.mRefCount.get() == 0) {
            onReleaseReferences();
            compactAndClear();
            markImmutable();
            this.mDetached = false;
            this.mEncoder = null;
            this.mParentCollection = null;
            this.mParentEncoderFlags = null;
            if (this.mSourcePool != null) {
                CloseGuard.close(this.mCloseGuard);
                onRelease();
                return;
            }
            return;
        }
        throw new IllegalStateException("Releasing object with non-zero refCount.");
    }

    /* access modifiers changed from: protected */
    public void throwIfNotAttachable() {
        if (this.mDetached) {
            throw new IllegalStateException("Attempting to re-attach a detached ParamsCollection");
        } else if (this.mParentCollection != null) {
            throw new IllegalStateException("Already added to " + this.mParentCollection);
        }
    }

    /* access modifiers changed from: package-private */
    public void attachToParent(ParamsCollection paramsCollection) {
        throwIfNotAttachable();
        this.mParentCollection = paramsCollection;
    }

    /* access modifiers changed from: protected */
    public void attachAsRefToParent(ParamsCollection paramsCollection) {
        throwIfNotAttachable();
        this.mParentCollection = paramsCollection;
        addRef();
    }

    public void markImmutable() {
        this.mExternallyMutable = false;
    }

    /* access modifiers changed from: package-private */
    public void markMutable() {
        throwIfExternallyMutable();
        this.mExternallyMutable = true;
    }

    /* access modifiers changed from: protected */
    public void throwIfNotExternallyMutable() {
        if (!this.mExternallyMutable) {
            throw new IllegalStateException("Expected object to be mutable");
        }
    }

    /* access modifiers changed from: protected */
    public void throwIfExternallyMutable() {
        if (this.mExternallyMutable) {
            throw new IllegalStateException("Internal bug, expected object to be immutable");
        }
    }

    private void compactAndClear() {
        ParamsCollectionPool paramsCollectionPool = this.mSourcePool;
        if (paramsCollectionPool != null) {
            onCompact(paramsCollectionPool.getMaxParamsSizeInPool());
        }
        onClear();
    }

    public void setEncoder(ParamsEncoder paramsEncoder) {
        Assertions.assertNotNull(paramsEncoder, "encoder cannot be null!");
        this.mEncoder = paramsEncoder;
    }

    /* access modifiers changed from: package-private */
    public ParamsEncoder getEncoder(ParamsEncoder paramsEncoder) {
        ParamsEncoder paramsEncoder2 = this.mEncoder;
        return paramsEncoder2 != null ? paramsEncoder2 : paramsEncoder;
    }

    public void putParentEncoderFlags(Class<? extends ParamsEncoder> cls, int i) {
        if (this.mParentEncoderFlags == null) {
            this.mParentEncoderFlags = new SimpleIntArrayMap<>(1);
        }
        this.mParentEncoderFlags.put(cls, i);
    }

    /* access modifiers changed from: package-private */
    public int getParentEncoderFlags(Class<? extends ParamsEncoder> cls, int i) {
        SimpleIntArrayMap<Class<? extends ParamsEncoder>> simpleIntArrayMap = this.mParentEncoderFlags;
        return simpleIntArrayMap != null ? simpleIntArrayMap.get(cls, i) : i;
    }

    public void encode(Writer writer) throws IOException {
        Assertions.assertNotNull(writer, "Writer is null!");
        Assertions.assertNotNull(this.mEncoder, "No encoder set, please call setEncoder() first!");
        this.mEncoder.encode(writer, this);
    }

    /* access modifiers changed from: package-private */
    public void encode(Writer writer, ParamsEncoder paramsEncoder) throws IOException {
        Assertions.assertNotNull(writer, "Writer is null!");
        ParamsEncoder paramsEncoder2 = this.mEncoder;
        if (paramsEncoder2 != null) {
            paramsEncoder = paramsEncoder2;
        }
        Assertions.assertNotNull(paramsEncoder, "No encoder available");
        paramsEncoder.encode(writer, this);
    }

    /* access modifiers changed from: protected */
    @Nonnull
    public final ParamsCollectionPool getSourcePool() {
        return (ParamsCollectionPool) Assertions.assumeNotNull(this.mSourcePool);
    }
}
