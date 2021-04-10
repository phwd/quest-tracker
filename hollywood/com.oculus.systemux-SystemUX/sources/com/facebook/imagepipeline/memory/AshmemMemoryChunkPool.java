package com.facebook.imagepipeline.memory;

import android.annotation.TargetApi;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
@TargetApi(27)
public class AshmemMemoryChunkPool extends MemoryChunkPool {
    @DoNotStrip
    public AshmemMemoryChunkPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
    }

    /* Return type fixed from 'com.facebook.imagepipeline.memory.AshmemMemoryChunk' to match base method */
    @Override // com.facebook.imagepipeline.memory.BasePool, com.facebook.imagepipeline.memory.MemoryChunkPool, com.facebook.imagepipeline.memory.MemoryChunkPool
    public MemoryChunk alloc(int i) {
        return new AshmemMemoryChunk(i);
    }
}
