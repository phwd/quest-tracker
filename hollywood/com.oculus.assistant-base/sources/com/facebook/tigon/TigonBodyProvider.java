package com.facebook.tigon;

import com.facebook.jni.HybridData;

public abstract class TigonBodyProvider {
    public HybridData mHybridData;

    public abstract void beginStream(TigonBodyStream tigonBodyStream);
}
