package com.facebook.common.jniexecutors;

import X.AnonymousClass06;
import X.C0431hn;
import com.facebook.debug.tracer.Tracer;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.systrace.Systrace;
import javax.annotation.Nullable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class NativeRunnable implements Runnable {
    @DoNotStrip
    @Nullable
    public HybridData mHybridData;
    @DoNotStrip
    @Nullable
    public volatile String mNativeExecutor;

    @DoNotStrip
    private native void nativeRun();

    static {
        C0431hn.A00("jniexecutors");
    }

    public void run() {
        Tracer.A01("%s", this);
        try {
            nativeRun();
        } finally {
            Systrace.A00(32);
        }
    }

    public String toString() {
        String str = this.mNativeExecutor;
        if (str == null) {
            return "NativeRunnable";
        }
        return AnonymousClass06.A04("NativeRunnable - ", str);
    }

    @DoNotStrip
    public NativeRunnable(@Nullable HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
