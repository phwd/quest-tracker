package com.facebook.mobileconfig;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public class MobileConfigMmapHandleHolder extends MobileConfigMmapHandle {
    @DoNotStrip
    private final HybridData mHybridData;

    public native String getFilename();

    static {
        NativeLoader.loadLibrary("mobileconfig-jni");
    }

    private MobileConfigMmapHandleHolder(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    @Override // com.facebook.mobileconfig.MobileConfigMmapHandle
    @Nullable
    public ByteBuffer getJavaByteBuffer() {
        return mmapFile(getFilename());
    }
}
