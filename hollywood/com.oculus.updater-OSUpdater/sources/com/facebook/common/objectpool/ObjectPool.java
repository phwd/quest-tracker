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

        @Override // com.facebook.common.objectpool.ObjectPool.Allocator
        public void onAllocate(T t) {
        }

        @Override // com.facebook.common.objectpool.ObjectPool.Allocator
        public void onRelease(T t) {
        }

        public BasicAllocator(Class<T> cls) {
            this.mClazz = cls;
        }

        @Override // com.facebook.common.objectpool.ObjectPool.Allocator
        @Nullable
        public T create() {
            try {
                return this.mClazz.newInstance();
            } catch (InstantiationException e) {
                BLog.e(ObjectPool.TAG, "Couldn't instantiate object", e);
                return null;
            } catch (IllegalAccessException e2) {
                BLog.e(ObjectPool.TAG, "Couldn't instantiate object", e2);
                return null;
            }
        }
    }

    public ObjectPool(Class<T> cls, int i, int i2, int i3, long j, Allocator<T> allocator, MonotonicClock monotonicClock) {
        this.mClazz = cls;
        this.mMinSize = Math.max(i, 0);
        this.mMaxSize = Math.max(this.mMinSize, i2);
        this.mIncrementSize = Math.max(i3, 1);
        this.mCompactionDelayMs = j;
        this.mAllocator = allocator;
        this.mClock = monotonicClock;
    }

    public synchronized T allocate() {
        T t;
        if (this.mSize > 0) {
            this.mSize--;
            t = this.mPool[this.mSize];
            this.mPool[this.mSize] = null;
        } else {
            t = this.mAllocator.create();
        }
        this.mAllocator.onAllocate(t);
        return t;
    }

    public synchronized void release(T t) {
        checkUsage();
        this.mAllocator.onRelease(t);
        if (this.mSize < this.mMaxSize) {
            if (this.mSize + 1 > this.mPool.length) {
                resizePool(Math.min(this.mMaxSize, this.mPool.length + this.mIncrementSize));
            }
            T[] tArr = this.mPool;
            int i = this.mSize;
            this.mSize = i + 1;
            tArr[i] = t;
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
        int max = Math.max(this.mPool.length - this.mIncrementSize, this.mMinSize);
        if (max != this.mPool.length) {
            resizePool(max);
        }
    }

    private void resizePool(int i) {
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) this.mClazz, i));
        T[] tArr2 = this.mPool;
        System.arraycopy(tArr2, 0, tArr, 0, Math.min(tArr2.length, i));
        this.mPool = tArr;
        this.mSize = Math.min(this.mSize, i);
    }
}
