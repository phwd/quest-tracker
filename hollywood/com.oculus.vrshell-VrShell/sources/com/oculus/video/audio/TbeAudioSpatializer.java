package com.oculus.video.audio;

import android.media.AudioTrack;
import android.media.MediaFormat;
import com.oculus.android.exoplayer2.Format;
import com.oculus.video.audio.AudioSpatializer;
import java.nio.ByteBuffer;

public class TbeAudioSpatializer implements AudioSpatializer {
    private static final int BYTES_PER_SAMPLE = 2;
    private static final int ENGINE_BUFFER_SAMPLES_PER_CHANNEL = 960;
    private static final float EPSILON = 1.0E-5f;
    private static final int NUM_OUTPUT_CHANNELS = 2;
    private static final int SPAT_QUEUE_SAMPLES_PER_CHANNEL = 8192;
    private AudioChannelLayout mAudioChannelLayout;
    private AudioSpatializerController mAudioSpatializerController;
    private float mGain = 1.0f;
    private long mNativePtr;
    private AudioSpatializer.PlayState mPlayState = AudioSpatializer.PlayState.UNKNOWN;
    private float mSampleRate;
    private final ByteBuffer mTempBuffer = ByteBuffer.allocateDirect(81920);

    private static native void nativeConfigure(long j, float f, int i, int i2, boolean z, boolean z2, boolean z3, String str, int i3);

    private static native int nativeConvert6To4Channel(ByteBuffer byteBuffer, int i, int i2, ByteBuffer byteBuffer2, String str);

    private static native long nativeCreate();

    private static native void nativeDestroy(long j);

    private static native void nativeEnableFocus(long j, boolean z);

    private static native int nativeGetAudioMix(long j, ByteBuffer byteBuffer);

    private static native int nativeGetBufferUnderrunCount(long j);

    private static native long nativeGetPlaybackHeadPosition(long j);

    private static native void nativeHandleEndOfStream(long j);

    private static native boolean nativeInitialize(long j);

    private static native boolean nativeIsInitialized(long j);

    private static native void nativePause(long j);

    private static native void nativePlay(long j);

    private static native void nativeReset(long j);

    private static native void nativeSetFocusProperties(long j, float f, float f2);

    private static native void nativeSetFocusWidthDegrees(long j, float f);

    private native synchronized void nativeSetListenerOrientation(long j, float[] fArr);

    private static native void nativeSetOffFocusLeveldB(long j, float f);

    private static native void nativeSetVolume(long j, float f);

    private static native int nativeWrite(long j, ByteBuffer byteBuffer, int i, int i2, String str);

    private static native int nativeWriteTbe442(long j, ByteBuffer byteBuffer, int i, int i2, long j2, int i3);

    private static native int nativeWriteVirtualizer(long j, ByteBuffer byteBuffer, int i, int i2);

    @Override // com.oculus.video.audio.AudioSpatializer
    public int getAudioMixBufferSize() {
        return 3840;
    }

    public TbeAudioSpatializer(AudioChannelLayout audioChannelLayout) {
        this.mAudioChannelLayout = audioChannelLayout;
        this.mNativePtr = nativeCreate();
    }

