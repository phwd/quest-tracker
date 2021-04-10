package com.oculus.android.exoplayer2.audio;

import android.support.v4.media.session.PlaybackStateCompat;
import com.oculus.android.exoplayer2.audio.AudioProcessor;
import com.oculus.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public final class SonicAudioProcessor implements AudioProcessor {
    private static final float CLOSE_THRESHOLD = 0.01f;
    public static final float MAXIMUM_PITCH = 8.0f;
    public static final float MAXIMUM_SPEED = 8.0f;
    public static final float MINIMUM_PITCH = 0.1f;
    public static final float MINIMUM_SPEED = 0.1f;
    private static final int MIN_BYTES_FOR_SPEEDUP_CALCULATION = 1024;
    public static final int SAMPLE_RATE_NO_CHANGE = -1;
    private ByteBuffer buffer = EMPTY_BUFFER;
    private int channelCount = -1;
    private long inputBytes;
    private boolean inputEnded;
    private ByteBuffer outputBuffer = EMPTY_BUFFER;
    private long outputBytes;
    private int outputSampleRateHz = -1;
    private int pendingOutputSampleRateHz = -1;
    private float pitch = 1.0f;
    private int sampleRateHz = -1;
    private ShortBuffer shortBuffer = this.buffer.asShortBuffer();
    private Sonic sonic;
    private float speed = 1.0f;

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public int getOutputEncoding() {
        return 2;
    }

    public float setSpeed(float f) {
        this.speed = Util.constrainValue(f, 0.1f, 8.0f);
        return this.speed;
    }

    public float setPitch(float f) {
        this.pitch = Util.constrainValue(f, 0.1f, 8.0f);
        return f;
    }

    public void setOutputSampleRateHz(int i) {
        this.pendingOutputSampleRateHz = i;
    }

    public long scaleDurationForSpeedup(long j) {
        long j2 = this.outputBytes;
        if (j2 < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            return (long) (((double) this.speed) * ((double) j));
        }
        int i = this.outputSampleRateHz;
        int i2 = this.sampleRateHz;
        if (i == i2) {
            return Util.scaleLargeTimestamp(j, this.inputBytes, j2);
        }
        return Util.scaleLargeTimestamp(j, this.inputBytes * ((long) i), j2 * ((long) i2));
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public boolean configure(int i, int i2, int i3) throws AudioProcessor.UnhandledFormatException {
        if (i3 == 2) {
            int i4 = this.pendingOutputSampleRateHz;
            if (i4 == -1) {
                i4 = i;
            }
            if (this.sampleRateHz == i && this.channelCount == i2 && this.outputSampleRateHz == i4) {
                return false;
            }
            this.sampleRateHz = i;
            this.channelCount = i2;
            this.outputSampleRateHz = i4;
            return true;
        }
        throw new AudioProcessor.UnhandledFormatException(i, i2, i3);
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public boolean isActive() {
        return Math.abs(this.speed - 1.0f) >= CLOSE_THRESHOLD || Math.abs(this.pitch - 1.0f) >= CLOSE_THRESHOLD || this.outputSampleRateHz != this.sampleRateHz;
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public int getOutputChannelCount() {
        return this.channelCount;
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public int getOutputSampleRateHz() {
        return this.outputSampleRateHz;
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            ShortBuffer asShortBuffer = byteBuffer.asShortBuffer();
            int remaining = byteBuffer.remaining();
            this.inputBytes += (long) remaining;
            this.sonic.queueInput(asShortBuffer);
            byteBuffer.position(byteBuffer.position() + remaining);
        }
        int samplesAvailable = this.sonic.getSamplesAvailable() * this.channelCount * 2;
        if (samplesAvailable > 0) {
            if (this.buffer.capacity() < samplesAvailable) {
                this.buffer = ByteBuffer.allocateDirect(samplesAvailable).order(ByteOrder.nativeOrder());
                this.shortBuffer = this.buffer.asShortBuffer();
            } else {
                this.buffer.clear();
                this.shortBuffer.clear();
            }
            this.sonic.getOutput(this.shortBuffer);
            this.outputBytes += (long) samplesAvailable;
            this.buffer.limit(samplesAvailable);
            this.outputBuffer = this.buffer;
        }
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public void queueEndOfStream() {
        this.sonic.queueEndOfStream();
        this.inputEnded = true;
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public ByteBuffer getOutput() {
        ByteBuffer byteBuffer = this.outputBuffer;
        this.outputBuffer = EMPTY_BUFFER;
        return byteBuffer;
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public boolean isEnded() {
        Sonic sonic2;
        return this.inputEnded && ((sonic2 = this.sonic) == null || sonic2.getSamplesAvailable() == 0);
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public void flush() {
        this.sonic = new Sonic(this.sampleRateHz, this.channelCount, this.speed, this.pitch, this.outputSampleRateHz);
        this.outputBuffer = EMPTY_BUFFER;
        this.inputBytes = 0;
        this.outputBytes = 0;
        this.inputEnded = false;
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public void reset() {
        this.sonic = null;
        this.buffer = EMPTY_BUFFER;
        this.shortBuffer = this.buffer.asShortBuffer();
        this.outputBuffer = EMPTY_BUFFER;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.outputSampleRateHz = -1;
        this.inputBytes = 0;
        this.outputBytes = 0;
        this.inputEnded = false;
        this.pendingOutputSampleRateHz = -1;
    }
}
