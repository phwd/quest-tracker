package com.facebook.crudolib.params;

import androidx.core.util.Pools;
import com.facebook.infer.annotation.NullsafeStrict;

@NullsafeStrict
public class ParamsCollectionPool {
    private final int mMaxParamsSizeInPool;
    private final int mMinParamsSizeInPool;
    private final Pools.SynchronizedPool<Object> mParamsArrayPool;
    private final Pools.SynchronizedPool<Object> mParamsMapPool;

    public static ParamsCollectionPool newDefaultInstance() {
        return new ParamsCollectionPool(8, 2, 16, 32);
    }

    public ParamsCollectionPool(int i, int i2, int i3, int i4) {
        this.mParamsMapPool = new Pools.SynchronizedPool<>(i);
        this.mParamsArrayPool = new Pools.SynchronizedPool<>(i2);
        this.mMinParamsSizeInPool = i3;
        this.mMaxParamsSizeInPool = i4;
    }
}
