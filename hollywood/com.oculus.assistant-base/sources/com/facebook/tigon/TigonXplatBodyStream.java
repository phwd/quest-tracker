package com.facebook.tigon;

import X.MG;
import com.facebook.jni.HybridData;
import java.nio.ByteBuffer;

public class TigonXplatBodyStream implements TigonBodyStream {
    public final HybridData mHybridData;

    private native void reportBodyLengthNative(int i);

    private native void reportErrorNativeByteBuffer(byte[] bArr, int i);

    private native int transferBytesNative(ByteBuffer byteBuffer, int i);

    private native void writeEOMNative();

    static {
        MG.A00();
    }

    public TigonXplatBodyStream(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
