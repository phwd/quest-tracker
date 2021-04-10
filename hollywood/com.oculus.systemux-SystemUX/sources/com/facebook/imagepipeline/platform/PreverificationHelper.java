package com.facebook.imagepipeline.platform;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.facebook.soloader.DoNotOptimize;

/* access modifiers changed from: package-private */
@DoNotOptimize
public class PreverificationHelper {
    PreverificationHelper() {
    }

    /* access modifiers changed from: package-private */
    @DoNotOptimize
    @TargetApi(26)
    public boolean shouldUseHardwareBitmapConfig(Bitmap.Config config) {
        return config == Bitmap.Config.HARDWARE;
    }
}
