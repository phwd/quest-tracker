package com.facebook.mobileconfig;

import X.AnonymousClass0RZ;
import X.C03250cX;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public class MobileConfigMmapHandleHolder extends AnonymousClass0RZ {
    @DoNotStrip
    public final HybridData mHybridData;

    public native String getFilename();

    static {
        C03250cX.A01("mobileconfig-jni");
    }

    public MobileConfigMmapHandleHolder(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    @Override // X.AnonymousClass0RZ
    @Nullable
    public ByteBuffer getJavaByteBuffer() {
        return A00(getFilename());
    }
}
