package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class TigonServiceHolder {
    @DoNotStrip
    protected HybridData mHybridData;

    protected TigonServiceHolder(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
