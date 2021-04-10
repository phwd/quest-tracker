package com.facebook.cameracore.mediapipeline.filterlib.cpuframes;

import X.C03160cK;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import java.nio.ByteBuffer;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class CpuFrameRenderer {
    public final HybridData mHybridData;

    public static native HybridData initHybrid();

    private native void uploadTexturesFromI420(int i, int i2, int i3, int i4, ByteBuffer byteBuffer, int i5, int i6, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i7, int i8);

    private native void uploadTexturesFromNV21(int i, int i2, int i3, int i4, byte[] bArr);

    static {
        C03160cK.A05("mediapipeline-filterlib", 0);
    }
}
