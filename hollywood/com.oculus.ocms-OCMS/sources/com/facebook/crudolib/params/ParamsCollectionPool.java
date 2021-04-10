package com.facebook.crudolib.params;

import androidx.core.util.Pools;
import com.facebook.infer.annotation.NullsafeStrict;

@NullsafeStrict
public class ParamsCollectionPool {
    private static final int DEFAULT_ARRAY_POOL_SIZE = 2;
    private static final int DEFAULT_MAP_POOL_SIZE = 8;
    private static final int DEFAULT_MAX_PARAMS_SIZE_IN_POOL = 32;
    private static final int DEFAULT_MIN_PARAMS_SIZE_IN_POOL = 16;
    private final int mMaxParamsSizeInPool;
    private final int mMinParamsSizeInPool;
    private final Pools.SynchronizedPool<ParamsCollectionArray> mParamsArrayPool;
    private final Pools.SynchronizedPool<ParamsCollectionMap> mParamsMapPool;

    public static ParamsCollectionPool newDefaultInstance() {
        return new ParamsCollectionPool(8, 2, 16, 32);
    }

    public ParamsCollectionPool(int i, int i2, int i3, int i4) {
        this.mParamsMapPool = new Pools.SynchronizedPool<>(i);
        this.mParamsArrayPool = new Pools.SynchronizedPool<>(i2);
        this.mMinParamsSizeInPool = i3;
        this.mMaxParamsSizeInPool = i4;
    }

    public ParamsCollectionMap acquireMap() {
        ParamsCollectionMap acquire = this.mParamsMapPool.acquire();
        if (acquire == null) {
            acquire = new ParamsCollectionMap(this.mMinParamsSizeInPool);
        }
        acquire.acquire(this);
        return acquire;
    }

    public ParamsCollectionArray acquireArray() {
        ParamsCollectionArray acquire = this.mParamsArrayPool.acquire();
        if (acquire == null) {
            acquire = new ParamsCollectionArray(this.mMinParamsSizeInPool);
        }
        acquire.acquire(this);
        return acquire;
    }

    /* access modifiers changed from: package-private */
    public void release(ParamsCollectionMap paramsCollectionMap) {
        this.mParamsMapPool.release(paramsCollectionMap);
    }

    /* access modifiers changed from: package-private */
    public void release(ParamsCollectionArray paramsCollectionArray) {
        this.mParamsArrayPool.release(paramsCollectionArray);
    }

    /* access modifiers changed from: package-private */
    public int getMaxParamsSizeInPool() {
        return this.mMaxParamsSizeInPool;
    }
}
