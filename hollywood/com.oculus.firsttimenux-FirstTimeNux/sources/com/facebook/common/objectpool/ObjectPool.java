package com.facebook.common.objectpool;

import com.facebook.common.time.MonotonicClock;
import com.facebook.debug.log.BLog;
import java.lang.reflect.Array;
import javax.annotation.Nullable;

public class ObjectPool<T> {
    private static final Class<?> TAG = ObjectPool.class;
    private final Allocator<T> mAllocator;
    private final Class<T> mClazz;
    private final MonotonicClock mClock;
    private final long mCompactionDelayMs;
    private final int mIncrementSize;
    private long mLastLowSupplyTimeMs;
    private final int mMaxSize;
    private final int mMinSize;
    private T[] mPool = ((T[]) ((Object[]) Array.newInstance((Class<?>) this.mClazz, this.mMinSize)));
    private int mSize;

    public interface Allocator<T> {
        T create();

        void onAllocate(T t);

        void onRelease(T t);
    }

    public static class BasicAllocator<T> implements Allocator<T> {
        Class<T> mClazz;

        public BasicAllocator(Class<T> clazz) {
            this.mClazz = clazz;
        }

        @Override // com.facebook.common.objectpool.ObjectPool.Allocator
        @Nullable
        public T create() {
            try {
                return this.mClazz.newInstance();
            } catch (InstantiationException e) {
                BLog.e(ObjectPool.TAG, "Couldn't instantiate object", e);
            } catch (IllegalAccessException e2) {
                BLog.e(ObjectPool.TAG, "Couldn't instantiate object", e2);
            }
            return null;
        }

        @Override // com.facebook.common.objectpool.ObjectPool.Allocator
        public void onAllocate(T t) {
        }

        @Override // com.facebook.common.objectpool.ObjectPool.Allocator
        public void onRelease(T t) {
        }
    }

    public ObjectPool(Class<T> clazz, int minSize, int maxSize, int incrementSize, long compactionDelay, Allocator<T> alloc, MonotonicClock clock) {
        this.mClazz = clazz;
        this.mMinSize = Math.max(minSize, 0);
        this.mMaxSize = Math.max(this.mMinSize, maxSize);
        this.mIncrementSize = Math.max(incrementSize, 1);
        this.mCompactionDelayMs = compactionDelay;
        this.mAllocator = alloc;
        this.mClock = clock;
    }

    public synchronized T allocate() {
        T obj;
        if (this.mSize > 0) {
            this.mSize--;
            obj = this.mPool[this.mSize];
            this.mPool[this.mSize] = null;
        } else {
            obj = this.mAllocator.create();
        }
        this.mAllocator.onAllocate(obj);
        return obj;
    }

    public synchronized void release(T obj) {
        checkUsage();
        this.mAllocator.onRelease(obj);
        if (this.mSize < this.mMaxSize) {
            if (this.mSize + 1 > this.mPool.length) {
                resizePool(Math.min(this.mMaxSize, this.mPool.length + this.mIncrementSize));
            }
            T[] tArr = this.mPool;
            int i = this.mSize;
            this.mSize = i + 1;
            tArr[i] = obj;
        }
    }

    public synchronized void checkUsage() {
        long now = this.mClock.now();
        if (this.mSize < this.mIncrementSize * 2) {
            this.mLastLowSupplyTimeMs = now;
        }
        if (now - this.mLastLowSupplyTimeMs > this.mCompactionDelayMs) {
            BLog.d(TAG, "ObjectPool.checkUsage is compacting the pool.");
            compactUsage();
        }
    }

    public synchronized void compactUsage() {
        int newSize = Math.max(this.mPool.length - this.mIncrementSize, this.mMinSize);
        if (newSize != this.mPool.length) {
            resizePool(newSize);
        }
    }

    public int getPooledObjectCount() {
        return this.mSize;
    }

    public int getMinimumSize() {
        return this.mMinSize;
    }

    public int getMaximumSize() {
        return this.mMaxSize;
    }

    public int getIncrementSize() {
        return this.mIncrementSize;
    }

    public long getCompactionDelayMs() {
        return this.mCompactionDelayMs;
    }

    public int getPoolSize() {
        return this.mPool.length;
    }

    private void resizePool(int newSize) {
        T[] newArr = (T[]) ((Object[]) Array.newInstance((Class<?>) this.mClazz, newSize));
        System.arraycopy(this.mPool, 0, newArr, 0, Math.min(this.mPool.length, newSize));
        this.mPool = newArr;
        this.mSize = Math.min(this.mSize, newSize);
    }
}
