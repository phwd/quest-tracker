package com.facebook.assistant.oacr;

import X.KJ;
import com.facebook.jni.HybridData;

public class ResourceApi {
    public final HybridData mHybridData;

    public native void forceDownload();

    static {
        KJ.A05("oacr_api_jni", 0);
    }

    public ResourceApi(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
