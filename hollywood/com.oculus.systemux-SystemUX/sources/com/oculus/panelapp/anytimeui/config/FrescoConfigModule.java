package com.oculus.panelapp.anytimeui.config;

import android.content.Context;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.statfs.StatFsHelper;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

public class FrescoConfigModule {
    private static final int DISK_CACHE_SIZE_BYTES = 104857600;
    private static final int MEMORY_CACHE_SIZE_BYTES = 1048576;
    private static final int MEMORY_MAX_ENTRIES_SIZE = 16;

    public static void initialize(Context context) {
        Fresco.initialize(context, ImagePipelineConfig.newBuilder(context).setBitmapMemoryCacheParamsSupplier($$Lambda$FrescoConfigModule$8MRNDT5eT3ZwPYsWW0U2AXi8UOc.INSTANCE).setMainDiskCacheConfig(DiskCacheConfig.newBuilder(context).setMaxCacheSize(StatFsHelper.DEFAULT_DISK_RED_LEVEL_IN_BYTES).build()).setDownsampleEnabled(true).build());
    }

    static /* synthetic */ MemoryCacheParams lambda$initialize$0() {
        return new MemoryCacheParams(1048576, 16, 1048576, 16, 16);
    }
}
