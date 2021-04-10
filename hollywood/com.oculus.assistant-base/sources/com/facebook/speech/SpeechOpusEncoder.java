package com.facebook.speech;

import X.KJ;
import com.facebook.jni.HybridData;
import java.nio.ByteBuffer;

public class SpeechOpusEncoder {
    public HybridData mHybridData = initHybrid(16000);

    private native HybridData initHybrid(int i);

    private native int nativeEncode(ByteBuffer byteBuffer, int i, ByteBuffer byteBuffer2);

    private native int nativeFlush(ByteBuffer byteBuffer);

    static {
        KJ.A05("speechopus", 0);
    }
}
