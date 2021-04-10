package com.facebook.mobileconfig;

import X.AbstractC0163Fj;
import X.KV;
import com.facebook.jni.HybridData;
import java.nio.ByteBuffer;

public class MobileConfigMmapHandleHolder extends AbstractC0163Fj {
    public final HybridData mHybridData;

    public native String getFilename();

    static {
        KV.A01("mobileconfig-jni");
    }

    public MobileConfigMmapHandleHolder(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    @Override // X.AbstractC0163Fj
    public ByteBuffer getJavaByteBuffer() {
        return A00(getFilename());
    }
}
