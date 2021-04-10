package com.facebook.common.jniexecutors;

import android.util.Log;
import com.facebook.common.objectpool.ObjectPool;
import com.facebook.common.objectpool.ObjectPoolBuilder;
import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class PooledNativeRunnable extends NativeRunnable {
    private static final ObjectPool<PooledNativeRunnable> sPool = new ObjectPoolBuilder(PooledNativeRunnable.class, AwakeTimeSinceBootClock.get()).setAllocator(new ObjectPool.BasicAllocator<PooledNativeRunnable>(PooledNativeRunnable.class) {
        /* class com.facebook.common.jniexecutors.PooledNativeRunnable.AnonymousClass1 */

        @Override // com.facebook.common.objectpool.ObjectPool.BasicAllocator, com.facebook.common.objectpool.ObjectPool.Allocator
        public PooledNativeRunnable create() {
            return new PooledNativeRunnable();
        }

        public void onAllocate(PooledNativeRunnable pooledNativeRunnable) {
            pooledNativeRunnable.mNativeExecutor = null;
        }

        public void onRelease(PooledNativeRunnable pooledNativeRunnable) {
            pooledNativeRunnable.mHybridData = null;
        }
    }).build();

    @DoNotStrip
    public static PooledNativeRunnable allocate(HybridData hybridData) {
        PooledNativeRunnable allocate = sPool.allocate();
        allocate.mHybridData = hybridData;
        return allocate;
    }

    @Override // com.facebook.common.jniexecutors.NativeRunnable
    public void run() {
        try {
            super.run();
        } catch (Exception e) {
            Log.e("PooledNativeRunnable", "run crashed", e);
        }
        sPool.release(this);
    }

    private PooledNativeRunnable() {
        super(null);
    }
}
