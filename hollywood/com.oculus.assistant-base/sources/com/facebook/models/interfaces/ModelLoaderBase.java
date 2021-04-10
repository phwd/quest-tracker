package com.facebook.models.interfaces;

import com.facebook.jni.HybridData;
import com.google.common.util.concurrent.ListenableFuture;

public abstract class ModelLoaderBase {
    public final HybridData mHybridData;

    public abstract ListenableFuture load(String str, long j);

    public ModelLoaderBase(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
