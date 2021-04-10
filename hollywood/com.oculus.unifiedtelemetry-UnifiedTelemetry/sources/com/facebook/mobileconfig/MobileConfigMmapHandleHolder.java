package com.facebook.mobileconfig;

import X.C0431hn;
import X.RW;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public class MobileConfigMmapHandleHolder extends RW {
    @DoNotStrip
    public final HybridData mHybridData;

    public native String getFilename();

    static {
        C0431hn.A00("mobileconfig-jni");
    }

    public MobileConfigMmapHandleHolder(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    @Override // X.RW
    @Nullable
    public ByteBuffer getJavaByteBuffer() {
        return A00(getFilename());
    }
}
