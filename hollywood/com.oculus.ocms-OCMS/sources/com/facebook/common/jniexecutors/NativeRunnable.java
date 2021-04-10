package com.facebook.common.jniexecutors;

import com.facebook.common.runnablename.ProvidesRunnableName;
import com.facebook.debug.tracer.Tracer;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;
import javax.annotation.Nullable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class NativeRunnable implements Runnable, ProvidesRunnableName {
    @DoNotStrip
    @Nullable
    protected HybridData mHybridData;
    @DoNotStrip
    @Nullable
    protected volatile String mNativeExecutor;

    @DoNotStrip
    private native void nativeRun();

    @Override // com.facebook.common.runnablename.ProvidesInnerRunnable
    public Object getInnerRunnable() {
        return this;
    }

    static {
        NativeLoader.loadLibrary("jniexecutors");
    }

    @DoNotStrip
    protected NativeRunnable(@Nullable HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public void run() {
        Tracer.startTracer("%s", this);
        try {
            nativeRun();
        } finally {
            Tracer.stopTracer();
        }
    }

    public String toString() {
        return getRunnableName();
    }

    @Override // com.facebook.common.runnablename.ProvidesRunnableName
    public String getRunnableName() {
        String str = this.mNativeExecutor;
        if (str == null) {
            return "NativeRunnable";
        }
        return "NativeRunnable - " + str;
    }
}
