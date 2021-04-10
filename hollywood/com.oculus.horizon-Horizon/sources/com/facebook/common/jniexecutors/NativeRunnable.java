package com.facebook.common.jniexecutors;

import X.AnonymousClass006;
import X.C03250cX;
import com.facebook.debug.tracer.Tracer;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
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
        C03250cX.A01("jniexecutors");
    }

    public void run() {
        Tracer.A02("%s", this);
        try {
            nativeRun();
        } finally {
            Tracer.A00();
        }
    }

    public String toString() {
        String str = this.mNativeExecutor;
        if (str == null) {
            return "NativeRunnable";
        }
        return AnonymousClass006.A05("NativeRunnable - ", str);
    }

    @DoNotStrip
    public NativeRunnable(@Nullable HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
