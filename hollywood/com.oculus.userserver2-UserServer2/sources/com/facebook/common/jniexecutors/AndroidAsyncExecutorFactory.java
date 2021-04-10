package com.facebook.common.jniexecutors;

import X.g6;
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
        g6.A00();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
