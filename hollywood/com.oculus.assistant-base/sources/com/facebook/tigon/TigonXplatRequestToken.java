package com.facebook.tigon;

import com.facebook.jni.HybridData;

public class TigonXplatRequestToken implements TigonRequestToken {
    public final HybridData mHybridData;

    public native void cancel();

    public native void cancelIfNotInflight();

    public native void changeHttpPriority(int i, boolean z);

    public native void changeTigonPriority(int i);

    public TigonXplatRequestToken(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
