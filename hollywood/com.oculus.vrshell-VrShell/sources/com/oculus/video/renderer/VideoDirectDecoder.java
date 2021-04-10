package com.oculus.video.renderer;

import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.concurrent.locks.ReentrantLock;

public class VideoDirectDecoder {
    private static final String TAG = "com.oculus.video.renderer.VideoDirectDecoder";
    private final ReentrantLock decoderStateAccessLock;
    private volatile boolean isActive;
    private EventListener listener;
    private long mNativePtr;
    private volatile boolean needToReleaseOutputSurface;

    public interface EventListener {
        void onRenderTextureSizeChange(int i, int i2, int i3, int i4, int i5, int i6, long j);
    }

    private static native long nativeCreate();

    private static native int nativeDestroy(long j);

    private static native String nativeFetchLastRenderTextureSize(long j);

    private static native int nativeGetBuffersInCodecCount(long j);

    private static native long nativeGetNextRenderBufferPresentationTime(long j);

    private static native boolean nativeIsExtractedBufferReady(long j);

    private static native boolean nativeIsOutputStreamEnded(long j);

    private static native int nativeOnPositionReset(long j);

    private static native void nativeReleaseOutputSurface(long j);

    private static native void nativeRequestVideoBufferDrop(long j, long j2);

    private static native void nativeRequestVideoBufferRelease(long j, long j2);

    private static native void nativeSetFormat(long j, boolean z, boolean z2, String str, int i, int i2, int i3, int i4, int i5, int i6, byte[] bArr);

    private static native void nativeSetNextVideoBufferInfoFlag(long j);

    private static native void nativeSetOutputSurface(long j, Surface surface);

    private static native void nativeStart(long j);

    private static native int nativeStop(long j);

    private static native void nativeWriteBuffer(long j, ByteBuffer byteBuffer, int i, int i2, long j2, int i3, boolean z);

    private static native void nativeWriteBufferWithCrypto(long j, ByteBuffer byteBuffer, int i, int i2, long j2, int i3, boolean z, byte[] bArr, byte[] bArr2, int i4, int[] iArr, int[] iArr2, int i5);

    private static native void nativeWriteCsdBuffer(long j, String str, byte[] bArr);

    VideoDirectDecoder() {
        this.mNativePtr = 0;
        this.isActive = false;
        this.needToReleaseOutputSurface = false;
        this.listener = null;
        this.decoderStateAccessLock = new ReentrantLock();
        this.mNativePtr = nativeCreate();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        this.decoderStateAccessLock.lock();
        try {
            nativeDestroy(this.mNativePtr);
            this.isActive = false;
            this.decoderStateAccessLock.unlock();
            super.finalize();
        } catch (Throwable th) {
            this.decoderStateAccessLock.unlock();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public void start(EventListener eventListener) {
        this.listener = eventListener;
        this.decoderStateAccessLock.lock();
        try {
            this.isActive = true;
            nativeStart(this.mNativePtr);
        } finally {
            this.decoderStateAccessLock.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public int stop() {
        this.listener = null;
        this.decoderStateAccessLock.lock();
        try {
            int nativeStop = nativeStop(this.mNativePtr);
            this.isActive = false;
            if (this.needToReleaseOutputSurface) {
                nativeReleaseOutputSurface(this.mNativePtr);
            }
            this.needToReleaseOutputSurface = false;
            return nativeStop;
        } finally {
            this.decoderStateAccessLock.unlock();
        }
    }

    public void releaseOutputSurface() {
        if (this.decoderStateAccessLock.tryLock()) {
            try {
                if (!this.isActive) {
                    nativeReleaseOutputSurface(this.mNativePtr);
                    this.needToReleaseOutputSurface = false;
                } else {
                    this.needToReleaseOutputSurface = true;
                }
            } finally {
                this.decoderStateAccessLock.unlock();
            }
        } else if (!this.isActive) {
            this.decoderStateAccessLock.lock();
            try {
                nativeReleaseOutputSurface(this.mNativePtr);
                this.needToReleaseOutputSurface = false;
            } finally {
                this.decoderStateAccessLock.unlock();
            }
        } else {
            this.needToReleaseOutputSurface = true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isActive() {
        return this.isActive;
    }

    /* access modifiers changed from: package-private */
    public int onPositionReset() {
        return nativeOnPositionReset(this.mNativePtr);
    }

    /* access modifiers changed from: package-private */
    public void setOutputSurface(Surface surface) {
        nativeSetOutputSurface(this.mNativePtr, surface);
    }

    /* access modifiers changed from: package-private */
    public void setVideoExtractorInfo(boolean z, boolean z2, String str, int i, int i2, int i3, int i4, int i5, int i6, byte[] bArr) {
        nativeSetFormat(this.mNativePtr, z, z2, str, i, i2, i3, i4, i5, i6, bArr);
    }

    /* access modifiers changed from: package-private */
    public void writeBuffer(ByteBuffer byteBuffer, int i, int i2, long j, int i3, boolean z) {
        nativeWriteBuffer(this.mNativePtr, byteBuffer, i, i2, j, i3, z);
    }

    /* access modifiers changed from: package-private */
    public void writeBufferWithCrypto(ByteBuffer byteBuffer, int i, int i2, long j, int i3, boolean z, byte[] bArr, byte[] bArr2, int i4, int[] iArr, int[] iArr2, int i5) {
        nativeWriteBufferWithCrypto(this.mNativePtr, byteBuffer, i, i2, j, i3, z, bArr, bArr2, i4, iArr, iArr2, i5);
    }

    /* access modifiers changed from: package-private */
    public void writeCsdBuffer(String str, byte[] bArr) {
        nativeWriteCsdBuffer(this.mNativePtr, str, bArr);
    }

    /* access modifiers changed from: package-private */
    public void requestVideoBufferRelease(long j) {
        nativeRequestVideoBufferRelease(this.mNativePtr, j);
    }

    /* access modifiers changed from: package-private */
    public void requestVideoBufferDrop(long j) {
        nativeRequestVideoBufferDrop(this.mNativePtr, j);
    }

    /* access modifiers changed from: package-private */
    public long getNextRenderBufferPresentationTime() {
        return nativeGetNextRenderBufferPresentationTime(this.mNativePtr);
    }

    /* access modifiers changed from: package-private */
    public void setNextVideoBufferInfoFlag(int i) {
        nativeSetNextVideoBufferInfoFlag(this.mNativePtr);
    }

    /* access modifiers changed from: package-private */
    public boolean isOutputStreamEnded() {
        return nativeIsOutputStreamEnded(this.mNativePtr);
    }

    /* access modifiers changed from: package-private */
    public boolean isExtractedBufferReady() {
        return nativeIsExtractedBufferReady(this.mNativePtr);
    }

    /* access modifiers changed from: package-private */
    public int getBuffersInCodecCount() {
        return nativeGetBuffersInCodecCount(this.mNativePtr);
    }

    /* access modifiers changed from: package-private */
    public void checkRenderTextureSizeChange() {
        if (this.listener != null) {
            String nativeFetchLastRenderTextureSize = nativeFetchLastRenderTextureSize(this.mNativePtr);
            if (!TextUtils.isEmpty(nativeFetchLastRenderTextureSize)) {
                String[] split = nativeFetchLastRenderTextureSize.split(" ");
                if (split.length >= 7) {
                    try {
                        this.listener.onRenderTextureSizeChange(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]), Integer.parseInt(split[5]), Long.parseLong(split[6]));
                    } catch (Exception unused) {
                        Log.w(TAG, "Unexpected render size on output format change");
                    }
                }
            }
        }
    }
}
