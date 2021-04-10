package com.facebook.assistant.oacr.utils;

import X.KJ;
import com.facebook.jni.HybridData;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.atomic.AtomicBoolean;

public class StreamHandler implements WritableByteChannel {
    public final HybridData mHybridData;
    public AtomicBoolean mIsClosed = new AtomicBoolean(false);

    private native void closeNative();

    public native void eos();

    public boolean isOpen() {
        return true;
    }

    @Override // java.nio.channels.WritableByteChannel
    public native int write(ByteBuffer byteBuffer);

    static {
        KJ.A05("oacr_utils_jni", 0);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() {
        if (this.mIsClosed.compareAndSet(false, true)) {
            eos();
            closeNative();
        }
    }

    public StreamHandler(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
