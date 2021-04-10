package com.facebook.common.objectpool;

import com.facebook.common.time.MonotonicClock;
import java.util.HashMap;
import javax.annotation.Nullable;

public class ObjectPoolManager {
    private final MonotonicClock mClock;
    private final HashMap<Class, ObjectPool> mObjectPools = new HashMap<>();

    public ObjectPoolManager(MonotonicClock clock) {
        this.mClock = clock;
    }

    public <T> ObjectPoolBuilder<T> createPoolBuilder(Class<T> clazz) {
        return new ObjectPoolBuilder<>(this, clazz, this.mClock);
    }

    /* access modifiers changed from: package-private */
    public <T> void addPool(Class<T> clazz, ObjectPool<T> pool) {
        this.mObjectPools.put(clazz, pool);
    }

    public <T> ObjectPool<T> getPool(Class<T> clazz) {
        return this.mObjectPools.get(clazz);
    }

    @Nullable
    public <T> T allocate(Class<T> clazz) {
        ObjectPool<T> pool = getPool(clazz);
        if (pool != null) {
            return pool.allocate();
        }
        return null;
    }

    public <T> void release(Class<T> clazz, T obj) {
        ObjectPool<T> pool = getPool(clazz);
        if (pool != null) {
            pool.release(obj);
        }
    }
}
