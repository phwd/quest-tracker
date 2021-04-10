package com.oculus.video.audio;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import com.oculus.android.exoplayer2.C;
import com.oculus.video.audio.AudioSpatializer;

@TargetApi(16)
public class SpatialAudioTrack {
    private static final int BYTES_PER_FRAME = 2;
    public static final long CURRENT_POSITION_NOT_SET = Long.MIN_VALUE;
    private static final int DISCONTINUITY_THRESHOLD_US = 200000;
    private static final int MAX_PLAYHEAD_OFFSET_COUNT = 10;
    private static final int MIN_PLAYHEAD_OFFSET_SAMPLE_INTERVAL_US = 30000;
    public static final int RESULT_BUFFER_CONSUMED = 2;
    public static final int RESULT_POSITION_DISCONTINUITY = 1;
    private static final int START_IN_SYNC = 1;
    private static final int START_NEED_SYNC = 2;
    private static final int START_NOT_SET = 0;
    private final AudioSpatializer mAudioSpatializer;
    private int mBufferBytesRemaining;
    private int mChannelCount;
    private int mCurrentBufferOffset;
    private boolean mInitialized;
    private long mLastPlayheadSampleTimeUs;
    private int mNextPlayheadOffsetIndex;
    private int mPlayheadOffsetCount;
    private final long[] mPlayheadOffsets;
    private int mSampleRate;
    private long mSmoothedPlayheadOffsetUs;
    private int mStartMediaTimeState;
    private long mStartMediaTimeUs;
    private long mSubmittedPcmBytes;
    private final int mTrack;

    public SpatialAudioTrack(AudioSpatializer audioSpatializer) {
        this(audioSpatializer, 0);
    }

    public SpatialAudioTrack(AudioSpatializer audioSpatializer, int i) {
        this.mPlayheadOffsets = new long[10];
        this.mAudioSpatializer = audioSpatializer;
        this.mTrack = i;
        this.mInitialized = false;
        this.mStartMediaTimeState = 0;
    }

    public AudioSpatializer getAudioSpatializer() {
        return this.mAudioSpatializer;
    }

    public void configure(MediaFormat mediaFormat, boolean z) throws AudioSpatializer.InitializationException {
        this.mSampleRate = mediaFormat.getInteger("sample-rate");
        this.mChannelCount = mediaFormat.getInteger("channel-count");
        if (z) {
            this.mAudioSpatializer.configure(mediaFormat, true);
        }
        this.mInitialized = true;
    }

    public boolean isInitialized() {
        return this.mAudioSpatializer.isInitialized();
    }

    public AudioSpatializer.PlayState getPlayState() {
        return this.mAudioSpatializer.getPlayState();
    }

    public void play() {
        this.mAudioSpatializer.play();
    }

    public void pause() {
        resetSyncParams();
        this.mAudioSpatializer.pause();
    }

    public boolean hasPendingData() {
        return isInitialized() && pcmBytesToFrames(this.mSubmittedPcmBytes) > this.mAudioSpatializer.getPlaybackHeadPosition();
    }

    public boolean canEndStream() {
        return !hasPendingData() || pcmBytesToFrames(this.mSubmittedPcmBytes) - this.mAudioSpatializer.getPlaybackHeadPosition() < 200000;
    }

    public void setVolume(float f) {
        this.mAudioSpatializer.setVolume(f);
    }

    public long getCurrentPositionUs() {
        long j;
        long j2;
        if (!hasCurrentPositionUs()) {
            return Long.MIN_VALUE;
        }
        if (this.mAudioSpatializer.getPlayState() == AudioSpatializer.PlayState.PLAYING) {
            syncPlayheadSmoothingParams();
        }
        long nanoTime = System.nanoTime() / 1000;
        if (this.mPlayheadOffsetCount == 0) {
            j2 = getPlaybackHeadPositionUs();
            j = this.mStartMediaTimeUs;
        } else {
            j2 = nanoTime + this.mSmoothedPlayheadOffsetUs;
            j = this.mStartMediaTimeUs;
        }
        return j2 + j;
    }

    private void syncPlayheadSmoothingParams() {
        long playbackHeadPositionUs = getPlaybackHeadPositionUs();
        if (playbackHeadPositionUs != 0) {
            long nanoTime = System.nanoTime() / 1000;
            if (nanoTime - this.mLastPlayheadSampleTimeUs >= 30000) {
                long[] jArr = this.mPlayheadOffsets;
                int i = this.mNextPlayheadOffsetIndex;
                jArr[i] = playbackHeadPositionUs - nanoTime;
                this.mNextPlayheadOffsetIndex = (i + 1) % 10;
                int i2 = this.mPlayheadOffsetCount;
                if (i2 < 10) {
                    this.mPlayheadOffsetCount = i2 + 1;
                }
                this.mLastPlayheadSampleTimeUs = nanoTime;
                this.mSmoothedPlayheadOffsetUs = 0;
                int i3 = 0;
                while (true) {
                    int i4 = this.mPlayheadOffsetCount;
                    if (i3 < i4) {
                        this.mSmoothedPlayheadOffsetUs += this.mPlayheadOffsets[i3] / ((long) i4);
                        i3++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private void resetSyncParams() {
        this.mSmoothedPlayheadOffsetUs = 0;
        this.mPlayheadOffsetCount = 0;
        this.mNextPlayheadOffsetIndex = 0;
        this.mLastPlayheadSampleTimeUs = 0;
    }

    private long getPlaybackHeadPositionUs() {
        return (this.mAudioSpatializer.getPlaybackHeadPosition() * C.MICROS_PER_SECOND) / ((long) this.mSampleRate);
    }

    public void reset() {
        this.mSubmittedPcmBytes = 0;
        this.mBufferBytesRemaining = 0;
        this.mStartMediaTimeState = 0;
        this.mStartMediaTimeUs = 0;
        resetSyncParams();
        this.mAudioSpatializer.reset();
    }

    public void release() {
        this.mAudioSpatializer.release();
        this.mInitialized = false;
    }

    public void handleDiscontinuity() {
        if (this.mStartMediaTimeState == 1) {
            this.mStartMediaTimeState = 2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int handleBuffer(java.nio.ByteBuffer r9, int r10, int r11, long r12) throws com.oculus.video.audio.AudioSpatializer.WriteException, com.oculus.video.audio.AudioSpatializer.UnsupportedAudioChannelLayoutException {
        /*
        // Method dump skipped, instructions count: 127
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.audio.SpatialAudioTrack.handleBuffer(java.nio.ByteBuffer, int, int, long):int");
    }

    private int bytesPerSample() {
        return this.mChannelCount * 2;
    }

    private long pcmBytesToFrames(long j) {
        if (this.mChannelCount != 0) {
            return j / ((long) bytesPerSample());
        }
        return 0;
    }

    private long framesToDurationUs(long j) {
        int i = this.mSampleRate;
        if (i != 0) {
            return (j * C.MICROS_PER_SECOND) / ((long) i);
        }
        return 0;
    }

    public void handleEndOfStream() {
        if (isInitialized()) {
            this.mAudioSpatializer.handleEndOfStream();
        }
    }

    private boolean hasCurrentPositionUs() {
        return isInitialized() && this.mStartMediaTimeState != 0;
    }

    public void updateListenerOrientation() {
        this.mAudioSpatializer.updateListenerOrientation();
    }
}
