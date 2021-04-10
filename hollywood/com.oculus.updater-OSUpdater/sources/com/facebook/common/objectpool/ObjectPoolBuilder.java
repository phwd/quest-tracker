package com.facebook.common.objectpool;

import com.facebook.common.objectpool.ObjectPool;
import com.facebook.common.time.MonotonicClock;
import javax.annotation.Nullable;

public class ObjectPoolBuilder<T> {
    private ObjectPool.Allocator<T> mAllocator;
    private Class<T> mClazz;
    private MonotonicClock mClock;
    private long mCompactionDelayMs;
    private int mIncrementSize;
    private final ObjectPoolManager mManager;
    private int mMaxSize;
    private int mMinSize;

    public ObjectPoolBuilder(Class<T> cls, MonotonicClock monotonicClock) {
        this(null, cls, monotonicClock);
    }

    ObjectPoolBuilder(@Nullable ObjectPoolManager objectPoolManager, Class<T> cls, MonotonicClock monotonicClock) {
        this.mMinSize = 16;
        this.mMaxSize = 1024;
        this.mIncrementSize = 16;
        this.mCompactionDelayMs = 60000;
        this.mManager = objectPoolManager;
        this.mClazz = cls;
        this.mClock = monotonicClock;
    }

    public ObjectPoolBuilder<T> setAllocator(ObjectPool.Allocator<T> allocator) {
        this.mAllocator = allocator;
        return this;
    }

    public ObjectPool<T> build() {
        if (this.mClock != null) {
            ObjectPool.Allocator allocator = this.mAllocator;
            if (allocator == null) {
                allocator = new ObjectPool.BasicAllocator(this.mClazz);
            }
            ObjectPool<T> objectPool = new ObjectPool<>(this.mClazz, this.mMinSize, this.mMaxSize, this.mIncrementSize, this.mCompactionDelayMs, allocator, this.mClock);
            ObjectPoolManager objectPoolManager = this.mManager;
            if (objectPoolManager != null) {
                objectPoolManager.addPool(this.mClazz, objectPool);
            }
            return objectPool;
        }
        throw new IllegalArgumentException("Must add a clock to the object pool builder");
    }
}
