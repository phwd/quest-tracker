package com.facebook.common.objectpool;

import com.facebook.common.objectpool.ObjectPool;
import com.facebook.common.time.MonotonicClock;
import javax.annotation.Nullable;

public class ObjectPoolBuilder<T> {
    public static final long DEFAULT_COMPACTION_DELAY_MS = 60000;
    public static final int DEFAULT_INCREMENT_SIZE = 16;
    public static final int DEFAULT_MAX_SIZE = 1024;
    public static final int DEFAULT_MIN_SIZE = 16;
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

    public ObjectPoolBuilder<T> setMinimumSize(int i) {
        this.mMinSize = i;
        return this;
    }

    public int getMinimumSize() {
        return this.mMinSize;
    }

    public ObjectPoolBuilder<T> setMaximumSize(int i) {
        this.mMaxSize = i;
        return this;
    }

    public int getMaximumSize() {
        return this.mMaxSize;
    }

    public ObjectPoolBuilder<T> setIncrementSize(int i) {
        this.mIncrementSize = i;
        return this;
    }

    public int getIncrementSize() {
        return this.mIncrementSize;
    }

    public ObjectPoolBuilder<T> setCompactionDelay(long j) {
        this.mCompactionDelayMs = j;
        return this;
    }

    public long getCompactionDelay() {
        return this.mCompactionDelayMs;
    }

    public ObjectPoolBuilder<T> setAllocator(ObjectPool.Allocator<T> allocator) {
        this.mAllocator = allocator;
        return this;
    }

    public ObjectPool.Allocator<T> getAllocator() {
        return this.mAllocator;
    }

    public ObjectPoolBuilder<T> setClock(MonotonicClock monotonicClock) {
        this.mClock = monotonicClock;
        return this;
    }

    public MonotonicClock getClock() {
        return this.mClock;
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
