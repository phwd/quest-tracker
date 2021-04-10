package com.facebook.mobileconfig;

import X.AnonymousClass0SV;
import X.C05400jG;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public class MobileConfigMmapHandleHolder extends AnonymousClass0SV {
    @DoNotStrip
    public final HybridData mHybridData;

    public native String getFilename();

    static {
        C05400jG.A00("mobileconfig-jni");
    }

    public MobileConfigMmapHandleHolder(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    @Override // X.AnonymousClass0SV
    @Nullable
    public ByteBuffer getJavaByteBuffer() {
        return A00(getFilename());
    }
}
