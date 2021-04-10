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

    public ObjectPoolBuilder(Class<T> clazz, MonotonicClock clock) {
        this(null, clazz, clock);
    }

    ObjectPoolBuilder(@Nullable ObjectPoolManager manager, Class<T> clazz, MonotonicClock clock) {
        this.mMinSize = 16;
        this.mMaxSize = 1024;
        this.mIncrementSize = 16;
        this.mCompactionDelayMs = 60000;
        this.mManager = manager;
        this.mClazz = clazz;
        this.mClock = clock;
    }

    public ObjectPoolBuilder<T> setMinimumSize(int size) {
        this.mMinSize = size;
        return this;
    }

    public int getMinimumSize() {
        return this.mMinSize;
    }

    public ObjectPoolBuilder<T> setMaximumSize(int size) {
        this.mMaxSize = size;
        return this;
    }

    public int getMaximumSize() {
        return this.mMaxSize;
    }

    public ObjectPoolBuilder<T> setIncrementSize(int size) {
        this.mIncrementSize = size;
        return this;
    }

    public int getIncrementSize() {
        return this.mIncrementSize;
    }

    public ObjectPoolBuilder<T> setCompactionDelay(long delayMs) {
        this.mCompactionDelayMs = delayMs;
        return this;
    }

    public long getCompactionDelay() {
        return this.mCompactionDelayMs;
    }

    public ObjectPoolBuilder<T> setAllocator(ObjectPool.Allocator<T> alloc) {
        this.mAllocator = alloc;
        return this;
    }

    public ObjectPool.Allocator<T> getAllocator() {
        return this.mAllocator;
    }

    public ObjectPoolBuilder<T> setClock(MonotonicClock clock) {
        this.mClock = clock;
        return this;
    }

    public MonotonicClock getClock() {
        return this.mClock;
    }

    public ObjectPool<T> build() {
        if (this.mClock == null) {
            throw new IllegalArgumentException("Must add a clock to the object pool builder");
        }
        ObjectPool.Allocator<T> alloc = this.mAllocator;
        if (alloc == null) {
            alloc = new ObjectPool.BasicAllocator<>(this.mClazz);
        }
        ObjectPool<T> pool = new ObjectPool<>(this.mClazz, this.mMinSize, this.mMaxSize, this.mIncrementSize, this.mCompactionDelayMs, alloc, this.mClock);
        if (this.mManager != null) {
            this.mManager.addPool(this.mClazz, pool);
        }
        return pool;
    }
}
