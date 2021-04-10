package com.facebook.imagepipeline.memory;

import android.os.Build;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.imagepipeline.core.NativeCodeSetup;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class PoolFactory {
    @Nullable
    private MemoryChunkPool mAshmemMemoryChunkPool;
    private BitmapPool mBitmapPool;
    @Nullable
    private MemoryChunkPool mBufferMemoryChunkPool;
    private final PoolConfig mConfig;
    private FlexByteArrayPool mFlexByteArrayPool;
    @Nullable
    private MemoryChunkPool mNativeMemoryChunkPool;
    private PooledByteBufferFactory mPooledByteBufferFactory;
    private PooledByteStreams mPooledByteStreams;
    private SharedByteArray mSharedByteArray;
    private ByteArrayPool mSmallByteArrayPool;

    public PoolFactory(PoolConfig poolConfig) {
        this.mConfig = (PoolConfig) Preconditions.checkNotNull(poolConfig);
    }

    public BitmapPool getBitmapPool() {
        MemoryTrimmableRegistry memoryTrimmableRegistry;
        if (this.mBitmapPool == null) {
            String bitmapPoolType = this.mConfig.getBitmapPoolType();
            char c = 65535;
            switch (bitmapPoolType.hashCode()) {
                case -1868884870:
                    if (bitmapPoolType.equals(BitmapPoolType.LEGACY_DEFAULT_PARAMS)) {
                        c = 3;
                        break;
                    }
                    break;
                case -1106578487:
                    if (bitmapPoolType.equals("legacy")) {
                        c = 4;
                        break;
                    }
                    break;
                case -404562712:
                    if (bitmapPoolType.equals(BitmapPoolType.EXPERIMENTAL)) {
                        c = 2;
                        break;
                    }
                    break;
                case -402149703:
                    if (bitmapPoolType.equals(BitmapPoolType.DUMMY_WITH_TRACKING)) {
                        c = 1;
                        break;
                    }
                    break;
                case 95945896:
                    if (bitmapPoolType.equals(BitmapPoolType.DUMMY)) {
                        c = 0;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                this.mBitmapPool = new DummyBitmapPool();
            } else if (c == 1) {
                this.mBitmapPool = new DummyTrackingInUseBitmapPool();
            } else if (c == 2) {
                int bitmapPoolMaxPoolSize = this.mConfig.getBitmapPoolMaxPoolSize();
                int bitmapPoolMaxBitmapSize = this.mConfig.getBitmapPoolMaxBitmapSize();
                NoOpPoolStatsTracker instance = NoOpPoolStatsTracker.getInstance();
                if (this.mConfig.isRegisterLruBitmapPoolAsMemoryTrimmable()) {
                    memoryTrimmableRegistry = this.mConfig.getMemoryTrimmableRegistry();
                } else {
                    memoryTrimmableRegistry = null;
                }
                this.mBitmapPool = new LruBitmapPool(bitmapPoolMaxPoolSize, bitmapPoolMaxBitmapSize, instance, memoryTrimmableRegistry);
            } else if (c == 3) {
                this.mBitmapPool = new BucketsBitmapPool(this.mConfig.getMemoryTrimmableRegistry(), DefaultBitmapPoolParams.get(), this.mConfig.getBitmapPoolStatsTracker(), this.mConfig.isIgnoreBitmapPoolHardCap());
            } else if (Build.VERSION.SDK_INT >= 21) {
                this.mBitmapPool = new BucketsBitmapPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getBitmapPoolParams(), this.mConfig.getBitmapPoolStatsTracker(), this.mConfig.isIgnoreBitmapPoolHardCap());
            } else {
                this.mBitmapPool = new DummyBitmapPool();
            }
        }
        return this.mBitmapPool;
    }

    @Nullable
    public MemoryChunkPool getBufferMemoryChunkPool() {
        if (this.mBufferMemoryChunkPool == null) {
            try {
                this.mBufferMemoryChunkPool = (MemoryChunkPool) Class.forName("com.facebook.imagepipeline.memory.BufferMemoryChunkPool").getConstructor(MemoryTrimmableRegistry.class, PoolParams.class, PoolStatsTracker.class).newInstance(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getMemoryChunkPoolParams(), this.mConfig.getMemoryChunkPoolStatsTracker());
            } catch (ClassNotFoundException unused) {
                this.mBufferMemoryChunkPool = null;
            } catch (IllegalAccessException unused2) {
                this.mBufferMemoryChunkPool = null;
            } catch (InstantiationException unused3) {
                this.mBufferMemoryChunkPool = null;
            } catch (NoSuchMethodException unused4) {
                this.mBufferMemoryChunkPool = null;
            } catch (InvocationTargetException unused5) {
                this.mBufferMemoryChunkPool = null;
            }
        }
        return this.mBufferMemoryChunkPool;
    }

    public FlexByteArrayPool getFlexByteArrayPool() {
        if (this.mFlexByteArrayPool == null) {
            this.mFlexByteArrayPool = new FlexByteArrayPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getFlexByteArrayPoolParams());
        }
        return this.mFlexByteArrayPool;
    }

    public int getFlexByteArrayPoolMaxNumThreads() {
        return this.mConfig.getFlexByteArrayPoolParams().maxNumThreads;
    }

    @Nullable
    public MemoryChunkPool getNativeMemoryChunkPool() {
        if (this.mNativeMemoryChunkPool == null) {
            try {
                this.mNativeMemoryChunkPool = (MemoryChunkPool) Class.forName("com.facebook.imagepipeline.memory.NativeMemoryChunkPool").getConstructor(MemoryTrimmableRegistry.class, PoolParams.class, PoolStatsTracker.class).newInstance(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getMemoryChunkPoolParams(), this.mConfig.getMemoryChunkPoolStatsTracker());
            } catch (ClassNotFoundException e) {
                FLog.e("PoolFactory", "", e);
                this.mNativeMemoryChunkPool = null;
            } catch (IllegalAccessException e2) {
                FLog.e("PoolFactory", "", e2);
                this.mNativeMemoryChunkPool = null;
            } catch (InstantiationException e3) {
                FLog.e("PoolFactory", "", e3);
                this.mNativeMemoryChunkPool = null;
            } catch (NoSuchMethodException e4) {
                FLog.e("PoolFactory", "", e4);
                this.mNativeMemoryChunkPool = null;
            } catch (InvocationTargetException e5) {
                FLog.e("PoolFactory", "", e5);
                this.mNativeMemoryChunkPool = null;
            }
        }
        return this.mNativeMemoryChunkPool;
    }

    @Nullable
    private MemoryChunkPool getAshmemMemoryChunkPool() {
        if (this.mAshmemMemoryChunkPool == null) {
            try {
                this.mAshmemMemoryChunkPool = (MemoryChunkPool) Class.forName("com.facebook.imagepipeline.memory.AshmemMemoryChunkPool").getConstructor(MemoryTrimmableRegistry.class, PoolParams.class, PoolStatsTracker.class).newInstance(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getMemoryChunkPoolParams(), this.mConfig.getMemoryChunkPoolStatsTracker());
            } catch (ClassNotFoundException unused) {
                this.mAshmemMemoryChunkPool = null;
            } catch (IllegalAccessException unused2) {
                this.mAshmemMemoryChunkPool = null;
            } catch (InstantiationException unused3) {
                this.mAshmemMemoryChunkPool = null;
            } catch (NoSuchMethodException unused4) {
                this.mAshmemMemoryChunkPool = null;
            } catch (InvocationTargetException unused5) {
                this.mAshmemMemoryChunkPool = null;
            }
        }
        return this.mAshmemMemoryChunkPool;
    }

    public PooledByteBufferFactory getPooledByteBufferFactory() {
        return getPooledByteBufferFactory(!NativeCodeSetup.getUseNativeCode() ? 1 : 0);
    }

    public PooledByteBufferFactory getPooledByteBufferFactory(int i) {
        if (this.mPooledByteBufferFactory == null) {
            MemoryChunkPool memoryChunkPool = getMemoryChunkPool(i);
            Preconditions.checkNotNull(memoryChunkPool, "failed to get pool for chunk type: " + i);
            this.mPooledByteBufferFactory = new MemoryPooledByteBufferFactory(getMemoryChunkPool(i), getPooledByteStreams());
        }
        return this.mPooledByteBufferFactory;
    }

    public PooledByteStreams getPooledByteStreams() {
        if (this.mPooledByteStreams == null) {
            this.mPooledByteStreams = new PooledByteStreams(getSmallByteArrayPool());
        }
        return this.mPooledByteStreams;
    }

    public SharedByteArray getSharedByteArray() {
        if (this.mSharedByteArray == null) {
            this.mSharedByteArray = new SharedByteArray(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getFlexByteArrayPoolParams());
        }
        return this.mSharedByteArray;
    }

    public ByteArrayPool getSmallByteArrayPool() {
        if (this.mSmallByteArrayPool == null) {
            this.mSmallByteArrayPool = new GenericByteArrayPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getSmallByteArrayPoolParams(), this.mConfig.getSmallByteArrayPoolStatsTracker());
        }
        return this.mSmallByteArrayPool;
    }

    @Nullable
    private MemoryChunkPool getMemoryChunkPool(int i) {
        if (i == 0) {
            return getNativeMemoryChunkPool();
        }
        if (i == 1) {
            return getBufferMemoryChunkPool();
        }
        if (i == 2) {
            return getAshmemMemoryChunkPool();
        }
        throw new IllegalArgumentException("Invalid MemoryChunkType");
    }
}
