package com.facebook.common.jniexecutors;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;
import java.util.concurrent.ScheduledExecutorService;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class AndroidAsyncExecutorFactory {
    @DoNotStrip
    private final HybridData mHybridData;

    private static native HybridData initHybrid(ScheduledExecutorService scheduledExecutorService);

    static {
        NativeLoader.loadLibrary("jniexecutors");
    }
}
