package com.facebook.common.jniexecutors;

import X.C05400jG;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.concurrent.ScheduledExecutorService;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class AndroidAsyncExecutorFactory {
    @DoNotStrip
    public final HybridData mHybridData;

    public static native HybridData initHybrid(ScheduledExecutorService scheduledExecutorService);

    static {
        C05400jG.A00("jniexecutors");
    }
}
