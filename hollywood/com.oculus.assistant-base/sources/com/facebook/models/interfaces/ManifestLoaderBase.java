package com.facebook.models.interfaces;

import com.facebook.jni.HybridData;

public abstract class ManifestLoaderBase {
    public final HybridData mHybridData;

    public ManifestLoaderBase(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