    public static boolean handlesAudioChannelLayout(AudioChannelLayout audioChannelLayout) {
        return audioChannelLayout == AudioChannelLayout.MONO || audioChannelLayout == AudioChannelLayout.STEREO || audioChannelLayout == AudioChannelLayout.TBE_4 || audioChannelLayout == AudioChannelLayout.TBE_4_2 || audioChannelLayout == AudioChannelLayout.TBE_6 || audioChannelLayout == AudioChannelLayout.TBE_6_2 || audioChannelLayout == AudioChannelLayout.TBE_8 || audioChannelLayout == AudioChannelLayout.TBE_8_2 || audioChannelLayout == AudioChannelLayout.AMBIX_4 || audioChannelLayout == AudioChannelLayout.AMBIX_4_2 || audioChannelLayout == AudioChannelLayout.AMBIX_9 || audioChannelLayout == AudioChannelLayout.AMBIX_9_2 || audioChannelLayout == AudioChannelLayout.AMBIX_16 || audioChannelLayout == AudioChannelLayout.AMBIX_16_2 || audioChannelLayout == AudioChannelLayout.TBE_4_4_2 || audioChannelLayout == AudioChannelLayout.S5_1 || audioChannelLayout == AudioChannelLayout.S7_1;
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public AudioSpatializerController getController() {
        return this.mAudioSpatializerController;
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public void setAudioSpatializerController(AudioSpatializerController audioSpatializerController) {
        this.mAudioSpatializerController = audioSpatializerController;
    }

    private void maybeResetAudioChannelLayout(MediaFormat mediaFormat) throws AudioSpatializer.InitializationException {
        if (this.mAudioChannelLayout != AudioChannelLayout.TBE_4_4_2) {
            int integer = mediaFormat.getInteger("channel-count");
            AudioChannelLayout channelLayoutForChannelCount = AudioChannelLayout.getChannelLayoutForChannelCount(integer);
            if (channelLayoutForChannelCount != AudioChannelLayout.UNKNOWN) {
                this.mAudioChannelLayout = channelLayoutForChannelCount;
            }
            if (this.mAudioChannelLayout.totalChannelCount != integer) {
                throw new AudioSpatializer.InitializationException("Channel layout not matching channel count");
            } else if (!handlesAudioChannelLayout(this.mAudioChannelLayout)) {
                throw new AudioSpatializer.InitializationException("Unsupported channel layout");
            }
        }
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public synchronized void configure(MediaFormat mediaFormat, boolean z) throws AudioSpatializer.InitializationException {
        maybeResetAudioChannelLayout(mediaFormat);
        float integer = (float) mediaFormat.getInteger("sample-rate");
        if (Math.abs(this.mSampleRate - integer) < EPSILON) {
            reset();
            return;
        }
        this.mSampleRate = integer;
        nativeConfigure(this.mNativePtr, (float) mediaFormat.getInteger("sample-rate"), 8192, ENGINE_BUFFER_SAMPLES_PER_CHANNEL, z, true, true, this.mAudioChannelLayout.key, Math.max(0, AudioTrack.getMinBufferSize((int) this.mSampleRate, 12, 2)) / 2);
        if (nativeInitialize(this.mNativePtr)) {
            nativeSetVolume(this.mNativePtr, this.mGain);
            if (this.mAudioSpatializerController != null) {
                nativeEnableFocus(this.mNativePtr, this.mAudioSpatializerController.getFocusEnabled());
                nativeSetOffFocusLeveldB(this.mNativePtr, this.mAudioSpatializerController.getOffFocusLeveldB());
                nativeSetFocusWidthDegrees(this.mNativePtr, this.mAudioSpatializerController.getFocusWidthDegrees());
            }
            return;
        }
        throw new AudioSpatializer.InitializationException();
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public synchronized boolean isInitialized() {
        return nativeIsInitialized(this.mNativePtr);
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public synchronized int processBuffer(ByteBuffer byteBuffer, long j, int i, int i2) throws AudioSpatializer.UnsupportedAudioChannelLayoutException {
        if (handlesAudioChannelLayout(this.mAudioChannelLayout)) {
            switch (this.mAudioChannelLayout) {
                case AMBIX_4:
                case TBE_4:
                    if (i2 == 6) {
                        this.mTempBuffer.clear();
                        return (nativeWrite(this.mNativePtr, this.mTempBuffer, 0, nativeConvert6To4Channel(byteBuffer, byteBuffer.position(), byteBuffer.remaining(), this.mTempBuffer, this.mAudioChannelLayout.key), this.mAudioChannelLayout.key) / 4) * 6;
                    }
                    return nativeWrite(this.mNativePtr, byteBuffer, byteBuffer.position(), byteBuffer.remaining(), this.mAudioChannelLayout.key);
                case TBE_4_4_2:
                    if (i2 == 6) {
                        this.mTempBuffer.clear();
                        return (nativeWriteTbe442(this.mNativePtr, this.mTempBuffer, 0, nativeConvert6To4Channel(byteBuffer, byteBuffer.position(), byteBuffer.remaining(), this.mTempBuffer, this.mAudioChannelLayout.key), j, i) / 4) * 6;
                    }
                    return nativeWriteTbe442(this.mNativePtr, byteBuffer, byteBuffer.position(), byteBuffer.remaining(), j, i);
                case MONO:
                case STEREO:
                case S5_1:
                case S7_1:
                    return nativeWriteVirtualizer(this.mNativePtr, byteBuffer, byteBuffer.position(), byteBuffer.remaining());
                default:
                    return nativeWrite(this.mNativePtr, byteBuffer, byteBuffer.position(), byteBuffer.remaining(), this.mAudioChannelLayout.key);
            }
        } else {
            throw new AudioSpatializer.UnsupportedAudioChannelLayoutException(this.mAudioChannelLayout);
        }
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public synchronized void updateListenerOrientation() {
        if (handlesAudioChannelLayout(this.mAudioChannelLayout)) {
            if (this.mAudioSpatializerController != null) {
                float[] headOrientationMatrix = this.mAudioSpatializerController.getHeadOrientationMatrix();
                int i = AnonymousClass1.$SwitchMap$com$oculus$video$audio$AudioChannelLayout[this.mAudioChannelLayout.ordinal()];
                if (!(i == 4 || i == 5)) {
                    headOrientationMatrix[3] = 0.0f;
                    headOrientationMatrix[7] = 0.0f;
                    headOrientationMatrix[11] = 0.0f;
                }
                nativeSetListenerOrientation(this.mNativePtr, headOrientationMatrix);
            }
        }
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public synchronized int getAudioMix(ByteBuffer byteBuffer) {
        return nativeGetAudioMix(this.mNativePtr, byteBuffer);
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public synchronized long getPlaybackHeadPosition() {
        return nativeGetPlaybackHeadPosition(this.mNativePtr);
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public synchronized void setVolume(float f) {
        this.mGain = f;
        nativeSetVolume(this.mNativePtr, f);
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public synchronized void enableFocus(boolean z) {
        nativeEnableFocus(this.mNativePtr, z);
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public synchronized void setFocusProperties(float f, float f2) {
        nativeSetFocusProperties(this.mNativePtr, f, f2);
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public synchronized void setOffFocusLeveldB(float f) {
        nativeSetOffFocusLeveldB(this.mNativePtr, f);
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public synchronized void setFocusWidthDegrees(float f) {
        nativeSetFocusWidthDegrees(this.mNativePtr, f);
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public synchronized void handleEndOfStream() {
        nativeHandleEndOfStream(this.mNativePtr);
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public synchronized void play() {
        if (isInitialized() && this.mPlayState != AudioSpatializer.PlayState.PLAYING) {
            nativePlay(this.mNativePtr);
            this.mPlayState = AudioSpatializer.PlayState.PLAYING;
        }
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public synchronized void pause() {
        if (isInitialized() && this.mPlayState != AudioSpatializer.PlayState.PAUSED) {
            nativePause(this.mNativePtr);
            this.mPlayState = AudioSpatializer.PlayState.PAUSED;
            int nativeGetBufferUnderrunCount = nativeGetBufferUnderrunCount(this.mNativePtr);
            if (this.mAudioSpatializerController != null && nativeGetBufferUnderrunCount > 0) {
                this.mAudioSpatializerController.onBufferUnderrun(nativeGetBufferUnderrunCount);
            }
        }
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public AudioSpatializer.PlayState getPlayState() {
        return this.mPlayState;
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public synchronized void reset() {
        pause();
        nativeReset(this.mNativePtr);
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public void release() {
        destroy();
    }

    private synchronized void destroy() {
        if (this.mNativePtr != 0) {
            nativeDestroy(this.mNativePtr);
            this.mNativePtr = 0;
        }
    }

    @Override // com.oculus.video.audio.AudioSpatializer
    public boolean supportsFormat(Format format) {
        switch (format.sampleRate) {
            case 44100:
            case 48000:
            case 96000:
            case 192000:
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        destroy();
        super.finalize();
    }
}
