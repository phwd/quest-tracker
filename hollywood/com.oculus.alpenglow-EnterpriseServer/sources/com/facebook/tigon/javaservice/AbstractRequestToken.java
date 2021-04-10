package com.facebook.tigon.javaservice;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class AbstractRequestToken {
    @DoNotStrip
    public HybridData mHybridData;

    @DoNotStrip
    public abstract void cancel();

    @DoNotStrip
    public abstract void changeHttpPriority(byte b, boolean z);

    @DoNotStrip
    public abstract void changeTigonPriority(int i);

    public AbstractRequestToken(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
