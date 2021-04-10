package com.facebook.common.jniexecutors;

import X.AnonymousClass08;
import X.KV;
import com.facebook.debug.tracer.Tracer;
import com.facebook.jni.HybridData;

public class NativeRunnable implements Runnable {
    public HybridData mHybridData;
    public volatile String mNativeExecutor;

    private native void nativeRun();

    static {
        KV.A01("jniexecutors");
    }

    public void run() {
        Tracer.A02("%s", 1, this);
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
        return AnonymousClass08.A04("NativeRunnable - ", str);
    }

    public NativeRunnable(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
