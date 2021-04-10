package com.facebook.speech;

import X.KJ;
import com.facebook.jni.HybridData;
import java.nio.ByteBuffer;

public class SpeechOpusDecoder {
    public HybridData mHybridData;

    private native HybridData initHybrid();

    private native int nativeDecode(ByteBuffer byteBuffer, int i);

    private native int nativeGetBufferSize();

    private native boolean nativeInitialize(int i, int i2);

    private native int nativeRead(ByteBuffer byteBuffer, int i);

    static {
        KJ.A05("speechopus", 0);
    }
}
