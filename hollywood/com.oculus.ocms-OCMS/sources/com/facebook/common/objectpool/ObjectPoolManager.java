package com.facebook.common.objectpool;

import com.facebook.common.time.MonotonicClock;
import java.util.HashMap;
import javax.annotation.Nullable;

public class ObjectPoolManager {
    private final MonotonicClock mClock;
    private final HashMap<Class, ObjectPool> mObjectPools = new HashMap<>();

    public ObjectPoolManager(MonotonicClock monotonicClock) {
        this.mClock = monotonicClock;
    }

    public <T> ObjectPoolBuilder<T> createPoolBuilder(Class<T> cls) {
        return new ObjectPoolBuilder<>(this, cls, this.mClock);
    }

    /* access modifiers changed from: package-private */
    public <T> void addPool(Class<T> cls, ObjectPool<T> objectPool) {
        this.mObjectPools.put(cls, objectPool);
    }

    public <T> ObjectPool<T> getPool(Class<T> cls) {
        return this.mObjectPools.get(cls);
    }

    @Nullable
    public <T> T allocate(Class<T> cls) {
        ObjectPool<T> pool = getPool(cls);
        if (pool != null) {
            return pool.allocate();
        }
        return null;
    }

    public <T> void release(Class<T> cls, T t) {
        ObjectPool<T> pool = getPool(cls);
        if (pool != null) {
            pool.release(t);
        }
    }
}
