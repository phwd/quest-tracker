package com.facebook.animated.gif;

import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;
import java.nio.ByteBuffer;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class GifImage {
    @DoNotStrip
    public long mNativeContext;

    @DoNotStrip
    public static native GifImage nativeCreateFromDirectByteBuffer(ByteBuffer byteBuffer, int i, boolean z);

    @DoNotStrip
    public static native GifImage nativeCreateFromFileDescriptor(int i, int i2, boolean z);

    @DoNotStrip
    public static native GifImage nativeCreateFromNativeMemory(long j, int i, int i2, boolean z);

    @DoNotStrip
    private native void nativeDispose();

    @DoNotStrip
    private native void nativeFinalize();

    @DoNotStrip
    private native int nativeGetDuration();

    @DoNotStrip
    private native GifFrame nativeGetFrame(int i);

    @DoNotStrip
    private native int nativeGetFrameCount();

    @DoNotStrip
    private native int[] nativeGetFrameDurations();

    @DoNotStrip
    private native int nativeGetHeight();

    @DoNotStrip
    private native int nativeGetLoopCount();

    @DoNotStrip
    private native int nativeGetSizeInBytes();

    @DoNotStrip
    private native int nativeGetWidth();

    @DoNotStrip
    private native boolean nativeIsAnimated();

    public void finalize() {
        nativeFinalize();
    }

    @DoNotStrip
    public GifImage() {
    }

    @DoNotStrip
    public GifImage(long j) {
        this.mNativeContext = j;
    }
}
