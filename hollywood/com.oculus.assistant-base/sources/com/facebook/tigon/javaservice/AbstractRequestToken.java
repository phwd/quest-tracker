package com.facebook.tigon.javaservice;

import com.facebook.jni.HybridData;

public abstract class AbstractRequestToken {
    public HybridData mHybridData;

    public abstract void cancel();

    public abstract void changeHttpPriority(byte b, boolean z);

    public abstract void changeTigonPriority(int i);

    public AbstractRequestToken(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
