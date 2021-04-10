package com.oculus.android.exoplayer2.audio;

import com.oculus.android.exoplayer2.audio.AudioProcessor;
import com.oculus.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class TrimmingAudioProcessor implements AudioProcessor {
    private ByteBuffer buffer = EMPTY_BUFFER;
    private int channelCount = -1;
    private byte[] endBuffer;
    private int endBufferSize;
    private boolean inputEnded;
    private boolean isActive;
    private ByteBuffer outputBuffer = EMPTY_BUFFER;
    private int pendingTrimStartBytes;
    private int sampleRateHz;
    private int trimEndSamples;
    private int trimStartSamples;

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public int getOutputEncoding() {
        return 2;
    }

    public void setTrimSampleCount(int i, int i2) {
        this.trimStartSamples = i;
        this.trimEndSamples = i2;
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public boolean configure(int i, int i2, int i3) throws AudioProcessor.UnhandledFormatException {
        if (i3 == 2) {
            this.channelCount = i2;
            this.sampleRateHz = i;
            int i4 = this.trimEndSamples;
            this.endBuffer = new byte[(i4 * i2 * 2)];
            this.endBufferSize = 0;
            int i5 = this.trimStartSamples;
            this.pendingTrimStartBytes = i2 * i5 * 2;
            boolean z = this.isActive;
            this.isActive = (i5 == 0 && i4 == 0) ? false : true;
            if (z != this.isActive) {
                return true;
            }
            return false;
        }
        throw new AudioProcessor.UnhandledFormatException(i, i2, i3);
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public boolean isActive() {
        return this.isActive;
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public int getOutputChannelCount() {
        return this.channelCount;
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public int getOutputSampleRateHz() {
        return this.sampleRateHz;
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i = limit - position;
        int min = Math.min(i, this.pendingTrimStartBytes);
        this.pendingTrimStartBytes -= min;
        byteBuffer.position(position + min);
        if (this.pendingTrimStartBytes <= 0) {
            int i2 = i - min;
            int length = (this.endBufferSize + i2) - this.endBuffer.length;
            if (this.buffer.capacity() < length) {
                this.buffer = ByteBuffer.allocateDirect(length).order(ByteOrder.nativeOrder());
            } else {
                this.buffer.clear();
            }
            int constrainValue = Util.constrainValue(length, 0, this.endBufferSize);
            this.buffer.put(this.endBuffer, 0, constrainValue);
            int constrainValue2 = Util.constrainValue(length - constrainValue, 0, i2);
            byteBuffer.limit(byteBuffer.position() + constrainValue2);
            this.buffer.put(byteBuffer);
            byteBuffer.limit(limit);
            int i3 = i2 - constrainValue2;
            this.endBufferSize -= constrainValue;
            byte[] bArr = this.endBuffer;
            System.arraycopy(bArr, constrainValue, bArr, 0, this.endBufferSize);
            byteBuffer.get(this.endBuffer, this.endBufferSize, i3);
            this.endBufferSize += i3;
            this.buffer.flip();
            this.outputBuffer = this.buffer;
        }
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public void queueEndOfStream() {
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
        return this.inputEnded && this.outputBuffer == EMPTY_BUFFER;
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public void flush() {
        this.outputBuffer = EMPTY_BUFFER;
        this.inputEnded = false;
        this.pendingTrimStartBytes = 0;
        this.endBufferSize = 0;
    }

    @Override // com.oculus.android.exoplayer2.audio.AudioProcessor
    public void reset() {
        flush();
        this.buffer = EMPTY_BUFFER;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.endBuffer = null;
    }
}
