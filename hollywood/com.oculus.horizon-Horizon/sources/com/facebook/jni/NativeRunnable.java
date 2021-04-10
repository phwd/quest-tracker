package com.facebook.jni;

import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class NativeRunnable implements Runnable {
    public final HybridData mHybridData;

    public native void run();

    public NativeRunnable(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
