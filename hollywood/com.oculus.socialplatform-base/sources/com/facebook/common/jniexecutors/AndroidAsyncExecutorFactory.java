package com.facebook.common.jniexecutors;

import X.AnonymousClass0lD;
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
        AnonymousClass0lD.A01("jniexecutors");
    }
}
