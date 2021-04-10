package com.facebook.common.objectpool;

import java.util.HashMap;

public class ObjectPoolManager {
    private final HashMap<Class, ObjectPool> mObjectPools;

    /* access modifiers changed from: package-private */
    public <T> void addPool(Class<T> cls, ObjectPool<T> objectPool) {
        this.mObjectPools.put(cls, objectPool);
    }
}
